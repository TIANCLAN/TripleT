package cn.edu.scnu.echarts.mapper;

import com.easymall.common.pojo.Order;
import easymall.po.Category;
import easymall.po.Orders;
import easymall.po.Products;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EchartsDao {
	//获取订单，以日期排序
	public List<Order> showOrderByTime();
	//获取商品，以销量排序
	public List<Products> showProductBySaleNum();
	//获取商品类别
	public List<Category> showCategory();
}
