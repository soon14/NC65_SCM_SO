package nc.ui.so.m30.billui.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.bd.feature.ffile.IPubFFileQueryService;
import nc.pubitf.ct.saledaily.saleorder.IRelateSaleCT;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.pub.locator.NCUILocator;
import nc.ui.so.m30.pub.SaleOrderCalculator;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.vo.ct.entity.CtRelatingVO;
import nc.vo.ct.saledaily.entity.CtSalebillViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.res.NCModule;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m30trantype.enumeration.SaleMode;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;
import nc.vo.so.pub.util.SOSysParaInitUtil;

/**
 * 销售订单自动关联合同规则
 * 
 * @since 6.3
 * @version 2012-12-05 14:57:20
 * @author 冯加彬
 */
public class AssociateConstractRule {

  private BillCardPanel cardPanel;

  private IKeyValue keyValue;

  private M30TranTypeVO trantypevo;

  /**
   * 构造子
   * 
   * @param cardPanel
   * @param trantype
   */
  public AssociateConstractRule(
      BillCardPanel cardPanel, M30TranTypeVO trantype) {
    this.cardPanel = cardPanel;
    this.keyValue = new CardKeyValue(cardPanel);
    this.trantypevo = trantype;
  }

  /**
   * 自动关联合同
   * 
   * @param rows
   */
  public void associateCT(int[] rows) {
    if (!SysInitGroupQuery.isCTEnabled()) {
      return;
    }
    if (null == rows || rows.length == 0 || null == this.trantypevo) {
      return;
    }
    // 退换货订单不支持关联合同
    Integer salemodel = this.trantypevo.getFsalemode();
    if (SaleMode.MODE_RETURN.equalsValue(salemodel)
        || SaleMode.MODE_RETURNCHANGE.equalsValue(salemodel)) {
      return;
    }
    // 清空需要关联行的ctid、ctbid
    this.cleanCTInfo(rows);

    // 1.组织关联VO
    Map<Integer, CtRelatingVO> mRelatingVOs = this.getCtRelatingVOMap(rows);
    int size = mRelatingVOs.size();
    if (size > 0) {
      CtRelatingVO[] relatingVOs = new CtRelatingVO[size];
      int[] associateCTrows = new int[size];
      int i = 0;
      for (Entry<Integer, CtRelatingVO> entry : mRelatingVOs.entrySet()) {
        associateCTrows[i] = entry.getKey().intValue();
        relatingVOs[i] = entry.getValue();
        i++;
      }
      // 2.关联合同
      IRelateSaleCT ictrelating =
          NCUILocator.getInstance().lookup(IRelateSaleCT.class, NCModule.CT);
      CtSalebillViewVO[] constractviewvos = null;
      try {
        constractviewvos = ictrelating.relateCT(relatingVOs);
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
      // 3.设置关联结果
      if (null != constractviewvos && constractviewvos.length > 0) {
        int[] changeRows =
            this.setValueFromCt(constractviewvos, associateCTrows);
        if (changeRows != null && changeRows.length > 0) {
          // 计算数量和单价金额
          this.calculate(changeRows);
        }
      }
    }
  }

  private void calculate(int[] changerows) {
    boolean bSO23 = true;

    // SO23基价含税、含税优先
    String pk_group = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_GROUP);
    UFBoolean ufbSO23 = SOSysParaInitUtil.getSO23(pk_group);
    if (null != ufbSO23) {
      bSO23 = ufbSO23.booleanValue();
    }

    String editkey =
        bSO23 ? SaleOrderBVO.NQTORIGTAXPRICE : SaleOrderBVO.NQTORIGPRICE;
    SaleOrderCalculator calculator = new SaleOrderCalculator(this.cardPanel);
    // 计算报价数量与数量
    calculator.calculateOnlyNum(changerows, SaleOrderBVO.NNUM);
    // 计算价税合计
    calculator.calculate(changerows, editkey);
  }

  private void cleanCTInfo(int[] rows) {
    for (int row : rows) {
      this.keyValue.setBodyValue(row, SaleOrderBVO.CCTMANAGEBID, null);
      this.keyValue.setBodyValue(row, SaleOrderBVO.CCTMANAGEID, null);
      this.keyValue.setBodyValue(row, SaleOrderBVO.VCTCODE, null);
    }
  }

