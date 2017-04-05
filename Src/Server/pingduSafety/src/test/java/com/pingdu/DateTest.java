package com.pingdu;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.pingdu.entity.user.User;

public class DateTest {

	
	public static class Stu{
		public Integer i;
		public boolean j;
		public User user;
		
		public void printFuck() {
			System.out.println(i);
			System.out.println(j);
			user = new User();
			System.out.println(user);
		}
	}
	public static void main(String[] args) {
		Date time = new Date(1489220640000L);
		SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(dFormat.format(time));
		System.out.println(dFormat.format(new Date()));
		System.err.print(new Date());
		System.out.println("adsasdasda");
	
	
		Stu ob = new Stu();
		ob.printFuck();
	}

}
