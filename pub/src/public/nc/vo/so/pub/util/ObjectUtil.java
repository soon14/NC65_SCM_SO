package nc.vo.so.pub.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.log.Log;

public class ObjectUtil {

  private ObjectUtil() {
    super();
  }

  /**
   * 测试流量
   */
  public static long objectSize(Object obj) {
    if (obj == null) {
      return -1;
    }
    try {
      ByteArrayOutputStream buf = new ByteArrayOutputStream();
      ObjectOutputStream o = new ObjectOutputStream(buf);
      o.writeObject(obj);
      Log.debug("Object size:" + buf.size());
      return buf.size();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return -1;
  }

  /**
   * 用序列化方法对Object进行深层复制
   * @param oIn
   * @return
   */
  public static Object serializableClone(Object oIn) {
    try {
      ByteArrayOutputStream buf = new ByteArrayOutputStream();
      ObjectOutputStream o = new ObjectOutputStream(buf);
      o.writeObject(oIn);

      ObjectInputStream in =
          new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
      return in.readObject();
    }
    catch (IOException ex) {
      ExceptionUtils.wrappException(ex);
    }
    catch (ClassNotFoundException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return oIn;
  }

}
