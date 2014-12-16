package com.iyangcong.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "onion", type = "openclasssegment", shards = 5, replicas = 1, indexStoreType = "memory", refreshInterval = "-1")
public class OpenClassSegment {

	@Id
	private String _id;

	@Version
	private Long _version;

	private String bookid;

	private String chapterid;

	private String language;

	private String segment_en;

	private String segment_zh;

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSegment_en() {
		return segment_en;
	}

	public void setSegment_en(String segment_en) {
		this.segment_en = segment_en;
	}

	public String getSegment_zh() {
		return segment_zh;
	}

	public void setSegment_zh(String segment_zh) {
		this.segment_zh = segment_zh;
	}

	@Override
	public String toString() {
		return "OpenClassSegment [_id=" + _id + ", _version=" + _version + ", bookid=" + bookid + ", chapterid=" + chapterid + ", language=" + language + ", segment_en=" + segment_en + ", segment_zh=" + segment_zh + "]";
	}

}
