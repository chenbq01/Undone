package cn.sofamovie.admin.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_images")
public class WebImage implements Serializable {

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

	@Column(name = "imagename", nullable = false, unique = false)
	private String imagename;

	@Column(name = "imageoriginalname", nullable = false, unique = false)
	private String imageoriginalname;

	@Column(name = "imageurl", nullable = false, unique = false)
	private String imageurl;

	@Column(name = "linkurl", nullable = true, unique = false)
	private String linkurl;

	@Column(name = "imagetype", nullable = false)
	private String imagetype;

	@Column(name = "sequence", nullable = false)
	private Integer sequence;

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

	public String getImagename() {
		return imagename;
	}

	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getImageoriginalname() {
		return imageoriginalname;
	}

	public void setImageoriginalname(String imageoriginalname) {
		this.imageoriginalname = imageoriginalname;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getLinkurl() {
		return linkurl;
	}

	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}

	public String getImagetype() {
		return imagetype;
	}

	public void setImagetype(String imagetype) {
		this.imagetype = imagetype;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	@Override
	public String toString() {
		return "WebImage [id=" + id + ", createuser=" + createuser
				+ ", createdtime=" + createdtime + ", updateuser=" + updateuser
				+ ", updatedtime=" + updatedtime + ", deleteflag=" + deleteflag
				+ ", imagename=" + imagename + ", imageoriginalname="
				+ imageoriginalname + ", imageurl=" + imageurl + ", linkurl="
				+ linkurl + ", imagetype=" + imagetype + ", sequence="
				+ sequence + "]";
	}

}
