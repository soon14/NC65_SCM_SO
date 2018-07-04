package nc.impl.so.task;

import nc.ui.pub.print.IMetaDataDataSource;

public class SOAlertMetaDataSource implements IMetaDataDataSource {

	private final Object[] mdDatas;

	public SOAlertMetaDataSource(Object[] datas) {
		this.mdDatas = datas;
	}

	@Override
	public Object[] getMDObjects() {
		return this.mdDatas;
	}

	@Override
	public String[] getItemValuesByExpress(String itemExpress) {
		return null;
	}

	@Override
	public boolean isNumber(String itemExpress) {
		return false;
	}

	@Override
	public String[] getDependentItemExpressByExpress(String itemExpress) {
		return null;
	}

	@Override
	public String[] getAllDataItemExpress() {
		return null;
	}

	@Override
	public String[] getAllDataItemNames() {
		return null;
	}

	@Override
	public String getModuleName() {
		return null;
	}

}
