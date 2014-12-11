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

@Service("spiderCrawlerService")
public class SpiderCrawlerService implements CrawlerService {

	private static final Logger logger = LoggerFactory
			.getLogger(SpiderCrawlerService.class);

	@Autowired
	private CinemaInfoRepository cinemaInfoRepository;

	@Override
	@Transactional
	public void getAllCinemaInformation() {
		try {
			Document document = Jsoup.connect("http://film.spider.com.cn/")
					.get();
			Elements cityElements = null;
			String cityUrl = null;
			String cityName = null;

			cityElements = document
					.select("div.movie_qh > div.movie_sx a.city");

			for (Element cityElement : cityElements) {
				cityName = cityElement.attr("id");
				cityUrl = cityElement.attr("href");
				System.out.println("<---" + cityName + ":" + cityUrl + "--->");
				getCityCinemaList(cityName, cityUrl);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCityCinemaList(String cityName, String cityUrl) {
		try {
			Document document = Jsoup.connect(cityUrl).get();
			String baseUrl = document.select("div.subnav >ul > li > a").get(2)
					.attr("href");
			System.out.println(baseUrl);
			document = Jsoup.connect(baseUrl).get();
			Elements elementsFYL = document.select("div.m3_syzx");
			String strfy = elementsFYL.html();
			int maxPageNo = Integer.valueOf(strfy.split("共")[1].split("页]")[0]);
			if (maxPageNo == 0) {
				return;
			}
			baseUrl = "http://film.spider.com.cn"
					+ strfy.split("fGotoPage")[1].split("_pageNo")[0]
							.substring(2) + "_pageNo=";
			Elements elementsCinema = null;
			for (int i = 1; i <= maxPageNo; i++) {
				document = Jsoup.connect(baseUrl + i).get();
				elementsCinema = document.select("div.produclb_cp2_zi");
				String cinemaName = null;
				String cinemaType = null;
				for (Element cinemaElement : elementsCinema) {
					cinemaName = cinemaElement.child(0).child(0).child(0)
							.child(0).html();
					cinemaType = cinemaElement.child(0).child(1)
							.select("a > span").html();

					CinemaInfo info = new CinemaInfo();
					info.setCinema(cinemaName);
					info.setCity(cityName);
					info.setPlatform("蜘蛛网");
					info.setType("选座购票".equals((cinemaType))?"实时选座":"未知合作类型");
					cinemaInfoRepository.save(info);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
