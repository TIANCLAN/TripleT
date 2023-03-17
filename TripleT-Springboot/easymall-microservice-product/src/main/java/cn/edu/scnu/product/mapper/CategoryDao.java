package cn.edu.scnu.product.mapper;

import easymall.po.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryDao")
@Mapper
public interface CategoryDao {

	public List<Category> allcategories();

	public void savecate(Category category);

	public void update(Category category);

	public Category oneCategory(String id);

	public void delcate(String id);

}
