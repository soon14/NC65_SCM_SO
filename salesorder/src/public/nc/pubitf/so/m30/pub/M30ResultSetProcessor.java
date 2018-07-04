package nc.pubitf.so.m30.pub;

import java.sql.ResultSet;
import java.sql.SQLException;

import nc.vo.so.m30trantype.entity.M30TranTypeVO;

import nc.jdbc.framework.processor.ResultSetProcessor;

public class M30ResultSetProcessor implements ResultSetProcessor {

  private static final long serialVersionUID = 3672983744363338489L;

  String[] names = new String[] {
    M30TranTypeVO.PK_TRANTYPE, M30TranTypeVO.CTRANTYPEID,
    M30TranTypeVO.FDIRECTTYPE, M30TranTypeVO.FSALEMODE,
    M30TranTypeVO.BMOREROWS, M30TranTypeVO.BCANCHANGEOUT,
    M30TranTypeVO.FASKQTRULE, M30TranTypeVO.BMODIFYASKEDQT,
    M30TranTypeVO.BMODIFYUNASKEDQT, M30TranTypeVO.FLARGESSGETQTRULE,
    M30TranTypeVO.BMODIFYDISCOUNT, M30TranTypeVO.PK_GROUP,
    M30TranTypeVO.VTRANTYPECODE, M30TranTypeVO.BREVISEPRICE,
    M30TranTypeVO.BREDORDERPAY, M30TranTypeVO.FLARGESSDISTYPE,
    M30TranTypeVO.BLARGESSPRICENO, M30TranTypeVO.BARRANGEINV,
    M30TranTypeVO.BARRANGEOUT, M30TranTypeVO.BNOFINDPRICEHIT,
    M30TranTypeVO.FMODIFYMNY, M30TranTypeVO.BLOSSRENEW
  };

  @Override
  public Object handleResultSet(ResultSet rs) throws SQLException {
    M30TranTypeVO mvo = new M30TranTypeVO();
    while (rs.next()) {
      for (int i = 0; i < this.names.length; i++) {
        mvo.setAttributeValue(this.names[i], rs.getString(this.names[i]));
      }
    }
    return mvo;
  }
}
