package com.pingdu.utility;

import java.text.SimpleDateFormat;
import java.util.Random;

public class PublicVariable {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	public static Integer rows = 15;

	public static Integer getRows() {
		return rows;
	}

	public static void setRows(Integer rows) {
		PublicVariable.rows = rows;
	}
	
	 public static String getRandomString(int length) { // length表示生成字符串的长度
		 String base = "abcdefghijklmnopqrstuvwxyz0123456789"; Random random = new
		 Random(); StringBuffer sb = new StringBuffer(); for (int i = 0; i <
		 length; i++) { int number = random.nextInt(base.length());
		 sb.append(base.charAt(number)); } return sb.toString();
		 
	 
	 }
}
