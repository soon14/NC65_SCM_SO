/**
 * 
 */
package nc.bs.so.m30.rule.rewrite.LS41;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.lm.lsdlxy.BaseWriteBackVO;
import nc.vo.lm.lsdlxy.LsxywtHVO;
import nc.vo.lm.lsdlxy.LsxywtbBVO;
import nc.vo.pub.JavaType;
import nc.vo.pub.VOStatus;

/**
 * @author wangzym
 * @version 2017年5月5日 下午6:09:09
 */
public class RewriteForLS41BillUpdateRule implements ICompareRule<SaleOrderVO> {

	public RewriteForLS41BillUpdateRule() {
		// TODO 自动生成的构造函数存根
	}

	@Override
	public void process(SaleOrderVO[] vo, SaleOrderVO[] originvo) {
		// TODO 只针对表体
		// 调用回写类直接进行回写操作
		SaleOrderBVO[] bvos = (SaleOrderBVO[]) vo[0].getChildrenVO();
		String[] srcPk = new String[bvos.length];
		for (int i = 0; i < bvos.length; i++) {
			SaleOrderBVO saleOrderBVO = bvos[i];
			if ((saleOrderBVO.getAttributeValue("csrcbid")) == null
					|| !"LS41".equals(saleOrderBVO
							.getAttributeValue("vsrctype"))) {
				// 不需要回写没有取到上游的来源单据
				// 或者取到的来源单据类型不是价审单
				return;
			}
		}
		Map<String, List<RewritePara>> rwParaMap = null;

		BillRewriter tool = new BillRewriter(this.getItemKeyMapping());

		tool.addSRCHeadClazz("LS41", LsxywtHVO.class);
		tool.addSRCItemClazz("LS41", LsxywtbBVO.class);

		if (vo != null && originvo != null) {
			rwParaMap = tool.splitForUpdate(vo, originvo);
		} else if (vo != null && null == originvo) {
			rwParaMap = tool.splitForInsert(vo);
		} else if (originvo != null && null == vo) {
			rwParaMap = tool.splitForDelete(originvo);
		}
		if (null == rwParaMap) {
			return;
		}

		// 遍历Map时，使用高效的entrySet()，这样避免二次搜索
		for (Map.Entry<String, List<RewritePara>> en : rwParaMap.entrySet()) {
			if (en.getValue().isEmpty()) {
				continue;
			}
			// 回写LS41
			writeBack(en.getValue());
		}

	}

	/**
	 * @Title:正式回写操作
	 * @Description: TODO
	 * @param value
	 * @return: void
	 */
	private void writeBack(List<RewritePara> paraList) {
		// TODO 自动生成的方法存根
		List<nc.vo.lm.lsdlxy.BaseWriteBackVO> writeBackList = new ArrayList<nc.vo.lm.lsdlxy.BaseWriteBackVO>();
		for (RewritePara para : paraList) {
			this.setWriteBackVO(para, writeBackList);
		}

		if (0 == writeBackList.size()) {
			return;
		}
		nc.vo.lm.lsdlxy.BaseWriteBackVO[] bvos = writeBackList
				.toArray(new nc.vo.lm.lsdlxy.BaseWriteBackVO[writeBackList
						.size()]);
		this.backWrite(bvos);

	}

	private ItemKeyMapping getItemKeyMapping() {
		ItemKeyMapping mapping = new ItemKeyMapping();
		// 来源单据类型
		mapping.setVsrctypeKey(SaleOrderBVO.VSRCTYPE);
		// 来源单据主表主键
		mapping.setCsrcidKey(SaleOrderBVO.CSRCID);
		// 来源单据表体主键
		mapping.setCsrcbidKey(SaleOrderBVO.CSRCBID);
		return mapping;
	}

	// 给回写的vo赋值
	private void setWriteBackVO(RewritePara para,
			List<nc.vo.lm.lsdlxy.BaseWriteBackVO> writeBackList) {
		nc.vo.lm.lsdlxy.BaseWriteBackVO vo = new nc.vo.lm.lsdlxy.BaseWriteBackVO();
		if (para.getStatus() == VOStatus.DELETED) {
			vo.setOperateFlag("DELETE");
		} else if (para.getStatus() == VOStatus.NEW) {
			vo.setOperateFlag("ADD");
		}
		vo.setPk_lsdlxy(para.getCsrcid());
		vo.setPk_lsdlxy_b(para.getCsrcbid());
		writeBackList.add(vo);

	}

	public void backWrite(BaseWriteBackVO[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}
		List<List<Object>> adddata = new ArrayList<List<Object>>();
		List<List<Object>> deldata = new ArrayList<List<Object>>();
		HashSet<String> headPks = new HashSet<String>();
		for (BaseWriteBackVO vo : vos) {
			LsxywtbBVO item = new LsxywtbBVO();
			item.setPrimaryKey(vo.getPk_lsdlxy_b());

			if (!headPks.contains(vo.getPk_lsdlxy())
					&& ("ADD" == vo.getOperateFlag() || "DELETE" == vo
							.getOperateFlag())) {
				LsxywtHVO head = new LsxywtHVO();
				head.setPrimaryKey(vo.getPk_lsdlxy());
			}

			List<Object> rowData = new ArrayList<Object>();
			rowData.add(vo.getPk_lsdlxy_b());

			if ("ADD" == vo.getOperateFlag()) {
				adddata.add(rowData);
			}
			if ("DELETE" == vo.getOperateFlag()) {
				deldata.add(rowData);
			}
		}
		if (adddata.size() > 0) {
			// this.upAdd(adddata);
		}
		if (deldata.size() > 0) {
			this.upDel(deldata);
		}

	}

	private void upDel(List<List<Object>> deldata) {
		if (deldata.size() > 0) {
			String delsql = "update lm_lsdlxyb set bdef1=0 WHERE pk_lsdlxy_b = ?  ";
			new DataAccessUtils().update(delsql,
					new JavaType[] { JavaType.String }, deldata);
		}
	}

}
