package com.ebook.polaris.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ebook.polaris.dto.OrderDto;
import com.ebook.polaris.model.Order;
import com.ebook.polaris.model.Order.OrderEnumType;
import com.ebook.polaris.model.OrderDetail;
import com.ebook.polaris.model.User;
import com.ebook.polaris.service.OrderService;
import com.ebook.polaris.service.UserService;

@Controller  
public class OrderController {
	
	 @Autowired  
 	 private UserService userService;  
	 @Autowired  
 	 private OrderService orderService;  
	 
	 /** 总记录数 使用静态变量的方式缓存 **/
	 private Set<OrderDetail> orderDetailSet;
		
		
	
	/**
	 * 购物车添加记录
	 * @return
	 */
	@RequestMapping(value="/user/addCart", method = {RequestMethod.POST })
	@ResponseBody 
	public String addOrder(@RequestBody OrderDto orderDto){
		Subject currentUser = SecurityUtils.getSubject(); 
		if(currentUser == null || currentUser.getPrincipal() ==null){
			return "请重新登陆";
		}
		//获取用户信息
		User userInfo = userService.get(currentUser.getPrincipal().toString());
		//根据操作类型  添加购物车 直接购买来
		if(orderDto.getTradeType().equals("directBuy")){
        	//生成主表信息
        	Order order = new Order();
        	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        	Date date=new Date();
        	
            UUID uuid = UUID.randomUUID();
            String orderId = sdf.format(date)+uuid.toString().substring(0, 23);
        	order.setOrderId(orderId);
        	if(null ==  orderDto.getBizNo() || "".equals(orderDto.getBizNo())){
        		return "请输入正确的业务流水号";
        	}
        	order.setBizNo(orderDto.getBizNo());
        	order.setOrderStatus(OrderEnumType.PAY_NO_SEAND);
        	order.setEmail(userInfo.getEmail());
        	order.setPrice(orderDto.getPrice());
        	order.setCreateTime(new Date());
        	order.setUpdateTime(new Date());
        	//生成子表信息
        	Set<OrderDetail> orders = new HashSet<OrderDetail>();
        	OrderDetail orderDetail = new OrderDetail();
        	orderDetail.setCoverUrl(orderDto.getCoverUrl());
        	orderDetail.setMd5(orderDto.getMd5());
        	orderDetail.setPrice(orderDto.getPrice());
        	orderDetail.setAuthors(orderDto.getAuthors());
        	orderDetail.setSize(orderDto.getSize());
        	orderDetail.setTitle(orderDto.getTitle());
        	orderDetail.setCreateTime(new Date());
        	orderDetail.setUpdateTime(new Date());
        	orders.add(orderDetail);
        	order.setOrders(orders);
        	orderService.saveOrder(order);
        	return "付款成功，系统将在一日之内发货";
		}else{
			//查询购物车
        	List<Order> userOrders=orderService.queryOrderByEmail(userInfo.getEmail(), OrderEnumType.getCartEnum());
			//添加购物车时，根据购物车ID 判断是新创建一个购物车还是修改一个购物车
			if(null == userOrders || userOrders.isEmpty()){
				//新建购物车
			  	//生成主表信息
	        	Order order = new Order();
	        	Calendar c = Calendar.getInstance();
	        	c.setTime(new Date());
	        	//年月日 + 24位UUID数值生成订单号
	            UUID uuid = UUID.randomUUID();
	            String orderId = c.get(Calendar.YEAR)+c.get(Calendar.MONTH)+c.get(Calendar.DAY_OF_MONTH)+uuid.toString().substring(0, 23);
	        	order.setOrderId(orderId);
	        	order.setOrderStatus(OrderEnumType.SHOPPING_CART);
	        	order.setEmail(userInfo.getEmail());
	        	order.setPrice(orderDto.getPrice());
	        	order.setCreateTime(new Date());
	        	order.setUpdateTime(new Date());
	        	//生成子表信息
	        	Set<OrderDetail> orders = new HashSet<OrderDetail>();
	        	OrderDetail orderDetail = new OrderDetail();
	        	orderDetail.setCoverUrl(orderDto.getCoverUrl());
	        	orderDetail.setMd5(orderDto.getMd5());
	        	orderDetail.setAuthors(orderDto.getAuthors());
	        	orderDetail.setPrice(orderDto.getPrice());
	        	orderDetail.setSize(orderDto.getSize());
	        	orderDetail.setTitle(orderDto.getTitle());
	        	orderDetail.setCreateTime(new Date());
	        	orderDetail.setUpdateTime(new Date());
	        	orders.add(orderDetail);
	        	order.setOrders(orders);
	        	orderService.saveOrder(order);
	        	return "successAdd";
			}else{
				Order order = userOrders.get(0);
				Set<OrderDetail> orderSet = order.getOrders();
				Iterator<OrderDetail> it = orderSet.iterator();
				while(it.hasNext()){
					OrderDetail tempDetail = it.next();
					if(tempDetail.getMd5().equals(orderDto.getMd5())){
						return "已添加！请不用重复添加";
					}
				}
				order.setPrice(order.getPrice()+orderDto.getPrice());
				order.setUpdateTime(new Date());
				OrderDetail orderDetail = new OrderDetail();
	        	orderDetail.setCoverUrl(orderDto.getCoverUrl());
	        	orderDetail.setMd5(orderDto.getMd5());
	        	orderDetail.setAuthors(orderDto.getAuthors());
	        	orderDetail.setPrice(orderDto.getPrice());
	        	orderDetail.setSize(orderDto.getSize());
	        	orderDetail.setTitle(orderDto.getTitle());
	        	orderDetail.setCreateTime(new Date());
	        	orderDetail.setUpdateTime(new Date());
	        	orderSet.add(orderDetail);
	        	order.setOrders(orderSet);
	        	orderService.updateOrder(order);
	        	return "successAdd";
			}
		}
	}
	
	
	
