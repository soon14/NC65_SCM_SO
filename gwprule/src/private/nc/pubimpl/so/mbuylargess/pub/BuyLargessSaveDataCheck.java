package nc.pubimpl.so.mbuylargess.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.mbuylargess.entity.BuyLargessBVO;
import nc.vo.so.mbuylargess.entity.BuyLargessHVO;
import nc.vo.so.mbuylargess.entity.BuyLargessVO;
import nc.vo.so.pub.util.BaseSaleClassUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.bs.ml.NCLangResOnserver;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.database.DataAccessUtils;

/**
 * 买赠设置保前数据校验公共逻辑
 * 
 * @since 6.3
 * @version 2014-04-09 15:42:41
 * @author 刘景
 */
public class BuyLargessSaveDataCheck {

  /**
   * 获取买赠设置保存失败信息
   * 
   * @param bills 买赠设置聚合VO数组
   * @return Map<校验失败数组下标,失败原因>
   */
  public Map<Integer, String> getCheckSaveErroMsgMap(BuyLargessVO[] bills) {
    Map<Integer, String> indexErromap = new HashMap<Integer, String>();
    int i = 0;
    for (BuyLargessVO bill : bills) {
      // 数据完整性校验
      if (VOChecker.isEmpty(bill.getChildrenVO())) {
        indexErromap
            .put(Integer.valueOf(i), nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006003_0", "04006003-0003")/*@res "单据体不能为空。"*/);
      }
      // 买赠设置保存时数据合法性校验包括非空校验和业务校验
      this.checkNotNull(bill, i, indexErromap);
      // 保存前唯一性校验：表头销售组织+物料+物料分类+物料销售分类+客户+客户分类+客户销售分类不允许重复
      this.checkUnique(bill, i, indexErromap);
      i++;
    }
    return indexErromap;
  }

  private void checkBody(BuyLargessBVO[] bodys, int index,
      Map<Integer, String> indexErromap) {
    if (null == bodys || bodys.length == 0) {
      return;
    }
    int length = bodys.length;
    for (int i = 0; i < length; i++) {
      BuyLargessBVO body = bodys[i];
      if (VOChecker.isEmpty(body.getNnum())
          || body.getNnum().compareTo(new UFDouble(0)) <= 0) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0004")/*@res "赠送数量不能为空，必须大于0。"*/);
      }
      if (VOChecker.isEmpty(body.getDbegdate())) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0005")/*@res "开始日期不能为空。"*/);
      }
      if (VOChecker.isEmpty(body.getDenddate())) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0006")/*@res "截止日期不能为空。"*/);
      }
      if (!VOChecker.isEmpty(body.getDbegdate())
          && !VOChecker.isEmpty(body.getDenddate())
          && body.getDbegdate().compareTo(body.getDenddate()) > 0) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0007")/*@res "截止日期不能小于开始日期。"*/);
      }
      if (!VOChecker.isEmpty(body.getFtoplimittype())
          && body.getFtoplimittype().intValue() != 2
          && VOChecker.isEmpty(body.getNtoplimitvalue())) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0008")/*@res "上限值不能为空。"*/);
      }
      if (!VOChecker.isEmpty(body.getFtoplimittype())
          && body.getFtoplimittype().intValue() == 1
          && VOChecker.isEmpty(body.getNprice())) {
        indexErromap
            .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                .getNCLangRes().getStrByID("4006003_0", "04006003-0009")/*@res "上限类型为金额，单价不能为空。"*/);
      }
      for (int j = 0; j < i; j++) {
        if (body.getPk_material().equals(bodys[j].getPk_material())) {
          if (this.isAfter(body.getDenddate(), bodys[j].getDbegdate())
              && this.isAfter(bodys[j].getDenddate(), body.getDbegdate())) {
            indexErromap
                .put(Integer.valueOf(index), nc.vo.ml.NCLangRes4VoTransl
                    .getNCLangRes().getStrByID("4006003_0", "04006003-0010")/*@res "相同赠品物料，赠品时间段有交叉重叠，请重新输入。"*/);
          }
        }
      }
    }
  }

  private void checkHead(BuyLargessHVO head, boolean isbas, int index,
      Map<Integer, String> indexErromap) {
    StringBuilder errmsg = new StringBuilder();
    if (isbas) {
      if (VOChecker.isEmpty(head.getCbuymarid())
          && VOChecker.isEmpty(head.getPk_marbasclass())) {
        errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
            "04006003-0022")/*物料编码和物料分类不能同时为空.*/);
        errmsg.append("\n");
      }

    }
    if (!isbas) {
      if (VOChecker.isEmpty(head.getCbuymarid())
          && VOChecker.isEmpty(head.getPk_marsaleclass())) {
        errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
            "04006003-0024")/*物料编码和物料销售分类不能同时为空.*/);
        errmsg.append("\n");
      }
    }
    // 单位
    if (PubAppTool.isNull(head.getCbuyunitid())) {
      errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0025")/*单位不能为空.*/);
      errmsg.append("\n");
    }
    // 购买数量
    if (VOChecker.isEmpty(head.getNbuynum())
        || head.getNbuynum().compareTo(new UFDouble(0)) <= 0) {
      errmsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0026")/*购买数量不能为空，必须大于0.*/);
      errmsg.append("\n");
    }
    if (errmsg.length() > 0) {
      indexErromap.put(Integer.valueOf(index), errmsg.toString());
    }
  }

  /**
   * 方法功能描述：判断日期date1和date2先后顺序：若date1或date2有一为空返回true；否则返回date1是否晚于date2。
   * <b>参数说明</b>
   * 
   * @param date1
   * @param date2
   * @return
   * @author fengjb
   * @time 2009-6-4 下午07:22:48
   */
  private boolean isAfter(UFDate date1, UFDate date2) {
    if (null == date1 || null == date2) {
      return true;
    }
    return date1.after(date2) || date1.equals(date2);
  }

  private void checkNotNull(BuyLargessVO bill, int index,
      Map<Integer, String> indexErromap) {
    // 表头数据合法性校验
    BuyLargessHVO head = bill.getParentVO();
    String pk_group = head.getPk_group();
    // 物料、物料分类不能同时为空
    boolean isbas = BaseSaleClassUtil.isMarBaseClass(pk_group);
    this.checkHead(head, isbas, index, indexErromap);
    BuyLargessBVO[] bodys = bill.getChildrenVO();
    this.checkBody(bodys, index, indexErromap);
  }

  private SqlBuilder getSql(SqlBuilder querysql, BuyLargessHVO head) {
    querysql
        .append("select so_buylargess.pk_buylargess from so_buylargess where ");
    querysql.append("so_buylargess.pk_org", head.getPk_org());
    // 物料
    querysql.append(" and ");
    querysql.append("so_buylargess.cbuymarid", head.getCbuymarid());
    // 物料分类
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_marbasclass", head.getPk_marbasclass());
    // 物料销售分类
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_marsaleclass", head.getPk_marsaleclass());
    // 客戶
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_customer", head.getPk_customer());
    // 客户分类
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_custclass", head.getPk_custclass());
    // 客户销售分类
    querysql.append(" and ");
    querysql.append("so_buylargess.pk_custsaleclass",
        head.getPk_custsaleclass());

    // 删除标志
    querysql.append(" and  so_buylargess.dr = 0 ");
    return querysql;
  }

  private void checkUnique(BuyLargessVO bill, int i,
      Map<Integer, String> indexErromap) {
    SqlBuilder querysql = new SqlBuilder() {

      @Override
      public void append(String name, String value) {
        if (VOChecker.isEmpty(value)) {
          super.append(name);
          super.append(" = '~' ");
        }
        else {
          super.append(name, value);
        }

      }
    };
    BuyLargessHVO head = bill.getParentVO();
    querysql = this.getSql(querysql, head);
    StringBuffer errMsg = new StringBuffer();
    errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
        "04006003-0013")/*销售组织+*/);

    String material = head.getCbuymarid();
    if (PubAppTool.isNull(material)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0014")/*物料+*/);
    }
    String marbascl = head.getPk_marbasclass();
    String pk_group = BSContext.getInstance().getGroupID();
    boolean ismarbas = BaseSaleClassUtil.isMarBaseClass(pk_group);
    if (ismarbas && PubAppTool.isNull(marbascl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0015")/*物料分类+*/);
    }
    String marsalecl = head.getPk_marsaleclass();
    if (!ismarbas && PubAppTool.isNull(marsalecl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0016")/*物料销售分类+*/);
    }

    String custid = head.getPk_customer();
    if (PubAppTool.isNull(custid)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0017")/*客户+*/);
    }

    String custbasecl = head.getPk_custclass();
    boolean iscustbas = BaseSaleClassUtil.isCustBaseClass(pk_group);
    if (iscustbas && PubAppTool.isNull(custbasecl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0018")/*客户分类+*/);
    }

    String custsalecl = head.getPk_custsaleclass();
    if (!iscustbas && PubAppTool.isNull(custsalecl)) {
      errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
          "04006003-0019")/*客户销售分类+*/);
    }
    errMsg.deleteCharAt(errMsg.length() - 1);

    errMsg.append(NCLangResOnserver.getInstance().getStrByID("4006003_0",
        "04006003-0020")/*重复！*/);
    DataAccessUtils util = new DataAccessUtils();
    IRowSet rs = util.query(querysql.toString());
    while (rs.next()) {
      String oldpk = rs.getString(0);
      String newpk = head.getPk_buylargess();
      if (null == newpk || !newpk.equals(oldpk)) {
        indexErromap.put(Integer.valueOf(i), errMsg.toString());
      }
    }
  }
}
