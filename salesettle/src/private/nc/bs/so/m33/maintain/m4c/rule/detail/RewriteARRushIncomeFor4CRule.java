package nc.bs.so.m33.maintain.m4c.rule.detail;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFieldsBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.so.m4331.SODeliveryServicesUtil;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m4331.so.m33.RewriteEstarnumPara;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m33.m4c.entity.SquareOutBVO;
import nc.vo.so.m33.m4c.entity.SquareOutDetailVO;
import nc.vo.so.m33.m4c.entity.SquareOutVO;
import nc.vo.so.m33.m4c.entity.SquareOutVOUtils;
import nc.vo.so.m33.m4c.entity.SquareOutViewVO;
import nc.vo.so.pub.util.SOVOChecker;
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 销售出库单取消结算明细回写累计应收结算数据
 * @scene
 * 取消结算明细、取消回冲应收单 、出库对冲传回冲应收
 * @param 
 * 无
 */
public class RewriteARRushIncomeFor4CRule implements IRule<SquareOutDetailVO> {

  @Override
  public void process(SquareOutDetailVO[] dvos) {
    String[] sqbids =
        SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREBID);
    Map<String, SquareOutViewVO> map =
        new QuerySquare4CVOBP().queryMapSquareOutViewVOByBID(sqbids);

    // 回写销售订单参数<30bid,Rewrite33Para>
    Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
    Rewrite33Para para30 = null;

    // 查询签收退回的销售出库单上游的来源于发货单的蓝字销售出库单<蓝字出库bid,4331bid>
    Map<String, String> m4c4331bid =
        new QuerySquare4CVOBP().query4C4331bid(map);

    // 回写发货单参数<4331bid,RewriteEstarnumPara>
    Map<String, RewriteEstarnumPara> m4331para =
        new HashMap<String, RewriteEstarnumPara>();
    RewriteEstarnumPara para4331 = null;

    for (SquareOutDetailVO dvo : dvos) {
      String bid = dvo.getCsalesquarebid();
      SquareOutViewVO view = map.get(bid);
      UFDouble oldvalue = view.getItem().getNarrushnum();
      UFDouble newarnum = dvo.getNsquarenum();

      view.getItem().setNarrushnum(MathTool.add(oldvalue, newarnum));
      // 回写订单的参数
      String ordbid = view.getItem().getCfirstbid();
      para30 = m30para.get(ordbid);
      if (null == para30) {
        para30 = new Rewrite33Para(ordbid, newarnum);
        m30para.put(ordbid, para30);
      }
      else {
        newarnum = MathTool.add(para30.getNarnum(), newarnum);
        para30.setNarnum(newarnum);
      }

      // 回写发货单-蓝字销售出库单上游是发货单
      String vsrctype = view.getItem().getVsrctype();
      if (SOBillType.Delivery.getCode().equals(vsrctype)) {
        String delbid = view.getItem().getCsrcbid();
        para4331 = m4331para.get(delbid);
        if (null == para4331) {
          para4331 = new RewriteEstarnumPara(delbid, newarnum);
          m4331para.put(delbid, para4331);
        }
        else {
          UFDouble new4331num = MathTool.add(para4331.getEstarnum(), newarnum);
          para4331.setEstarnum(new4331num);
        }
      }
      // 签收退回的出库单上游蓝字销售出库单的上游是发货单
      else if (m4c4331bid.size() > 0) {
        String outbid = view.getItem().getCsrcbid();
        String delbid = m4c4331bid.get(outbid);
        if (!SOVOChecker.isEmpty(delbid)) {
          para4331 = m4331para.get(delbid);
          if (null == para4331) {
            para4331 = new RewriteEstarnumPara(delbid, newarnum);
            m4331para.put(delbid, para4331);
          }
          else {
            UFDouble new4331num =
                MathTool.add(para4331.getEstarnum(), newarnum);
            para4331.setEstarnum(new4331num);
          }
        }
      }

    }

    // 回写结算单累计回冲应收数量
    int size = map.values().size();
    SquareOutVO[] sqvos =
        SquareOutVOUtils.getInstance().combineBill(
            map.values().toArray(new SquareOutViewVO[size]));
    new UpdateSquare4CFieldsBP().updateFields(sqvos, null, new String[] {
      SquareOutBVO.NARRUSHNUM
    });

    try {
      // 回写发货单累计暂估应收数量(注：发货单的累计暂估数量=累计暂估+累计回冲，都是给信用用的)
      size = m4331para.size();
      if (size > 0) {
        RewriteEstarnumPara[] paras =
            m4331para.values().toArray(new RewriteEstarnumPara[size]);
        SODeliveryServicesUtil.rewrite4331Estarnum(paras);
      }

      // 回写销售订单累计暂估应收数量(注：销售订单的累计暂估数量=累计暂估+累计回冲，都是给信用用的)
      size = m30para.values().size();
      SOSaleOrderServicesUtil.rewrite30ETFor33(m30para.values().toArray(
          new Rewrite33Para[size]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

  }

}
