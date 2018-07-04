package nc.pubimpl.so;

import nc.bs.bank_cvp.formulainterface.RefCompilerBS;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.pubitf.so.m30.ICustomFunction;
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.bank_cvp.compile.datastruct.ArrayValue;
import nc.vo.bd.material.MaterialVO;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m4c.entity.SaleOutHeadVO;
import nc.vo.ic.m4c.entity.SaleOutViewVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.CarrierRuntimeException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.mreturncondition.entity.ReturnConditionVO;
import nc.vo.so.mreturnreason.entity.ReturnReasonVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * 此处插入类型说明。
 * 创建日期：(2004-3-10 11:05:57)
 * 
 * @author：左小军
 */
// import nc.vo.bank_cvp.compile.datastruct.IContext;
public class CustomFunctionImpl implements ICustomFunction {
  /**
   * CustomFunction 构造子注解。
   */
  public CustomFunctionImpl() {
    super();
  }

  /**
   * 函数名称：申请退货单据金额
   * 输入参数：单据头的客户编码(解析时的变量)
   * 返回值：当前退货申请单的客户的当前单据各行价税合计金额之和。
   * 应用实例：选择业务函数"申请退货单据金额"，表示条件为"如果当前退货申请单的客户的申请退货总金额"。
   */
  @Override
  public double getAppRetBillMny(ReturnAssignMatchVO paravo) {
    if (VOChecker.isEmpty(paravo)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0131")/*所指定的当前的退货申请单标题记录无效！*/);
    }
    if (paravo.getNorigtaxmny() == null) {
      return 0;
    }
    return paravo.getNorigtaxmny().doubleValue();
  }

  /**
   * 函数名称：申请退货数量
   * 输入参数：单据行的存货编码＋自由项(解析时的变量)
   * 返回值：当前行存货的申请退货的主数量。
   * 应用实例：选择业务函数"申请退货数量"，表示条件为"如果当前退货申请单的客户当前行存货的申请退货数量"
   */
  @Override
  public double getAppRetNum(ReturnAssignMatchVO paravo) {
    if (VOChecker.isEmpty(paravo)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0131")/*所指定的当前的退货申请单标题记录无效！*/);
    }
    if (paravo.getNnum() == null) {
      return 0;
    }
    return paravo.getNnum().doubleValue();
  }

