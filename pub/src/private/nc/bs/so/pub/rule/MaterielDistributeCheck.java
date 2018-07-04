package nc.bs.so.pub.rule;

import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pubapp.pattern.pub.MapList;

public class MaterielDistributeCheck {

  /**
   * 校验物料和库存组织的分配关系
   * 
   * @param materIDStoreIDs [物料][库存组织]
   *          单据一行表体的物料、发货库存组织对应参数二维数组的一行
   */
  public void check(String[][] materIDStoreIDs) {
    // <库存组织,物料数组>
    MapList<String, String> mstoreIDmaterIDs = new MapList<String, String>();

    for (String[] storeIDmaterID : materIDStoreIDs) {
      mstoreIDmaterIDs.put(storeIDmaterID[1], storeIDmaterID[0]);
    }

    for (Entry<String, List<String>> entry : mstoreIDmaterIDs.entrySet()) {
      // 物料ID
      List<String> aryMarid = entry.getValue();
      Set<String> setid = new HashSet<String>();
      for (String marid : aryMarid) {
        setid.add(marid);
      }
      String[] marids = new String[setid.size()];
      setid.toArray(marids);
      // 发货库存组织
      String storeID = entry.getKey();
      // 校验
      MaterialPubService.checkMaterialVisiabilityInStorckOrg(storeID, marids);
    }

  }

}
