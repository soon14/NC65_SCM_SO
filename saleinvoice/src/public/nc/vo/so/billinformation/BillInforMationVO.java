package nc.vo.so.billinformation;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2017-12-25
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class BillInforMationVO extends SuperVO {
	
/**
*主键
*/
public java.lang.String pk_billinformation;
/**
*组织
*/
public java.lang.String pk_org;
/**
*组织版本
*/
public java.lang.String pk_org_v;
/**
*业务流程
*/
public java.lang.String business;
/**
*单据类型
*/
public java.lang.String billtype;
/**
*集团
*/
public java.lang.String pk_group;
/**
*金税票号
*/
public java.lang.String taxbillno;
/**
*物料
*/
public java.lang.String invname;
/**
*无税金额
*/
public java.lang.String notaxmny;
/**
*税率
*/
public java.lang.String taxrate;
/**
*税额
*/
public java.lang.String taxmny;
/**
*备注
*/
public java.lang.String memo;
/**
*来源表头id
*/
public java.lang.String srchvoid;
/**
*来源表体id
*/
public java.lang.String srcbvoid;
/**
*来源单据类型
*/
public java.lang.String srctype;
/**
*单据号
*/
public java.lang.String code;
/**
*单据日期
*/
public UFDate dbilldate;
/**
*创建人
*/
public java.lang.String creator;
/**
*创建时间
*/
public UFDateTime creationtime;
/**
*修改人
*/
public java.lang.String modifier;
/**
*修改时间
*/
public UFDateTime modifiedtime;
/**
*自定义项1
*/
public java.lang.String def1;
/**
*自定义项2
*/
public java.lang.String def2;
/**
*自定义项3
*/
public java.lang.String def3;
/**
*自定义项30
*/
public java.lang.String def30;
/**
*自定义项29
*/
public java.lang.String def29;
/**
*自定义项28
*/
public java.lang.String def28;
/**
*自定义项27
*/
public java.lang.String def27;
/**
*自定义项26
*/
public java.lang.String def26;
/**
*自定义项25
*/
public java.lang.String def25;
/**
*自定义项24
*/
public java.lang.String def24;
/**
*自定义项23
*/
public java.lang.String def23;
/**
*自定义项22
*/
public java.lang.String def22;
/**
*自定义项21
*/
public java.lang.String def21;
/**
*自定义项20
*/
public java.lang.String def20;
/**
*自定义项19
*/
public java.lang.String def19;
/**
*自定义项18
*/
public java.lang.String def18;
/**
*自定义项17
*/
public java.lang.String def17;
/**
*自定义项16
*/
public java.lang.String def16;
/**
*自定义项15
*/
public java.lang.String def15;
/**
*自定义项14
*/
public java.lang.String def14;
/**
*自定义项13
*/
public java.lang.String def13;
/**
*自定义项12
*/
public java.lang.String def12;
/**
*自定义项11
*/
public java.lang.String def11;
/**
*自定义项10
*/
public java.lang.String def10;
/**
*自定义项9
*/
public java.lang.String def9;
/**
*自定义项8
*/
public java.lang.String def8;
/**
*自定义项4
*/
public java.lang.String def4;
/**
*自定义项5
*/
public java.lang.String def5;
/**
*自定义项6
*/
public java.lang.String def6;
/**
*自定义项7
*/
public java.lang.String def7;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_billinformation的Getter方法.属性名：主键
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getPk_billinformation() {
return this.pk_billinformation;
} 

/**
* 属性pk_billinformation的Setter方法.属性名：主键
* 创建日期:2017-12-25
* @param newPk_billinformation java.lang.String
*/
public void setPk_billinformation ( java.lang.String pk_billinformation) {
this.pk_billinformation=pk_billinformation;
} 
 
/**
* 属性 pk_org的Getter方法.属性名：组织
*  创建日期:2017-12-25
* @return nc.vo.org.FinanceOrgVO
*/
public java.lang.String getPk_org() {
return this.pk_org;
} 

/**
* 属性pk_org的Setter方法.属性名：组织
* 创建日期:2017-12-25
* @param newPk_org nc.vo.org.FinanceOrgVO
*/
public void setPk_org ( java.lang.String pk_org) {
this.pk_org=pk_org;
} 
 
/**
* 属性 pk_org_v的Getter方法.属性名：组织版本
*  创建日期:2017-12-25
* @return nc.vo.vorg.OrgVersionVO
*/
public java.lang.String getPk_org_v() {
return this.pk_org_v;
} 

/**
* 属性pk_org_v的Setter方法.属性名：组织版本
* 创建日期:2017-12-25
* @param newPk_org_v nc.vo.vorg.OrgVersionVO
*/
public void setPk_org_v ( java.lang.String pk_org_v) {
this.pk_org_v=pk_org_v;
} 
 
/**
* 属性 business的Getter方法.属性名：业务流程
*  创建日期:2017-12-25
* @return nc.vo.pf.pub.BusitypeVO
*/
public java.lang.String getBusiness() {
return this.business;
} 

/**
* 属性business的Setter方法.属性名：业务流程
* 创建日期:2017-12-25
* @param newBusiness nc.vo.pf.pub.BusitypeVO
*/
public void setBusiness ( java.lang.String business) {
this.business=business;
} 
 
/**
* 属性 billtype的Getter方法.属性名：单据类型
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getBilltype() {
return this.billtype;
} 

/**
* 属性billtype的Setter方法.属性名：单据类型
* 创建日期:2017-12-25
* @param newBilltype java.lang.String
*/
public void setBilltype ( java.lang.String billtype) {
this.billtype=billtype;
} 
 
/**
* 属性 pk_group的Getter方法.属性名：集团
*  创建日期:2017-12-25
* @return nc.vo.org.GroupVO
*/
public java.lang.String getPk_group() {
return this.pk_group;
} 

/**
* 属性pk_group的Setter方法.属性名：集团
* 创建日期:2017-12-25
* @param newPk_group nc.vo.org.GroupVO
*/
public void setPk_group ( java.lang.String pk_group) {
this.pk_group=pk_group;
} 
 
/**
* 属性 taxbillno的Getter方法.属性名：金税票号
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getTaxbillno() {
return this.taxbillno;
} 

/**
* 属性taxbillno的Setter方法.属性名：金税票号
* 创建日期:2017-12-25
* @param newTaxbillno java.lang.String
*/
public void setTaxbillno ( java.lang.String taxbillno) {
this.taxbillno=taxbillno;
} 
 
/**
* 属性 invname的Getter方法.属性名：物料
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getInvname() {
return this.invname;
} 

/**
* 属性invname的Setter方法.属性名：物料
* 创建日期:2017-12-25
* @param newInvname java.lang.String
*/
public void setInvname ( java.lang.String invname) {
this.invname=invname;
} 
 
/**
* 属性 notaxmny的Getter方法.属性名：无税金额
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getNotaxmny() {
return this.notaxmny;
} 

/**
* 属性notaxmny的Setter方法.属性名：无税金额
* 创建日期:2017-12-25
* @param newNotaxmny java.lang.String
*/
public void setNotaxmny ( java.lang.String notaxmny) {
this.notaxmny=notaxmny;
} 
 
/**
* 属性 taxrate的Getter方法.属性名：税率
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getTaxrate() {
return this.taxrate;
} 

/**
* 属性taxrate的Setter方法.属性名：税率
* 创建日期:2017-12-25
* @param newTaxrate java.lang.String
*/
public void setTaxrate ( java.lang.String taxrate) {
this.taxrate=taxrate;
} 
 
/**
* 属性 taxmny的Getter方法.属性名：税额
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getTaxmny() {
return this.taxmny;
} 

/**
* 属性taxmny的Setter方法.属性名：税额
* 创建日期:2017-12-25
* @param newTaxmny java.lang.String
*/
public void setTaxmny ( java.lang.String taxmny) {
this.taxmny=taxmny;
} 
 
/**
* 属性 memo的Getter方法.属性名：备注
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getMemo() {
return this.memo;
} 

/**
* 属性memo的Setter方法.属性名：备注
* 创建日期:2017-12-25
* @param newMemo java.lang.String
*/
public void setMemo ( java.lang.String memo) {
this.memo=memo;
} 
 
/**
* 属性 srchvoid的Getter方法.属性名：来源表头id
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getSrchvoid() {
return this.srchvoid;
} 

/**
* 属性srchvoid的Setter方法.属性名：来源表头id
* 创建日期:2017-12-25
* @param newSrchvoid java.lang.String
*/
public void setSrchvoid ( java.lang.String srchvoid) {
this.srchvoid=srchvoid;
} 
 
/**
* 属性 srcbvoid的Getter方法.属性名：来源表体id
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getSrcbvoid() {
return this.srcbvoid;
} 

/**
* 属性srcbvoid的Setter方法.属性名：来源表体id
* 创建日期:2017-12-25
* @param newSrcbvoid java.lang.String
*/
public void setSrcbvoid ( java.lang.String srcbvoid) {
this.srcbvoid=srcbvoid;
} 
 
/**
* 属性 srctype的Getter方法.属性名：来源单据类型
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getSrctype() {
return this.srctype;
} 

/**
* 属性srctype的Setter方法.属性名：来源单据类型
* 创建日期:2017-12-25
* @param newSrctype java.lang.String
*/
public void setSrctype ( java.lang.String srctype) {
this.srctype=srctype;
} 
 
/**
* 属性 code的Getter方法.属性名：单据号
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getCode() {
return this.code;
} 

/**
* 属性code的Setter方法.属性名：单据号
* 创建日期:2017-12-25
* @param newCode java.lang.String
*/
public void setCode ( java.lang.String code) {
this.code=code;
} 
 
/**
* 属性 dbilldate的Getter方法.属性名：单据日期
*  创建日期:2017-12-25
* @return nc.vo.pub.lang.UFDate
*/
public UFDate getDbilldate() {
return this.dbilldate;
} 

/**
* 属性dbilldate的Setter方法.属性名：单据日期
* 创建日期:2017-12-25
* @param newDbilldate nc.vo.pub.lang.UFDate
*/
public void setDbilldate ( UFDate dbilldate) {
this.dbilldate=dbilldate;
} 
 
/**
* 属性 creator的Getter方法.属性名：创建人
*  创建日期:2017-12-25
* @return nc.vo.sm.UserVO
*/
public java.lang.String getCreator() {
return this.creator;
} 

/**
* 属性creator的Setter方法.属性名：创建人
* 创建日期:2017-12-25
* @param newCreator nc.vo.sm.UserVO
*/
public void setCreator ( java.lang.String creator) {
this.creator=creator;
} 
 
/**
* 属性 creationtime的Getter方法.属性名：创建时间
*  创建日期:2017-12-25
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getCreationtime() {
return this.creationtime;
} 

/**
* 属性creationtime的Setter方法.属性名：创建时间
* 创建日期:2017-12-25
* @param newCreationtime nc.vo.pub.lang.UFDateTime
*/
public void setCreationtime ( UFDateTime creationtime) {
this.creationtime=creationtime;
} 
 
/**
* 属性 modifier的Getter方法.属性名：修改人
*  创建日期:2017-12-25
* @return nc.vo.sm.UserVO
*/
public java.lang.String getModifier() {
return this.modifier;
} 

/**
* 属性modifier的Setter方法.属性名：修改人
* 创建日期:2017-12-25
* @param newModifier nc.vo.sm.UserVO
*/
public void setModifier ( java.lang.String modifier) {
this.modifier=modifier;
} 
 
/**
* 属性 modifiedtime的Getter方法.属性名：修改时间
*  创建日期:2017-12-25
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getModifiedtime() {
return this.modifiedtime;
} 

/**
* 属性modifiedtime的Setter方法.属性名：修改时间
* 创建日期:2017-12-25
* @param newModifiedtime nc.vo.pub.lang.UFDateTime
*/
public void setModifiedtime ( UFDateTime modifiedtime) {
this.modifiedtime=modifiedtime;
} 
 
/**
* 属性 def1的Getter方法.属性名：自定义项1
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef1() {
return this.def1;
} 

/**
* 属性def1的Setter方法.属性名：自定义项1
* 创建日期:2017-12-25
* @param newDef1 java.lang.String
*/
public void setDef1 ( java.lang.String def1) {
this.def1=def1;
} 
 
/**
* 属性 def2的Getter方法.属性名：自定义项2
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef2() {
return this.def2;
} 

/**
* 属性def2的Setter方法.属性名：自定义项2
* 创建日期:2017-12-25
* @param newDef2 java.lang.String
*/
public void setDef2 ( java.lang.String def2) {
this.def2=def2;
} 
 
/**
* 属性 def3的Getter方法.属性名：自定义项3
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef3() {
return this.def3;
} 

/**
* 属性def3的Setter方法.属性名：自定义项3
* 创建日期:2017-12-25
* @param newDef3 java.lang.String
*/
public void setDef3 ( java.lang.String def3) {
this.def3=def3;
} 
 
/**
* 属性 def30的Getter方法.属性名：自定义项30
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef30() {
return this.def30;
} 

/**
* 属性def30的Setter方法.属性名：自定义项30
* 创建日期:2017-12-25
* @param newDef30 java.lang.String
*/
public void setDef30 ( java.lang.String def30) {
this.def30=def30;
} 
 
/**
* 属性 def29的Getter方法.属性名：自定义项29
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef29() {
return this.def29;
} 

/**
* 属性def29的Setter方法.属性名：自定义项29
* 创建日期:2017-12-25
* @param newDef29 java.lang.String
*/
public void setDef29 ( java.lang.String def29) {
this.def29=def29;
} 
 
/**
* 属性 def28的Getter方法.属性名：自定义项28
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef28() {
return this.def28;
} 

/**
* 属性def28的Setter方法.属性名：自定义项28
* 创建日期:2017-12-25
* @param newDef28 java.lang.String
*/
public void setDef28 ( java.lang.String def28) {
this.def28=def28;
} 
 
/**
* 属性 def27的Getter方法.属性名：自定义项27
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef27() {
return this.def27;
} 

/**
* 属性def27的Setter方法.属性名：自定义项27
* 创建日期:2017-12-25
* @param newDef27 java.lang.String
*/
public void setDef27 ( java.lang.String def27) {
this.def27=def27;
} 
 
/**
* 属性 def26的Getter方法.属性名：自定义项26
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef26() {
return this.def26;
} 

/**
* 属性def26的Setter方法.属性名：自定义项26
* 创建日期:2017-12-25
* @param newDef26 java.lang.String
*/
public void setDef26 ( java.lang.String def26) {
this.def26=def26;
} 
 
/**
* 属性 def25的Getter方法.属性名：自定义项25
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef25() {
return this.def25;
} 

/**
* 属性def25的Setter方法.属性名：自定义项25
* 创建日期:2017-12-25
* @param newDef25 java.lang.String
*/
public void setDef25 ( java.lang.String def25) {
this.def25=def25;
} 
 
/**
* 属性 def24的Getter方法.属性名：自定义项24
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef24() {
return this.def24;
} 

/**
* 属性def24的Setter方法.属性名：自定义项24
* 创建日期:2017-12-25
* @param newDef24 java.lang.String
*/
public void setDef24 ( java.lang.String def24) {
this.def24=def24;
} 
 
/**
* 属性 def23的Getter方法.属性名：自定义项23
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef23() {
return this.def23;
} 

/**
* 属性def23的Setter方法.属性名：自定义项23
* 创建日期:2017-12-25
* @param newDef23 java.lang.String
*/
public void setDef23 ( java.lang.String def23) {
this.def23=def23;
} 
 
/**
* 属性 def22的Getter方法.属性名：自定义项22
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef22() {
return this.def22;
} 

/**
* 属性def22的Setter方法.属性名：自定义项22
* 创建日期:2017-12-25
* @param newDef22 java.lang.String
*/
public void setDef22 ( java.lang.String def22) {
this.def22=def22;
} 
 
/**
* 属性 def21的Getter方法.属性名：自定义项21
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef21() {
return this.def21;
} 

/**
* 属性def21的Setter方法.属性名：自定义项21
* 创建日期:2017-12-25
* @param newDef21 java.lang.String
*/
public void setDef21 ( java.lang.String def21) {
this.def21=def21;
} 
 
/**
* 属性 def20的Getter方法.属性名：自定义项20
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef20() {
return this.def20;
} 

/**
* 属性def20的Setter方法.属性名：自定义项20
* 创建日期:2017-12-25
* @param newDef20 java.lang.String
*/
public void setDef20 ( java.lang.String def20) {
this.def20=def20;
} 
 
/**
* 属性 def19的Getter方法.属性名：自定义项19
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef19() {
return this.def19;
} 

/**
* 属性def19的Setter方法.属性名：自定义项19
* 创建日期:2017-12-25
* @param newDef19 java.lang.String
*/
public void setDef19 ( java.lang.String def19) {
this.def19=def19;
} 
 
/**
* 属性 def18的Getter方法.属性名：自定义项18
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef18() {
return this.def18;
} 

/**
* 属性def18的Setter方法.属性名：自定义项18
* 创建日期:2017-12-25
* @param newDef18 java.lang.String
*/
public void setDef18 ( java.lang.String def18) {
this.def18=def18;
} 
 
/**
* 属性 def17的Getter方法.属性名：自定义项17
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef17() {
return this.def17;
} 

/**
* 属性def17的Setter方法.属性名：自定义项17
* 创建日期:2017-12-25
* @param newDef17 java.lang.String
*/
public void setDef17 ( java.lang.String def17) {
this.def17=def17;
} 
 
/**
* 属性 def16的Getter方法.属性名：自定义项16
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef16() {
return this.def16;
} 

/**
* 属性def16的Setter方法.属性名：自定义项16
* 创建日期:2017-12-25
* @param newDef16 java.lang.String
*/
public void setDef16 ( java.lang.String def16) {
this.def16=def16;
} 
 
/**
* 属性 def15的Getter方法.属性名：自定义项15
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef15() {
return this.def15;
} 

/**
* 属性def15的Setter方法.属性名：自定义项15
* 创建日期:2017-12-25
* @param newDef15 java.lang.String
*/
public void setDef15 ( java.lang.String def15) {
this.def15=def15;
} 
 
/**
* 属性 def14的Getter方法.属性名：自定义项14
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef14() {
return this.def14;
} 

/**
* 属性def14的Setter方法.属性名：自定义项14
* 创建日期:2017-12-25
* @param newDef14 java.lang.String
*/
public void setDef14 ( java.lang.String def14) {
this.def14=def14;
} 
 
/**
* 属性 def13的Getter方法.属性名：自定义项13
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef13() {
return this.def13;
} 

/**
* 属性def13的Setter方法.属性名：自定义项13
* 创建日期:2017-12-25
* @param newDef13 java.lang.String
*/
public void setDef13 ( java.lang.String def13) {
this.def13=def13;
} 
 
/**
* 属性 def12的Getter方法.属性名：自定义项12
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef12() {
return this.def12;
} 

/**
* 属性def12的Setter方法.属性名：自定义项12
* 创建日期:2017-12-25
* @param newDef12 java.lang.String
*/
public void setDef12 ( java.lang.String def12) {
this.def12=def12;
} 
 
/**
* 属性 def11的Getter方法.属性名：自定义项11
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef11() {
return this.def11;
} 

/**
* 属性def11的Setter方法.属性名：自定义项11
* 创建日期:2017-12-25
* @param newDef11 java.lang.String
*/
public void setDef11 ( java.lang.String def11) {
this.def11=def11;
} 
 
/**
* 属性 def10的Getter方法.属性名：自定义项10
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef10() {
return this.def10;
} 

/**
* 属性def10的Setter方法.属性名：自定义项10
* 创建日期:2017-12-25
* @param newDef10 java.lang.String
*/
public void setDef10 ( java.lang.String def10) {
this.def10=def10;
} 
 
/**
* 属性 def9的Getter方法.属性名：自定义项9
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef9() {
return this.def9;
} 

/**
* 属性def9的Setter方法.属性名：自定义项9
* 创建日期:2017-12-25
* @param newDef9 java.lang.String
*/
public void setDef9 ( java.lang.String def9) {
this.def9=def9;
} 
 
/**
* 属性 def8的Getter方法.属性名：自定义项8
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef8() {
return this.def8;
} 

/**
* 属性def8的Setter方法.属性名：自定义项8
* 创建日期:2017-12-25
* @param newDef8 java.lang.String
*/
public void setDef8 ( java.lang.String def8) {
this.def8=def8;
} 
 
/**
* 属性 def4的Getter方法.属性名：自定义项4
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef4() {
return this.def4;
} 

/**
* 属性def4的Setter方法.属性名：自定义项4
* 创建日期:2017-12-25
* @param newDef4 java.lang.String
*/
public void setDef4 ( java.lang.String def4) {
this.def4=def4;
} 
 
/**
* 属性 def5的Getter方法.属性名：自定义项5
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef5() {
return this.def5;
} 

/**
* 属性def5的Setter方法.属性名：自定义项5
* 创建日期:2017-12-25
* @param newDef5 java.lang.String
*/
public void setDef5 ( java.lang.String def5) {
this.def5=def5;
} 
 
/**
* 属性 def6的Getter方法.属性名：自定义项6
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef6() {
return this.def6;
} 

/**
* 属性def6的Setter方法.属性名：自定义项6
* 创建日期:2017-12-25
* @param newDef6 java.lang.String
*/
public void setDef6 ( java.lang.String def6) {
this.def6=def6;
} 
 
/**
* 属性 def7的Getter方法.属性名：自定义项7
*  创建日期:2017-12-25
* @return java.lang.String
*/
public java.lang.String getDef7() {
return this.def7;
} 

/**
* 属性def7的Setter方法.属性名：自定义项7
* 创建日期:2017-12-25
* @param newDef7 java.lang.String
*/
public void setDef7 ( java.lang.String def7) {
this.def7=def7;
} 
 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2017-12-25
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2017-12-25
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("so.BillInforMationVO");
    }
   }
    