package nc.ui.so.m32.billui.action.ast;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.scmpub.goldtax.TransGoldTaxDlg;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.res.NCModule;
import nc.vo.scmpub.goldtax.GoldTaxBodyVO;
import nc.vo.scmpub.goldtax.GoldTaxHeadVO;
import nc.vo.scmpub.goldtax.GoldTaxVO;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

/**
 * 发票传金税
 * 
 * @since 6.0
 * @version 2011-11-16 下午04:18:53
 * @author 么贵敬
 */
public class ExportGoldTaxAction extends NCAction {

  /**
     *
     */
  private static final long serialVersionUID = 5739522957642379189L;

  private AbstractAppModel model;
  
  //== lijj 添加备件、内贸钢材、代理费 三个特殊业务处理==
  //== 默认值为0为标准传金税、 1为备件、 2为内贸钢材、3为代理费 ==
  private String busiType = "0";
  
  public String getBusiType() {
	return busiType;
  }

  public void setBusiType(String busiType) {
	this.busiType = busiType;
	
    //根据业务更改按钮名称
	switch (busiType) {
		case "1":
			setBtnName("传备件金税");
			break;
		case "2":
			setBtnName("传内贸钢材金税");
			break;
		case "3":
			setBtnName("传代理费金税");
			break;
		default:
			break;
	}
    
  }
  //== lijj 添加备件、内贸钢材、代理费 三个特殊业务处理  默认值为0 为标准传金税==
  

  public ExportGoldTaxAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 选中发票VOs
    BillManageModel billmodel = (BillManageModel) this.getModel();
    Object[] selectDatas = billmodel.getSelectedOperaDatas();

    int ilength = selectDatas.length;
    SaleInvoiceVO[] voInvoices = new SaleInvoiceVO[ilength];
    for (int i = 0; i < ilength; i++) {
      voInvoices[i] = (SaleInvoiceVO) selectDatas[i];
    }

    // 转换成金税VOs
    GoldTaxVO[] gtvos = this.chgSaleInvoiceToGoldtax(voInvoices);
    
    //=== lijj 鞍钢特殊需求特殊业务特别处理 ===
    resetGoldTaxVO(gtvos);
    //=== lijj 鞍钢特殊需求特殊业务特别处理 ===
    
    // 导出金税对话框
    TransGoldTaxDlg goldtaxdlg =
        new TransGoldTaxDlg(WorkbenchEnvironment.getInstance().getWorkbench());
    goldtaxdlg.setGoldTaxVOs(gtvos);
    
    goldtaxdlg.setPkOrg(voInvoices[0].getParentVO().getPk_org());
    
    //==== lijj 把业务类型传到dlg,有合并的特殊业务需要处理====
    goldtaxdlg.setBusiType(busiType);
    //==== lijj 把业务类型传到dlg,有合并的特殊业务需要处理====

    if (UIDialog.ID_OK == goldtaxdlg.showModal()) {
      SaleInvoiceHVO[] voHeads = this.getUpdateHeadVOs(voInvoices);
      ISaleInvoiceMaintain updatesrv =
          NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);

      SaleInvoiceHVO[] retHeads = updatesrv.updateWhenExportGoldTax(voHeads);
      this.updateClientVOs(voInvoices, retHeads);

