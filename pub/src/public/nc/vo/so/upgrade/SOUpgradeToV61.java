package nc.vo.so.upgrade;

import nc.vo.pub.BusinessException;
import nc.vo.scmpub.res.DBHintConstantValue;
import nc.vo.scmpub.vatupdate.VatFieldEnum;
import nc.vo.scmpub.vatupdate.VatUpdateProcess;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.SOTable;

import nc.impl.pubapp.pattern.database.DataAccessUtils;

import nc.scmmm.upgrade.scmpub.AbstractUpgradeToV61;

/**
 * 销售管理V61升级类
 * 
 * @since 6.1
 * @version 2012-11-29 09:50:45
 * @author 冯加彬
 */
public class SOUpgradeToV61 extends AbstractUpgradeToV61 {

  @Override
  protected void doAfterUpdateDataFrom60To61() throws BusinessException {

    DataAccessUtils accessutil = new DataAccessUtils();

    /*** 升级V60->V61更改字段 begin */
    // 发货单主表发货部门、发货部门最新版本、发货计划员字段变更
    String parallel = DBHintConstantValue.getHintCode("so_delivery");

    String deliverysql =
        "update "
            + parallel
            + "so_delivery set csenddeptvid  = cdeptvid ,csenddeptid  = cdeptid ,csendemployeeid  = cemployeeid  where dr = 0";
    accessutil.update(deliverysql);

    // 销售发票结算单子表行备注字段变更
    parallel = DBHintConstantValue.getHintCode("so_squareinv_b");
    String squareinvsql =
        " update " + parallel
            + "so_squareinv_b set vrownote = frownote  where dr = 0";
    accessutil.update(squareinvsql);

    // 销售出库结算单子表行备注字段变更
    parallel = DBHintConstantValue.getHintCode("so_squareout_b");
    String squareoutsql =
        " update " + parallel
            + "so_squareout_b set vrownote = frownote  where dr = 0";
    accessutil.update(squareoutsql);

    // 途损结算单子表行备注字段变更
    parallel = DBHintConstantValue.getHintCode("so_squarewas_b");
    String squarewassql =
        " update " + parallel
            + "so_squarewas_b set vrownote = frownote  where dr = 0";
    accessutil.update(squarewassql);

    /*** 升级V60->V61更改字段 end */

    /*** 升级国家、购销类型、三角贸易、扣税类别等数据 begin */
    // 升级VAT
    VatUpdateProcess vatupdate = new VatUpdateProcess();
    // 报价单子表：收货国家/地区、发货国/地区、报税国/地区、购销类型、三角贸易、扣税类别
    VatFieldEnum[] m4331vatfields =
        new VatFieldEnum[] {
          VatFieldEnum.CRECECOUNTRYID, VatFieldEnum.CSENDCOUNTRYID,
          VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.FBUYSELLFLAG,
          VatFieldEnum.BTRIATRADEFLAG, VatFieldEnum.FTAXTYPEFLAG,
        };
    vatupdate.processVatUpdate(SOTable.SALEQUOTATION_B.getName(),
        m4331vatfields);

    // 预订单子表、销售订单子表、发货单子表、发货单质检表：
    // 收货国家/地区、发货国/地区、报税国/地区、购销类型、三角贸易、扣税类别、计税金额
    VatFieldEnum[] pubvatfields =
        new VatFieldEnum[] {
          VatFieldEnum.CRECECOUNTRYID, VatFieldEnum.CSENDCOUNTRYID,
          VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.FBUYSELLFLAG,
          VatFieldEnum.BTRIATRADEFLAG, VatFieldEnum.FTAXTYPEFLAG,
          VatFieldEnum.NCALTAXMNY
        };
    vatupdate.processVatUpdate(SOTable.PREORDER_B.getName(), pubvatfields);
    vatupdate.processVatUpdate(SOTable.SALEORDER_B.getName(), pubvatfields);
    vatupdate.processVatUpdate(SOTable.DELIVERY_B.getName(), pubvatfields);
    vatupdate.processVatUpdate(SOTable.DELIVERY_CHECK.getName(), pubvatfields);

    // 销售发票主表、发票结算清单主表、出库结算单主表、途损结算单主表:
    // 收货国家/地区、发货国/地区、报税国/地区、购销类型、三角贸易
    VatFieldEnum[] h_vatfields =
        new VatFieldEnum[] {
          VatFieldEnum.CRECECOUNTRYID, VatFieldEnum.CSENDCOUNTRYID,
          VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.FBUYSELLFLAG,
          VatFieldEnum.BTRIATRADEFLAG
        };
    vatupdate.processVatUpdate(SOTable.SALEINVOICE.getName(), h_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREINV.getName(), h_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREOUT.getName(), h_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREWAS.getName(), h_vatfields);

    // 销售发票子表、发票结算单子表、发票结算明细表、出库结算单子表、出库结算明细表、途损结算单子表、途损结算明细表：
    // 税码、扣税类别
    VatFieldEnum[] b_vatfields = new VatFieldEnum[] {
      VatFieldEnum.FTAXTYPEFLAG, VatFieldEnum.NCALTAXMNY
    };
    vatupdate.processVatUpdate(SOTable.SALEINVOICE_B.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREINV_B.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREINV_D.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREOUT_B.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREOUT_D.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREWAS_B.getName(), b_vatfields);
    vatupdate.processVatUpdate(SOTable.SQUAREWAS_D.getName(), b_vatfields);
    /*** 升级国家、购销类型、三角贸易、扣税类别等数据 end */

    /*** 升级税码数据 begin */
    // 报价单子表
    vatupdate.processTaxCodeUpdate(SOTable.SALEQUOTATION_B.getName(),
        SOItemKey.CTAXCODEID, "pk_material_v");
    // 预订单子表
    vatupdate.processTaxCodeUpdate(SOTable.PREORDER_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // 销售订单子表
    vatupdate.processTaxCodeUpdate(SOTable.SALEORDER_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // 发货单子表
    vatupdate.processTaxCodeUpdate(SOTable.DELIVERY_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // // 发货单质检表
    vatupdate.processTaxCodeUpdate(SOTable.DELIVERY_CHECK.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // 销售发票子表
    vatupdate.processTaxCodeUpdate(SOTable.SALEINVOICE_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // 发票结算单子表
    vatupdate.processTaxCodeUpdate(SOTable.SQUAREINV_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // 出库结算单子表
    vatupdate.processTaxCodeUpdate(SOTable.SQUAREOUT_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);
    // 途损结算单子表
    vatupdate.processTaxCodeUpdate(SOTable.SQUAREWAS_B.getName(),
        SOItemKey.CTAXCODEID, SOItemKey.CMATERIALVID);

    // 结算明细表：发票结算明细表、出库结算明细表、途损结算明细表
    String parallel_d = DBHintConstantValue.getHintCode("so_squareinv_d");
    String parallel_b = DBHintConstantValue.getHintCode("so_squareinv_b");
    String squareinvtaxsql =
        "update "
            + parallel_d
            + "so_squareinv_d set so_squareinv_d.ctaxcodeid = "
            + "( select "
            + parallel_b
            + "so_squareinv_b.ctaxcodeid from so_squareinv_b where so_squareinv_b.csalesquarebid = so_squareinv_d.csalesquarebid and so_squareinv_b.dr = 0 ) where so_squareinv_d.dr = 0 ";
    accessutil.update(squareinvtaxsql);

    parallel_d = DBHintConstantValue.getHintCode("so_squareout_d");
    parallel_b = DBHintConstantValue.getHintCode("so_squareout_b");
    String squareouttaxsql =
        "update "
            + parallel_d
            + "so_squareout_d set so_squareout_d.ctaxcodeid = "
            + "( select "
            + parallel_b
            + "so_squareout_b.ctaxcodeid from so_squareout_b where so_squareout_b.csalesquarebid = so_squareout_d.csalesquarebid and so_squareout_b.dr = 0 ) where so_squareout_d.dr = 0 ";
    accessutil.update(squareouttaxsql);

    parallel_d = DBHintConstantValue.getHintCode("so_squarewas_d");
    parallel_b = DBHintConstantValue.getHintCode("so_squarewas_b");
    String squarewastaxsql =
        "update "
            + parallel_d
            + "so_squarewas_d set so_squarewas_d.ctaxcodeid = "
            + "( select "
            + parallel_b
            + "so_squarewas_b.ctaxcodeid from so_squarewas_b where so_squarewas_b.csalesquarebid = so_squarewas_d.csalesquarebid and so_squarewas_b.dr = 0 ) where so_squarewas_d.dr = 0 ";
    accessutil.update(squarewastaxsql);
    /*** 升级税码数据 end */

    /*** 升级单据模板 begin */
    // 删除原币税额
    String sotempsql =
        "delete from pub_billtemplet_b where pk_billtemplet in "
            + "(select h.pk_billtemplet from  pub_billtemplet h  where h.nodecode  like '4006%') and itemkey = 'norigtax'";
    accessutil.update(sotempsql);

    // 更新单据模板物料编辑关联项
    sotempsql =
        "update pub_billtemplet_b set metadatarelation =replace(metadatarelation,',ntaxrate=pk_taxitems.taxrate','') where "
            + "metadatarelation like '%pk_taxitems.taxrate%' and pk_billtemplet in ( select h.pk_billtemplet from pub_billtemplet h where h.nodecode like '4006%') ";
    accessutil.update(sotempsql);

    // 删除发货单发货部门、业务员
    String deliverttempsql =
        "delete from pub_billtemplet_b where pk_billtemplet in "
            + "(select h.pk_billtemplet from  pub_billtemplet h  where h.nodecode  = '40060402') and metadataproperty in ('so.delivery.cdeptvid','so.delivery.cdeptid','so.delivery.cemployeeid')";
    accessutil.update(deliverttempsql);
    /*** 升级单据模板 end */

    /*** 升级数据交换 begin */
    String sovochgsql =
        "delete from pub_vochange_b where pk_vochange in "
            + "( select h.pk_vochange from pub_vochange h where h.dest_billtype in('4310','30','4331','32','434C','4332','4353') ) and dest_attr like '%norigtax'";
    accessutil.update(sovochgsql);

    sovochgsql =
        "delete from pub_vochange_b where pk_vochange in "
            + "(select h.pk_vochange from pub_vochange h where h.src_billtype = '4H' and h.dest_billtype = '30') and dest_attr = 'so_saleorder_b.ntaxrate'";
    accessutil.update(sovochgsql);
    /*** 升级数据交换 end */

    /*** 升级预置参数 begin */
    String parachgsql =
        "delete from pub_sysinittemp where initcode in('SO23','SO24','SO25') ";
    accessutil.update(parachgsql);

    parachgsql =
        "delete from pub_sysinit where initcode in('SO23','SO24','SO25') ";
    accessutil.update(parachgsql);
    /*** 升级预置参数 end */

    /*** 升级订单审批流状态 begin **/
    // 销售订单主表V61添加审批流状态fpfstatusflag字段，升级时赋值规则为：
    // 当订单单据状态为自由时，审批流状态为IPfRetCheckInfo.NOSTATE = -1
    // 当订单单据状态为审批未通过时，审批流状态为IPfRetCheckInfo.NOPASS = 0
    // 当订单单据状态为审批中时，审批流状态为IPfRetCheckInfo.GOINGON = 2
    // 当订单单据状态为审批/冻结/关闭时，审批流状态为IPfRetCheckInfo.PASSING = 1
    String parallel_table = DBHintConstantValue.getHintCode("so_saleorder");
    String pfstatesql =
        "update "
            + parallel_table
            + " so_saleorder set fpfstatusflag = case when fstatusflag = 1 then -1 when fstatusflag = 8 then 0 when fstatusflag = 7 then 2 else 1 end  where dr = 0 ";
    accessutil.update(pfstatesql);
    /*** 升级订单审批流状态 end **/
  }
}
