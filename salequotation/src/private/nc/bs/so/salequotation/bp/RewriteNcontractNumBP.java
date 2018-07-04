package nc.bs.so.salequotation.bp;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.salequotation.entity.QuatationRewritePara;
import nc.vo.so.salequotation.entity.SalequoViewVO;
import nc.vo.so.salequotation.entity.SalequotationBVO;

public class RewriteNcontractNumBP {
  public void rewriteSaleContractNum(QuatationRewritePara[] vos) {
    Map<String, QuatationRewritePara> para = this.wrapPara(vos);
    SalequoViewVO[] views = this.queryViewVO(para);
    this.setRewriteNum(views, para);
    ViewUpdate<SalequoViewVO> updateBO = new ViewUpdate<SalequoViewVO>();
    updateBO.update(views, SalequotationBVO.class, new String[] {
      "ncontractnum"
    });
  }

  private SalequoViewVO[] queryViewVO(Map<String, QuatationRewritePara> para) {
    String[] ids = para.keySet().toArray(new String[0]);
    ViewQuery<SalequoViewVO> query =
        new ViewQuery<SalequoViewVO>(SalequoViewVO.class);
    query.setSharedHead(true);
    SalequoViewVO[] views = query.query(ids);
    if (views.length != para.size()) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4006009_0","04006009-0019")/*@res "出现并发，请重新查询销售报价单"*/;
      ExceptionUtils.wrappBusinessException(message);
    }
    return views;
  }

  private void setRewriteNum(SalequoViewVO[] views,
      Map<String, QuatationRewritePara> para) {
    for (SalequoViewVO view : views) {
      SalequotationBVO childrenVO =
          (SalequotationBVO) view.getVO(SalequotationBVO.class);
      QuatationRewritePara backVO = para.get(childrenVO.getPrimaryKey());
      UFDouble ncontractnum = childrenVO.getNcontractnum();
      UFDouble num = backVO.getNnum();
      childrenVO.setNcontractnum(MathTool.add(ncontractnum, num));
    }
  }

  private Map<String, QuatationRewritePara> wrapPara(QuatationRewritePara[] vos) {
    Map<String, QuatationRewritePara> map =
        new HashMap<String, QuatationRewritePara>();
    for (QuatationRewritePara vo : vos) {
      map.put(vo.getPk_salequobill_b(), vo);
    }
    return map;
  }
}