package cn.iwalkers.services;

import java.util.List;

import cn.iwalkers.entity.User;

public interface UserServices {
	public boolean login(String username,String password);
	public boolean register(User user);
	public User getUser(String username);
	public List<User> getAllUsers();
}
