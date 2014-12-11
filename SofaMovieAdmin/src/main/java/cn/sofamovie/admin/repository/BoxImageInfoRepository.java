package cn.sofamovie.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.sofamovie.admin.domain.BoxImageInfo;

public interface BoxImageInfoRepository extends Repository<BoxImageInfo, Long> {

	public BoxImageInfo save(BoxImageInfo boxImageInfo);
	
	@Query("from BoxImageInfo b where b.deleteflag = 0 order by b.sequence")
	public List<BoxImageInfo> findAllImages();

	@Query("from BoxImageInfo b where b.boxid = :boxid and b.deleteflag = 0 order by b.sequence")
	public List<BoxImageInfo> findImagesByBoxID(@Param("boxid") Long boxid);

	@Modifying
	@Query("update BoxImageInfo b set b.deleteflag = 1 where b.boxid = :boxid and b.sequence = :sequence and b.deleteflag = 0")
	public int deleteImageByImagetypeAndSequence(@Param("boxid") Long boxid,
			@Param("sequence") Integer sequence);

	@Modifying
	@Query("update BoxImageInfo b set b.deleteflag = 1 where b.id = :id and b.deleteflag = 0")
	public int deleteImageByID(@Param("id") Long id);

}
