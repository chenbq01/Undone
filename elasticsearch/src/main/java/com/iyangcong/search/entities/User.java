package com.iyangcong.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "onion", type = "user", shards = 5, replicas = 1, indexStoreType = "memory", refreshInterval = "-1")
public class User {

	@Id
	private String _id;

	@Version
	private Long _version;

	private String activestatus;

	private String attentionnum;

	private String description;

	private String likenum;

	private String name;

	private String nickname;

	private String readnum;

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

	public String getActivestatus() {
		return activestatus;
	}

	public void setActivestatus(String activestatus) {
		this.activestatus = activestatus;
	}

	public String getAttentionnum() {
		return attentionnum;
	}

	public void setAttentionnum(String attentionnum) {
		this.attentionnum = attentionnum;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLikenum() {
		return likenum;
	}

	public void setLikenum(String likenum) {
		this.likenum = likenum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getReadnum() {
		return readnum;
	}

	public void setReadnum(String readnum) {
		this.readnum = readnum;
	}

	@Override
	public String toString() {
		return "User [_id=" + _id + ", _version=" + _version + ", activestatus=" + activestatus + ", attentionnum=" + attentionnum + ", description=" + description + ", likenum=" + likenum + ", name=" + name + ", nickname=" + nickname + ", readnum=" + readnum + "]";
	}

}
