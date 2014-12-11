package com.crawler.web.service.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("tongChengMaMaCrawlerService")
public class TongChengCrawlerService {

	private static final Logger logger = LoggerFactory
			.getLogger(TongChengCrawlerService.class);

	public void getSightsCount() {
		//System.out.println("北京:" + getSightList("北京"));
		System.out.println("深圳:" + getSightList("深圳"));
		System.out.println("苏州:" + getSightList("苏州"));
		System.out.println("南京:" + getSightList("南京"));
		System.out.println("无锡:" + getSightList("无锡"));
		System.out.println("常州:" + getSightList("常州"));
	}

	private int getSightList(String keyword) {
		int result = 0;
		try {
			String url = "http://www.ly.com/scenery/SearchList.aspx?&action=getlist&page=1&kw="
					+ keyword
					+ "&pid=0&cid=0&cyid=0&theme=0&grade=0&money=0&sort=0&paytype=0&ismem=0&istuan=0&isnow=0&spType=&isyiyuan=0&iid=0.28225278854370117";
			Document document = Jsoup
					.connect(url)
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.103 Safari/537.36")
					.ignoreContentType(true).get();

			int pageCount = Integer.valueOf(
					document.select("input#txt_AllpageNumber").val())
					.intValue();

			for (int i = 1; i <= pageCount; i++) {
				url = "http://www.ly.com/scenery/SearchList.aspx?&action=getlist&page="
						+ i
						+ "&kw="
						+ keyword
						+ "&pid=0&cid=0&cyid=0&theme=0&grade=0&money=0&sort=0&paytype=0&ismem=0&istuan=0&isnow=0&spType=&isyiyuan=0&iid=0.28225278854370117";
				document = Jsoup
						.connect(url)
						.header("User-Agent",
								"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.103 Safari/537.36")
						.ignoreContentType(true).get();
				Elements sights = document.select("dl.info_top");
				Element sight = null;
				for (int j = 0; j < sights.size(); j++) {
					sight = sights.get(j);

					/*System.out.println("第" + i + "页第" + (j + 1) + "条搜索结果:["
							+ sight.select("dt > a.fir_name").html() + "]--同程价格：["
							+ sight.select("dd.scenery_details > div.s_price > b").html() + "]");*/

					if (sight.select("dd.scenery_details > div.s_price > b").html().length() > 0) {
						result++;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		TongChengCrawlerService qcs = new TongChengCrawlerService();
		qcs.getSightsCount();
	}

}
