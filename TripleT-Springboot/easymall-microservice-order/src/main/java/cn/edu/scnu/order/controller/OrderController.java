package cn.edu.scnu.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easymall.common.pojo.Order;
import com.easymall.common.vo.SysResult;

import cn.edu.scnu.order.service.OrderService;

@RestController
@RequestMapping("/order/manage")
public class OrderController {
	@Autowired
	private OrderService orderService;
	//新增订单
	@RequestMapping("save")
	public SysResult saveOrder(Order order){
		try {
			orderService.saveOrder(order);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	//清除购物车
	@RequestMapping("clean/{userId}")
	public SysResult cleanCart(@PathVariable String userId){
		try {
			orderService.cleanCart(userId);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "", null);
		}
	}
	//显示订单
	@RequestMapping("query/{userId}")
	public List<Order> queryMyOrder(@PathVariable String userId){
		return orderService.queryMyOrder(userId);
	}
	//删除订单
	@RequestMapping("delete/{orderId}")
	public SysResult deleteOrder(@PathVariable String orderId){
		try {
			orderService.deleteOrder(orderId);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "", null);
		}
	}
	//支付订单
	@RequestMapping("pay/{orderId}")
	public SysResult payOrder(@PathVariable String orderId){
		try {
			orderService.payOrder(orderId);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "", null);
		}
	}
	//确认收货
	@RequestMapping("receive/{orderId}")
	public SysResult receiveOrder(@PathVariable String orderId){
		try {
			orderService.receiveOrder(orderId);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "", null);
		}
	}
	//后台查看订单
	@RequestMapping("queryall")
	public List<Order> queryallOrder(){
		return orderService.queryallOrder();
	}
	//后台发货
	@RequestMapping("deliver/{orderId}")
	public SysResult deliverOrder(@PathVariable String orderId){
		try {
			orderService.deliverOrder(orderId);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "", null);
		}
	}
}
