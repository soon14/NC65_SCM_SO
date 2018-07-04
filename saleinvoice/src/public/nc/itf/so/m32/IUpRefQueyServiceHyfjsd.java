package nc.itf.so.m32;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.lm.hyfjsd.AggSeasettHVO;
import nc.vo.pub.BusinessException;

public interface IUpRefQueyServiceHyfjsd {

	public AggSeasettHVO[] queryUpForDownHyfjsd(IQueryScheme queryScheme) throws BusinessException;
	
}
