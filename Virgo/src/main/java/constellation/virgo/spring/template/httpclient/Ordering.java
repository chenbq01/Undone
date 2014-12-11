package constellation.virgo.spring.template.httpclient;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class Ordering {

	public static void main(String[] args) {

		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = new HttpGet(
				"http://www.hipiao.com/member/index/buy/sysauto.php?rand=0.5sasdaff7ydasdffsusdffdsfsddsfsdfsd");
		// System.out.println(httpGet.getRequestLine());

		try {
			for (int i = 0; i < 1; i++) {
				System.out.println("请求第" + i + "次开始");
				long a = System.currentTimeMillis();
				HttpResponse httpResponse = closeableHttpClient
						.execute(httpGet);
				HttpEntity entity = httpResponse.getEntity();
				System.out.println("status:" + httpResponse.getStatusLine());
				if (entity != null) {
					// System.out.println("contentEncoding:"
					// + entity.getContentEncoding());
					System.out.println("response content:"
							+ EntityUtils.toString(entity));
				}
				System.out.println("执行耗时 : " + (System.currentTimeMillis() - a)
						/ 1000f + " 秒 ");
				System.out.println("请求第" + i + "次结束");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
