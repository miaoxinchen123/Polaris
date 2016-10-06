package com.ebook.polaris.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebook.polaris.dao.CreditsDao;
import com.ebook.polaris.dao.OrderDao;
import com.ebook.polaris.dao.UserDao;
import com.ebook.polaris.dao.UserRelationDao;
import com.ebook.polaris.model.Credits;
import com.ebook.polaris.model.Order;
import com.ebook.polaris.model.Order.OrderEnumType;
import com.ebook.polaris.model.User;
import com.ebook.polaris.model.UserRelation;
import com.ebook.polaris.service.OrderService;

@Service("orderService")  
public class OrderServiceImpl implements OrderService {

	@Autowired  
    private OrderDao orderDao;
	@Autowired  
    private UserRelationDao userRelationDao;
	
	@Autowired  
    private UserDao userDao;
	
	@Autowired  
    private CreditsDao creditsDao;
	
	private static final int LEVEL_ONE = (int) 0.3;
	private static final int LEVEL_TWO = (int) 0.1;
	private static final int LEVEL_THREE = (int) 0.2;
	
	public List<Order> queryOrderByEmail(String email, OrderEnumType[] orderStatus) {
		return orderDao.queryOrderByEmail(email, orderStatus);
	}

	public Order getOrder(String id) {
		return orderDao.get(id);
	}

	public String saveOrder(Order order) {
		//生成订单的时候，插入返现表
		 if(order.getOrderStatus().equals(OrderEnumType.PAY_NO_SEAND)){
			 //第一级返现查询
			 UserRelation levelOneUserRelation =  userRelationDao.queryUseRelationBySelfUser(order.getEmail());
			 if(levelOneUserRelation!=null){
				 User levelOneUser = userDao.get(levelOneUserRelation.getFatherUser());
				 if(levelOneUser.getCodeStatus() =="yes"){
					 Credits levelOneCredits = new Credits();
					 levelOneCredits.setEmail(levelOneUser.getEmail());
					 levelOneCredits.setCreditsType("reward");
					 levelOneCredits.setBizNo(order.getOrderId());
					 levelOneCredits.setCreateTime(new Date());
					 levelOneCredits.setCredits(order.getPrice()*LEVEL_ONE*30);
					 creditsDao.save(levelOneCredits);
					 
						 //第二级返现查询
						 UserRelation levelTwoUserRelation =  userRelationDao.queryUseRelationBySelfUser(levelOneUser.getEmail());
						 if(levelTwoUserRelation!=null){
							 User levelTwoUser = userDao.get(levelTwoUserRelation.getFatherUser());
							 if(levelTwoUser.getCodeStatus() =="yes"){
								 Credits levelTwoCredits = new Credits();
								 levelTwoCredits.setEmail(levelTwoUser.getEmail());
								 levelTwoCredits.setCreditsType("reward");
								 levelTwoCredits.setBizNo(order.getOrderId());
								 levelTwoCredits.setCreateTime(new Date());
								 levelTwoCredits.setCredits(order.getPrice()*LEVEL_ONE*10);
								 creditsDao.save(levelTwoCredits);
							 }
							//第三级返现查询
							 UserRelation levelThereUserRelation =  userRelationDao.queryUseRelationBySelfUser(levelTwoUser.getEmail());
							 if(levelThereUserRelation!=null){
								 User levelThereUser = userDao.get(levelThereUserRelation.getFatherUser());
								 if(levelThereUser.getCodeStatus() =="yes"){
									 Credits levelThereCredits = new Credits();
									 levelThereCredits.setEmail(levelTwoUser.getEmail());
									 levelThereCredits.setCreditsType("reward");
									 levelThereCredits.setBizNo(order.getOrderId());
									 levelThereCredits.setCreateTime(new Date());
									 levelThereCredits.setCredits(order.getPrice()*LEVEL_ONE*10);
									 creditsDao.save(levelThereCredits);
								 }
							 }
						 }
					 }
				 }
			 }
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
