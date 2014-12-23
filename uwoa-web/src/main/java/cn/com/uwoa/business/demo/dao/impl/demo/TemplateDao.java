package cn.com.uwoa.business.demo.dao.impl.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import cn.com.uwoa.business.demo.dao.ITemplateDao;
import cn.com.uwoa.business.demo.po.LogPo;
import cn.com.uwoa.business.demo.po.UserPo;
import cn.com.uwoa.global.exception.DaoException;

@Repository
public class TemplateDao implements ITemplateDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SolrServer solrServer;

	@Override
	public List<UserPo> getAllUsers() {
		return jdbcTemplate.query("select u.ID,u.Name,u.Password from user u",
				new RowMapper<UserPo>() {

					@Override
					public UserPo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						UserPo user = new UserPo();
						user.setId(rs.getInt("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("Password"));
						return user;
					}

				});
	}

	@Override
	public UserPo getUserByID(String userid) {
		return jdbcTemplate.queryForObject(
				"select u.ID,u.Name,u.Password from user u where u.ID = '"
						+ userid + "'", new RowMapper<UserPo>() {

					@Override
					public UserPo mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						UserPo user = new UserPo();
						user.setId(rs.getInt("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("Password"));
						return user;
					}

				});
	}

	@Override
	public List<LogPo> getLogFromSolrByLogMsg(String msg) {
		SolrQuery query = new SolrQuery();
		query.addField("id");
		query.addField("log_thread");
		query.addField("log_level");
		query.addField("log_class");
		query.addField("log_message");
		query.setQuery("log_class:" + msg + " or log_message:" + msg);
		query.addSort("id", SolrQuery.ORDER.desc);
		QueryResponse qrsp = null;
		try {
			qrsp = solrServer.query(query);
		} catch (SolrServerException e) {
			throw new DaoException("solr出错", e);
		}
		SolrDocumentList docs = qrsp.getResults();
		Iterator<SolrDocument> it = docs.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
		return qrsp.getBeans(LogPo.class);
	}

}
