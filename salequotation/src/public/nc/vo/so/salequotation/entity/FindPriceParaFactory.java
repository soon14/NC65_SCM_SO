package nc.vo.so.salequotation.entity;

import java.util.HashMap;
import java.util.Map;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 构建询价参数VO工厂方法类
 * 
 * @since 6.0
 * @version 2011-9-14 下午09:36:56
 * @modifier 王天文
 */

public class FindPriceParaFactory {
  private static FindPriceParaFactory instance = new FindPriceParaFactory();

  private FindPriceParaFactory() {
    // do nothing
  }

  public static FindPriceParaFactory getInstance() {
    return FindPriceParaFactory.instance;
  }

  /**
   * 询历史报价询价参数填充
   * 
   * @param data
   * @param selectedRows 被选中的要发生历史询价的行号集合
   * @param pk_saleorg
   * @param pk_group
   * @return FindHistoryPriceParameter[]
   */
  public FindHistoryPriceParameter[] createFindHistoryPriceParameters(
      AggSalequotationHVO data, int[] selectedRows, String pk_saleorg,
      String pk_group) {
    FindHistoryPriceParameter[] parameters =
        new FindHistoryPriceParameter[selectedRows.length];
    for (int i = 0; i < selectedRows.length; i++) {
      parameters[i] =
          this.createFindHistoryPriceParameter(data,
              data.getChildrenVO()[selectedRows[i]], pk_saleorg, pk_group);
    }
    return parameters;
  }

  /**
   * 询价格管理询价参数填充
   * 
   * @param data
   * @param selectedrows
   * @param pk_saleorg
   * @param pk_group
   * @return FindPriceParaVO[]
   */
  public FindPriceParaVO[] createFindPriceParaVO(AggSalequotationHVO data,
      int[] rows, String pk_saleorg, String pk_group) {
    FindPriceParaVO[] paraVOs = new FindPriceParaVO[rows.length];
    for (int i = 0; i < rows.length; i++) {
      paraVOs[i] =
          this.createFindPriceParaVO(data, rows[i], pk_saleorg, pk_group);
    }
    return paraVOs;
  }

  /**
   * 询价格管理询价参数填充
   * 
   * @param data
   * @param pk_saleorg
   * @param pk_group
   * @return Map<Integer, FindPriceParaVO>
   */
  public Map<Integer, FindPriceParaVO> createFindPriceParaVO(
      AggSalequotationHVO data, String pk_saleorg, String pk_group) {
    Map<Integer, FindPriceParaVO> paraMap =
        new HashMap<Integer, FindPriceParaVO>();
    SalequotationHVO header = data.getParentVO();
    SalequotationBVO[] bodies = data.getChildrenVO();
    if (bodies != null) {
      for (int row = 0; row < bodies.length; row++) {
        if (StringUtil.isEmptyWithTrim(bodies[row].getPk_material())) {
          continue;
        }
        FindPriceParaVO paraVO =
            this.createFindPriceParaVO(header, bodies[row], pk_saleorg,
                pk_group);
        paraMap.put(Integer.valueOf(row), paraVO);
      }
    }
    return paraMap;
  }

  /**
   * 
   * @param data
   * @param row
   * @param pk_saleorg
   * @param pk_group
   * @return
   */
  private FindHistoryPriceParameter createFindHistoryPriceParameter(
      AggSalequotationHVO data, SalequotationBVO bvo, String pk_saleorg,
      String pk_group) {
    FindHistoryPriceParameter parameter = new FindHistoryPriceParameter();
    SalequotationHVO header = data.getParentVO();
    SalequotationBVO[] bodies = data.getChildrenVO();
    if (null == header || null == bodies || bodies.length == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0043")/*@res "报价单表体数据为空！"*/);
      return null;
    }
    // parameter.setFindPriceParaVO(this.createFindPriceParaVO(data, bvo,
    // pk_saleorg, pk_group));
    parameter.setCemployeeid(header.getCemployeeid());
    parameter.setCsendtypeid(header.getCsendtypeid());
    parameter.setPk_dept(header.getPk_dept());
    parameter.setPk_payterm(header.getPk_payterm());
    parameter.setVtrantype(header.getVtrantype());
    parameter.setPk_balatype(header.getPk_balatype());
    parameter.setPk_channeltype(header.getPk_channeltype());
    parameter.setPk_currtype(header.getPk_currtype());
    parameter.setPk_customer(header.getPk_customer());
    UFDate dquotedate = header.getDquotedate();
    parameter.setTpricedate(new UFDateTime(dquotedate.toDate()));

    parameter.setCqtunitid(bvo.getCqtunitid());
    parameter.setPk_areacl(bvo.getPk_areacl());
    parameter.setPk_material(bvo.getPk_material());
    parameter.setPk_qualitylevel(bvo.getPk_qualitylevel());

    parameter.setPk_group(pk_group);
    parameter.setPk_org(pk_saleorg);

    return parameter;
  }

