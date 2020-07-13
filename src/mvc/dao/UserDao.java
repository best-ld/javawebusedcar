package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.User;

public interface UserDao {
	public void addUser(Connection connection, User user)//添加新会员
			throws SQLException;
	public User searchUserById(Connection connection,Integer user_id)//按用户id查找会员
			throws SQLException;
	public User searchUserByName(Connection connection,String user_name)//按用户姓名查找会员
			throws SQLException;
	public User searchUserByIdcard(Connection connection,String idcard)//按用户身份证号查找会员
			throws SQLException;
	public User searchUserByNameandPassword(Connection connection,String user_name,String password)//按姓名、密码查找会员
			throws SQLException; 
	public List<User> fetchAllUsers(Connection connection)//列出所有会员
			throws SQLException;
	public void updateUser(Connection connection,User user)//修改会员信息
			throws SQLException;
	public void deleteUser(Connection connection,int user_id)//按会员号删除会员
			throws SQLException;
	public void updatePermission(Connection connection, User user) 
			throws SQLException;
}
