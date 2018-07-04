package nc.ui.so.m30.billrefForLS41;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.bd.material.assign.IMaterialAssignService;
import nc.itf.bd.material.baseinfo.IMaterialBaseInfoService;
import nc.itf.bd.material.measdoc.IMeasdocService;
import nc.itf.bd.material.stock.IMaterialStockService;
import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.busiflow.PfButtonClickContext;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.md.persist.framework.MDPersistenceService;
import nc.pubitf.para.SysInitQuery;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.material.cost.MaterialCostmodeVO;
import nc.vo.bd.material.marbasclass.MarBasClassVO;
import nc.vo.bd.material.measdoc.MeasdocVO;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.lm.lsdlxy.AggLsxywtHVO;
import nc.vo.lm.lsdlxy.LsxywtHVO;
import nc.vo.lm.lsdlxy.LsxywtbBVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.util.TimeUtils;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

/*下游单据触发参照上游单据*/
public class AddLS41Action extends AbstractReferenceAction {

	private static final long serialVersionUID = -4417976703049420354L;

	private BillForm editor;

	private AbstractAppModel model;
	
	String pk_group = "0001N610000000000IT0";// 组织，集团直接写死
	String pk_org = "0001N610000000000IT0";
	
	UFDateTime ufDateTime = new UFDateTime();
	String wlbm=String.valueOf(ufDateTime.getMillis());

	@Override
	public void doAction(ActionEvent e) throws Exception {

		
		PfUtilClient.childButtonClickedNew(createPfButtonClickContext());
		if (PfUtilClient.isCloseOK()) {
			AggLsxywtHVO[] lsvos=(AggLsxywtHVO[]) PfUtilClient.getRetOldVos();
			//对物料进行处理
			this.processForMaterial(lsvos);
			SaleOrderVO[] vos = (SaleOrderVO[]) PfUtilClient.getRetVos();
			
			// 显示到转单界面上
			
			for (SaleOrderVO saleOrderVO : vos) {
				SaleOrderHVO hvo = saleOrderVO.getParentVO();
				
				if((String)hvo.getAttributeValue("buyccurrencyid")==null){
					continue;
				}
				UFDouble localcurrate = CurrencyRate
						.getGlobalLocalCurrencySellRate(
								(String)hvo.getAttributeValue("buyccurrencyid"),
								TimeUtils.getsrvBaseDate());
				// 给汇率赋值
				saleOrderVO.getParentVO().setAttributeValue("exchange_rate", localcurrate);
			}	
			
			
			this.getTransferViewProcessor().processBillTransfer(vos);
			
		}
	}

	private void processForMaterial(AggLsxywtHVO[] lsvos) throws BusinessException {
		// TODO 自动生成的方法存根
		List<String> maPkList = new ArrayList<String>();//存新增的销售订单
		for(AggLsxywtHVO aggvo:lsvos){
			LsxywtHVO parentVO = aggvo.getParentVO();//
			LsxywtbBVO[] bvos = (LsxywtbBVO[]) aggvo.getChildrenVO();
			for(LsxywtbBVO lsbvo:bvos){
				String zwmc=(String)lsbvo.getAttributeValue("zwmc");
				String ggxh=(String)lsbvo.getAttributeValue("ggxh");
				//判断物料是否存在
				if(!isHas(zwmc+"-"+ggxh)){
					MaterialVO returnVO = insertMaterial(lsbvo, pk_org, pk_group);// 调用更新物料档案方法
					String pk = returnVO.getPk_material();
					maPkList.add(pk);
				}
			}
			if(maPkList != null && maPkList.size()>0){
				//自动分配物料
				autoMaterialAssign(maPkList);

				//自动启用成本信息批次核算
				autoEnableBatch(maPkList);
				
				//自动启用库存信息批次管理
				autoEnableStock(maPkList);
			}

		}
	}

