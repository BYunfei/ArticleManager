package test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import cn.iwalkers.dao.UserDao;
import cn.iwalkers.entity.User;

public class TestUserDAO {

	public void testSaveUser() {
		String[] username = TestUtil.createRandomString(20,6);
		String[] password = TestUtil.createRandomString(20,6);
		for (int i = 0; i < username.length; i++) {
			User u = new User();
			u.setPassword(password[i]);
			u.setUsername(username[i]);
			new UserDao().save(u);
		}
	}
	
	@Test
	public void testGetUserByID(){
		User u = new UserDao().getUserById(10);
		System.out.println("Get By ID: "+u.getPassword());
	}
	
	@Test
	public void testGetUserByUsername(){
		User u = new UserDao().getUserByUsername("abc");
		System.out.println("Get By Username: "+u.getPassword());
	}
	
	@Test
	public void testUpdateUser(){
		User u = new UserDao().getUserById(12);
		u.setPassword("hahaha");
		new UserDao().update(u);
	}
	
	public void testDeleteUser(){
		new UserDao().delete(60);
	}
	
}
