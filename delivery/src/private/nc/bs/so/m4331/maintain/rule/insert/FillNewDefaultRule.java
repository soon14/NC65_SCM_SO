package nc.bs.so.m4331.maintain.rule.insert;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.so.pub.enumeration.BillStatus;
import nc.vo.so.pub.util.MaterialPubServiceUtil;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @description
 *              销售发货单保存前填充默认值
 * @scene
 *        销售发货单保存前
 * @param 无
 */
public class FillNewDefaultRule implements IRule<DeliveryVO> {

  @Override
  public void process(DeliveryVO[] vos) {

    for (DeliveryVO vo : vos) {
      // 填充新增时默认值
      this.setHeadDefault(vo);
      this.setBodyDefault(vo);
    }
    // 填充单据号
    this.setBillCode(vos);
    // 设置免检属性
    this.setIsretinstobychk(vos);
  }

  private void calculate(DeliveryVO vo) {
    UFDouble totalnum = new UFDouble(0.0);
    UFDouble totalweight = new UFDouble(0.0);
    UFDouble totalvolume = new UFDouble(0.0);
    UFDouble totalpiece = new UFDouble(0.0);
    DeliveryBVO[] bvo = vo.getChildrenVO();
    for (DeliveryBVO dilverybvo : bvo) {
      if (dilverybvo.getNastnum() != null) {
        totalnum = totalnum.add(dilverybvo.getNastnum());
      }
      if (dilverybvo.getNweight() != null) {
        totalweight = totalweight.add(dilverybvo.getNweight());
      }
      if (dilverybvo.getNvolume() != null) {
        totalvolume = totalvolume.add(dilverybvo.getNvolume());
      }
      if (dilverybvo.getNpiece() != null) {
        totalpiece = totalpiece.add(dilverybvo.getNpiece());
      }
    }
    vo.getParentVO().setNtotalastnum(totalnum);
    vo.getParentVO().setNtotalpiece(totalpiece);
    vo.getParentVO().setNtotalvolume(totalvolume);
    vo.getParentVO().setNtotalweight(totalweight);

  }

  private void setBillCode(DeliveryVO[] vos) {

    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.Delivery.getCode(), DeliveryHVO.VBILLCODE,
            DeliveryHVO.PK_GROUP, DeliveryHVO.PK_ORG, DeliveryHVO.VTRANTYPECODE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.createBillCode(vos);

  }

  private void setBodyDefault(DeliveryVO vo) {
    String org = vo.getParentVO().getPk_org();
    String pk_group = vo.getParentVO().getPk_group();
    if (null == pk_group || "".equals(pk_group)) {
      pk_group = BSContext.getInstance().getGroupID();
    }
    UFDate billdate = vo.getParentVO().getDbilldate();
    for (DeliveryBVO bvo : vo.getChildrenVO()) {
      bvo.setPk_group(pk_group);
      bvo.setPk_org(org);
      bvo.setDbilldate(billdate);
    }
  }

  private void setHeadDefault(DeliveryVO vo) {
    DeliveryHVO head = vo.getParentVO();
    BSContext proxy = BSContext.getInstance();
    String groupid = proxy.getGroupID();
    // 集团
    if (VOChecker.isEmpty(head.getPk_group())) {
      head.setPk_group(groupid);
    }
    // 打印次数
    if (VOChecker.isEmpty(head.getIprintcount())) {
      head.setIprintcount(Integer.valueOf(0));
    }
    // 状态
    if (null == head.getFstatusflag()) {
      head.setFstatusflag(BillStatus.FREE.getIntegerValue());
    }
    this.calculate(vo);
  }

  private void setIsretinstobychk(DeliveryVO[] vos) {
    Set<String> materialset = new HashSet<String>();
    Set<String> sendorgset = new HashSet<String>();
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        UFDouble num = bvo.getNnum();
        if (MathTool.compareTo(num, UFDouble.ZERO_DBL) >= 0) {
          continue;
        }
        String pk_material = bvo.getCmaterialvid();
        String pk_sendorg = bvo.getCsendstockorgid();
        materialset.add(pk_material);
        sendorgset.add(pk_sendorg);
      }
    }
    // 查询发货库存组织下的物料的免检属性 为N的可以报检
    Map<String, MaterialStockVO> materialMap = null;
    if (materialset.size() > 0 || sendorgset.size() > 0) {
      materialMap =
          MaterialPubServiceUtil.queryMaterialStockInfoByPks(
              materialset.toArray(new String[materialset.size()]),
              sendorgset.toArray(new String[sendorgset.size()]), new String[] {
                MaterialStockVO.ISRETINSTOBYCHK
              });
    }
    for (DeliveryVO vo : vos) {
      DeliveryBVO[] bvos = vo.getChildrenVO();
      for (DeliveryBVO bvo : bvos) {
        UFDouble num = bvo.getNnum();
        if (MathTool.compareTo(num, UFDouble.ZERO_DBL) >= 0) {
          continue;
        }
        String pk_material = bvo.getCmaterialvid();
        String pk_sendorg = bvo.getCsendstockorgid();
        String matorgkey = pk_material + pk_sendorg;
        MaterialStockVO stockvo = materialMap.get(matorgkey);
        bvo.setBusecheckflag(stockvo == null ? UFBoolean.FALSE : stockvo
            .getIsretinstobychk());
      }
    }
  }

}
