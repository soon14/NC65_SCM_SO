package nc.pubimpl.so.m30.invp;

import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.enumeration.BillStatus;

import nc.pubitf.invp.plan.IReqResultForInvp;
import nc.pubitf.invp.plan.ReqResultForInvpVO;
import nc.pubitf.so.m30.invp.ISaleOrderForInvp;

/**
 * 销售订单提供库存计划取数接口的实现
 * 
 * @since 6.3
 * @version 2012-11-08 08:50:43
 * @author zhangkai4
 */
public class SaleOrderForInvpImpl implements ISaleOrderForInvp {

  @Override
  public IReqResultForInvp getVO(String sendStockOrg, String tempName,
      boolean needRed) {
    ReqResultForInvpVO stockVO = new ReqResultForInvpVO();
    // from和where部分
    this.setFromWhere(stockVO, sendStockOrg, tempName, needRed);
    // 字段名部分
    this.setFiled(stockVO);
    return stockVO;
  }

  /**
   * 设置字段名（表名.字段名），并添加到VO中
   * 
   * @param stockVO
   */
  private void setFiled(ReqResultForInvpVO stockVO) {
    // 单据类型的编码值
    stockVO.setReqTypecode(SOBillType.Order.getCode());
    // 单据类型的编主键
    stockVO.setReqTypeid(SOBillType.Order.getCode());
    // 单据交易类型的编码值
    stockVO
        .setReqTrantypecode(this.getHeadFullPath(SaleOrderHVO.VTRANTYPECODE));
    // 单据交易类型的主键值
    stockVO.setReqTrantypeid(this.getHeadFullPath(SaleOrderHVO.CTRANTYPEID));
    // 表头主键
    stockVO.setReqid(this.getHeadFullPath(SaleOrderHVO.CSALEORDERID));
    // 表体主键
    stockVO.setReqbid(this.getBodyFullPath(SaleOrderBVO.CSALEORDERBID));
    // 单据行号
    stockVO.setReqRowno(this.getBodyFullPath(SaleOrderBVO.CROWNO));
    // 库存组织
    stockVO.setReqOrgid(this.getBodyFullPath(SaleOrderBVO.CSENDSTOCKORGID));
    // 库存组织版本
    stockVO.setReqOrgvid(this.getBodyFullPath(SaleOrderBVO.CSENDSTOCKORGVID));
    // 物料主键
    stockVO.setMaterialid(this.getBodyFullPath(SaleOrderBVO.CMATERIALID));
    // 物料版本主键
    stockVO.setMaterialvid(this.getBodyFullPath(SaleOrderBVO.CMATERIALVID));
    // 物料主单位
    stockVO.setCunitid(this.getBodyFullPath(SaleOrderBVO.CUNITID));
    // 单据号
    stockVO.setReqCode(this.getHeadFullPath(SaleOrderHVO.VBILLCODE));
    // 需求日期(计划发货日期)
    stockVO.setReqDate(this.getBodyFullPath(SaleOrderBVO.DSENDDATE));
    // 单据日期
    stockVO.setBillDate(this.getBodyFullPath(SaleOrderBVO.DBILLDATE));
    // 需求主数量 = 主数量-累计出库数量
    StringBuilder reqNum = new StringBuilder();
    String num = this.getBodyFullPath(SaleOrderBVO.NNUM);
    reqNum.append(" isnull( " + num + ", 0)");
    reqNum.append(" - ");
    String totalNum = this.getExeFullPath("ntotaloutnum");
    reqNum.append(" isnull( " + totalNum + ", 0)");
    stockVO.setNnum(reqNum.toString());
    // 预留数量
    stockVO.setNallocnum(this.getExeFullPath(SaleOrderBVO.NREQRSNUM));// 预留主数量
  }

  /**
   * 销售订单主表字段的全路径
   * 
   * @param fieldName 字段名
   * @return 表名.字段名
   */
  private String getHeadFullPath(String fieldName) {
    return SOTable.SALEORDER.getName() + SOConstant.POINT + fieldName;
  }

  /**
   * 销售订单子表字段的全路径
   * 
   * @param fieldName 字段名
   * @return 表名.字段名
   */
  private String getBodyFullPath(String fieldName) {
    return SOTable.SALEORDER_B.getName() + SOConstant.POINT + fieldName;
  }

  /**
   * 销售订单执行表字段的全路径
   * 
   * @param fieldName 字段名
   * @return 表名.字段名
   */
  private String getExeFullPath(String fieldName) {
    return SOTable.SALEORDER_EXE.getName() + SOConstant.POINT + fieldName;
  }

  /**
   * 设置取数所需sql的from和where部分，并填充到VO中
   * 
   * @param stockVO 要填充的VO
   * @param sendStockOrg 库存组织
   * @param tempName 临时表（pk_material 物料OID,dstart 开始时间,dend 结束时间）
   * @param needRed 是否包含红字单据
   */
  private void setFromWhere(ReqResultForInvpVO stockVO, String sendStockOrg,
      String tempName, boolean needRed) {

    /* from部分 */
    SqlBuilder fromPart = new SqlBuilder();
    // 销售订单表和销售订单子表
    fromPart
        .append(" so_saleorder so_saleorder inner join so_saleorder_b so_saleorder_b ");
    fromPart
        .append(" on so_saleorder.csaleorderid = so_saleorder_b.csaleorderid ");
    // 销售订单执行表
    fromPart.append(" inner join so_saleorder_exe so_saleorder_exe ");
    fromPart
        .append(" on so_saleorder_b.csaleorderbid = so_saleorder_exe.csaleorderbid ");
    fromPart
        .append(" inner join  so_m30trantype so_m30trantype on so_m30trantype.ctrantypeid=so_saleorder.ctrantypeid  ");
    // 临时表
    fromPart.append(" inner join " + tempName + " " + tempName);
    fromPart.append(" on " + tempName
        + ".pk_material = so_saleorder_b.cmaterialid");
    stockVO.setFrom(fromPart.toString());
    /* from部分结束 */

    /* where部分 */
    SqlBuilder wherePart = new SqlBuilder();
    // 发货时间
    wherePart.append("so_saleorder_b.dsenddate between " + tempName
        + ".dstart " + " and " + tempName + ".dend");
    // 发货库存组织
    wherePart.append(" and ");
    wherePart.append("so_saleorder_b.csendstockorgid", sendStockOrg);
    // 未删除
    wherePart
        .append(" and so_saleorder.dr = 0 and so_saleorder_b.dr = 0 and so_saleorder_exe.dr = 0");
    // 自由态和审批态
    wherePart.append(" and ");
    int[] billStatus = new int[] {
      BillStatus.FREE.getIntValue(), BillStatus.AUDIT.getIntValue()
    };
    wherePart.append("so_saleorder_b.frowstatus", billStatus);
    // 出库关闭
    wherePart.append(" and isnull(so_saleorder_b.bboutendflag,'N') = 'N' ");
    if (!needRed) {
      wherePart.append(" and so_saleorder_b.nnum > 0");
    }
    // 本集团
    String pk_group = AppContext.getInstance().getPkGroup();
    wherePart.append(" and ");
    wherePart.append("so_saleorder.pk_group", pk_group);
    wherePart.append(" and ");
    wherePart.append("so_saleorder_b.pk_group", pk_group);
    // 非直运
    wherePart
        .append(" and so_m30trantype.fdirecttype",
            nc.vo.so.m30trantype.enumeration.DirectType.DIRECTTRAN_NO
                .getIntValue());
    stockVO.setWhere(wherePart.toString());
    /* where部分结束 */
  }
}
