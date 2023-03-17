package cn.edu.scnu.product.controller;

import cn.edu.scnu.product.service.ProductsService;
import com.easymall.common.pojo.Product;
import com.easymall.common.vo.SysResult;
import easymall.po.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("productsController")
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	private ProductsService productsService;

	private String productsUrl="redirect:http://www.triplet.com/products";

	@RequestMapping("/prodlist")
	public String prodlist(String name, String category,
                           String minprice, String maxprice, Model model){
		//查找商品表中所有的商品类别
		List<String> categories = productsService.allcategories();
		model.addAttribute("categories",categories);
		
		//为搜索条件设置默认值，并检索条件是否合法
		double _minPrice=0; //默认从0到最大值
		double _maxPrice = Double.MAX_VALUE;
		
		String reg = "^\\d+$"; //只能输入数字
		if(minprice!=null && !"".equals(minprice.trim()) && minprice.matches(reg)){
			_minPrice = Double.parseDouble(minprice);
		}
		if(maxprice!=null && !"".equals(maxprice.trim()) && maxprice.matches(reg)){
			//最高价格如果大于等于最低价格
			if(Double.parseDouble(maxprice) >= _minPrice){
				_maxPrice = Double.parseDouble(maxprice);
			}
		}
		//创建map,用于存放查询条件
		Map<String,Object> map=new HashMap<>();
		map.put("name", name);
		map.put("category",category);
		map.put("minPrice",_minPrice);
		map.put("maxPrice",_maxPrice);
		//根据条件查询符合条件的商品信息
		List<Products> products = productsService.prodlist(map);
		model.addAttribute("name",name);
		model.addAttribute("minPrice",_minPrice);
		model.addAttribute("maxPrice",_maxPrice);
		//查询结果暴露给前端
		model.addAttribute("products",products);
		model.addAttribute("prod_length",products.size());
		//qwe账号
		model.addAttribute("userId","6dd8674f-9ef4-4ed2-90aa-66f66bd59a2a");
		return "prod_list";
	}
	@RequestMapping("/prodInfo")
	public String prodInfo(String pid, Model model){
		Products product = productsService.oneProduct(pid);
		model.addAttribute("product",product);
		//返回到WEB-INF
		return "prod_info";
	}

	@RequestMapping("/queryOne")
	@ResponseBody
	public Products queryOne(String pid){
		return productsService.oneProduct(pid);
	}

	@RequestMapping("/ranklist")
	 public String ranklist(Model model){
	  List<Products> products = productsService.ranklist();
	  model.addAttribute("products",products);
	  //返回到WEB-INF
	  return "rank_list";
	 }

	@RequestMapping(value="/prodclass/{prodclass}")
	public String prodclass(@PathVariable String prodclass, Model model){
		List<Products> products=productsService.proclass(prodclass);
		model.addAttribute("products",products);
		//返回到WEB-INF
		return "prod_list";
	}

	@RequestMapping("/favorite")
	 public String favorite(String id, Model model){
	  
	  Products product=productsService.oneProduct(id);
	  productsService.favorite(product);
	  
	  return productsUrl+"/prodInfo?pid="+id;
	  //返回到WEB-INF
	  
	 }
}
