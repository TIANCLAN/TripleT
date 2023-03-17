package cn.edu.scnu.user.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * Shiro的配置类
 * @author lenovo
 *
 */
@Configuration
@MapperScan("cn.edu.scnu.mapper")
public class ShiroConfig {

	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//修改跳转的登录页面，不加此项就会跳转到login.jsp页面
		shiroFilterFactoryBean.setLoginUrl("login.html");
		//设置未授权提示页面
		shiroFilterFactoryBean.setUnauthorizedUrl("unAuth.html");
		//添加Shiro内置过滤器
		/**
		 * Shiro内置过滤器，可以实现权限相关的拦截器
		 *    常用的过滤器：
		 *       anon: 无需认证（登录）可以访问
		 *       authc: 必须认证才可以访问
		 *       user: 如果使用rememberMe的功能可以直接访问
		 *       perms： 该资源必须得到资源权限才可以访问
		 *       role: 该资源必须得到角色权限才可以访问
		 */
		Map<String,String> filterMap = new LinkedHashMap<String,String>();
//		filterMap.put("/TripleT-static/mycart.html", "authc");
//		filterMap.put("/cart/manage", "authc");

		//授权过滤器
		//注意：当前授权拦截后，shiro会自动跳转到未授权页面
		filterMap.put("login.html", "anon");
		filterMap.put("/user/manage/login", "anon");
		filterMap.put("/user/manage/query/**", "anon");
		
		filterMap.put("admin_login.html", "perms[user:manage]");
		filterMap.put("**.html", "authc");
		
//		filterMap.put("/login", "anon");
//		//授权过滤器：授权拦截后，shiro会自动跳转到未授权页面
//		filterMap.put("/add", "perms[user:add]");
//		filterMap.put("/*", "authc");
//		//修改调整的登录页面
//		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		System.out.println(shiroFilterFactoryBean.getLoginUrl());
//		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//		
//		
		return shiroFilterFactoryBean;
	}

	
	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//关联realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	/**
	 * 创建Realm
	 */
	@Bean(name="userRealm")
	public UserRealm getRealm(){
		return new UserRealm();
	}
	/**
	 * 配置ShiroDialect，用于thymeleaf和shiro标签配合使用
	 */
	@Bean
	public ShiroDialect getShiroDialect(){
		return new ShiroDialect();
	}

}

