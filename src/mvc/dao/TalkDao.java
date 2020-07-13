package mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import mvc.bean.TalkMessage;


public interface TalkDao {
	
	public void addMessage(Connection connection,TalkMessage message)//发送新消息
			throws SQLException;
	
	public List<TalkMessage> searchMessageByC_id(Connection connection,int car_id)//按车辆id查询
			throws SQLException;
	
	public List<TalkMessage> searchMessageBysendid(Connection connection,int send_id)//按发送者id查询
			throws SQLException;
	
	public List<TalkMessage> searchMessageByC_idS_id(Connection connection,int car_id,int send_id)//按车辆id查询
			throws SQLException;
	
	public TalkMessage searchMessageByM_id(Connection connection,int m_id)//按车辆id查询
			throws SQLException;
	
	public void deleteMessageByM_id(Connection connection,int m_id)//按车辆id查询
			throws SQLException;
	
	public void deleteMessageByC_id(Connection connection,int c_id)//按车辆id删除
			throws SQLException;
}
