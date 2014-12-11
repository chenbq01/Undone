package cn.sofamovie.admin.service;

import java.util.List;

import cn.sofamovie.admin.domain.CardInfo;

public interface CardInfoService {

	public List<CardInfo> findAllCards();

	public List<CardInfo> findAllCardsByRegionID(String regionid);

	public CardInfo addCardByRegionID(String cardname, String regionid);

	public boolean removeCard(String id);

}
