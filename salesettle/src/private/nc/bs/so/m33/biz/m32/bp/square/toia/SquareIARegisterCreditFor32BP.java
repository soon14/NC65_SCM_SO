package nc.bs.so.m33.biz.m32.bp.square.toia;

import nc.bs.so.m33.biz.m32.rule.toia.SquareIACloseFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toia.ToIABizFor32Rule;
import nc.bs.so.m33.biz.m32.rule.toia.ToIACheckFor32Rule;
import nc.bs.so.m33.maintain.m32.InsertSquare32DetailBP;
import nc.bs.so.m33.maintain.m32.rule.detail.RewriteIARegsiterFor32Rule;
import nc.bs.so.m33.plugin.BPPlugInPoint;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.itf.so.m33.ref.ia.mi5.IAI5For32ServicesUtil;
import nc.itf.so.m33.ref.pcia.m4635.PCIA4635For32ServicesUtil;
import nc.vo.ia.mi5.entity.I5BillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m32.entity.SquareInvDetailVO;
import nc.vo.so.m33.m32.entity.SquareInvVO;
import nc.vo.so.m33.m32.entity.SquareInvVOUtils;

public class SquareIARegisterCreditFor32BP {

  public void square(SquareInvVO[] sqvos) {
    try {

      AroundProcesser<SquareInvVO> processer =
          new AroundProcesser<SquareInvVO>(
              BPPlugInPoint.SquareToIARegisterCreditBy32);

      // 增加执行前业务规则
      this.addBeforeRule(processer);

      // 增加执行后业务规则
      this.addAfterRule(processer);

      // 销售发票传发出商品前执行业务规则
      processer.before(sqvos);

      // 将传发出商品结算单VO转化为传发出商品结算明细VO
      SquareInvDetailVO[] bills =
          SquareInvVOUtils.getInstance().changeSQVOtoSQDVOForREG(sqvos);

      // 发出商品结算明细保存BP
      this.saveDetail(sqvos, bills);

      // 进行vo交换
      I5BillVO[] i5vos = new VOChange4332ToI5Util().exchange(sqvos);

      // 调用I5的销售发票发出商品结算接口
      IAI5For32ServicesUtil.insertI5ForSO32Intransit(i5vos);
      
      // 调用销售发票普通结算到利润中心存货保存接口 add by zhangby5 for 65
      PCIA4635For32ServicesUtil.insert4635ForSO32Settle(sqvos);

      processer.after(sqvos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void addAfterRule(AroundProcesser<SquareInvVO> processer) {
    IRule<SquareInvVO> rule = new SquareIACloseFor32Rule();
    processer.addAfterRule(rule);
  }

  private void addBeforeRule(AroundProcesser<SquareInvVO> processer) {
    // 检查成本域
    IRule<SquareInvVO> rule = new ToIACheckFor32Rule();
    processer.addBeforeRule(rule);

    // 传存货核算数据业务处理
    rule = new ToIABizFor32Rule();
    processer.addBeforeRule(rule);
  }

  private void saveDetail(SquareInvVO[] sqvos, SquareInvDetailVO[] bills) {
    AroundProcesser<SquareInvDetailVO> processer =
        new AroundProcesser<SquareInvDetailVO>(
            BPPlugInPoint.SquareToIARegisterCreditBy32Detail);

    // 发出商品结算明细保存BP
    new InsertSquare32DetailBP().insert(sqvos, bills);

    // 回写累计成本结算数据
    IRule<SquareInvDetailVO> rule = new RewriteIARegsiterFor32Rule();
    processer.addAfterRule(rule);
    processer.after(bills);
  }

}
