package nc.bs.so.m30.rule.sobalance.arengross;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.arap.pub.IArap4VerifyOutOperation;
import nc.vo.arap.pfflow.ArapBillMapVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * 保存收款核销关系后更新收付单据的结算金额
 * @scene
 * 销售订单保存收款核销关系后
 * @param 
 * 无
 * @author gdsjw
 */
public class RewriteD2WhenAddNewRule implements IRule<SoBalanceVO> {

  /**
   *
   */
  @Override
  public void process(SoBalanceVO[] vos) {
    // 设置上游单据类型、ID、BID、数量字段
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setCsrcidKey(SoBalanceBVO.CPAYBILLID);
    mapping.setCsrcbidKey(SoBalanceBVO.CPAYBILLROWID);
    mapping.setNnumKey(SoBalanceBVO.NORIGORDBALMNY);
    mapping.setSrcTSKey(SaleOrderBVO.SRCTS);

    // 分单
    List<RewritePara> paraList = this.splitForInsert(vos, mapping);

    if ((null != paraList) && (paraList.size() > 0)) {
      int size = paraList.size();
      ArapBillMapVO[] paras = new ArapBillMapVO[size];
      for (int i = 0; i < size; i++) {
        String id = paraList.get(i).getCsrcid();
        String bid = paraList.get(i).getCsrcbid();
        UFDouble nnum = paraList.get(i).getNnum();
        int status = paraList.get(i).getStatus();
        paras[i] = new ArapBillMapVO();
        paras[i].setS_billtype("F2");
        paras[i].setS_billid(id);
        paras[i].setS_itemid(bid);
        paras[i].setStatus(status);
        paras[i].setYbje(nnum);
      }
      IArap4VerifyOutOperation api =
          NCLocator.getInstance().lookup(IArap4VerifyOutOperation.class);
      try {
        api.updateOccupationMny(paras);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
      // IArapGatheringBillPubServiceForSCM api =
      // NCLocator.getInstance().lookup(IArapGatheringBillPubServiceForSCM.class);
      // try {
      // api.setGatheringBillMoneyUsed(paras);
      // } catch (BusinessException ex) {
      // ExceptionUtils.wrappException(ex);
      // }
    }

  }

  public List<RewritePara> splitForInsert(IBill[] bills, ItemKeyMapping mapping) {
    List<RewritePara> paraList = new ArrayList<RewritePara>();
    IVOMeta childMeta = bills[0].getMetaData().getChildren()[0];
    for (IBill bill : bills) {
      ISuperVO[] vos = bill.getChildren(childMeta);
      for (ISuperVO vo : vos) {
        this.processInsertRow(paraList, vo, mapping);
      }
    }
    return paraList;
  }

  private void processInsertRow(List<RewritePara> paraList, ISuperVO vo,
      ItemKeyMapping mapping) {
    RewritePara para = this.construct(vo, mapping);
    para.setStatus(VOStatus.NEW);
    paraList.add(para);
  }

  private RewritePara construct(ISuperVO vo, ItemKeyMapping mapping) {
    RewritePara para = new RewritePara();

    String key = mapping.getCsrcidKey();
    String csrcid = (String) vo.getAttributeValue(key);
    para.setCsrcid(csrcid);

    key = mapping.getCsrcbidKey();
    String csrcbid = (String) vo.getAttributeValue(key);
    para.setCsrcbid(csrcbid);

    key = mapping.getNnumKey();
    UFDouble nnum = (UFDouble) vo.getAttributeValue(key);
    para.setNnum(nnum);

    para.setCbill_bid(vo.getPrimaryKey());
    return para;
  }

}
