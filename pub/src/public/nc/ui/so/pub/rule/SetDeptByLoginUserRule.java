package nc.ui.so.pub.rule;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.org.DeptVO;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.so.pub.keyvalue.IKeyValue;
import nc.vo.uif2.LoginContext;

import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.rbac.UserManageQuery;

/**
 * 根据登陆用户带人员部门
 * 
 * @since 6.3
 * @version 2013-10-31 上午09:38:11
 * @author 董礼
 */
public class SetDeptByLoginUserRule {

  private IKeyValue keyValue;

  // 部门最新版本字段值
  private String pk_dept_vKey;

  // 部门字段值
  private String pk_deptKey;

  private String pk_login_user;

  private String pk_org;

  // 人员字段值
  private String pk_psndocKey;

  /**
   * 构造方法
   * 
   * @param keyvalue
   * @param context
   * @param pk_psndocKey
   * @param pk_deptKey
   * @param pk_dept_vKey
   */
  public SetDeptByLoginUserRule(IKeyValue keyvalue, LoginContext context,
      String pk_psndocKey, String pk_deptKey, String pk_dept_vKey) {
    this(keyvalue, pk_psndocKey, pk_deptKey, pk_dept_vKey, context
        .getPk_loginUser(), context.getPk_org());
  }

  /**
   * 构造方法
   * 
   * @param keyvalue
   * @param pk_psndocKey
   * @param pk_deptKey
   * @param pk_dept_vKey
   * @param pk_user
   * @param pk_org
   */
  public SetDeptByLoginUserRule(IKeyValue keyvalue, String pk_psndocKey,
      String pk_deptKey, String pk_dept_vKey, String pk_user, String pk_org) {
    this.keyValue = keyvalue;
    this.pk_psndocKey = pk_psndocKey;
    this.pk_deptKey = pk_deptKey;
    this.pk_dept_vKey = pk_dept_vKey;
    this.pk_org = pk_org;
    this.pk_login_user = pk_user;
  }

  /**
   * 构造方法
   */
  public void setPsnAndDept() {

    if (this.keyValue.getHeadValue(this.pk_psndocKey) == null) {
      String psnid = UserManageQuery.queryPsndocByUserid(this.pk_login_user);
      this.keyValue.setHeadValue(this.pk_psndocKey, psnid);
    }

    String psn = (String) this.keyValue.getHeadValue(this.pk_psndocKey);
    if (StringUtil.isEmptyWithTrim(psn)) {
      return;
    }
    if (StringUtils.isBlank(this.pk_org)) {
      return;
    }

    MapList<String, String> dept =
        PsndocPubService.queryDeptIDByPsndocIDs(new String[] {
          psn
        }, this.pk_org);

    if (null != dept && null != dept.get(psn) && dept.get(psn).size() == 1) {
      if (this.canSetDept(dept.get(psn).get(0))) {
        this.keyValue.setHeadValue(this.pk_deptKey, dept.get(psn).get(0));
        String dept_v = this.getLastdept_v(dept.get(psn).get(0));
        this.keyValue.setHeadValue(this.pk_dept_vKey, dept_v);
      }
    }
    else {
      this.keyValue.setHeadValue(this.pk_psndocKey, null);
    }
  }

  private String getLastdept_v(String dept) {
    Map<String, String> retMap =
        DeptPubService.getLastVIDSByDeptIDS(new String[] {
          dept
        });
    return retMap.get(dept);
  }

  /**
   * 部门是否组织看见
   * 
   * @param pk_dept
   * @param pk_org
   * @return
   */
  private boolean canSetDept(String pk_dept) {
    DeptVO[] retDept = DeptPubService.queryDeptVOsByPKS(new String[] {
      pk_dept
    });
    return StringUtils.equals(retDept[0].getPk_org(), this.pk_org);
  }

}
