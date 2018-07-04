package nc.pubimpl.so.pfxx;

import java.util.List;

import nc.vo.pfxx.auxiliary.AggxsysregisterVO;
import nc.vo.pfxx.util.PfxxPluginUtils;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import nc.bs.pfxx.ISwapContext;
import nc.bs.pfxx.plugin.AbstractPfxxPlugin;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * 外部平台导入销售管理公共基类
 * 
 * @since 6.0
 * @version 2012-2-21 下午03:31:13
 * @author 么贵敬
 */
public abstract class AbstractSOPfxxPlugin extends AbstractPfxxPlugin {

  /**
   * 外部导入校验器集合
   */
  private List<IRule<AggregatedValueObject>> checkers;

  public List<IRule<AggregatedValueObject>> getCheckers() {
    return this.checkers;
  }

  /**
   * 将由XML转换过来的VO导入NC系统。业务插件实现此方法即可。<br>
   * 请注意，业务方法的校验一定要充分
   * 
   * @param vo
   *          转换后的vo数据，在NC系统中可能为ValueObject,SuperVO,AggregatedValueObject,
   *          IExAggVO等。
   * @param swapContext
   *          各种交换参数，组织，接受方，发送方，帐套等等
   * @param aggxsysvo
   *          辅助信息vo
   * @return 操作后返回的vo
   * @throws BusinessException
   */
  @Override
  protected Object processBill(Object vo, ISwapContext swapContext,
      AggxsysregisterVO aggxsysvo) throws BusinessException {

    // 1.得到转换后的VO数据,取决于向导第一步注册的VO信息
    AggregatedValueObject resvo = (AggregatedValueObject) vo;

    // 2.查询此单据是否已经被导入过，有两个方法，具体使用哪一个请参考方法说明javadoc
    String vopk =
        PfxxPluginUtils.queryBillPKBeforeSaveOrUpdate(
            swapContext.getBilltype(), swapContext.getDocID());

    // 3. 如果单据设置有辅助信息，aggxsysvo为用户配置的具体辅助信息

    // 4.如果此单据没有导入过，那么准备保存新单据，保存单据前请进行必要的数据检查，并给出明确的业务异常...
    AggregatedValueObject returnVO = null;

    // 单据数据检查逻辑
    this.checkBill(resvo);

    if (PubAppTool.isNull(vopk)) {
      resvo.getParentVO().setStatus(VOStatus.NEW);
      for (CircularlyAccessibleValueObject bvo : resvo.getChildrenVO()) {
        bvo.setStatus(VOStatus.NEW);
      }
      returnVO = this.insert(resvo);
    }
    else {
      // 5.如果此单据已经导入过，请调用PfxxPluginUtils.checkBillCanBeUpdate(UfinterfaceVO
      // swapContext)检查单据是否允许更新
      // 如果不允许更新,此方法会抛出业务异常
      // PfxxPluginUtils.checkBillCanBeUpdate(swapContext);
      returnVO = this.update(resvo, vopk);
    }
    vopk = returnVO.getParentVO().getPrimaryKey();
    // 6.如果希望单据将来可以更新，请调用下列接口插入文档流水号与生成PK的对照关系
    PfxxPluginUtils.addDocIDVsPKContrast(swapContext.getBilltype(),
        swapContext.getDocID(), vopk);

    // 7.准备返回值,此函数的返回值，最终会以字符串的形式返回给外系统，
    // 对于普通单据可以返回NC系统生成的PK值，对于凭证可能返回凭证号，具体视单据而定
    // 对于查询插件要求返回org.w3c.dom.Node[]数组 或者org.w3c.dom.Node
    return vopk;
  }

  /**
   * 新增操作，各个单据自己处理
   * 
   * @param vo
   * @return 保存后的vo
   */
  protected abstract AggregatedValueObject insert(AggregatedValueObject vo);

  /**
   * 更新操作，各个单据自己处理
   * 
   * @param vo
   * @return 更新后的vo
   */
  protected abstract AggregatedValueObject update(AggregatedValueObject vo,
      String vopk);

  /**
   * 单据检查
   * 
   * @param vo
   */
  private void checkBill(AggregatedValueObject vo) {
    if (this.getCheckers() != null) {
      for (IRule<AggregatedValueObject> checker : this.getCheckers()) {
        checker.process(new AggregatedValueObject[] {
          vo
        });
      }
    }
  }

}
