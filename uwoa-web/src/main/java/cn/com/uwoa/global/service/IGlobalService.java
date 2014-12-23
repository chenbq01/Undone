package cn.com.uwoa.global.service;

public interface IGlobalService {
	/**
	 * 直接执行sql语句
	 * @param sql
	 * @return
	 */
	public int executeNoQuery(String sql);
	/**
	 * 得到单个值
	 * @param sql
	 * @return
	 */
	public String getData(String sql);
}
