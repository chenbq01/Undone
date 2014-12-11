package cn.sofamovie.admin.service;

import java.util.List;

import cn.sofamovie.admin.domain.WebImage;

public interface WebImageService {

	public WebImage saveWebImage(WebImage webImage);

	public List<WebImage> findAllMainImages();

	public List<WebImage> findAllDeletedMainImages();

	public List<WebImage> findAllFocusImages();

	public boolean removeImage(String sequence);

	public boolean deleteMainImage(String id);

	public boolean resetMainImage(String id);

}
