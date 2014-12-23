package cn.com.uwoa.report.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.uwoa.report.service.IReportService;
import cn.com.uwoa.system.assembly.AssemblyHelper;
import cn.com.uwoa.system.assembly.dao.impl.AssemblyDao;
import cn.com.uwoa.system.frame.po.FieldPo;
import cn.com.uwoa.system.frame.po.TablePo;
import cn.com.uwoa.system.frame.service.impl.FrameService;
import cn.com.uwoa.system.tools.Pinyin;

/**
 * 报表 - 业务服务层实现类
 * @author liyue
 */
@Service
public class ReportService extends FrameService implements IReportService {

	@Override
	public Map<String, Object> query(Map<String, Object> condMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int edit(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return 0;
	}
}
