package com.murong.test;

import org.junit.Test;

public class TestA {
	@Test
	public void tst(){
		FindFile findFile=new FindFile();
		findFile.findByFtp("test10010.txt");
		
	}

}
