package com.murong.objectTOxml;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 测试Object对象转换为xml格式数据
 * @author pzc
 *
 */
public class ObjectTOXML {
	@Test
	public void obTOxml(){
		Person person=new Person();
		person.setId("1");
		person.setName("潘在垂");
		XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		String xmlData=xStreamForRequestPostData.toXML(person);
		System.out.println(xmlData);
		 
		
		
	}

}
