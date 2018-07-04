package nc.pubimpl.so;

/**
 * 此处插入类型说明。
 * 创建日期：(2004-3-24 21:52:20)
 * @author：左小军
 */
import nc.pubitf.so.m30.ReturnAssignMatchVO;
import nc.vo.bank_cvp.compile.datastruct.IContext;

public class FunctionContex implements IContext {
  private static final long serialVersionUID = 1L;

  ReturnAssignMatchVO m_paravo;

  /**
   * FunctionContex 构造子注解。
   */
  public FunctionContex(ReturnAssignMatchVO paravo) {
    super();
    this.m_paravo = paravo;
  }

  /**
   * 得到某一种名称的背景参数
   * 创建日期：(2002-4-3 9:34:03)
   * 
   * @return java.lang.Object
   * @param name java.lang.String
   * @exception java.lang.Exception 异常说明。
   */
  @Override
  public java.lang.Object getProperty(java.lang.String name)
      throws java.lang.Exception {
    if (name == null) {
      throw new IllegalArgumentException();
    }
    if (name.equals("para")) {
      return this.m_paravo;
    }
    throw new IllegalArgumentException();
  }

  /**
   * 得到背景参数的个数
   * 创建日期：(2002-4-3 9:35:19)
   * 
   * @return int
   */
  @Override
  public int getSize() {
    return 2;
  }

  /**
   * 检测是否存在某一种背景参数。
   * 创建日期：(2002-4-3 9:34:38)
   * 
   * @return boolean
   * @param name java.lang.String
   */
  @Override
  public boolean hasProperty(String name) {
    if (name.equals("para")) {
      return true;
    }
    return false;
  }
}
