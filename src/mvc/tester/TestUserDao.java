package mvc.tester;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import mvc.bean.SellPremission;
import mvc.bean.User;
import mvc.dao.PremissionDao;
import mvc.dao.UserDao;
import mvc.daoImpl.PremissionDaoImpl;
import mvc.daoImpl.UserDaoJdbcImpl;
import mvc.service.AdmirSevice;
import mvc.service.UserLoginService;
import mvc.service.UserService;
import mvc.service.UserSignupService;
import mvc.utils.JdbcTools;

class TestUserDao {

	@Test
	public void testGetAll() {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			List<User> users = userdao.fetchAllUsers(connection);
			System.out.println(users);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	@Test
	public void testGetN() {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		String user_name = "ld";
		try {
			connection = JdbcTools.getConnection();
			User user = userdao.searchUserByName(connection, user_name);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	@Test
	public void testGetId() {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		Integer user_id = 23;
		try {
			connection = JdbcTools.getConnection();
			User user = userdao.searchUserById(connection, user_id);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	@Test
	public void testGetNP() {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		String user_name = "ld";
		String password = "23356";
		try {
			connection = JdbcTools.getConnection();
			User user = userdao.searchUserByNameandPassword(connection, user_name, password);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	@Test
	public void testUpdateUser() {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		User user = new User(19, "ld", "666", "lindun", "8721398", "¶«Ý¸");
		try {
			connection = JdbcTools.getConnection();
			userdao.updateUser(connection, user);
			User user1 = userdao.searchUserByName(connection, "ld");
			System.out.println(user1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	@Test
	public void testAddUser() {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		User user = new User(null, "cl", "7777", "chenlin", "445122178646", "875469", "¹ãÖÝ", null);
		try {
			connection = JdbcTools.getConnection();
			userdao.addUser(connection, user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	@Test
	public void testDeleteUser() {
		int id = 21;
		Connection connection = null;
		UserDao userdao = new UserDaoJdbcImpl();
		try {
			connection = JdbcTools.getConnection();
			userdao.deleteUser(connection, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	@Test
	public void testServiceUserN() {
		UserLoginService service = new UserLoginService();
		String user_name = "ldd";
		if (service.checkLoginName(user_name)) {
			System.out.println("True");
		}
	}

	@Test
	public void testServiceUserNP() {
		UserLoginService service = new UserLoginService();
		String user_name = "ld";
		String password = "2335";
		if (service.checkLoginNamePassword(user_name, password)) {
			System.out.println("True");
		}
	}

	@Test
	public void testServiceUserI() {
		UserSignupService service = new UserSignupService();
		String idcard = "445122130256";
		if (service.checkSignupIdcard(idcard)) {
			System.out.println("True");
		}
	}

	@Test
	public void testServiceUseradd() {
		UserSignupService service = new UserSignupService();
		String user_name = "jack";
		String password = "2966";
		String name = "jckasd";
		String idcard = "44512219875";
		String phone = "8569742";
		String city = "Îäºº";
		service.add_User(user_name, password, name, idcard, phone, city);
	}

	@Test
	public void testGetI() {
		UserDao userdao = new UserDaoJdbcImpl();
		Connection connection = null;
		String idcard = "445122130256";
		try {
			connection = JdbcTools.getConnection();
			User user = userdao.searchUserByIdcard(connection, idcard);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	@Test
	public void testServiceUserU() {
		UserService service = new UserService();
		service.update_User(19, "ldd", "6666", "1300056", "dg");
	}
	
	@Test
	public void testServiceUserSNP() {
		UserLoginService service = new UserLoginService();
		String user_name = "ld";
		String password = "2335";
		User user =  service.searchUserByNamePassword(user_name, password);
		System.out.println(user);
	}
	
	@Test
	public void testServiceSI() {
		UserService service = new UserService();
		User user=service.search_UserById(23);
		System.out.println(user);
	}
	
	@Test
	public void testGetSP() {
		PremissionDao premissionDao = new PremissionDaoImpl();
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			List<SellPremission> sellPremissions = premissionDao.searchAllPre(connection);
			System.out.println(sellPremissions);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		AdmirSevice admirSevice = new AdmirSevice();
		System.out.println(admirSevice.getAllSellMessage());
	}
}
