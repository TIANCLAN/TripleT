package cn.edu.scnu.user.mapper;

import java.util.List;

import com.easymall.common.pojo.User;

public interface UserMapper {

	Integer queryUsername(String userName);

	void userSave(User user);

	User queryUserByNameAndPassword(User user);
	
	User findByName(String name);
	
	public List<User> queryUser();

	public void deleteUser(User user);
}
