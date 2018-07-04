package nc.bs.so.salequotation.rule;


import nc.vo.pubapp.util.TimeUtils;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.vo.trade.checkrule.VOChecker;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.so.salequotation.entity.SalequotationBVO;
import nc.vo.so.salequotation.entity.SalequotationHVO;
import nc.vo.so.salequotation.entity.AggSalequotationHVO;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售报价单保存前填充数据
 * @scene
 * 销售报价单保存前
 * @param
 * 无
 * @since 6.0
 * @version 2011-11-11 下午04:40:31
 * @author 王天文
 */
public class FillDataBeforeRule implements IRule<AggSalequotationHVO> {

  @Override
  public void process(AggSalequotationHVO[] vos) {
    for(AggSalequotationHVO vo: vos) {
      this.setBodyDefValue(vo);
      this.setHeadDefValue(vo);
    }
  }
  
  private void setBodyDefValue(AggSalequotationHVO vo) {
    //设置表体冗余销售组织、集团
    SalequotationHVO hvo = vo.getParentVO();
    String orgid = hvo.getPk_org();
    String pk_group = hvo.getPk_group();
    SalequotationBVO[] bvos = vo.getChildrenVO();
    for(SalequotationBVO bvo : bvos) {      
      bvo.setPk_group(pk_group);
      bvo.setPk_org(orgid);
    }
    
    // 为行号为空的行补充行号。
    VORowNoUtils.setVOsRowNoByRule(bvos, SalequotationBVO.CROWNO);
  }
  
  private void setHeadDefValue(AggSalequotationHVO vo) {
    SalequotationHVO hvo = vo.getParentVO();
    String pk_group = InvocationInfoProxy.getInstance().getGroupId();
    //集团
    if(VOChecker.isEmpty(hvo.getPk_group())) {
      hvo.setPk_group(pk_group);
    }
    //失效日期
//    if(VOChecker.isEmpty(hvo.getDenddate())) {
//      hvo.setDenddate(TimeUtils.getStartDate(hvo.getDbilldate()));
//    }
//    // 外部平台导入时不一定是自由状态
//    if (null == hvo.getFstatusflag()) {
//      hvo.setFstatusflag(BillStatus.FREE.getIntegerValue());
//    }
    
  }

}
