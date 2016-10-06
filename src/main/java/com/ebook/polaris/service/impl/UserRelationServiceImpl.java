package com.ebook.polaris.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.polaris.dao.UserRelationDao;
import com.ebook.polaris.model.UserRelation;
import com.ebook.polaris.service.UserRelationService;

@Service("userRelationService")  
public class UserRelationServiceImpl implements UserRelationService {  
  
    @Autowired  
    private UserRelationDao userRelationDao;  
  
    public UserRelation load(String id) {  
        return userRelationDao.load(id);  
    }  
  
    public UserRelation get(String id) {  
        return userRelationDao.get(id);  
    }  
  
     
    public List<UserRelation> findAll() {  
        return userRelationDao.findAll();  
    }  
  
     
    public void persist(UserRelation entity) {  
        userRelationDao.persist(entity);  
    }  
  
     
    public String save(UserRelation entity) {  
        return userRelationDao.save(entity);  
    }  
  
     
    public void saveOrUpdate(UserRelation entity) {  
        userRelationDao.saveOrUpdate(entity);  
    }  
  
     
    public void delete(String id) {  
        userRelationDao.delete(id);  
    }  
    

	public UserRelation queryUseRelationBySelfUser(String UserRelation) {
		return userRelationDao.queryUseRelationBySelfUser(UserRelation);
	}
  
}  