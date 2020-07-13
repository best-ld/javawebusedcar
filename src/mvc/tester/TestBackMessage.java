package mvc.tester;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.Test;

import mvc.bean.BackAndGetMessage;
import mvc.bean.BackMessage;
import mvc.bean.TalkMessage;
import mvc.bean.User;
import mvc.dao.BackDao;
import mvc.dao.TalkDao;
import mvc.dao.UserDao;
import mvc.daoImpl.BackDaoImpl;
import mvc.daoImpl.TalkDaoImpl;
import mvc.daoImpl.UserDaoJdbcImpl;
import mvc.utils.JdbcTools;

class TestBackMessage {

	

	@Test
	public void testadd() {
		BackDao backDao = new BackDaoImpl();
		Connection connection = null;
		BackMessage bMessage = new BackMessage(null, 19, 23, "cl", "666");
		try {
			connection = JdbcTools.getConnection();
			// backDao.addBackMessage(connection, bMessage);
			// System.out.println(backDao.searchBackMessagesBym_id(connection, 19));
			backDao.deleteBackMessagesBym_id(connection, 19);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}

	@Test
	public void testnewlei() {
		BackDao backDao = new BackDaoImpl();
		TalkDao talkDao = new TalkDaoImpl();
		UserDao userDao = new UserDaoJdbcImpl();
		Connection connection = null;
		try {
			connection = JdbcTools.getConnection();
			TalkMessage talkMessage = talkDao.searchMessageByM_id(connection, 32);
			List<BackMessage> backMessages = backDao.searchBackMessagesBym_id(connection, 32);
			//User user = userDao.searchUserById(connection, talkMessage.getSend_id());
			//BackAndGetMessage backAndGetMessage = new BackAndGetMessage(backMessages, talkMessage);
			//System.out.println(backAndGetMessage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
}
