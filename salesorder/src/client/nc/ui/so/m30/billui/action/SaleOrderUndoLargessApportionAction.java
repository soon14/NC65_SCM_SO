package nc.ui.so.m30.billui.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.enumeration.Fretexchange;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SaleOrderClientContext;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.pub.keyvalue.IKeyValue;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.so.m30.billui.rule.LargessPropertyRule;
import nc.ui.so.m30.billui.rule.MatchBindLargessRule;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.so.m30.pub.CardEditSetter;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.NCAction;

public class SaleOrderUndoLargessApportionAction extends NCAction {

	private static final long serialVersionUID = 5771647178049827763L;

	private BillForm editor;

	private BillManageModel model;

	public SaleOrderUndoLargessApportionAction() {
		super();
		SCMActionInitializer.initializeAction(this,
				SCMActionCode.SO_UNDOLARGESSAPPORTION);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {

		BillCardPanel cardPanel = this.getCardPanel();
		LargessPropertyRule larapprule = new LargessPropertyRule(cardPanel);
		larapprule.undoLargessApportion();

		this.doafter();
	}

	public BillForm getEditor() {
		return this.editor;
	}

	public BillManageModel getModel() {
		return this.model;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		AppUiState uistate = this.getModel().getAppUiState();

		if (AppUiState.EDIT == uistate || AppUiState.ADD == uistate
				|| AppUiState.COPY_ADD == uistate
				|| AppUiState.TRANSFERBILL_ADD == uistate) {
			BillCardPanel cardPanel = this.getCardPanel();
			Object offsetfalg = cardPanel.getHeadItem(SaleOrderHVO.BOFFSETFLAG)
					.getValueObject();
			boolean boffsetfalg = ValueUtils.getBoolean(offsetfalg);
			if (boffsetfalg) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	private BillCardPanel getCardPanel() {
		return this.getEditor().getBillCardPanel();
	}

	private void doafter() {
		CardEditSetter editset = new CardEditSetter(
				(SaleOrderBillForm) this.editor);
		editset.setOriginalEditByFlargessTypeFlag();
		BillCardPanel cardPanel = this.getCardPanel();
		IKeyValue keyValue = new CardKeyValue(cardPanel);
		String tranTypeCode = keyValue
				.getHeadStringValue(SaleOrderHVO.VTRANTYPECODE);
		String pk_group = AppContext.getInstance().getPkGroup();
		SaleOrderClientContext cache = ((SaleOrderBillForm) this.editor)
				.getM30ClientContext();
		M30TranTypeVO m30transvo = cache.getTransType(tranTypeCode, pk_group);
		// 匹配买赠、捆绑(2013.11.25)赠品兑付的销售订单，不匹配买赠设置)
		boolean isblrgcash = m30transvo.getBlrgcashflag().booleanValue();
		if (!isblrgcash) {
			List<Integer> matchrows = getNeedApprotRows(keyValue);
			MatchBindLargessRule matchrule = new MatchBindLargessRule(
					this.editor.getBillCardPanel(), m30transvo);
			if (matchrows.size() == 0) {
				return;
			}
			matchrule.matchBindLargess(this
					.changeIntegerListToIntArray(matchrows));
			// --13.计算合计
			HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(
					keyValue);
			totalrule.calculateHeadTotal();
		}
	}

	private List<Integer> getNeedApprotRows(IKeyValue keyValue) {
		List<Integer> listneedrow = new ArrayList<Integer>();
		int bodycount = keyValue.getBodyCount();
		for (int i = 0; i < bodycount; i++) {
			// 物料为空行不分摊
			String cmarterialvid = keyValue.getBodyStringValue(i,
					SaleOrderBVO.CMATERIALVID);
			if (PubAppTool.isNull(cmarterialvid)) {
				continue;
			}
			// 数量为空或者红字不分摊
			UFDouble nnum = keyValue.getBodyUFDoubleValue(i, SaleOrderBVO.NNUM);
			if (null == nnum || nnum.compareTo(UFDouble.ZERO_DBL) <= 0) {
				continue;
			}
			// 退换货行不分摊
			Integer retexchange = keyValue.getBodyIntegerValue(i,
					SaleOrderBVO.FRETEXCHANGE);
			if (Fretexchange.EXCHANGE.equalsValue(retexchange)
					|| Fretexchange.WITHDRAW.equalsValue(retexchange)) {
				continue;
			}
			listneedrow.add(Integer.valueOf(i));
		}
		return listneedrow;
	}

	/**
	 * 将Integer的list转成int数组
	 * 
	 * @param integerList
	 * @return
	 */
	private int[] changeIntegerListToIntArray(List<Integer> integerList) {
		int[] rows = new int[integerList.size()];
		int i = 0;
		for (Integer row : integerList) {
			rows[i] = row.intValue();
			i++;
		}
		return rows;
	}

}
