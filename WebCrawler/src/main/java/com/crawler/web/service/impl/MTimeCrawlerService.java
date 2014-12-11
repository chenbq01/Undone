package com.crawler.web.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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

@Service("mTimeCrawlerService")
public class MTimeCrawlerService implements CrawlerService {

	private static final Logger logger = LoggerFactory
			.getLogger(MTimeCrawlerService.class);

	@Autowired
	private CinemaInfoRepository cinemaInfoRepository;

	@Override
	@Transactional
	public void getAllCinemaInformation() {
		String body = null;
		try {
			String url = "http://m.mtime.cn/Service/callback.mi?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Mobile.Pages.CallbackService&Ajax_CallBackMethod=RemoteCallbackSameDomain&Ajax_RequestUrl=http%3A%2F%2Fm.mtime.cn%2F%23!%2Fcitylist%2F&t=201452013383455625&Ajax_CallBackArgument0=%2FShowtime%2FHotCitiesByCinema.api&Ajax_CallBackArgument1=";
			// String url =
			// "http://m.mtime.cn/Service/callback.mi?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Mobile.Pages.CallbackService&Ajax_CallBackMethod=RemoteCallbackSameDomain&Ajax_RequestUrl=http%3A%2F%2Fm.mtime.cn%2F%23!%2Ftheater%2F290%2Fcinema%2Fticket%2F&t=201452211185978860&Ajax_CallBackArgument0=%2FCinema%2FCinemasByLocationID.api%3FlocationId%3D290&Ajax_CallBackArgument1=";
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			httpget.setHeader("Accept-Encoding","gzip, deflate");
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			GzipDecompressingEntity gzipentity = new GzipDecompressingEntity(
					entity);
			System.out
					.println("ContentLength:" + gzipentity.getContentLength());
			System.out.println("ContentType:" + gzipentity.getContentType());

			InputStream in = gzipentity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = br.readLine();
			System.out.println(line);
			httpclient.close();
			/*
			 * String responseBody = Jsoup .connect(
			 * "http://m.mtime.cn/Service/callback.mi?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Mobile.Pages.CallbackService&Ajax_CallBackMethod=RemoteCallbackSameDomain&Ajax_RequestUrl=http%3A%2F%2Fm.mtime.cn%2F%23!%2Fcitylist%2F&t=201452013383455625&Ajax_CallBackArgument0=%2FShowtime%2FHotCitiesByCinema.api&Ajax_CallBackArgument1="
			 * ).System.out .println(responseBody); JSONObject obj =
			 * JSONObject.fromObject(responseBody); JSONArray jsonArray =
			 * obj.getJSONArray("p"); String cityUrl = null; String cityName =
			 * null;
			 * 
			 * for (int i = 0; i < jsonArray.size(); i++) { JSONObject
			 * jsonCinema = jsonArray.getJSONObject(i); cityName =
			 * jsonCinema.getString("n"); cityUrl = jsonCinema.getString("id");
			 * System.out.println("<---" + cityName + ":" + cityUrl + "--->");
			 * // getCityCinemaList(cityName, cityUrl); }
			 */
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
					info.setType("选座购票".equals((cinemaType)) ? "实时选座"
							: "未知合作类型");
					cinemaInfoRepository.save(info);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getStringFromStream(InputStream is) {
		String str = "";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		int len = 0;
		byte[] data = new byte[1024];
		if (is != null) {
			try {
				while ((len = is.read(data)) != -1) {
					bos.write(data, 0, len);
				}

				str = new String(bos.toByteArray(), "utf-8");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return str;
	}

}
