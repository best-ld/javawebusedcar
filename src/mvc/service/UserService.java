package mvc.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import mvc.bean.Car;
import mvc.bean.User;
import mvc.dao.CarDao;
import mvc.dao.UserDao;
import mvc.daoImpl.CarDaoImpl;
import mvc.daoImpl.UserDaoJdbcImpl;
import mvc.utils.JdbcTools;

public class UserService {
	
	public UserService() {
		super();
		this.userdao =  new UserDaoJdbcImpl();
		this.cardao = new CarDaoImpl();
		this.connection = null;
	}
	
	private UserDao userdao;
	private Connection connection;
	private CarDao cardao;
	
	public void update_User(Integer user_id, String user_name, String password, String phone, String city) {
		User user = new User(user_id, user_name,password, null, phone, city);
		try {
			connection = JdbcTools.getConnection();
			userdao.updateUser(connection, user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	public User search_UserById(Integer user_id) {
		User user = new User();
		try {
			connection = JdbcTools.getConnection();
			user = userdao.searchUserById(connection, user_id);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return user;
	}
	
	public List<Car> search_AllCar(){
		List<Car> cars = new ArrayList<Car>();
		try {
			connection = JdbcTools.getConnection();
			cars = cardao.searchAllCar(connection);
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return cars;
	}
	
	public List<User> search_AllUser() {
		List<User> users = new ArrayList<User>();
		try {
			connection = JdbcTools.getConnection();
			users = userdao.fetchAllUsers(connection);
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return users;
	}
	
	public void updateUser(User user) {
		try {
			connection = JdbcTools.getConnection();
			userdao.updatePermission(connection, user);
		}
		catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
}
