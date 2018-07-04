package nc.bs.so.salequotation.bp;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.scmpub.app.flow.billcode.BillCodeInfoBuilder;
import nc.bs.so.pub.rule.FillBillTailInfoRuleForIns;
import nc.bs.so.salequotation.plugin.M4310PlugInPoint;
import nc.bs.so.salequotation.rule.BillNOInsertRule;
import nc.bs.so.salequotation.rule.BillNOUpdateRule;
import nc.bs.so.salequotation.rule.FillDataBeforeRule;
import nc.bs.so.salequotation.rule.FillHeadSumDataRule;
import nc.bs.so.salequotation.rule.SavedDataCheckRule;
import nc.impl.pubapp.bill.billcode.BillCodeInfo;
import nc.impl.pubapp.bill.billcode.BillCodeUtils;
import nc.impl.pubapp.pattern.data.bill.BillDelete;
import nc.impl.pubapp.pattern.data.bill.BillInsert;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.impl.pubapp.pub.smart.MakeTimeSetter;
import nc.itf.price.priceform.IPriceFormService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.util.SetAddAuditInfoRule;
import nc.vo.pubapp.util.SetUpdateAuditInfoRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.rule.SaleOrgEnableCheckRule;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.so.salequotation.entity.TailFieldSetter;

/**
 * 单据维护
 * 
 * @author chenyyb
 * 
 */
public class SalequoMaintainBP {

  private IPriceFormService priceFormService;

  public void delete(AggSalequotationHVO[] aggVOs) throws Exception {

    // 报价单删除后规则，返回单据号规则
    BillCodeInfo info =
        BillCodeInfoBuilder.buildBillCodeInfo(SOBillType.SaleQuotation.getCode(),
            SalequotationHVO.VBILLCODE, SalequotationHVO.PK_GROUP,
            SalequotationHVO.PK_ORG, SalequotationHVO.VTRANTYPE);
    BillCodeUtils util = new BillCodeUtils(info);
    util.returnBillCode(aggVOs);

    // 报价单删除前规则
    this.checkBillStatusWhenDelete(aggVOs);

    AroundProcesser<AggSalequotationHVO> processer =
        new AroundProcesser<AggSalequotationHVO>(M4310PlugInPoint.DeleteBP);
    processer.before(aggVOs);

    BillDelete<AggSalequotationHVO> deleteAction =
        new BillDelete<AggSalequotationHVO>();
    deleteAction.delete(aggVOs);

    if (aggVOs != null && aggVOs.length > 0) {
      boolean icEnable = SysInitGroupQuery.isPRICEEnabled();
      if (!icEnable) {
        return;
      }
      this.getPriceFormService().deletePriceFormByBillPK(
          aggVOs[0].getPrimaryKey());
    }

  }

  public AggSalequotationHVO[] insert(AggSalequotationHVO[] aggVOs)
      throws Exception {

    AroundProcesser<AggSalequotationHVO> aroundProcesser =
        new AroundProcesser<AggSalequotationHVO>(M4310PlugInPoint.InsertBP);
    this.addBeforeRule(aroundProcesser);

    BillTransferTool<AggSalequotationHVO> transferTool =
        new BillTransferTool<AggSalequotationHVO>(aggVOs);
    AggSalequotationHVO[] fullBills = transferTool.getClientFullInfoBill();
    aroundProcesser.before(fullBills);
    BillInsert<AggSalequotationHVO> insertAction =
        new BillInsert<AggSalequotationHVO>();
    AggSalequotationHVO[] vosAfterSave = insertAction.insert(fullBills);
    aroundProcesser.after(vosAfterSave);
    this.savePriceForm(vosAfterSave);
    AggSalequotationHVO[] retVOs =
        transferTool.getBillForToClient(vosAfterSave);
    for (int i = 0; i < vosAfterSave.length; i++) {
      ((SalequotationHVO) retVOs[i].getParent())
          .setVbillcode(((SalequotationHVO) vosAfterSave[i].getParent())
              .getVbillcode());
    }
    TailFieldSetter.setTailDefaultValue(retVOs, true);
    return retVOs;
  }

  private void addBeforeRule(AroundProcesser<AggSalequotationHVO> processer) {

    // 销售组织停用检查
    IRule<AggSalequotationHVO> rule =
        new SaleOrgEnableCheckRule<AggSalequotationHVO>();
    processer.addBeforeRule(rule);
    // 计算表头合计
    rule = new FillHeadSumDataRule();
    processer.addBeforeRule(rule);

    // 设置审计信息
    rule = new SetAddAuditInfoRule<AggSalequotationHVO>();
    processer.addBeforeRule(rule);

    // 填充表尾信息
    rule = new FillBillTailInfoRuleForIns<AggSalequotationHVO>();
    processer.addBeforeRule(rule);

    // 数据检查
    rule = new SavedDataCheckRule();
    processer.addBeforeRule(rule);

    // 单据号检查
    rule = new BillNOInsertRule();
    processer.addBeforeRule(new BillNOInsertRule());

    // 填充默认值
    rule = new FillDataBeforeRule();
    processer.addBeforeRule(rule);
  }

