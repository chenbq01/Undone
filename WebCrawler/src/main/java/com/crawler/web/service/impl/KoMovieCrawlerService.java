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

@Service("koMovieCrawlerService")
public class KoMovieCrawlerService implements CrawlerService {

	private static final Logger logger = LoggerFactory
			.getLogger(KoMovieCrawlerService.class);

	@Autowired
	private CinemaInfoRepository cinemaInfoRepository;

	@Override
	@Transactional
	public void getAllCinemaInformation() {
		try {
			Document document = Jsoup.connect("http://www.komovie.cn/").get();
			Elements cityElements = null;
			String cityUrl = null;
			String cityName = null;

			cityElements = document
					.select("#city_divs > div.hidden > div > div.list > a");

			for (Element cityElement : cityElements) {
				cityName = cityElement.html();
				cityUrl = cityElement.attr("city_id");
				System.out.println("<---" + cityName + ":" + cityUrl + "--->");
				getCityCinemaList(cityName, cityUrl);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCityCinemaList(String cityName, String cityUrl) {
		try {
			Document document = Jsoup.connect(
					"http://www.komovie.cn/cinema/city/" + cityUrl).get();
			Elements elementsCinema = null;
			elementsCinema = document.select("tr.pt.color_bg1");
			String cinemaName = null;
			String cinemaType = null;
			for (Element cinemaElement : elementsCinema) {
				cinemaName = cinemaElement.child(0).child(0).attr("title");
				cinemaType = cinemaElement.attr("p");
				CinemaInfo info = new CinemaInfo();
				info.setCinema(cinemaName);
				info.setCity(cityName);
				info.setPlatform("抠电影");
				info.setType("seat".equals((cinemaType)) ? "实时选座" : "非实时选座");
				cinemaInfoRepository.save(info);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
