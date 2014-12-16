package com.iyangcong.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "onion", type = "groups", shards = 5, replicas = 1, indexStoreType = "memory", refreshInterval = "-1")
public class Group {

	@Id
	private String _id;

	@Version
	private Long _version;

	private String name;

	private String checkstatus;

	private String description;

	private String nickname;

	private String topicnum;

	private String usernum;

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public Long get_version() {
		return _version;
	}

	public void set_version(Long _version) {
		this._version = _version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTopicnum() {
		return topicnum;
	}

	public void setTopicnum(String topicnum) {
		this.topicnum = topicnum;
	}

	public String getUsernum() {
		return usernum;
	}

	public void setUsernum(String usernum) {
		this.usernum = usernum;
	}

	@Override
	public String toString() {
		return "Group [_id=" + _id + ", _version=" + _version + ", name=" + name + ", checkstatus=" + checkstatus + ", description=" + description + ", nickname=" + nickname + ", topicnum=" + topicnum + ", usernum=" + usernum + "]";
	}

}
