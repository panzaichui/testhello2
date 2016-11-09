package com.murong.repl;
/**
 * SQL语句的格式化
 * @author MACHENIKE
 *
 */
public class SqlRecl {
	public static void main(String[] args) {
		String str="18, 0, 11, 125959, 1, 20160530, 20160918, 7010010, 银行卡收单清结算改造测试商户, 301101010017987, 20160918182421, U, 110, 02, 1, 20991231, 182421, 280682584268368251, 收单, 188548441, 280682584268368251, 1, ALIPAY, 020A, 301101010017987, 北京市, QRP, 201605300002565335, QRP, 20160825";
		String[] strs=str.split(",\\s");
		StringBuffer spen=new StringBuffer();
		for(String tmp:strs){
			spen.append("'"+tmp+"',");
		}
		System.out.println(spen);
	}

}
