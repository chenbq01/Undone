package cn.com.uwoa.global.log;

import org.apache.log4j.Category;
import org.apache.log4j.Priority;
import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;

public class CustomJDBCAppender extends JDBCAppender {

	protected String getLogStatement(LoggingEvent event) {
		String fqnOfCategoryClass = event.fqnOfCategoryClass;
		Category logger = event.getLogger();
		Priority level = event.getLevel();
		Object message = event.getMessage();
		Throwable throwable = null;
		CustomLoggingEvent bEvent = new CustomLoggingEvent(fqnOfCategoryClass,
				logger, level, message, throwable);
		return super.getLogStatement(bEvent);
	}

}
