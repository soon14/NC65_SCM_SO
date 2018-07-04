package nc.ui.so.m4331.arrange.pub;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.bill.BillListPanel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.pub.scale.UIScaleUtils;
import nc.ui.so.pub.keyvalue.ListKeyValue;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.pub.CalculatorCondtionUtil;
import nc.vo.so.m4331.pub.WeightAndVolParaUtil;
import nc.vo.so.pub.enumeration.ListTemplateType;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.para.WeightVolPieceVO;
import nc.vo.so.pub.rule.SOBuysellTriaRule;
import nc.vo.so.pub.rule.SOUnitChangeRateRule;

/**
 * 
 * @since 6.0
 * @version 2011-6-30 下午02:58:53
 * @author 祝会征
 */
public class DeliveryModelCalculator {
  /**
   * 需要触发数量单价金额算法的字段
   */
  private static final String[] STRNEEDCALKEY = new String[] {
    // 数量、主数量、换算率
    DeliveryBVO.NASTNUM,
    DeliveryBVO.NNUM,
    DeliveryBVO.VCHANGERATE,
    // 报价单位数量、报价换算率、税率
    DeliveryBVO.NQTUNITNUM,
    DeliveryBVO.VQTUNITRATE,
    DeliveryBVO.NTAXRATE,
    // 单品折扣、主含税单价、主无税单价
    DeliveryBVO.NITEMDISCOUNTRATE,
    DeliveryBVO.NORIGTAXPRICE,
    DeliveryBVO.NORIGPRICE,
    // 主含税净价、主无税净价、含税单价
    DeliveryBVO.NORIGTAXNETPRICE, DeliveryBVO.NORIGNETPRICE,
    DeliveryBVO.NQTORIGTAXPRICE,
    // 无税单价、含税净价、无税净价
    DeliveryBVO.NQTORIGPRICE, DeliveryBVO.NQTORIGTAXNETPRC,
    DeliveryBVO.NQTORIGNETPRICE,
    // 税额、无税金额、价税合计
    DeliveryBVO.NTAX, DeliveryBVO.NORIGMNY, DeliveryBVO.NORIGTAXMNY,
    // 折扣额、折本汇率
    DeliveryBVO.NORIGDISCOUNT, DeliveryBVO.NEXCHANGERATE,
  };

  private BillModel billmodel;

  private BillListPanel listPanel;

  private Set<String> hsNeedCalKey;

  private ScaleUtils scale;

  private Map<String, WeightVolPieceVO> voMap;

  /**
   * BillModelCalculator 的构造子
   * 
   * @param billmodel
   */
  public DeliveryModelCalculator(BillListPanel billListPanle) {
    this.billmodel = billListPanle.getBodyBillModel();
    this.listPanel = billListPanle;
  }

  /**
   * 方法功能描述：数量单价金额运算。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   * @param editkey
   *          <p>
   * @author fengjb
   * @time 2010-6-1 下午02:25:42
   */
  public void calculate(int[] rows, String editkey) {
    // 如果编辑字段不会影响到数量单价金额，不进行计算
    if (!this.getNeedCalKey().contains(editkey)) {
      return;
    }
    // 1.创建数据映射实例 获得数据项之间的映射关系
    IRelationForItems item = new RelationItemForCal();
    // 2.创建数据集实例 初始化数据关系计算用的数据集
    Calculator tool = null;
    this.scale = UIScaleUtils.getScaleUtils();
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    CalculatorCondtionUtil util = new CalculatorCondtionUtil();
    Condition cond = util.getCalcCondition();
    // 两个参数 cond 为计算时的参数条件
    for (int row : rows) {
      IKeyValue keyValue =
          new ListKeyValue(this.listPanel, row, ListTemplateType.SUB);

      IDataSetForCal data = new BillModelDataSet(this.billmodel, row, item);
      // 设置是否国内销售
      SOBuysellTriaRule buysellrule = new SOBuysellTriaRule(keyValue);
      cond.setInternational(buysellrule.isBuysellFlagOut(row));
      // 设置是否固定单位换算率
      SOUnitChangeRateRule unitrule = new SOUnitChangeRateRule(keyValue);
      cond.setIsFixNchangerate(unitrule.isAstFixedRate(row));
      cond.setIsFixNqtunitrate(unitrule.isQtFixedRate(row));

      tool = new Calculator(data, this.scale);
      // 两个参数 cond 为计算时的参数条件
      tool.calculate(cond, editkey);
    }
    if (DeliveryBVO.VCHANGERATE.equals(editkey)
        || DeliveryBVO.NASTNUM.equals(editkey)
        || DeliveryBVO.NNUM.equals(editkey)) {
      this.initMapInfo(rows);
      this.setWeihtVolPiece(rows);
    }
  }

