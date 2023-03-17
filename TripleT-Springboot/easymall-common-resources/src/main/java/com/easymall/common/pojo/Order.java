package com.easymall.common.pojo;

import java.util.Date;
import java.util.List;

public class Order {
	private String orderId;
	private Double orderMoney;
	private String orderReceiverinfo;
	private Integer orderPaystate;
	private Integer orderReceivestate;
	private Integer orderDeliverstate;
	private Date orderTime;
	private String userId;
	//对多的关联表格的相关属性
	private List<OrderItem> orderItems;

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Double getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(Double orderMoney) {
		this.orderMoney = orderMoney;
	}
	public String getOrderReceiverinfo() {
		return orderReceiverinfo;
	}
	public void setOrderReceiverinfo(String orderReceiverinfo) {
		this.orderReceiverinfo = orderReceiverinfo;
	}
	public Integer getOrderPaystate() {
		return orderPaystate;
	}
	public void setOrderPaystate(Integer orderPaystate) {
		this.orderPaystate = orderPaystate;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getOrderReceivestate() {
		return orderReceivestate;
	}
	public void setOrderReceivestate(Integer orderReceivestate) {
		this.orderReceivestate = orderReceivestate;
	}
	public Integer getOrderDeliverstate() {
		return orderDeliverstate;
	}
	public void setOrderDeliverstate(Integer orderDeliverstate) {
		this.orderDeliverstate = orderDeliverstate;
	}

}