	private PfButtonClickContext createPfButtonClickContext() {
		PfButtonClickContext context = new PfButtonClickContext();
		context.setParent(this.getModel().getContext().getEntranceUI());
		context.setSrcBillType(this.getSourceBillType());
		context.setPk_group(this.getModel().getContext().getPk_group());
		context.setUserId(this.getModel().getContext().getPk_loginUser());
		// 如果该节点是由交易类型发布的，那么这个参数应该传交易类型，否则传单据类型
		String vtrantype = TrantypeFuncUtils.getTrantype(this.getModel()
				.getContext());
		if (StringUtil.isEmptyWithTrim(vtrantype)) {
			// 设置当前(下游)单据类型编码
			context.setCurrBilltype("30");
		} else {
			context.setCurrBilltype(vtrantype);
		}
		context.setUserObj(null);
		context.setSrcBillId(null);
		context.setBusiTypes(this.getBusitypes());
		// 上面的参数在原来调用的方法中都有涉及，只不过封成了一个整结构，下面两个参数是新加的参数
		// 上游的交易类型集合
		context.setTransTypes(this.getTranstypes());
		// 标志在交换根据目的交易类型分组时，查找目的交易类型的依据，有三个可设置值：1（根据接口定义）、
		// 2（根据流程配置）、-1（不根据交易类型分组）
		context.setClassifyMode(PfButtonClickContext.ClassifyByItfdef);
		return context;
	}

	public BillForm getEditor() {
		return this.editor;
	}

	public AbstractAppModel getModel() {
		return this.model;
	}

	public void setEditor(BillForm editor) {
		this.editor = editor;
	}

