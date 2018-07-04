package nc.itf.so.m33.biz.canclesquare;

import java.util.List;

import nc.vo.pub.SuperVO;

public interface ICancelSquareAction<T extends SuperVO> {

  void cancelSquare(List<T> list);

}
