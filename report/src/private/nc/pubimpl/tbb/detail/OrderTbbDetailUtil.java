package nc.pubimpl.tbb.detail;

import nc.bs.framework.common.NCLocator;
import nc.itf.so.m30.self.ISaleOrderMaintain;
import nc.pubitf.so.tbb.SOTbbFieldConst;
import nc.pubitf.so.tbb.SOUninureStatus;
import nc.pubitf.so.tbb.detail.SODetailDataProvider;
import nc.pubitf.so.tbb.saleorder.SaleOrderDateMetaPath;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.tbb.TbbRegister;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.tb.obj.NtbParamVO;

public class OrderTbbDetailUtil {
  // TODO 冯加滨 2012.03.14
  public SaleOrderViewVO[] getExecDataBatch(NtbParamVO ntbparamvos) {

    TbbRegister listregister = this.getSOTbbRegister();
    SODetailDataProvider provider = new SODetailDataProvider();
    String sql = "select distinct(so_saleorder_b.csaleorderbid) ";
    String where = provider.getExecDatas(listregister, ntbparamvos);
    sql = sql + where;
    sql = sql + " and so_saleorder_b.dr=0";
    ISaleOrderMaintain service =
        NCLocator.getInstance().lookup(ISaleOrderMaintain.class);
    try {
      SaleOrderViewVO[] vos = service.querySaleorderForTbb(sql);
      return vos;
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private TbbRegister getSOTbbRegister() {

    /** 添加销售订单注册信息 */
    TbbRegister m30register =
        new TbbRegister(SOBillType.Order.getCode(), SaleOrderHVO.class);
    // 1.添加数据库注册普通字段
    // 财务组织
    m30register.addNormalPara(SOTbbFieldConst.CSETTLEORGID,
        "so_saleorder_b.csettleorgid");
    // 发货库存组织
    m30register.addNormalPara(SOTbbFieldConst.CSENDSTOCKORGID,
        "so_saleorder_b.csendstockorgid");
    // 物料
    m30register.addNormalPara(SOTbbFieldConst.CMATERIALID,
        "so_saleorder_b.cmaterialid");
    // 客户
    m30register.addNormalPara(SOTbbFieldConst.CUSTOMER, "ccustomerid");
    // 业务员
    m30register.addNormalPara(SOTbbFieldConst.CEMPLOYEEID, "cemployeeid");
    // 产品线
    m30register.addNormalPara(SOTbbFieldConst.CPRODLINEID,
        "so_saleorder_b.cprodlineid");
    // 品牌
    m30register.addNormalPara(SOTbbFieldConst.BRANDDOC,
        "so_saleorder_b.cmaterialid.pk_brand");
    // 销售渠道类型
    m30register.addNormalPara(SOTbbFieldConst.CCHANNELTYPEID,
        SaleOrderHVO.CCHANNELTYPEID);

    // 2.添加数据库注册级次字段
    // 销售组织
    m30register.addLevelPara(SOTbbFieldConst.SALEORG, "so_saleorder_b.pk_org");
    // 地区分类
    m30register
        .addLevelPara(SOTbbFieldConst.AREACLASS, "ccustomerid.pk_areacl");
    // 客户分类
    m30register.addLevelPara(SOTbbFieldConst.CUSTOMERCLASS,
        "ccustomerid.pk_custclass");
    // 部门
    m30register.addLevelPara(SOTbbFieldConst.CDEPTID, "cdeptid");
    // 物料基本分类
    m30register.addLevelPara(SOTbbFieldConst.INVCLASS,
        "so_saleorder_b.cmaterialvid.pk_marbasclass");
    // 项目
    m30register.addLevelPara(SOTbbFieldConst.CPROJECTID,
        "so_saleorder_b.cprojectid");

    // 3.添加日期元数据路径映射接口
    m30register.setITbbDateMetaPath(new SaleOrderDateMetaPath());
    // 4.添加原币币种元数据路径
    m30register.setOrigcurrtypeMetaPath(SaleOrderHVO.CORIGCURRENCYID);
    // 5.添加冗余字段信息
    // m30register.addParaReduncyMap("dbilldate", "so_saleorder_b.dbilldate");
    // m30register.addParaReduncyMap("pk_org", "so_saleorder_b.pk_org");
    // 6.添加包含未生效接口
    m30register.setITbbUninureStatus(new SOUninureStatus());

    return m30register;
  }
}
