package nc.ui.so.m33.service.ic.m4c.itf;

import java.awt.Container;

import nc.vo.pub.BusinessException;
import nc.vo.so.m33.m4c.linkqryoutrush.entity.LinkQueryOutRushVO;

public interface ISquareOutRushLinkQueryUICtr {

  void outRushLinkQuery(Container parent, LinkQueryOutRushVO[] paravo)
      throws BusinessException;
}
