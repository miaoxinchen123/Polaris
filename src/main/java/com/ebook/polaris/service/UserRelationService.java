package com.ebook.polaris.service;

import java.util.List;

import com.ebook.polaris.model.UserRelation;

public interface UserRelationService {  
    UserRelation load(String id);  
  
    UserRelation get(String id);  
  
    List<UserRelation> findAll();  
  
    void persist(UserRelation entity);  
  
    String save(UserRelation entity);  
  
    void saveOrUpdate(UserRelation entity);  
  
    void delete(String id);  
    
    UserRelation queryUseRelationBySelfUser(String UserRelation);
  
}  