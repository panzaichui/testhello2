package com.murong.weixinMD5;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class TestWeixinMD5 {
	public String aa="111";
	public int bb=222;
	@Test
	public  void test() {
		HashMap map = new HashMap();
		Set set=new HashSet();
		
		Field[] fields = this.getClass().getDeclaredFields();
		Field[] arr$ = fields;
		int len$ = fields.length;
		
		for(int i$ = 0; i$ < len$; ++i$) {
            Field field = arr$[i$];

            try {
                Object obj = field.get(this);
                if(obj != null) {
                    map.put(field.getName(), obj);
                    System.out.println(field.getName());
                    System.out.println(obj.toString());
                }
            } catch (IllegalArgumentException var9) {
                var9.printStackTrace();
            } catch (IllegalAccessException var10) {
                var10.printStackTrace();
            }
        }
		
	}

}
