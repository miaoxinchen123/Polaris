package com.ebook.polaris.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.polaris.dao.OrderDao;
import com.ebook.polaris.model.Order;
import com.ebook.polaris.model.Order.OrderEnumType;
import com.ebook.polaris.service.OrderService;

@Service("orderService")  
public class OrderServiceImpl implements OrderService {

	@Autowired  
    private OrderDao orderDao;
	
	public List<Order> queryOrderByEmail(String email, OrderEnumType[] orderStatus) {
		return orderDao.queryOrderByEmail(email, orderStatus);
	}

	public Order getOrder(String id) {
		return orderDao.get(id);
	}

	public String saveOrder(Order order) {
		 return orderDao.save(order);
	}

	public void updateOrder(Order order) {
		orderDao.saveOrUpdate(order);
	}

	public void deleteOrder(String[]  orderIds) {
		for(int i=0;i<orderIds.length;i++){
			orderDao.delete(orderIds[i]);
		}
		
	}  

}
