/**
 * 
 */
package nc.ui.so.m30.billui.action.line;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.ui.so.m30.billui.editor.headevent.JhqHeadAndBodyEditHandler;
import nc.ui.so.m30.billui.rule.RelateRowDeleteRule;
import nc.ui.so.pub.keyvalue.CardKeyValue;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.m30.util.SpecialBusiUtil;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.util.SOMathUtil;

/**
 * 销售订单删行动作
 * 
 * @since 6.0
 * @version 2011-6-15 下午06:06:34
 * @author fengjb
 */
public class SaleOrderDelLineAction extends BodyDelLineAction {

	/**
	 * 累计数量字段名称，用来片段订单是否存在下游(全部都是UFDouble类型字段)
	 */
	private static String[] nextbillfields = new String[] {
			SaleOrderBVO.NTOTALSENDNUM, SaleOrderBVO.NTOTALINVOICENUM,
			SaleOrderBVO.NARRANGEPOAPPNUM, SaleOrderBVO.NARRANGEMONUM,
			SaleOrderBVO.NARRANGEPONUM, SaleOrderBVO.NARRANGESCORNUM,
			SaleOrderBVO.NARRANGETOAPPNUM, SaleOrderBVO.NARRANGETOORNUM,
			SaleOrderBVO.NARRANGEITCNUM, SaleOrderBVO.NTOTALOUTNUM,
			SaleOrderBVO.NTOTALNOTOUTNUM };

	private static final long serialVersionUID = -1675285691455875757L;

	@Override
	public void doAction() {
		super.doAction();

		// 计算表头价税合计
		IKeyValue keyValue = new CardKeyValue(this.getCardPanel());
		HeadTotalCalculateRule headtotalrule = new HeadTotalCalculateRule(
				keyValue);
		headtotalrule.calculateHeadTotal();
		/**
		 * @author wzy modify by wangzym 2017-03-04
		 */
		// 重新计算表头的最迟交货期
		JhqHeadAndBodyEditHandler handler = new JhqHeadAndBodyEditHandler();
		handler.calculateForDelLine(keyValue);

	}

	@Override
	public boolean doBeforeAction(ActionEvent e) {

		boolean isdo = super.doBeforeAction(e);
		if (!isdo) {
			return false;
		}
		BillCardPanel cardPanel = this.getCardPanel();
		IKeyValue keyValue = new CardKeyValue(cardPanel);
		int[] srcrows = cardPanel.getBodyPanel().getTable().getSelectedRows();
		// 判断删除行是否是赠品行，是赠品行要考虑一匹配行的促销类型ID是否需要删除
		this.delProID(srcrows, keyValue);
		// 修订时检查订单行是否可删除
		boolean checkpass = this.checkCanDelRows(srcrows, keyValue);
		if (!checkpass) {
			return false;
		}

		RelateRowDeleteRule delrowrule = new RelateRowDeleteRule(keyValue);
		int[] reltodelrows = delrowrule.getRelaDeleteRows(srcrows);

		for (int deleterow : reltodelrows) {
			cardPanel.getBodyPanel().getTable().getSelectionModel()
					.addSelectionInterval(deleterow, deleterow);
		}
		
		//==== lijj 添加是否有下游进口合同的判断=====
		SpecialBusiUtil busiUtil = new SpecialBusiUtil();
		SaleOrderVO bill = (SaleOrderVO) this.getModel().getSelectedData();
	
		if(bill != null){
			boolean hasLowerBill = busiUtil.hasLowerBill(bill.getPrimaryKey());
			if(hasLowerBill){
				MessageDialog.showHintDlg(null, "提示", "已生成进口合同不能进行此操作！");
				return false;
			}
		}
		//==== lijj 添加是否有下游进口合同的判断=====
		
		return true;
	}

