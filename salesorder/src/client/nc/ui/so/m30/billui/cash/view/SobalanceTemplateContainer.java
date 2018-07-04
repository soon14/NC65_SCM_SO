package nc.ui.so.m30.billui.cash.view;

import java.util.ArrayList;
import java.util.Arrays;

import nc.ui.pub.bill.BillUIUtil;
import nc.ui.uif2.editor.TemplateContainer;

public class SobalanceTemplateContainer extends TemplateContainer {
  private boolean isLoaded;

  @Override
  public void realLoad() {
    if (!this.isLoaded) {
      try {
        if (this.getNodeKeies() == null) {
          this.setNodeKeies(new ArrayList<String>());
          this.getNodeKeies().add(null);
        }
        else {
          if (!this.getNodeKeies().contains("")
              && !this.getNodeKeies().contains(null)) {
            this.getNodeKeies().remove(null);
            this.getNodeKeies().add(0, null);
          }
        }
        this.getTemplates().clear();
        this.getTemplates().addAll(
            Arrays.asList(BillUIUtil.getDefaultTempletStatic("40060304", this
                .getContext().getPk_loginUser(), this.getContext()
                .getPk_group(), this.getNodeKeies().toArray(new String[0]))));
      }
      finally {
        this.isLoaded = true;
      }

    }
  }
}
