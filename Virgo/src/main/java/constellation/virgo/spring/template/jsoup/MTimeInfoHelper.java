package constellation.virgo.spring.template.jsoup;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MTimeInfoHelper {

	public static void main(String[] args) {

		// printBeijingMovies();
		printAllPlan();
	}

	public static void printBeijingMovies() {
		Map<String, String> map = getMoviesByLocationStringID("China_Beijing");
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> e = it.next();
			System.out.println(e.getKey() + ":" + e.getValue());
		}
	}

	public static void printAllPlan() {

		try {
			String responseBody = Jsoup
					.connect(
							"http://static1.mtime.cn/20140303201805/Utility/Data/TheaterListBoxData.m")
					.ignoreContentType(true).execute().body();
			String strJson = responseBody
					.substring("var threaterListBoxData = ".length());
			strJson = strJson.substring(0, strJson.lastIndexOf(";"));
			System.out.println(strJson);
			JSONArray jsonArray = JSONObject.fromObject(strJson)
					.getJSONObject("locations").getJSONArray("List");
			int counter = 1;
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject objLocation = jsonArray.getJSONObject(i);
				String strLocationID = objLocation.getString("Id");
				System.out.println(strLocationID);
				String strLocationNameCN = objLocation.getString("NameCn");
				System.out.println(strLocationNameCN);
				String strLocationNameEN = objLocation.getString("NameEn");
				System.out.println(strLocationNameEN);
				String strPinyinShort = objLocation.getString("PinyinShort");
				System.out.println(strPinyinShort);
				JSONArray arrayDistricts = objLocation.getJSONObject(
						"Districts").getJSONArray("List");
				for (int j = 0; j < arrayDistricts.size(); j++) {
					JSONObject objDistrict = arrayDistricts.getJSONObject(j);
					String strDistrictID = objDistrict.getString("Id");
					System.out.println("----" + strDistrictID);
					String strDistrictNameCN = objDistrict.getString("NameCn");
					System.out.println("----" + strDistrictNameCN);
					String strDistrictStringID = objDistrict
							.getString("StringId");
					System.out.println("----" + strDistrictStringID);
					String strDistrictParentID = objDistrict
							.getString("ParentId");
					System.out.println("----" + strDistrictParentID);

					JSONArray arrayCinemas = objDistrict.getJSONObject(
							"Cinemas").getJSONArray("List");
					for (int k = 0; k < arrayCinemas.size(); k++) {
						JSONObject objCinema = arrayCinemas.getJSONObject(k);
						String strCinemaID = objCinema.getString("Id");
						System.out.println("--------" + strCinemaID);
						String strCinemaNameCN = objCinema.getString("NameCn");
						System.out.println("--------" + strCinemaNameCN);
						String strCinemaCityID = objCinema.getString("CityId");
						System.out.println("--------" + strCinemaCityID);
						String strCinemaDistrictID = objCinema
								.getString("DistrictId");
						System.out.println("--------" + strCinemaDistrictID);

						Date date = new Date();
						System.out.println("####尝试抓取第" + counter
								+ "个影院排期信息####");
						System.out.println("---获取" + strLocationNameCN + " "
								+ strDistrictNameCN + " " + strCinemaNameCN
								+ " " + date + "排期开始---");
						getPlanByCinemaIDAndDate(strDistrictStringID,
								strCinemaID, date);
						System.out.println("---获取" + strLocationNameCN + " "
								+ strDistrictNameCN + " " + strCinemaNameCN
								+ " " + date + "排期结束---");
						Thread.sleep(10000);
						counter++;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void getPlanByCinemaIDAndDate(String strDistrictStringID,
			String strCinemaID, Date date) {
		try {
			String strDateSplit = new SimpleDateFormat("yyyy-MM-dd")
					.format(date);
			String strDate = new SimpleDateFormat("yyyyMMdd").format(date);
			String strRandom = String.valueOf(new Random().nextInt());// 2014362034037870
			String strRequestUrl = "http://service.theater.mtime.com/service/showtime.ms?Ajax_CallBack=true&Ajax_CallBackType=Mtime.Showtime.Pages.ShowtimeService&Ajax_CallBackMethod=GetTheaterDateShowtimes&Ajax_CrossDomain=1&Ajax_RequestUrl=http%3A%2F%2Ftheater.mtime.com%2F"
					+ strDistrictStringID
					+ "%2F"
					+ strCinemaID
					+ "%2F%3Fd%3D"
					+ strDate
					+ "&t="
					+ strRandom
					+ "&Ajax_CallBackArgument0=1&Ajax_CallBackArgument1="
					+ strCinemaID
					+ "&Ajax_CallBackArgument2="
					+ strDistrictStringID
					+ "&Ajax_CallBackArgument3=&Ajax_CallBackArgument4=0&Ajax_CallBackArgument5="
					+ strDateSplit
					+ "%2000%3A00&Ajax_CallBackArgument6=8&Ajax_CallBackArgument7=0&Ajax_CallBackArgument8=31&Ajax_CallBackArgument9=59&Ajax_CallBackArgument10=1";
			System.out.println(strRequestUrl);
			String responseBody = Jsoup.connect(strRequestUrl)
					.ignoreContentType(true).execute().body();
			String htmlString = responseBody.split("\"showtimeList\":\"")[1]
					.replace("\\\"", "\"");
			Document doc = Jsoup.parse(htmlString);

			Elements items = doc.select("div[method=mdShowtime]");
			String movieName = null;
			String movieCode = null;
			String movieLength = null;
			String startDateTime = null;
			String hall = null;
			String price = null;
			String language = null;
			String version = null;
			for (Element item : items) {
				movieName = item.select(".c_000").html();
				movieCode = item.select(".point.ml12.px14").attr(
						"data_rating_movie_id");
				movieLength = item.select(".fr.mt6").html().indexOf("片长") != -1 ? item
						.select(".fr.mt6").html().split("片长")[1].split("分钟")[0]
						: "";// 可能不存在片长
				System.out.println(movieName + "(" + movieCode + ")" + "|"
						+ movieLength + "分钟");

				for (Element li : item.select("ul").get(1).children()) {
					startDateTime = li.attr("time");
					hall = li.select(".hall").html();
					price = li.select("em").html().length() > 5 ? li
							.select("em").html().substring(5) : "";// 可能不存在价格
					language = "1".equals(li.attr("language")) ? "中文" : "2"
							.equals(li.attr("language")) ? "英文" : "未知语言";
					version = "1".equals(li.attr("version")) ? "2D" : "2"
							.equals(li.attr("version")) ? "3D" : "未知版本";
					System.out.println(startDateTime + "|" + hall + "|" + price
							+ "元|" + language + "|" + version);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Map<String, String> getMoviesByLocationStringID(
			String strLocationStringID) {
		Map<String, String> mapMovie = new HashMap<String, String>();
		try {
			// China_Beijing
			Document doc = Jsoup.connect(
					"http://theater.mtime.com/" + strLocationStringID
							+ "/movie/").get();
			Elements docShowTimeList = doc
					.select("div.i_showtimelist h3.fl.px14");
			for (Element item : docShowTimeList) {
				mapMovie.put(item.child(0).attr("href").split("/movie/")[1]
						.split("/")[0], item.child(0).html());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return mapMovie;
	}
}
