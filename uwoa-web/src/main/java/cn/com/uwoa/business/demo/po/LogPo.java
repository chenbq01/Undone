package cn.com.uwoa.business.demo.po;

import org.apache.solr.client.solrj.beans.Field;

public class LogPo {
	@Field
	private String id;

	@Field
	private String log_thread;

	@Field
	private String log_level;

	@Field
	private String log_class;

	@Field
	private String log_message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLog_thread() {
		return log_thread;
	}

	public void setLog_thread(String log_thread) {
		this.log_thread = log_thread;
	}

	public String getLog_level() {
		return log_level;
	}

	public void setLog_level(String log_level) {
		this.log_level = log_level;
	}

	public String getLog_class() {
		return log_class;
	}

	public void setLog_class(String log_class) {
		this.log_class = log_class;
	}

	public String getLog_message() {
		return log_message;
	}

	public void setLog_message(String log_message) {
		this.log_message = log_message;
	}

}
