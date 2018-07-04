package nc.ui.so.m30.revise.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ArrayListProcessor;
import nc.ui.ml.NCLangRes;
import nc.ui.pubapp.uif2app.actions.EditAction;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.AppEvent;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.util.FeatureSelectUtil;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.util.StringUtil;

/**
 * 销售订单修订节点修订按钮
 * 
 * @version 6.0
 * @author 刘志伟
 * @time 2010-8-9 下午08:27:54
 */
public class M30ReviseEditAction extends EditAction {

	/**
   * 
   */
	private static final long serialVersionUID = 2499699416308097994L;

	private BillForm editor;

	private AbstractAppModel model;

	public M30ReviseEditAction() {
		this.setBtnName(NCLangRes.getInstance().getStrByID("4006011_0",
				"04006011-0297")/* 修订 */);
		this.setCode("revise");
		this.putValue(Action.SHORT_DESCRIPTION, NCLangRes.getInstance()
				.getStrByID("4006011_0", "04006011-0297")/* 修订 */);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		this.model.setUiState(UIState.EDIT);
		CardKeyValue keyValue = new CardKeyValue(this.editor.getBillCardPanel());
		FeatureSelectUtil.clearAllRowValue(keyValue, SOConstant.AGGFFILEVO);
		// 王梓懿新增加对于保存与保存提交的控制 2018-05-30
		this.getEditor().getBillCardPanel().getHeadItem("agdef1")
				.setValue("修订");
		this.getEditor().getBillCardPanel().getHeadItem("agdef1")
				.setEdit(false);
		this.model.fireEvent(new AppEvent("刷一下大家的按钮状态"));
	}

	public BillForm getEditor() {
		return this.editor;
	}

	@Override
	public AbstractAppModel getModel() {
		return this.model;
	}

	@Override
	protected boolean isActionEnable() {
		boolean iseidtable = (this.model.getUiState() == UIState.NOT_EDIT)
				&& (this.model.getSelectedData() != null);
		if (iseidtable) {
			SaleOrderVO vo = (SaleOrderVO) this.getModel().getSelectedData();
			// 审批态的不能修改
			// modify by 王梓懿 销售订单修订支持审批流 当修订未提交时 单据状态为自由、审批中，不能被修订 2018-05-30
			// 或者不是最新的版本不能被修订
			Integer fstatusflag = vo.getParentVO().getFstatusflag();
			Integer iVersion = vo.getParentVO().getIversion();
			boolean lastestVersion = isLastestVersion(vo.getParentVO()
					.getCsaleorderid(), iVersion);
			if (!nc.vo.so.pub.enumeration.BillStatus.AUDIT
					.equalsValue(fstatusflag) || !lastestVersion) {
				return false;
			}
			// add by wangshu6 已经做过费用冲抵或赠品兑付的销售订单，禁止修订。2015-01-05
			if (!StringUtil
					.isEmptyTrimSpace(vo.getParentVO().getCarsubtypeid())) {
				iseidtable = false;
			} else if (!MathTool.isZero(vo.getParentVO().getNtotalorigsubmny())) {
				iseidtable = false;
			}
			// end
		}
		return iseidtable;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	@Override
	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	/**
	 * @Title:
	 * @Description: 数据库查询一下是否为最新版本 只能为了需求牺牲一下效率
	 * @return: boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean isLastestVersion(String primaryKey, Integer iVersion) {
		// TODO 自动生成的方法存根

		String sql = "select max(iversion) from so_orderhistory where csaleorderid ='"
				+ primaryKey + "' and dr =0";
		IUAPQueryBS service = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		ArrayList<Object[]> rs = new ArrayList<Object[]>();
		try {
			rs = (ArrayList<Object[]>) service.executeQuery(sql,
					new ArrayListProcessor());
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		if (rs == null || rs.get(0) == null) {
			return false;
		}
		int version = rs.get(0)[0] == null ? 0 : (Integer) rs.get(0)[0];
		// 没修订过可以修订 fix bug 2018-06-05
		if (Integer.valueOf(version) != iVersion) {
			return false;
		}
		return true;
	}

}
