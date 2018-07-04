package nc.bs.so.m30.rule.maintainprocess;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m30.ref.so.rtpolicy.SORtPolicyServicesUtil;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.rule.HeadTotalCalculateRule;
import nc.vo.so.pub.SOItemKey;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.VOKeyValue;

/**
 * @description
 * 补充单据冗余数据规则。补全表体组织、单据日期 修改人修改时间、默认值等
 * @scene 
 * 销售订单新增、修改保存前
 * @param 
 * 无
 */
public class FillupRedundanceDataRule implements IRule<SaleOrderVO> {

  @Override
  public void process(SaleOrderVO[] vos) {
    for (SaleOrderVO bill : vos) {
      // 这个是补全VO，处理时需要区分行状态
      // 补充表体冗余字段
      this.fillupRowByHead(bill);
//    modify by wangshu6 将补充退换货标记提取出去成公共类 20150319
//   // 补充退换货标记
//   this.fillupFretexchange(bill);
      // 补充退货政策
      SORtPolicyServicesUtil.fillupReturnPolicy(bill);

      // 补全行状态
      this.fillupRowStateByHead(bill);

      // 这个是补全行号，处理时需要区分行状态
      this.fillupRowNo(bill);
      // 处理布尔型默认值
      this.fillupBooleanFields(bill);
      // 计算表头合计信息
      IKeyValue keyValue = new VOKeyValue<SaleOrderVO>(bill);
      HeadTotalCalculateRule totalrule = new HeadTotalCalculateRule(keyValue);
      totalrule.calculateHeadTotal();
    }
  }

  private void fillupRowStateByHead(SaleOrderVO bill) {

    SaleOrderHVO headvo = bill.getParentVO();

    Integer status = headvo.getFstatusflag();
    SaleOrderBVO[] bodyvos = bill.getChildrenVO();
    for (SaleOrderBVO bodyvo : bodyvos) {
      // 删除的行不补充冗余信息
      if (VOStatus.DELETED == bodyvo.getStatus()) {
        continue;
      }
      if (null == bodyvo.getFrowstatus()) {
        bodyvo.setFrowstatus(status);
      }
    }
  }

  private void fillupRowNo(IBill vo) {
    AbstractBill bill = (AbstractBill) vo;
    // 为行号为空的行补充行号。
    CircularlyAccessibleValueObject[] items = bill.getChildrenVO();
    List<CircularlyAccessibleValueObject> bvos =
        new ArrayList<CircularlyAccessibleValueObject>();
    for (CircularlyAccessibleValueObject item : items) {
      int vostatus = item.getStatus();
      // 不包含删除的行
      if (vostatus == VOStatus.DELETED) {
        continue;
      }
      bvos.add(item);
    }
    items = bvos.toArray(new CircularlyAccessibleValueObject[0]);
    VORowNoUtils.setVOsRowNoByRule(items, SOItemKey.CROWNO);
  }


  private void fillupRowByHead(SaleOrderVO bill) {

    SaleOrderHVO headvo = bill.getParentVO();
    String orgid = headvo.getPk_org();
    String pk_group = headvo.getPk_group();
    UFDate billdate = headvo.getDbilldate();
    SaleOrderBVO[] bodyvos = bill.getChildrenVO();
    for (SaleOrderBVO bodyvo : bodyvos) {
      int vostatus = bodyvo.getStatus();
      // 删除的行不补充冗余信息
      if (VOStatus.DELETED == vostatus) {
        continue;
      }
      bodyvo.setPk_org(orgid);
      bodyvo.setPk_group(pk_group);
      bodyvo.setDbilldate(billdate);

    }
  }

  /**
   * 布尔值的默认填充
   * 
   * @param bill
   */
  private void fillupBooleanFields(SaleOrderVO bill) {

    SaleOrderBVO[] bodyvos = bill.getChildrenVO();
    String[] booleanFields =
        new String[] {
          SaleOrderBVO.BLARGESSFLAG, SaleOrderBVO.BDISCOUNTFLAG,
          SaleOrderBVO.BLABORFLAG, SaleOrderBVO.BARRANGEDFLAG,
          SaleOrderBVO.BBARSETTLEFLAG, SaleOrderBVO.BBCOSTSETTLEFLAG,
          SaleOrderBVO.BBINDFLAG, SaleOrderBVO.BBINVOICENDFLAG,
          SaleOrderBVO.BBOUTENDFLAG, SaleOrderBVO.BBSENDENDFLAG,
          SaleOrderBVO.BBSETTLEENDFLAG, SaleOrderBVO.BJCZXSFLAG,
          SaleOrderBVO.BPREROWCLOSEFLAG, SaleOrderBVO.BTRIATRADEFLAG
        };
    for (SaleOrderBVO bodyvo : bodyvos) {
      // 删除的行不补充冗余信息
      if (VOStatus.DELETED == bodyvo.getStatus()) {
        continue;
      }
      for (String key : booleanFields) {
        if (bodyvo.getAttributeValue(key) == null) {
          bodyvo.setAttributeValue(key, UFBoolean.FALSE);
        }
      }
    }
  }

}