  /*
  * 函数名称：存货编码
  * 输入参数：单据行的存货编码 (解析时的变量)
  * 返回值：存货编码。
  * 应用实例：选择业务函数"存货"，选择"＝"，在字符串处输入"1876A"表示条件为"如果当前行存货编码为'1876A'。"。一般适用于某种存货准退或不准退等。
  */
  @Override
  public String getInvCode(ReturnAssignMatchVO paravo) {
    if (VOChecker.isEmpty(paravo)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0131")/*所指定的当前的退货申请单标题记录无效！*/);
    }
    if (paravo.getPk_material() == null) {
      ExceptionUtils
          .wrappBusinessException(NCLangResOnserver.getInstance().getStrByID(
              "4006006_0", "04006006-0132")/*所指定的当前的退货申请单标题记录未指定存货信息。*/);
    }
    String strPk = paravo.getPk_material();
    try {
      MaterialVO[] vos =
          MaterialPubService.queryMaterialBaseInfoByPks(new String[] {
            strPk
          }, new String[] {
            MaterialVO.CODE
          });
      String strCode = vos[0].getCode();
      return strCode;
    }
    catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * 函数名称：存货生命周期
   * 输入参数：单据行的存货编码＋自由项(解析时的变量)
   * 返回值：存货管理档案中的存货生命周期（0，1，2，3）。
   * 应用实例：选择业务函数"存货生命周期"，选择"＝"，在字符串处输入"0"表示条件为"如果当前行存货的生命周期为0(即试销期)"。
   */
  @Override
  public int getInvLifePrd(ReturnAssignMatchVO paravo) throws BusinessException {
    String pk_material = paravo.getPk_material();
    MaterialVO[] vos =
        MaterialPubService.queryMaterialBaseInfoByPks(new String[] {
          pk_material
        }, new String[] {
          MaterialVO.PROLIFEPERIOD
        });
    if (null != vos && vos.length > 0) {
      Integer life = vos[0].getProlifeperiod();
      if (null == life) {
        return 0;
      }
      return life.intValue();
    }
    return 0;
  }

  /**
   * 函数名称：销售订单金额（时间区间）
   * 输入参数：单据头的客户编码(解析时的变量) ，单据头的单据日期(解析时的变量)，时间区间（函数定义时的常量）
   * 返回值：日期区间在"单据日期"与"单据日期－时间区间"的所有销售订单的本币价税合计之和。
   * 应用实例：选择业务函数"销售订单金额"，选择"（"，在数字处输入"10"，选择"）"，构成函数"销售订单金额（10）"表示条件为
   * "如果当前单据的客户在单据日期往前倒推10日内的实际销售订单金额之和"（形成where子句的between (today-10) and
   * today）。
   */
  @Override
  public double getOrderMny(int iDays, ReturnAssignMatchVO paravo) {
    UFDouble dblMny = null;
    String cust = paravo.getPk_customer();
    String material = paravo.getPk_material();
    UFDate date = paravo.getDbilldate();
    UFDate startDate = date.getDateBefore(iDays);
    StringBuffer sbfSql =
        new StringBuffer("select sum(so_saleorder_b.norigmny) ");
    sbfSql.append("from so_saleorder_b left join so_saleorder ");
    sbfSql
        .append("on so_saleorder.csaleorderid = so_saleorder_b.csaleorderid ");
    sbfSql.append("where so_saleorder.dbilldate<='" + date.toString() + "' ");
    sbfSql
        .append("and so_saleorder.dbilldate>='" + startDate.toString() + "' ");
    sbfSql.append("and so_saleorder.ccustomerid ='" + cust + "' ");
    sbfSql.append("and so_saleorder_b.cmaterialvid ='" + material + "' ");
    sbfSql.append("and so_saleorder.fstatusflag=2 ");
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet rowset = utils.query(sbfSql.toString());
    while (rowset.next()) {
      dblMny = rowset.getUFDouble(0);
    }
    if (dblMny == null) {
      return 0;
    }
    return dblMny.getDouble();
  }

  /**
   * 函数名称：实际出库数量（时间区间）
   * 输入参数：单据行的存货编码＋自由项(解析时的变量)，单据头的客户编码(解析时的变量)，单据头的单据日期(解析时的变量)，时间区间（函数定义时的常量参数
   * ）
   * 返回值：日期区间在"单据日期"与"单据日期－时间区间"的所有销售出库单的实际出库的数量之和。
   * 应用实例：选择业务函数"实际出库数量"，选择"（"，在数字处输入"10"，选择"）"，构成函数"实际出库数量（10）"表示条件为
   * "如果当前单据的客户当前行存货的在单据日期往前倒推10日内的实际出库数量之和"（形成where子句的between (today-10) and
   * today）。
   */
  @Override
  public double getOutNumber(int iDays, ReturnAssignMatchVO paravo) {
    UFDate date = paravo.getDbilldate();
    UFDate startDate = date.getDateBefore(iDays);
    String cust = paravo.getPk_customer();
    String cmaterialid1 = paravo.getPk_material();
    StringBuffer sql = new StringBuffer();
    sql.append("select " + MetaNameConst.CGENERALBID
        + " from ic_saleout_h h,ic_saleout_b b");
    sql.append(" where h.dr =0 and b.dr = 0 ");
    sql.append(" and h." + MetaNameConst.CCUSTOMERID + " ='" + cust + "'");
    sql.append(" and b." + MetaNameConst.DBIZDATE + " >='" + startDate + "'");
    sql.append(" and b." + MetaNameConst.DBIZDATE + " <='" + date + "'");
    sql.append(" and b." + ICPubMetaNameConst.CMATERIALVID + "='"
        + cmaterialid1 + "'");
    DataAccessUtils utils = new DataAccessUtils();
    String[] bids = utils.query(sql.toString()).toOneDimensionStringArray();
    SaleOutViewVO[] views =
        new ViewQuery<SaleOutViewVO>(SaleOutViewVO.class).query(bids);
    UFDouble value = new UFDouble(0.0);
    for (SaleOutViewVO view : views) {
      UFDouble num = view.getItem().getNnum();
      value = MathTool.add(num, value);
    }
    return value.doubleValue();
  }

  /**
   * 函数名称：退货申请单日期。
   */
  @Override
  public String getResBillDate(ReturnAssignMatchVO paravo) {
    return this.strtostr(paravo.getDbilldate().toString());
  }

  /**
   * 函数名称：退货原因类型
   * 输入参数：单据体的退货原因编码
   * 返回值：退货原因档案中的退货原因类型取值。
   * 应用实例：选择业务函数"退货原因类型"，选择"＝"，在字符串处输入"0"
   * (质量退货)，表示条件为"如果当前退货申请单的当前行存货的退货原因的退货原因类型为"0"（质量退货）。一般用于判断是否因质量原因退回等。
   */
  @Override
  public int getRetRsnType(ReturnAssignMatchVO paravo) {
    String strPk_ReturnReason = paravo.getCretreasonid();
    if (strPk_ReturnReason == null) {
      throw new CarrierRuntimeException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0133")/*请录入退货原因！*/);
    }
    VOQuery<ReturnReasonVO> query =
        new VOQuery<ReturnReasonVO>(ReturnReasonVO.class);
    ReturnReasonVO[] vos = query.query(new String[] {
      strPk_ReturnReason
    });
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0134", null, new String[] {
            strPk_ReturnReason
          })/*在退货申请单中指定的退货原因主键{0}不存在！*/);
    }
    return vos[0].getFreasontype().intValue();
  }

  /**
   * 函数名称：源发票日期
   */
  @Override
  public String getSaleInvoiceBillDate(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor32(paravo);
    if (strBillID != null) {
      return this.strtostr(this.getDateFor32(strBillID));
    }
    return this.getResBillDate(paravo);
  }

  /**
   * 函数名称：源订单日期
   */
  @Override
  public String getSaleOrderBillDate(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor30(paravo);
    if (strBillID != null) {
      return this.strtostr(this.getDateFor30(strBillID));
    }
    return this.getResBillDate(paravo);
  }

  /**
   * 函数名称：源出库单日期
   */
  @Override
  public String getSaleOutBillDate(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor4C(paravo);
    if (strBillID != null) {
      return this.strtostr(this.getDateFor4C(strBillID));

    }
    return this.getResBillDate(paravo);
  }

  /**
   * 函数名称：源发票日期
   */
  @Override
  public int getSourceInvoiceDays(ReturnAssignMatchVO paravo) {
    String sourceInvoice = this.getSaleInvoiceBillDate1(paravo);
    if (sourceInvoice == null) {
      return 0;
    }
    return new UFDate(this.getResBillDate1(paravo)).getDaysAfter(new UFDate(
        sourceInvoice));
  }

  /**
   * 函数名称：源发票日期
   */
  @Override
  public int getSourceOrderDays(ReturnAssignMatchVO paravo) {
    String sourceInvoice = this.getSaleOrderBillDate1(paravo);
    if (sourceInvoice == null) {
      return 0;
    }
    return new nc.vo.pub.lang.UFDate(this.getResBillDate1(paravo))
        .getDaysAfter(new nc.vo.pub.lang.UFDate(sourceInvoice));
  }

  /**
   * 函数名称：源发票日期
   */
  @Override
  public int getSourceOutDays(ReturnAssignMatchVO paravo) {
    String sourceInvoice = this.getSaleOutBillDate1(paravo);
    if (sourceInvoice == null) {
      return 0;
    }
    return new nc.vo.pub.lang.UFDate(this.getResBillDate1(paravo))
        .getDaysAfter(new nc.vo.pub.lang.UFDate(sourceInvoice));
  }

  /**
   * 函数名称：赠品
   * 输入参数：单据体的存货编码(解析时的变量)，单据体行的"赠品"标记
   * 返回值："是"或"否"。
   * 应用实例：选择业务函数"赠品"，选择"＝"，在字符串处输入"是"，表示条件为"如果当前退货申请单的当前行存货的赠品标志为"是
   * "。一般用于判断是否赠品销售的退回（在参照销售订单生成退货申请单的情况下有效）。
   */
  @Override
  public boolean isLargessFlag(ReturnAssignMatchVO paravo) {
    return paravo.getBlargessflag() != null
        && paravo.getBlargessflag().booleanValue();
  }

  @Override
  public boolean judge(String strConditionCode, ReturnAssignMatchVO paravo) {
    FunctionContex context = new FunctionContex(paravo);
    String pk_org = paravo.getPk_saleorg();
    StringBuffer where = new StringBuffer();
    where.append(" and " + ReturnConditionVO.PK_ORG + " ='" + pk_org + "'");
    where.append(" and " + ReturnConditionVO.VCONDITIONCODE + " = '"
        + strConditionCode + "'");
    VOQuery<ReturnConditionVO> query =
        new VOQuery<ReturnConditionVO>(ReturnConditionVO.class);
    ReturnConditionVO[] vos = query.query(where.toString(), null);
    if (VOChecker.isEmpty(vos)) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4006006_0", "04006006-0135", null, new String[] {
            pk_org, strConditionCode
          })/*参数无效，在组织{0}中，不存在编码为{1}的退货条件！*/);
    }
    try {
      String strExpress = vos[0].getVexpressname();
      ArrayValue v = RefCompilerBS.getExpressionResult(strExpress, context);
      Object objTemp = v.getValue();
      if (objTemp instanceof Boolean) {
        Boolean bln = (Boolean) objTemp;
        if (bln.booleanValue()) {
          return true;
        }
      }
      else if (objTemp instanceof String) {
        if (objTemp.equals("true")) {
          return true;
        }
        else if (objTemp.equals("false")) {
          return false;
        }
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("4006006_0", "04006006-0136")/*相关退货政策所对应的退货条件没有意义*/);
      }
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }

  private String getDateFor30(String strBillID) {
    VOQuery<SaleOrderHVO> query = new VOQuery<SaleOrderHVO>(SaleOrderHVO.class);
    SaleOrderHVO[] hvos = query.query(new String[] {
      strBillID
    });
    return hvos[0].getDbilldate().toString();
  }

  private String getDateFor32(String strBillID) {
    VOQuery<SaleInvoiceHVO> query =
        new VOQuery<SaleInvoiceHVO>(SaleInvoiceHVO.class);
    SaleInvoiceHVO[] hvos = query.query(new String[] {
      strBillID
    });
    return hvos[0].getDbilldate().toString();
  }

  private String getDateFor4C(String strBillID) {
    VOQuery<SaleOutHeadVO> query =
        new VOQuery<SaleOutHeadVO>(SaleOutHeadVO.class);
    SaleOutHeadVO[] hvos = query.query(new String[] {
      strBillID
    });
    return hvos[0].getDbilldate().toString();
  }

  private String getHidFor30(ReturnAssignMatchVO paravo) {
    String strBillID = null;
    if (paravo.getVsrctype() != null && paravo.getVsrctype().equals("30")) {
      strBillID = paravo.getCsrcid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    if (strBillID == null && paravo.getVfirsttype() != null
        && paravo.getVfirsttype().equals("30")) {
      strBillID = paravo.getCfirstid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    return strBillID;
  }

  private String getHidFor32(ReturnAssignMatchVO paravo) {
    String strBillID = null;
    if (paravo.getVsrctype() != null
        && paravo.getVsrctype().equals(SOBillType.Invoice.getCode())) {
      strBillID = paravo.getCsrcid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    if (strBillID == null && paravo.getVfirsttype() != null
        && paravo.getVfirsttype().equals(SOBillType.Invoice.getCode())) {
      strBillID = paravo.getCfirstid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    return strBillID;
  }

  private String getHidFor4C(ReturnAssignMatchVO paravo) {
    String strBillID = null;
    if (paravo.getVsrctype() != null && paravo.getVsrctype().equals("4C")) {
      strBillID = paravo.getCsrcid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    if (strBillID == null && paravo.getVfirsttype() != null
        && paravo.getVfirsttype().equals("4C")) {
      strBillID = paravo.getCfirstid();
      if (strBillID.trim().length() == 0) {
        strBillID = null;
      }
    }
    return strBillID;
  }

  /**
   * 函数名称：退货申请单日期。
   */
  private String getResBillDate1(ReturnAssignMatchVO paravo) {
    return paravo.getDbilldate().toString();
  }

  /**
   * 函数名称：源发票日期
   */
  private String getSaleInvoiceBillDate1(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor32(paravo);
    if (strBillID != null) {
      return this.getDateFor32(strBillID);
    }
    return this.getResBillDate1(paravo);
  }

  /**
   * 函数名称：源订单日期
   */
  private String getSaleOrderBillDate1(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor30(paravo);
    if (strBillID != null) {
      this.getDateFor30(strBillID);
    }
    return this.getResBillDate1(paravo);
  }

  /**
   * 函数名称：源出库单日期
   */
  private String getSaleOutBillDate1(ReturnAssignMatchVO paravo) {
    String strBillID = this.getHidFor4C(paravo);
    if (strBillID != null) {
      try {

        return this.getDateFor4C(strBillID);
      }
      catch (Exception e) {
        ExceptionUtils.wrappBusinessException(e.getMessage());
      }

    }
    return this.getResBillDate1(paravo);
  }

  /**
   * 只截取日期字符串因为公式编辑器里面使用了日期
   * ?user>
   * 功能：
   * 参数：
   * 返回：
   * 例外：
   * 日期：(2004-7-14 15:34:58)
   * 修改日期，修改人，修改原因，注释标志：
   * 
   * @return java.lang.String
   * @param s java.lang.String
   */
  private String strtostr(String s) {
    String s1 = s;
    StringBuffer sb = new StringBuffer();
    int index = 0;
    while ((index = s1.indexOf("-")) >= 0) {
      sb.append(s1.substring(0, index));
      s1 = s1.substring(index + 1);
    }
    sb.append(s1);
    return sb.toString().substring(0, 8);
  }
}
