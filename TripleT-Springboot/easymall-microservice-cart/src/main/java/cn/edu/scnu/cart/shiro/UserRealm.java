//package cn.edu.scnu.shiro;
//
//import javax.annotation.Resource;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authc.UsernamePasswordToken;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.easymall.common.pojo.User;
//
//
//
///**
// * 自定义Realm
// * @author lenovo
// *
// */
//public class UserRealm extends AuthorizingRealm{
//
//	/**
//	 * 执行授权逻辑
//	 */
//	@Override
//	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//		System.out.println("执行授权逻辑");
//		
//		//给资源进行授权
//		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//		
//		//添加资源的授权字符串
//		info.addStringPermission("user:add");
//		
//		return info;
//
//	}
//
//	@Autowired
//	private UserService userSerivce;
//
//	/**
//	 * 执行认证逻辑
//	 */
//	@Override
//	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
//		System.out.println("执行认证逻辑");
//		
//        // 编写shiro判断逻辑，判断用户名和密码
//     		System.out.println(arg0.toString());
//        //1. 判断用户名
//        UsernamePasswordToken token = (UsernamePasswordToken) arg0;
//        User user = userSerivce.findByName(token.getUsername());
//        System.out.println(user.getUserPassword());
//        System.out.println("token"+token);
//        if (user==null) {
//            //用户名不存在shiro底层会抛出UnknownAccountException
//            return null;
//        }
//        //2. 判断密码,参数1：需要返回给login方法的数据；参数2：数据库密码，shiro会自动判断
//        return new SimpleAuthenticationInfo("", user.getUserPassword(), "");
//	}
//
//}
