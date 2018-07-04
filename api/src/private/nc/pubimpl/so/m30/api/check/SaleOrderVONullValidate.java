package nc.pubimpl.so.m30.api.check;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.ValidationException;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.check.vovalidate.VONullValidate;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.pub.SOItemKey;

/**
 * @description
 * 销售订单非空校验1
 * @scene
 *
 * @param
 *
 *
 * @since 6.5
 * @version 2015-10-26 下午2:38:44
 * @author 刘景
 */
public class SaleOrderVONullValidate extends VONullValidate {

  @Override
  public  String [] getHeadNotNullFields() {
    String[] headnames =
        {
          SOItemKey.PK_ORG, SOItemKey.CCUSTOMERID,
        };
    return headnames;
  }

  @Override
  public String [] getBodyNotNullFields() {

    String[] bodynames = {
      SOItemKey.CMATERIALVID,SOItemKey.NQTUNITNUM
    };
    return bodynames;
  }

  @Override
  public MapList<String, String> getMultiBodyNotNullFields() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void otherCheck(AbstractBill billVO,
      List<ValidationException> exceptions) {

    // 表体不能同时为空
    check(billVO, new String[] {
      SOItemKey.CTRANTYPEID, SOItemKey.VTRANTYPECODE
    },null, exceptions);

   /* check(billVO, null, new String[] {
      SOItemKey.NQTORIGTAXPRICE, SOItemKey.NORIGTAXPRICE,
      SOItemKey.NORIGTAXMNY,
    }, exceptions);*/
  }

  protected void check(AbstractBill bill, String[] headnullkeys,
      String[] bodynullkeys, List<ValidationException> exceptions) {
    // 检查表头
    checkHead(bill, headnullkeys, exceptions);

    // 检查表体
    checkBody(bill, bodynullkeys, exceptions);
  }

  private void checkHead(AbstractBill bill, String[] headnullkeys,
      List<ValidationException> exceptions) {

    IBillMeta billmeta = bill.getMetaData();
    IVOMeta parent = billmeta.getParent();
    // 检查表头
    if (headnullkeys != null) {
      Set<String> nullheadset = new HashSet<String>();
      ISuperVO headvo = bill.getParent();
      for (String headnullkey : headnullkeys) {
        if (headvo.getAttributeValue(headnullkey) == null) {
          IAttributeMeta attribute=parent.getAttribute(headnullkey);
          nullheadset.add(attribute.getColumn().getLabel());
        }
      }
      if (nullheadset.size() > 0 && nullheadset.size() == headnullkeys.length) {
        String message = "表头以下字段不能同时为空：" + getshow(nullheadset, "、");/*-=notranslate=-*/
        exceptions.add(new ValidationException(message));
      }
    }

  }

  private void checkBody(AbstractBill bill, String[] bodynullkeys,
      List<ValidationException> exceptions) {
    if (bodynullkeys != null) {
      int row = 1;
      ISuperVO[] supervos = bill.getChildren(SaleOrderBVO.class);
      Set<String> messagelist = new HashSet<String>();
      for (ISuperVO vo : supervos) {
        Set<String> nullbodyset = new HashSet<String>();
        for (String bodynullkey : bodynullkeys) {
          if (vo.getAttributeValue(bodynullkey) == null) {
            IAttributeMeta attribute=vo.getMetaData().getAttribute(bodynullkey);
            nullbodyset.add(attribute.getColumn().getLabel());
          }
        }
        if (nullbodyset.size() > 0 && nullbodyset.size() == bodynullkeys.length) {
          String message =
              "表体第" + row + "行以下字段不能同时为空：" + getshow(nullbodyset, "、");/*-=notranslate=-*/
          messagelist.add(message);
        }
        row++;
      }
      if (messagelist.size() > 0) {
        exceptions.add(new ValidationException(getshow(messagelist, "\r\n")));
      }
    }
  }

  private String getshow(Set<String> nullfields, String partition) {
    StringBuffer str = new StringBuffer();
    for (String field : nullfields) {
      str.append(field + partition);
    }
    str.deleteCharAt(str.length() - 1);
    return str.toString();
  }
}