      for (SaleInvoiceVO newvo : voInvoices) {
        billmodel.directlyUpdate(newvo);
      }
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006008_0",
              "04006008-0002")/*@res "金税导出成功"*/, billmodel.getContext());
    }
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  @Override
  protected boolean isActionEnable() {
    SaleInvoiceVO selectVO = (SaleInvoiceVO) this.model.getSelectedData();

    boolean isEnable =
        this.model.getUiState() == UIState.NOT_EDIT && selectVO != null;

    return isEnable;
  }

  private GoldTaxVO[] chgSaleInvoiceToGoldtax(SaleInvoiceVO[] voInvoices) {
    // 非空校验
    if (null == voInvoices || voInvoices.length == 0) {
      return new GoldTaxVO[0];
    }
    this.validateCheck(voInvoices);

    List<String> bidlist = new ArrayList<String>();
    for (SaleInvoiceVO vo : voInvoices) {
      bidlist.add(vo.getParentVO().getCsaleinvoiceid());
    }

    ISaleInvoiceMaintain srv =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);

    GoldTaxVO[] goldtaxvos = null;
    try {
      goldtaxvos =
          srv.executeVOChangeTogtax(bidlist.toArray(new String[bidlist.size()]));
      for (GoldTaxVO goldtaxvo : goldtaxvos) {
        // 表单标识
        goldtaxvo.setBillIdentifier("SJJK0101");
        // 表单名称
        goldtaxvo.setBillName(NCLangRes.getInstance().getStrByID("4006008_0",
            "04006008-0077")/*销售发票传入*/);
        // 来源模块
        goldtaxvo.setSourceModule(NCModule.SO.getName());
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    // GoldTaxVO[] goldtaxvos =
    // PfServiceScmUtil.executeVOChange(SOBillType.Invoice.getCode(), "gtax",
    // voInvoices);

    return goldtaxvos;
  }

  private SaleInvoiceHVO[] getUpdateHeadVOs(SaleInvoiceVO[] voInvoices) {
    int ilength = voInvoices.length;
    UFDateTime curtime = AppContext.getInstance().getServerTime();
    SaleInvoiceHVO[] voHeads = new SaleInvoiceHVO[ilength];
    for (int i = 0; i < ilength; i++) {
      voHeads[i] = new SaleInvoiceHVO();
      voHeads[i].setCsaleinvoiceid(voInvoices[i].getParentVO()
          .getCsaleinvoiceid());
      // 是否传金税标识
      voHeads[i].setBtogoldtaxflag(UFBoolean.TRUE);
      // 最后传金税时间
      voHeads[i].setTgoldtaxtime(curtime);
    }
    return voHeads;
  }

  private void initializeAction() {
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.SCM_EXPORTGOLDTAX);
  }

  private void updateClientVOs(SaleInvoiceVO[] voInvoices,
      SaleInvoiceHVO[] retHeads) {

    int ilength = voInvoices.length;
    for (int i = 0; i < ilength; i++) {
      SaleInvoiceHVO orighead = voInvoices[i].getParentVO();
      orighead.setBtogoldtaxflag(retHeads[i].getBtogoldtaxflag());
      orighead.setTgoldtaxtime(retHeads[i].getTgoldtaxtime());
      orighead.setTs(retHeads[i].getTs());
    }
  }

  private void validateCheck(SaleInvoiceVO[] voInvoices) {
    // 开票组织校验
    SaleInvoiceHVO voHead = voInvoices[0].getParentVO();
    String hid = voHead.getCsaleinvoiceid();
    String pk_org = voHead.getPk_org();
    List<String> valiBillcode = new ArrayList<String>();
    for (SaleInvoiceVO invoice : voInvoices) {
      SaleInvoiceHVO hvo = invoice.getParentVO();
      if (!pk_org.equals(hvo.getPk_org())
          && !hid.equals(hvo.getCsaleinvoiceid())) {
        valiBillcode.add(invoice.getParentVO().getVbillcode());
      }
    }
    if (valiBillcode.size() > 0) {
      StringBuilder msg = new StringBuilder();
      StringBuilder msgCode = new StringBuilder();
      for (String code : valiBillcode) {
        msgCode.append("[" + code + "]");
      }
      msg.append(NCLangRes.getInstance().getStrByID("4006008_0",
          "04006008-0114", null, new String[] {
            msgCode.toString()
          })/*下列单据：{0}与选中第一张单据开票组织不同，不能同时传金税*/);
      ExceptionUtils.wrappBusinessException(msg.toString());
    }

  }
  
  /**
   * 
   * TODO 根据鞍钢特殊要求或特殊业务对金税VO个别值重新加工处理
   */
  private void resetGoldTaxVO(GoldTaxVO[] gtvos){
	  if(gtvos == null || gtvos.length == 0){
		return;
	  }
	  
	  //1- lijj 无论哪种业务（含标准处理）统一将 	“购货方银行账号”：改为客户开户银行名称+客户银行账号===
	  for(GoldTaxVO taxVO : gtvos){
		GoldTaxHeadVO headvo = taxVO.getParentVO();
		
		//开户银行名称
		String bankName = headvo.getBankname();
		if(bankName == null)
			bankName = "";
		
		//购货方银行账号 
		String account = headvo.getAccount();
		if(account == null)
			account = "";
		
		//购货方银行账号 重新赋值
		headvo.setAccount(bankName + account);
	  }
	    
	  //2- lijj 根据业务标记 分别进行特殊处理 ====
	  switch (busiType) {
		case "1":
			//===备件特殊业务处理 =====
		    /*
		     * 将物料名称增加到规格型号中，将物料名称位置进行赋值“备件”
		     */
	    	for(GoldTaxVO taxVO : gtvos){
	    		GoldTaxBodyVO[] bodyvos = taxVO.getChildrenVO();
	    		if(bodyvos != null && bodyvos.length > 0){
	    			for(GoldTaxBodyVO bodyvo : bodyvos){
	    				String invname = bodyvo.getInvname();
	    				String invspec = bodyvo.getInvspec();
	    				
	    				if(invspec == null){
	    					invspec = "";
	    				}
	    				bodyvo.setInvname("备件");
	    				bodyvo.setInvspec(invspec + invname);
	    			}
	    		}
	    	}
	    	
			break;
		case "2":
			//=== 内贸钢材特殊业务处理  ====
			/*
		     * “物料名称”：改为物料基本分类名称+物料名称
			 * “规则型号”：改为自定义项15
		     */
			for(GoldTaxVO taxVO : gtvos){
				GoldTaxBodyVO[] bodyvos = taxVO.getChildrenVO();
	    		if(bodyvos != null && bodyvos.length > 0){
	    			for(GoldTaxBodyVO bodyvo : bodyvos){
	    				//物料名称
	    				String invname = bodyvo.getInvname();
	    				
	    				//分类名称
	    				String invclassname = bodyvo.getInvclassname();
	    				if(invclassname == null){
	    					invclassname = "";
	    				}
	    				
	    				String def15 = bodyvo.getVdef15();
	    				if(def15 == null){
	    					def15 = "";
	    				}
	    				
	    				bodyvo.setInvname(invclassname + invname);
	    				bodyvo.setInvspec(def15);
	    			}
	    		}
			}
			
			break;
		case "3":
			//=== 内贸钢材特殊业务处理  ====
			/*
		     * “物料名称”为固定值“代理费”
		     */
			for(GoldTaxVO taxVO : gtvos){
	    		GoldTaxBodyVO[] bodyvos = taxVO.getChildrenVO();
	    		if(bodyvos != null && bodyvos.length > 0){
	    			for(GoldTaxBodyVO bodyvo : bodyvos){
	    				bodyvo.setInvname("代理费");
	    				
	    				String def15 = bodyvo.getVdef15();
	    				if(def15 == null){
	    					def15 = "";
	    				}
	    				bodyvo.setInvspec(def15);
	    				
	    			}
	    		}
	    	}
			break;
		default:
			//do nothing
			break;
	}
	    
  }

}
