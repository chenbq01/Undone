package cn.sofamovie.admin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sofamovie.admin.domain.CardImageInfo;
import cn.sofamovie.admin.repository.CardImageInfoRepository;
import cn.sofamovie.admin.service.CardImageInfoService;

@Service("cardImageInfoService")
public class CardImageInfoServiceImpl implements CardImageInfoService {

	@Autowired
	private CardImageInfoRepository cardImageInfoRepository;

	@Override
	@Transactional
	public CardImageInfo saveCardImage(CardImageInfo cardImageInfo) {
		cardImageInfoRepository.deleteImageByImagetypeAndSequence(
				cardImageInfo.getCardid(), cardImageInfo.getSequence());
		return cardImageInfoRepository.save(cardImageInfo);
	}

	@Override
	public List<CardImageInfo> findAllImagesByCardID(String cardid) {
		return cardImageInfoRepository.findImagesByCardID(Long.valueOf(cardid));
	}

	@Override
	@Transactional
	public boolean removeImage(String id) {
		return cardImageInfoRepository.deleteImageByID(Long.valueOf(id)) > 0;
	}

	@Override
	public List<CardImageInfo> findAllImages() {
		return cardImageInfoRepository.findImages();
	}

}
