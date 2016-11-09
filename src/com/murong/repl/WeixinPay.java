package com.murong.repl;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;



public class WeixinPay  
{  
  
    // HttpClient ����Http�ͻ���   
    // HttpEntity ��Ϣ����,���ͻ��߽�����Ϣ������,����ͨ���ͻ���������߷�������Ӧ��ȡʵ��  
    // HttpConnection ����http����  
	//����
    /** 
     * @param args 
     * @throws JSONException 
     */  
    public  void get(HashMap<String,String> map,String ma)  
    {  
    	
		StringBuffer spen = new StringBuffer();
		for (String key : map.keySet()) {
			//System.out.println("key= " + key + " and value= " + map.get(key));
			spen.append(key+"="+map.get(key)+"&");
		}
		StringBuffer s=spen.deleteCharAt(spen.length()-1);
        // ����Ĭ�ϵĿͻ���ʵ��  
        HttpClient httpCLient = new DefaultHttpClient();  
          
        // ����get����ʵ��  
       HttpGet httpget = new HttpGet("http://192.168.9.143:8281/sysmng/OQRPPUB1/"+ma+".dow?"+s.toString());  
        
        System.out.println("sendmsg OQRPPUB1 7010010 -d "+s.toString());
        
        
          
        System.out.println("executing request "+httpget.getURI());  
          
        try  
        {  
              
            // �ͻ���ִ��get���� ������Ӧʵ��  
            HttpResponse response = httpCLient.execute(httpget);  
              
            // ��������Ӧ״̬��  
            System.out.println(response.getStatusLine());  
              
            Header[] heads = response.getAllHeaders();  
            // ��ӡ������Ӧͷ  
            for(Header h:heads){  
                //System.out.println(h.getName()+":"+h.getValue());  
            }  
              
            // ��ȡ��Ӧ��Ϣʵ��  
            HttpEntity entity = response.getEntity();  
              
            System.out.println("------------------------------------");  
              
              
              
            if(entity != null){  
                //��Ӧ����  
            	String jsonStr=EntityUtils.toString(entity);
                //System.out.println(jsonStr);  
                JSONObject jsonObject = null;
				try {
					jsonObject = new JSONObject(jsonStr);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                String gwa = jsonObject.getString("GWA");
                jsonObject = new JSONObject(gwa);
                String msg_cd=jsonObject.getString("MSG_CD");
                String msg_dat=jsonObject.getString("MSG_DAT");
                System.out.println("MSG_CD="+msg_cd);
                System.out.println("MSG_DAT="+msg_dat);
                System.out.println("----------------------------------------");  
                // ��Ӧ���ݳ���  
                System.out.println("��Ӧ���ݳ��ȣ�"+entity.getContentLength());  
            }  
              
        } catch (Exception e){  
            e.printStackTrace();  
        }finally{  
            httpCLient.getConnectionManager().shutdown();  
        }  
        
    }
    /**
     *���� 
     */
	@Test
	public void xiaofei(){
		/**
		 * ��������
		 */
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("BUS_CNL", "QRP");
		map.put("SYS_CNL", "QRP");
		map.put("BUS_TYP", "020A");
		map.put("SUB_BUS_TYP", "1");
		map.put("BIP_CD", "1");
		map.put("BIP_ORD_DT", "20160923");
		map.put("MERC_ID", "301101810018847");
		map.put("ORD_AMT", "0.02");
		map.put("TX_DESC", "1");
		map.put("BIP_ORD_NO", "1278878");
		map.put("RUT_CORG", "MUCFC");
		map.put("AUTH_CD", "184698058420911002");
		
		map.put("DIS_TYP", "0");
		map.put("HP_NUM", "12");
		map.put("PRD_TYP", "11");
		map.put("PRD_NM", "tttsst");
		map.put("STO_ID", "1515");
		map.put("STO_PROV", "0001");
		map.put("STO_DC", "0111");
		map.put("STO_CITY", "0011");
		map.put("TRM_NO", "12345678");
		map.put("OPR_ID", "2578945");
		map.put("MBL_NO", "18840852352");
		map.put("CORG_SUB_MERC", "11211");
		map.put("BIZ_JRN_NO", "28859933");
		String ma="7010010";
		get(map,ma);
    }

  
}  
