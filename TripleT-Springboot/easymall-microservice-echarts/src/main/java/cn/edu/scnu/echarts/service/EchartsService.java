package cn.edu.scnu.echarts.service;

import cn.edu.scnu.echarts.mapper.EchartsDao;
import com.easymall.common.pojo.Order;
import easymall.po.Category;
import easymall.po.Orders;
import easymall.po.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("echartsService")
public class EchartsService {

	@Autowired
	private EchartsDao echartsDao;
	
	public List<Order> showOrderByTime() {
		return echartsDao.showOrderByTime();
	}
	
	public List<Products> showProductBySaleNum() {
		// TODO Auto-generated method stub
		return echartsDao.showProductBySaleNum();
	}
	
	public List<Category> showCategory() {
		// TODO Auto-generated method stub
		return echartsDao.showCategory();
	}

}
