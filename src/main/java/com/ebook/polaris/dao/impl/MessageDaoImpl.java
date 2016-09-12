package com.ebook.polaris.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebook.polaris.dao.MessageDao;
import com.ebook.polaris.model.Message;
import com.ebook.polaris.model.Message;


@Repository("messageDao") 
public class MessageDaoImpl implements MessageDao {
	
	@Autowired  
    private SessionFactory sessionFactory;
	
	private Session getCurrentSession() {  
        return this.sessionFactory.getCurrentSession();  
    } 

	public Message load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Message get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Message> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void persist(Message entity) {
		// TODO Auto-generated method stub
		
	}

	public String save(Message entity) {
		return (String) this.getCurrentSession().save(entity); 
	}

	public void saveOrUpdate(Message entity) {
		// TODO Auto-generated method stub
		
	}

	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}  
  
}
