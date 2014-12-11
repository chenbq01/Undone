package cn.sofamovie.admin.service;

import java.util.List;

import cn.sofamovie.admin.domain.RegionInfo;

public interface RegionInfoService {

	public List<RegionInfo> findAllRegions();

	public RegionInfo addRegion(String regionname);

	public boolean removeRegion(String id);

	public boolean setChannel(String id, String guidechannels,
			String demandchannels);

	public boolean setSupportPhone(String id, String supportphone);

	public RegionInfo getinfo(String id);

}
