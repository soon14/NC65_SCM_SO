package nc.impl.so.m38.migrate.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.page.IPage;
import nc.impl.pubapp.pattern.page.SecondaryPage;
import nc.impl.pubapp.pattern.page.db.IDDBPage;
import nc.impl.so.m38.migrate.constant.OPC_PreData;
import nc.pubitf.opc.mc1.opc.m38.ISaveCustomerPO;
import nc.vo.opc.mc1.entity.CustomerPOBVO;
import nc.vo.opc.mc1.entity.CustomerPOHVO;
import nc.vo.opc.mc1.entity.CustomerPOVO;
import nc.vo.opc.mc1trantype.enumeration.SaleMode;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m38.entity.PreOrderBVO;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.enumeration.BillStatus;

public class PreOrderDataMigrateAction {

  /**
   * 每页大小
   */
  private static final int fetchSize = 500;

  private final Integer OPC_FREE;

  private final Integer OPC_AUDIT;

  private final Integer OPC_AUDITING;

  private final Integer OPC_INVALIDATE;

  private final Integer OPC_SIGN;

  private final Integer OPC_FINISH;

  private final Integer OPC_NOPASS;

  public PreOrderDataMigrateAction() {
    OPC_FREE = nc.vo.opc.opcpub.enumeration.BillStatus.FREE.getIntegerValue();
    OPC_AUDIT = nc.vo.opc.opcpub.enumeration.BillStatus.AUDIT.getIntegerValue();
    OPC_AUDITING =
        nc.vo.opc.opcpub.enumeration.BillStatus.AUDITING.getIntegerValue();
    OPC_INVALIDATE =
        nc.vo.opc.opcpub.enumeration.BillStatus.INVALIDATE.getIntegerValue();
    OPC_SIGN = nc.vo.opc.opcpub.enumeration.BillStatus.SIGN.getIntegerValue();
    OPC_FINISH =
        nc.vo.opc.opcpub.enumeration.BillStatus.FINISH.getIntegerValue();
    OPC_NOPASS =
        nc.vo.opc.opcpub.enumeration.BillStatus.NOPASS.getIntegerValue();
  }

