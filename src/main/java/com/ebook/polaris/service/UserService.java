package com.ebook.polaris.service;

import java.util.List;

import com.ebook.polaris.model.User;
import com.ebook.polaris.model.UserRelation;

public interface UserService {  
    User load(String id);  
  
    User get(String id);  
  
    List<User> findAll();  
  
    void persist(User entity);  
  
    String save(User entity);  
    
    String save(User entity,User fatherUser);  
  
    void saveOrUpdate(User entity);  
  
    void delete(String id);  
    
    User queryUserByEmailAndPassWord(User user);
    
    User queryUserByInvitionCode(String invitationCode);
  
}  