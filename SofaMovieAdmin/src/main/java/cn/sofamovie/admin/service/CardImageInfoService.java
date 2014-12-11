package cn.sofamovie.admin.service;

import java.util.List;

import cn.sofamovie.admin.domain.CardImageInfo;

public interface CardImageInfoService {

	public CardImageInfo saveCardImage(CardImageInfo cardImageInfo);

	public List<CardImageInfo> findAllImages();

	public List<CardImageInfo> findAllImagesByCardID(String cardid);

	public boolean removeImage(String id);

}
