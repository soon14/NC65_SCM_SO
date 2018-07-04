package nc.vo.so.m4331.rule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.qc.pub.util.QCSysParamUtil;
import nc.vo.so.m4331.entity.DeliveryBVO;

/**
 * 发货单报检检查rule
 * 
 * @since 6.0
 * @version 2011-5-17 上午09:31:08
 * @author 祝会征
 */
public class CheckBillRule {
  // 缓存可以进行报检的发货单表体vo
  private Map<String, DeliveryBVO> bvoMap;

  private DeliveryBVO[] bvos;

  private StringBuffer errMsg;

  // 缓存物料是否可以报检
  private Map<String, UFBoolean> matMap;

  public CheckBillRule(DeliveryBVO[] vos) {
    if (null == vos) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006002_0", "04006002-0155")
      /*请选中发货单表体行进行报检。*/);
      return;
    }

    this.matMap = new HashMap<String, UFBoolean>();
    this.bvos = vos;
    this.bvoMap = new HashMap<String, DeliveryBVO>();
    for (DeliveryBVO bvo : this.bvos) {
      this.bvoMap.put(bvo.getCdeliverybid(), bvo);
    }
    this.initMatMap();
  }

  /*
   * 单据检查判断 只有负数行的发货单可以进行报检
   * 并且物料的销售组织页签“销售退货免检”设置为“否”时，才能进行报检
   */

  /**
   * 判断选中的表体行能不能做报检
   * 
   * @param bvos
   */
  public void isCheckBill() {
    this.errMsg = new StringBuffer();
    for (DeliveryBVO bvo : this.bvos) {
      // 检测选中的行是否是负数行
      this.checkOppose(bvo);
      // 检查物料
      this.checkMaterial(bvo);
      // 检查报检是否结束
      this.ischeckFinsh(bvo);
    }
    this.bvoMap.clear();
    if (this.errMsg.length() > 0) {
      ExceptionUtils.wrappBusinessException(this.errMsg.toString());
    }
  }

  /*
   * 根据物料的是否免检标记过滤出需要报检和不需要报检的表体vo
   */
  private void checkMaterial(DeliveryBVO bvo) {
    if (!this.isHave(bvo)) {
      return;
    }
    String pk_material = bvo.getCmaterialvid();
    UFBoolean flag = this.matMap.get(pk_material);
    if (!flag.booleanValue()) {
      return;
    }
    this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0156", null, new String[] {
          bvo.getCrowno()
        })/*发货单行：{0}的物料为免检物料，不能报检。*/);
    this.errMsg.append("\n");
    this.bvoMap.remove(bvo.getCdeliverybid());
  }

  /*
   * 检查发货单可以报检的行
   * 过滤出正数行和负数行 缓存到map中
   */
  private void checkOppose(DeliveryBVO bvo) {
    if (!this.isHave(bvo)) {
      return;
    }
    UFDouble num = bvo.getNnum();
    // 数量为负 可以报检
    if (num.compareTo(UFDouble.ZERO_DBL) < 0) {
      return;
    }
    this.bvoMap.remove(bvo.getCdeliverybid());
    this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4006002_0", "04006002-0157", null, new String[] {
          bvo.getCrowno()
        })/*发货单行：{0}正数行不能报检。*/);
    this.errMsg.append("\n");
  }

  private void initMatMap() {
    String pk_sendorg = null;
    // 缓存物料版本
    Set<String> materialSet = new HashSet<String>();
    for (DeliveryBVO bvo : this.bvos) {
      materialSet.add(bvo.getCmaterialvid());
      // 发货库存组织
      pk_sendorg = bvo.getCsendstockorgid();
      if (null == pk_sendorg || "".equals(pk_sendorg)) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4006002_0", "04006002-0158")/*发货单表体发货库存组织为空，不能报检。*/);
      }
      // 读取参数INI01(启用质量管理模块)的值
      if (QCSysParamUtil.getINI01(pk_sendorg) == UFBoolean.FALSE) {
        ExceptionUtils
            .wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
                .getStrByID("4006002_0", "04006002-0175")/*参数【INI01  启用质量管理模块】为否,不能行进行报检。*/);
      }
    }
    String[] pk_materials = new String[materialSet.size()];
    pk_materials = materialSet.toArray(pk_materials);
    // 查询发货库存组织下的物料的免检属性 为N的可以报检
    Map<String, MaterialStockVO> materialMap =
        MaterialPubService.queryMaterialStockInfo(pk_materials, pk_sendorg,
            new String[] {
              MaterialStockVO.ISRETFREEOFCHK, MaterialStockVO.PK_MATERIAL
            });
    if (materialMap.size() == 0) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4006002_0", "04006002-0159")/*表体行的物料没有分配到对应的发货库存组织。*/);
    }
    for (DeliveryBVO bvo : this.bvos) {
      String pk_material = bvo.getCmaterialvid();
      UFBoolean checkfreeflag =
          materialMap.get(pk_material).getIsretfreeofchk();
      if (null == checkfreeflag || !checkfreeflag.booleanValue()) {
        this.matMap.put(pk_material, UFBoolean.FALSE);
        continue;
      }
      this.matMap.put(pk_material, UFBoolean.TRUE);
    }
  }

  /*
   * 检查报检是否结束 如果是否质检结束N,是否报检为Y，则不能进行报检
   */
  private void ischeckFinsh(DeliveryBVO bvo) {
    if (!this.isHave(bvo)) {
      return;
    }
    UFBoolean checkflag = bvo.getBcheckflag();
    if (null == checkflag) {
      checkflag = UFBoolean.FALSE;
    }
    UFBoolean qualityflag = bvo.getBqualityflag();
    if (null == qualityflag) {
      qualityflag = UFBoolean.FALSE;
    }
    if (checkflag.booleanValue() && !qualityflag.booleanValue()) {
      this.errMsg.append(NCLangRes4VoTransl.getNCLangRes().getStrByID(
          "4006002_0", "04006002-0160", null, new String[] {
            bvo.getCrowno()
          })/*发货单行:{0}还没有质检结束，不能再次报检。*/);
      this.errMsg.append("\n");
      this.bvoMap.remove(bvo.getCdeliverybid());
    }
  }

  private boolean isHave(DeliveryBVO bvo) {
    String bid = bvo.getCdeliverybid();
    if (!this.bvoMap.containsKey(bid)) {
      return false;
    }
    return true;
  }
}