	public void setModel(AbstractAppModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	@Override
	protected boolean isActionEnable() {
		return this.model.getUiState() == UIState.NOT_EDIT;
	}
	
	/**
	 *  自动启用库存信息批次管理
	 * @author ljf
	 * @throws BusinessException 
	 */
	@SuppressWarnings("unchecked")
	private void autoEnableStock(List<String> maPkList) throws BusinessException {
		String orgsql = "select pk_org from org_orgs where pk_group <> pk_org and (orgtype43='Y' or isbusinessunit ='Y' or orgtype19='Y' ) and (enablestate = 2) and pk_group = '0001N610000000000IT0'";
		
		List<String> orglist = (List<String>) getQueryBS().executeQuery(orgsql, new ColumnListProcessor());
		if(orglist == null || orglist.size() == 0){
			return;
		}
		
		IMaterialStockService stockservice = NCLocator.getInstance().lookup(IMaterialStockService.class);
		
		SqlBuilder querysql = new SqlBuilder();
		querysql.append(MaterialStockVO.PK_MATERIAL, maPkList.toArray(new String[maPkList.size()]));
		List<MaterialStockVO> stockvolist = (List<MaterialStockVO>) getMDQueryService().queryBillOfVOByCond(MaterialStockVO.class, querysql.toString(), false);
		if(stockvolist == null || stockvolist.size() == 0){
			return;
		}
		
		for(int i = 0; i < stockvolist.size(); i++){
			MaterialStockVO stockvo = stockvolist.get(i);
			stockvo.setWholemanaflag(UFBoolean.TRUE);
		}
		
		stockservice.updateMaterialStockVOs(stockvolist.toArray(new MaterialStockVO[stockvolist.size()]));
	}
	
	private IMDPersistenceQueryService getMDQueryService() {
	    return MDPersistenceService.lookupPersistenceQueryService();
	 }
	
	/**
	 *  自动启用成本信息批次核算
	 * @author ljf
	 * @throws MetaDataException 
	 */
	@SuppressWarnings("unchecked")
	private void autoEnableBatch(List<String> maPkList) throws BusinessException {
		SqlBuilder sql = new SqlBuilder();
		sql.append(MaterialCostmodeVO.PK_MATERIAL, maPkList.toArray(new String[maPkList.size()]));
		
		List<MaterialCostmodeVO> costmodevolist = (List<MaterialCostmodeVO>) getMDQueryService().queryBillOfVOByCond(MaterialCostmodeVO.class, sql.toString(), false);
		if(costmodevolist == null || costmodevolist.size() == 0){
			return;
		}
		
		for(int i = 0; i < costmodevolist.size(); i++){
			MaterialCostmodeVO costmodevo = costmodevolist.get(i);
			costmodevo.setBatchcost(UFBoolean.TRUE);
		}
		
		HYPubBO_Client.updateAry(costmodevolist.toArray(new MaterialCostmodeVO[costmodevolist.size()]));
	}
	
	/**
	 * 判断物料是否存在
	 * @param name 
	 * @throws BusinessException 
	 */
	public Boolean isHas(String zwmc) throws BusinessException{
		String sqlmact = "select count(pk_material) from bd_material where name ='"+ zwmc + "' and nvl(dr,0)=0 ";//+"and materialspec='"+ggxh+"'";// 物料档案
		int k = 0;
		k = (Integer)getQueryBS().executeQuery(sqlmact,new ColumnProcessor());
		if (k==0) {
			return false;
		}
		return true;
	}
	
	public MaterialVO insertMaterial(LsxywtbBVO lsbvo, String pk_group,
			String pk_org) throws BusinessException {
		HYPubBO_Client bo = new HYPubBO_Client();
		IMaterialBaseInfoService maservice = NCLocator.getInstance().lookup(
				IMaterialBaseInfoService.class);
		
		MaterialVO maVO = new MaterialVO();
		maVO.setPk_group(pk_group);
		maVO.setPk_org(pk_org);
		// 默认成一般纳税商品，正式：未知
		maVO.setPk_mattaxes("1001Z01000000003W0WH");
		// 电子采购打钩
		maVO.setIselectrans(UFBoolean.FALSE);
		// 产品簇不打钩
		maVO.setProductfamily(UFBoolean.FALSE);
		// 电子销售不打钩
		maVO.setElectronicsale(UFBoolean.FALSE);
		// 适用零售不打钩
		maVO.setRetail(UFBoolean.FALSE);
		// 启用状态,已启用
		maVO.setEnablestate(2);
		// 入库容差
		maVO.setIntolerance(new UFDouble(0));
		// 入库容差
		maVO.setOuttolerance(new UFDouble(0));
		// 入库容差
		maVO.setOutcloselowerlimit(new UFDouble(0));
		// 物料名称
		if((String) lsbvo.getAttributeValue("ggxh")==null){
			maVO.setName((String) lsbvo.getAttributeValue("zwmc"));
		}else{
			maVO.setName((String) lsbvo.getAttributeValue("zwmc")+"-"+(String) lsbvo.getAttributeValue("ggxh"));
		}
		// 物料规格
		//maVO.setMaterialspec((String) lsbvo.getAttributeValue("ggxh"));// materialspec
		// 物料型号
		//maVO.setMaterialtype((String) lsbvo.getAttributeValue("ggxh"));
		// 物料分类
		String Pk_marbasclass = null;
		String code = this.getMarbasclasscode();// 物料分类编码
		MarBasClassVO[] classVO = (MarBasClassVO[]) bo.queryByCondition(
				MarBasClassVO.class, " code='" + code + "'");
		Pk_marbasclass = classVO[0].getPk_marbasclass();
		maVO.setPk_marbasclass(Pk_marbasclass);
		// 物料编码
		if (lsbvo.getAttributeValue("wlbm") == null || ((String) lsbvo.getAttributeValue("wlbm")).trim().length() == 0) {
			// 设备系统没有物料编码，用单据号作为物料编码			
			maVO.setCode(wlbm);
		} else {			
			maVO.setCode((String) lsbvo.getAttributeValue("wlbm"));
		}
		// 物料单位
		MeasdocVO[] measdocVOs = (MeasdocVO[]) bo.queryByCondition(
		// MeasdocVO.class, " name='" + srchvo.getCastunitid() + "' and code='"
		// + srchvo.getCastunitid() + "'");
				MeasdocVO.class, " code='" + "'");

		String pk_measdoc = "1001C010000000088AB4";
/*		if (measdocVOs != null && measdocVOs.length > 0) {
			pk_measdoc = measdocVOs[0].getPk_measdoc();
		} else {			
			// 自动新增计量档案
			MeasdocVO measdocVO = new MeasdocVO();
			measdocVO.setName("千克");
			if(!isMeasdoc(measdocVO.getName())){
				measdocVO.setCode(wlbm);// 编码
				measdocVO.setName("千克");// 名称
				measdocVO.setPk_group(pk_group);
				measdocVO.setPk_org("GLOBLE00000000000000");// 全局组织
				measdocVO.setOppdimen("E");// 所属量纲
				measdocVO.setBasecodeflag(UFBoolean.FALSE);// 是否为基本单位
				measdocVO.setBitnumber(4);// 小数位数
				IMeasdocService measdocSer = NCLocator.getInstance().lookup(
						IMeasdocService.class);
				measdocVO = measdocSer.insertMeasdocForPfxx(measdocVO);
				pk_measdoc = measdocVO.getPk_measdoc();
			}
		}*/
		//主计量单位
		maVO.setPk_measdoc(pk_measdoc);
		// 特征件
		maVO.setIsfeature(UFBoolean.FALSE);
		// 成套件
		maVO.setSetpartsflag(UFBoolean.FALSE);
		// 服务类
		maVO.setFee(UFBoolean.FALSE);
		// 促销商品
		maVO.setIshproitems(UFBoolean.FALSE);
		// 折扣价格
		maVO.setDiscountflag(UFBoolean.FALSE);
		// 是否最新
		maVO.setLatest(UFBoolean.FALSE);
		// 自动追加辅计量单位
		// MaterialConvertVO conVO = new MaterialConvertVO();
		// // 计量单位同主计量
		// String main_measdocID = pk_measdoc;
		// conVO.setPk_measdoc(main_measdocID);
		// // 与主计量单位换算率为1/1
		// conVO.setMeasrate("1/1");
		// // 默认为固定换算
		// conVO.setFixedflag(UFBoolean.TRUE);
		// // 自动标记所有辅计量
		// conVO.setIsprodmeasdoc(UFBoolean.TRUE);
		// conVO.setIspumeasdoc(UFBoolean.TRUE);
		// conVO.setIsretailmeasdoc(UFBoolean.TRUE);
		// conVO.setIssalemeasdoc(UFBoolean.TRUE);
		// conVO.setIsstockmeasdoc(UFBoolean.TRUE);
		// 系统导入的都没有辅计量，所以自动增加的也不设置辅计量
		// maVO.setMaterialconvert(new MaterialConvertVO[]{conVO});
//		MaterialVO returnVO = maservice.insertMaterial(maVO);
		return maservice.insertMaterial(maVO);
	}
	
	/**
	 *  自动分配物料
	 * @author ljf
	 */
	@SuppressWarnings("unchecked")
	private void autoMaterialAssign(List<String> maPkList) throws BusinessException {
		String sql = "select pk_org from org_orgs where pk_group <> pk_org and (orgtype43='Y' or isbusinessunit ='Y' or orgtype19='Y' ) and (enablestate = 2) and pk_group = '0001N610000000000IT0'";
		
		List<String> orglist = (List<String>) getQueryBS().executeQuery(sql, new ColumnListProcessor());
		if(orglist == null || orglist.size() == 0){
			return;
		}
		getAssignService().assignMaterialByPks(maPkList.toArray(new String[maPkList.size()]), orglist.toArray(new String[orglist.size()]), null);
	}
	
	/**
	 * 
	 * @author zjf 根据参数值获取物料分类编码
	 * @throws BusinessException 
	 */
	public String getMarbasclasscode() throws BusinessException {
		SysInitVO initVO = null;
		String pk_org = "GLOBLE00000000000000";// 全局参数组织主键

		initVO = SysInitQuery.querySysinitVO(pk_org, "GFCGJH001");// 获取业务参数方法（组织主键，参数编码）
		String code = initVO.getValue();// 获取参数值(物料分类编码)

		return code;

	}
	
	private IMaterialAssignService getAssignService() {
	    return NCLocator.getInstance().lookup(IMaterialAssignService.class);
	}
	
	private IUAPQueryBS getQueryBS(){
		return NCLocator.getInstance().lookup(IUAPQueryBS.class);
	}

}