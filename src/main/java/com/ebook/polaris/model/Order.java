package com.ebook.polaris.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.ebook.polaris.model.Order.OrderEnumType;  


@Entity  
@Table(name = "order", catalog = "polaris")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class Order {
  
	private String orderId;  
    private String email;
    private String bizNo;
    private OrderEnumType orderStatus;  
    private Integer price;  
    private Date createTime;  
    private Date updateTime;
    
	
	@Id  
    @Column(name = "order_id", unique = true, nullable = false, length = 32)  
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Column(name = "email", nullable = false, length = 32)  
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Enumerated(EnumType.STRING)	
	@Column(name="order_status")
	public OrderEnumType getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(OrderEnumType orderStatus) {
		this.orderStatus = orderStatus;
	}
		
	@Column(name ="price")	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "create_time", length = 19)  
	public Date getCreateTime() {
		return createTime;
	}
	

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Temporal(TemporalType.TIMESTAMP)  
    @Column(name = "update_time", length = 19)  
	public Date getUpdateTime() {
		return updateTime;
	}
	
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
 	@Column(name = "biz_no")  
	public String getBizNo() {
		return bizNo;
	}
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}  

	private Set<OrderDetail> orders = new HashSet<OrderDetail>();
	@OneToMany()
	@JoinColumn(name = "order_id")  
    @Cascade(CascadeType.SAVE_UPDATE)
	public Set<OrderDetail> getOrders() {
		return orders;
	}
	public void setOrders(Set<OrderDetail> orders) {
		this.orders = orders;
	}
	
	/* 
     * 添加订单 
     */  
    public void addOrderItem(OrderDetail orderitem) {  
        if (!this.orders.contains(orderitem)) {  
            this.orders.add(orderitem);  
            orderitem.setOrder(this);  
        }  
    }  
  
    /* 
     * 删除订单 
     */  
    public void removeOrderItem(OrderDetail orderitem) {  
        orderitem.setOrder(null);  
        this.orders.remove(orderitem);  
    }  
    
    
    public enum OrderEnumType {
    	SHOPPING_CART,
    	PAY_NO_SEAND,
    	PAY_AND_SEAND,
    	PAY_NOT_ENOUGH;
    	
    	public static OrderEnumType[] getCartEnum(){
   		 OrderEnumType[] orderStatus =new OrderEnumType[]{OrderEnumType.SHOPPING_CART};
   		 return orderStatus;
   		 
    	}
    	
    	public static OrderEnumType[] getOrderEnum(){
    		 OrderEnumType[] orderStatus =new OrderEnumType[]{OrderEnumType.PAY_NO_SEAND,OrderEnumType.PAY_AND_SEAND,OrderEnumType.PAY_NOT_ENOUGH};
    		 return orderStatus;
    		}
    	}
    
    
   
    
   
}
