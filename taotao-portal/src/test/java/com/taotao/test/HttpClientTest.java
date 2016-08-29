package com.taotao.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	@Test
	public void TestHttpClient() throws Exception {
		 CloseableHttpClient httpClient=HttpClients.createDefault();
		 URIBuilder uriBuilder=new URIBuilder("http://www.baidu.com/s");
		 uriBuilder.addParameter("wd", "kobe");
		 HttpGet httpGet=new HttpGet(uriBuilder.build());
		 CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
		 HttpEntity httpEntity = httpResponse.getEntity();
		 String string = EntityUtils.toString(httpEntity,"utf-8");
		 System.out.println(string);
		 httpGet.clone();
		 httpClient.close();

		/*// 创建一个httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 创建一个GET对象
		HttpGet get = new HttpGet("http://www.sogou.com");
		// 执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		// 取响应的结果
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity, "utf-8");
		System.out.println(string);
		// 关闭httpclient
		response.close();
		httpClient.close();*/
	}

}
