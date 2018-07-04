package nc.bs.so.m30.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.ref.po.m20.POm20ServicesUtil;
import nc.itf.so.m30.ref.to.m5a.TOm5AServicesUtil;
import nc.itf.uap.pf.IPFMetaModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.pfflow04.MessagedriveVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.SaleOrderTranTypeUtil;
import nc.vo.so.m30trantype.enumeration.DirectType;
import nc.vo.so.pub.votools.SoVoTools;

/**
 *
 * @description
 * 取消审批——检查是否配置消息驱动(PUSH5AOR20)规则(before):
 * <ul>
 * <li>没有配置：检查下游已安排生成调入申请/请购单不允许取消审批
 * <li>配置驱动：并且直运类型为直运调拨需要删除下游自由态调入申请
 * <li>配置驱动：并且直运类型为直运采购需要删除下游自由态请购单
 * @scene 
 * 销售订单取消审批前
 * @param 
 * pfMetaModelService 配置消息驱动服务接口
 * alDelete5AVOs 配置消息驱动PUSH5AOR20需要删除下游自由态调入申请VOs
 * alDelete20VOs 配置消息驱动PUSH5AOR20需要删除下游自由态请购单VOs
 * @since 6.0
 * @version 2010-12-21 上午10:03:45
 * @author 刘志伟
 */
public class CheckPush5Aor20Rule implements IRule<SaleOrderVO> {

  private IPFMetaModel pfMetaModelService;

  // 配置消息驱动PUSH5AOR20需要删除下游自由态调入申请VOs
  private List<SaleOrderVO> alDelete5AVOs;

  // 配置消息驱动PUSH5AOR20需要删除下游自由态请购单VOs
  private List<SaleOrderVO> alDelete20VOs;

  @Override
  public void process(SaleOrderVO[] vos) {
    // 1.过滤VOs分组
    this.filterVOs(vos);

    // 2.配置驱动：取消审批删除下游消息驱动推单生成的调入申请
    if (null != this.alDelete5AVOs && this.alDelete5AVOs.size() > 0) {
      SaleOrderVO[] delete5Avos = new SaleOrderVO[this.alDelete5AVOs.size()];
      this.alDelete5AVOs.toArray(delete5Avos);
      this.delete5A(delete5Avos);
    }

    // 3.配置驱动：取消审批删除下游消息驱动推单生成的请购单
    if (null != this.alDelete20VOs && this.alDelete20VOs.size() > 0) {
      SaleOrderVO[] delete20vos = new SaleOrderVO[this.alDelete20VOs.size()];
      this.alDelete20VOs.toArray(delete20vos);
      this.delete20(delete20vos);
    }
  }

  private void delete20(SaleOrderVO[] vos) {
    String[] m30IDs = SoVoTools.getVOPKValues(vos);
    try {
      POm20ServicesUtil.delete20ByPo(m30IDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void delete5A(SaleOrderVO[] vos) {
    String[] m30IDs = SoVoTools.getVOPKValues(vos);
    try {
      TOm5AServicesUtil.delete5AByTo(m30IDs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void filterVOs(SaleOrderVO[] vos) {
    for (SaleOrderVO vo : vos) {
      // 根据是否配置消息驱动PUSH5AOR20分组VOs
      if (this.isConfigDrive(vo)) {
        this.filterVOsByDirectType(vo);
      }
    }
  }

  private void filterVOsByDirectType(SaleOrderVO vo) {
    int directType =
        new SaleOrderTranTypeUtil().getDirectType(vo.getParentVO()
            .getCtrantypeid());
    this.alDelete5AVOs = new ArrayList<SaleOrderVO>();
    this.alDelete20VOs = new ArrayList<SaleOrderVO>();
    // 根据交易类型直运类型标记分组VOs
    if (DirectType.DIRECTTRAN_TO.getIntValue() == directType) {
      this.alDelete5AVOs.add(vo);
    }
    else if (DirectType.DIRECTTRAN_PO.getIntValue() == directType) {
      this.alDelete20VOs.add(vo);
    }
    // 补货时需要全部删除
    else {
      this.alDelete5AVOs.add(vo);
      this.alDelete20VOs.add(vo);
    }
    // 需要添加寻源过滤,否则通过寻源生成的下游不会自动删除
  }

  private boolean isConfigDrive(SaleOrderVO vo) {
    IPFMetaModel service = this.getPFMetaModel();
    try {
      MessagedriveVO[] mdVOs =
          service.queryAllMsgdrvVOs(vo.getParentVO().getVtrantypecode(), vo
              .getParentVO().getCbiztypeid(), "APPROVE");
      for (MessagedriveVO mdVO : mdVOs) {
        if ("PUSH5AOR20".equals(mdVO.getActiontype())) {
          return true;
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  private IPFMetaModel getPFMetaModel() {
    if (this.pfMetaModelService == null) {
      this.pfMetaModelService =
          NCLocator.getInstance().lookup(IPFMetaModel.class);
    }
    return this.pfMetaModelService;
  }
}
