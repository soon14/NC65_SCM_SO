package nc.bs.so.m33.maintain.m4c.rule.detail;

import java.util.HashMap;
import java.util.Map;

import nc.bs.so.m33.maintain.m4c.UpdateSquare4CFieldsBP;
import nc.bs.so.m33.maintain.m4c.query.QuerySquare4CVOBP;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.so.m33.ref.ic.m4c.ICM4CServiceUtil;
import nc.itf.so.m33.ref.so.m30.SOSaleOrderServicesUtil;
import nc.itf.so.m33.ref.so.m4331.SODeliveryServicesUtil;
import nc.pubitf.ic.m4c.m33.Parameter4CFor33;
import nc.pubitf.so.m30.so.m33.Rewrite33Para;
import nc.pubitf.so.m4331.so.m33.RewriteRushNumPara;
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
import nc.vo.so.pub.votools.SoVoTools;

/**
 * @description
 * 出库对冲回写累计发出商品数据
 * @scene
 * 销售出库单出库对冲、取消出库对冲
 * @param 
 * 无
 */
public class RewriteOutRush4CRule implements IRule<SquareOutDetailVO> {

  @Override
  public void process(SquareOutDetailVO[] dvos) {
    try {
      String[] sqbids =
          SoVoTools.getVOsOnlyValues(dvos, SquareOutDetailVO.CSALESQUAREBID);
      Map<String, SquareOutViewVO> map =
          new QuerySquare4CVOBP().queryMapSquareOutViewVOByBID(sqbids);

      // 回写销售订单参数<30bid,Rewrite33Para>
      Map<String, Rewrite33Para> m30para = new HashMap<String, Rewrite33Para>();
      Rewrite33Para para30 = null;

      // 回写发货单参数<4331bid,RewriteRushNumPara>
      Map<String, RewriteRushNumPara> m4331para =
          new HashMap<String, RewriteRushNumPara>();
      RewriteRushNumPara para4331 = null;

      // 回写销售出库单参数<4cbid,Parameter4CFor33>
      Map<String, Parameter4CFor33> m4cpara =
          new HashMap<String, Parameter4CFor33>();
      Parameter4CFor33 para4c = null;

      for (SquareOutDetailVO dvo : dvos) {
        String bid = dvo.getCsalesquarebid();
        SquareOutViewVO view = map.get(bid);
        SquareOutBVO bvo = view.getItem();
        UFDouble oldvalue = bvo.getNrushnum();
        UFDouble newvalue = dvo.getNsquarenum();
        bvo.setNrushnum(MathTool.add(oldvalue, newvalue));
        // 回写订单的参数
        String ordbid = bvo.getCfirstbid();
        para30 = m30para.get(ordbid);
        if (null == para30) {
          para30 = new Rewrite33Para(ordbid, newvalue);
          m30para.put(ordbid, para30);
        }
        else {
          UFDouble new30value = MathTool.add(para30.getNarnum(), newvalue);
          para30.setNarnum(new30value);
        }

        // 回写发货单
        String vsrctype = view.getItem().getVsrctype();
        if (SOBillType.Delivery.getCode().equals(vsrctype)) {
          String delbid = view.getItem().getCsrcbid();
          para4331 = m4331para.get(delbid);
          if (null == para4331) {
            para4331 = new RewriteRushNumPara(delbid, newvalue);
            m4331para.put(delbid, para4331);
          }
          else {
            UFDouble new4331num = MathTool.add(para4331.getRushnum(), newvalue);
            para4331.setRushnum(new4331num);
          }
        }

        // 回写销售出库单的参数
        String outhid = bvo.getCsquarebillid();
        String outbid = bvo.getCsquarebillbid();
        para4c = m4cpara.get(outbid);
        if (null == para4c) {
          para4c = new Parameter4CFor33(outhid, outbid);
          para4c.setNrushnum(newvalue);
          m4cpara.put(outbid, para4c);
        }
        else {
          UFDouble new4cvalue = MathTool.add(para4c.getNrushnum(), newvalue);
          para4c.setNrushnum(new4cvalue);
        }
      }

      int size = map.values().size();
      SquareOutVO[] sqvos =
          SquareOutVOUtils.getInstance().combineBill(
              map.values().toArray(new SquareOutViewVO[size]));

      // 回写结算单累计出库对冲数量
      new UpdateSquare4CFieldsBP().updateFields(sqvos, null, new String[] {
        SquareOutBVO.NRUSHNUM
      });

      // 回写销售出库单累计出库对冲数量
      size = m4cpara.values().size();
      ICM4CServiceUtil.rewrite4CRushFor33(m4cpara.values().toArray(
          new Parameter4CFor33[size]));

      // 回写发货单
      size = m4331para.size();
      if (size > 0) {
        RewriteRushNumPara[] paras =
            m4331para.values().toArray(new RewriteRushNumPara[size]);
        SODeliveryServicesUtil.rewrite4331RushNum(paras);
      }

      // 回写销售订单累计出库对冲数量
      size = m30para.size();
      Rewrite33Para[] paras = m30para.values().toArray(new Rewrite33Para[size]);
      SOSaleOrderServicesUtil.rewrite30OutRushFor33(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappBusinessException(e.getMessage());
    }
  }

}
