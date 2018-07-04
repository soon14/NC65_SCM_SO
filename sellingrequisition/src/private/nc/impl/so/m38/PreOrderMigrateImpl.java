package nc.impl.so.m38;

import java.util.HashMap;
import java.util.Map;

import nc.bs.businessevent.BusinessEvent;
import nc.bs.businessevent.EventDispatcher;
import nc.bs.businessevent.IEventType;
import nc.impl.so.m38.migrate.action.BillItfDefMigrateAction;
import nc.impl.so.m38.migrate.action.PreOrderAfterMigNodeAction;
import nc.impl.so.m38.migrate.action.PreOrderDataMigrateAction;
import nc.impl.so.m38.migrate.action.PreOrderTranTypeMigrateAction;
import nc.impl.so.m38.migrate.action.UpdatePreOrderMigStateAction;
import nc.impl.so.m38.migrate.rule.PreOrderMigBeforeRule;
import nc.itf.so.m38.IPreOrderMigrate;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @since 6.36
 * @author liylr
 * @version 2015-05-22
 */
public class PreOrderMigrateImpl implements IPreOrderMigrate {

  @Override
  public void migratePreOrder() {
    try {

      /** 销售预订单的元数据ID */
      String voMetaDataID = "575c639d-8dcb-4692-b151-c91f38525c70";

      /*
       * 预订单迁移，迁移的数据内容包括交易类型，单据接口定义，预订单数据，下游单据四个部分。其中交易类型信息存在于交易类型与bd_billtype
       * 这两个表中，单据接口定义引用bd_billtype表，预订单数据表引用交易类型表数据，下游单据也引用交易类型id
       * 由于bd_billtype为销售预订单以及EC预订单共用的表，所以将销售预订单的bd_billtype表中的数据迁移到电子销售模块时，需要重新
       * 生成新的bd_billtype数据
       * （即需要新的id）,那么迁移前后的id映射关系将存储于oldNewTrantypeIdMap之中。该映射关系将会在
       * 预订单交易类型迁移、单据数据迁移、下游单据引用的上游单据的交易类型id更新等三个地方用到。其映射关系（bd_billtype表中交易类型
       * 迁移前与迁移后的pk_billtypeid映射关系
       * ）以Map的对象的方式存在：<迁移前pk_billtypeid，迁移后pk_billtypeid>
       */
      Map<String, String> oldNewTrantypeIdMap = new HashMap<String, String>();

      // 1:数据准确性校验
      PreOrderMigBeforeRule rule = new PreOrderMigBeforeRule();
      rule.check();

      // 2.在开始迁移前发出迁移前事件
      EventDispatcher.fireEvent(new BusinessEvent(voMetaDataID,
          IEventType.TYPE_UPGRADE_BEFORE, null));

      // 3.预订单交易类型迁移
      PreOrderTranTypeMigrateAction pottmAction =
          new PreOrderTranTypeMigrateAction();
      pottmAction.migrate(oldNewTrantypeIdMap);

      // 4.单据接口定义迁移
      BillItfDefMigrateAction bidmAction = new BillItfDefMigrateAction();
      bidmAction.migrate();

      // 5.单据迁移
      PreOrderDataMigrateAction migAction = new PreOrderDataMigrateAction();
      migAction.migrate(oldNewTrantypeIdMap);

      // 6.删除预订单相关
      PreOrderAfterMigNodeAction afterAction = new PreOrderAfterMigNodeAction();
      afterAction.process();

      // 7.记录迁移标记
      UpdatePreOrderMigStateAction update = new UpdatePreOrderMigStateAction();
      update.update();
      
      // 8.对其它模块的影响, 升级结束以后发出升级后事件
      EventDispatcher.fireEvent(new BusinessEvent(voMetaDataID,
          IEventType.TYPE_UPGRADE_AFTER, oldNewTrantypeIdMap));
    }catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }
}
