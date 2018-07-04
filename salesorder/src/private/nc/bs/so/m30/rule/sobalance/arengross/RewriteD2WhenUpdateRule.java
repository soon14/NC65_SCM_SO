package nc.bs.so.m30.rule.sobalance.arengross;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.arap.pub.IArap4VerifyOutOperation;
import nc.vo.arap.pfflow.ArapBillMapVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.BillRowCompare;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.sobalance.entity.SoBalanceBVO;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;

/**
 * @description
 * 修改收款核销关系后更新收付单据的结算金额
 * @scene
 * 销售订单修改收款核销关系后
 * @param 
 * 无
 * @author gdsjw
 */
public class RewriteD2WhenUpdateRule implements ICompareRule<SoBalanceVO> {

  @Override
  public void process(SoBalanceVO[] vos, SoBalanceVO[] originVOs) {
    ItemKeyMapping mapping = new ItemKeyMapping();
    mapping.setCsrcidKey(SoBalanceBVO.CPAYBILLID);
    mapping.setCsrcbidKey(SoBalanceBVO.CPAYBILLROWID);
    mapping.setNnumKey(SoBalanceBVO.NORIGORDBALMNY);
    /*
     * 这里需要特殊处理 因为正常单据保存时表体一定不为空，而核销关系有可能为空
     */
    List<SoBalanceVO> deletebills = new ArrayList<SoBalanceVO>();
    List<SoBalanceVO> deleteoriginbills = new ArrayList<SoBalanceVO>();
    List<SoBalanceVO> updatebills = new ArrayList<SoBalanceVO>();
    List<SoBalanceVO> updateoriginbills = new ArrayList<SoBalanceVO>();
    for (int i = 0; i < vos.length; i++) {
      SoBalanceVO bill = vos[i];
      SoBalanceVO originbill = originVOs[i];
      IVOMeta childMeta = bill.getMetaData().getChildren()[0];
      ISuperVO[] childvos = bill.getChildren(childMeta);
      if ((childvos == null) || (childvos.length == 0)) {
        deletebills.add(bill);
        deleteoriginbills.add(originbill);
      }
      else {
        updatebills.add(bill);
        updateoriginbills.add(originbill);
      }
    }
    List<RewritePara> paraList = new ArrayList<RewritePara>();
    if (updatebills.size() > 0) {
      this.splitForUpdate(paraList, updatebills.toArray(new SoBalanceVO[0]),
          updateoriginbills.toArray(new SoBalanceVO[0]), mapping);
    }
    if (deletebills.size() > 0) {
      this.splitForDelete(paraList,
          deleteoriginbills.toArray(new SoBalanceVO[0]), mapping);
    }
    paraList = this.combine(paraList);
    if (paraList.size() > 0) {
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
    }

  }

  public void splitForDelete(List<RewritePara> paraList, IBill[] bills,
      ItemKeyMapping mapping) {
    IVOMeta childMeta = bills[0].getMetaData().getChildren()[0];
    for (IBill bill : bills) {
      ISuperVO[] vos = bill.getChildren(childMeta);
      for (ISuperVO vo : vos) {
        this.processDeleteRow(paraList, vo, mapping);
      }
    }
  }

  public void splitForUpdate(List<RewritePara> paraList, IBill[] bills,
      IBill[] originBills, ItemKeyMapping mapping) {
    BillRowCompare tool = new BillRowCompare(bills, originBills);
    List<ISuperVO> insertList = tool.getInsertList();
    List<ISuperVO> deleteList = tool.getDeleteList();
    List<ISuperVO> updateList = tool.getUpdateList();
    List<ISuperVO> updateOriginList = tool.getUpdateOriginList();

    if (insertList.size() > 0) {
      for (ISuperVO vo : insertList) {
        this.processInsertRow(paraList, vo, mapping);
      }
    }
    if (deleteList.size() > 0) {
      for (ISuperVO vo : deleteList) {
        this.processDeleteRow(paraList, vo, mapping);
      }
    }
    if (updateList.size() > 0) {
      int size = updateList.size();
      for (int i = 0; i < size; i++) {
        this.processUpdateRow(paraList, updateList.get(i),
            updateOriginList.get(i), mapping);
      }
    }
  }

  private List<RewritePara> combine(List<RewritePara> paraList) {
    List<RewritePara> list = new ArrayList<RewritePara>();
    Map<String, RewritePara> index = new HashMap<String, RewritePara>();
    for (RewritePara para : paraList) {
      String key = para.getCsrcbid();
      if (index.containsKey(key)) {
        RewritePara p = index.get(key);
        UFDouble num = MathTool.add(para.getNnum(), p.getNnum());
        p.setNnum(num);
      }
      else {
        index.put(key, para);
      }
    }
    list.addAll(index.values());
    return list;
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

  private void processDeleteRow(List<RewritePara> paraList, ISuperVO vo,
      ItemKeyMapping mapping) {
    RewritePara para = this.construct(vo, mapping);
    UFDouble nnum = para.getNnum();
    if (nnum != null) {
      nnum = MathTool.sub(UFDouble.ZERO_DBL, nnum);
      para.setNnum(nnum);
    }
    para.setStatus(VOStatus.DELETED);
    paraList.add(para);
  }

  private void processInsertRow(List<RewritePara> paraList, ISuperVO vo,
      ItemKeyMapping mapping) {
    RewritePara para = this.construct(vo, mapping);
    para.setStatus(VOStatus.NEW);
    paraList.add(para);
  }

  private boolean processNum(RewritePara para, ISuperVO originVO,
      ItemKeyMapping mapping) {
    UFDouble nnum = para.getNnum();
    UFDouble noriginnum =
        (UFDouble) originVO.getAttributeValue(mapping.getNnumKey());
    boolean flag = MathTool.equals(nnum, noriginnum);
    if (!flag) {
      nnum = MathTool.sub(nnum, noriginnum);
      para.setNnum(nnum);
    }
    else {
      para.setNnum(UFDouble.ZERO_DBL);
    }
    return flag;
  }

  private void processUpdateRow(List<RewritePara> paraList, ISuperVO vo,
      ISuperVO originVO, ItemKeyMapping mapping) {
    RewritePara para = this.construct(vo, mapping);
    boolean flag = this.processNum(para, originVO, mapping);
    // 修改前后的VO回写数量有差异
    if (!flag) {
      para.setStatus(VOStatus.UPDATED);
      paraList.add(para);
    }
  }

}
