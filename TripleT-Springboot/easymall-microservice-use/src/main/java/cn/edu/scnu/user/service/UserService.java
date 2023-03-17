package cn.edu.scnu.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easymall.common.pojo.User;
import com.easymall.common.utils.MD5Util;
import com.easymall.common.utils.MapperUtil;
import com.easymall.common.utils.PrefixKey;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.edu.scnu.user.mapper.UserMapper;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import org.apache.commons.lang.StringUtils;


@Service
public class UserService {
	@Autowired
	private JedisCluster jedis;
	@Autowired
	private UserMapper userMapper;
//	@Autowired
//	private ShardedJedisPool pool;
	private ObjectMapper mapper = MapperUtil.MP;
	public Integer checkUsername(String userName) {
		// TODO Auto-generated method stub
		return userMapper.queryUsername(userName);
	}
	public void userSave(User user) {
		// TODO Auto-generated method stub
		user.setUserId(UUID.randomUUID().toString());
		user.setUserPassword(MD5Util.md5(user.getUserPassword()));
		userMapper.userSave(user);
	}
	public String doLogin(User user) {
		//ShardedJedis jedis = pool.getResource();
		try{
			user.setUserPassword(MD5Util.md5(user.getUserPassword()));
			User exist = userMapper.queryUserByNameAndPassword(user);
			
			if(exist == null){
				return "";
			}else{
				String ticket = PrefixKey.USER_LOGINED_CHECK_PREFIX+exist.getUserId();
				System.out.println(user.getUserId());
				//String ticket = UUID.randomUUID().toString();
				String userJson;
				userJson = mapper.writeValueAsString(exist);
				//System.out.println(mapper.writeValueAsString(user));
				System.out.println(ticket);
				//----------登陆顶替---------
				//判断当前用户是否曾经有人登录过
				String existTicket=jedis.get(PrefixKey.USER_LOGINED_CHECK_PREFIX+exist.getUserId());
				System.out.println("111"+existTicket);
				//顶替实现.不允许前一个登录的人ticket存在
				if(StringUtils.isNotEmpty(existTicket)){
					jedis.del(existTicket);
				}
				//定义当前客户端登录的信息 userId:ticket
				jedis.setex(PrefixKey.USER_LOGINED_CHECK_PREFIX+exist.getUserId(), 60*30,ticket);
				
				
				//---------一账户三个用户登录
//				List<String> existTicketList=jedis.lrange(PrefixKey.USER_LOGINED_CHECK_PREFIX+exist.getUserId(), 0, -1);
//				if(existTicketList.size()>=2){
//					jedis.del(jedis.rpop(PrefixKey.USER_LOGINED_CHECK_PREFIX+exist.getUserId()));
//				}
//				jedis.lpush(PrefixKey.USER_LOGINED_CHECK_PREFIX+exist.getUserId(),ticket);

				
				jedis.setex(ticket,60*30, userJson);
				return ticket;
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	public String queryUserJson(String ticket) {
		//ShardedJedis jedis = pool.getResource();
		String userJson = "";
		try{
			//判断超时剩余时间
			Long leftTime = jedis.pttl(ticket);
			//少于10分钟，延长5分钟
			if(leftTime<1000*60*10l){
				jedis.pexpire(ticket, leftTime+1000*60*5);
			}
			userJson = jedis.get(ticket);
			return userJson;
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	public User findByName(String name) {
		return userMapper.findByName(name);
	}
	public List<User> queryUser() {
		return userMapper.queryUser();
	 }
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}
	
	public void userLogout(String ticket,User user) {
		// TODO Auto-generated method stub
		//ShardedJedis jedis = pool.getResource();
		System.out.println("33"+user);
		User exist = userMapper.queryUserByNameAndPassword(user);
		System.out.println("333"+exist);
		System.out.println("123"+ticket);
		jedis.del(ticket);
		//jedis.del(PrefixKey.USER_LOGINED_CHECK_PREFIX+user.getUserId());
	}
}
