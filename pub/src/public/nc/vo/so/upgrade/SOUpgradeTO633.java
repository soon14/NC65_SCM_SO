package nc.vo.so.upgrade;

import nc.scmmm.upgrade.scmpub.v633.AbstractUpgradeToV633;
import nc.scmmm.upgrade.scmpub.v633.ISCMUpdateAccount;

public class SOUpgradeTO633 extends AbstractUpgradeToV633 {

  @Override
  protected ISCMUpdateAccount[] getUpgradeAccount() {

    ISCMUpdateAccount up63to63eimm = new SOUpgrade63ToV63eimm();
    return new ISCMUpdateAccount[] {
      up63to63eimm
    };
  }

}
