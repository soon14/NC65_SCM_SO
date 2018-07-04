package nc.impl.so.m30;

import java.util.Map;

import nc.bs.so.pub.SORelationOrgQuery;
import nc.itf.so.m30.IQueryRelationOrg;
import nc.vo.pub.BusinessException;

/**
 * ���۶�������Ҫ���ݿͻ���������֯�����ϻ�ȡ���ϵķ��������֯�����������֯ID��Ӧ����֯ID����������ID������/�㷨����
 * <ol>
 * <li>���ù��������ȡ���������֯
 * <li>���ù��������ȡ���������֯ID��Ӧ����֯ID����������ID
 * <li>--���ù��������ȡĬ��������֯--modify by zhangby5 ȡĬ��������֯Ҫ����ȡ�ֿ���� 
 * </ol>
 * 
 * @since 6.0
 * @version 2011-7-29 ����15:05:00
 * @author ������
 * @see
 */
public class QueryRelationOrgImpl implements IQueryRelationOrg {

  /**
   * ���ݿͻ���������֯�����ϻ�ȡ���ϵķ��������֯�����������֯ID��Ӧ����֯ID����������ID
   * ���ݽ��������Ƿ�ֱ�˻�ȡֱ�˲�
   * 
   * @param transtypeID ��������ID
   * @param customerID �ͻ�ID
   * @param saleOrg ������֯
   * @param materialIDS ����ID����
   * @param sendStordocIDS �ֿ����������֯����
   * @return
   *         Map<Key�����ϣ�Value��String[]{���������֯�����������֯ID��Ӧ����֯ID����������ID��ֱ�˲�}
   *         >
   * @throws BusinessException
   * @see
   */
  @Override
  public Map<String, String[]> querySaleRelationOrg(String transtypeID,
      String customerID, String saleOrg, String[] materialIDS)
      throws BusinessException {

    SORelationOrgQuery orgQuery =
        new SORelationOrgQuery(customerID, saleOrg, materialIDS);
    orgQuery.setTranstypeID(transtypeID);
    return orgQuery.querySaleRelationOrg();
  }

}