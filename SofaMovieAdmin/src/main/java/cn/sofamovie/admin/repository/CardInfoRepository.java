package cn.sofamovie.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.sofamovie.admin.domain.CardInfo;

public interface CardInfoRepository extends Repository<CardInfo, Long> {

	public CardInfo save(CardInfo cardInfo);

	@Query("from CardInfo c where c.deleteflag = 0")
	public List<CardInfo> findAllCards();

	@Query("from CardInfo c where c.regionid = :regionid and c.deleteflag = 0")
	public List<CardInfo> findAllCardsByRegionID(
			@Param("regionid") Long regionid);

	@Modifying
	@Query("update CardInfo c set c.deleteflag = :deleteflag where c.id = :id")
	public int modifyDeleteflagByID(@Param("deleteflag") Integer deleteflag,
			@Param("id") Long id);

}
