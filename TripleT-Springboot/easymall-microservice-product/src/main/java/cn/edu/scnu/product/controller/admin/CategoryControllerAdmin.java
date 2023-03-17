package cn.edu.scnu.product.controller.admin;

import easymall.po.Category;
import cn.edu.scnu.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products/admin")
public class CategoryControllerAdmin {

	@Autowired
	private  CategoryService categoryService;

	private String productsUrl="redirect:http://www.triplet.com/products";

	@RequestMapping("/catelist")
	public String catelist(Model model){
		List<Category> categories = categoryService.allcategories();
		model.addAttribute("categories",categories);
		return "/admin/cate_list";
	}
	@RequestMapping("/addcate")
	public String addcate(Model model){
		
		model.addAttribute("category", new Category());
		return "/admin/add_category";
	}
	
	@RequestMapping("/savecate")
	public String save(@Valid @ModelAttribute Category category,
                       HttpServletRequest request, Model model)
			throws Exception {
		String msg=categoryService.savecate(category,request);
		System.out.println(msg+"0.0");
		model.addAttribute("msg",msg);
		return productsUrl+"/admin/addcate";
	}
	
	@RequestMapping("/showcate")
	public String showcate(String id, Model model){
		Category category=categoryService.oneCategory(id);
		category.setName(category.getName());
		category.setDescription(category.getDescription());
		model.addAttribute("id", id);
		model.addAttribute("category", category);
		return "/admin/show_cate";
	}
	
	@RequestMapping("/modifycate")
	public String modifycate(String id, @Valid @ModelAttribute Category category,
                             HttpServletRequest request, Model model)
			throws Exception {
		String msg=categoryService.updatecate(id,category,request);
		System.out.println(msg+"0.0");
		model.addAttribute("msg",msg);
		return productsUrl+"/admin/showcate?id="+id;
	}
	
	@RequestMapping("/delcate")
	public String delcate(String id, Model model){
		categoryService.delcate(id);
		return productsUrl+"/admin/catelist?id"+id;
	}
}
