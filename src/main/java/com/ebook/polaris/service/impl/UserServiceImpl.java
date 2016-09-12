package com.ebook.polaris.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.polaris.dao.UserDao;
import com.ebook.polaris.model.User;
import com.ebook.polaris.service.UserService;

@Service("userService")  
public class UserServiceImpl implements UserService {  
  
    @Autowired  
    private UserDao userDao;  
  
    public User load(String id) {  
        return userDao.load(id);  
    }  
  
    public User get(String id) {  
        return userDao.get(id);  
    }  
  
     
    public List<User> findAll() {  
        return userDao.findAll();  
    }  
  
     
    public void persist(User entity) {  
        userDao.persist(entity);  
    }  
  
     
    public String save(User entity) {  
        return userDao.save(entity);  
    }  
  
     
    public void saveOrUpdate(User entity) {  
        userDao.saveOrUpdate(entity);  
    }  
  
     
    public void delete(String id) {  
        userDao.delete(id);  
    }  
    
    public User queryUserByEmailAndPassWord(User user){
		return userDao.queryUserByEmailAndPassWord(user);
    	
    }
  
}  