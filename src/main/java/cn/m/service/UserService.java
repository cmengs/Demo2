package cn.m.service;

import java.util.List;

import cn.m.models.User;

public interface UserService {

	public User getUserById(String id);
	
	public List<User> getAllUser();
}
