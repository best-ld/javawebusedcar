package mvc.service;

import java.sql.Connection;

import mvc.bean.User;
import mvc.dao.UserDao;
import mvc.daoImpl.UserDaoJdbcImpl;
import mvc.utils.JdbcTools;

public class UserSignupService {

	public UserSignupService() {
	}
	//��ѯ�û����Ƿ���ڣ�Ϊ�շ���true
	public boolean checkSignName(String user_name) {
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
		return flag;
	}
	//��ѯ���֤���Ƿ���ڣ�Ϊ�շ���true
	public boolean checkSignupIdcard(String idcard) {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		boolean flag = false;
		try {
			connection = JdbcTools.getConnection();
			User user = userdao.searchUserByIdcard(connection, idcard);
			if(user == null) {
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return flag;
	}
	//��ѯ�ظ�����������Ƿ�һ��
	public boolean check_Password(String password,String again_password)
	{
		return again_password.equals(password);
	}
	//���ѧ��
	public void add_User(String user_name,String password,String name,String idcard,String phone,String city) {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		User user = new User(null,user_name,password,name,idcard,phone,city,null);
		try {
			connection = JdbcTools.getConnection();
			userdao.addUser(connection, user);
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
}
