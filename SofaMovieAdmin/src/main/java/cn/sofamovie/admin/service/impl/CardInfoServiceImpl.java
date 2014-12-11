package cn.sofamovie.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sofamovie.admin.domain.CardInfo;
import cn.sofamovie.admin.repository.CardInfoRepository;
import cn.sofamovie.admin.service.CardInfoService;

@Service("cardInfoService")
public class CardInfoServiceImpl implements CardInfoService {

	@Autowired
	private CardInfoRepository cardInfoRepository;

	@Override
	public List<CardInfo> findAllCardsByRegionID(String regionid) {
		return cardInfoRepository
				.findAllCardsByRegionID(Long.valueOf(regionid));
	}

	@Override
	@Transactional
	public CardInfo addCardByRegionID(String cardname, String regionid) {
		CardInfo cardInfo = new CardInfo();
		cardInfo.setRegionid(Long.valueOf(regionid));
		cardInfo.setCardname(cardname);
		return cardInfoRepository.save(cardInfo);
	}

	@Override
	@Transactional
	public boolean removeCard(String id) {
		return cardInfoRepository.modifyDeleteflagByID(1, Long.valueOf(id)) > 0;
	}

	@Override
	public List<CardInfo> findAllCards() {
		return cardInfoRepository.findAllCards();
	}

}
