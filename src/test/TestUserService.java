package test;

import org.junit.Test;

import cn.iwalkers.dao.UserDao;
import cn.iwalkers.entity.User;
import cn.iwalkers.services.UserServiceImpl;

public class TestUserService {

	@Test
	public void testRegister() {
			User u = new User();
			u.setUsername("apandi");
			u.setPassword("hahaha");
			System.out.println(new UserServiceImpl().register(u));
	}

}
