/*    */ package nc.jdbc.framework.util;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.ObjectInputStream;
/*    */ import java.io.ObjectOutputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.io.Serializable;
/*    */ import nc.bs.logging.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InOutUtil
/*    */ {
/*    */   public static int readLine(InputStream in, OutputStream out)
/*    */     throws IOException
/*    */   {
/* 20 */     int count = 0;
/*    */     for (;;) {
/* 22 */       int b = in.read();
/*    */       
/* 24 */       if (b == -1) {
/*    */         break;
/*    */       }
/* 27 */       count++;
/* 28 */       out.write(b);
/* 29 */       if (b == 10) {
/*    */         break;
/*    */       }
/*    */     }
/* 33 */     return count;
/*    */   }
/*    */   
/*    */   public static byte[] serialize(Serializable s) throws IOException {
/* 37 */     if (s == null)
/* 38 */       return null;
/* 39 */     ByteArrayOutputStream bo = new ByteArrayOutputStream();
/* 40 */     ObjectOutputStream os = new ObjectOutputStream(bo);
/* 41 */     os.writeObject(s);
/* 42 */     return bo.toByteArray();
/*    */   }
/*    */   
/*    */   public static synchronized Serializable deserialize(byte[] ba) {
/* 46 */     Serializable value = null;
/*    */     try {
/* 48 */       if (ba == null)
/* 49 */         return null;
/* 50 */       ByteArrayInputStream bi = new ByteArrayInputStream(ba);
/* 51 */       ObjectInputStream is = new ObjectInputStream(bi);
/*    */       
/* 53 */       value = (Serializable)is.readObject();
/*    */     }
/*    */     catch (IOException e) {
/* 56 */       Logger.error("deserialize error", e);
/* 57 */       return ba;
/*    */     } catch (ClassNotFoundException e) {
/* 59 */       Logger.error("deserialize error", e);
/*    */     }
/*    */     
/* 62 */     return value;
/*    */   }
/*    */ }