	/**
	 * 购物车 删除记录
	 * @return
	 */
	@RequestMapping(value="/remove", method = {RequestMethod.GET })  
	public String RemoveCart(HttpServletRequest request,  Model model){
		String orderDetailId = request.getParameter("orderDetailId");
		String[] orderDetailIdArray = orderDetailId.split(",");
		Subject currentUser = SecurityUtils.getSubject(); 
		if(currentUser.getPrincipal()==null){
			return "index";
		}
		User userInfo = userService.get(currentUser.getPrincipal().toString());
		List<Order> userOrders=orderService.queryOrderByEmail(userInfo.getEmail(), OrderEnumType.getCartEnum());
		Order order = userOrders.get(0);
		Set<OrderDetail> orderSet = order.getOrders();
		Iterator<OrderDetail> it = orderSet.iterator();
		while(it.hasNext()){
			OrderDetail tempDetail = it.next();
			for(int i=0;i<orderDetailIdArray.length;i++){
				if(tempDetail.getId().equals(orderDetailIdArray[i])){
					it.remove();
				}
			}
			
		}
		orderService.updateOrder(order);
		return getCartDetail(model);
		
	}
	
	/**
	 * 购物车 是否有权限访问
	 */
	@RequestMapping(value="/user/cart", method = {RequestMethod.GET })  
	@ResponseBody 
	public String getCartValidate(){
		return "cart";
	}
	
