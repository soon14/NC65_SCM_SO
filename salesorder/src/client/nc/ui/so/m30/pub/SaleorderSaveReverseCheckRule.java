package nc.ui.so.m30.pub;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/**
 * 订单保存时校验预留
 * 
 * @since 6.0
 * @version 2011-4-25 上午11:15:38
 * @author 祝会征
 */
public class SaleorderSaveReverseCheckRule {
  private Map<String, SaleOrderBVO> bvoMap;

  /**
   * 订单保存时检查单据上改变的值
   * 
   * @param oldVO
   * @param newVO
   * @return
   */
  public String checkChangeItems(SaleOrderVO oldVO, SaleOrderVO newVO) {
    String errMsg = null;
    this.initOldbvoInfos(oldVO);
    if ((null == this.bvoMap) || (this.bvoMap.size() == 0)) {
      return null;
    }
    errMsg = this.checkChangeItems(newVO);
    return errMsg;
  }

  private String checkChangeItems(SaleOrderVO newVO) {
    SaleOrderBVO[] newbvos = newVO.getChildrenVO();
    for (SaleOrderBVO newbvo : newbvos) {
      String bid = newbvo.getCsaleorderbid();
      if (!this.bvoMap.containsKey(bid)) {
        continue;
      }
      SaleOrderBVO oldbvo = this.bvoMap.get(bid);
      boolean expr1 = this.checkNum(oldbvo, newbvo);
      boolean expr2 = this.checkFree(oldbvo, newbvo);
      boolean expr3 = this.checkMaterial(oldbvo, newbvo);
      boolean expr4 = this.checkProductInfo(oldbvo, newbvo);
      boolean expr5 = this.checkSendDate(oldbvo, newbvo);
      boolean expr6 = this.checkSendInfo(oldbvo, newbvo);
      boolean expr = expr1 || expr2 || expr3 || expr4 || expr5 || expr6;
      if (expr) {
        String errMsg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006011_0",
                "04006011-0043")/*@res "修改后的物料存量信息与原预留记录不一致，保存将会删除预留记录，请确认是否保存"*/;
        return errMsg;
      }
    }
    return null;

  }

  /**
   * 自由项检查
   * 
   * @param oldbvo
   * @param newbvo
   * @return
   */
  private boolean checkFree(SaleOrderBVO oldbvo, SaleOrderBVO newbvo) {
    boolean expr = false;
    for (int i = 0; i < 10; i++) {
      String oldfree = (String) oldbvo.getAttributeValue("vfree" + i);
      String newfree = (String) newbvo.getAttributeValue("vfree" + i);
      if (!PubAppTool.isEqual(oldfree, newfree)) {
        expr = true;
        break;
      }
    }
    return expr;
  }

  /**
   * 检查物料信息、物料版本信息
   * 
   * @param oldbvo
   * @param newbvo
   * @return
   */
  private boolean checkMaterial(SaleOrderBVO oldbvo, SaleOrderBVO newbvo) {
    String oldpk = oldbvo.getCmaterialvid();
    String oldpk_v = oldbvo.getCmaterialid();
    String newpk = newbvo.getCmaterialvid();
    String newpk_v = newbvo.getCmaterialid();
    return !PubAppTool.isEqual(newpk, oldpk)
        || !PubAppTool.isEqual(newpk_v, oldpk_v);
  }

  /**
   * 发货主数量是否小于预留的主数量
   * 
   * @param oldbvo
   * @param newbvo
   * @return
   */
  private boolean checkNum(SaleOrderBVO oldbvo, SaleOrderBVO newbvo) {
    UFDouble reqnum = oldbvo.getNreqrsnum();
    UFDouble num = newbvo.getNnum();
    return MathTool.compareTo(num, reqnum) < 0;
  }

  /**
   * 生产厂商、供应商、项目检查
   * 
   * @param oldbvo
   * @param newbvo
   * @return
   */
  private boolean checkProductInfo(SaleOrderBVO oldbvo, SaleOrderBVO newbvo) {
    String oldproduct = oldbvo.getCproductorid();
    String newproduct = newbvo.getCproductorid();
    String oldcvendor = oldbvo.getCvendorid();
    String newcvendor = newbvo.getCvendorid();
    String oldproject = oldbvo.getCprojectid();
    String newproject = newbvo.getCprojectid();
    return !PubAppTool.isEqual(oldproduct, newproduct)
        || !PubAppTool.isEqual(oldproject, newproject)
        || !PubAppTool.isEqual(oldcvendor, newcvendor);
  }

  /**
   * 检查发货日期
   * 
   * @param oldbvo
   * @param newbvo
   * @return
   */
  private boolean checkSendDate(SaleOrderBVO oldbvo, SaleOrderBVO newbvo) {
    UFDate oldDate = oldbvo.getDsenddate();
    UFDate newDate = newbvo.getDsenddate();
    return newDate.before(oldDate);
  }

  /**
   * 检查发货库存组织和发货仓库
   * 
   * @param oldbvo
   * @param newbvo
   * @return
   */
  private boolean checkSendInfo(SaleOrderBVO oldbvo, SaleOrderBVO newbvo) {
    String oldstockpk = oldbvo.getCsendstockorgvid();
    String newstockpk = newbvo.getCsendstockorgvid();
    String oldwarepk = oldbvo.getCsendstordocid();
    String newwarepk = newbvo.getCsendstordocid();
    return !PubAppTool.isEqual(oldstockpk, newstockpk)
        || !PubAppTool.isEqual(oldwarepk, newwarepk);
  }

  /**
   * 用于判断该单据是否做过预留
   * 
   * @param oldvo
   * @return
   */
  private void initOldbvoInfos(SaleOrderVO oldvo) {
    this.bvoMap = new HashMap<String, SaleOrderBVO>();
    SaleOrderBVO[] bvos = oldvo.getChildrenVO();
    for (SaleOrderBVO bvo : bvos) {
      UFDouble reqnum = bvo.getNreqrsnum();
      if (MathTool.compareTo(reqnum, UFDouble.ZERO_DBL) != 0) {
        this.bvoMap.put(bvo.getCsaleorderbid(), bvo);
      }
    }
  }
}
