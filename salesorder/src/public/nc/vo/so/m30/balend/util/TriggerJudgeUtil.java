package nc.vo.so.m30.balend.util;

import java.util.HashSet;
import java.util.Set;

import nc.vo.so.m30.balend.enumeration.BalEndTrigger;
import nc.vo.so.m30.balend.enumeration.BalOpenTrigger;

public class TriggerJudgeUtil {

  private static TriggerJudgeUtil judgeutil = new TriggerJudgeUtil();

  /**
   * TriggerBalJudgeUtil 的构造子
   */
  private TriggerJudgeUtil() {
    // 私有构造子
  }

  public static TriggerJudgeUtil getInstance() {
    return TriggerJudgeUtil.judgeutil;
  }

  /**
   * 方法功能描述：传入触发点是否只影响销售出库单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param trigger
   * @return <p>
   * @author fengjb
   * @time 2010-7-19 下午03:19:39
   */
  public boolean isOnlyAffectOut(String trigger) {
    String[] outaffecttypes =
        new String[] {
          BalEndTrigger.OUT_CLOSE.getCode(),
          BalEndTrigger.OUT_DELETE.getCode(),
          BalOpenTrigger.OUT_OPEN.getCode(),
          BalEndTrigger.WAST_AUDIT.getCode(),
          BalEndTrigger.WAST_DELETE.getCode(),
          BalOpenTrigger.WAST_ADD.getCode(),
          BalOpenTrigger.WAST_UNAUDIT.getCode()
        };
    Set<String> hsType = new HashSet<String>();
    for (String type : outaffecttypes) {
      hsType.add(type);
    }

    return hsType.contains(trigger);

  }

  /**
   * 方法功能描述：传入触发点是否只影响销售出库单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param trigger
   * @return <p>
   * @author fengjb
   * @time 2010-7-19 下午03:19:39
   */
  public boolean isOnlyAffectOutNoWas(String trigger) {
    String[] outaffecttypes =
        new String[] {
          BalEndTrigger.OUT_CLOSE.getCode(),
          BalEndTrigger.OUT_DELETE.getCode(), BalOpenTrigger.OUT_OPEN.getCode()
        };
    Set<String> hsType = new HashSet<String>();
    for (String type : outaffecttypes) {
      hsType.add(type);
    }

    return hsType.contains(trigger);

  }

  /**
   * 方法功能描述：传入触发点是否只影响销售发票.
   * <p>
   * <b>参数说明</b>
   * 
   * @param trigger
   * @return <p>
   * @author fengjb
   * @time 2010-7-19 下午03:21:13
   */
  public boolean isOnlyAffectVoice(String trigger) {

    String[] voiceaffecttypes =
        new String[] {
          BalEndTrigger.VOICE_CLOSE.getCode(),
          BalEndTrigger.VOICE_DELETE.getCode(),
          BalOpenTrigger.VOICE_OPEN.getCode()
        };
    Set<String> hsType = new HashSet<String>();
    for (String type : voiceaffecttypes) {
      hsType.add(type);
    }

    return hsType.contains(trigger);

  }

  /**
   * 方法功能描述：传入触发点是否影响应收结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param trigger
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 下午03:11:56
   */
  public boolean isAffectIncome(String trigger) {
    String[] incometypes =
        new String[] {
          BalEndTrigger.OUT_INCOME.getCode(),
          BalEndTrigger.OUT_ESTAR.getCode(), BalEndTrigger.OUT_RUSH.getCode(),
          BalEndTrigger.OUT_CLOSE.getCode(),
          BalEndTrigger.OUT_DELETE.getCode(),
          BalEndTrigger.VOICE_INCOME.getCode(),
          BalEndTrigger.VOICE_RUSH.getCode(),
          BalEndTrigger.VOICE_ADJUST.getCode(),
          BalEndTrigger.VOICE_CLOSE.getCode(),
          BalEndTrigger.VOICE_DELETE.getCode(),
          BalEndTrigger.WAST_AUDIT.getCode(),
          BalEndTrigger.WAST_DELETE.getCode(),
          BalOpenTrigger.OUT_NOINCOME.getCode(),
          BalOpenTrigger.OUT_NORUSH.getCode(),
          BalOpenTrigger.OUT_OPEN.getCode(),
          BalOpenTrigger.VOICE_NOINCOME.getCode(),
          BalOpenTrigger.VOICE_OPEN.getCode(),
          BalOpenTrigger.WAST_UNAUDIT.getCode(),
          BalOpenTrigger.WAST_ADD.getCode()
        };
    Set<String> hsType = new HashSet<String>();
    for (String type : incometypes) {
      hsType.add(type);
    }

    return hsType.contains(trigger);
  }

  /**
   * 方法功能描述：传入触发点是否影响成本结算关闭。
   * <p>
   * <b>参数说明</b>
   * 
   * @param trigger
   * @return <p>
   * @author fengjb
   * @time 2010-7-14 下午03:12:16
   */
  public boolean isAffectCost(String trigger) {
    String[] costtypes =
        new String[] {
          BalEndTrigger.OUT_COST.getCode(), BalEndTrigger.OUT_RUSH.getCode(),
          BalEndTrigger.OUT_REGIST.getCode(),
          BalEndTrigger.OUT_CLOSE.getCode(),
          BalEndTrigger.OUT_DELETE.getCode(),
          BalEndTrigger.VOICE_COST.getCode(),
          BalEndTrigger.VOICE_CLOSE.getCode(),
          BalEndTrigger.VOICE_DELETE.getCode(),
          BalEndTrigger.WAST_AUDIT.getCode(),
          BalEndTrigger.WAST_DELETE.getCode(),
          BalOpenTrigger.OUT_NOCOST.getCode(),
          BalOpenTrigger.OUT_NORUSH.getCode(),
          BalOpenTrigger.OUT_OPEN.getCode(),
          BalOpenTrigger.VOICE_NOCOST.getCode(),
          BalOpenTrigger.VOICE_OPEN.getCode(),
          BalOpenTrigger.WAST_UNAUDIT.getCode(),
          BalOpenTrigger.WAST_ADD.getCode()
        };
    Set<String> hsType = new HashSet<String>();
    for (String type : costtypes) {
      hsType.add(type);
    }
    return hsType.contains(trigger);
  }

  public boolean isVoiceCloseTrigger(String trigger) {
    return BalEndTrigger.VOICE_CLOSE.getCode().equals(trigger);
  }
}
