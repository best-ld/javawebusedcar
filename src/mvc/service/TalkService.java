package mvc.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import mvc.bean.BackMessage;
import mvc.bean.TalkMessage;
import mvc.dao.BackDao;
import mvc.dao.TalkDao;
import mvc.daoImpl.BackDaoImpl;
import mvc.daoImpl.TalkDaoImpl;
import mvc.utils.JdbcTools;

public class TalkService {

	private TalkDao talkdao;
	private Connection connection;
	private BackDao backDao;
	
	public TalkService() {
		super();
		this.talkdao = new TalkDaoImpl();
		this.connection = null;
		this.backDao = new BackDaoImpl();
	}
	
	public void addNewMessage(int send_id,int get_id,int car_id,String content) {
		try {
			connection = JdbcTools.getConnection();
			TalkMessage message =new TalkMessage(null,send_id,get_id,car_id,content);
			talkdao.addMessage(connection, message);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	public  List<TalkMessage> searchMessageByCar_id(int car_id) {
		List<TalkMessage> messages = new ArrayList<TalkMessage>();
		try {
			connection = JdbcTools.getConnection();
			messages = talkdao.searchMessageByC_id(connection, car_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return messages;
	}
	
	public List<BackMessage> searchBackMessageByM_id(int m_id) {
		List<BackMessage> backMessages = new ArrayList<BackMessage>();
		try {
			connection = JdbcTools.getConnection();
			backMessages = backDao.searchBackMessagesBym_id(connection, m_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return backMessages;
	}
	
	public TalkMessage searcMeeageByM_id(int m_id) {
		TalkMessage talkMessage = new TalkMessage();
		try {
			connection = JdbcTools.getConnection();
			talkMessage= talkdao.searchMessageByM_id(connection, m_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return talkMessage;
	}
	
	public void addBackMessage(int m_id,int send_id,String send_name,String content) {
		BackMessage backMessage = new BackMessage(null,m_id,send_id,send_name,content);
		try {
			connection = JdbcTools.getConnection();
			backDao.addBackMessage(connection, backMessage);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	public void deleteMessage(int m_id) {
		try {
			connection = JdbcTools.getConnection();
			backDao.deleteBackMessagesBym_id(connection, m_id);
			talkdao.deleteMessageByM_id(connection, m_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
	
	public List<TalkMessage> searchMessageBySendId(int send_id) {
		List<TalkMessage> talkMessages = new ArrayList<TalkMessage>();
		try {
			connection = JdbcTools.getConnection();
			talkMessages = talkdao.searchMessageBysendid(connection, send_id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
		return talkMessages;
	}
	
	public void deleteMessageByCar_id(int car_id) {
		try {
			connection = JdbcTools.getConnection();
			List<TalkMessage> talkMessages = talkdao.searchMessageByC_id(connection, car_id);
			for (int i = 0; i < talkMessages.size(); i++) {
				deleteMessage(talkMessages.get(i).getM_id());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcTools.releaseResource(null, connection);
		}
	}
}
