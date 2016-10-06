package com.ebook.polaris.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebook.polaris.dao.UserDao;
import com.ebook.polaris.model.User;


@Repository("userDao") 
public class UserDaoImpl implements UserDao {

    @Autowired  
    private SessionFactory sessionFactory;  
  
    private Session getCurrentSession() {  
        return this.sessionFactory.getCurrentSession();  
    }  
	
	public User load(String email) {
		return (User) this.getCurrentSession().load(User.class, email); 
	}

	public User get(String email) {
		return (User) this.getCurrentSession().get(User.class, email);  
	}
	
	public List<User> findAll() {
		List<User> users = this.getCurrentSession().createQuery("from user").setCacheable(true).list();  
        return users;  
	}

	public void persist(User entity) {
		  this.getCurrentSession().persist(entity);  
	}

	public String save(User entity) {
		  return (String) this.getCurrentSession().save(entity);  
	}

	public void saveOrUpdate(User entity) {
		this.getCurrentSession().saveOrUpdate(entity);  
	}

	public void delete(String email) {
		User entity = this.load(email);  
        this.getCurrentSession().delete(entity);  
	}
	
	public User queryUserByEmailAndPassWord(User user){
		SQLQuery  sqlQuery= this.getCurrentSession().createSQLQuery("select * from user where email = ? and password= ?");
		sqlQuery.setParameter(0, user.getEmail());
		sqlQuery.setParameter(1, user.getPassword());
		List<User> users  = sqlQuery.addEntity(User.class).list();
		return (null==users||users.size()==0) ? null:users.get(0);  
	}

	public User queryUserByInvitionCode(String invitionCode) {
		SQLQuery  sqlQuery= this.getCurrentSession().createSQLQuery("select * from user where invitation_code = ?");
		sqlQuery.setParameter(0,invitionCode);
		List<User> users  = sqlQuery.addEntity(User.class).list();
		return (null==users||users.size()==0) ? null:users.get(0);
	}

}
