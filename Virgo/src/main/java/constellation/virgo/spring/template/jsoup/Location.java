package constellation.virgo.spring.template.jsoup;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.jsoup.Jsoup;

import constellation.virgo.spring.template.common.IPAddressValidator;

public class Location {

	public static void main(String[] args) {
		System.out.println(getCityByIP(null));

	}

	public static String getCityByIP(String IP) {
		String requestURL = "http://api.map.baidu.com/location/ip?ak=FkOIO99t8g0cPBf6BV7RyZ0R";
		String city = null;
		if (IP != null && IPAddressValidator.validate(IP)) {
			requestURL += "&ip=" + IP;
		}
		try {
			String responseBody = Jsoup.connect(requestURL)
					.ignoreContentType(true).execute().body();
			JSONObject jsonobjLocation = JSONObject.fromObject(responseBody)
					.getJSONObject("content").getJSONObject("address_detail");
			city = jsonobjLocation.getString("city");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return city;
	}

}
