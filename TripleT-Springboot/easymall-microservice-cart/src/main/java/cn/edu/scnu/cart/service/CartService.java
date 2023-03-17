package cn.edu.scnu.cart.service;

import java.util.List;

import easymall.po.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.easymall.common.pojo.Cart;
import com.easymall.common.pojo.Product;

import cn.edu.scnu.cart.mapper.CartMapper;

@Service
public class CartService {
	@Autowired
	private CartMapper cartMapper;
	@Autowired
	private RestTemplate restTemplate;
	public List<Cart> queryMyCart(String userId) {
		return cartMapper.queryMyCart(userId);
	}

	public void cartSave(Cart cart) {
		Cart exist = cartMapper.queryOne(cart);
		if(exist!=null){
			exist.setNum(exist.getNum()+cart.getNum());
			cartMapper.updateNum(exist);
		}else{
			Products product = restTemplate
					.getForObject("http://productservice/products/queryOne?pid="
							+cart.getProductId(),Products.class);
			cart.setProductPrice(product.getPrice());
			cart.setProductName(product.getName());
			cart.setProductImage(product.getImgurl());
			cartMapper.saveCart(cart);
		}

	}

	public void updateNum(Cart cart) {
		cartMapper.updateNum(cart);
	}

	public void deleteCart(Cart cart) {
		cartMapper.deleteCart(cart);
	}
}
