package cn.sofamovie.admin.service;

import java.util.List;

import cn.sofamovie.admin.domain.BoxInfo;

public interface BoxInfoService {

	public List<BoxInfo> findAllBoxes();

	public List<BoxInfo> findAllBoxesByRegionID(String regionid);

	public BoxInfo addBoxByRegionID(String boxname, String regionid);

	public boolean removeBox(String id);

}
