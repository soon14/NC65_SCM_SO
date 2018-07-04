package nc.pubitf.so.m4310.crm;

import java.io.Serializable;

import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * CRM查询参数对象
 * 
 * @since 6.3.1
 * @version 2013-08-06 08:44:04
 * @author 张云枫
 */
public class CRMQueryPara implements Serializable {

  private static final long serialVersionUID = -4178285724050894132L;

  /**
   * 客户ID
   */
  private String customerid;

  /**
   * 商机ID
   */
  private String busid;

  /**
   * 开始单据日期
   */
  private UFDate dfromdate;

  /**
   * 结束单据日期
   */
  private UFDate dtodate;

  /**
   * 开始条数
   */
  private int nstartcount;

  /**
   * 结束条数
   */
  private int nendcount;

  /**
   * 构造参数，给变量赋值
   * 
   * @param customerid 客户ID
   * @param busid 商机ID
   * @param dfromdate 开始日期
   * @param dtodate 结束日期
   * @param nstartcount 开始条数
   * @param nendcount 结束条数
   * 
   */
  public CRMQueryPara(String customerid, String busid, UFDate dfromdate,
      UFDate dtodate, int nstartcount, int nendcount) {
    this.customerid = customerid;
    this.busid = busid;
    this.dfromdate = dfromdate;
    this.dtodate = dtodate;
    this.nstartcount = nstartcount;
    this.nendcount = nendcount;

    this.validate();
  }

  /**
   * 获得客户ID
   * 
   * @return 客户ID
   */
  public String getCustomerid() {
    return this.customerid;
  }

  /**
   * 获得商机ID
   * 
   * @return 商机ID
   */
  public String getBusid() {
    return this.busid;
  }

  /**
   * 获得开始日期
   * 
   * @return 开始日期
   */
  public UFDate getDfromdate() {
    return this.dfromdate;
  }

  /**
   * 获得结束日期
   * 
   * @return 结束日期
   */
  public UFDate getDtodate() {
    return this.dtodate;
  }

  /**
   * 获得开始条数
   * 
   * @return 开始条数
   */
  public int getNstartcount() {
    return this.nstartcount;
  }

  /**
   * 获得结束条数
   * 
   * @return 结束条数
   */
  public int getNendcount() {
    return this.nendcount;
  }

  /**
   * 校验参数的准确性
   * 
   * @param CRM查询参数
   */
  private void validate() {

    if (PubAppTool.isNull(this.getDfromdate().toString())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0080")/* @res
                                                       * "查询开始日期不能为空 !" */);
    }
    else if (PubAppTool.isNull(this.getDtodate().toString())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0081")/* @res
                                                       * "查询结束日期不能为空!" */);
    }
    else if (this.getNstartcount() == 0) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0082")/* @res
                                                       * "查询开始条数不能小于1!" */);
    }
    else if (PubAppTool.isNull(this.getCustomerid())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0083")/* @res
                                                       * "客户ID不能为空!" */);
    }
    else if (this.getNstartcount() > this.getNendcount()) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0084")/* @res
                                                       * "结束条数不能小于开始条数!" */);
    }
    else if (this.getNendcount() - this.getNstartcount() > 499) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0085")/* @res
                                                       * "返回条数最多只能是500条!" */);
    }
    else if (this.getDtodate().before(this.getDfromdate())) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4006009_0", "04006009-0086")/* @res
                                                       * "结束日期不能早于开始日期!" */);
    }
  }
}
