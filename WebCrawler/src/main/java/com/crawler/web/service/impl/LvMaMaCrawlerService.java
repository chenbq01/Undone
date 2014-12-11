package com.crawler.web.service.impl;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("lvMaMaCrawlerService")
public class LvMaMaCrawlerService {

	private static final Logger logger = LoggerFactory
			.getLogger(LvMaMaCrawlerService.class);

	public void getSightsCount() {
		System.out.println("北京:" + getSightList("1-1"));
		System.out.println("上海:" + getSightList("79-79"));
		System.out.println("广州:" + getSightList("229-229"));
		System.out.println("深圳:" + getSightList("231-231"));
		System.out.println("重庆:" + getSightList("277-277"));
	}

	private int getSightList(String keyword) {
		int result = 0;
		try {
			String url = "http://www.lvmama.com/search/ticket/" + keyword
					+ "-P1.html#list";
			Document document = Jsoup
					.connect(url)
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.103 Safari/537.36")
					.ignoreContentType(true).get();

			int pageCount = Integer.valueOf(
					document.select("div#list  div.pageoper > span").html()
							.split("/")[1]).intValue();
			for (int i = 1; i <= pageCount; i++) {
				url = "http://www.lvmama.com/search/ticket/" + keyword + "-P"
						+ i + ".html#list";
				document = Jsoup
						.connect(url)
						.header("User-Agent",
								"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.103 Safari/537.36")
						.ignoreContentType(true).get();
				Elements sights = document.select("div#list > div.slist-item");
				Element sight = null;
				for (int j = 0; j < sights.size(); j++) {
					sight = sights.get(j);
					/*System.out.println("第" + i + "页第" + (j + 1) + "条搜索结果:["
							+ sight.select("h5 > a").html() + "]--驴妈妈价格：["
							+ sight.select("dd.info > dfn > i").html() + "]");*/
					if (sight.select("dd.info > dfn > i").html().length() > 0) {
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
		LvMaMaCrawlerService qcs = new LvMaMaCrawlerService();
		qcs.getSightsCount();
	}

}
