package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.BackMessage;

public interface BackDao {
	public void addBackMessage(Connection connection, BackMessage backmessage) 
			throws SQLException;
	public List<BackMessage> searchBackMessagesBym_id(Connection connection, int m_id) 
			throws SQLException;
	public void deleteBackMessagesBym_id(Connection connection, int m_id)
			throws SQLException;
	public void deleteBackMessagesByb_id(Connection connection, int b_id)
			throws SQLException;
}