  /**
   * 方法功能描述：初始化触发数量单价金额编辑事件的字段。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-21 下午03:46:10
   */
  public Set<String> getNeedCalKey() {
    if (null == this.hsNeedCalKey) {
      this.hsNeedCalKey = new HashSet<String>();
      for (String key : DeliveryModelCalculator.STRNEEDCALKEY) {
        this.hsNeedCalKey.add(key);
      }
    }
    return this.hsNeedCalKey;
  }

  private Map<String, WeightVolPieceVO> getWeightAndVolInfo(String[] pks) {
    try {
      return WeightAndVolParaUtil.getWeightAndVolValue(pks);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private void initMapInfo(int[] rows) {
    String[] pks = new String[rows.length];
    // 取得卡片模板上面所有的物料主键
    for (int i = 0; i < rows.length; i++) {
      DefaultConstEnum materialid =
          (DefaultConstEnum) this.billmodel.getValueObjectAt(rows[i],
              DeliveryBVO.CMATERIALVID);
      pks[i] = (String) materialid.getValue();
    }
    this.voMap = this.getWeightAndVolInfo(pks);
  }

  /**
   * 设置件数
   * 
   * @param row
   * @param num
   * @param key
   */
  private void setPiece(int row, UFDouble num, String key) {
    DefaultConstEnum cunitid =
        (DefaultConstEnum) this.billmodel.getValueObjectAt(row,
            DeliveryBVO.CASTUNITID);
    WeightVolPieceVO vo = this.voMap.get(key);
    if (null == vo) {
      return;
    }
    String changerate =
        MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(key,
            (String) cunitid.getValue(), vo.getNpiece());
    if (null == changerate || "".equals(changerate)) {
      return;
    }
    UFDouble value = HslParseUtil.hslDivUFDouble(changerate, num);
    this.scale.adjustUnitScale(value, key);
    this.billmodel.setValueAt(value, row, DeliveryBVO.NPIECE);
    this.billmodel.setValueAt(value, row, DeliveryHVO.NTOTALPIECE);
  }

  /**
   * 设置体积
   * 
   * @param row
   * @param num
   * @param key
   */
  private void setVol(int row, UFDouble num, String key) {
    WeightVolPieceVO vo = this.voMap.get(key);
    if (null == vo) {
      return;
    }
    UFDouble vol = vo.getNvol();
    if (vol == null) {
      return;
    }
    UFDouble value = num.multiply(vol);
    value = this.scale.adjustStandardVolumnScale(value);
    this.billmodel.setValueAt(value, row, DeliveryBVO.NVOLUME);
    this.billmodel.setValueAt(value, row, DeliveryHVO.NTOTALVOLUME);
  }

  /**
   * 设置重量
   * 
   * @param row
   * @param num
   * @param key
   */
  private void setWeight(int row, UFDouble num, String key) {
    WeightVolPieceVO vo = this.voMap.get(key);
    if (null == vo) {
      return;
    }
    UFDouble weight = vo.getNweight();
    if (weight == null) {
      return;
    }
    UFDouble value = num.multiply(weight);
    value = this.scale.adjustStandardWeightScale(value);
    this.billmodel.setValueAt(value, row, DeliveryBVO.NWEIGHT);
    this.billmodel.setValueAt(value, row, DeliveryHVO.NTOTALWEIGHT);
  }

  /**
   * 设置重量 体积 件数
   */
  private void setWeihtVolPiece(int[] rows) {
    for (int i = 0; i < rows.length; i++) {
      DefaultConstEnum pk =
          (DefaultConstEnum) this.billmodel.getValueObjectAt(rows[i],
              DeliveryBVO.CMATERIALVID);
      String key = (String) pk.getValue();
      UFDouble num =
          (UFDouble) this.billmodel.getValueObjectAt(rows[i], DeliveryBVO.NNUM);
      if (num != null) {
        this.setWeight(rows[i], num, key);
        this.setVol(rows[i], num, key);
        this.setPiece(rows[i], num, key);
      }
    }
  }
}
