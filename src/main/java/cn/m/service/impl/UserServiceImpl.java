package cn.m.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.m.mappers.UserMapper;
import cn.m.models.User;
import cn.m.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserById(String id){
		return userMapper.getUserById(id);
	}

	@Override
	public List<User> getAllUser() {
		return userMapper.getAllUser();
	}
}
