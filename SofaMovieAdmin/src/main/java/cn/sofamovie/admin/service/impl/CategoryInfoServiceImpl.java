package cn.sofamovie.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sofamovie.admin.domain.CategoryInfo;
import cn.sofamovie.admin.repository.CategoryInfoRepository;
import cn.sofamovie.admin.service.CategoryInfoService;

@Service("categoryInfoService")
public class CategoryInfoServiceImpl implements CategoryInfoService {

	@Autowired
	private CategoryInfoRepository categoryInfoService;

	@Override
	public List<CategoryInfo> findAllCategories() {
		return categoryInfoService.findAllCategories();
	}

	@Override
	@Transactional
	public CategoryInfo addCategory(String categoryname, String orderno) {
		CategoryInfo categoryInfo = new CategoryInfo();
		categoryInfo.setCategoryname(categoryname);
		categoryInfo.setOrderno(Integer.valueOf(orderno));
		return categoryInfoService.save(categoryInfo);
	}

	@Override
	@Transactional
	public boolean removeCategory(String id) {
		return categoryInfoService.modifyDeleteflagByID(1, Long.valueOf(id)) > 0;
	}

	@Override
	@Transactional
	public boolean setCategory(String id, String categoryname, String orderno) {
		return categoryInfoService.modifyCategorynameAndOrdernoByID(
				categoryname, Integer.valueOf(orderno), Long.valueOf(id)) > 0;
	}

	@Override
	public CategoryInfo getinfo(String id) {
		return categoryInfoService.findOne(Long.valueOf(id));
	}

}
