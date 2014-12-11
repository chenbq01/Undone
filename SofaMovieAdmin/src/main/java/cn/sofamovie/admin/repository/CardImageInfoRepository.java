package cn.sofamovie.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.sofamovie.admin.domain.CardImageInfo;

public interface CardImageInfoRepository extends
		Repository<CardImageInfo, Long> {

	public CardImageInfo save(CardImageInfo cardImageInfo);

	@Query("from CardImageInfo c where c.deleteflag = 0 order by c.sequence")
	public List<CardImageInfo> findImages();

	@Query("from CardImageInfo c where c.cardid = :cardid and c.deleteflag = 0 order by c.sequence")
	public List<CardImageInfo> findImagesByCardID(@Param("cardid") Long cardid);

	@Modifying
	@Query("update CardImageInfo c set c.deleteflag = 1 where c.cardid = :cardid and c.sequence = :sequence and c.deleteflag = 0")
	public int deleteImageByImagetypeAndSequence(@Param("cardid") Long cardid,
			@Param("sequence") Integer sequence);

	@Modifying
	@Query("update CardImageInfo c set c.deleteflag = 1 where c.id = :id and c.deleteflag = 0")
	public int deleteImageByID(@Param("id") Long id);

}
