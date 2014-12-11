package cn.sofamovie.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sofamovie.admin.domain.RegionInfo;
import cn.sofamovie.admin.repository.RegionInfoRepository;
import cn.sofamovie.admin.service.RegionInfoService;

@Service("regionInfoService")
public class RegionInfoServiceImpl implements RegionInfoService {

	@Autowired
	private RegionInfoRepository regionInfoRepository;

	@Override
	public List<RegionInfo> findAllRegions() {
		return regionInfoRepository.findAllRegions();
	}

	@Override
	@Transactional
	public RegionInfo addRegion(String regionname) {
		RegionInfo regionInfo = new RegionInfo();
		regionInfo.setRegionname(regionname);
		return regionInfoRepository.save(regionInfo);
	}

	@Override
	@Transactional
	public boolean removeRegion(String id) {
		return regionInfoRepository.modifyDeleteflagByID(1, Long.valueOf(id)) > 0;
	}

	@Override
	@Transactional
	public boolean setChannel(String id, String guidechannels,
			String demandchannels) {
		return regionInfoRepository.modifyGuidechannelsAndDemandchannelsByID(
				guidechannels, demandchannels, Long.valueOf(id)) > 0;
	}

	@Override
	public RegionInfo getinfo(String id) {
		return regionInfoRepository.findOne(Long.valueOf(id));
	}

	@Override
	@Transactional
	public boolean setSupportPhone(String id, String supportphone) {
		return regionInfoRepository.modifySupportPhoneByID(supportphone,
				Long.valueOf(id)) > 0;
	}

}
