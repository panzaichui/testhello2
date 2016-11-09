package com.murong.repl;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 
 */
public class TestJsonPost {

  public static void main(String arg[]) throws Exception {
    String url = "test.com";
    JSONObject params = new JSONObject();
    params.put("SRC_STM_CODE", "wsf");
    params.put("TENANT_ID", "123");
    params.put("NM", "张三");
    params.put("BRTH_DT", "1983-01-20");
    params.put("GND_CODE", "1");
    System.out.println(params.toString());
  }

  /**
   httpClient的get请求方式2
   * @return
   * @throws Exception
   */
  

}
