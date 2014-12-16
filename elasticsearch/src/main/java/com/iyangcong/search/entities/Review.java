package com.iyangcong.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "onion", type = "reviews", shards = 5, replicas = 1, indexStoreType = "memory", refreshInterval = "-1")
public class Review {

	@Id
	private String _id;

	@Version
	private Long _version;

	private String checkstatus;

	private String content;

	private String nickname;

	private String title;

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

	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Review [_id=" + _id + ", _version=" + _version + ", checkstatus=" + checkstatus + ", content=" + content + ", nickname=" + nickname + ", title=" + title + "]";
	}

}
