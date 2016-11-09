package com.murong.weixinMD5;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Test1 {
	@Test
	public void TestList(){
		List list=new ArrayList<String>();
		list.add("id=1");
		list.add("name=zhangsan");
		String[] str=(String[])list.toArray(new String[list.size()]);
		System.out.println(str[0]);
	}

}
