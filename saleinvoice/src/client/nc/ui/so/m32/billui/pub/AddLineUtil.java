package nc.ui.so.m32.billui.pub;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IColumnMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.m32.entity.SaleInvoiceBVO;
import nc.vo.so.m32.entity.SaleInvoiceHVO;
import nc.vo.so.m32.entity.SaleInvoiceVO;

import nc.ui.ml.NCLangRes;

/**
 * 参照增行工具类（包含字段校验和合并）
 * 
 * @since 6.0
 * @version 2011-8-30 上午10:44:09
 * @author 么贵敬
 */
public class AddLineUtil {

  private final String[] checkkey = new String[] {
    SaleInvoiceHVO.PK_ORG, SaleInvoiceHVO.PK_ORG_V,
    SaleInvoiceHVO.CINVOICECUSTID, SaleInvoiceHVO.CORIGCURRENCYID,
    SaleInvoiceHVO.CCURRENCYID, SaleInvoiceHVO.CCUSTBANKACCID,
    SaleInvoiceHVO.CCUSTBANKACCID, SaleInvoiceHVO.CPAYTERMID,
    SaleInvoiceHVO.CTAXCOUNTRYID, SaleInvoiceHVO.CRECECOUNTRYID,
    SaleInvoiceHVO.CSENDCOUNTRYID, SaleInvoiceHVO.FBUYSELLFLAG,
    SaleInvoiceHVO.BTRIATRADEFLAG, SaleInvoiceHVO.CTRADEWORDID
  };

  /**
   * 合并编辑下校验是否允许参照增行
   * 
   * @param oldvo
   * @param newvos
   */
  public void checkCombinVO(SaleInvoiceVO oldvo, SaleInvoiceVO[] newvos) {
    SaleInvoiceHVO oldhvo = oldvo.getParentVO();
    for (SaleInvoiceVO newvo : newvos) {

      SaleInvoiceHVO newhvo = newvo.getParentVO();
      IBillMeta billmeta = oldvo.getMetaData();
      IVOMeta vometa = billmeta.getParent();
      List<String> errornames = new ArrayList<String>();
      for (String key : this.checkkey) {
        if (null == oldhvo.getAttributeValue(key)
            && null == newhvo.getAttributeValue(key)) {
          continue;
        }
        else if (null == oldhvo.getAttributeValue(key)) {
          IAttributeMeta attri = vometa.getAttribute(key);
          errornames.add(attri.getColumn().getLabel());
        }
        else {
          if (!oldhvo.getAttributeValue(key).equals(
              newhvo.getAttributeValue(key))) {
            IAttributeMeta attri = vometa.getAttribute(key);
            IColumnMeta column = attri.getColumn();
            errornames.add(column.getLabel());
          }
        }

      }
      if (errornames.size() > 0) {
        Set<String> vbillcodes = new HashSet<String>();
        SaleInvoiceBVO[] bvos = newvo.getChildrenVO();
        for (SaleInvoiceBVO bvo : bvos) {
          vbillcodes.add(bvo.getVsrccode());
        }
        StringBuffer buffer = new StringBuffer();
        for (String errorkey : errornames) {
          buffer.append(NCLangRes.getInstance().getStrByID("4006008_0", "04006008-0158", null, new String[]{errorkey})/*{0}、*/);
        }

        StringBuffer billcodebuffer = new StringBuffer();
        for (String billcode : vbillcodes) {
          billcodebuffer.append(billcode + ",");
        }

        String billcodes =
            NCLangRes.getInstance().getStrByID("4006008_0", "04006008-0159", null, new String[]{billcodebuffer.substring(0,billcodebuffer.length() - 1)})/*【{0}】*/;
        String keyNames = NCLangRes.getInstance().getStrByID("4006008_0", "04006008-0159", null, new String[]{buffer.substring(0,buffer.length() - 1)})/*【{0}】*/;
        ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
            .getStrByID("4006008_0", "04006008-0084", null, new String[] {
              billcodes, keyNames
            })/* 单据号：{0}以下字段：{1}不同不允许参照增行 */);
      }
      else {
        SaleInvoiceBVO[] oldbvos = oldvo.getChildrenVO();
        SaleInvoiceBVO[] newbvos = newvo.getChildrenVO();
        for (SaleInvoiceBVO bvo : newbvos) {
          String srcbid = bvo.getCsrcbid();
          for (SaleInvoiceBVO obvo : oldbvos) {
            // modify by zhangby5 少了判空
            if (!PubAppTool.isNull(obvo.getCsrcbid())
                && obvo.getCsrcbid().equals(srcbid)) {
              AbstractNCLangRes nclangres = NCLangRes4VoTransl.getNCLangRes();
              ExceptionUtils.wrappBusinessException(nclangres.getStrByID(
                  "4006008_0", "04006008-0009")/* @res "选择的数据已经在卡片上，参照增行失败" */);
            }
          }
        }
      }
    }
  }

  /**
   * 得到合并后的vo
   * 
   * @param oldvo
   * @param newvos
   * @return 合并后的vo
   */
  public SaleInvoiceVO getCombin(SaleInvoiceVO oldvo, SaleInvoiceVO[] newvos) {
    // SaleInvoiceHVO hvo = oldvo.getParentVO();
    List<SaleInvoiceBVO> bvos = new ArrayList<SaleInvoiceBVO>();
    for (SaleInvoiceBVO bvo : oldvo.getChildrenVO()) {
      bvos.add(bvo);
    }
    for (SaleInvoiceVO newvo : newvos) {
      for (SaleInvoiceBVO bvo : newvo.getChildrenVO()) {
        bvos.add(bvo);
      }
    }
    oldvo.setChildrenVO(bvos.toArray(new SaleInvoiceBVO[bvos.size()]));
    return oldvo;
  }

}
