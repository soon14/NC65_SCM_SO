package nc.ui.so.m30.billrefFor28;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/*下游单据触发参照上游单据*/
public class Add28Action extends AbstractReferenceAction {

	private static final long serialVersionUID = -4417976703049420324L;

	private BillForm editor;

	private AbstractAppModel model;

	@Override
	public void doAction(ActionEvent e) throws Exception {

		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {

			SaleOrderVO[] vos = (SaleOrderVO[]) PfUtilClient.getRetVos();
			// 显示到转单界面上

			for (SaleOrderVO saleOrderVO : vos) {
				SaleOrderHVO hvo = saleOrderVO.getParentVO();
				if((String)hvo.getAttributeValue("buyccurrencyid")==null){
					continue;
				}
				UFDouble localcurrate = CurrencyRate
						.getGlobalLocalCurrencySellRate(
								(String)hvo.getAttributeValue("buyccurrencyid"),
								TimeUtils.getsrvBaseDate());
				// 给汇率赋值
				saleOrderVO.getParentVO().setAttributeValue("exchange_rate", localcurrate);
			}
			this.getTransferViewProcessor().processBillTransfer(vos);
		}
	}

	private PfButtonClickContext createPfButtonClickContext() {
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(this.getModel().getContext().getEntranceUI());
		context.setSrcBillType(this.getSourceBillType());
		context.setPk_group(this.getModel().getContext().getPk_group());
		context.setUserId(this.getModel().getContext().getPk_loginUser());
		// 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
		String vtrantype = TrantypeFuncUtils.getTrantype(this.getModel()
				.getContext());
		if (StringUtil.isEmptyWithTrim(vtrantype)) {
			// 设置当前(下游)单据类型编码
			context.setCurrBilltype("30");
		} else {
			context.setCurrBilltype(vtrantype);
		}
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
		// 上游的交易类型集合
		context.setTransTypes(this.getTranstypes());
		// 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
		// 2（根据流程配置）、-1（不根据交易类型分组）
		context.setClassifyMode(PfButtonClickContext.ClassifyByItfdef);
		return context;
	}

	public BillForm getEditor() {
		return this.editor;
	}

	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		return this.model.getUiState() == UIState.NOT_EDIT;
	}
}