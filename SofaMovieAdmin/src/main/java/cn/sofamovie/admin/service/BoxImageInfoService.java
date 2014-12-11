package cn.sofamovie.admin.service;

import java.util.List;

import cn.sofamovie.admin.domain.BoxImageInfo;

public interface BoxImageInfoService {

	public List<BoxImageInfo> findAllImages();

	public BoxImageInfo saveBoxImage(BoxImageInfo boxImageInfo);

	public List<BoxImageInfo> findAllImagesByBoxID(String boxid);

	public boolean removeImage(String id);

}
