package nc.bs.so.salequotation.task;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.pub.LockOperator;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m38.entity.PreOrderHVO;
import nc.vo.so.pub.SOTable;
import nc.vo.so.salequotation.entity.BillStatusEnum;
import nc.vo.so.salequotation.entity.SalequotationHVO;

/**
 * 报价单自动失效和关闭后台任务业务插件
 * 
 * @since 6.0
 * @version 2011-11-24 下午04:08:23
 * @author 王天文
 */
public class SalequoBGWorkPlugin implements IBackgroundWorkPlugin {

  /**
   * 关闭和自动失效到期的且已经加锁的报价单
   * 
   * @param bgWorkhvos 可以进行关闭和自动失效的单据实体集
   */
  private void autoInvaliClose(SalequotationHVO[] bgWorkhvos) {

    List<SalequotationHVO> closeList = new ArrayList<SalequotationHVO>();
    List<SalequotationHVO> invalidationList = new ArrayList<SalequotationHVO>();
    for (SalequotationHVO vo : bgWorkhvos) {
      Integer billStatus = vo.getFstatusflag();
      if (BillStatusEnum.FREE.equalsValue(billStatus)
          || BillStatusEnum.AUDITING.equalsValue(billStatus)) {
        invalidationList.add(vo);
      }
      else {
        closeList.add(vo);
      }
    }
    if (closeList.size() > 0) {
      this.closeOrder(closeList);
    }
    if (invalidationList.size() > 0) {
      this.invalidationOrder(invalidationList);
    }

  }

  /**
   * 关闭报价单
   * 
   * @param closeList 待关闭的单据实体集
   */
  private void closeOrder(List<SalequotationHVO> closeList) {

    SalequotationHVO[] hvos = new SalequotationHVO[closeList.size()];
    closeList.toArray(hvos);
    for (SalequotationHVO hvo : hvos) {
      hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_CLOSE));
      hvo.setStatus(VOStatus.UPDATED);
    }
    String[] names = new String[] {
      SalequotationHVO.FSTATUSFLAG
    };
    VOUpdate<SalequotationHVO> update = new VOUpdate<SalequotationHVO>();
    update.update(hvos, names);
  }

  @Override
  public PreAlertObject executeTask(BgWorkingContext bgwc)
      throws BusinessException {

    PreAlertObject retObj = new PreAlertObject();
    try {
      // 获取需要自动关闭和自动失效的单据实体集合
      SalequotationHVO[] bgWorkhvos = this.getBGWorkInfo(bgwc);
      // 自动失效到期报价单，关闭审批通过的到期报价单
      this.autoInvaliClose(bgWorkhvos);
      retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return retObj;

  }

  /**
   * 获取需要自动失效和关闭的单据实体结果集
   * 
   * @param bgwc 后台任务环境变量
   * @return 需要自动失效和关闭的，且已经加锁的单据实体结果集
   */
  private SalequotationHVO[] getBGWorkInfo(BgWorkingContext bgwc) {

    SqlBuilder where = this.getWhereSql(bgwc);
    String[] key = new String[] {
      SalequotationHVO.PK_SALEQUOTATION
    };
    VOQuery<SalequotationHVO> qrysrvOrig =
        new VOQuery<SalequotationHVO>(SalequotationHVO.class, key);
    SalequotationHVO[] keyhvos = qrysrvOrig.query(where.toString(), null);
    if (keyhvos.length == 0) {
      return keyhvos;
    }
    List<String> idsList = new ArrayList<String>();
    for (SalequotationHVO hvo : keyhvos) {
      idsList.add(hvo.getPk_salequotation());
    }
    String[] ids = idsList.toArray(new String[idsList.size()]);
    // 加pk锁，避免后台任务执行过程中单据发生改变
    LockOperator lock = new LockOperator();
    lock.lock(
        ids,
        NCLangRes4VoTransl.getNCLangRes().getStrByID("4006012_0",
            "04006012-000007")/*后台任务执行失败，请稍候重新触发后台任务！*/);
    // 使用原始where保证，所有过滤信息还是加锁前的信息不变，如时间等条件
    where.append(" and ");
    // 利用临时表避免查询到的id数组过长数据量过大情况
    IDExQueryBuilder iq = new IDExQueryBuilder(SOTable.TMP_SO_ID1.getName());
    String insql = iq.buildSQL(SalequotationHVO.PK_SALEQUOTATION, ids);
    where.append(insql);
    VOQuery<SalequotationHVO> qrysrv =
        new VOQuery<SalequotationHVO>(SalequotationHVO.class);
    SalequotationHVO[] toabdatehvos = qrysrv.query(where.toString(), null);

    return toabdatehvos;
  }

  /**
   * 查询需要自动失效和关闭报价单信息
   * 
   * @param bgwc 后台任务环境变量
   * @return 查询条件——已经到期的，且单据状态为“自由/审批中/审批通过”的单据
   */
  private SqlBuilder getWhereSql(BgWorkingContext bgwc) {

    SqlBuilder where = new SqlBuilder();
    where.append(" and ");
    where.append(SalequotationHVO.PK_GROUP, bgwc.getGroupId());
    String[] pkorgs = bgwc.getPk_orgs();
    if (pkorgs != null && pkorgs.length > 0) {
      where.append(" and ");
      where.append(PreOrderHVO.PK_ORG, pkorgs);
    }
    where.append(" and ");
    int[] status = new int[] {
      BillStatusEnum.C_FREE, BillStatusEnum.C_AUDITING, BillStatusEnum.C_AUDIT
    };
    where.append(SalequotationHVO.FSTATUSFLAG, status);
    where.append(" and ");
    UFDate serviceDate = AppContext.getInstance().getServerTime().getDate();
    UFDate dateStart = serviceDate.getDateBefore(7).asBegin();
    where.append(SalequotationHVO.DENDDATE, " >= ", dateStart.toString());
    where.append(" and ");
    UFDate dateEnd = serviceDate.asBegin();
    where.append(SalequotationHVO.DENDDATE, " <= ", dateEnd.toString());
    return where;
  }

  /**
   * 失效报价单
   * 
   * @param invalidationList 待自动失效的单据实体集
   */
  private void invalidationOrder(List<SalequotationHVO> invalidationList) {

    SalequotationHVO[] hvos = new SalequotationHVO[invalidationList.size()];
    invalidationList.toArray(hvos);
    for (SalequotationHVO hvo : hvos) {
      hvo.setFstatusflag(Integer.valueOf(BillStatusEnum.C_INVALIDATE));
      hvo.setStatus(VOStatus.UPDATED);
    }
    String[] names = new String[] {
      SalequotationHVO.FSTATUSFLAG
    };
    VOUpdate<SalequotationHVO> update = new VOUpdate<SalequotationHVO>();
    update.update(hvos, names);
  }

}
