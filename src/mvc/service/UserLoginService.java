package mvc.service;

import java.sql.Connection;

import mvc.bean.User;
import mvc.dao.UserDao;
import mvc.daoImpl.UserDaoJdbcImpl;
import mvc.utils.JdbcTools;



public class UserLoginService {

	public UserLoginService() {
		// TODO Auto-generated constructor stub
	}
	
	//若用户名存在，即不为null,返回flase，可以登录
	public boolean checkLoginName(String user_name) {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		boolean flag = false;
		try {
			connection = JdbcTools.getConnection();
			User user = userdao.searchUserByName(connection, user_name);
			if(user == null) {
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		//System.out.println(flag);
		return flag;
	}
	
	public boolean checkLoginNamePassword(String user_name,String password) {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		boolean flag = false;
		try {
			connection = JdbcTools.getConnection();
			User user = userdao.searchUserByNameandPassword(connection, user_name, password);
			if(user == null) {
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		//System.out.println(flag);
		return flag;
	}
	
	//按密码和用户名查找学生
	public User searchUserByNamePassword(String user_name,String password) {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		User user= new User();
		try {
			connection = JdbcTools.getConnection();
			user = userdao.searchUserByNameandPassword(connection, user_name, password);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return user;
	}
	
	//

}
