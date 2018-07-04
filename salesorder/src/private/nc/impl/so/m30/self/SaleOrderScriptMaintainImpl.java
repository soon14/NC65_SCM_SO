package nc.impl.so.m30.self;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.env.BSContext;
import nc.impl.so.m30.action.main.DeleteSaleOrderAction;
import nc.impl.so.m30.action.main.InsertSaleOrderAction;
import nc.impl.so.m30.action.main.UpdateSaleOrderAction;
import nc.itf.so.m30.self.ISaleOrderScriptMaintain;
import nc.pubitf.so.m35.so.m30.IArsubToSaleorder;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.so.m30.entity.OffsetTempVO;
import nc.vo.so.m30.entity.SaleOrderUserObject;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.scale.SaleOrderScaleProcessor;
import nc.vo.so.m30.sobalance.entity.SoBalanceVO;
import nc.vo.so.m30.util.SaleOrderVOUtil;
import nc.vo.so.m35.entity.ArsubInterfaceVO;
import nc.vo.so.pub.util.SOVOChecker;

public class SaleOrderScriptMaintainImpl implements ISaleOrderScriptMaintain {

  @Override
  public SaleOrderVO[] saleOrderUpdate(SaleOrderVO[] vos, PfUserObject userObj,
      SaleOrderVO[] originBills) throws BusinessException {

    SaleOrderVO[] ret = null;

    try {
      this.checkRule(userObj);
      // 判断是否处理现销——收款核销关系，在session中设置标识
      this.examSoBalance(userObj, vos[0]);
      // 精度检查
      checkScale(userObj, vos);
      /** -------------- 修改动作 -------------- */
      UpdateSaleOrderAction action = new UpdateSaleOrderAction();
      ret = action.update(vos, originBills);
      /** -------------- 修改动作 -------------- */

      // 判断是否处理费用冲抵，在session中设置标识：以后要改为和收款核销一样的rule处理
      this.examOffset(userObj, ret);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

    return ret;
  }

  private void checkScale(PfUserObject userObj, SaleOrderVO[] vos) {
    // 外部交换平台的单据才进行精度检查。
    if (userObj==null||userObj.getUserObject() == null) {
      new SaleOrderScaleProcessor().checkBillPrecision(vos);
    }
    else {
      SaleOrderUserObject obj = (SaleOrderUserObject) userObj.getUserObject();
      if (!obj.isIsclientsave()) {
        new SaleOrderScaleProcessor().checkBillPrecision(vos);
      }
    }
  }

  @Override
  public SaleOrderVO[] saleOrderInsert(SaleOrderVO[] vos, PfUserObject userObj)
      throws BusinessException {

    SaleOrderVO[] ret = null;

    try {
      this.checkRule(userObj);
      // 判断是否处理现销——收款核销关系，在session中设置标识
      this.examSoBalance(userObj, vos[0]);
      // 精度检查
      checkScale(userObj, vos);
      /** -------------- 保存动作 -------------- */
      InsertSaleOrderAction action = new InsertSaleOrderAction();
      ret = action.insert(vos);
      /** -------------- 保存动作 -------------- */

      // 判断是否处理费用冲抵，在session中设置标识：以后要改为和收款核销一样的rule处理
      this.examOffset(userObj, ret);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }

    return ret;
  }

  @Override
  public SaleOrderVO[] saleOrderDelete(SaleOrderVO[] vos, PfUserObject userObj)
      throws BusinessException {
    SaleOrderVO[] ret = null;
    try {
      DeleteSaleOrderAction action = new DeleteSaleOrderAction();
      ret = action.delete(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  private void checkRule(PfUserObject userObj) {
    this.examThisGatheringMny(userObj);
  }

  private void examThisGatheringMny(PfUserObject userObj) {
    // 本次订单收款金额
    if (userObj != null && userObj.getUserObject() != null) {
      SaleOrderUserObject userobj =
          (SaleOrderUserObject) userObj.getUserObject();
      UFDouble thisGatheringMny = userobj.getThisGatheringMny();
      BSContext.getInstance().setSession("cashsale.thisGatheringMny",
          thisGatheringMny);
    }
  }

  private void examOffset(PfUserObject userObj, SaleOrderVO[] vos) {
    // 保存冲抵关系并回写销售费用单
    if (userObj != null && userObj.getUserObject() != null) {
      SaleOrderVOUtil voutil = new SaleOrderVOUtil();
      ArsubInterfaceVO[] arsubvo = voutil.changeToArsubInterfaceVO(vos);
      IArsubToSaleorder service =
          NCLocator.getInstance().lookup(IArsubToSaleorder.class);
      SaleOrderUserObject userobj =
          (SaleOrderUserObject) userObj.getUserObject();
      OffsetTempVO[] tempvo = new OffsetTempVO[] {
        userobj.getOffsetTempVO()
      };
      if (userobj.getOffsetTempVO() != null) {
        try {
          service.writeArsubRelation(arsubvo, tempvo);
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
      }
    }
  }

  private void examSoBalance(PfUserObject userObj, SaleOrderVO inCurVO) {
    // 收款核销关系
    if (userObj != null && userObj.getUserObject() != null) {
      SaleOrderUserObject userobj =
          (SaleOrderUserObject) userObj.getUserObject();
      SoBalanceVO soBalanceVO = userobj.getSoBalanceVO();
      if (!SOVOChecker.isEmpty(soBalanceVO)
          && !SOVOChecker.isEmpty(soBalanceVO.getParentVO())) {
        // 因为前台销售单据日期可以修改，所以此处重新在后台赋一次
        soBalanceVO.getParentVO().setDbilldate(
            inCurVO.getParentVO().getDbilldate());
        List<SoBalanceVO> voList = new ArrayList<SoBalanceVO>();
        voList.add(soBalanceVO);
        BSContext.getInstance().setSession("cashsale.sobalancevos", voList);
      }
    }
  }
}
