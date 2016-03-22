package test;

import java.util.Random;

import cn.iwalkers.entity.User;

public class TestUtil {
	
	public static User[] createRandomUser(int num){
		String[] username = createRandomString(num,8);
		String[] password = createRandomString(num, 8);
		
		User[] users = new User[num];
		for (int i = 0; i < users.length; i++) {
			users[i] = new User();
			users[i].setUsername(username[i]);
			users[i].setPassword(password[i]);
		}
		return users;
	}
	
	public static String[] createRandomString(int num,int len){
		String[] str = new String[num];
		String base = "abcdefghijklmnopqrstuvwxyz0123456789"; 
		Random random = new Random();
		for (int i = 0; i < str.length; i++) {
		    StringBuffer sb = new StringBuffer();     
		    for (int j = 0; j < len; j++) {     
		        int number = random.nextInt(base.length());     
		        sb.append(base.charAt(number));     
		    }
		    str[i] = sb.toString();
		}
		
		return str;
	}
}
