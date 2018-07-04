/*     */ package nc.uap.pf.metadata;
/*     */ 
/*     */ import java.lang.reflect.Array;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import nc.bs.dao.BaseDAO;
/*     */ import nc.itf.uap.pf.metadata.IHeadBodyQueryItf;
/*     */ import nc.md.common.AssociationKind;
/*     */ import nc.md.data.access.NCObject;
/*     */ import nc.md.model.IAssociation;
/*     */ import nc.md.model.IAttribute;
/*     */ import nc.md.model.IBean;
/*     */ import nc.md.model.IBusinessEntity;
/*     */ import nc.md.model.IColumn;
/*     */ import nc.md.model.IForeignKey;
/*     */ import nc.md.model.ITable;
/*     */ import nc.md.model.access.javamap.AggVOStyle;
/*     */ import nc.md.model.access.javamap.IBeanStyle;
/*     */ import nc.md.persist.framework.IMDPersistenceQueryService;
/*     */ import nc.md.persist.framework.MDPersistenceService;
/*     */ import nc.vo.ml.AbstractNCLangRes;
/*     */ import nc.vo.ml.NCLangRes4VoTransl;
/*     */ import nc.vo.pub.AggregatedValueObject;
/*     */ import nc.vo.pub.BusinessException;
/*     */ import nc.vo.pub.CircularlyAccessibleValueObject;
/*     */ import nc.vo.uap.pf.PFBusinessException;
/*     */ 
/*     */ public class HeadBodyQueryImpl
/*     */   extends AbstractBizInterfaceImpl implements IHeadBodyQueryItf
/*     */ {
/*     */   public HeadBodyQueryImpl(NCObject ncObj)
/*     */   {
/*  36 */     super(ncObj);
/*     */     
/*  38 */     initByBizItf(IHeadBodyQueryItf.class.getName());
/*     */   }
/*     */   
/*     */   public CircularlyAccessibleValueObject[] queryAllBodyData(String billType, String headPK, String whereString) throws BusinessException
/*     */   {
/*  43 */     IBusinessEntity be = PfMetadataTools.queryMetaOfBilltype(billType);
/*     */     
/*  45 */     IBeanStyle bs = be.getBeanStyle();
/*  46 */     if (!(bs instanceof AggVOStyle))
/*     */     {
/*     */ 
/*  49 */       throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow1", "HeadBodyQueryImpl-000000"));
/*     */     }
/*  51 */     List<IBean> relatedBeans = be.getRelatedEntities(AssociationKind.Composite, 2);
/*     */     
/*     */ 
/*     */ 
/*  55 */     if (relatedBeans.size() == 0)
/*  56 */       return null;
/*  57 */     IBean relatedBean = (IBean)relatedBeans.iterator().next();
/*  58 */     if (relatedBean == null) {
/*  59 */       return null;
/*     */     }
/*  61 */     Class c = null;
/*     */     try {
/*  63 */       c = Class.forName(relatedBean.getFullClassName());
/*     */     } catch (ClassNotFoundException e) {
/*  65 */       throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow1", "HeadBodyQueryImpl-000001"), e);
/*     */     }
/*  67 */     String strPkField = relatedBean.getTable().getForeignKeyWithEndTable(be.getTable()).getStartColumn().getName();
/*     */     
/*     */ 
/*  70 */     String strWhere = null;
/*  71 */     strWhere = strPkField + "='" + headPK + "'";
/*  72 */     if (whereString != null)
/*  73 */       strWhere = strWhere + " and (" + whereString + ")";
/*  74 */     strWhere = strWhere + " and (isnull(dr,0)=0) ";
/*     */     
/*  76 */     BaseDAO dao = new BaseDAO();
/*  77 */     Collection coBodys = dao.retrieveByClause(c, strWhere);
/*     */     
/*     */ 
/*     */ 
/*  81 */     CircularlyAccessibleValueObject[] bodyVos = (CircularlyAccessibleValueObject[])Array.newInstance(c, coBodys.size());
/*     */     
/*  83 */     int i = 0;
/*  84 */     for (Iterator iterator = coBodys.iterator(); iterator.hasNext();) {
/*  85 */       CircularlyAccessibleValueObject body = (CircularlyAccessibleValueObject)iterator.next();
/*  86 */       bodyVos[(i++)] = body;
/*     */     }
/*  88 */     return bodyVos;
/*     */   }
/*     */   
/*     */   public CircularlyAccessibleValueObject[] queryAllHeadData(String billType, String whereString)
/*     */     throws BusinessException
/*     */   {
/*  94 */     IBusinessEntity bean = PfMetadataTools.queryMetaOfBilltype(billType);
/*  95 */     Class c = null;
/*     */     try {
/*  97 */       c = Class.forName(bean.getFullClassName());
/*     */     } catch (ClassNotFoundException e) {
/*  99 */       throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow1", "HeadBodyQueryImpl-000002"), e);
/*     */     }
/*     */     
/* 102 */     BaseDAO dao = new BaseDAO();
/* 103 */     Collection coRet = dao.retrieveByClause(c, whereString);
/* 104 */     CircularlyAccessibleValueObject[] heads = (CircularlyAccessibleValueObject[])coRet.toArray(new CircularlyAccessibleValueObject[0]);
/*     */     
/*     */ 
/* 107 */     return heads;
/*     */   }
/*     */   
/*     */   public CircularlyAccessibleValueObject queryHeadData(String billType, String billId)
/*     */     throws BusinessException
/*     */   {
/* 113 */     IBusinessEntity bean = PfMetadataTools.queryMetaOfBilltype(billType);
/* 114 */     Class c = null;
/*     */     try {
/* 116 */       c = Class.forName(bean.getFullClassName());
/*     */     } catch (ClassNotFoundException e) {
/* 118 */       throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow1", "HeadBodyQueryImpl-000002"), e);
/*     */     }
/*     */     
/* 121 */     BaseDAO dao = new BaseDAO();
/* 122 */     Object objRet = dao.retrieveByPK(c, billId);
/* 123 */     return (CircularlyAccessibleValueObject)objRet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AggregatedValueObject queryAggData(String billType, String billId)
/*     */     throws BusinessException
/*     */   {
/* 131 */     IBusinessEntity be = PfMetadataTools.queryMetaOfBilltype(billType);
/* 132 */     IBeanStyle bs = be.getBeanStyle();
/* 133 */     if (!(bs instanceof AggVOStyle))
/*     */     {
/* 135 */       throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow1", "HeadBodyQueryImpl-000000"));
/*     */     }
/*     */     
/* 138 */     Class c = null;
/*     */     try {
/* 140 */       c = Class.forName(be.getFullClassName());
/* 141 */       NCObject ncobj = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByPKWithDR(c, billId, true);
/*     */       
/* 143 */       if (ncobj == null) {
/* 144 */         return null;
/*     */       }
/* 146 */       return (AggregatedValueObject)ncobj.getContainmentObject();
/*     */     } catch (ClassNotFoundException e) {
/* 148 */       throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow1", "HeadBodyQueryImpl-000002"), e);
/*     */     }
/*     */   }
/*     */   
/*     */   public HashMap<String, CircularlyAccessibleValueObject[]> queryBodyDataByCode(String billType, String headPK, HashMap<String, String> tableCode2Where)
/*     */     throws BusinessException
/*     */   {
/* 155 */     HashMap<String, CircularlyAccessibleValueObject[]> hmRet = new HashMap();
/*     */     
/* 157 */     IBusinessEntity be = PfMetadataTools.queryMetaOfBilltype(billType);
/*     */     
/* 159 */     IBeanStyle bs = be.getBeanStyle();
/* 160 */     if (!(bs instanceof AggVOStyle))
/*     */     {
/*     */ 
/* 163 */       throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow1", "HeadBodyQueryImpl-000000"));
/*     */     }
/*     */     
/* 166 */     List<IAssociation> assos = be.getAssociationsByKind(AssociationKind.Composite, 2);
/*     */     
/* 168 */     HashMap<String, IBean> hmChildBean = new HashMap();
/* 169 */     for (IAssociation asso : assos) {
/* 170 */       hmChildBean.put(asso.getStartAttribute().getName(), asso.getEndBean());
/*     */     }
/*     */     
/*     */ 
/* 174 */     for (Iterator iterator = tableCode2Where.keySet().iterator(); iterator.hasNext();) {
/* 175 */       String tCode = (String)iterator.next();
/* 176 */       IBean childBean = (IBean)hmChildBean.get(tCode);
/* 177 */       if (childBean == null) {
/* 178 */         throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow1", "HeadBodyQueryImpl-000003", null, new String[] { tCode }));
/*     */       }
/*     */       
/* 181 */       Class c = null;
/*     */       try {
/* 183 */         c = Class.forName(childBean.getFullClassName());
/*     */       } catch (ClassNotFoundException e) {
/* 185 */         throw new PFBusinessException(NCLangRes4VoTransl.getNCLangRes().getStrByID("pfworkflow1", "HeadBodyQueryImpl-000001"), e);
/*     */       }
/* 187 */       String strPkField = childBean.getTable().getForeignKeyWithEndTable(be.getTable()).getStartColumn().getName();
/*     */       
/*     */ 
/* 190 */       String strWhere = strPkField + "='" + headPK + "'";
/* 191 */       String bodyWhere = (String)tableCode2Where.get(tCode);
/* 192 */       if (bodyWhere != null)
/* 193 */         strWhere = strWhere + " and (" + bodyWhere + ")";
/* 194 */       strWhere = strWhere + " and (isnull(dr,0)=0) ";
/*     */       
/* 196 */       BaseDAO dao = new BaseDAO();
/* 197 */       Collection coBodys = dao.retrieveByClause(c, strWhere);
/*     */       
/*     */ 
/*     */ 
/* 201 */       CircularlyAccessibleValueObject[] bodyVos = (CircularlyAccessibleValueObject[])Array.newInstance(c, coBodys.size());
/*     */       
/* 203 */       int i = 0;
/* 204 */       for (Iterator iterator2 = coBodys.iterator(); iterator2.hasNext();) {
/* 205 */         CircularlyAccessibleValueObject body = (CircularlyAccessibleValueObject)iterator2.next();
/* 206 */         bodyVos[(i++)] = body;
/*     */       }
/*     */       
/* 209 */       hmRet.put(tCode, bodyVos);
/*     */     }
/* 211 */     return hmRet;
/*     */   }
/*     */ }