	private void delProID(int[] srcrows, IKeyValue keyValue) {
		for (int row : srcrows) {
			UFBoolean flag = keyValue.getBodyUFBooleanValue(row,
					SaleOrderBVO.BLARGESSFLAG);
			if (flag.booleanValue()) {
				String largesssrcid = keyValue.getBodyStringValue(row,
						SaleOrderBVO.CLARGESSSRCID);
				if (PubAppTool.isNull(largesssrcid)) {
					continue;
				}
				int index = 0;
				for (int i = 0; i < keyValue.getBodyCount(); i++) {
					// 如果是被删除的哪一行，则跳过
					if (row == i) {
						continue;
					}
					if (largesssrcid.equals(keyValue.getBodyStringValue(i,
							SaleOrderBVO.CLARGESSSRCID))) {
						index++;
					}
				}
				for (int i = 0; i < keyValue.getBodyCount(); i++) {
					if (index == 0
							&& largesssrcid.equals(keyValue.getBodyStringValue(
									i, SaleOrderBVO.CSALEORDERBID))) {
						keyValue.setBodyValue(i, SaleOrderBVO.CBUYPROMOTTYPEID,
								null);
						keyValue.setBodyValue(i, SaleOrderBVO.CBUYLARGESSACTID,
								null);
					}
				}
			}
		}
	}

	protected boolean checkCanDelRows(int[] srcrows, IKeyValue keyValue) {
		if (null == srcrows || srcrows.length == 0) {
			return false;
		}
		Set<String> listnotdelrow = new HashSet<String>();
		List<String> offsetnotdelrow = new ArrayList<String>();
		for (int row : srcrows) {
			String rowno = keyValue
					.getBodyStringValue(row, SaleOrderBVO.CROWNO);
			UFDouble norigsubmny = keyValue.getBodyUFDoubleValue(row,
					SaleOrderBVO.NORIGSUBMNY);
			if (!SOMathUtil.isZero(norigsubmny)) {
				offsetnotdelrow.add(rowno);
				continue;
			}
			for (String name : nextbillfields) {
				UFDouble ntotalvalue = keyValue.getBodyUFDoubleValue(row, name);
				if (!MathTool.equals(ntotalvalue, UFDouble.ZERO_DBL)) {
					listnotdelrow.add(rowno);
					break;
				}
			}
		}

		if (listnotdelrow.size() == 0 && offsetnotdelrow.size() == 0) {
			return true;
		}

		StringBuilder nodeletemsg = new StringBuilder();
		if (offsetnotdelrow.size() > 0) {
			StringBuilder offseterrrows = new StringBuilder();
			for (String rowno : offsetnotdelrow) {
				offseterrrows.append("[" + rowno + "]、");/* -=notranslate=- */
			}
			offseterrrows.deleteCharAt(offseterrrows.length() - 1);

			nodeletemsg.append(NCLangRes.getInstance().getStrByID("4006011_0",
					"04006011-0410", null,
					new String[] { offseterrrows.toString() })/*
															 * 行号为[{0}]的订单行已经做过冲抵
															 * ，不能删除！
															 */);
			nodeletemsg.append("\n");
		}

		if (listnotdelrow.size() > 0) {
			StringBuilder tonextrows = new StringBuilder();
			for (String rowno : listnotdelrow) {
				tonextrows.append("[" + rowno + "]、");/* -=notranslate=- */
			}
			tonextrows.deleteCharAt(tonextrows.length() - 1);
			nodeletemsg.append(NCLangRes.getInstance().getStrByID("4006011_0",
					"04006011-0411", null,
					new String[] { tonextrows.toString() })/*
															 * 行号为[{0}]的订单行已经生成下游单据
															 * ，不能删除！
															 */);
		}

		if (nodeletemsg.length() > 0) {
			ShowStatusBarMsgUtil.showErrorMsg(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("pubapp_0", "0pubapp-0092")/*
																		 * @res
																		 * "错误"
																		 */,
					nodeletemsg.toString(), this.getModel().getContext());
			return false;
		}
		return true;
	}
	
}
