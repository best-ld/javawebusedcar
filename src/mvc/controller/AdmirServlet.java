package mvc.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.bean.Car;
import mvc.bean.CarAndUser;
import mvc.bean.SPAndUserAndCar;
import mvc.bean.SellPremission;
import mvc.bean.User;
import mvc.service.AdmirSevice;
import mvc.service.CarService;
import mvc.service.TalkService;
import mvc.service.UserService;

@WebServlet("*.admirdo")
public class AdmirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TalkService talkservice;
	private CarService carservice;
	private UserService userservice;
	private AdmirSevice admirSevice;

	public AdmirServlet() {
		super();
		this.admirSevice = new AdmirSevice();
		this.carservice = new CarService();
		this.userservice = new UserService();
		this.talkservice = new TalkService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1, servletPath.length() - ".admirdo".length());
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

	public void admirlogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("admirname");
		String password = request.getParameter("password");
		if (!admirSevice.checkAdnmirName(name)) {
			request.setAttribute("message", "!请输入正确的管理员账号");
			request.getRequestDispatcher("/AdmirLogin.jsp").forward(request, response);
		} else {
			if (!admirSevice.checkAdnmirPassword(name, password)) {
				request.setAttribute("message", "!请输入正确的密码");
				request.getRequestDispatcher("/AdmirLogin.jsp").forward(request, response);
			} else {
				List<User> users = userservice.search_AllUser();
				request.setAttribute("users", users);
				request.getRequestDispatcher("/WEB-INF/views/AdmirUser.jsp").forward(request, response);
			}
		}
	}

	public void permitvisit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer user_id = Integer.parseInt(request.getParameter("user_id"));
		Integer flag = Integer.parseInt(request.getParameter("flag"));
		User user = userservice.search_UserById(user_id);
		user.setPermission(flag);
		// System.out.println(user);
		userservice.updateUser(user);
		List<User> users = userservice.search_AllUser();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/views/AdmirUser.jsp").forward(request, response);
	}

	public void releasemessgae(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<SellPremission> spremissions = admirSevice.getAllSellMessage();
		List<SPAndUserAndCar> spucs = new ArrayList<SPAndUserAndCar>();
		for (int i = 0; i < spremissions.size(); i++) {
			SellPremission spremission = spremissions.get(i);
			User user = userservice.search_UserById(spremission.getUser_id());
			Car car = carservice.search_CarbyCarId(spremission.getCar_id());
			SPAndUserAndCar spuc = new SPAndUserAndCar(car, user, spremission);
			spucs.add(spuc);
		}
		request.setAttribute("spucs", spucs);
		request.getRequestDispatcher("/WEB-INF/views/AdmirRelease.jsp").forward(request, response);
	}

	public void updateSellPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer s_id = Integer.parseInt(request.getParameter("s_id"));
		Integer flag = Integer.parseInt(request.getParameter("flag"));
		SellPremission sellPremission = admirSevice.searchByS_id(s_id);
		sellPremission.setPremission(flag);
		// 授权的时候默认发布状态为0
		if (flag == 1) {
			sellPremission.setReleasing(0);
		}
		admirSevice.updatePremission(sellPremission);
		User user = userservice.search_UserById(sellPremission.getUser_id());
		List<SellPremission> spremissions = admirSevice.getAllSellMessage();
		List<SPAndUserAndCar> spucs = new ArrayList<SPAndUserAndCar>();
		for (int i = 0; i < spremissions.size(); i++) {
			SellPremission spremission = spremissions.get(i);
			Car car = carservice.search_CarbyCarId(spremission.getCar_id());
			SPAndUserAndCar spuc = new SPAndUserAndCar(car, user, spremission);
			spucs.add(spuc);
		}
		request.setAttribute("spucs", spucs);
		request.getRequestDispatcher("/WEB-INF/views/AdmirRelease.jsp").forward(request, response);
	}

	public void toadmiruser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = userservice.search_AllUser();
		request.setAttribute("users", users);
		request.getRequestDispatcher("/WEB-INF/views/AdmirUser.jsp").forward(request, response);
	}

	public void carmessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Car> cars = carservice.search_AllCar();
		List<CarAndUser> carAndUsers = new ArrayList<CarAndUser>();
		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			// 已发布的信息
			if (admirSevice.searchByC_id(car.getCar_id())!= null&&admirSevice.searchByC_id(car.getCar_id()).getReleasing() == 1
					&& admirSevice.searchByC_id(car.getCar_id()).getPremission() == 1) {
				User user = userservice.search_UserById(car.getUser_id());
				CarAndUser carAndUser = new CarAndUser(user, car);
				carAndUsers.add(carAndUser);
			}
		}
		request.setAttribute("cars", carAndUsers);
		request.getRequestDispatcher("/WEB-INF/views/CarMessageAdmir.jsp").forward(request, response);
	}

	public void offShelves(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		SellPremission sellPremission = admirSevice.searchByC_id(car_id);
		sellPremission.setPremission(0);
		admirSevice.updatePremission(sellPremission);
		List<Car> cars = carservice.search_AllCar();
		List<CarAndUser> carAndUsers = new ArrayList<CarAndUser>();
		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			// 已发布的信息
			if (admirSevice.searchByC_id(car.getCar_id()).getReleasing() == 1
					&& admirSevice.searchByC_id(car.getCar_id()).getPremission() == 1) {
				User user = userservice.search_UserById(car.getUser_id());
				CarAndUser carAndUser = new CarAndUser(user, car);
				carAndUsers.add(carAndUser);
			}
		}
		request.setAttribute("cars", carAndUsers);
		request.getRequestDispatcher("/WEB-INF/views/CarMessageAdmir.jsp").forward(request, response);
	}
	
	public void backlogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("AdmirLogin.jsp").forward(request, response);
	}
}
