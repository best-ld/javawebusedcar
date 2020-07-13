package mvc.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.BackMessage;
import mvc.bean.User;
import mvc.dao.BackDao;

public class BackDaoImpl extends DaoJdbcImpl<BackMessage> implements BackDao {

	public void addBackMessage(Connection connection, BackMessage backmessage) throws SQLException {
		String sql = "insert into ldusedcar.backmessage(m_id,send_id,send_name,content) values(?,?,?,?);";
		Object[] objs ={backmessage.getM_id(),backmessage.getSend_id(),backmessage.getSend_name(),backmessage.getContent()};
		update(connection, sql, objs);
	}

	public List<BackMessage> searchBackMessagesBym_id(Connection connection, int m_id) throws SQLException {
		String sql = "SELECT * FROM ldusedcar.backmessage where m_id = ?;";
		return fetchList(connection, sql, m_id);
	}

	public void deleteBackMessagesBym_id(Connection connection, int m_id) throws SQLException {
		String sql = "delete from ldusedcar.backmessage where m_id = ?;";
		update(connection, sql, m_id);
	}

	public void deleteBackMessagesByb_id(Connection connection, int b_id) throws SQLException {
		String sql = "delete from ldusedcar.backmessage where b_id = ?;";
		update(connection, sql, b_id);
	}


}
