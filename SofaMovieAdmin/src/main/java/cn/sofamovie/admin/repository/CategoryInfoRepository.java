package cn.sofamovie.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import cn.sofamovie.admin.domain.CategoryInfo;

public interface CategoryInfoRepository extends Repository<CategoryInfo, Long> {

	public CategoryInfo save(CategoryInfo categoryInfo);

	public CategoryInfo findOne(Long id);

	@Query("from CategoryInfo c where c.deleteflag = 0 order by c.orderno")
	public List<CategoryInfo> findAllCategories();

	@Modifying
	@Query("update CategoryInfo c set c.deleteflag = :deleteflag where c.id = :id")
	public int modifyDeleteflagByID(@Param("deleteflag") Integer deleteflag,
			@Param("id") Long id);

	@Modifying
	@Query("update CategoryInfo c set c.categoryname = :categoryname, c.orderno = :orderno where c.id = :id")
	public int modifyCategorynameAndOrdernoByID(
			@Param("categoryname") String categoryname,
			@Param("orderno") Integer orderno, @Param("id") Long id);

}
