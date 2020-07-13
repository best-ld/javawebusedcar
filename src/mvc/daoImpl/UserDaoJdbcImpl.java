package mvc.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.User;
import mvc.dao.UserDao;

public class UserDaoJdbcImpl extends DaoJdbcImpl<User> implements UserDao {

	public UserDaoJdbcImpl() {
	}

	public void addUser(Connection connection, User user) throws SQLException {
		String sql = "insert into user(user_name,password,name,idcard,phone,city) values (?,?,?,?,?,?);";
		Object[] objs = {user.getUser_name(),user.getPassword(),user.getName(),user.getIdcard(),user.getPhone(),
				user.getCity()};
		update(connection, sql, objs);
	}

	public User searchUserByName(Connection connection, String user_name) throws SQLException {
		String sql = "select * from  ldusedcar.user where user_name = ?;";
		return fetch(connection, sql, user_name);
	}

	public User searchUserByNameandPassword(Connection connection, String user_name, String password)
			throws SQLException {
		String sql = "select * from  ldusedcar.user where user_name = ? and password = ?;";
		Object[] objs = {user_name,password};
		return fetch(connection, sql,objs);
	}

	public List<User> fetchAllUsers(Connection connection) throws SQLException {
		String sql = "select * from user;";
		return fetchList(connection, sql);
	}

	public void updateUser(Connection connection, User user) throws SQLException {
		String sql = "update user set user_name=?,password=?,phone=?,city=? where user_id=?;";
		Object[] objs = {user.getUser_name(),user.getPassword(),user.getPhone(),user.getCity(),user.getUser_id()};
		update(connection, sql, objs);
	}
	
	public void updatePermission(Connection connection, User user) throws SQLException{
		String sql = "update user set permission=? where user_id=?;";
		Object[] objs = {user.getPermission(),user.getUser_id()};
		update(connection, sql, objs);
	}

	public void deleteUser(Connection connection, int user_id) throws SQLException {
		String sql = "delete from user where user_id = ?;";
		update(connection, sql, user_id) ;
	}

	public User searchUserByIdcard(Connection connection, String idcard) throws SQLException {
		String sql = "select * from  ldusedcar.user where idcard = ?;";
		return fetch(connection, sql,idcard);
	}

	public User searchUserById(Connection connection, Integer user_id) throws SQLException {
		String sql = "select * from  ldusedcar.user where user_id = ?;";
		return fetch(connection, sql,user_id);
	}

}
