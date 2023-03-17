package cn.edu.scnu.product.mapper;

import easymall.po.Products;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ProductsDao {
	//查找商品类别
	public List<String> allcategories();
	//查找商品
	public List<Products> prodlist(Map<String, Object> map);
	//根据pid查找单个商品
	public Products oneProduct(String pid);
	//根据分类查找商品
	public List<Products> proclass(String category);

	public Object findByImgurl(String imgurl);

	public void save(Products products);

	public List<Products> allprods();

	public void updateSaleStatus(Map<String, Object> map);

	public void updateSoldNum(Map<String, Object> map);
	public void update(Products products);
	public void delprod(String id);
	public List<Products> ranklist();
	public void favorite(Products product);
}
