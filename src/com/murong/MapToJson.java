package com.murong;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.junit.Test;

import com.murong.objectTOxml.Person;
/**
 * ����java����תjson
 * @author pzc
 *
 */
public class MapToJson {
	@Test
	public void mapToJson() {
       Map map=new HashMap<String, String>();
       map.put("id", "1");
       map.put("name", "���ڴ�");
       
       Person person=new Person();
       person.setId("1");
       person.setName("���ڴ�");
       JSONArray jsonArray = JSONArray.fromObject(person);  
       System.out.println(jsonArray.toString());
	}

}