  private Map<Integer, CtRelatingVO> getCtRelatingVOMap(int[] rows) {
    Map<Integer, CtRelatingVO> mRelatingVOs =
        new HashMap<Integer, CtRelatingVO>();
    String pk_org = this.keyValue.getHeadStringValue(SaleOrderHVO.PK_ORG);
    String ccustomerid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CCUSTOMERID);
    String corigcurrencyid =
        this.keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    UFDate dbilldate = this.keyValue.getHeadUFDateValue(SaleOrderHVO.DBILLDATE);
    for (int row : rows) {
      String materialid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CMATERIALVID);
      String rowNO = this.keyValue.getBodyStringValue(row, SaleOrderBVO.CROWNO);
      String cqtunitid =
          this.keyValue.getBodyStringValue(row, SaleOrderBVO.CQTUNITID);
      // 固定维度：组织、客户、物料、币种、日期, 这几个字段全部能为空
      if (PubAppTool.isNull(pk_org) || PubAppTool.isNull(ccustomerid)
          || PubAppTool.isNull(materialid)
          || PubAppTool.isNull(corigcurrencyid) || dbilldate == null) {
        continue;
      }
      CtRelatingVO relatingvo = new CtRelatingVO();
      relatingvo.setPk_org(pk_org);
      relatingvo.setCvendor(ccustomerid);
      relatingvo.setCurrency(corigcurrencyid);
      relatingvo.setDate(dbilldate);
      relatingvo.setMaterial(materialid);
      relatingvo.setSourerowno(rowNO);
      relatingvo.setCqtunit(cqtunitid);
      mRelatingVOs.put(Integer.valueOf(row), relatingvo);
    }
    return mRelatingVOs;
  }

  /**
   * 通过关联合同结果集更新相应行
   * <p>
   * <b>ct接口保证关联vo和结果vo11对应，没有关联的为null</b>
   * 
   * @since 6.0
   * @author 刘志伟
   * @time 2010-10-15 下午07:47:58
   */
  private int[] setValueFromCt(CtSalebillViewVO[] constractviewvos, int[] rows) {
    CtSalebillViewVO constractviewvo = null;
    List<Integer> changeRowList = new ArrayList<Integer>();
    int[] retRows = null;
    // 可能计算换算率
    SOUnitChangeRateRule unitraterule = new SOUnitChangeRateRule(this.keyValue);
    // add by zhangby5 V65合同支持特征码，关联合同时需要将特征码带到订单并计算特征价 
    // 经刘达和合同需求确认,无需再根据特征价影响订单单价
    Map<String, UFDouble> nmffilepriceMap = this.queryFfileprice(constractviewvos);
    for (int i = 0; i < rows.length; i++) {
      constractviewvo = constractviewvos[i];
      if (constractviewvo == null) {
        continue;
      }
      // 设置特征码和特征价 begin zhangby5
      String orderFfile =
          this.keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CMFFILEID);
      String ctFfile = constractviewvo.getItem().getCffileid();
      UFDouble ffilePrice = nmffilepriceMap.get(ctFfile);
      if (PubAppTool.isNull(orderFfile) && !PubAppTool.isNull(ctFfile)) {
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CMFFILEID, ctFfile);
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NMFFILEPRICE,
            ffilePrice);
      }
      // end zhangby5
      changeRowList.add(rows[i]);
      // 合同主表ID、附表ID
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CCTMANAGEID,
          constractviewvo.getHead().getPk_ct_sale());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CCTMANAGEBID,
          constractviewvo.getPk_ct_sale_b());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.VCTCODE,
          constractviewvo.getVbillcode());
      // 收款协议
      this.keyValue.setHeadValue(SaleOrderHVO.CPAYTERMID, constractviewvo
          .getHead().getPk_payterm());

      // 项目
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CPROJECTID,
          constractviewvo.getHead().getCprojectid());
      // 根据需求要求，标准产品中不再增加自定义项的关联了，如果项目需要，可以打开该段代码即可，同时需要合同出脚本Z301将自定义项加上
      // 设置表体自定义项
      // this.setVbdef(rows[i], constractviewvo);
      // 主单位
      String unitid =
          this.keyValue.getBodyStringValue(rows[i], SaleOrderBVO.CUNITID);
      // 业务数量
      // dongli2 2013.8.29 关联合同时 要判断一下 分类合同就不携带单位，否则就携带
      String astunitid = constractviewvo.getCastunitid();
      if (PubAppTool.isNull(astunitid)) {
        astunitid = unitid;
      }
      else {
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CASTUNITID, astunitid);
        unitraterule.calcAstChangeRate(rows[i]);
      }
      // 报价单位
      String cqtunitid = constractviewvo.getCqtunitid();
      if (PubAppTool.isNull(cqtunitid)) {
        cqtunitid = astunitid;
      }
      else {
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.CQTUNITID, cqtunitid);
        unitraterule.calcQtChangeRate(rows[i]);
      }

      // 税率
      if (null != constractviewvo.getNtaxrate()) {
        this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NTAXRATE,
            constractviewvo.getNtaxrate());
      }
      // 单价
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NORIGPRICE,
          constractviewvo.getNorigprice());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NQTORIGPRICE,
          constractviewvo.getNqtorigprice());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NORIGTAXPRICE,
          constractviewvo.getNorigtaxprice());
      this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NQTORIGTAXPRICE,
          constractviewvo.getNqtorigtaxprice());

      // 数量、报价单位数量 = 合同数量 - 累计订单数量
      // 客户、日期、原币触发关联合同如果数量有值则数量不变，不用合同的数量
      UFDouble num =
          this.keyValue.getBodyUFDoubleValue(rows[i], SaleOrderBVO.NNUM);
      if (num == null || MathTool.compareTo(num, UFDouble.ZERO_DBL) == 0) {
        UFDouble numTemp =
            MathTool.sub(constractviewvo.getItem().getNnum(),
                constractviewvo.getNordnum());
        if (MathTool.compareTo(numTemp, UFDouble.ZERO_DBL) > 0) {
          this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NNUM, numTemp);
        }
        else {
          this.keyValue.setBodyValue(rows[i], SaleOrderBVO.NNUM,
              UFDouble.ZERO_DBL);
        }
      }
    }
    if (changeRowList.size() > 0) {
      retRows = new int[changeRowList.size()];
      for (int i = 0; i < changeRowList.size(); i++) {
        retRows[i] = changeRowList.get(i).intValue();
      }
    }
    return retRows;
  }
  
  private Map<String, UFDouble> queryFfileprice(
      CtSalebillViewVO[] constractviewvos) {
    Set<String> cmffileSet = new HashSet<>();
    for (CtSalebillViewVO constractviewvo : constractviewvos) {
      if (constractviewvo == null) {
        continue;
      }
      String ctFfileId = constractviewvo.getItem().getCffileid();
      if (PubAppTool.isNull(ctFfileId)) {
        continue;
      }
      cmffileSet.add(ctFfileId);
    }
    String corigcurrencyid =
        keyValue.getHeadStringValue(SaleOrderHVO.CORIGCURRENCYID);
    Map<String, UFDouble> nmffilepriceMap = new HashMap<>();
    if (cmffileSet.size() > 0) {
      // 根据币种和特征码到匹配特征价目表得到特征价
      IPubFFileQueryService ffileQueryService =
          NCLocator.getInstance().lookup(IPubFFileQueryService.class);
      try {
        nmffilepriceMap =
            ffileQueryService.queryPriceByPK(
                cmffileSet.toArray(new String[cmffileSet.size()]),
                corigcurrencyid);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
    return nmffilepriceMap;
  }

  private void setVbdef(int row, CtSalebillViewVO constractviewvo) {
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF1, constractviewvo
        .getItem().getVbdef1());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF2, constractviewvo
        .getItem().getVbdef2());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF3, constractviewvo
        .getItem().getVbdef3());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF4, constractviewvo
        .getItem().getVbdef4());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF5, constractviewvo
        .getItem().getVbdef5());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF6, constractviewvo
        .getItem().getVbdef6());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF7, constractviewvo
        .getItem().getVbdef7());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF8, constractviewvo
        .getItem().getVbdef8());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF9, constractviewvo
        .getItem().getVbdef9());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF10, constractviewvo
        .getItem().getVbdef10());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF11, constractviewvo
        .getItem().getVbdef11());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF12, constractviewvo
        .getItem().getVbdef12());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF13, constractviewvo
        .getItem().getVbdef13());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF14, constractviewvo
        .getItem().getVbdef14());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF15, constractviewvo
        .getItem().getVbdef15());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF16, constractviewvo
        .getItem().getVbdef16());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF17, constractviewvo
        .getItem().getVbdef17());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF18, constractviewvo
        .getItem().getVbdef18());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF19, constractviewvo
        .getItem().getVbdef19());
    this.keyValue.setBodyValue(row, SaleOrderBVO.VBDEF20, constractviewvo
        .getItem().getVbdef20());
  }
}
