package nc.pubimpl.tbb;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.itf.tb.control.IAccessableBusiVO;
import nc.itf.tb.control.IBusiSysExecDataProvider;
import nc.pubitf.so.tbb.SODateMetaPath;
import nc.pubitf.so.tbb.SOTbbFieldConst;
import nc.pubitf.so.tbb.SOUninureStatus;
import nc.pubitf.so.tbb.saleinvoice.SaleInvoiceExtender;
import nc.pubitf.so.tbb.saleinvoice.SaleInvoiceSelectItem;
import nc.pubitf.so.tbb.saleorder.SaleOrderDateMetaPath;
import nc.pubitf.so.tbb.saleorder.SaleOrderExtender;
import nc.pubitf.so.tbb.saleorder.SaleOrderSelectItem;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.scmpub.tbb.TbbExecDataProvider;
import nc.vo.scmpub.tbb.TbbRegister;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.tb.obj.NtbParamVO;

public class SOToTbbProvider implements IBusiSysExecDataProvider {

  // TODO 冯加滨 2012.03.14
  @Override
  public void createBillType(NtbParamVO[] arg0) throws BusinessException {
    // 不用实现
  }

  @Override
  public int getCtlPoint(String arg0) throws RemoteException {
    return 0;
  }

  @Override
  public IAccessableBusiVO[] getCvtProvider(IAccessableBusiVO[] arg0)
      throws RemoteException {
    return null;
  }

  @Override
  public UFDouble[] getExecData(NtbParamVO arg0) throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[][] getExecDataBatch(NtbParamVO[] ntbparamvos)
      throws BusinessException {

    List<TbbRegister> listregister = this.getSOTbbRegister();
    TbbExecDataProvider provider = new TbbExecDataProvider();
    UFDouble[][] result = provider.getExecDatas(listregister, ntbparamvos);
    return result;
  }

  private List<TbbRegister> getSOTbbRegister() {
    List<TbbRegister> listregister = new ArrayList<TbbRegister>();

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
    // 6.添加选择字段映射接口
    m30register.setITbbSelectItem(new SaleOrderSelectItem());
    // 7.添加包含未生效接口
    m30register.setITbbUninureStatus(new SOUninureStatus());
    // 8.添加扩展接口
    m30register.setFuncSqlExtender(new SaleOrderExtender());

    listregister.add(m30register);

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
    // 6.添加选择字段映射接口
    m32register.setITbbSelectItem(new SaleInvoiceSelectItem());
    // 7.添加包含未生效接口
    m32register.setITbbUninureStatus(new SOUninureStatus());
    // 8.添加扩展接口
    m32register.setFuncSqlExtender(new SaleInvoiceExtender());

    listregister.add(m32register);

    return listregister;
  }

  @Override
  public UFDouble[] getPointData(NtbParamVO arg0) throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[][] getPointDataBatch(NtbParamVO[] arg0)
      throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[] getReadyData(NtbParamVO arg0) throws BusinessException {
    return null;
  }

  @Override
  public UFDouble[][] getReadyDataBatch(NtbParamVO[] arg0)
      throws BusinessException {
    return null;
  }

  @Override
  public Map<NtbParamVO, Map<String, UFDouble[]>> getExecDataGroupBatch(
      String groupDocType, Map<NtbParamVO, List<String>> groupParaVOs,
      Map<String, String[]> childGroupDocs) throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<NtbParamVO, Map<String, UFDouble[]>> getReadyDataGroupBatch(
      String groupDocType, Map<NtbParamVO, List<String>> groupParaVOs,
      Map<String, String[]> childGroupDocs) throws BusinessException {
    // TODO Auto-generated method stub
    return null;
  }

 
}
