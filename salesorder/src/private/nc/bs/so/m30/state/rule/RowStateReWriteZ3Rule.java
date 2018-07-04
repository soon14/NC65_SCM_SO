package nc.bs.so.m30.state.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.ct.entity.CtWriteBackForOrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderViewVO;
import nc.vo.so.pub.util.SOSysParaInitUtil;

import nc.pubitf.ct.saledaily.so.IReWriteZ3For30;
import nc.pubitf.so.m30.ic.m4c.Rewrite4CPara;

import nc.bs.framework.common.NCLocator;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 * 销售订单行操作(关闭、打开)回写销售合同rule
 * @scene
 * 销售订单行操作(关闭、打开)后
 * @param
 * bIsClose 是否行关闭
 * mParas 出库变化量参数
 * @since 6.0
 * @version 2011-6-20 上午09:49:43
 * @author 么 贵 敬
 */
public class RowStateReWriteZ3Rule implements IRule<SaleOrderViewVO> {

  private boolean bIsClose;

  private Map<String, Rewrite4CPara> mParas;

  /**
   * @param bIsClose 是否行关闭
   */
  public RowStateReWriteZ3Rule(boolean bIsClose) {
    this.bIsClose = bIsClose;
    Map<String, Rewrite4CPara> mParass =
        (Map<String, Rewrite4CPara>) BSContext.getInstance().getSession(
            Rewrite4CPara.class.getName());
    if (mParass != null) {
      this.mParas = mParass;
    }
  }

  /**
   * 构造器for出库回写销售订单时
   * 
   * @param bIsClose 是否行关闭
   * @param mParas 出库变化量参数
   */
  public RowStateReWriteZ3Rule(boolean bIsClose,
      Map<String, Rewrite4CPara> mParas) {
    this.bIsClose = bIsClose;
    this.mParas = mParas;
  }

  @Override
  public void process(SaleOrderViewVO[] vos) {
    this.fillCtType(vos);
    Map<String, UFBoolean> so10map = new HashMap<String, UFBoolean>();
    List<CtWriteBackForOrderVO> paralist =
        new ArrayList<CtWriteBackForOrderVO>();
    for (SaleOrderViewVO viewvo : vos) {
      SaleOrderBVO bvo = viewvo.getBody();
      SaleOrderHVO hvo = viewvo.getHead();
      if (CTBillType.SaleDaily.getCode().equals(bvo.getVcttype())) {
        String pk_org = bvo.getPk_org();
        UFBoolean so10 = so10map.get(pk_org);
        if (null == so10) {
          so10 = this.getSO10(pk_org);
          so10map.put(pk_org, so10);
        }
        if (so10.booleanValue()) {
          CtWriteBackForOrderVO paras = new CtWriteBackForOrderVO();
          // paras.setPk_ctpu(bvo.getCsrcid());
          // paras.setPk_ctpu_b(bvo.getCsrcbid());
          // 2013-04-26 tianft：如果销售订单一行来源销售合同，一行关联销售合同，用srcid是不对的，要用manageid
          paras.setPk_ctpu(bvo.getCctmanageid());
          paras.setPk_ctpu_b(bvo.getCctmanagebid());
          paras.setcRowNum(bvo.getCrowno());
          if (this.bIsClose) {
            // 行关闭且为出库回写销售订单时
            if (this.mParas != null) {
              Rewrite4CPara m4CPara = this.mParas.get(bvo.getCsaleorderbid());
              UFDouble changenum = m4CPara.getNchangenum();
              paras.setNum(changenum);
            }
            // 行关闭时
            else {
              paras.setNum(MathTool.sub(bvo.getNtotaloutnum(), bvo.getNnum()));
            }
          }
          // 行打开时
          else {
            // 行打开且为出库回写销售订单时
            if (this.mParas != null) {
              Rewrite4CPara m4CPara = this.mParas.get(bvo.getCsaleorderbid());
              UFDouble changenum = m4CPara.getNchangenum();
              paras.setNum(changenum);
            }
            else {
              paras.setNum(MathTool.sub(bvo.getNnum(), bvo.getNtotaloutnum()));
            }
          }
          paras.setPrice(bvo.getNorigtaxprice());

          UFDouble norigtaxmny =
              bvo.getNorigtaxnetprice().multiply(paras.getNum());
          ScaleUtils scale = new ScaleUtils(pk_org);
          UFDouble norigtaxmnyAfterSacle =
              scale.adjustMnyScale(norigtaxmny, hvo.getCorigcurrencyid());
          paras.setPriceNum(norigtaxmnyAfterSacle);
          paralist.add(paras);
        }
      }
    }
    if (paralist.size() > 0) {
      CtWriteBackForOrderVO[] paras =
          paralist.toArray(new CtWriteBackForOrderVO[paralist.size()]);

      IReWriteZ3For30 api =
          NCLocator.getInstance().lookup(IReWriteZ3For30.class);
      try {
        api.rewriteBackZ3For30(paras);
      }
      catch (BusinessException ex) {
        ExceptionUtils.wrappException(ex);
      }
    }
  }

  private void fillCtType(SaleOrderViewVO[] vos) {
    for (SaleOrderViewVO vo : vos) {
      SaleOrderBVO body = vo.getBody();
      if (body.getCctmanagebid() != null
          && body.getCctmanagebid().trim().length() > 0) {
        body.setVcttype(CTBillType.SaleDaily.getCode());
      }

    }
  }

  private UFBoolean getSO10(String pk_org) {
    UFBoolean ret = UFBoolean.FALSE;

    ret = SOSysParaInitUtil.getSO10(pk_org);

    return ret;
  }
}
