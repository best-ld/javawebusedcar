package mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.bean.BackAndGetMessage;
import mvc.bean.BackMessage;
import mvc.bean.Car;
import mvc.bean.TalkMessage;
import mvc.bean.User;
import mvc.service.CarService;
import mvc.service.TalkService;
import mvc.service.UserService;

@WebServlet("*.talkdao")
public class SellerBuyerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TalkService talkservice;
	private CarService carservice;
	private UserService userservice;

	public SellerBuyerServlet() {
		super();
		this.talkservice = new TalkService();
		this.carservice = new CarService();
		this.userservice = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1, servletPath.length() - ".talkdao".length());
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 买家发送消息给卖家（卖家=get，买家=send）
	public void sendmessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String content = request.getParameter("content");
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		int get_id = Integer.parseInt(request.getParameter("get_id"));
		int send_id = Integer.parseInt(request.getParameter("send_id"));
		if (content != "") {
			talkservice.addNewMessage(send_id, get_id, car_id, content);
		} else {
			request.setAttribute("waringmessage", "！留言不能为空");
		}
		List<TalkMessage> messages = new ArrayList<TalkMessage>();
		messages = talkservice.searchMessageByCar_id(car_id);
		User buyer = userservice.search_UserById(send_id);
		Car car = carservice.search_CarbyCarId(car_id);
		User seller = userservice.search_UserById(get_id);
		List<TalkMessage> sub_messages = new ArrayList<TalkMessage>();
		for (int i = 0; i < messages.size(); i++) {
			TalkMessage message = null;
			if (messages.get(i).getContent().length() >= 15) {
				message = new TalkMessage(messages.get(i).getM_id(), messages.get(i).getSend_id(),
						messages.get(i).getGet_id(), messages.get(i).getCar_id(),
						messages.get(i).getContent().substring(0, 15));
			} else {
				message = messages.get(i);
			}
			sub_messages.add(message);
			System.out.println(sub_messages.get(i).getContent());
		}
		request.setAttribute("messages", sub_messages);
		request.setAttribute("buyer", buyer);
		request.setAttribute("car", car);
		request.setAttribute("seller", seller);
		request.getRequestDispatcher("/WEB-INF/views/CarDetails.jsp").forward(request, response);
	}

	// 查看某一条留言的全部回复
	public void seebackMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		int flag = Integer.parseInt(request.getParameter("flag"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = userservice.search_UserById(user_id);
		List<BackMessage> backMessages = new ArrayList<BackMessage>();
		backMessages = talkservice.searchBackMessageByM_id(m_id);
		TalkMessage talkmessage = talkservice.searcMeeageByM_id(m_id);
		// 返回car类
		Car car = carservice.search_CarbyCarId(talkmessage.getCar_id());
		// 该条留言的发布者，即回复的接收者
		User geter = userservice.search_UserById(talkmessage.getSend_id());
		request.setAttribute("car", car);
		request.setAttribute("flag", flag);
		request.setAttribute("user", user);
		request.setAttribute("geter", geter);
		request.setAttribute("talkmessage", talkmessage);
		request.setAttribute("backMessages", backMessages);
		request.getRequestDispatcher("/WEB-INF/views/SeeBackMessage.jsp").forward(request, response);
	}

	// 添加评论功能
	public void addbackMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		int send_id = Integer.parseInt(request.getParameter("send_id"));// 本机用户
		int get_id = Integer.parseInt(request.getParameter("get_id"));// 要评论的对象
		int flag = Integer.parseInt(request.getParameter("flag"));
		System.out.println(flag);
		String send_name = userservice.search_UserById(send_id).getUser_name();
		String content = request.getParameter("content");
		if (content == "") {
			request.setAttribute("waringmessage", "！留言不能为空");
		} else {
			talkservice.addBackMessage(m_id, send_id, send_name, content);
		}
		User user = userservice.search_UserById(send_id);
		User geter = userservice.search_UserById(get_id);
		TalkMessage talkmessage = talkservice.searcMeeageByM_id(m_id);
		List<BackMessage> backMessages = new ArrayList<BackMessage>();
		backMessages = talkservice.searchBackMessageByM_id(m_id);
		Car car = carservice.search_CarbyCarId(talkmessage.getCar_id());
		request.setAttribute("flag", flag);
		request.setAttribute("car", car);
		request.setAttribute("user", user);
		request.setAttribute("geter", geter);
		request.setAttribute("talkmessage", talkmessage);
		request.setAttribute("backMessages", backMessages);
		
		request.getRequestDispatcher("/WEB-INF/views/SeeBackMessage.jsp").forward(request, response);
	}

	// 删除自己发布的留言
	public void deleteMymessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int send_id = Integer.parseInt(request.getParameter("send_id"));
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		// int get_id = Integer.parseInt(request.getParameter("get_id"));
		TalkMessage talkMessage = talkservice.searcMeeageByM_id(m_id);
		int car_id = talkMessage.getCar_id();
		talkservice.deleteMessage(m_id);
		List<TalkMessage> messages = new ArrayList<TalkMessage>();
		messages = talkservice.searchMessageByCar_id(car_id);
		User buyer = userservice.search_UserById(send_id);
		Car car = carservice.search_CarbyCarId(car_id);
		User seller = userservice.search_UserById(car.getUser_id());
		List<TalkMessage> sub_messages = new ArrayList<TalkMessage>();
		for (int i = 0; i < messages.size(); i++) {
			TalkMessage message = null;
			if (messages.get(i).getContent().length() >= 15) {
				message = new TalkMessage(messages.get(i).getM_id(), messages.get(i).getSend_id(),
						messages.get(i).getGet_id(), messages.get(i).getCar_id(),
						messages.get(i).getContent().substring(0, 15));
			} else {
				message = messages.get(i);
			}
			sub_messages.add(message);
		}
		request.setAttribute("messages", sub_messages);
		request.setAttribute("buyer", buyer);
		request.setAttribute("car", car);
		request.setAttribute("seller", seller);
		request.setAttribute("succeffulmessage", "删除成功！");
		request.getRequestDispatcher("/WEB-INF/views/CarDetails.jsp").forward(request, response);
	}
	
	public void deleteMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		talkservice.deleteMessage(m_id);
		User user = userservice.search_UserById(user_id);
		List<TalkMessage> talkMessages = talkservice.searchMessageBySendId(user_id);
		List<BackAndGetMessage> backAndGetMessages = new ArrayList<BackAndGetMessage>();
		for (int i = 0; i < talkMessages.size(); i++) {
			List<BackMessage> backMessages = talkservice.searchBackMessageByM_id(talkMessages.get(i).getM_id());
			Car car = carservice.search_CarbyCarId(talkMessages.get(i).getCar_id());
			BackAndGetMessage backAndGetMessage = new BackAndGetMessage(backMessages,talkMessages.get(i),car);
			backAndGetMessages.add(backAndGetMessage);
		}
		request.setAttribute("allmessages", backAndGetMessages);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/SeeMyMessage.jsp").forward(request, response);
	}

	public void seeMyLeaveMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = userservice.search_UserById(user_id);
		List<TalkMessage> talkMessages = talkservice.searchMessageBySendId(user_id);
		List<BackAndGetMessage> backAndGetMessages = new ArrayList<BackAndGetMessage>();
		for (int i = 0; i < talkMessages.size(); i++) {
			List<BackMessage> backMessages = talkservice.searchBackMessageByM_id(talkMessages.get(i).getM_id());
			Car car = carservice.search_CarbyCarId(talkMessages.get(i).getCar_id());
			BackAndGetMessage backAndGetMessage = new BackAndGetMessage(backMessages,talkMessages.get(i),car);
			backAndGetMessages.add(backAndGetMessage);
		}
		request.setAttribute("allmessages", backAndGetMessages);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/SeeMyMessage.jsp").forward(request, response);
	}
	
	
}
