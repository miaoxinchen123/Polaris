package com.ebook.polaris.dao;

import com.ebook.polaris.model.User;

public interface UserDao extends DefaultDao<User, String> {  
	
	public User queryUserByEmailAndPassWord(User user);
	
	public User queryUserByInvitionCode(String  invitionCode);
}  
