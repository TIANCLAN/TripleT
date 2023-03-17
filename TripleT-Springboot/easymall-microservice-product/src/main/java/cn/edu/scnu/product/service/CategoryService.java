package cn.edu.scnu.product.service;

import cn.edu.scnu.product.mapper.CategoryDao;
import easymall.po.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("categoryService")
public class CategoryService{
	@Autowired
	private CategoryDao categoryDao;
	
	
	public List<Category> allcategories() {
		return categoryDao.allcategories();
	}

	
	public String savecate(Category category, HttpServletRequest request) {
		Category category2 = new Category();
		category2.setId(category.getId());
		category2.setName(category.getName());
		category2.setDescription(category.getDescription());
		categoryDao.savecate(category2);
		return "商品种类添加成功";
	}
	
	
	public String updatecate(String id, Category category, HttpServletRequest request) {
		Category category2 = new Category();
		category2=oneCategory(id);
		category2.setName(category.getName());
		category2.setDescription(category.getDescription());
		categoryDao.update(category2);
		return "商品种类修改成功";
	}

	
	public Category oneCategory(String id) {

		return categoryDao.oneCategory(id);
	}

	
	public void delcate(String id) {
		
		
		categoryDao.delcate(id);
	}

	




}
