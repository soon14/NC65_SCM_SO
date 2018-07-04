package nc.pubimpl.so.custmatrel.mergelistener;

import nc.bs.bd.businessevent.MergeBusinessEvent;
import nc.bs.businessevent.IBusinessEvent;
import nc.bs.businessevent.IBusinessListener;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.bd.cust.CustomerVO;
import nc.vo.pub.BusinessException;
import nc.vo.so.custmatrel.entity.CustMatRelBVO;

/**
 * 销售管理基础设置节点客户与物料关系定义在客商合并时删除源客户存在的数据
 * 
 * @since 6.0
 * @version 2011-11-12 上午10:54:41
 * @author fengjb
 */
public class SOCustMaterMergeListener implements IBusinessListener {

  @Override
  public void doAction(IBusinessEvent event) throws BusinessException {

    if (event instanceof MergeBusinessEvent) {
      MergeBusinessEvent e = (MergeBusinessEvent) event;
      CustomerVO source = (CustomerVO) e.getSourceVO();
      String customerid = source.getPrimaryKey();
      VOQuery<CustMatRelBVO> voquery =
          new VOQuery<CustMatRelBVO>(CustMatRelBVO.class);
      String wheresql = " and pk_customer = '" + customerid + "'";
      CustMatRelBVO[] bvos = voquery.query(wheresql, null);
      if (null == bvos || bvos.length == 0) {
        return;
      }
      // 删除
      VODelete<CustMatRelBVO> vodelete = new VODelete<CustMatRelBVO>();
      vodelete.delete(bvos);
    }
  }
}
