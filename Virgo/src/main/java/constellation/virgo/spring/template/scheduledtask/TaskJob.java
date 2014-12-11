package constellation.virgo.spring.template.scheduledtask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("taskJob")
public class TaskJob {

	private static final Logger logger = LoggerFactory.getLogger(TaskJob.class);

	// cron：指定cron表达式
	// fixedDelay：即表示从上一个任务完成开始到下一个任务开始的间隔，单位是毫秒。
	// fixedRate：即从上一个任务开始到下一个任务开始的间隔，单位是毫秒。

	// cronExpression的配置说明
	// 字段 允许值 允许的特殊字符
	// 秒 0-59 , - * /
	// 分 0-59 , - * /
	// 小时 0-23 , - * /
	// 日期 1-31 , - * ? / L W C
	// 月份 1-12 或者 JAN-DEC , - * /
	// 星期 1-7 或者 SUN-SAT , - * ? / L C #
	// 年（可选） 留空, 1970-2099 , - * /
	// - 区间
	// * 通配符
	// ? 你不想设置那个字段
	// 下面只例出几个式子
	//
	// CRON表达式 含义
	// "0 0 12 * * ?" 每天中午十二点触发
	// "0 15 10 ? * *" 每天早上10：15触发
	// "0 15 10 * * ?" 每天早上10：15触发
	// "0 15 10 * * ? *" 每天早上10：15触发
	// "0 15 10 * * ? 2005" 2005年的每天早上10：15触发
	// "0 * 14 * * ?" 每天从下午2点开始到2点59分每分钟一次触发
	// "0 0/5 14 * * ?" 每天从下午2点开始到2：55分结束每5分钟一次触发
	// "0 0/5 14,18 * * ?" 每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
	// "0 0-5 14 * * ?" 每天14:00至14:05每分钟一次触发
	// "0 10,44 14 ? 3 WED" 三月的每周三的14：10和14：44触发
	// "0 15 10 ? * MON-FRI" 每个周一、周二、周三、周四、周五的10：15触发

	// @Scheduled(cron = "0/5 * * * * ?")
	public void job() {
		logger.info("任务进行中... ...");
	}
	
	public void quartzJob() {
		logger.info("quartz任务进行中... ...");
	}
	
}