  /**
   * 
   * @param data
   * @param row
   * @param pk_saleorg
   * @param pk_group
   * @return
   */
  private FindPriceParaVO createFindPriceParaVO(AggSalequotationHVO data,
      int row, String pk_saleorg, String pk_group) {
    SalequotationHVO header = data.getParentVO();
    SalequotationBVO[] bodies = data.getChildrenVO();
    if (null == header || null == bodies || bodies.length == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0043")/*@res "报价单表体数据为空！"*/);
      return null;
    }

    return this
        .createFindPriceParaVO(header, bodies[row], pk_saleorg, pk_group);
  }

  // /**
  // *
  // * @param data
  // * @param row
  // * @param pk_saleorg
  // * @param pk_group
  // * @return
  // */
  // private FindPriceParaVO createFindPriceParaVO(AggSalequotationHVO data,
  // SalequotationBVO bvo, String pk_saleorg, String pk_group) {
  // SalequotationHVO header = data.getParentVO();
  // SalequotationBVO[] bodies = data.getChildrenVO();
  // if (null == header || null == bodies || bodies.length == 0) {
  // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0043")/*@res
  // "报价单表体数据为空！"*/);
  // }
  //
  // return this.createFindPriceParaVO(header, bvo, pk_saleorg, pk_group);
  // }

  private FindPriceParaVO createFindPriceParaVO(SalequotationHVO header,
      SalequotationBVO bodyVO, String pk_saleorg, String pk_group) {
    FindPriceParaVO paraVO = new FindPriceParaVO();
    paraVO.setPk_qualitylevel(bodyVO.getPk_qualitylevel());
    paraVO.setPk_material(bodyVO.getPk_material());
    paraVO.setPk_customer(header.getPk_customer());
    paraVO.setPk_areacl(bodyVO.getPk_areacl());
    paraVO.setPk_channeltype(header.getPk_channeltype());
    paraVO.setPk_balatype(header.getPk_balatype());
    paraVO.setPk_currtype(header.getPk_currtype());
    paraVO.setPk_unit(bodyVO.getCqtunitid());
    paraVO.setPk_pricetype(bodyVO.getPk_pricetype());
    paraVO.setPk_sendtype(header.getCsendtypeid());
    paraVO.setVfree1(bodyVO.getVfree1());
    paraVO.setVfree2(bodyVO.getVfree2());
    paraVO.setVfree3(bodyVO.getVfree3());
    paraVO.setVfree4(bodyVO.getVfree4());
    paraVO.setVfree5(bodyVO.getVfree5());
    paraVO.setVfree6(bodyVO.getVfree6());
    paraVO.setVfree7(bodyVO.getVfree7());
    paraVO.setVfree8(bodyVO.getVfree8());
    paraVO.setVfree9(bodyVO.getVfree9());
    paraVO.setVfree10(bodyVO.getVfree10());
    paraVO.setNnum(bodyVO.getNqtnum());
    UFDate dquotedate = header.getDquotedate();
    if(dquotedate!=null){
    paraVO.setTpricedate(new UFDateTime(dquotedate.toDate()));
    }
    paraVO.setPk_org(pk_saleorg);
    paraVO.setPk_group(pk_group);
    return paraVO;
  }

}
