package nc.ui.so.pub.closingcheck;

import nc.ui.org.closeaccbook.check.AbstractCloseCheckPanel;

/**
 * 存货核算前端检查panel。
 * @since 6.36
 * @version 2015-4-14 下午3:20:04
 * @author Legend_Han(hanlja@yonyou.com)
 */

@SuppressWarnings("restriction")
public class IACloseCheckPanel extends AbstractCloseCheckPanel{

	public IACloseCheckPanel(String title, String funCode, String configFilePath) {
		super(title, funCode, configFilePath);
	}

	@Override
	public String getCheckActionName() {
		return "checkAction";
	}

	@Override
	public String getPrintActionName() {
		return "printAction";
	}

	@Override
	public String getExportActionName() {
		return "outputAction";
	}

	@Override
	public String getPreviewActionName() {
		return "preViewAction";
	}

	@Override
	public String getCheckModelName() {
		return null;
	}
}
