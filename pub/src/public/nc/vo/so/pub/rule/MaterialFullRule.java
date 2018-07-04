package nc.vo.so.pub.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.so.pub.keyvalue.IKeyRela;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.so.pub.keyvalue.SOKeyRela;
import nc.vo.so.pub.keyvalue.VOKeyValue;

/**
 * @description
 * 物料oid填充规则
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-10-20 下午10:42:56
 * @author 刘景
 */
public class MaterialFullRule<E extends IBill> {

  private IKeyRela keyRela;

  private E[] vos;

  /**
   * 构造方法
   * @param e
   */
  public MaterialFullRule(
      E[] e) {
    this.keyRela = new SOKeyRela();
    this.vos=e;
  }

  /**
   * 构造方法
   * @param e
   * @param keyRela
   */
  public MaterialFullRule(
      E[] e, IKeyRela keyRela) {
    this.keyRela = keyRela;
    this.vos=e;
  }

  public void setMaterialOid() {

    Set<String> matvidset = new HashSet<>();
    for (E vo : vos) {
      IKeyValue keyValue = new VOKeyValue<E>(vo);
      for (int j = 0; j < keyValue.getBodyCount(); j++) {
        String cmaterialvid =
            keyValue.getBodyStringValue(j, keyRela.getCmaterialvidKey());
        String cmaterialid =
            keyValue.getBodyStringValue(j, keyRela.getCmaterialidKey());
        if (cmaterialid == null) {
          matvidset.add(cmaterialvid);
        }
      }

    }
    if (matvidset.size() > 0) {
      Map<String, String> matoldmap =
          MaterialPubService.queryMaterialOidByVid(matvidset
              .toArray(new String[matvidset.size()]));

      for (E vo : vos) {
        IKeyValue keyValue = new VOKeyValue<E>(vo);
        for (int j = 0; j < keyValue.getBodyCount(); j++) {
          String cmaterialvid =
              keyValue.getBodyStringValue(j, keyRela.getCmaterialvidKey());
          String cmaterialid =
              keyValue.getBodyStringValue(j, keyRela.getCmaterialidKey());
          if (cmaterialid == null) {
            keyValue.setBodyValue(j, keyRela.getCmaterialidKey(),
                matoldmap.get(cmaterialvid));
          }
        }

      }
    }
  }

}
