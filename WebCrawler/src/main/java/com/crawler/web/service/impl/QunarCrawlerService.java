package com.crawler.web.service.impl;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("qunarCrawlerService")
public class QunarCrawlerService {

	private static final Logger logger = LoggerFactory
			.getLogger(QunarCrawlerService.class);

	public void getSightsCount() {
		System.out.println("北京:" + getSightList("北京"));
		System.out.println("上海:" + getSightList("上海"));
		System.out.println("广州:" + getSightList("广州"));
		System.out.println("深圳:" + getSightList("深圳"));
		System.out.println("江苏:" + getSightList("江苏"));
		System.out.println("浙江:" + getSightList("浙江"));
		System.out.println("福建:" + getSightList("福建"));
		System.out.println("重庆:" + getSightList("重庆"));

		System.out.println("苏州:" + getSightList("苏州"));
		System.out.println("南京:" + getSightList("南京"));
		System.out.println("无锡:" + getSightList("无锡"));
		System.out.println("常州:" + getSightList("常州"));
		System.out.println("宁波:" + getSightList("宁波"));
		System.out.println("湖州:" + getSightList("湖州"));
		System.out.println("金华:" + getSightList("金华"));
		System.out.println("台州:" + getSightList("台州"));
		System.out.println("福州:" + getSightList("福州"));
		System.out.println("贵州:" + getSightList("贵州"));
		System.out.println("杭州:" + getSightList("杭州"));
	}

	private int getSightList(String keyword) {
		int result = 0;
		try {
			String url = "http://piao.qunar.com/ticket/list.json?keyword="
					+ keyword + "&region=&from=mpl_search";
			String responseBody = Jsoup
					.connect(url)
					.header("User-Agent",
							"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.103 Safari/537.36")
					.ignoreContentType(true).execute().body();
			JSONObject obj = JSONObject.fromObject(responseBody);

			int totalCount = obj.getJSONObject("data").getInt("totalCount");
			int pageCount = Double.valueOf(
					Math.ceil((double) (totalCount / 15))).intValue();
			String data = null;
			for (int i = 1; i <= pageCount; i++) {
				url = "http://piao.qunar.com/ticket/list.json?keyword="
						+ keyword + "&region=&from=mpl_search&total="
						+ totalCount + "&page=" + i;
				responseBody = Jsoup
						.connect(url)
						.header("User-Agent",
								"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.103 Safari/537.36")
						.ignoreContentType(true).execute().body();
				obj = JSONObject.fromObject(responseBody);
				data = obj.getString("data");

				if (data != null && data != "null") {
					JSONArray arr = obj.getJSONObject("data").getJSONArray(
							"sightList");
					for (int j = 0; j < arr.size(); j++) {

						/*
						 * System.out .println("第" + i + "页第" + (j + 1) +
						 * "条搜索结果:[" + arr.getJSONObject(j).getString(
						 * "sightName") + "]--去哪折扣：[" +
						 * (arr.getJSONObject(j).containsKey( "discount") ? arr
						 * .getJSONObject(j).getString( "discount") : "无") +
						 * "]");
						 */

						if (arr.getJSONObject(j).containsKey("discount")) {
							result++;
						}
					}
				} else {
					break;
				}

			}
			// result = totalCount;

		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		 * catch (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return result;
	}

	public static void main(String[] args) {
		QunarCrawlerService qcs = new QunarCrawlerService();
		qcs.getSightsCount();
	}

}
