package nc.bs.so.m33.maintain.m4c.rule.detail;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFieldsBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.so.m4331.SODeliveryServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m4331.so.m33.RewriteArnumPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 *              结算明细保存后：
 *              回写结算单累计确认应收数量
 *              回写销售订单累计确认应收数量、金额
 * @scene
 *        保存、取消结算明细
 * @param 无
 * @version 本版本号
 * @since 上一版本号
 * @author zhangcheng
 * @time 2010-5-28 上午10:55:46
 */
public class RewriteARIncomeFor4CRule implements IRule<SquareOutDetailVO> {

  @Override
  public void process(SquareOutDetailVO[] dvos) {
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREBID);
    Map<String, SquareOutViewVO> map =
        new QuerySquare4CVOBP().queryMapSquareOutViewVOByBID(sqbids);

    // 回写销售订单参数<30bid,Rewrite33Para>
    Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
    Rewrite33Para para30 = null;

    // 回写发货单参数<4331bid,RewriteArnumPara>
    Map<String, RewriteArnumPara> m4331para =
        new HashMap<String, RewriteArnumPara>();

    // 退回的红字出库被暂估，需要获取对应蓝字出库的来源 发货单<传应收出库单来源ID,传应收出库单的来源出库单来源发货单ID>
    Map<String, String> deliverymap = getOutSrcById(dvos, map);

    RewriteArnumPara para4331 = null;
    for (SquareOutDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareOutViewVO view = map.get(bid);
      UFDouble oldarnum = view.getItem().getNsquarearnum();
      UFDouble newarnum = dvo.getNsquarenum();
      UFDouble oldarmny = view.getItem().getNsquarearmny();
      UFDouble newarmny = dvo.getNorigtaxmny();

      // 回写销售出库待结算单
      view.getItem().setNsquarearnum(MathTool.add(oldarnum, newarnum));
      view.getItem().setNsquarearmny(MathTool.add(oldarmny, newarmny));
      if (MathTool.equals(view.getItem().getNnum(), view.getItem()
          .getNsquarearnum())) {
        view.getItem().setBsquarearfinish(UFBoolean.TRUE);
      }
      else {
        view.getItem().setBsquarearfinish(UFBoolean.FALSE);
      }

      // 回写发货单
      String vsrctype = view.getItem().getVsrctype();
      if (SOBillType.Delivery.getCode().equals(vsrctype)
          || ICBillType.SaleOut.getCode().equals(vsrctype)) {
        String delbid = view.getItem().getCsrcbid();
        String deliveryid = deliverymap.get(delbid);
        // 来源于发货单的退回出库单传应收
        if (!PubAppTool.isNull(deliveryid)) {
          delbid = deliveryid;
        }
        // 蓝字出库不来源于发单，退货出库单也不来源于发货单，因此不回写
        if ((deliverymap.size() > 0 && !PubAppTool.isNull(deliveryid))
            || SOBillType.Delivery.getCode().equals(vsrctype)) {
          para4331 = m4331para.get(delbid);
          if (null == para4331) {
            para4331 = new RewriteArnumPara(delbid, newarnum);
            m4331para.put(delbid, para4331);
          }
          else {
            UFDouble new4331num = MathTool.add(para4331.getArnum(), newarnum);
            para4331.setArnum(new4331num);
          }
        }
      }

      // 回写订单的参数
      String ordbid = view.getItem().getCfirstbid();
      para30 = m30para.get(ordbid);
      if (null == para30) {
        para30 = new Rewrite33Para(ordbid, newarnum, newarmny);
        m30para.put(ordbid, para30);
      }
      else {
        UFDouble new30num = MathTool.add(para30.getNarnum(), newarnum);
        UFDouble new30mny = MathTool.add(para30.getNarmny(), newarmny);
        para30.setNarnum(new30num);
        para30.setNarmny(new30mny);
      }
    }

    int size = map.values().size();
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareOutViewVO[size]));

    try {
      // 回写结算单累计确认应收数量
      new UpdateSquare4CFieldsBP().updateFields(sqvos, null, new String[] {
        SquareOutBVO.NSQUAREARNUM, SquareOutBVO.NSQUAREARMNY,
        SquareOutBVO.BSQUAREARFINISH
      });

      // 回写发货单
      size = m4331para.size();
      if (size > 0) {
        RewriteArnumPara[] paras =
            m4331para.values().toArray(new RewriteArnumPara[size]);
        SODeliveryServicesUtil.rewrite4331Arnum(paras);
      }

      // 回写销售订单累计确认应收数量、金额
      size = m30para.size();
      Rewrite33Para[] paras = m30para.values().toArray(new Rewrite33Para[size]);
      SOSaleOrderServicesUtil.rewrite30ARFor33(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

  private Map<String, String> getOutSrcById(SquareOutDetailVO[] dvos,
      Map<String, SquareOutViewVO> map) {
    Map<String, String> m4331bidmap = new HashMap<String, String>();
    Set<String> saleoutid = new HashSet<String>();
    for (SquareOutDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareOutViewVO view = map.get(bid);
      String vsrctype = view.getItem().getVsrctype();
      if (ICBillType.SaleOut.getCode().equals(vsrctype)) {
        String delbid = view.getItem().getCsrcbid();
        saleoutid.add(delbid);
      }
    }
    if (saleoutid.size() > 0) {
      m4331bidmap =
          new QuerySquare4CVOBP().queryOutSrcidBy4CBID(saleoutid
              .toArray(new String[0]));
    }
    return m4331bidmap;
  }

}
