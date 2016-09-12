package com.ebook.polaris.service;

import java.util.List;

import com.ebook.polaris.model.Order;
import com.ebook.polaris.model.Order.OrderEnumType;

public interface OrderService {
	
	/**
	 *  根据邮箱/状态  查找订单/购物车  --------用户获取全部订单/获取购物车数量
	 */
	public List<Order> queryOrderByEmail(String email,OrderEnumType[] orderStatus);
	
	
	/***
	 * 	 查询单个订单详情
	 */
	public Order getOrder(String id);
	
	/***
	 * 	新增订单/购物车
	 */
	public String saveOrder(Order order);
	
	/**
	 * 	修改 订单/购物车
	 */
	public void updateOrder(Order order);
	
	/**
	 * 	删除
	 */
	public void deleteOrder(String[] orderIdAarry);
}
