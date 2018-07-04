package nc.vo.so.m30.util;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.ArrayUtil;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryBVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryHVO;
import nc.vo.so.m30.revise.entity.SaleOrderHistoryVO;

/**
 * @description 销售订单修订vo 销售订单vo转换工具
 * @since 6.36
 * @version 2014-12-26 上午11:29:01
 * @author wangshu6
 */
public class Transfer30and30RVOTool {

	/**
	 * 将销售订单批量转换成销售订单修订VO
	 * 
	 * @param vos
	 *            销售订单
	 * @return 销售订单修订vos
	 */
	public SaleOrderHistoryVO[] transfer30TOOrderhisVO(SaleOrderVO[] vos) {
		List<SaleOrderHistoryVO> list = new ArrayList<SaleOrderHistoryVO>();
		for (SaleOrderVO vo : vos) {

			SaleOrderHistoryVO orderVO = transfer30TOOrderhisVO(vo);
			list.add(orderVO);
		}
		SaleOrderHistoryVO[] arrs = list.toArray(new SaleOrderHistoryVO[0]);
		return arrs;
	}

	/**
	 * 将销售订单转换成销售订单修订VO
	 * 
	 * @param vo
	 * @return 销售订单修订vos
	 */
	public SaleOrderHistoryVO transfer30TOOrderhisVO(SaleOrderVO vo) {
		SaleOrderBVO[] bvo = vo.getChildrenVO();
		SaleOrderHVO hvo = vo.getParentVO();

		SaleOrderHistoryBVO[] orderbVO = transferVOS(bvo,
				SaleOrderHistoryBVO.class);
		SaleOrderHistoryHVO orderHisVO = transferVOS(hvo,
				SaleOrderHistoryHVO.class);

		SaleOrderHistoryVO orderVO = new SaleOrderHistoryVO();
		orderVO.setChildrenVO(orderbVO);
		orderVO.setParentVO(orderHisVO);
		return orderVO;
	}

	/**
	 * 将销售订单修订VO换成销售订单VO
	 * 
	 * @param vos
	 * @return
	 */
	public SaleOrderVO[] transferOrderhisBVOTO30VO(SaleOrderHistoryVO[] vos) {
		List<SaleOrderVO> list = new ArrayList<SaleOrderVO>();
		for (SaleOrderHistoryVO vo : vos) {
			SaleOrderHistoryBVO[] bvo = vo.getChildrenVO();
			SaleOrderHistoryHVO hvo = vo.getParentVO();
			SaleOrderBVO[] orderbVO = transferVOS(bvo, SaleOrderBVO.class);
			SaleOrderHVO orderHVO = transferVOS(hvo, SaleOrderHVO.class);
			SaleOrderVO saleorderVO = new SaleOrderVO();
			saleorderVO.setChildrenVO(orderbVO);
			saleorderVO.setParentVO(orderHVO);
			list.add(saleorderVO);
		}
		SaleOrderVO[] arrs = list.toArray(new SaleOrderVO[0]);

		return arrs;
	}

	/**
	 * 批量转换vo
	 * 
	 * @param vos
	 *            待转换vo
	 * @param descClazz
	 *            目标类名
	 * @return 转换结果
	 */
	@SuppressWarnings("unchecked")
	public <T extends SuperVO, E extends SuperVO> E[] transferVOS(T[] vos,
			Class<? extends SuperVO> descClazz) {

		if (ArrayUtil.isEmpty(vos)) {
			return null;
		}

		E[] result = (E[]) Constructor.declareArray(descClazz, vos.length);

		for (int i = 0; i < vos.length; i++) {
			result[i] = (E) Constructor.construct(descClazz);
			tranferVO(vos[i], result[i]);
		}
		return result;
	}

	public <T extends SuperVO, E extends SuperVO> E transferVOS(T vos,
			Class<? extends SuperVO> descClazz) {

		@SuppressWarnings("unchecked")
		E result = (E) Constructor.construct(descClazz);
		tranferVO(vos, result);
		return result;
	}

	/**
	 * 转换核心代码，属性的复制
	 * 
	 * @param 待转换vo
	 * @param 目标vo
	 */
	private void tranferVO(SuperVO originVO, SuperVO newVO) {
		String attributes[] = newVO.getAttributeNames();
		for (String att : attributes) {
			Object value = originVO.getAttributeValue(att);
			newVO.setAttributeValue(att, value);
		}
		// 销售订单转销售订单修订时， 给修订交易类型编码赋值
		this.doAfterSaleOrderVOToOrderHistoryVO(newVO, originVO);
	}

	private void doAfterSaleOrderVOToOrderHistoryVO(SuperVO newVO,
			SuperVO originVO) {
		if (newVO.getClass().equals(SaleOrderHistoryHVO.class)) {
			newVO.setAttributeValue("vhistrantypecode", "30R");
			newVO.setAttributeValue("chistrantypeid", "30R");
			Integer iVersion = (Integer) newVO.getAttributeValue("iversion");
			// 王梓懿 2018-06-23修改赋值给corderhistoryid真正的值
			String corderhistoryid = queryCorderHistoryidWithIdAndVersion(
					originVO.getPrimaryKey(), iVersion);
			newVO.setAttributeValue("corderhistoryid", corderhistoryid);
		} else {
			newVO.setAttributeValue("corderhistorybid",
					originVO.getPrimaryKey());
			newVO.setAttributeValue("corderhistoryid",
					originVO.getAttributeValue("csaleorderid"));
		}
	}

	/**
	 * @Title: 从数据库查询真正的销售订单修订主表ID
	 * @Description: TODO
	 * @param primaryKey
	 *            销售订单主表ID
	 * @param iVersion
	 *            销售订单修订版本
	 * @return 销售订单修订主表主键
	 */
	private String queryCorderHistoryidWithIdAndVersion(String primaryKey,
			Integer iVersion) {
		// select corderhistoryid from so_orderhistory where csaleorderid =''
		// and iversion ='' and dr<> 1
		SqlBuilder sql = new SqlBuilder();

		sql.append(" select corderhistoryid from so_orderhistory where ");
		sql.append(" csaleorderid ", primaryKey);
		sql.append(" and ");
		sql.append(" iversion ", iVersion);
		sql.append(" and dr<>1");
		DataAccessUtils utils = new DataAccessUtils();
		IRowSet rs = utils.query(sql.toString());
		String[] oneDimensionStringArray = rs.toOneDimensionStringArray();
		return oneDimensionStringArray.length == 0 ? null
				: oneDimensionStringArray[0];
	}
}
