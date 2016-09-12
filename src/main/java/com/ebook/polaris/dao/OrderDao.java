package com.ebook.polaris.dao;

import java.util.List;

import com.ebook.polaris.model.Order;
import com.ebook.polaris.model.Order.OrderEnumType;

public interface OrderDao extends DefaultDao<Order, String> {  
	/**
	 *  根据邮箱/状态  查找订单/购物车  --------用户获取全部订单/获取购物车数量
	 */
	public List<Order> queryOrderByEmail(String email,OrderEnumType[] orderStatus);
}  
