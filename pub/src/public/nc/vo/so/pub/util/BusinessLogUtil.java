package nc.vo.so.pub.util;

import java.util.ArrayList;
import java.util.List;

import nc.itf.scmpub.reference.uap.md.MDQueryFacade;
import nc.itf.scmpub.reference.uap.util.BusiLogServiceUtil;
import nc.md.model.IBean;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.sm.busilog.BusiLogSmartVO;
import nc.vo.sm.busilog.BusiobjURL;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.pub.SOItemKey;

/**
 * so 书写业务日志调用类
 * 
 * @since 6.0
 * @version 2010-11-5 上午11:21:04
 * @author 祝会征
 */
public class BusinessLogUtil {
  // 动作编码
  private String actiontype;

  // 业务对象名称
  private String busiobjname;

  // 打开功能节点时的初始化参数，由业务程序自己解析
  private String funcletInitData;

  // 功能节点号
  private String funcnode;

  // 操作详细信息
  private String logmsg;

  public String getActiontype() {
    return this.actiontype;
  }

  public String getBusiobjname() {
    return this.busiobjname;
  }

  public String getFuncletInitData() {
    return this.funcletInitData;
  }

  public String getFuncnode() {
    return this.funcnode;
  }

  public String getLogmsg() {
    return this.logmsg;
  }

  /**
   * 设置单据的业务日志
   * 
   * @param bills
   * @param action
   * @throws BusinessException
   */
  public void insertBusiLog(AbstractBill[] bills, boolean isHead)
      throws BusinessException {
    if (null == bills) {
      return;
    }
    this.checkInfo();
    List<BusiLogSmartVO> list = new ArrayList<BusiLogSmartVO>();
    for (AbstractBill vo : bills) {
      BusiLogSmartVO logVo = new BusiLogSmartVO();
      logVo.setOperationcode(this.getActiontype());
      IBean bean = this.getMetadata(vo, isHead);
      logVo.setTypepk_busiobj(bean.getID());
      logVo.setMd_namespace(bean.getFullName());
      logVo.setMd_name(bean.getName());
      logVo.setPk_busiobj(vo.getParentVO().getPrimaryKey());
      Object billcode = vo.getParentVO().getAttributeValue(SOItemKey.VBILLCODE);
      logVo.setBusiobjcode(ValueUtils.getString(billcode));
      logVo.setBusiobjname(this.getBusiobjname());
      BusiobjURL url = new BusiobjURL();
      url.setFuncNode(this.getFuncnode());
      url.setFuncletInitData(this.getFuncletInitData());
      Object pk_org = vo.getParentVO().getAttributeValue(SOItemKey.PK_ORG);
      logVo.setOrgpk_busiobj(ValueUtils.getString(pk_org));
      logVo.setBusiobjurl(url);
      logVo.setOperateresult(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006004_0", "04006004-0213")/*成功*/);
      logVo.setLogmsg(this.getLogmsg());
      list.add(logVo);
    }
    BusiLogServiceUtil.insertSmartBusiLog(list);
  }

  /**
   * 设置单据的表体操作业务日志
   * 
   * @param bills
   * @param action
   * @throws BusinessException
   */
  public void insertBusiLogForBody(AbstractBill[] bills)
      throws BusinessException {
    if (null == bills) {
      return;
    }
    this.checkInfo();
    List<BusiLogSmartVO> list = new ArrayList<BusiLogSmartVO>();
    for (AbstractBill vo : bills) {
      CircularlyAccessibleValueObject[] bvos = vo.getChildrenVO();
      for (CircularlyAccessibleValueObject bvo : bvos) {
        BusiLogSmartVO logVo = new BusiLogSmartVO();
        logVo.setOperationcode(this.getActiontype());
        IBean bean = this.getMetadata(vo, false);
        logVo.setTypepk_busiobj(bean.getID());
        logVo.setMd_namespace(bean.getFullName());
        logVo.setMd_name(bean.getName());
        logVo.setPk_busiobj(vo.getParentVO().getPrimaryKey());
        Object billcode =
            vo.getParentVO().getAttributeValue(SOItemKey.VBILLCODE);
        logVo.setBusiobjcode(ValueUtils.getString(billcode));
        logVo.setBusiobjname(this.getBusiobjname());
        BusiobjURL url = new BusiobjURL();
        url.setFuncNode(this.getFuncnode());
        url.setFuncletInitData(this.getFuncletInitData());
        Object pk_org = vo.getParentVO().getAttributeValue(SOItemKey.PK_ORG);
        logVo.setOrgpk_busiobj(ValueUtils.getString(pk_org));
        logVo.setBusiobjurl(url);
        logVo.setOperateresult(NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4006004_0", "04006004-0213")/*成功*/);
        logVo.setLogmsg(NCLangRes4VoTransl.getNCLangRes().getStrByID(
            "4006004_0",
            "04006004-0214",
            null,
            new String[] {
              (String) bvo.getAttributeValue(SOItemKey.CROWNO),
              this.getBusiobjname()
            })/*有以下行：{0}{1}*/);
        list.add(logVo);
      }
    }
    BusiLogServiceUtil.insertSmartBusiLog(list);
  }

  public void setActiontype(String actiontype) {
    this.actiontype = actiontype;
  }

  public void setBusiobjname(String busiobjname) {
    this.busiobjname = busiobjname;
  }

  public void setFuncletInitData(String funcletInitData) {
    this.funcletInitData = funcletInitData;
  }

  public void setFuncnode(String funcnode) {
    this.funcnode = funcnode;
  }

  public void setLogmsg(String logmsg) {
    this.logmsg = logmsg;
  }

  /**
   * 检查必须信息是否为空
   */
  private void checkInfo() {
    StringBuffer errMsg = new StringBuffer();
    if (this.isNull(this.getActiontype())) {
      errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0",
          "04006004-0215")/*单据动作编码不能为空。*/);
      errMsg.append("\n");
    }
    if (this.isNull(this.getFuncnode())) {
      errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0",
          "04006004-0216")/*功能节点号不能为空。*/);
      errMsg.append("\n");
    }
    if (this.isNull(this.getBusiobjname())) {
      errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4006004_0",
          "04006004-0217")/*业务对象名称不能为空。*/);
      errMsg.append("\n");
    }
    if (errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(errMsg.toString());
    }
  }

  private IBean getMetadata(AbstractBill vo, boolean isHead) {
    IVOMeta meta;
    if (isHead) {
      meta = vo.getParent().getMetaData();
    }
    else {
      meta = vo.getChildren(DeliveryBVO.class)[0].getMetaData();
    }
    //
    String beanname = meta.getEntityName();
    return MDQueryFacade.getBeanByFullName(beanname);
  }

  private boolean isNull(String str) {
    if (null == str || str.trim().length() == 0) {
      return true;
    }
    return false;
  }
}
