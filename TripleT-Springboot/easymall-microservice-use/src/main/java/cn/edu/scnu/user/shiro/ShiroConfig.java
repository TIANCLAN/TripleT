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
 * Shiro��������
 * @author lenovo
 *
 */
@Configuration
@MapperScan("cn.edu.scnu.mapper")
public class ShiroConfig {

	/**
	 * ����ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		
		//���ð�ȫ������
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//�޸���ת�ĵ�¼ҳ�棬���Ӵ���ͻ���ת��login.jspҳ��
		shiroFilterFactoryBean.setLoginUrl("login.html");
		//����δ��Ȩ��ʾҳ��
		shiroFilterFactoryBean.setUnauthorizedUrl("unAuth.html");
		//���Shiro���ù�����
		/**
		 * Shiro���ù�����������ʵ��Ȩ����ص�������
		 *    ���õĹ�������
		 *       anon: ������֤����¼�����Է���
		 *       authc: ������֤�ſ��Է���
		 *       user: ���ʹ��rememberMe�Ĺ��ܿ���ֱ�ӷ���
		 *       perms�� ����Դ����õ���ԴȨ�޲ſ��Է���
		 *       role: ����Դ����õ���ɫȨ�޲ſ��Է���
		 */
		Map<String,String> filterMap = new LinkedHashMap<String,String>();
//		filterMap.put("/TripleT-static/mycart.html", "authc");
//		filterMap.put("/cart/manage", "authc");

		//��Ȩ������
		//ע�⣺��ǰ��Ȩ���غ�shiro���Զ���ת��δ��Ȩҳ��
		filterMap.put("login.html", "anon");
		filterMap.put("/user/manage/login", "anon");
		filterMap.put("/user/manage/query/**", "anon");
		
		filterMap.put("admin_login.html", "perms[user:manage]");
		filterMap.put("**.html", "authc");
		
//		filterMap.put("/login", "anon");
//		//��Ȩ����������Ȩ���غ�shiro���Զ���ת��δ��Ȩҳ��
//		filterMap.put("/add", "perms[user:add]");
//		filterMap.put("/*", "authc");
//		//�޸ĵ����ĵ�¼ҳ��
//		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		System.out.println(shiroFilterFactoryBean.getLoginUrl());
//		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
//		
//		
		return shiroFilterFactoryBean;
	}

	
	/**
	 * ����DefaultWebSecurityManager
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//����realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	/**
	 * ����Realm
	 */
	@Bean(name="userRealm")
	public UserRealm getRealm(){
		return new UserRealm();
	}
	/**
	 * ����ShiroDialect������thymeleaf��shiro��ǩ���ʹ��
	 */
	@Bean
	public ShiroDialect getShiroDialect(){
		return new ShiroDialect();
	}

}

