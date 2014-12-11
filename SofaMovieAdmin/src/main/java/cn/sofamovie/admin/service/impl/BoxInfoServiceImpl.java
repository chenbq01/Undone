package cn.sofamovie.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sofamovie.admin.domain.BoxInfo;
import cn.sofamovie.admin.repository.BoxInfoRepository;
import cn.sofamovie.admin.service.BoxInfoService;

@Service("boxInfoService")
public class BoxInfoServiceImpl implements BoxInfoService {

	@Autowired
	private BoxInfoRepository boxInfoRepository;

	@Override
	public List<BoxInfo> findAllBoxesByRegionID(String regionid) {
		return boxInfoRepository.findAllBoxesByRegionID(Long.valueOf(regionid));
	}

	@Override
	@Transactional
	public BoxInfo addBoxByRegionID(String boxname, String regionID) {
		BoxInfo boxInfo = new BoxInfo();
		boxInfo.setRegionid(Long.valueOf(regionID));
		boxInfo.setBoxname(boxname);
		return boxInfoRepository.save(boxInfo);
	}

	@Override
	@Transactional
	public boolean removeBox(String id) {
		return boxInfoRepository.modifyDeleteflagByID(1, Long.valueOf(id)) > 0;
	}

	@Override
	public List<BoxInfo> findAllBoxes() {
		return boxInfoRepository.findAllBoxes();
	}

}
