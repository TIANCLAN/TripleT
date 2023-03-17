package cn.edu.scnu.product.controller.admin;

//import cn.edu.scnu.product.controller.BaseController;

import cn.edu.scnu.product.service.PicService;
import com.easymall.common.vo.PicUploadResult;
import easymall.po.Products;
import easymall.pojo.MyProducts;
import cn.edu.scnu.product.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/products/admin")
public class ProductsControllerAdmin {
    //extends BaseController
    @Autowired
    private ProductsService productsService;

    @Autowired
    private PicService picService;

    private String productsUrl="redirect:http://www.triplet.com/products";

    @RequestMapping("/prodlist")
    public String prodlist(Model model) {
//		//为搜索条件设置默认值，并检索条件是否合法
//		double _minPrice=0;//默认从0到最大值
//		double _maxPrice=Double.MAX_VALUE;
//		//创建map,用于存放查询条件
//		Map<String,Object> map = new HashMap<>();
//		map.put("name", "");
//		map.put("category", "");
//		map.put("minPrice", _minPrice);
//		map.put("maxPrice", _maxPrice);
        //根据条件查询符合条件的商品信息
        List<Products> products = productsService.allprods();
        //System.out.println(products.size());

        //查询结果暴露给前端
        model.addAttribute("products", products);
        return "/admin/prod_list";
    }

    @RequestMapping("/addprod")
    public String addprod(Model model) {
        List<String> categories = productsService.allcategories();
        model.addAttribute("categories", categories);
        model.addAttribute("myproducts", new MyProducts());
        return "/admin/add_prod";
    }

    @RequestMapping("/updateprod")
    public String updateprod(String id, Model model) {
        Products product = productsService.oneProduct(id);
        MyProducts myProducts = new MyProducts();
        myProducts.setName(product.getName());
        myProducts.setPrice(product.getPrice());
        myProducts.setPnum(product.getPnum());
        myProducts.setDescription(product.getDescription());
        myProducts.setCategory(product.getCategory());
        myProducts.setCategory(product.getImgurl());
        List<String> categories = productsService.allcategories();
        model.addAttribute("id", id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        model.addAttribute("myproducts", myProducts);
        return "/admin/update_prod";
    }

    //@RequestMapping("/save")
    //public String save(@Valid @ModelAttribute MyProducts myproducts,
    //                   HttpServletRequest request, Model model, BindingResult result)
    //		throws Exception {
    //	if(result.hasErrors()){
    //		List<ObjectError> ls = result.getAllErrors();
    //		for (int i = 0; i < ls.size(); i++) {
    //			System.out.println("error:"+ls.get(i));
    //		}
    //	}
    //	String msg=productsService.save(myproducts,request);
    //	model.addAttribute("msg",msg);
    //	return "redirect:/admin/addprod";
    //}

    @RequestMapping("/save")
    public String save(MyProducts myproducts,
                       HttpServletRequest request, Model model, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            for (int i = 0; i < ls.size(); i++) {
                System.out.println("error:" + ls.get(i));
            }
        }
        //添加图片上传单独操作
        PicUploadResult resultmap = picService.picUpload(myproducts.getImgurl());
        String imgurl = "";
        if (resultmap.getError() == 1) {
            System.out.println("上传图片出错");
        } else {
            imgurl = resultmap.getUrl();
            String msg = productsService.save(myproducts, imgurl, request);
            model.addAttribute("msg", msg);
        }
        return productsUrl+"/admin/addprod";
    }

    @RequestMapping("/update")
    public String update(String id, MyProducts myproducts,
                         HttpServletRequest request, Model model)
            throws Exception {
        //添加图片上传单独操作
        PicUploadResult result = picService.picUpload(myproducts.getImgurl());
        String imgurl = "";
        if (result.getError() == 1) {
            System.out.println("上传图片出错");
        } else {
            imgurl = result.getUrl();
            String msg = productsService.update(id, myproducts, imgurl, request);
            model.addAttribute("msg", msg);
        }
        return productsUrl+"/admin/updateprod?id=" + id;
    }

    @RequestMapping("/OnSale")
    public String OnSale(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("status", 1);
        productsService.updateSaleStatus(map);
        return productsUrl+"/admin/prodlist";
    }

    @RequestMapping("/OffSale")
    public String OffSale(String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("status", 0);
        productsService.updateSaleStatus(map);
        return productsUrl+"/admin/prodlist";
    }

    @RequestMapping("/delprod")
    public String delcate(String id, Model model) {
        productsService.delprod(id);
        return productsUrl+"/admin/prodlist";
    }
}	
