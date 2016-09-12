package com.ebook.polaris.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebook.polaris.dao.OrderDao;
import com.ebook.polaris.model.Order;
import com.ebook.polaris.model.Order.OrderEnumType;


@Repository("orderDao") 
public class OrderDaoImpl implements OrderDao {
	
	@Autowired  
    private SessionFactory sessionFactory;  
  
    private Session getCurrentSession() {  
        return this.sessionFactory.getCurrentSession();  
    }  

	public Order load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Order get(String id) {
		return (Order) this.getCurrentSession().get(Order.class, id);  
	}

	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void persist(Order entity) {
		

	}

	public String save(Order entity) {
		return (String) this.getCurrentSession().save(entity);  
	}

	public void saveOrUpdate(Order entity) {
		this.getCurrentSession().update(entity);

	}

	public void delete(String id) {
		this.getCurrentSession().delete(this.getCurrentSession().get(Order.class, id));

	}

	@SuppressWarnings("unchecked")
	public List<Order> queryOrderByEmail(String email, OrderEnumType[] orderStatus) {
		List<Order> orders = null;
		
		if(orderStatus[0].equals(OrderEnumType.SHOPPING_CART)){
			Criteria criteria = this.getCurrentSession().createCriteria(Order.class);
			criteria.add(Restrictions.eq("orderStatus",OrderEnumType.SHOPPING_CART));
			criteria.add(Restrictions.eq("email", email));
			criteria.addOrder(org.hibernate.criterion.Order.desc("updateTime"));
			orders = criteria.list(); 
		}else{
			Criteria criteria = this.getCurrentSession().createCriteria(Order.class);
			criteria.add(Restrictions.eq("email", email));
			criteria.add(Restrictions.in("orderStatus",orderStatus));
			criteria.addOrder(org.hibernate.criterion.Order.desc("updateTime"));
			orders = criteria.list(); 
		}
		return orders;
	}

}
