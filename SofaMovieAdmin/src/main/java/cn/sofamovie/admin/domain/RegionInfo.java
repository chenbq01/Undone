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
@Table(name = "tbl_regions")
public class RegionInfo implements Serializable {

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

	@Column(name = "regionname", nullable = false)
	private String regionname;

	@Column(name = "guidechannels", nullable = true)
	private String guidechannels;

	@Column(name = "demandchannels", nullable = true)
	private String demandchannels;

	@Column(name = "supportphone", nullable = true)
	private String supportphone;

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

	public String getRegionname() {
		return regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getGuidechannels() {
		return guidechannels;
	}

	public void setGuidechannels(String guidechannels) {
		this.guidechannels = guidechannels;
	}

	public String getDemandchannels() {
		return demandchannels;
	}

	public void setDemandchannels(String demandchannels) {
		this.demandchannels = demandchannels;
	}

	public String getSupportphone() {
		return supportphone;
	}

	public void setSupportphone(String supportphone) {
		this.supportphone = supportphone;
	}

	@Override
	public String toString() {
		return "RegionInfo [id=" + id + ", createuser=" + createuser
				+ ", createdtime=" + createdtime + ", updateuser=" + updateuser
				+ ", updatedtime=" + updatedtime + ", deleteflag=" + deleteflag
				+ ", regionname=" + regionname + ", guidechannels="
				+ guidechannels + ", demandchannels=" + demandchannels
				+ ", supportphone=" + supportphone + "]";
	}

}
