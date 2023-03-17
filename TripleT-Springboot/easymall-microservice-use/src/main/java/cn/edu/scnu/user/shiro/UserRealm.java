package cn.edu.scnu.user.shiro;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.easymall.common.pojo.User;

import cn.edu.scnu.user.service.UserService;


/**
 * �Զ���Realm
 * @author lenovo
 *
 */
public class UserRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	/**
	 * ִ����Ȩ�߼�
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("ִ����Ȩ�߼�");
		
		//����Դ������Ȩ
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//�����Դ����Ȩ�ַ���
//		info.addStringPermission("user:add");
		
		//��ȡ��ǰ�û�
        Subject subject=SecurityUtils.getSubject();
        User user=(User)subject.getPrincipal();
        User dbUser=userService.findByName(user.getUserName());//ͨ����ǰ��¼�û�id���ҵ����ݿ��û�
        
        //info.addStringPermission(dbUser.getPerms());������1
		return info;

	}



	/**
	 * ִ����֤�߼�
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("ִ����֤�߼�");
		
        // ��дshiro�ж��߼����ж��û���������
     		System.out.println(arg0.toString());
        //1. �ж��û���
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
        User user = userService.findByName(token.getUsername());
        System.out.println(user.getUserPassword());
        System.out.println("token"+token);
        if (user==null) {
            //�û���������shiro�ײ���׳�UnknownAccountException
            return null;
        }
        //2. �ж�����,����1����Ҫ���ظ�login���������ݣ�����2�����ݿ����룬shiro���Զ��ж�
        return new SimpleAuthenticationInfo(user,user.getUserPassword(),"");
        
	}

}
