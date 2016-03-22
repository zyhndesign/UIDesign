package com.cidic.design.dao;

import com.cidic.design.model.User;

public interface UserDao {
	
	public void insertUser(User user);
	
	public boolean checkUser(User user);
	
	public boolean checkUserName(String username);
}
