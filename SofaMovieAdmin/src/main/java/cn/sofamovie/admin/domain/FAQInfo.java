package cn.sofamovie.admin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dante
 * 
 */
@Entity
@Table(name = "tbl_faqs")
public class FAQInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true)
	private Long id;

	@Column(name = "createuser")
	private String createuser;

	@Column(name = "createdtime")
	private String createdtime;

	@Column(name = "updateuser")
	private String updateuser;

	@Column(name = "updatedtime")
	private String updatedtime;

	@Column(name = "deleteflag")
	private Integer deleteflag = 0;

	@Column(name = "categoryid", nullable = false)
	private Long categoryid;

	@Column(name = "question", nullable = false, unique = false, length = 400)
	private String question;

	@Column(name = "answer", nullable = false, unique = false, length = 4000)
	private String answer;

	@Column(name = "orderno", nullable = false, unique = false)
	private Integer orderno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getCreatedtime() {
		return createdtime;
	}

	public void setCreatedtime(String createdtime) {
		this.createdtime = createdtime;
	}

	public String getUpdateuser() {
		return updateuser;
	}

	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}

	public String getUpdatedtime() {
		return updatedtime;
	}

	public void setUpdatedtime(String updatedtime) {
		this.updatedtime = updatedtime;
	}

	public Integer getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Integer deleteflag) {
		this.deleteflag = deleteflag;
	}

	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getOrderno() {
		return orderno;
	}

	public void setOrderno(Integer orderno) {
		this.orderno = orderno;
	}

	@Override
	public String toString() {
		return "FAQInfo [id=" + id + ", createuser=" + createuser
				+ ", createdtime=" + createdtime + ", updateuser=" + updateuser
				+ ", updatedtime=" + updatedtime + ", deleteflag=" + deleteflag
				+ ", categoryid=" + categoryid + ", question=" + question
				+ ", answer=" + answer + ", orderno=" + orderno + "]";
	}

}
