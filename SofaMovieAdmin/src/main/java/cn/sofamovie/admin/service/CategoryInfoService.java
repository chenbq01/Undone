package cn.sofamovie.admin.service;

import java.util.List;

import cn.sofamovie.admin.domain.CategoryInfo;

public interface CategoryInfoService {

	public List<CategoryInfo> findAllCategories();

	public CategoryInfo addCategory(String categoryname, String orderno);

	public boolean removeCategory(String id);

	public boolean setCategory(String id, String categoryname, String orderno);

	public CategoryInfo getinfo(String id);

}
