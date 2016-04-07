package com.cidic.design.service;

import com.cidic.design.model.User;

public interface UserService {
	
	public void insertUser(User user);
	
	public boolean checkUser(User user);
	
	public boolean checkUserName(String username);
}
