package nc.impl.so.custmatrel.action;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.data.bill.tool.BillConcurrentTool;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.log.TimeLog;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.transfer.bill.ServerBillCombinClient;
import nc.vo.pubapp.pattern.model.transfer.bill.ServerBillToClient;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;
import nc.vo.so.custmatrel.entity.CustMatRelHVO;
import nc.vo.so.custmatrel.entity.CustMatRelVO;

/**
 * 前台界面和后台交互时的单据信息VO的传递处理
 * 
 * @param <E> 单据类型
 * @since 6.0
 * @version 2009-5-19 下午01:22:51
 * @author 钟鸣
 */
public class BillTransferTool<E extends IBill> {

  /**
   * 客户算传来的单据
   */
  private E[] clientBills;

  /**
   * 数据库中存在的原始单据
   */
  private E[] originBills;
  
  /**
   * 前后台单据传输工具构造函数
   * 
   * @param bills 单据实体
   */
  public BillTransferTool(E[] bills) {
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
   * 获取前台的完整单据实体。
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
   * 获取数据库中存放的完整原始单据实体。
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

  /**
   * 补全前台传进来的单据。
   * 
   * @param bills
   */
@SuppressWarnings({
    "unchecked", "rawtypes"
  })
	private void initUpdateed(E[] bills) {
		BillConcurrentTool tool = new BillConcurrentTool();
		TimeLog.logStart();
		tool.lockBill(bills);
		TimeLog.info("锁定表头、表体主健"); /* -=notranslate=- */

		TimeLog.logStart();
		String[] ids = new String[bills.length];
		int length = bills.length;
		List<String> bids = getPrimaryKey(bills, ids, length);
		TimeLog.info("获取单据主健"); /* -=notranslate=- */

		TimeLog.logStart();
		VOQuery query = new VOQuery(bills[0].getParent().getClass());
		CustMatRelHVO[] queryVO = (CustMatRelHVO[]) query.query(ids);
		CustMatRelBVO[] bvos = getCustMatRelBVO(bids);
		// 聚合vo
		CustMatRelVO[] custMatRelVOs = buildCustMatRelVO(queryVO, bvos);
		originBills = (E[]) custMatRelVOs;
		TimeLog.info("查询原始单据VO"); /* -=notranslate=- */

		TimeLog.logStart();
		length = this.originBills.length;
		E[] vos = (E[]) Constructor.construct(bills[0].getClass(), length);
		for (int i = 0; i < length; i++) {
			vos[i] = (E) this.originBills[i].clone();
		}
		ServerBillCombinClient<E> combineClient = new ServerBillCombinClient<E>();
		combineClient.combine(vos, bills);
		this.clientBills = vos;
		TimeLog.info("前台单据VO补充完整"); /* -=notranslate=- */  

		TimeLog.logStart();
		tool.checkTS(bills, this.originBills);
		TimeLog.info("检查时间戳"); /* -=notranslate=- */
	}

	/**
	 * 
	 * 分别获取表头VO表体VO根据vo主键来确定同一单据并聚合。
	 * 
	 * @param queryVO
	 * @param bvos  
	 * @return
	 */
	private CustMatRelVO[] buildCustMatRelVO(CustMatRelHVO[] queryVO,
			CustMatRelBVO[] bvos) {
		int leng = queryVO.length;
		CustMatRelVO[] custMatRelVOs = new CustMatRelVO[leng];
		for (int i = 0; i < leng; i++) {
			CustMatRelHVO hvo = queryVO[i];
			String hid = hvo.getPrimaryKey();
			CustMatRelVO aggVO = new CustMatRelVO();
			List<CustMatRelBVO> custMatRelBVOs = new ArrayList<CustMatRelBVO>();

			if (bvos == null) {
				aggVO.setParentVO(hvo);
				aggVO.setChildrenVO(null);
				custMatRelVOs[i] = aggVO;
				continue;
			}
			for (CustMatRelBVO bvo : bvos) {
				String bid = bvo.getPk_custmatrel();
				if (PubAppTool.isEqual(hid, bid)) {
					custMatRelBVOs.add(bvo);
				}
			}
			// 聚合vo
			aggVO.setParentVO(hvo);
			aggVO.setChildrenVO(custMatRelBVOs
					.toArray(new CustMatRelBVO[custMatRelBVOs.size()]));
			custMatRelVOs[i] = aggVO;
		}
		return custMatRelVOs;
	}

	/**
	 * 
	 * 根据表体PK来获得表体VO。
	 * 
	 * @param bids
	 * @return
	 */
	private CustMatRelBVO[] getCustMatRelBVO(List<String> bids) {
		VOQuery queryBVO = new VOQuery(CustMatRelBVO.class);
		CustMatRelBVO[] bvos = null;
		// 由于新增的表体是没有保存在数据库中，故没有主键
		if (bids.size() > 0) {
			bvos = (CustMatRelBVO[]) queryBVO.query(bids
					.toArray(new String[bids.size()]));
		}
		return bvos;
	}

	/**
	 * 
	 * 获取获得表体，表头VO主键。
	 * 
	 * @param bills
	 * @param ids
	 * @param length
	 * @return
	 */
	private List<String> getPrimaryKey(E[] bills, String[] ids, int length) {
		List<String> bids = new ArrayList<String>();
		for (int i = 0; i < length; i++) {
			ids[i] = bills[i].getPrimaryKey();
			if (bills[i].getChildren(CustMatRelBVO.class) == null
					|| bills[i].getChildren(SuperVO.class).length == 0) {
				continue;
			}
			// 获取BVO主键，将主键存在数组中
			for (ISuperVO bvo : bills[i].getChildren(SuperVO.class)) {
				if (bvo.getPrimaryKey() != null) {
					bids.add(bvo.getPrimaryKey());
				}
			}
		}
		return bids;
	}
}
