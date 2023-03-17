package cn.edu.scnu.user.controller;

import cn.edu.scnu.user.service.UserService;
import com.easymall.common.pojo.User;
import com.easymall.common.utils.CookieUtils;
import com.easymall.common.vo.SysResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//import com.easymall.common.utils.PrefixKey;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/user/manage/checkUserName")
	public SysResult checkUsername(String userName){
		Integer exist=userService.checkUsername(userName);
		if(exist == 0){
			return SysResult.ok();
		}else{
			return SysResult.build(201, "ÒÑ´æÔÚ", null);
		}	
	}
	
	@RequestMapping("/user/manage/save")
	public SysResult userSave(User user){
		Integer a = userService.checkUsername(user.getUserName());
		if(a>0)
			return SysResult.build(201, "ÓÃ»§ÒÑ´æÔÚ", null);
		try{
			userService.userSave(user);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, e.getMessage(), null);
		}
	}
	@RequestMapping("/user/manage/login")
	public SysResult doLogin(User user,HttpServletRequest request,
			HttpServletResponse response){
		String ticket = userService.doLogin(user);
		if(StringUtils.isNotEmpty(ticket)){
			CookieUtils.setCookie(request, response, "EM_TICKET", ticket);
			return SysResult.ok();
		}
		else{
			return SysResult.build(201, "µÇÂ¼Ê§°Ü", null);
		}
		
	}
	@RequestMapping("/user/manage/AllUsers")
	 public List<User> queryUser(){
	  
	  return userService.queryUser();
	 }
	 
	 @RequestMapping("/user/manage/delete")
	 public SysResult deleteUser(User user){
	  try {
	   userService.deleteUser(user);
	   return SysResult.ok();
	  } catch (Exception e) {
	   e.printStackTrace();
	   return SysResult.build(201, "", null);
	  }
	 }
//	@RequestMapping("/user/manage/Login")
//	public String login() {
//		return "login";
//	}
	@RequestMapping("/user/manage/query/{ticket}")
	public SysResult checkLoginUser(@PathVariable String ticket){
		String userJson = userService.queryUserJson(ticket);
		if(StringUtils.isNotEmpty(userJson)){
			return SysResult.build(200, "ok", userJson);
		}else{
			return SysResult.build(201, "", null);
		}
	}
	@RequestMapping("/user/manage/logout/{ticket}")
	public SysResult userLogout(User user,@PathVariable String ticket,HttpServletRequest request,
			HttpServletResponse response){
		try{
			//CookieUtils.deleteCookie(request, response, PrefixKey.USER_LOGIN_TICKET);´íÎó±àºÅ2
			userService.userLogout(ticket,user);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.build(201, "µÇ³öÊ§°Ü", null);
		}
	}
	


}
