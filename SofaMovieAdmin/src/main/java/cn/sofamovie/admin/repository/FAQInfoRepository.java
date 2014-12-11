package cn.sofamovie.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.sofamovie.admin.domain.FAQInfo;

public interface FAQInfoRepository extends Repository<FAQInfo, Long> {

	public FAQInfo save(FAQInfo faqInfo);

	@Query("from FAQInfo f where f.categoryid = :categoryid and f.deleteflag = 0 order by f.orderno")
	public List<FAQInfo> findFAQsByCategoryID(
			@Param("categoryid") Long categoryid);

	@Modifying
	@Query("update FAQInfo f set f.deleteflag = 1 where f.id = :id and f.deleteflag = 0")
	public int deleteImageByID(@Param("id") Long id);

	@Modifying
	@Query("update FAQInfo f set f.question = :question, f.answer = :answer, f.orderno = :orderno where f.id = :id and f.deleteflag = 0")
	public int modifyFAQByID(@Param("id") Long id,
			@Param("question") String question, @Param("answer") String answer,
			@Param("orderno") Integer orderno);
	
	public FAQInfo findOne(Long id);

}
