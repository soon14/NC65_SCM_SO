package nc.vo.so.salequotation.entity;

import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDouble;


/**
 * 历史报价VO
 * 
 * @since 6.36
 * @version 2015-6-25 下午9:00:49
 * @author 刘景
 */
public class HisSalequoVO extends CircularlyAccessibleValueObject {

  public static final String CROWNO = "crowno";

  public static final String HISPRICE = "hisprice";

  public static final String NEWPRICE = "newprice";

  public static final String PK_MATERIAL = "pk_material";

  public static final String PK_CURRTYPE = "pk_currtype";

  /**
   * 
   */
  private static final long serialVersionUID = 4163535862176165739L;

  private String crowno;

  private UFDouble hisprice;

  private UFDouble newprice;

  private String pk_material;

  private String pk_currtype;

  @Override
  public String[] getAttributeNames() {
    // TODO Auto-generated method stub
    return new String[] {
      HisSalequoVO.PK_MATERIAL, HisSalequoVO.HISPRICE, HisSalequoVO.NEWPRICE,
      HisSalequoVO.CROWNO, HisSalequoVO.PK_CURRTYPE
    };
  }

  @Override
  public Object getAttributeValue(String attributeName) {
    Object retValue = null;
    if (HisSalequoVO.PK_MATERIAL.equals(attributeName)) {
      retValue = this.pk_material;
    }
    if (HisSalequoVO.HISPRICE.equals(attributeName)) {
      retValue = this.hisprice;
    }
    if (HisSalequoVO.NEWPRICE.equals(attributeName)) {
      retValue = this.newprice;
    }
    if (HisSalequoVO.CROWNO.equals(attributeName)) {
      retValue = this.crowno;
    }
    if (HisSalequoVO.PK_CURRTYPE.equals(attributeName)) {
      retValue = this.pk_currtype;
    }
    return retValue;
  }

  public String getCrowno() {
    return this.crowno;
  }

  @Override
  public String getEntityName() {
    // TODO Auto-generated method stub
    return null;
  }

  public UFDouble getHisprice() {
    return this.hisprice;
  }

  public UFDouble getNewprice() {
    return this.newprice;
  }

  public String getPk_material() {
    return this.pk_material;
  }

  @Override
  public void setAttributeValue(String name, Object value) {
    // TODO Auto-generated method stub
    if (HisSalequoVO.PK_MATERIAL.equals(name)) {
      this.pk_material = (String) value;
    }
    if (HisSalequoVO.HISPRICE.equals(name)) {
      this.hisprice = (UFDouble) value;
    }
    if (HisSalequoVO.NEWPRICE.equals(name)) {
      this.newprice = (UFDouble) value;
    }
    if (HisSalequoVO.CROWNO.equals(name)) {
      this.crowno = (String) value;
    }
    if (HisSalequoVO.PK_CURRTYPE.equals(name)) {
      this.pk_currtype = (String) value;
    }
  }

  public String getPk_currtype() {
    return pk_currtype;
  }

  public void setPk_currtype(String pk_currtype) {
    this.pk_currtype = pk_currtype;
  }

  public void setCrowno(String crowno) {
    this.crowno = crowno;
  }

  public void setHisprice(UFDouble hisprice) {
    this.hisprice = hisprice;
  }

  public void setNewprice(UFDouble newprice) {
    this.newprice = newprice;
  }

  public void setPk_material(String pkMaterial) {
    this.pk_material = pkMaterial;
  }

  @Override
  public void validate() throws ValidationException {
    // TODO Auto-generated method stub

  }

}
