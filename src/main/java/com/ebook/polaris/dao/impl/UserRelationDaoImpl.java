package com.ebook.polaris.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebook.polaris.dao.UserRelationDao;
import com.ebook.polaris.model.UserRelation;


@Repository("userRelationDao") 
public class UserRelationDaoImpl implements UserRelationDao {

	@Autowired  
    private SessionFactory sessionFactory;  
  
    private Session getCurrentSession() {  
        return this.sessionFactory.getCurrentSession();  
    }  

	public UserRelation load(String id) {
		return (UserRelation) this.getCurrentSession().load(UserRelation.class, id); 
	}

	public UserRelation get(String id) {
		return (UserRelation) this.getCurrentSession().get(UserRelation.class, id); 
	}

	public List<UserRelation> findAll() {
		List<UserRelation> users = this.getCurrentSession().createQuery("from user_relation").setCacheable(true).list();  
        return users;  
	}

	public void persist(UserRelation entity) {
		this.getCurrentSession().persist(entity);  

	}

	public String save(UserRelation entity) {
		 return (String) this.getCurrentSession().save(entity);  
	}

	public void saveOrUpdate(UserRelation entity) {
		this.getCurrentSession().saveOrUpdate(entity);  

	}

	public void delete(String id) {
		UserRelation entity = this.load(id);  
        this.getCurrentSession().delete(entity);  

	}

	public UserRelation queryUseRelationBySelfUser(String selfUser) {
		SQLQuery  sqlQuery= this.getCurrentSession().createSQLQuery("select * from user_relation where selfUser = ?");
		sqlQuery.setParameter(0, selfUser);
		List<UserRelation> users  = sqlQuery.addEntity(UserRelation.class).list();
		return (null==users||users.size()==0) ? null:users.get(0);  
	}

}
