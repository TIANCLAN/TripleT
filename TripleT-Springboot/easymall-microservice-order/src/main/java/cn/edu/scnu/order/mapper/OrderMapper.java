package cn.edu.scnu.order.mapper;

import java.util.List;

import com.easymall.common.pojo.Order;

public interface OrderMapper {

	void saveOrder(Order order);

	List<Order> queryOrder(String userId);

	void deleteOrder(String orderId);

	void payOrder(String orderId);

	void receiveOrder(String orderId);

	List<Order> queryallOrder();

	void cleanCart(String userId);

	void deliverOrder(String orderId);
	
}
