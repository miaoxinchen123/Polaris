package com.ebook.polaris.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;  


@Entity  
@Table(name = "order_detail", catalog = "polaris")  
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)  
public class OrderDetail {
  
	private String Id;  
	private String md5; 
	private String coverUrl;
	private String title;
	private Integer size;  
    private Integer price; 
    private String authors;
	private Date createTime;  
    private Date updateTime;
    
	
    @Id @GeneratedValue(generator="system-uuid")  
    @GenericGenerator(name="system-uuid", strategy = "uuid")  
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	
	@Column(name ="price")	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Column(name ="md5")	
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	@Column(name ="authors")	
	public String getAuthors() {
		return authors;
	}
	public void setAuthors(String authors) {
		this.authors = authors;
	}

	@Column(name ="cover_url")	
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	
	@Column(name ="title")	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name ="size")	
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
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
	

	private Order order;
	@ManyToOne()
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="order_id")
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
    
}
