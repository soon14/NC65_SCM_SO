package nc.pf.so.function;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ServerBillCombinClient;
import nc.vo.pubapp.pattern.model.transfer.bill.ServerBillToClient;
import nc.vo.pubapp.pattern.pub.Constructor;

public class SaleOrderFuncTransferTool<E extends IBill> {

  /**
   * 客户算传来的单据
   */
  private E[] clientBills;

  /**
   * 数据库中存在的原始单据
   */
  private E[] originBills;

  /**
   * 是否需要校验时间戳
   */
  private UFBoolean nocheckts;

  /**
   * 前后台单据传输工具构造函数
   * */
  public SaleOrderFuncTransferTool(E[] bills, UFBoolean nocheckts) {
    this.nocheckts = nocheckts;
    // 为了防止网络中断导致单据重复增加，此处新增单据的主键可能已经设置
    if ((bills[0].getPrimaryKey() == null)
        || (bills[0].getParent().getStatus() == VOStatus.NEW)) {
      this.initInserted(bills);
    }
    else {
      this.initUpdateed(bills);
    }

  }

  /**
   * 与前台单据实体作比较，获取需要传递到前台的单据实体快照
   * 
   * @param bills 完整的单据实体
   * @return 需要传递到前台的单据实体快照
   */
  @SuppressWarnings({
    "rawtypes", "unchecked"
  })
  public E[] getBillForToClient(E[] bills) {
    ServerBillToClient clientTransfer = new ServerBillToClient();
    E[] vos = (E[]) clientTransfer.construct(this.clientBills, bills);

    return vos;
  }

  /**
   * 获取前台的完整单据实体
   * 
   * @return 前台的完整单据实体
   */
  @SuppressWarnings("unchecked")
  public E[] getClientFullInfoBill() {
    // 克隆是为了方便返回前台时知道后台保存中又改变了什么字段的值
    int length = this.clientBills.length;
    E[] bills =
        (E[]) Constructor.construct(this.clientBills[0].getClass(), length);
    for (int i = 0; i < length; i++) {
      bills[i] = (E) this.clientBills[i].clone();
    }
    return bills;
  }

  /**
   * 获取数据库中存放的完整原始单据实体
   * 
   * @return 数据库中存放的完整原始单据实体
   */
  public E[] getOriginBills() {
    return this.originBills;
  }

  @SuppressWarnings("unchecked")
  private void initInserted(E[] bills) {
    int size = bills.length;
    E[] vos = (E[]) Constructor.construct(bills[0].getClass(), size);
    for (int i = 0; i < size; i++) {
      vos[i] = (E) bills[i].clone();
    }
    this.originBills = vos;
    this.clientBills = vos;
  }

  @SuppressWarnings({
    "unchecked", "rawtypes"
  })
  private void initUpdateed(E[] bills) {
    BillConcurrentTool tool = new BillConcurrentTool();
    TimeLog.logStart();
    tool.lockBill(bills);
    TimeLog.info("锁定表头、表体主健"); /*-=notranslate=-*/

    TimeLog.logStart();
    String[] ids = new String[bills.length];
    int length = bills.length;
    for (int i = 0; i < length; i++) {
      ids[i] = bills[i].getPrimaryKey();
    }
    TimeLog.info("获取单据主健"); /*-=notranslate=-*/

    TimeLog.logStart();
    BillQuery query = new BillQuery(bills[0].getClass());
    this.originBills = (E[]) query.query(ids);
    TimeLog.info("查询原始单据VO"); /*-=notranslate=-*/

    TimeLog.logStart();
    length = this.originBills.length;
    E[] vos = (E[]) Constructor.construct(bills[0].getClass(), length);
    for (int i = 0; i < length; i++) {
      vos[i] = (E) this.originBills[i].clone();
    }
    ServerBillCombinClient<E> combineClient = new ServerBillCombinClient<E>();
    combineClient.combine(vos, bills);
    this.clientBills = vos;
    TimeLog.info("前台单据VO补充完整"); /*-=notranslate=-*/

    // 如果不检查时间戳
    if (null != this.nocheckts && this.nocheckts.booleanValue()) {
      return;
    }
    TimeLog.logStart();
    tool.checkTS(bills, this.originBills);
    TimeLog.info("检查时间戳"); /*-=notranslate=-*/
  }

}
