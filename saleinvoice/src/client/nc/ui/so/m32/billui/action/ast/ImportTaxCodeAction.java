package nc.ui.so.m32.billui.action.ast;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.goldtax.GoldTaxHeadVO;
import nc.vo.scmpub.goldtax.GoldTaxVO;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.trade.checkrule.VOChecker;
import nc.itf.so.m32.ISaleInvoiceMaintain;
import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.md.data.access.NCObject;
import nc.pubitf.so.saleinvoice.api.ISaleinvoiceReceive;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.scmpub.goldtax.TransGoldTaxDlg;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>销售发票导入金税票号功能实现
 * </ul>
 * <p>
 * 
 * @version 本版本号 6.0
 * @since
 * @author fengjb
 * @time 2010-8-24 下午08:09:42
 */
public class ImportTaxCodeAction extends NCAction {

  /**
     * 
     */
  private static final long serialVersionUID = 5934911193080254065L;

  private AbstractAppModel model;

  public ImportTaxCodeAction() {
    super();
    this.initializeAction();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
	
    TransGoldTaxDlg goldtaxdlg =
        new TransGoldTaxDlg(WorkbenchEnvironment.getInstance().getWorkbench());
    GoldTaxVO[] goldTaxVOs = goldtaxdlg.importGoldTax();

    if (VOChecker.isEmpty(goldTaxVOs)) {
      return;
    }
    Map<String, String> mapTaxcode = new HashMap<String, String>();
    for (GoldTaxVO gtvo : goldTaxVOs) {
      GoldTaxHeadVO gthead = gtvo.getParentVO();
      String billcode = gthead.getCode();
      if (mapTaxcode.containsKey(billcode)) {
        String parttaxcode = mapTaxcode.get(billcode);
        String newtaxcode = gthead.getTaxbillno().trim();
        if (PubAppTool.isNull(newtaxcode)) {
          continue;
        }
        parttaxcode = parttaxcode + "," + newtaxcode;
        mapTaxcode.put(billcode, parttaxcode);
      }
      else {
        String newtaxcode = gthead.getTaxbillno().trim();
        if (PubAppTool.isNull(newtaxcode)) {
          continue;
        }
        mapTaxcode.put(billcode, newtaxcode);
      }
    }
    if (mapTaxcode.size() == 0) {
      return;
    }
    List<SaleInvoiceHVO> alvoiceheads = new ArrayList<SaleInvoiceHVO>();

    for (Entry<String, String> entry : mapTaxcode.entrySet()) {
      SaleInvoiceHVO voicehead = new SaleInvoiceHVO();
      voicehead.setVbillcode(entry.getKey());
      voicehead.setVgoldtaxcode(entry.getValue());
      alvoiceheads.add(voicehead);
    }
    ISaleInvoiceMaintain maintainsrv =
        NCLocator.getInstance().lookup(ISaleInvoiceMaintain.class);
    SaleInvoiceHVO[] retHeads =
        maintainsrv.updateGoldTaxCode(alvoiceheads
            .toArray(new SaleInvoiceHVO[0]));
    NCLangRes nclangres = NCLangRes.getInstance();
    if (retHeads.length == 0) {
      ShowStatusBarMsgUtil.showStatusBarMsg(
          nclangres.getStrByID("4006008_0", "04006008-0142")/*@res "全部导入失败"*/,
          this.getModel().getContext());
      return;
    }
    else if (mapTaxcode.size() > retHeads.length) {
      this.updateModelData(retHeads);
      Set<String> sucesscode = new HashSet<String>();
      for (SaleInvoiceHVO hvo : retHeads) {
        sucesscode.add(hvo.getVbillcode());
      }
      StringBuffer errMsg = new StringBuffer();
      for (String field : mapTaxcode.keySet()) {
        errMsg
            .append("[")
            .append(field)
            .append("]")
            .append(
                NCLangRes.getInstance()
                    .getStrByID("4006008_0", "04006008-0148")/*、*/);
      }
      errMsg.deleteCharAt(errMsg.length() - 1);
      errMsg.append("\n");
      ShowStatusBarMsgUtil.showStatusBarMsg(nclangres.getStrByID("4006011_0",
          "04006011-0143", null, new String[] {
            errMsg.toString()
          })/*@res "以下单据导入失败:\n{0}"*/, this.getModel().getContext());
      return;
    }
    // 更新缓存
    this.updateModelData(retHeads);

    ShowStatusBarMsgUtil.showStatusBarMsg(
        nclangres.getStrByID("4006008_0", "04006008-0128")/*@res "导入成功"*/,
        this.getModel().getContext());
//    //向接口发送数据
//    ISaleinvoiceReceive is = NCLocator.getInstance().lookup(
//    		ISaleinvoiceReceive.class);
//    Object obj =  getModel().getSelectedData();//获取单选
//    SaleInvoiceVO saleInvoiceVO = new SaleInvoiceVO();
//    saleInvoiceVO = (SaleInvoiceVO)NCObject.newInstance(obj).getContainmentObject();
//    //向接口发送数据
//    boolean b = is.sendMsgAGCG000003(saleInvoiceVO);
//    
//    // 接口数据发送成功后写入中间表
//    if (b) {
//		is.sendZjbInsert(saleInvoiceVO);
//	}else {
//		try {
//			nc.vo.pubapp.pattern.exception.ExceptionUtils.marsh(null);
//		} catch (Exception ex) {
//			// TODO 自动生成的 catch 块
//			ExceptionUtils.wrappBusinessException("接口数据发送不成功");
//		}
//	}
    
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private void initializeAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.SO_IMPORTTAXCODE);
  }

  private void updateModelData(SaleInvoiceHVO[] retHeads) {
    Map<String, SaleInvoiceHVO> mapUpdate =
        new HashMap<String, SaleInvoiceHVO>();
    for (SaleInvoiceHVO head : retHeads) {
      mapUpdate.put(head.getVbillcode(), head);
    }
    BillManageModel billmodel = (BillManageModel) this.getModel();
    for (Object objdata : billmodel.getData()) {
      SaleInvoiceVO voInvoice = (SaleInvoiceVO) objdata;
      String billcode = voInvoice.getParentVO().getVbillcode();
      if (mapUpdate.containsKey(billcode)) {
        SaleInvoiceHVO newhead = mapUpdate.get(billcode);
        voInvoice.getParentVO().setVgoldtaxcode(newhead.getVgoldtaxcode());
        voInvoice.getParentVO().setTs(newhead.getTs());
        billmodel.directlyUpdate(voInvoice);
      }
    }
  }

}
