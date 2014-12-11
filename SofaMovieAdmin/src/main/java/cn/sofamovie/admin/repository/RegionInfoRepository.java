package cn.sofamovie.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.sofamovie.admin.domain.RegionInfo;

public interface RegionInfoRepository extends Repository<RegionInfo, Long> {

	public RegionInfo save(RegionInfo regionInfo);

	public RegionInfo findOne(Long id);

	@Query("from RegionInfo r where r.deleteflag = 0")
	public List<RegionInfo> findAllRegions();

	@Modifying
	@Query("update RegionInfo r set r.deleteflag = :deleteflag where r.id = :id")
	public int modifyDeleteflagByID(@Param("deleteflag") Integer deleteflag,
			@Param("id") Long id);

	@Modifying
	@Query("update RegionInfo r set r.guidechannels = :guidechannels, r.demandchannels = :demandchannels where r.id = :id")
	public int modifyGuidechannelsAndDemandchannelsByID(
			@Param("guidechannels") String guidechannels,
			@Param("demandchannels") String demandchannels, @Param("id") Long id);

	@Modifying
	@Query("update RegionInfo r set r.supportphone = :supportphone where r.id = :id")
	public int modifySupportPhoneByID(
			@Param("supportphone") String supportphone, @Param("id") Long id);

}
