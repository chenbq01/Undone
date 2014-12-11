package com.crawler.web.service.impl;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

@Service("wangPiaoCrawlerService")
public class WangPiaoCrawlerService implements CrawlerService {

	private static final Logger logger = LoggerFactory
			.getLogger(WangPiaoCrawlerService.class);

	@Autowired
	private CinemaInfoRepository cinemaInfoRepository;

	@Override
	@Transactional
	public void getAllCinemaInformation() {
		try {
			Document document = Jsoup.connect("http://www.wangpiao.com/").get();
			Elements hotcities = document.select("#HeadDiv_citylist > h2 > a");
			Elements othercities = document
					.select("#HeadDiv_citylist > ul > li > a");

			String cityUrl = null;
			String cityName = null;
			for (Element cityElement : hotcities) {
				cityName = cityElement.html();
				cityUrl = cityElement.attr("cityid");
				logger.debug("<---" + cityName + ":" + cityUrl + "--->");
				getCityCinemaList(cityName, cityUrl);
			}

			for (Element cityElement : othercities) {
				cityName = cityElement.html();
				cityUrl = cityElement.attr("cityid");
				logger.debug("<---" + cityName + ":" + cityUrl + "--->");
				getCityCinemaList(cityName, cityUrl);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCityCinemaList(String cityName, String cityUrl) {
		try {
			String baseUrl = "http://www.wangpiao.com/Cinema.ajax?Ajax=true&Ajax=true&Ajax_CallBackType=WPWEB_V2.Controllers.CinemaIndex&Ajax_CallBackMethod=GetCinemaListbyPageAndWant&AjaxArgument0="
					+ cityUrl
					+ "&AjaxArgument1=0&AjaxArgument2=0&AjaxArgument3=0&AjaxArgument4=0&AjaxArgument5=0&AjaxArgument6=0&AjaxArgument7=&AjaxArgument8=1&AjaxArgument9=10&AjaxArgument10=0&AjaxArgument11=0&AjaxArgument12=1&_=1400203065465";
			String responseBody = Jsoup.connect(baseUrl)
					.cookie("city", cityUrl).ignoreContentType(true).execute()
					.body();
			JSONObject obj = JSONObject.fromObject(responseBody);
/*			System.out.println("<---" + cityName + ":" + cityUrl + ":"
					+ obj.getJSONObject("value").getString("Count") + "--->");*/
			int cinemacount = Integer.valueOf(obj.getJSONObject("value")
					.getString("Count"));
			int maxPageNo = (int) Math.ceil((double) cinemacount / (double) 10);
			for (int i = 1; i <= maxPageNo; i++) {
				String strUrl = "http://www.wangpiao.com/Cinema.ajax?Ajax=true&Ajax=true&Ajax_CallBackType=WPWEB_V2.Controllers.CinemaIndex&Ajax_CallBackMethod=GetCinemaListbyPageAndWant&AjaxArgument0="
						+ cityUrl
						+ "&AjaxArgument1=0&AjaxArgument2=0&AjaxArgument3=0&AjaxArgument4=0&AjaxArgument5=0&AjaxArgument6=0&AjaxArgument7=&AjaxArgument8="
						+ i
						+ "&AjaxArgument9=10&AjaxArgument10=0&AjaxArgument11=0&AjaxArgument12=1&_=1400203065465";
				//System.out.println(strUrl);
				responseBody = Jsoup.connect(strUrl).cookie("city", cityUrl)
						.ignoreContentType(true).execute().body();
/*				System.out.println("<---" + cityName + ":" + cityUrl + ":[" + i
						+ "/" + maxPageNo + "]:"
						+ obj.getJSONObject("value").getString("Count")
						+ "--->");*/
				obj = JSONObject.fromObject(responseBody);
				JSONArray jsonArray = obj.getJSONObject("value").getJSONArray(
						"ListData");
				for (int j = 0; j < jsonArray.size(); j++) {
					JSONObject jsonCinema = jsonArray.getJSONObject(j);
					String SupportType = jsonCinema.getString("SupportType");
					String Product = jsonCinema.getString("Product");
					String cinemaType = "未知合作类型";
					if ("1".equals(SupportType) || "3".equals(SupportType)) {
						cinemaType = "实时选座";// 购票选座
					} else if (Product.indexOf("观影码") > -1) {
						cinemaType = "非实时选座";// 购观影码
					}
					CinemaInfo info = new CinemaInfo();
					info.setCinema(jsonCinema.getString("FullName"));
					info.setCity(cityName);
					info.setPlatform("网票网");
					info.setType(cinemaType);
					cinemaInfoRepository.save(info);
					System.out.println("<---" + cityName + ":" + cityUrl + ":["
							+ i + "/" + maxPageNo + "]:"
							+ obj.getJSONObject("value").getString("Count")
							+ info + "--->");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
