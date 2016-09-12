package com.ebook.polaris.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebook.polaris.dao.CreditsDao;
import com.ebook.polaris.model.Credits;

@Repository("creditsDao") 
public class CreditsDaoImpl implements CreditsDao{
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {  
        return this.sessionFactory.getCurrentSession();  
    } 

	public Credits load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Credits get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Credits> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void persist(Credits entity) {
		// TODO Auto-generated method stub
		
	}

	public String save(Credits entity) {
		return (String) this.getCurrentSession().save(entity); 
	}

	public void saveOrUpdate(Credits entity) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}  
  
}
