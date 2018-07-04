package nc.impl.so.m38trantype;

import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;
import nc.vo.so.m38trantype.entity.M38TranTypeVO;

import nc.itf.so.m38trantype.IM38TranTypeSelfService;

import nc.impl.pubapp.pattern.data.vo.VOQuery;

/**
 * 用于自己的界面查询编辑
 * 
 * @since 6.0
 * @version 2012-3-15 下午03:46:21
 * @author 么贵敬
 */
public class M38TranTypeSelfServiceImpl implements IM38TranTypeSelfService {

  @Override
  public M38TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(M38TranTypeVO.CTRANTYPEID, ctrantypeids);
    VOQuery<M38TranTypeVO> srv =
        new VOQuery<M38TranTypeVO>(M38TranTypeVO.class);
    M38TranTypeVO[] vos = srv.query(sql.toString(), null);
    return vos;
  }

  @Override
  public M38TranTypeVO queryTranTypeVO(String ctrantypeid)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(M30TranTypeVO.CTRANTYPEID, ctrantypeid);
    VOQuery<M38TranTypeVO> srv =
        new VOQuery<M38TranTypeVO>(M38TranTypeVO.class);
    M38TranTypeVO[] vos = srv.query(sql.toString(), null);
    return vos[0];
  }

}
