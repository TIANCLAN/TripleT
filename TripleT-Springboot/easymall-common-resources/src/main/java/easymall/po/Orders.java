package easymall.po;

import java.sql.Timestamp;

public class Orders {
	private String id;
	private Double money;
	private String receiverinfo;
	private Integer paystate;
	private Timestamp ordertime;
	private Integer user_id;
	private Integer deliverstate;
	private Integer receivestate;
	public Orders() {}

	

	public Orders(String id, Double money, String receiverinfo, Integer paystate, Timestamp ordertime, Integer user_id,
			Integer deliverstate, Integer receivestate) {
		super();
		this.id = id;
		this.money = money;
		this.receiverinfo = receiverinfo;
		this.paystate = paystate;
		this.ordertime = ordertime;
		this.user_id = user_id;
		this.deliverstate = deliverstate;
		this.receivestate = receivestate;
	}


	

	public Integer getReceivestate() {
		return receivestate;
	}



	public void setReceivestate(Integer receivestate) {
		this.receivestate = receivestate;
	}



	public Integer getDeliverstate() {
		return deliverstate;
	}



	public void setDeliverstate(Integer deliverstate) {
		this.deliverstate = deliverstate;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getReceiverinfo() {
		return receiverinfo;
	}

	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}

	public Integer getPaystate() {
		return paystate;
	}

	public void setPaystate(Integer paystate) {
		this.paystate = paystate;
	}

	public Timestamp getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	
}
