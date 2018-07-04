package nc.ui.so.m33.service.ic.m4c.impl;

import java.awt.Container;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.so.m33.ic.m4c.ISquareOutRushLinkQuery;
import nc.ui.so.m33.service.ic.m4c.itf.ISquareOutRushLinkQueryUICtr;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.LinkQueryOutRushVO;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.OutRushExeInfoVO;

public class SquareOutRushLinkQueryUICtrImpl implements
    ISquareOutRushLinkQueryUICtr {

  private static final long serialVersionUID = 1L;

  @Override
  public void outRushLinkQuery(Container parent, LinkQueryOutRushVO[] paravo)
      throws BusinessException {
    ISquareOutRushLinkQuery boqry =
        NCLocator.getInstance().lookup(ISquareOutRushLinkQuery.class);
    try {
      OutRushExeInfoVO[] vos = boqry.outRushLinkQuery(paravo);
      new SquareOutRushLinkQueryDialog(parent, vos).showModal();
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }
}
