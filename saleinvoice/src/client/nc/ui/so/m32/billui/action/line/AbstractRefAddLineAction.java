package nc.ui.so.m32.billui.action.line;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;
import nc.vo.so.m32.paravo.CombinCacheVO;
import nc.vo.so.m32.paravo.CombinResultVO;
import nc.vo.so.m32.paravo.RefAddLineParaVO;
import nc.vo.so.m32.util.HeadTotalCalcUtil;
import nc.vo.trade.checkrule.VOChecker;

import nc.itf.scmpub.IScmpubMaintain;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;

import nc.bs.framework.common.NCLocator;

import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.so.m32.billui.model.SaleInvoiceManageModel;
import nc.ui.so.m32.billui.pub.AddLineUtil;
import nc.ui.so.m32.billui.pub.SaleInvoiceCombin;
import nc.ui.so.m32.billui.view.SaleInvoiceEditor;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;

/**
 * 销售发票参照增行基类
 * 处理了合并编辑下参照增行的处理
 * 
 * @since 6.0
 * @version 2011-8-20 下午07:32:00
 * @author 么贵敬
 */
public abstract class AbstractRefAddLineAction extends AbstractReferenceAction {

  /**
     * 
     */
  private static final long serialVersionUID = -6778240291241731465L;

  private SaleInvoiceEditor editor;

  private AbstractAppModel model;

  /**
   * 构造方法
   */
  public AbstractRefAddLineAction() {
    super();
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object obj = this.editor.getValue();
    SaleInvoiceVO oldVO = (SaleInvoiceVO) obj;
    List<String> busitypes = new ArrayList<String>();
    busitypes.add(oldVO.getParentVO().getCbiztypeid());
    RefAddLineParaVO userobj = this.getUserOjb(oldVO);
    this.setBusitypes(busitypes);
    PfButtonClickContext context = this.createPfButtonClickContext(userobj);
    PfUtilClient.childButtonClickedNew(context);
    SaleInvoiceVO[] newvos = null;
    if (PfUtilClient.isCloseOK()) {
      newvos = (SaleInvoiceVO[]) PfUtilClient.getRetVos();
      if (VOChecker.isEmpty(newvos)) {
        return;
      }

      AddLineUtil util = new AddLineUtil();
      SaleInvoiceManageModel invoicemodel =
          (SaleInvoiceManageModel) this.getModel();
      CombinCacheVO cachevo = invoicemodel.getCombinCacheVO();
      // 合并显示
      if (null != cachevo && cachevo.getBcombinflag()) {
        // 原缓存关系
        MapList<String, SaleInvoiceBVO> oldmap = cachevo.getCombinRela();
        SaleInvoiceCombin combin = new SaleInvoiceCombin();

        SaleInvoiceVO detainvos = combin.splitEditSaleInvoice(oldVO, oldmap);
        util.checkCombinVO(detainvos, newvos);

        this.addLineCombinVO(newvos, cachevo);

        // 合并编辑下参照增行
        SaleInvoiceVO newvo =
            combin.getCombinVOByRefAndLine(detainvos, newvos, cachevo);
        newvos = new SaleInvoiceVO[] {
          newvo
        };

      }
      else {
        util.checkCombinVO(oldVO, newvos);
        SaleInvoiceVO newvo = util.getCombin(oldVO, newvos);
        newvos = new SaleInvoiceVO[] {
          newvo
        };
      }
      this.afterTranProcessor(newvos);
      this.editor.setValue(newvos[0]);

    }
  }

  /**
   * 
   * @return 发票卡片
   */
  public SaleInvoiceEditor getEditor() {
    return this.editor;
  }

  /**
   * 
   * @return 发票model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public boolean isEnabled() {
    return this.getModel().getUiState() == UIState.EDIT
        || this.getModel().getUiState() == UIState.ADD;
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
  public void setModel(AbstractAppModel model) {
    this.model = model;
    this.model.addAppEventListener(this);
  }

  /**
   * 
   * @param vo
   * @return 自定义对象
   */
  protected abstract RefAddLineParaVO getUserOjb(SaleInvoiceVO vo);

  /**
   * 合并编辑下增行设置子表主键
   * 
   * @param newvos 增行过来的VOS
   * @param cachevo 发票合并的缓存对象
   */
  private void addLineCombinVO(SaleInvoiceVO[] newvos, CombinCacheVO cachevo) {
    List<SaleInvoiceBVO> alNewBodys = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceVO vo : newvos) {
      for (SaleInvoiceBVO bvo : vo.getChildrenVO()) {
        alNewBodys.add(bvo);
      }
    }
    IScmpubMaintain srv = NCLocator.getInstance().lookup(IScmpubMaintain.class);

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
    CombinResultVO combinres = combinutil.combinSaleInvoices(newvos, cachevo);
    MapList<String, SaleInvoiceBVO> newmap = combinres.getCombinRela();
    Set<String> newkeys = newmap.keySet();
    MapList<String, SaleInvoiceBVO> oldmap = cachevo.getCombinRela();
    for (String key : newkeys) {
      oldmap.putAll(key, newmap.get(key));
    }

  }

  private void afterTranProcessor(SaleInvoiceVO[] newvos) {

    // 行号设置
    for (SaleInvoiceVO newvo : newvos) {
      VORowNoUtils.setVOsRowNoByRule(newvo.getAllChildrenVO(),
          SaleInvoiceBVO.CROWNO);
    }

    HeadTotalCalcUtil.getInstance().calcHeadTotalValue(newvos);

  }

  private PfButtonClickContext createPfButtonClickContext(
      RefAddLineParaVO userobj) {
    PfButtonClickContext context = new PfButtonClickContext();
    context.setParent(this.getModel().getContext().getEntranceUI());
    context.setSrcBillType(this.getSourceBillType());
    context.setPk_group(this.getModel().getContext().getPk_group());
    context.setUserId(this.getModel().getContext().getPk_loginUser());
    // 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
    context.setCurrBilltype(SOBillType.Invoice.getCode());
    context.setUserObj(userobj);
    context.setSrcBillId(null);
    context.setBusiTypes(this.getBusitypes());
    // 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
    // 上游的交易类型集合
    context.setTransTypes(this.getTranstypes());
    // 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
    // 2（根据流程配置）、-1（不根据交易类型分组）
    context.setClassifyMode(PfButtonClickContext.ClassifyByBusiflow);
    return context;
  }
}
