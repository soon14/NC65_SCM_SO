package nc.ui.so.m38.report;

import java.awt.Container;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.bs.pf.pub.PfDataCache;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.m38.IPreOrderMaintain;
import nc.itf.uap.bbd.func.IFuncRegisterQueryService;
import nc.pub.smart.data.DataSet;
import nc.pub.smart.tracedata.ITraceDataOperator;
import nc.pub.smart.tracedata.TraceDataInterface;
import nc.pub.smart.tracedata.TraceDataParam;
import nc.sfbase.client.ClientToolKit;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m38.entity.PreOrderVO;
import nc.vo.to.pub.util.StringUtils;

import com.ufida.report.free.userdef.IMenuActionInfo;

/**
 * 联查预订单类
 *
 * @since 6.0
 * @version 2011-1-5 上午10:09:05
 * @author 么贵敬
 */
public class LinkQueryPreOrderAction implements ITraceDataOperator,
    TraceDataInterface {

  @Override
  public Action[] ctreateExtensionActions() {
    return null;
  }

  @Override
  public IMenuActionInfo getMenuItemInfo() {
    return null;
  }

  @Override
  public ITraceDataOperator[] provideTraceDataOperator() {
    return new LinkQueryPreOrderAction[] {
      new LinkQueryPreOrderAction()
    };
  }

  @Override
  public void traceData(Container container, TraceDataParam param) {
    if (container == null || param == null) {
      String hint = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0013")/*@res "请选择预订单行！"*/;
      MessageDialog.showHintDlg(container, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0004")/*@res "提示"*/, hint);
      return;
    }
    DataSet dataSet = param.getDataSet();
    String hid = (String) dataSet.getData(param.getRow(), "cpreorderid");
    if (StringUtils.isEmpty(hid)) {
      String msg = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0014")/*@res "预订单主键为空，出现数据错误！"*/;
      MessageDialog.showErrorDlg(container, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0","04006012-0015")/*@res "错误"*/, msg);
      return;
    }
    PreOrderVO[] preordervo = this.queryPreOrderVOByHid(hid);

    IFuncRegisterQueryService funcService =
        NCLocator.getInstance().lookup(IFuncRegisterQueryService.class);
    String nodecode = PfDataCache.getBillType("38").getNodecode();
    FuncRegisterVO funvo = null;
    try {
      funvo = funcService.queryFunctionByCode(nodecode);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    FuncletInitData initData = new FuncletInitData();
    initData.setInitType(101);
    initData.setInitData(preordervo);
    FuncletWindowLauncher.openFuncNodeDialog(ClientToolKit.getApplet(), funvo,
        initData, null, true, false);
  }

  private PreOrderVO[] queryPreOrderVOByHid(String hid) {
    IPreOrderMaintain rp =
        NCLocator.getInstance().lookup(IPreOrderMaintain.class);
    PreOrderVO[] bvos = null;
    SqlBuilder sql = new SqlBuilder();
    sql.append("select cpreorderid from so_preorder");
    sql.append(" where ");
    sql.append("cpreorderid", hid);
    try {
      bvos = rp.queryPreOrder(sql.toString());
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return bvos;
  }
}