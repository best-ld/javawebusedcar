package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.User;

public interface UserDao {
	public void addUser(Connection connection, User user)//����»�Ա
			throws SQLException;
	public User searchUserById(Connection connection,Integer user_id)//���û�id���һ�Ա
			throws SQLException;
	public User searchUserByName(Connection connection,String user_name)//���û��������һ�Ա
			throws SQLException;
	public User searchUserByIdcard(Connection connection,String idcard)//���û����֤�Ų��һ�Ա
			throws SQLException;
	public User searchUserByNameandPassword(Connection connection,String user_name,String password)//��������������һ�Ա
			throws SQLException; 
	public List<User> fetchAllUsers(Connection connection)//�г����л�Ա
			throws SQLException;
	public void updateUser(Connection connection,User user)//�޸Ļ�Ա��Ϣ
			throws SQLException;
	public void deleteUser(Connection connection,int user_id)//����Ա��ɾ����Ա
			throws SQLException;
	public void updatePermission(Connection connection, User user) 
			throws SQLException;
}
