package mvc.daoImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import mvc.bean.TalkMessage;
import mvc.dao.TalkDao;

public class TalkDaoImpl extends DaoJdbcImpl<TalkMessage> implements TalkDao {

	public void addMessage(Connection connection, TalkMessage message) throws SQLException {
		String sql = "insert into ldusedcar.message(send_id,get_id,car_id,content) values(?,?,?,?);";
		Object[] objs = { message.getSend_id(), message.getGet_id(), message.getCar_id(), message.getContent() };
		update(connection, sql, objs);
	}

	public List<TalkMessage> searchMessageByC_id(Connection connection, int car_id) throws SQLException {
		String sql = "select * from ldusedcar.message where car_id = ?;";
		return fetchList(connection, sql, car_id);
	}

	public List<TalkMessage> searchMessageByC_idS_id(Connection connection, int car_id, int send_id)
			throws SQLException {
		String sql = "select * from ldusedcar.message where car_id = ? and send_id =?;";
		Object[] objs = { car_id, send_id };
		return fetchList(connection, sql, objs);
	}

	public TalkMessage searchMessageByM_id(Connection connection, int m_id) throws SQLException {
		String sql = "select * from ldusedcar.message where m_id = ?;";
		return fetch(connection, sql, m_id);
	}

	public void deleteMessageByM_id(Connection connection, int m_id) throws SQLException {
		String sql = "delete from ldusedcar.message where m_id = ?;";
		update(connection, sql, m_id);
	}

	@Override
	public List<TalkMessage> searchMessageBysendid(Connection connection, int send_id) throws SQLException {
		String sql = "select * from ldusedcar.message where send_id =?;";
		return fetchList(connection, sql, send_id);
	}

	@Override
	public void deleteMessageByC_id(Connection connection, int c_id) throws SQLException {
		String sql = "delete from ldusedcar.message where car_id = ?;";
		update(connection, sql, c_id);
	}

}
