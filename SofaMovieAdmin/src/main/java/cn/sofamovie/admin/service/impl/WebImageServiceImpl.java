package cn.sofamovie.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sofamovie.admin.domain.WebImage;
import cn.sofamovie.admin.repository.WebImageRepository;
import cn.sofamovie.admin.service.WebImageService;

@Service("webImageService")
public class WebImageServiceImpl implements WebImageService {

	@Autowired
	private WebImageRepository webImageRepository;

	@Override
	@Transactional
	public WebImage saveWebImage(WebImage webImage) {
		webImageRepository.deleteWebImageByImagetypeAndSequence(
				webImage.getImagetype(), webImage.getSequence());
		return webImageRepository.save(webImage);
	}

	@Override
	public List<WebImage> findAllMainImages() {
		return webImageRepository.findImagesByImageType("FWT");
	}

	@Override
	public List<WebImage> findAllFocusImages() {
		return webImageRepository.findImagesByImageType("JDT");
	}

	@Override
	@Transactional
	public boolean removeImage(String sequence) {
		return webImageRepository.deleteWebImageByImagetypeAndSequence("JDT",
				Integer.valueOf(sequence)) > 0;
	}

	@Override
	public List<WebImage> findAllDeletedMainImages() {
		return webImageRepository.findDeletedImagesByImageType("FWT");
	}

	@Override
	@Transactional
	public boolean deleteMainImage(String id) {
		webImageRepository.delete(Long.valueOf(id));
		return true;
	}

	@Override
	@Transactional
	public boolean resetMainImage(String id) {
		boolean flag1, flag2 = false;
		flag1 = webImageRepository.deleteWebImageByImagetypeAndSequence("FWT",
				1) > 0;
		flag2 = webImageRepository.resetWebImageByID(Long.valueOf(id)) > 0;
		return flag1 && flag2;
	}
}
