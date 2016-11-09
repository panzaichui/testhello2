package com.murong.weixinpay;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class WeixinPay {
	@Test
	public void pay() throws Exception {
		StringBuilder sb = new StringBuilder();
		File file = new File("D:/work/TestFtp/src/para.xml");

		InputStreamReader isr = new InputStreamReader(
				new FileInputStream(file), "UTF-8");
		BufferedReader bu = new BufferedReader(isr);
		String str = "";
		while ((str = bu.readLine()) != null) {
			sb.append(str);
		}
        System.out.println(sb.toString());

		
		String parameters = sb.toString();
		String apiURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost method = new HttpPost(apiURL);

		method.addHeader("Content-type", "application/xml; charset=utf-8");
		method.setHeader("Accept", "application/xml");
		method.setEntity(new StringEntity(parameters, Charset.forName("UTF-8")));

		HttpResponse response = httpClient.execute(method);
		int statusCode = response.getStatusLine().getStatusCode();
		InputStream in=response.getEntity().getContent();
//		HttpEntity entity=response.getEntity();
//		String xmlContent=EntityUtils.toString(entity);
//		System.out.println(xmlContent);
//		int hasread=0;
//		byte[] b=new byte[1024];
//		while((hasread=in.read(b))>0){
//			String s=new String(b, "utf-8");
//			System.out.println(s);
//		}

           HttpEntity entity = response.getEntity();

           String s = EntityUtils.toString(entity, "UTF-8");
           System.out.println(s);
		System.out.println(statusCode);

	}
}
