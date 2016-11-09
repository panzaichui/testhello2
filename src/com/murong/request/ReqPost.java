package com.murong.request;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ReqPost {
	/**
	 * 消费请求
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * 建立参数
		 */
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("BUS_CNL", "QRP");
		map.put("SYS_CNL", "QRP");
		map.put("BUS_TYP", "020A");
		map.put("SUB_BUS_TYP", "1");
		map.put("BIP_CD", "1");
		map.put("BIP_ORD_DT", "20160825");
		map.put("MERC_ID", "301101010020144");
		map.put("TX_AMT", "100");
		map.put("TX_DESC", "1");
		map.put("BIP_ORD_NO", "35512");
		map.put("RUT_CORG", "ALIPAY");

		StringBuffer spen = new StringBuffer();
		for (String key : map.keySet()) {
			System.out.println("key= " + key + " and value= " + map.get(key));
			spen.append(map.get(key));
		}

		String url = "http://192.168.9.143:8281/sysmng/OQRPPUB1/7010010.dow";
		HttpClient httpClient=new DefaultHttpClient();
		HttpPost method = new HttpPost(url);
		try {
			StringEntity entity = new StringEntity(spen.toString());
			method.setEntity(entity);
			System.out.println(method==null);
			HttpResponse result = httpClient.execute(method);
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";

				str = EntityUtils.toString(result.getEntity());
				System.out.println(str);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
