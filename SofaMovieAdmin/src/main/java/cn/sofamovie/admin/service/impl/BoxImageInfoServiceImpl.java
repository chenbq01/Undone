package cn.sofamovie.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sofamovie.admin.domain.BoxImageInfo;
import cn.sofamovie.admin.repository.BoxImageInfoRepository;
import cn.sofamovie.admin.service.BoxImageInfoService;

@Service("boxImageInfoService")
public class BoxImageInfoServiceImpl implements BoxImageInfoService {

	@Autowired
	private BoxImageInfoRepository boxImageInfoRepository;

	@Override
	@Transactional
	public BoxImageInfo saveBoxImage(BoxImageInfo boxImageInfo) {
		boxImageInfoRepository.deleteImageByImagetypeAndSequence(
				boxImageInfo.getBoxid(), boxImageInfo.getSequence());
		return boxImageInfoRepository.save(boxImageInfo);
	}

	@Override
	public List<BoxImageInfo> findAllImagesByBoxID(String boxid) {
		return boxImageInfoRepository.findImagesByBoxID(Long.valueOf(boxid));
	}

	@Override
	@Transactional
	public boolean removeImage(String id) {
		return boxImageInfoRepository.deleteImageByID(Long.valueOf(id)) > 0;
	}

	@Override
	public List<BoxImageInfo> findAllImages() {
		return boxImageInfoRepository.findAllImages();
	}

}
