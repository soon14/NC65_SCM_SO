package nc.ui.so.m38.billui.action.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.price.priceform.IPriceFormService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pubapp.AppUiContext;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.uif2.LoginContext;

/**
 * copy单据后执行:用于清空不必要数据
 * 
 * @param billVO
 * @author 刘志伟
 *         创建日期:2010-04-28 13:38:44
 * @version v6.0
 */
public class CopyActionProcessor implements ICopyActionProcessor<PreOrderVO> {

  @Override
  public void processVOAfterCopy(PreOrderVO vo, LoginContext context) {

    this.processHeadVO(vo, context);
    this.processBodyVO(vo);
  }

  private void processBodyVO(PreOrderVO vo) {
    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    UFDate localend = busidate.asLocalEnd();
    PreOrderBVO[] bvos = vo.getChildrenVO();

    List<String> listpriceform = new ArrayList<String>();
    for (PreOrderBVO bvo : bvos) {

      String oldpriceform = bvo.getCpriceformid();
      if (!PubAppTool.isNull(oldpriceform)) {
        listpriceform.add(oldpriceform);
      }
      // 设置空处理
      bvo.setCpreorderbid(null);
      bvo.setCpreorderid(null);
      bvo.setNarrnum(null);
      // 设置默认
      bvo.setBlineclose(UFBoolean.FALSE);
      bvo.setDbilldate(busidate);
      bvo.setDsenddate(localend);
      bvo.setDreceivedate(localend);
      bvo.setDarrdate(null);
    }

    if (listpriceform.size() > 0) {
      String[] oldforms = new String[listpriceform.size()];
      listpriceform.toArray(oldforms);

      IPriceFormService pricesrv =
          NCLocator.getInstance().lookup(IPriceFormService.class);
      Map<String, String> mapform = null;
      try {
        mapform = pricesrv.copyPriceForm(oldforms);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      for (PreOrderBVO item : bvos) {
        String oldpriceform = item.getCpriceformid();
        if (PubAppTool.isNull(oldpriceform)) {
          continue;
        }
        item.setCpriceformid(mapform.get(oldpriceform));
      }
    }
  }

  private void processHeadVO(PreOrderVO vo, LoginContext context) {
    PreOrderHVO hvo = vo.getParentVO();

    // 设置新的销售组织
    String newOrgID = OrgUnitPubService.getNewVIDByOrgID(hvo.getPk_org());
    hvo.setPk_org_v(newOrgID);

    // 设置空处理
    hvo.setCpreorderid(null);
    hvo.setVbillcode(null);
    hvo.setBillmaker(null);
    hvo.setDmakedate(null);
    hvo.setCreator(null);
    hvo.setCreationtime(null);
    hvo.setApprover(null);
    hvo.setTaudittime(null);
    hvo.setModifier(null);
    hvo.setModifiedtime(null);
    hvo.setTs(null);
    hvo.setVsrctype(null);

    // 设置默认
    UFDate busidate = AppUiContext.getInstance().getBusiDate();
    hvo.setDbilldate(busidate);

    UFDate abatedate = busidate.asLocalEnd().getDateAfter(3);
    hvo.setDabatedate(abatedate);
    hvo.setPk_group(context.getPk_group());
    hvo.setFstatusflag(BillStatus.FREE.getIntegerValue());
    hvo.setIprintcount(Integer.valueOf(0));
  }

}