  public AggSalequotationHVO[] saveBase(AggSalequotationHVO[] aggVOs)
      throws Exception {
    AggSalequotationHVO[] retVOs = null;
    List<AggSalequotationHVO> insertVOList =
        new ArrayList<AggSalequotationHVO>();
    List<AggSalequotationHVO> updateVOList =
        new ArrayList<AggSalequotationHVO>();
    for (int i = 0; i < aggVOs.length; i++) {
      if (StringUtil.isEmpty(aggVOs[i].getPrimaryKey())) {
        insertVOList.add(aggVOs[i]);
      }
      else {
        updateVOList.add(aggVOs[i]);
      }
    }

    if (insertVOList.size() != 0) {
      AggSalequotationHVO[] insertAggVOs =
          insertVOList.toArray(new AggSalequotationHVO[insertVOList.size()]);
      retVOs = this.insert(insertAggVOs);
    }
    if (updateVOList.size() != 0) {
      AggSalequotationHVO[] updAggVOs =
          updateVOList.toArray(new AggSalequotationHVO[updateVOList.size()]);
      retVOs = this.update(updAggVOs);
    }
    return retVOs;
  }

  public AggSalequotationHVO[] update(AggSalequotationHVO[] bills)
      throws Exception {
    if (bills == null) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006009_0", "04006009-0023")/* @res "请选择单据！" */);
    }

    // TODO 当单据没有做任何修改时，前台没有把VOStatus设置成修改状态，导致审计信息不更新。
    for (AggSalequotationHVO bill : bills) {
      bill.getParent().setStatus(VOStatus.UPDATED);
    }

    BillUpdate<AggSalequotationHVO> updateAction =
        new BillUpdate<AggSalequotationHVO>();
    AroundProcesser<AggSalequotationHVO> aroundProcesser =
        new AroundProcesser<AggSalequotationHVO>(M4310PlugInPoint.UpdateBP);
    aroundProcesser.addBeforeFinalRule(new FillHeadSumDataRule());
    aroundProcesser
        .addBeforeFinalRule(new SetUpdateAuditInfoRule<AggSalequotationHVO>());
    aroundProcesser.addBeforeFinalRule(new SavedDataCheckRule());
    aroundProcesser
        .addBeforeFinalRule(new MakeTimeSetter<AggSalequotationHVO>());
    BillTransferTool<AggSalequotationHVO> transferTool =
        new BillTransferTool<AggSalequotationHVO>(bills);
    AggSalequotationHVO[] originBills = transferTool.getOriginBills();
    AggSalequotationHVO[] fullBills = transferTool.getClientFullInfoBill();

    CompareAroundProcesser<AggSalequotationHVO> compAroundProcesser =
        new CompareAroundProcesser<AggSalequotationHVO>(null);
    compAroundProcesser.addBeforeRule(new BillNOUpdateRule());
    aroundProcesser.before(fullBills);
    compAroundProcesser.before(fullBills, originBills);
    // 更新之后返回出删除行VO之外的全VO，TS已经被更新过
    AggSalequotationHVO[] newbills =
        updateAction.update(fullBills, originBills);
    aroundProcesser.after(newbills);
    this.savePriceForm(bills);
    // 向前台返回只发生了改变的轻量vo
    AggSalequotationHVO[] retVOs = transferTool.getBillForToClient(newbills);
    TailFieldSetter.setTailDefaultValue(retVOs, false);
    return retVOs;
  }

  private void checkBillStatusWhenDelete(AggSalequotationHVO[] aggVOs) {
    List<String> pks = new ArrayList<String>();
    for (AggSalequotationHVO vo : aggVOs) {
      pks.add(vo.getPrimaryKey());
    }
    BillQuery<AggSalequotationHVO> qryBO =
        new BillQuery<AggSalequotationHVO>(AggSalequotationHVO.class);
    AggSalequotationHVO[] dbVOs = qryBO.query(pks.toArray(new String[0]));
    for (AggSalequotationHVO dbVO : dbVOs) {
      SalequotationHVO hvo = (SalequotationHVO) dbVO.getParent();
      if (hvo == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006009_0", "04006009-0024")/*
                                                                     * @res
                                                                     * "无效单据"
                                                                     */);
        continue;
      }
      int billStatus = hvo.getFstatusflag().intValue();
      if (billStatus != BillStatusEnum.C_FREE
          && billStatus != BillStatusEnum.C_INVALIDATE) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4006009_0", "04006009-0026")/*
                                                                     * @res
                                                                     * "只能删除自由态和失效的单据"
                                                                     */);
      }
    }
  }

  private IPriceFormService getPriceFormService() {
    if (this.priceFormService == null) {
      this.priceFormService =
          NCLocator.getInstance().lookup(IPriceFormService.class);
    }
    return this.priceFormService;
  }

  private void savePriceForm(AggSalequotationHVO[] bills)
      throws BusinessException {
    boolean icEnable = SysInitGroupQuery.isPRICEEnabled();
    if (!icEnable) {
      return;
    }

    for (AggSalequotationHVO bill : bills) {
      SalequotationBVO[] bodys = bill.getChildrenVO();
      List<String> alpriceform = new ArrayList<String>();
      for (SalequotationBVO body : bodys) {
        if (body.getStatus() == VOStatus.DELETED
            || body.getStatus() == VOStatus.UNCHANGED) {
          continue;
        }
        String priceform =
            (String) body.getAttributeValue(SalequotationBVO.VPRICEDETAIL);
        if (!PubAppTool.isNull(priceform)) {
          alpriceform.add(priceform);
        }
      }
      if (alpriceform.size() > 0) {
        String[] priceforms = new String[alpriceform.size()];
        alpriceform.toArray(priceforms);
        try {
          String primarykey = bill.getParentVO().getPrimaryKey();
          this.getPriceFormService()
              .savePriceForm(primarykey, priceforms, true);
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    }
  }
}
