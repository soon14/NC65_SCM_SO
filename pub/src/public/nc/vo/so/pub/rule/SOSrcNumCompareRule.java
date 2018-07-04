package nc.vo.so.pub.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.so.pub.SOItemKey;

public class SOSrcNumCompareRule<E extends AggregatedValueObject, T extends SuperVO> {

  private List<E> chgvolist;

  private List<E> nochgvolist;

  private Class<E> voClass;

  private Class<T> bodyvoClass;

  public SOSrcNumCompareRule(Class<E> voClass, Class<T> bodyvoClass) {
    this.voClass = voClass;
    this.bodyvoClass = bodyvoClass;
  }

  public void compareSrcNum(AggregatedValueObject[] srcVOs,
      AggregatedValueObject[] destVOs) {
    this.chgvolist = new ArrayList<E>();
    this.nochgvolist = new ArrayList<E>();
    Map<String, UFDouble> mapsrcnum = this.getSrcNumMap(srcVOs);

    for (AggregatedValueObject destVO : destVOs) {
      List<CircularlyAccessibleValueObject> listchgbody =
          new ArrayList<CircularlyAccessibleValueObject>();

      List<CircularlyAccessibleValueObject> listnochgbody =
          new ArrayList<CircularlyAccessibleValueObject>();

      for (CircularlyAccessibleValueObject destbvo : destVO.getChildrenVO()) {
        String srcbid = (String) destbvo.getAttributeValue(SOItemKey.CSRCBID);

        UFDouble srcnum = mapsrcnum.get(srcbid);

        UFDouble destnum = (UFDouble) destbvo.getAttributeValue(SOItemKey.NNUM);

        if (null != srcnum && MathTool.absCompareTo(srcnum, destnum) != 0) {
          listchgbody.add(destbvo);
        }
        else {
          listnochgbody.add(destbvo);
        }
      }
      if (listchgbody.size() == 0) {
        this.nochgvolist.add((E) destVO);
      }
      else if (listnochgbody.size() == 0) {
        this.chgvolist.add((E) destVO);
      }
      else {

        T[] chgbodys =
            Constructor.construct(this.bodyvoClass, listchgbody.size());
        listchgbody.toArray(chgbodys);

        E chgvo = Constructor.construct(this.voClass);
        chgvo.setParentVO(destVO.getParentVO());
        chgvo.setChildrenVO(chgbodys);
        this.chgvolist.add(chgvo);

        T[] nochgbodys =
            Constructor.construct(this.bodyvoClass, listnochgbody.size());
        listnochgbody.toArray(nochgbodys);
        E nochgvo = Constructor.construct(this.voClass);
        nochgvo.setParentVO(destVO.getParentVO());
        nochgvo.setChildrenVO(nochgbodys);
        this.nochgvolist.add(nochgvo);
      }
    }
  }

  private Map<String, UFDouble> getSrcNumMap(AggregatedValueObject[] srcVOs) {

    Map<String, UFDouble> mapsrcnum = new HashMap<String, UFDouble>();
    for (AggregatedValueObject srcvo : srcVOs) {
      for (CircularlyAccessibleValueObject srcbvo : srcvo.getChildrenVO()) {
        String srcbid = null;
        try {
          srcbid = srcbvo.getPrimaryKey();
        }
        catch (BusinessException e) {
          ExceptionUtils.wrappException(e);
        }
        UFDouble srcnum = (UFDouble) srcbvo.getAttributeValue(SOItemKey.NNUM);
        mapsrcnum.put(srcbid, srcnum);
      }
    }
    return mapsrcnum;
  }

  public List<E> getNumChgVOList() {
    return this.chgvolist;
  }

  public List<E> getNumNoChgVOList() {
    return this.nochgvolist;
  }
}
