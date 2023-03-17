package cn.edu.scnu.echarts.controller;

import cn.edu.scnu.echarts.service.EchartsService;
import com.easymall.common.pojo.Order;
import easymall.po.Category;
import easymall.po.Orders;
import easymall.po.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("echartsController")
@RequestMapping("/echarts/admin")
public class EchartsController {
	@Autowired
	private EchartsService echartsService;
	@RequestMapping("/showEcharts")
	public String showEcharts(Model model){
		List<Order> ordersbytime = echartsService.showOrderByTime();
		model.addAttribute("ordersbytime", ordersbytime);
		List<Products> productbysalenum = echartsService.showProductBySaleNum();
		model.addAttribute("productbysalenum", productbysalenum);
		List<Category> category = echartsService.showCategory();
		model.addAttribute("category", category);
		return "/showEcharts";
	}
	
}
