package com.iyangcong.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "onion", type = "keywords", shards = 5, replicas = 1, indexStoreType = "memory", refreshInterval = "-1")
public class Keyword {

	@Id
	private String _id;

	@Version
	private Long _version;

	private String count;

	private String keyword;

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

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {
		return "Keyword [_id=" + _id + ", _version=" + _version + ", count=" + count + ", keyword=" + keyword + "]";
	}

}
