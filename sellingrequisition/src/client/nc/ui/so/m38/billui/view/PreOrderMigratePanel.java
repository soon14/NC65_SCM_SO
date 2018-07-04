package nc.ui.so.m38.billui.view;

import java.awt.Color;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITextArea;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class PreOrderMigratePanel extends UIPanel {

	private static final long serialVersionUID = 2480663474733277960L;
	private UITextArea m_taTextArea = null;

	public void initUI() {
		setLayout(null);
		add(getTextAreaHint());
	}

	private UITextArea getTextAreaHint() {
		if (this.m_taTextArea == null) {
			try {
				StringBuffer sText = new StringBuffer();
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0119")/*\t预订单迁移步骤：*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0120")/*\t（1）安装 电子销售-订单中心（免费）;*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0121")/*\t（2）点击“迁移”按钮。*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0104")/*\t预订单迁移注意事项：*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0105")/*\t（1）销售管理的预订单迁移后，生成订单中心预订单，原销售管理的预订单数据被删除，且该过程不可逆;*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0107")/*\t（2）迁移后，生产制造支持将订单中心预订单作为需求单据做生产计划;*/);
				sText.append("\n\n");
				sText.append(NCLangRes.getInstance().getStrByID("4006012_0", "04006012-0108")/*\t（3）迁移后，请将订单中心预订单的自定义项的设置与预订单保持一致。*/);
				
				this.m_taTextArea = new UITextArea();
				this.m_taTextArea.setName("UITextArea1");

				this.m_taTextArea.setRows(15);
				this.m_taTextArea.setLineWrap(true);
				this.m_taTextArea.setEditable(false);
				this.m_taTextArea.setEnabled(false);

				this.m_taTextArea.setBackground(getBackground());
				this.m_taTextArea.setDisabledTextColor(Color.BLACK);

				this.m_taTextArea.setText(sText.toString());
				this.m_taTextArea.setBounds(75, 30, 1050, 455);
				
			} catch (Exception ex) {
				ExceptionUtils.wrappException(ex);
			}
		}
		return this.m_taTextArea;
	}
}
