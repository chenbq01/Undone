package com.crawler.web.service.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crawler.web.domain.CinemaInfo;
import com.crawler.web.repository.CinemaInfoRepository;
import com.crawler.web.service.CrawlerService;

@Service("gewaraCrawlerService")
public class GewaraCrawlerService implements CrawlerService {

	private static final Logger logger = LoggerFactory
			.getLogger(GewaraCrawlerService.class);

	@Autowired
	private CinemaInfoRepository cinemaInfoRepository;

	@Override
	@Transactional
	public void getAllCinemaInformation() {
		try {
			Document document = Jsoup.connect(
					"http://www.gewara.com/cityList.xhtml").get();
			Elements provinceElements = document.select("#city_content > dl");
			Elements cityElements = null;
			String provinceName = null;
			String cityUrl = null;
			String cityName = null;

			for (Element provinceElement : provinceElements) {
				provinceName = provinceElement.select("dt > span").html();
				cityElements = provinceElement.select("dd > b > a");
				logger.debug("<------" + provinceName + "------>");
				for (Element cityElement : cityElements) {
					cityName = cityElement.html();
					cityUrl = cityElement.attr("href");
					logger.debug("<---" + cityName + ":" + cityUrl + "--->");
					getCityCinemaList(cityName, cityUrl);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCityCinemaList(String cityName, String cityUrl) {
		try {
			String baseUrl = "http://www.gewara.com" + cityUrl + "/cinemalist";
			Document document = Jsoup.connect(baseUrl).get();

			Elements elementsFYL = document.select("#page");
			if (elementsFYL.size() > 0) {// 存在分页栏
				// 最大页号
				String maxPageNo = document.select("#page > a")
						.get(document.select("#page > a").size() - 2)
						.select("span").html();

				for (int i = 0; i < Integer.valueOf(maxPageNo).intValue(); i++) {
					String url = baseUrl + "?pageNo=" + i;
					getCinema(cityName, url);
				}
			} else {
				getCinema(cityName, baseUrl);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCinema(String cityName, String url) throws IOException {
		Document cinemalistDoc = null;
		Elements elementsCinema = null;
		cinemalistDoc = Jsoup.connect(url).get();
		elementsCinema = cinemalistDoc.select("#cinemaListArea > ul > li");
		String cinemaName = null;
		String cinemaType = null;
		for (Element cinemaElement : elementsCinema) {
			cinemaName = cinemaElement
					.select("div.ui_media > div.ui_text > div.clear > div.left > div.title > h2 > a")
					.html();
			cinemaType = cinemaElement
					.select("div.ui_media > div.ui_text > div.clear > div.right.mr10")
					.html().indexOf("选座购票") > 0 ? "实时选座" : "非实时选座";
			logger.debug(cinemaName + ":" + cinemaType);
			CinemaInfo info = new CinemaInfo();
			info.setCinema(cinemaName);
			info.setCity(cityName);
			info.setPlatform("格瓦拉");
			info.setType(cinemaType);
			cinemaInfoRepository.save(info);
		}
	}

}
