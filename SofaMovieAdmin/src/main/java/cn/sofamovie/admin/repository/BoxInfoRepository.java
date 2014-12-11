package cn.sofamovie.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.sofamovie.admin.domain.BoxInfo;

public interface BoxInfoRepository extends Repository<BoxInfo, Long> {

	@Query("from BoxInfo b where b.deleteflag = 0")
	public List<BoxInfo> findAllBoxes();

	public BoxInfo save(BoxInfo boxInfo);

	@Query("from BoxInfo b where b.regionid = :regionid and b.deleteflag = 0")
	public List<BoxInfo> findAllBoxesByRegionID(@Param("regionid") Long regionid);

	@Modifying
	@Query("update BoxInfo b set b.deleteflag = :deleteflag where b.id = :id")
	public int modifyDeleteflagByID(@Param("deleteflag") Integer deleteflag,
			@Param("id") Long id);

}
