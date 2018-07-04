package nc.pubimpl.so.m30arrange;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.so.m30trantype.IM30TranTypeSelfService;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.so.m30trantype.entity.M30TranTypeVO;

/**
 * 用于自己的界面查询编辑
 * 
 * @since 6.0
 * @version 2012-3-15 下午03:46:21
 * @author 么贵敬
 */
public class M30TranTypeSelfServiceImpl implements IM30TranTypeSelfService {

  @Override
  public M30TranTypeVO[] queryTranTypeVOs(String[] ctrantypeids)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(M30TranTypeVO.CTRANTYPEID, ctrantypeids);
    VOQuery<M30TranTypeVO> srv =
        new VOQuery<M30TranTypeVO>(M30TranTypeVO.class);
    M30TranTypeVO[] vos = srv.query(ctrantypeids);
    return vos;
  }

  @Override
  public M30TranTypeVO queryTranTypeVO(String ctrantypeid)
      throws BusinessException {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and ");
    sql.append(M30TranTypeVO.CTRANTYPEID, ctrantypeid);
    VOQuery<M30TranTypeVO> srv =
        new VOQuery<M30TranTypeVO>(M30TranTypeVO.class);
    M30TranTypeVO[] vos = srv.query(sql.toString(), null);
    return vos[0];
  }

}
