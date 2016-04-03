package cn.iwalkers.services;

import java.util.List;

import cn.iwalkers.dao.UserDao;
import cn.iwalkers.entity.User;

public class UserServiceImpl implements UserServices{

	@Override
	public boolean login(String username, String password) {
		User u = new UserDao().getUserByUsername(username);
		if(u == null)
			return false;
		if(u.getPassword().equals(password))
			return true;
		return false;
	}

	@Override
	public boolean register(User user) {
		try{
			new UserDao().save(user);	
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getUser(String username) {
		return new UserDao().getUserByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return null;
	}

	@Override
	public boolean update(User user) {
		try{
			new UserDao().update(user);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
