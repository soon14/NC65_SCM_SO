package nc.pubimpl.tbb.detail;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.pubitf.so.tbb.SODateMetaPath;
import nc.pubitf.so.tbb.SOTbbFieldConst;
import nc.pubitf.so.tbb.SOUninureStatus;
import nc.pubitf.so.tbb.detail.SODetailDataProvider;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.tbb.TbbRegister;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceViewVO;
import nc.vo.tb.obj.NtbParamVO;

public class InvoiceTbbDetailUtil {
  // TODO 冯加滨 2012.03.14
  public SaleInvoiceViewVO[] getExecDataBatch(NtbParamVO ntbparamvos) {

    TbbRegister listregister = this.getSOTbbRegister();
    SODetailDataProvider provider = new SODetailDataProvider();
    String sql = "select distinct(so_saleinvoice_b.csaleinvoicebid) ";
    String where = provider.getExecDatas(listregister, ntbparamvos);
    sql = sql + where;
    sql = sql + " and so_saleinvoice_b.dr=0";
    ISaleInvoiceMaintain service =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
    try {
      SaleInvoiceViewVO[] vos = service.queryViewvo(sql);
      return vos;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private TbbRegister getSOTbbRegister() {

    /** 添加销售发票注册信息 */
    TbbRegister m32register =
        new TbbRegister(SOBillType.Invoice.getCode(), SaleInvoiceHVO.class);
    // 1.添加数据库注册普通字段
    // 财务组织
    m32register.addNormalPara(SOTbbFieldConst.CSETTLEORGID, "pk_org");
    // 发货库存组织
    m32register.addNormalPara(SOTbbFieldConst.CSENDSTOCKORGID,
        "csaleinvoicebid.csendstockorgid");
    // 物料
    m32register.addNormalPara(SOTbbFieldConst.CMATERIALID,
        "csaleinvoicebid.cmaterialid");
    // 客户
    m32register.addNormalPara(SOTbbFieldConst.CUSTOMER,
        "csaleinvoicebid.cordercustid");
    // 业务员
    m32register.addNormalPara(SOTbbFieldConst.CEMPLOYEEID,
        "csaleinvoicebid.cemployeeid");
    // 产品线
    m32register.addNormalPara(SOTbbFieldConst.CPRODLINEID,
        "csaleinvoicebid.cprodlineid");
    // 品牌
    m32register.addNormalPara(SOTbbFieldConst.BRANDDOC,
        "csaleinvoicebid.cmaterialid.pk_brand");
    // 销售取道类型--暂不处理

    // 2.添加数据库注册级次字段
    // 销售组织
    m32register.addLevelPara(SOTbbFieldConst.SALEORG,
        "csaleinvoicebid.csaleorgid");
    // 地区分类
    m32register.addLevelPara(SOTbbFieldConst.AREACLASS,
        "csaleinvoicebid.cordercustid.pk_areacl");
    // 客户分类
    m32register.addLevelPara(SOTbbFieldConst.CUSTOMERCLASS,
        "csaleinvoicebid.cordercustid.pk_custclass");
    // 部门
    m32register
        .addLevelPara(SOTbbFieldConst.CDEPTID, "csaleinvoicebid.cdeptid");
    // 物料基本分类
    m32register.addLevelPara(SOTbbFieldConst.INVCLASS,
        "csaleinvoicebid.cmaterialvid.pk_marbasclass");
    // 项目
    m32register.addLevelPara(SOTbbFieldConst.CPROJECTID,
        "csaleinvoicebid.cprojectid");

    // 3.添加日期元数据路径映射接口
    m32register.setITbbDateMetaPath(new SODateMetaPath());
    // 4.添加原币币种元数据路径
    m32register.setOrigcurrtypeMetaPath(SaleInvoiceHVO.CORIGCURRENCYID);
    // 5.添加冗余字段信息
    // m32register.addParaReduncyMap("dbilldate", "csaleinvoicebid.dbilldate");
    // m32register.addParaReduncyMap("pk_org", "csaleinvoicebid.pk_org");
    // 6.添加包含未生效接口
    m32register.setITbbUninureStatus(new SOUninureStatus());

    return m32register;
  }
}
