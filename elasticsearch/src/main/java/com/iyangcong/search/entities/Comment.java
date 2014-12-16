package com.iyangcong.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "onion", type = "comments", shards = 5, replicas = 1, indexStoreType = "memory", refreshInterval = "-1")
public class Comment {

	@Id
	private String _id;

	@Version
	private Long _version;

	private String language;

	private String bookid;

	private String chapterid;

	private String checkstatus;

	private String commented_content;

	private String content;

	private String nickname;

	private String segmentid;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getChapterid() {
		return chapterid;
	}

	public void setChapterid(String chapterid) {
		this.chapterid = chapterid;
	}

	public String getCheckstatus() {
		return checkstatus;
	}

	public void setCheckstatus(String checkstatus) {
		this.checkstatus = checkstatus;
	}

	public String getCommented_content() {
		return commented_content;
	}

	public void setCommented_content(String commented_content) {
		this.commented_content = commented_content;
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

	public String getSegmentid() {
		return segmentid;
	}

	public void setSegmentid(String segmentid) {
		this.segmentid = segmentid;
	}

	@Override
	public String toString() {
		return "Comment [_id=" + _id + ", _version=" + _version + ", language=" + language + ", bookid=" + bookid + ", chapterid=" + chapterid + ", checkstatus=" + checkstatus + ", commented_content=" + commented_content + ", content=" + content + ", nickname=" + nickname + ", segmentid=" + segmentid + "]";
	}

}
