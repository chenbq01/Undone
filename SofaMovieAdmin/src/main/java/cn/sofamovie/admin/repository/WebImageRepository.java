package cn.sofamovie.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.sofamovie.admin.domain.WebImage;

public interface WebImageRepository extends Repository<WebImage, Long> {

	public WebImage save(WebImage webImage);

	@Query("from WebImage w where w.imagetype = :imagetype and w.deleteflag = 0 order by w.sequence")
	public List<WebImage> findImagesByImageType(
			@Param("imagetype") String imagetype);

	@Modifying
	@Query("update WebImage w set w.deleteflag = 1 where w.imagetype = :imagetype and w.sequence = :sequence and w.deleteflag = 0")
	public int deleteWebImageByImagetypeAndSequence(
			@Param("imagetype") String imagetype,
			@Param("sequence") Integer sequence);

	void delete(Long id);

	@Query("from WebImage w where w.imagetype = :imagetype and w.deleteflag = 1 order by w.id desc")
	public List<WebImage> findDeletedImagesByImageType(
			@Param("imagetype") String imagetype);

	@Modifying
	@Query("update WebImage w set w.deleteflag = 0 where w.id = :id")
	public int resetWebImageByID(@Param("id") Long id);

}
