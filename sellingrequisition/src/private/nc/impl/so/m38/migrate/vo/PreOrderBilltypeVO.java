package nc.impl.so.m38.migrate.vo;

import nc.vo.arap.pub.VOMetaFactory;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.billtype.BilltypeVO;

/**
 * 在预订单迁移过程中，两次需要涉及到bd_billtype表数据迁移，在读取和插入bd_billtype表数据时，
 * 目前没有现成的接口可以调用，也不能使用诸如VOInsert、VOQuery之类的方式去操作billtype数据，
 * 因此这里使用PreOrderBilltypeVO继承自BilltypeVO,以使之可以使用VOQuery、VOInsert
 * 方法来操作bd_billtype数据
 * @since 6.36
 * @version 2015-05-25
 * @author liylr
 */
public class PreOrderBilltypeVO extends BilltypeVO{
	
	private static final long serialVersionUID = 5365296687429519177L;

	@Override
	public IVOMeta getMetaData() {
	    IVOMeta meta = VOMetaFactory.getInstance().getVOMeta("uap.BilltypeVO");
	    return meta;
	  }
}
