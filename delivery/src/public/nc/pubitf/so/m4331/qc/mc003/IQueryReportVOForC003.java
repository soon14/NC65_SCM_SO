package nc.pubitf.so.m4331.qc.mc003;

import nc.vo.pub.BusinessException;
import nc.vo.qc.c003.entity.ReportVO;

/**
 * 质检报告联查接口
 * 
 * @since 6.0
 * @version 2011-7-13 上午08:41:18
 * @author 祝会征
 */
public interface IQueryReportVOForC003 {
  ReportVO[] queryReportVOs(String[] deliverybids) throws BusinessException;
}
