package com.cidic.design;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class HttpServiceTest {
	public final  static String SAMPLE_URL = "http://design.hnu.edu.cn/pinwall/api/topics/57/artifacts?noCache=1460425238774&count=3&last_id=0"; 
	//public final  static String SAMPLE_URL2 = "http://facebook.com/pinwall/api/topics/57/artifacts?noCache=1460425238774&count=3&last_id=0"; 
	
	@Test
	public void givenLowTimeout_whenExecutingRequestWithTimeout_thenException(){ 
		CloseableHttpClient client = HttpClientBuilder.create().build();  
	    RequestConfig requestConfig = RequestConfig.custom()  
	    .setConnectionRequestTimeout(5000).setConnectTimeout(5000)  
	    .setSocketTimeout(5000).build();  
	    HttpGet request = new HttpGet(SAMPLE_URL);  
	    request.setConfig(requestConfig);  
	    CloseableHttpResponse response;
		try {
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			analysisJsonData(EntityUtils.toString(entity));
		}
		catch(ConnectTimeoutException e){
			e.printStackTrace();
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}  
	
	public Map<Integer,String> analysisJsonData(String jsonString){
		Map<Integer,String> urlMap = new HashMap<Integer,String>();
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		JSONArray artifacts = jsonObject.getJSONArray("artifacts");
		for (int i = 0; i < artifacts.size(); i++){
			JSONObject tempObject = artifacts.getJSONObject(i);
			JSONObject artifact = tempObject.getJSONObject("artifact");
			System.out.println(artifact.get("profile_image"));
			urlMap.put(artifact.getInt("id"), artifact.getString("profile_image"));
		}
		return urlMap;
	}
}
