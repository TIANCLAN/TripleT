package easymall.po;

import java.util.Map;

public class OrderInfo {
	private Orders order;//订单信息
	private Map<Products, Integer> map;//该订单中的所有订单项信息
	private User user;
	public OrderInfo() {
		
	}
	public OrderInfo(Orders order, Map<Products, Integer> map, User user) {
		super();
		this.order = order;
		this.map = map;
		this.user = user;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Map<Products, Integer> getMap() {
		return map;
	}
	public void setMap(Map<Products, Integer> map) {
		this.map = map;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