  /**
   * 预订单单据迁移
   * 
   * @author liylr @2015-01-09
   */
  public void migrate(Map<String, String> oldNewTrantypeIdMap) {
    IDDBPage ds =
        new IDDBPage("select cpreorderid from so_preorder where dr=0");
    ISaveCustomerPO save_inter =
        NCLocator.getInstance().lookup(ISaveCustomerPO.class);
    IPage<String> page = new SecondaryPage<String>(ds, fetchSize);
    while (page.hasNext()) {
      String[] ids = page.next();
      // 1. 准备数据
      PreOrderHVO[] hvos = getPreOrderHVOs(ids);
      PreOrderBVO[] bvos = getPreOrderBVOs(ids);
      Map<String, SaleOrderBVO> saleOrderBVOMap = getSaleOrderBVO(ids);

      // 2. 数据转换
      CustomerPOVO[] customerPOVOs =
          transferVOs(hvos, bvos, oldNewTrantypeIdMap, saleOrderBVOMap);

      // 3. 保存数据
      try {
        save_inter.saveCustomerPO(customerPOVOs);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
  }

  /**
   * 将销售预订单的表头表体VO转成EC预订单的聚合VO
   * 
   * @param preOrderHVOs
   * @param preOrderBVOs
   * @param saleOrderBVOMap
   * @return
   */
  private CustomerPOVO[] transferVOs(PreOrderHVO[] preOrderHVOs,
      PreOrderBVO[] preOrderBVOs, Map<String, String> oldNewTrantypeIdMap,
      Map<String, SaleOrderBVO> saleOrderBVOMap) {
    Map<String, SaleOrderBVO> preOrderBidDestBillValueMap =
        getDestBillValues(saleOrderBVOMap);
    CustomerPOHVO[] hvos =
        transferHVOs(preOrderHVOs, oldNewTrantypeIdMap, saleOrderBVOMap);
    CustomerPOBVO[] bvos =
        transferBVOs(preOrderBVOs, saleOrderBVOMap, preOrderBidDestBillValueMap);
    CustomerPOVO[] vos = aggvo(hvos, bvos, preOrderBidDestBillValueMap);
    return vos;
  }

  /**
   * 转化成Ec预订单的聚合VO
   * 
   * @param hvos
   * @param bvos
   * @param preOrderBidDestBillValueMap
   * @param saleOrderBVOMap
   * @return
   */
  private CustomerPOVO[] aggvo(CustomerPOHVO[] hvos, CustomerPOBVO[] bvos,
      Map<String, SaleOrderBVO> preOrderBidDestBillValueMap) {
    CustomerPOVO[] vos = new CustomerPOVO[hvos.length];
    MapList<String, CustomerPOBVO> map = new MapList<String, CustomerPOBVO>();
    for (CustomerPOBVO bvo : bvos) {
      map.put(bvo.getCcustomerpoid(), bvo);
    }

    // 根据pk找到对应的BVO，然后形成聚合VO
    for (int i = 0; i < hvos.length; i++) {
      int opc_finish_flag = 0;
      int opc_sign_flag = 0;
      CustomerPOHVO hvo = hvos[i];
      List<CustomerPOBVO> list = map.get(hvo.getCcustomerpoid());
      CustomerPOBVO[] cpbvo = new CustomerPOBVO[list.size()];
      // 遍历该HVO对应的所有BVO，设置每个表体行的状态
      for (int j = 0; j < list.size(); j++) {
        Integer billstate = hvo.getFstatusflag();
        CustomerPOBVO bvo = list.get(j);

        // 如果表头是安排关闭的（即销售预订单单据状态是关闭的或者冻结的），则表体全部为行关闭
        if (hvo.getBarrangeflag().booleanValue()) {
          bvo.setBlineclose(UFBoolean.TRUE);
        }
        // 首先行状态直接复制单据状态
        bvo.setFrowstatus(hvo.getFstatusflag());
        // 如果预订单状态为审批通过
        if (OPC_AUDIT.equals(billstate)) {

          // 1.累计安排数量>=主数量(即，!主数量>累计安排数量) 则行状态为安排关闭
          if (!MathTool.greaterThan(bvo.getNnum(), bvo.getNarrnum())) {
            bvo.setBlineclose(UFBoolean.TRUE);
          }

          // 2.当累计安排数量=0，且行关闭，则订单中心预订单行出库关闭。
          if (MathTool.equals(bvo.getNarrnum(), UFDouble.ZERO_DBL)
              && bvo.getBlineclose().booleanValue()) {
            bvo.setBboutendflag(UFBoolean.TRUE);
          }

          // 3. 当累计安排数量>0，且行关闭，下游销售订单均出库关闭，则订单中心预订单行出库关闭。
          SaleOrderBVO saleorderBVO =
              preOrderBidDestBillValueMap.get(bvo.getCcustomerpobid());
          boolean destBillOutSendClose;
          if (saleorderBVO == null) {
            destBillOutSendClose = false;
          }
          else {
            destBillOutSendClose =
                saleorderBVO.getBboutendflag().booleanValue();
          }
          if (MathTool.greaterThan(bvo.getNarrnum(), UFDouble.ZERO_DBL)
              && bvo.getBlineclose().booleanValue() && destBillOutSendClose) {
            bvo.setBboutendflag(UFBoolean.TRUE);
          }

          // 4. 累计出库数量大于0，行状态为已发货
          if (MathTool.greaterThan(bvo.getNtotaloutnum(), UFDouble.ZERO_DBL)) {
            opc_sign_flag++;
            bvo.setFrowstatus(OPC_SIGN);
          }

          // 5. 累计签收数量大于0，且出库关闭，行状态为交易完成
          if (MathTool.greaterThan(bvo.getNtotalsignnum(), UFDouble.ZERO_DBL)
              && bvo.getBboutendflag().booleanValue()) {
            opc_finish_flag++;
            bvo.setFrowstatus(OPC_FINISH);
          }

        }
        cpbvo[j] = bvo;
      }
      // 若表体行有已发货状态的，则表头单据状态更新为已发货
      if (opc_sign_flag > 0) {
        hvo.setFstatusflag(OPC_SIGN);
      }
      // 所有行状态均为“交易完成”时头状态转为“交易完成”
      if (opc_finish_flag == list.size()) {
        hvo.setFstatusflag(OPC_FINISH);
      }

      CustomerPOVO temp = new CustomerPOVO();
      temp.setParent(hvo);
      temp.setChildrenVO(cpbvo);
      vos[i] = temp;
    }
    return vos;
  }

  /**
   * 
   * 根据ID获取预订单表体VO
   * 
   * @author liylr
   */
  private PreOrderBVO[] getPreOrderBVOs(String[] ids) {
    // 根据ids获取预订单表体VO
    IDQueryBuilder whereSql = new IDQueryBuilder();
    StringBuilder condition = new StringBuilder();
    condition.append(" and ");
    condition.append(whereSql.buildSQL(PreOrderBVO.CPREORDERID, ids));
    VOQuery<PreOrderBVO> srv = new VOQuery<PreOrderBVO>(PreOrderBVO.class);
    return srv.query(condition.toString(), null);
  }

  /**
   * 根据ID获取预订单表头VO
   * 
   * @author liylr
   */
  private PreOrderHVO[] getPreOrderHVOs(String[] ids) {
    // 根据ids获取预订单表头VO
    IDQueryBuilder idqb = new IDQueryBuilder();
    StringBuilder wheresql = new StringBuilder();
    wheresql.append(" and ");
    wheresql.append(idqb.buildSQL(PreOrderHVO.CPREORDERID, ids));
    VOQuery<PreOrderHVO> srv = new VOQuery<PreOrderHVO>(PreOrderHVO.class);
    return srv.query(wheresql.toString(), null);
  }

  /**
   * 将销售预订单表头VO转化成Ec预订单表头VO
   * 
   * @author liylr
   * @param vos
   * @param saleOrderBVOMap
   * @return
   */
  private CustomerPOHVO[] transferHVOs(PreOrderHVO[] vos,
      Map<String, String> oldNewTrantypeIdMap,
      Map<String, SaleOrderBVO> saleOrderBVOMap) {
    /*
     * 根据vos获取下游订单的出库关闭状态
     * 用于存储每个预订单对应的下游订单的关闭状态（每个预订单可能对应多个下游订单）
     */
    Map<String, Boolean> descBillCloseStatus = new HashMap<String, Boolean>();
    Set<String> saleorderbids = saleOrderBVOMap.keySet();
    for (String saleorderbid : saleorderbids) {
      // 这里只能查询出有下游订单的记录，没有下游订单的预订单行ID不会出现在这个map中
      UFBoolean bboutendflag =
          saleOrderBVOMap.get(saleorderbid).getBboutendflag();
      String cpreorderid = saleOrderBVOMap.get(saleorderbid).getCsrcid();
      if (descBillCloseStatus.get(cpreorderid) == null) {
        descBillCloseStatus.put(cpreorderid, convertBool(bboutendflag));
      }
      else {
        descBillCloseStatus.put(cpreorderid, convertBool(bboutendflag)
            && descBillCloseStatus.get(cpreorderid));
      }
    }

    CustomerPOHVO[] cusHVOs = new CustomerPOHVO[vos.length];
    for (int i = 0; i < vos.length; i++) {
      CustomerPOHVO cusHVO = new CustomerPOHVO();
      for (String field : this.HEADMIGFIELDS) {
        cusHVO.setAttributeValue(field, vos[i].getAttributeValue(field));
      }

      String code =
          new StringBuilder(OPC_PreData.ECC1_CODE).append("-")
              .append(vos[i].getVtrantypecode()).toString();
      cusHVO.setVtrantypecode(code);

      // 预订单主表
      cusHVO.setCcustomerpoid(vos[i].getCpreorderid());

      // 单据类型
      cusHVO.setCtrantypeid(oldNewTrantypeIdMap.get(cusHVO.getCtrantypeid()));

      // 安排关闭： 根据单据状态是关闭的或者冻结的，勾选（Y）；否则，不勾选（N）
      if (vos[i].getFstatusflag() == BillStatus.I_CLOSED
          || vos[i].getFstatusflag() == BillStatus.I_FREEZE) {
        cusHVO.setBarrangeflag(UFBoolean.TRUE);
      }
      else {
        cusHVO.setBarrangeflag(UFBoolean.FALSE);
      }

      // 出库关闭：如果单据状态关闭且下游订单行全部出库关闭（Y），则勾选（Y）；否则不勾选（N）。
      if (vos[i].getFstatusflag() == BillStatus.I_CLOSED
          && (descBillCloseStatus.get(vos[i].getCpreorderid()) == null ? false
              : descBillCloseStatus.get(vos[i].getCpreorderid()))) {
        cusHVO.setBoutendflag(UFBoolean.TRUE);
      }
      else {
        cusHVO.setBoutendflag(UFBoolean.FALSE);
      }

      // 单据状态
      int state = vos[i].getFstatusflag();
      switch (state) {
        case BillStatus.I_FREE:
          cusHVO.setFstatusflag(OPC_FREE);
          break;
        case BillStatus.I_INVALIDATE:
          cusHVO.setFstatusflag(OPC_INVALIDATE);
          break;
        case BillStatus.I_AUDITING:
          cusHVO.setFstatusflag(OPC_AUDITING);
          break;
        case BillStatus.I_NOPASS:
          cusHVO.setFstatusflag(OPC_NOPASS);
          break;
        case BillStatus.I_AUDIT:
          break;
        case BillStatus.I_CLOSED:
          break;
        case BillStatus.I_FREEZE:
          cusHVO.setFstatusflag(OPC_AUDIT);
          break;
      }

      cusHVOs[i] = cusHVO;
    }
    return cusHVOs;
  }

  /**
   * 将销售预订单表体VO转化成Ec预订单表体VO
   * 
   * @author liylr
   * @param vos
   * @param saleOrderBVOMap
   * @return
   */
  private CustomerPOBVO[] transferBVOs(PreOrderBVO[] vos,
      Map<String, SaleOrderBVO> saleOrderBVOMap,
      Map<String, SaleOrderBVO> preOrderBidDestBillValueMap) {
    CustomerPOBVO[] cusBVOs = new CustomerPOBVO[vos.length];
    for (int i = 0; i < vos.length; i++) {
      CustomerPOBVO cusBVO = new CustomerPOBVO();
      for (String attr : this.BODYMIGFIELDS) {
        cusBVO.setAttributeValue(attr, vos[i].getAttributeValue(attr));
      }
      // 预订单主表_主键
      cusBVO.setCcustomerpoid(vos[i].getCpreorderid());
      // 预订单子表主键
      cusBVO.setCcustomerpobid(vos[i].getCpreorderbid());
      // 批次档案
      cusBVO.setVbatchcode(vos[i].getPk_batchcode());
      // 行状态
      String frowstatus = vos[i].getFrowstatus();
      if (frowstatus != null) {
        int rowState = Integer.parseInt(frowstatus);
        switch (rowState) {
          case BillStatus.I_FREE:
            cusBVO.setFrowstatus(OPC_FREE);
            break;
          case BillStatus.I_INVALIDATE:
            cusBVO.setFrowstatus(OPC_INVALIDATE);
            break;
          case BillStatus.I_AUDITING:
            cusBVO.setFrowstatus(OPC_AUDITING);
            break;
          case BillStatus.I_NOPASS:
            cusBVO.setFrowstatus(OPC_NOPASS);
            break;
          case BillStatus.I_AUDIT:
            break;
          case BillStatus.I_CLOSED:
            break;
          case BillStatus.I_FREEZE:
            cusBVO.setFrowstatus(OPC_AUDIT);
            break;
        }
      }

      SaleOrderBVO saleorderBVO =
          preOrderBidDestBillValueMap.get(vos[i].getCpreorderbid());
      if (saleorderBVO == null) {
        cusBVOs[i] = cusBVO;
        continue;
      }

      for (String field : DESTBILLFEILDS) {
        cusBVO.setAttributeValue(field, saleorderBVO.getAttributeValue(field));
      }
      // 如果是安排关闭的并且下游单据出库关闭后，预订单才可以设置为出库关闭
      if (cusBVO.getBlineclose().booleanValue()
          && saleorderBVO.getBboutendflag().booleanValue()) {
        cusBVO.setBboutendflag(UFBoolean.TRUE);
      }
      else {
        cusBVO.setBboutendflag(UFBoolean.FALSE);
      }

      cusBVO.setFretexchange(saleorderBVO.getFretexchange());
      // cusBVO.setNtotalexenum(saleorderBVO.getNnum());

      cusBVOs[i] = cusBVO;
    }

    return cusBVOs;
  }

  /**
   * 获取下游单据相关的值：如果预订单安排了下游订单，将会把某一个预订单对应的所有下游单据的值全部综合起来，
   * 然后重新存储到SaleOrderBVO对象中，然后就将该预订单跟处理后的SaleOrderBVO通过map对应起来
   * 
   * @param saleOrderBVOMap
   * @return
   */
  private Map<String, SaleOrderBVO> getDestBillValues(
      Map<String, SaleOrderBVO> saleOrderBVOMap) {
    // 存储下游订单的一些值
    Map<String, SaleOrderBVO> preOrderBidDestBillValueMap =
        new HashMap<String, SaleOrderBVO>();
    for (String saleOrderBid : saleOrderBVOMap.keySet()) {
      SaleOrderBVO saleOrderBVO = saleOrderBVOMap.get(saleOrderBid);
      String cpreorderbid = saleOrderBVO.getCsrcbid();
      SaleOrderBVO destBillValuesVO =
          preOrderBidDestBillValueMap.get(cpreorderbid);
      if (destBillValuesVO == null) {
        destBillValuesVO = new SaleOrderBVO();
        for (String field : this.DESTBILLFEILDS) {
          destBillValuesVO.setAttributeValue(field,
              saleOrderBVO.getAttributeValue(field));
        }
        destBillValuesVO.setBboutendflag(saleOrderBVO.getBboutendflag());
      }
      else {

        // 设置出库关闭状态
        // 获取本条订单的出库关闭状态
        boolean f1 = convertBool(saleOrderBVO.getBboutendflag());
        // 获取已有的其它订单的出库关闭状态
        boolean f2 = convertBool(destBillValuesVO.getBboutendflag());
        destBillValuesVO.setBboutendflag(UFBoolean.valueOf(f1 && f2));

        // 设置金额
        for (String field : this.DESTBILLNUMFEILDS) {
          UFDouble newVal = (UFDouble) saleOrderBVO.getAttributeValue(field);
          UFDouble oldVal =
              (UFDouble) destBillValuesVO.getAttributeValue(field);
          destBillValuesVO.setAttributeValue(field,
              MathTool.add(newVal, oldVal));
        }
      }

      // 设置退换货标记
      if (saleOrderBVO.getNastnum().compareTo(UFDouble.ZERO_DBL) < 0) {
        destBillValuesVO.setFretexchange(SaleMode.MODE_COMMONRETURNCHANGE
            .getIntegerValue()); // 数量是负数，则为普通+退换货
      }
      else {
        destBillValuesVO
            .setFretexchange(SaleMode.MODE_COMMON.getIntegerValue()); // 数量为正数或空或0，则为普通
      }

      preOrderBidDestBillValueMap.put(cpreorderbid, destBillValuesVO);
    }
    return preOrderBidDestBillValueMap;
  }

  /**
   * 根据传入的BFBoolean值来返回对应的布尔值
   * 
   * @param UFboolVal UFBoolean值
   * @return
   */
  private boolean convertBool(UFBoolean boolVal) {
    if (boolVal == null)
      return false;
    return boolVal.booleanValue();
  }

  /**
   * @param ids 销售订单主表主键
   * @return <销售订单字表主键，销售订单BVO>
   */
  private Map<String, SaleOrderBVO> getSaleOrderBVO(String[] ids) {
    VOQuery<SaleOrderBVO> srv = new VOQuery<SaleOrderBVO>(SaleOrderBVO.class);
    IDQueryBuilder whereSql = new IDQueryBuilder();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(whereSql.buildSQL(SaleOrderBVO.CSRCID, ids));
    SaleOrderBVO[] saleOrderBVOs = srv.query(sql.toString(), null);

    Map<String, SaleOrderBVO> saleOrderVOMap =
        new HashMap<String, SaleOrderBVO>();
    for (SaleOrderBVO vo : saleOrderBVOs) {
      saleOrderVOMap.put(vo.getCsaleorderbid(), vo);
    }
    return saleOrderVOMap;
  }

  /**
   * 预订单迁移时，销售预订单到电子销售预订单对应的表头字段
   */
  private final String[] HEADMIGFIELDS = new String[] {
    PreOrderHVO.APPROVER, PreOrderHVO.BILLMAKER, PreOrderHVO.CCHANNELTYPEID,
    PreOrderHVO.CCUSTOMERID, PreOrderHVO.CDEPTID, PreOrderHVO.CDEPTVID,
    PreOrderHVO.CEMPLOYEEID, PreOrderHVO.CINVOICECUSTID,
    PreOrderHVO.CORIGCURRENCYID, PreOrderHVO.CPAYTERMID,
    PreOrderHVO.CREATIONTIME, PreOrderHVO.CREATOR,
    PreOrderHVO.CTRANSPORTTYPEID, PreOrderHVO.CTRANTYPEID,
    PreOrderHVO.DABATEDATE, PreOrderHVO.DBILLDATE, PreOrderHVO.DMAKEDATE,
    PreOrderHVO.IPRINTCOUNT, PreOrderHVO.MODIFIEDTIME, PreOrderHVO.MODIFIER,
    PreOrderHVO.NDISCOUNTRATE, PreOrderHVO.NHEADSUMMNY, PreOrderHVO.NTOTALNUM,
    PreOrderHVO.PK_GROUP, PreOrderHVO.PK_ORG, PreOrderHVO.PK_ORG_V,
    PreOrderHVO.TAUDITTIME, PreOrderHVO.TS, PreOrderHVO.VBILLCODE,
    PreOrderHVO.VDEF1, PreOrderHVO.VDEF10, PreOrderHVO.VDEF11,
    PreOrderHVO.VDEF12, PreOrderHVO.VDEF13, PreOrderHVO.VDEF14,
    PreOrderHVO.VDEF15, PreOrderHVO.VDEF16, PreOrderHVO.VDEF17,
    PreOrderHVO.VDEF18, PreOrderHVO.VDEF19, PreOrderHVO.VDEF2,
    PreOrderHVO.VDEF20, PreOrderHVO.VDEF3, PreOrderHVO.VDEF4,
    PreOrderHVO.VDEF5, PreOrderHVO.VDEF6, PreOrderHVO.VDEF7, PreOrderHVO.VDEF8,
    PreOrderHVO.VDEF9, PreOrderHVO.VNOTE, PreOrderHVO.VSRCTYPE,
    PreOrderHVO.VTRANTYPECODE, "pseudocolumn", "dr"
  };

  private final String[] BODYMIGFIELDS = new String[] {
    PreOrderBVO.BLARGESSFLAG, PreOrderBVO.BLINECLOSE,
    PreOrderBVO.BTRIATRADEFLAG, PreOrderBVO.CARORGID, PreOrderBVO.CARORGVID,
    PreOrderBVO.CARRANGEID, PreOrderBVO.CASTUNITID, PreOrderBVO.CCURRENCYID,
    PreOrderBVO.CCUSTMATERIALID, PreOrderBVO.CMATERIALID,
    PreOrderBVO.CMATERIALVID, PreOrderBVO.CORIGCURRENCYID,
    PreOrderBVO.CPRICEFORMID, PreOrderBVO.CPRICEITEMID,
    PreOrderBVO.CPRICEITEMTABLEID, PreOrderBVO.CPRICEPOLICYID,
    PreOrderBVO.CPRODUCTORID, PreOrderBVO.CPROFITCENTERID,
    PreOrderBVO.CPROFITCENTERVID, PreOrderBVO.CPROJECTID,
    PreOrderBVO.CQTUNITID, PreOrderBVO.CQUALITYLEVELID,
    PreOrderBVO.CRECECOUNTRYID, PreOrderBVO.CRECEIVEADDRID,
    PreOrderBVO.CRECEIVEAREAID, PreOrderBVO.CRECEIVECUSTID,
    PreOrderBVO.CRECEIVESITEID, PreOrderBVO.CROWNO, PreOrderBVO.CSENDCOUNTRYID,
    PreOrderBVO.CSENDSTOCKORGID, PreOrderBVO.CSENDSTOCKORGVID,
    PreOrderBVO.CSENDSTORDOCID, PreOrderBVO.CSETTLEORGID,
    PreOrderBVO.CSETTLEORGVID, PreOrderBVO.CTAXCODEID,
    PreOrderBVO.CTAXCOUNTRYID, PreOrderBVO.CTRAFFICORGID,
    PreOrderBVO.CTRAFFICORGVID, PreOrderBVO.CUNITID, PreOrderBVO.CVENDORID,
    PreOrderBVO.DARRDATE, PreOrderBVO.DBILLDATE, PreOrderBVO.DRECEIVEDATE,
    PreOrderBVO.DSENDDATE, PreOrderBVO.FBUYSELLFLAG, PreOrderBVO.FTAXTYPEFLAG,
    PreOrderBVO.NARRNUM, PreOrderBVO.NASKQTORIGNETPRICE,
    PreOrderBVO.NASKQTORIGPRICE, PreOrderBVO.NASKQTORIGTAXPRC,
    PreOrderBVO.NASKQTORIGTXNTPRC, PreOrderBVO.NASTNUM, PreOrderBVO.NCALTAXMNY,
    PreOrderBVO.NDISCOUNT, PreOrderBVO.NDISCOUNTRATE,
    PreOrderBVO.NEXCHANGERATE, PreOrderBVO.NGLOBALEXCHGRATE,
    PreOrderBVO.NGLOBALMNY, PreOrderBVO.NGLOBALTAXMNY,
    PreOrderBVO.NGROUPEXCHGRATE, PreOrderBVO.NGROUPMNY,
    PreOrderBVO.NGROUPTAXMNY, PreOrderBVO.NITEMDISCOUNTRATE, PreOrderBVO.NMNY,
    PreOrderBVO.NNETPRICE, PreOrderBVO.NNUM, PreOrderBVO.NORIGDISCOUNT,
    PreOrderBVO.NORIGMNY, PreOrderBVO.NORIGNETPRICE, PreOrderBVO.NORIGPRICE,
    PreOrderBVO.NORIGTAXMNY, PreOrderBVO.NORIGTAXNETPRICE,
    PreOrderBVO.NORIGTAXPRICE, PreOrderBVO.NPRICE, PreOrderBVO.NQTNETPRICE,
    PreOrderBVO.NQTORIGNETPRICE, PreOrderBVO.NQTORIGPRICE,
    PreOrderBVO.NQTORIGTAXNETPRC, PreOrderBVO.NQTORIGTAXPRICE,
    PreOrderBVO.NQTPRICE, PreOrderBVO.NQTTAXNETPRICE, PreOrderBVO.NQTTAXPRICE,
    PreOrderBVO.NQTUNITNUM, PreOrderBVO.NTAX, PreOrderBVO.NTAXMNY,
    PreOrderBVO.NTAXNETPRICE, PreOrderBVO.NTAXPRICE, PreOrderBVO.NTAXRATE,
    PreOrderBVO.PK_GROUP, PreOrderBVO.PK_ORG, PreOrderBVO.TS,
    PreOrderBVO.VBATCHCODE, PreOrderBVO.VBDEF1, PreOrderBVO.VBDEF10,
    PreOrderBVO.VBDEF11, PreOrderBVO.VBDEF12, PreOrderBVO.VBDEF13,
    PreOrderBVO.VBDEF14, PreOrderBVO.VBDEF15, PreOrderBVO.VBDEF16,
    PreOrderBVO.VBDEF17, PreOrderBVO.VBDEF18, PreOrderBVO.VBDEF19,
    PreOrderBVO.VBDEF2, PreOrderBVO.VBDEF20, PreOrderBVO.VBDEF3,
    PreOrderBVO.VBDEF4, PreOrderBVO.VBDEF5, PreOrderBVO.VBDEF6,
    PreOrderBVO.VBDEF7, PreOrderBVO.VBDEF8, PreOrderBVO.VBDEF9,
    PreOrderBVO.VCHANGERATE, PreOrderBVO.VFREE1, PreOrderBVO.VFREE10,
    PreOrderBVO.VFREE2, PreOrderBVO.VFREE3, PreOrderBVO.VFREE4,
    PreOrderBVO.VFREE5, PreOrderBVO.VFREE6, PreOrderBVO.VFREE7,
    PreOrderBVO.VFREE8, PreOrderBVO.VFREE9, PreOrderBVO.VQTUNITRATE,
    PreOrderBVO.VROWNOTE, "pseudocolumn", "dr"
  };

  /**
   * 预订单安排销售订单后，销售订单表体所涉及到的数字字段
   * 即，预订单表体与订单表体为一对多关系，对以下字段求和之后等于预订单中对应的字段值
   */
  private final String[] DESTBILLNUMFEILDS = new String[] {
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALARNUM,
    SaleOrderBVO.NTOTALINVOICENUM, SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALPAYMNY, SaleOrderBVO.NTOTALRETURNNUM,
    SaleOrderBVO.NTOTALSIGNNUM, SaleOrderBVO.NNUM
  };

  /**
   * 预订单安排销售订单后，销售订单表体所涉及到的所有字段
   */
  private final String[] DESTBILLFEILDS = new String[] {
    SaleOrderBVO.NTOTALARMNY, SaleOrderBVO.NTOTALARNUM,
    SaleOrderBVO.NTOTALINVOICENUM, SaleOrderBVO.NTOTALOUTNUM,
    SaleOrderBVO.NTOTALPAYMNY, SaleOrderBVO.NTOTALRETURNNUM,
    SaleOrderBVO.NTOTALSIGNNUM,
  };

}
