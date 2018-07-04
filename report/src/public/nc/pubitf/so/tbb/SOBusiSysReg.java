package nc.pubitf.so.tbb;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.core.util.ObjectCreator;
import nc.itf.tb.control.IBusiSysExecDataProvider;
import nc.itf.tb.control.IBusiSysReg;
import nc.itf.tb.control.IDateType;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.tb.control.ControlBillType;
import nc.vo.tb.control.ControlObjectType;
import nc.vo.tb.control.DocTypeEnum;

/**
 * 预算取数接口类
 * 
 * @since 6.0
 * @version 2011-3-10 下午04:42:48
 * @author 祝会征
 */
public class SOBusiSysReg implements IBusiSysReg, IDateType {
  // 日期类型
  private static final String[] DATETYPE = new String[] {
    SOTbbFieldConst.DBILLDATE, SOTbbFieldConst.TAUDITTIME
  };

  // 日期类型描述
  private static final String[] DATETYPEDESC =
      new String[] {
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0",
            "04006005-0010")/*单据日期*/,
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0",
            "04006005-0011")
      /*审核日期*/
      };

  private final String[] billCode = new String[] {
    SOBillType.Order.getCode(), SOBillType.Invoice.getCode()
  };

  private final String[] billName = new String[] {
    SOBillType.Order.getName(), SOBillType.Invoice.getName()
  };

  // ntb_id_bdcontrast表中销售组织档案
  private final String BD_SALEORG = "SO00Z800000000000001";

  // ntb_id_bdcontrast表中财务组织档案
  private final String BD_FINAORG = "SO00Z800000000000002";

  // ntb_id_bdcontrast表中库存组织档案
  private final String BD_INVORG = "SO00Z800000000000003";

  @Override
  public ArrayList<ControlBillType> getBillType() {
    List<ControlBillType> ret = new ArrayList<ControlBillType>();
    for (int i = 0; i < this.billCode.length; i++) {
      String code = this.billCode[i];
      String name = this.billName[i];
      ControlBillType control = new ControlBillType();
      control.setPk_billType(code);
      control.setBillType_code(code);
      control.setBillType_name(name);
      control.setDoctype(DocTypeEnum.Bill_type.toCodeString());
      List<String> pk_orgs = new ArrayList<String>();
      if (SOBillType.Order.getCode().equals(code)) {
        // 添加主组织: 销售组织
        pk_orgs.add(BD_SALEORG);
        // 结算财务组织
        pk_orgs.add(BD_FINAORG);
        // 库存组织
        pk_orgs.add(BD_INVORG);
      }
      else {
        // 添加主组织: 销售结算组织
        pk_orgs.add(BD_FINAORG);
        // 销售组织
        pk_orgs.add(BD_SALEORG);
        // 库存组织
        pk_orgs.add(BD_INVORG);
      }
      control.setPk_orgs((ArrayList<String>) pk_orgs);
      ret.add(control);
    }
    return (ArrayList<ControlBillType>) ret;
  }

  @Override
  public String getBusiSysDesc() {
    return NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0",
        "04006005-0012")/*NC销售管理*/;
  }

  @Override
  public String getBusiSysID() {
    return "SO";
  }

  @Override
  public String[] getBusiType() {
    return null;
  }

  @Override
  public String[] getBusiTypeDesc() {
    return null;
  }

  @Override
  public String[] getControlableDirections() {
    return null;
  }

  @Override
  public String[] getControlableDirectionsDesc() {
    return null;
  }

  @Override
  public ArrayList<ControlObjectType> getControlableObjects() {
    ControlObjectType[] subTypes = new ControlObjectType[3];
    ControlObjectType subType =
        new ControlObjectType(SOTbbFieldConst.NNUM, SOTbbFieldConst.NNUM,
            NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0",
                "04006005-0013")/*数量*/);
    subTypes[0] = subType;
    ControlObjectType subType1 =
        new ControlObjectType(SOTbbFieldConst.NORIGMNY,
            SOTbbFieldConst.NORIGMNY, NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006005_0", "04006005-0014")/*无税金额*/);
    subTypes[1] = subType1;
    ControlObjectType subType2 =
        new ControlObjectType(SOTbbFieldConst.NORIGTAXMNY,
            SOTbbFieldConst.NORIGTAXMNY, NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006005_0", "04006005-0015")/*价税合计*/);
    subTypes[2] = subType2;
    List<ControlObjectType> l = new ArrayList<ControlObjectType>();
    ControlObjectType oType =
        new ControlObjectType(SOTbbFieldConst.OPERATE, SOTbbFieldConst.OPERATE,
            NCLangRes4VoTransl.getNCLangRes().getStrByID("4006005_0",
                "04006005-0016")/*发生*/);
    oType.setCotMember(subTypes);
    l.add(oType);
    return (ArrayList<ControlObjectType>) l;
  }

  @Override
  public ArrayList<ControlObjectType> getControlableObjects(String billtype) {
    return null;
  }

  @Override
  public String[] getDataType() {
    return SOBusiSysReg.DATETYPE;
  }

  @Override
  public String[] getDataTypeDesc() {
    return SOBusiSysReg.DATETYPEDESC;
  }

  @Override
  public String[] getDateType(String billtype) {
    return SOBusiSysReg.DATETYPE;
  }

  @Override
  public IBusiSysExecDataProvider getExecDataProvider() {
    return (IBusiSysExecDataProvider) ObjectCreator.newInstance("so",
        "nc.pubimpl.tbb.SOToTbbProvider");
  }

  @Override
  public String getMainPkOrg() {
    return null;
  }

  @Override
  public boolean isBudgetControling() {
    return false;
  }

  @Override
  public boolean isSupportMutiSelect() {
    return false;
  }

  @Override
  public boolean isUseAccountDate(String billtype) {
    return false;
  }

}
