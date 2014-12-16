package com.iyangcong.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "onion", type = "question_recommend", shards = 5, replicas = 1, indexStoreType = "memory", refreshInterval = "-1")
public class QuestionRecommend {

	@Id
	private String _id;

	@Version
	private Long _version;

	private String answer;

	private String back_uid;

	private String category;

	private String create_time;

	private String question;

	private String update_time;

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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getBack_uid() {
		return back_uid;
	}

	public void setBack_uid(String back_uid) {
		this.back_uid = back_uid;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	@Override
	public String toString() {
		return "QuestionRecommend [_id=" + _id + ", _version=" + _version + ", answer=" + answer + ", back_uid=" + back_uid + ", category=" + category + ", create_time=" + create_time + ", question=" + question + ", update_time=" + update_time + "]";
	}

}
