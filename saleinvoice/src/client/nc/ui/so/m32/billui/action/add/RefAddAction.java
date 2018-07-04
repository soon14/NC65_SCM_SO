package nc.ui.so.m32.billui.action.add;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.pf.PfAddInfo;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.so.m32.paravo.RefAddLineParaVO;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.IScmpubMaintain;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;

import nc.bs.framework.common.NCLocator;

import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.UIState;

/**
 * 销售发票参照新增
 * 
 * @since 6.3
 * @version 2012-12-21 上午10:52:30
 * @author yaogj
 */
public class RefAddAction extends AbstractReferenceAction {

  /**
     * 
     */
  private static final long serialVersionUID = 8278944084279171430L;

  private SaleInvoiceEditor editor;

  private SaleInvoiceManageModel model;

  /**
   * RefAddAction 的构造子
   */
  public RefAddAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    PfButtonClickContext context = this.createPfButtonClickContext();
    PfUtilClient.childButtonClickedNew(context);

    SaleInvoiceVO[] newvos = null;
    if (PfUtilClient.isCloseOK()) {
      newvos = (SaleInvoiceVO[]) PfUtilClient.getRetVos();
      if (VOChecker.isEmpty(newvos)) {
        return;
      }
      // 调公共转单处理前
      this.beforeTranProcessor(newvos);
      // 如果集团参数是汇总显示的话
      CombinResultVO combinres = this.combinSaleInvoices(newvos);
      if (combinres.getBcombinflag()) {
        newvos = combinres.getCombinvos();
      }
      // 缓存汇总结果
      this.getModel().setCombinCacheVO(combinres.getCacheVO());
      // 拉单数据界面处理
      this.getTransferViewProcessor().processBillTransfer(newvos);
    }
  }

  /**
   * 方法功能描述：返回销售发票Editor。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-29 下午07:24:42
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * 方法功能描述：返回销售发票的model。
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author 冯加滨
   * @time 2010-4-29 下午07:25:28
   */
  public SaleInvoiceManageModel getModel() {
    return this.model;
  }

  /**
   * 按钮可用设置
   */
  @Override
  public boolean isEnabled() {
    return this.getModel().getUiState() == UIState.NOT_EDIT;
  }

  /**
   * 
   * @param view 发票卡片
   */
  public void setEditor(SaleInvoiceEditor view) {
    this.editor = view;
  }

  /**
   * 
   * @param model 发票model
   */
  public void setModel(SaleInvoiceManageModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  /**
   * 方法功能描述：调用公共转单处理类前处理。
   * <p>
   * <b>参数说明</b>
   * 
   * @param newvos
   *          <p>
   * @author 冯加滨
   * @time 2010-4-29 下午07:28:04
   */
  protected void beforeTranProcessor(SaleInvoiceVO[] newvos) {
    if (newvos != null && newvos.length > 0) {
      // 行号设置
      for (SaleInvoiceVO newvo : newvos) {
        VORowNoUtils.setVOsRowNoByRule(newvo.getAllChildrenVO(),
            SaleInvoiceBVO.CROWNO);
      }
      // 计算表头合计数值
      HeadTotalCalcUtil.getInstance().calcHeadTotalValue(newvos);
    }
  }

  protected CombinResultVO combinSaleInvoices(SaleInvoiceVO[] newvos) {

    CombinResultVO combinres = null;
    CombinCacheVO cachevo = this.getModel().getCombinCacheVO();
    if (cachevo.getBcombinflag()) {
      List<SaleInvoiceBVO> alNewBodys = new ArrayList<SaleInvoiceBVO>();
      List<SaleInvoiceVO> alNewHeads = new ArrayList<SaleInvoiceVO>();
      for (SaleInvoiceVO vo : newvos) {
        alNewHeads.add(vo);
        for (SaleInvoiceBVO bvo : vo.getChildrenVO()) {
          alNewBodys.add(bvo);
        }
      }
      IScmpubMaintain srv =
          NCLocator.getInstance().lookup(IScmpubMaintain.class);

      /**
       * 这里只补充子表ID 原因是要用主表id判断新增还是修改
       * 主表ID放到保存前动作补充
       */
      String[] ids = null;
      try {
        ids = srv.getIDs(alNewBodys.size());
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
      if (null != ids) {
        int i = 0;
        for (SaleInvoiceVO vo : newvos) {
          vo.getParentVO().setStatus(VOStatus.NEW);
          for (SaleInvoiceBVO bvo : vo.getChildrenVO()) {
            bvo.setPrimaryKey(ids[i]);
            bvo.setStatus(VOStatus.NEW);
            i++;
          }
        }
      }

      SaleInvoiceCombin combinutil = new SaleInvoiceCombin();
      combinres = combinutil.combinSaleInvoices(newvos, cachevo);
    }
    else {
      combinres = new CombinResultVO(false);
    }
    return combinres;
  }

  public PfButtonClickContext createPfButtonClickContext() {

    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
    context.setCurrBilltype(SOBillType.Invoice.getCode());
    context.setUserObj(null);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
    // 上游的交易类型集合
    context.setTransTypes(this.getTranstypes());
    // 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
    // 2（根据流程配置）、-1（不根据交易类型分组）
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);

    if (SysInitGroupQuery.isICEnabled()) {
      try {
        RefAddLineParaVO userobj = new RefAddLineParaVO();
        String trantype =
            TrantypeFuncUtils.getTrantype(this.getModel().getContext());
        PfAddInfo[] vosInfoAdd =
            PfUtilClient.retAddInfo(SOBillType.Invoice.getCode(), trantype,
                this.getModel().getContext().getPk_group(), this.getModel()
                    .getContext().getPk_loginUser(), true);
        // 没有出库到发票的按钮
        if (null == vosInfoAdd) {
          return context;
        }
        for (PfAddInfo voInfoAdd : vosInfoAdd) {
          if (ICBillType.SaleOut.getCode().equals(voInfoAdd.getSrc_billtype())) {
            List<String> busitypes = voInfoAdd.getBusitypes();
            userobj
                .setBusitypes(busitypes.toArray(new String[busitypes.size()]));
          }
        }
        context.setUserObj(userobj);
      }
      catch (Exception e) {
        ExceptionUtils.wrappException(e);
      }
    }
    return context;
  }

  /**
   * 修改业务日期
   * 
   * @param pbillVOs
   */
  // private void setDefaultValue(SaleInvoiceVO[] pbillVOs) {
  // SaleInvoiceVO[] vos = pbillVOs;
  // UFDate date = AppContext.getInstance().getBusiDate();
  // for (SaleInvoiceVO vo : vos) {
  // vo.getParentVO().setDbilldate(date);
  // SaleInvoiceBVO[] bvos = vo.getChildrenVO();
  // for (SaleInvoiceBVO bvo : bvos) {
  // bvo.setDbilldate(date);
  // }
  // }
  // }
}