	/**
	 * 订单 是否有权限访问
	 */
	@RequestMapping(value="/user/order", method = {RequestMethod.GET })  
	@ResponseBody 
	public String getOrderValidate(){
		return "order";
	}
	
	
	/**
	 * 订单列表 查询
	 */
	@RequestMapping(value="/order", method = {RequestMethod.GET })  
	public String getOrderList(Model model){
		Subject currentUser = SecurityUtils.getSubject(); 
		if(currentUser == null || currentUser.getPrincipal() ==null){
			return "/index";
		}
		List<Order> orders = orderService.queryOrderByEmail(currentUser.getPrincipal().toString(),OrderEnumType.getOrderEnum());
		if(!orders.isEmpty() &&  orders.get(0).getOrders()!=null && orders.get(0).getOrders().size() >0 ){
			model.addAttribute("orders", orders);
			model.addAttribute("size", orders.size());
		}else{
			model.addAttribute("emptyMessage", "无订单，请及时添加");
			model.addAttribute("size", 0);
		}
		return "/views/order";
	}
	
	
	/**
	 * 购物车 详情查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/cart", method = {RequestMethod.GET })  
	public String getCartDetail(Model model){
		Subject currentUser = SecurityUtils.getSubject(); 
		if(currentUser == null || currentUser.getPrincipal() ==null){
			return "/index";
		}
		List<Order> orders = orderService.queryOrderByEmail(currentUser.getPrincipal().toString(), OrderEnumType.getCartEnum());
		if(!orders.isEmpty() &&  orders.get(0).getOrders()!=null && orders.get(0).getOrders().size() >0 ){
			Order shoppingCart = (Order) orders.get(0);
			orderDetailSet = shoppingCart.getOrders();
			List orderDetailList =new ArrayList(orderDetailSet);
			model.addAttribute("orderDetails", orderDetailList);
			model.addAttribute("size", orderDetailList.size());
		}else{
			model.addAttribute("emptyMessage", "购物车暂时无书籍，请及时添加");
			model.addAttribute("size", 0);
		}
		return "/views/cart";
	}
	
	
	/**
	 * 购物车中直接购买 
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/cartDirectBuy", method = {RequestMethod.POST })  
	@ResponseBody 
	public String getUserOrder(@RequestBody OrderDto orderDto){
		Subject currentUser = SecurityUtils.getSubject(); 
		if(currentUser == null || currentUser.getPrincipal()==null ){
			return "请重新登陆";
		}
		List<Order> orders = orderService.queryOrderByEmail(currentUser.getPrincipal().toString(), OrderEnumType.getCartEnum());
		if(orders==null || orders.isEmpty() || orders.get(0).getOrders() == null){
			return "购物车已无数据，请重新添加";
		}
		Order order = orders.get(0);
		Set<OrderDetail> orderDetailSet = order.getOrders();
		Iterator<OrderDetail> it = orderDetailSet.iterator();
		String OrderDetails = orderDto.getOrderDetailId();
		String[] orderDetailIdArray = OrderDetails.split(",");
		List buyList = new ArrayList();
		int price = 0;
		
		
		//生成主表信息
    	Order newOrder = new Order();
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
    	Date date=new Date();
    	
        UUID uuid = UUID.randomUUID();
        String orderId = sdf.format(date)+uuid.toString().substring(0, 23);
    	newOrder.setOrderId(orderId);
    	if(null ==  orderDto.getBizNo() || "".equals(orderDto.getBizNo())){
    		return "请输入正确的业务流水号";
    	}
    	newOrder.setBizNo(orderDto.getBizNo());
    	newOrder.setOrderStatus(OrderEnumType.PAY_NO_SEAND);
    	newOrder.setEmail(currentUser.getPrincipal().toString());
    	newOrder.setCreateTime(new Date());
    	newOrder.setUpdateTime(new Date());
    	
    	//修改子表归属
		while(it.hasNext()){
			OrderDetail tempDetail = it.next();
			for(int i=0;i<orderDetailIdArray.length;i++){
				if(tempDetail.getId().equals(orderDetailIdArray[i])){
					tempDetail.setOrder(newOrder);
					buyList.add(tempDetail);
					price = price + tempDetail.getPrice();
					it.remove();
				}
			}
		}
		orderService.updateOrder(order);
		
		
    	//生成子表信息
    	Set<OrderDetail> newOrders = new HashSet<OrderDetail>();
    	newOrders.addAll(buyList);
    	newOrder.setPrice(price);
    	orderService.saveOrder(newOrder);
		return "购买成功";
		
	}
	
	/**
	 * 订单删除记录
	 * @return
	 */
	@RequestMapping(value="/removeOrder", method = {RequestMethod.GET })  
	public String RemoveOrder(HttpServletRequest request,  Model model){
		String orderId = request.getParameter("orderId");
		
		Subject currentUser = SecurityUtils.getSubject(); 
		if(currentUser.getPrincipal()==null){
			return "index";
		}
		String[] orderIdAarry = orderId.split(",");
		orderService.deleteOrder(orderIdAarry);
		
		return getOrderList(model);
		
	}
	
	
	/**
	 * 订单删除记录
	 * @return
	 */
	@RequestMapping(value="/orderDetail", method = {RequestMethod.GET })  
	public String orderDetail(HttpServletRequest request,  Model model){
		String orderId = request.getParameter("orderId");
		
		Subject currentUser = SecurityUtils.getSubject(); 
		if(currentUser.getPrincipal()==null){
			return "index";
		}
		Order order = orderService.getOrder(orderId);
		model.addAttribute("orderDetails", order.getOrders());
		model.addAttribute("isDetail","true");
		return getOrderList(model);
		
	}
	
	
}
