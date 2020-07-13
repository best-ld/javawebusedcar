package mvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import mvc.bean.Car;
import mvc.bean.SPAndUserAndCar;
import mvc.bean.SellPremission;
import mvc.bean.TalkMessage;
import mvc.bean.User;
import mvc.daoImpl.PhotoDaoImpl;
import mvc.service.AdmirSevice;
import mvc.service.CarService;
import mvc.service.TalkService;
import mvc.service.UserService;
import mvc.service.UserSignupService;

@WebServlet("*.cardo")
public class CarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CarService carservice;
	private UserService userservice;
	private TalkService talkservice;
	private AdmirSevice admirSevice;

	public CarServlet() {
		super();
		this.admirSevice = new AdmirSevice();
		this.talkservice = new TalkService();
		this.carservice = new CarService();
		this.userservice = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String servletPath = request.getServletPath();
		String methodName = servletPath.substring(1, servletPath.length() - ".cardo".length());
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

	// 添加车辆
	public void addCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		
		//*****************************
		List<String> list= new ArrayList<String>();
		String filename = PhotoDaoImpl.getPhotoNewName();
		ServletContext servletContext = null;
		servletContext = request.getSession().getServletContext();
		List<FileItem> items = PhotoDaoImpl.getRequsetFileItems(request, servletContext);
		// System.out.println(user_id);
		boolean isLoadToSQL = false;
		for (FileItem item : items) {
			if (!item.isFormField()) {
				if (PhotoDaoImpl.isGif(item)) {
					isLoadToSQL = PhotoDaoImpl.saveFile(item, filename);
				} else {
					System.out.print("后缀格式有误，保存文件失败");
				}
			} else {
				list.add(item.getString("UTF-8"));
			}
		}
		// System.out.println(list);
		String finalPhotoName = "imgs/" + filename;

		// *****************************
		String license_plate = list.get(0);
		String brand = list.get(1);
		String model = list.get(2);
		String years_s = list.get(3);
		String evaluation_s = list.get(4);
		User user = userservice.search_UserById(user_id);

		if (evaluation_s == "") {
			request.setAttribute("message", "！输入估价为空");
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/AddSellCar.jsp").forward(request, response);
		} else {
			if (years_s == "") {
				request.setAttribute("message", "！输入年限为空");
				request.setAttribute("user", user);
				request.getRequestDispatcher("/WEB-INF/views/AddSellCar.jsp").forward(request, response);
			} else {
				if (!carservice.check_license_plate(license_plate)) {
					request.setAttribute("message", "！该车牌号已存在，请填写正确的车牌号码");
					request.setAttribute("user", user);
					request.getRequestDispatcher("/WEB-INF/views/AddSellCar.jsp").forward(request, response);
				} else {
					Integer evaluation = Integer.parseInt(evaluation_s);
					Integer years = Integer.parseInt(years_s);
					carservice.add_NewCar(user_id, license_plate, brand, model, years, evaluation, finalPhotoName);
					List<SPAndUserAndCar> spaucs = new ArrayList<SPAndUserAndCar>();
					List<Car> cars = carservice.search_CarbyUserId(user_id);
					Car car = carservice.search_CarbyLicense_plate(license_plate);
					admirSevice.addNewPremission(car.getCar_id(), user_id);
					for (int i = 0; i < cars.size(); i++) {
						SellPremission sellPremission = admirSevice.searchByC_id(cars.get(i).getCar_id());
						SPAndUserAndCar spauc = new SPAndUserAndCar(cars.get(i), user, sellPremission);
						spaucs.add(spauc);
					}
					request.setAttribute("cars", cars);
					request.setAttribute("spaucs", spaucs);
					request.setAttribute("user", user);
					request.setAttribute("successfulmessage", "！添加车辆成功");
					request.getRequestDispatcher("/WEB-INF/views/SellPage.jsp").forward(request, response);
				}
			}
		}
	}

	// 进入卖车界面
	public void sellCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		List<Car> cars = carservice.search_CarbyUserId(user_id);
		List<SPAndUserAndCar> spaucs = new ArrayList<SPAndUserAndCar>();
		SPAndUserAndCar spauc = null;
		User user = userservice.search_UserById(user_id);
		if (user.getPermission() == 0) {
			cars = userservice.search_AllCar();
			request.setAttribute("cars", cars);
			request.setAttribute("user", user);
			request.setAttribute("message", "!您没有权限进行该项操作，请前往申请权限");
			request.getRequestDispatcher("/WEB-INF/views/UserHomepage.jsp").forward(request, response);
		} else {
			for (int i = 0; i < cars.size(); i++) {
				SellPremission sellPremission = admirSevice.searchByC_id(cars.get(i).getCar_id());
				spauc = new SPAndUserAndCar(cars.get(i), user, sellPremission);
				spaucs.add(spauc);
			}
			request.setAttribute("user", user);
			request.setAttribute("spaucs", spaucs);
			request.setAttribute("cars", cars);
			request.getRequestDispatcher("/WEB-INF/views/SellPage.jsp").forward(request, response);
		}
	}

	// 修改发布状态
	public void release(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		int flag = Integer.parseInt(request.getParameter("flag"));
		SellPremission sellPremission_u = admirSevice.searchByC_id(car_id);
		sellPremission_u.setReleasing(flag);
		admirSevice.updatePremission(sellPremission_u);
		List<Car> cars = carservice.search_CarbyUserId(user_id);
		List<SPAndUserAndCar> spaucs = new ArrayList<SPAndUserAndCar>();
		User user = userservice.search_UserById(user_id);
		if (user.getPermission() == 0) {
			cars = userservice.search_AllCar();
			request.setAttribute("cars", cars);
			request.setAttribute("user", user);
			request.setAttribute("message", "!您没有权限进行该项操作，请前往申请权限");
			request.getRequestDispatcher("/WEB-INF/views/UserHomepage.jsp").forward(request, response);
		} else {
			for (int i = 0; i < cars.size(); i++) {
				SellPremission sellPremission = admirSevice.searchByC_id(cars.get(i).getCar_id());
				SPAndUserAndCar spauc = new SPAndUserAndCar(cars.get(i), user, sellPremission);
				spaucs.add(spauc);
			}
			request.setAttribute("user", user);
			request.setAttribute("spaucs", spaucs);
			request.setAttribute("cars", cars);
			request.getRequestDispatcher("/WEB-INF/views/SellPage.jsp").forward(request, response);
		}
	}

	// 进入修改车辆信息界面
	public void editCar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		Car car = carservice.search_CarbyCarId(car_id);
		User user = userservice.search_UserById(user_id);
		request.setAttribute("car", car);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/UpdateSellCar.jsp").forward(request, response);
	}

	// 修改车辆信息
	public void updateCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));

		List<String> list = new ArrayList<String>();
		String filename = PhotoDaoImpl.getPhotoNewName();
		ServletContext servletContext = null;
		servletContext = request.getSession().getServletContext();
		List<FileItem> items = PhotoDaoImpl.getRequsetFileItems(request, servletContext);
		System.out.println(user_id);
		System.out.println(car_id);
		boolean isLoadToSQL = false;
		for (FileItem item : items) {
			if (!item.isFormField()) {
				if (PhotoDaoImpl.isGif(item)) {
					isLoadToSQL = PhotoDaoImpl.saveFile(item, filename);
				} else {
					//System.out.print("后缀格式有误，保存文件失败");
				}
			} else {
				list.add(item.getString("UTF-8"));
			}
		}
		//System.out.println(list);
		String finalPhotoName = "imgs/" + filename;
		String license_plate = list.get(0);
		String brand = list.get(1);
		String model = list.get(2);
		String years_s = list.get(3);
		String evaluation_s = list.get(4);
		Integer years = Integer.parseInt(years_s);
		Integer evaluation = Integer.parseInt(evaluation_s);

		Car car = carservice.search_CarbyCarId(car_id);
		User user = userservice.search_UserById(user_id);
		if (admirSevice.searchByC_id(car_id).getReleasing() == 1
				&& admirSevice.searchByC_id(car_id).getPremission() == 1) {
			request.setAttribute("warningmessage", "！该车辆已经发布，无法修改，请取消发布后再进行修改");
			request.setAttribute("car", car);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/UpdateSellCar.jsp").forward(request, response);
		} else {
			if ((!carservice.check_license_plate(license_plate))
					&& (!carservice.search_CarbyCarId(car_id).getLicense_plate().equals(license_plate))) {
				request.setAttribute("car", car);
				request.setAttribute("user", user);
				request.setAttribute("warningmessage", "！您要修改的车牌号已经存在");
				request.getRequestDispatcher("/WEB-INF/views/UpdateSellCar.jsp").forward(request, response);
			}
			else {
				carservice.update_Car(car_id, license_plate, brand, model, years, evaluation,finalPhotoName);
				List<Car> cars = carservice.search_CarbyUserId(user_id);
				List<SPAndUserAndCar> spaucs = new ArrayList<SPAndUserAndCar>();
				for (int i = 0; i < cars.size(); i++) {
					SellPremission sellPremission = admirSevice.searchByC_id(cars.get(i).getCar_id());
					SPAndUserAndCar spauc = new SPAndUserAndCar(cars.get(i),user,sellPremission);
					spaucs.add(spauc);
				}
				request.setAttribute("user", user);
				request.setAttribute("spaucs", spaucs);
				request.setAttribute("cars", cars);
				request.getRequestDispatcher("/WEB-INF/views/SellPage.jsp").forward(request, response);
			}
		}
	}

	// 进入删除界面
	public void deleteCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		Car car = carservice.search_CarbyCarId(car_id);
		User user = userservice.search_UserById(user_id);
		request.setAttribute("car", car);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/DeleteSellCar.jsp").forward(request, response);
	}

	// 删除车辆，按车辆编号
	public void closeCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = userservice.search_UserById(user_id);
		String user_name = request.getParameter("username");
		String password = request.getParameter("password");
		String again_password = request.getParameter("again_password");
		if ((!user.getPassword().equals(password)) || (!user.getUser_name().equals(user_name))
				|| (!password.equals(again_password))) {
			Car car = carservice.search_CarbyCarId(car_id);
			request.setAttribute("message", "!用户密码或账号输入有误");
			request.setAttribute("car", car);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/DeleteSellCar.jsp").forward(request, response);
		} else {
			admirSevice.deletePremission(car_id);
			talkservice.deleteMessageByCar_id(car_id);
			carservice.delete_Car(car_id);
			// List<Car> cars = carservice.search_CarbyUserId(user_id);
			List<SPAndUserAndCar> spaucs = new ArrayList<SPAndUserAndCar>();
			List<Car> cars = carservice.search_CarbyUserId(user_id);
			for (int i = 0; i < cars.size(); i++) {
				SellPremission sellPremission = admirSevice.searchByC_id(cars.get(i).getCar_id());
				SPAndUserAndCar spauc = new SPAndUserAndCar(cars.get(i), user, sellPremission);
				spaucs.add(spauc);
			}
			request.setAttribute("user", user);
			request.setAttribute("spaucs", spaucs);
			request.setAttribute("cars", cars);
			request.getRequestDispatcher("/WEB-INF/views/SellPage.jsp").forward(request, response);
		}
	}

	// 查找自己的车辆
	public void searchMyCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String license_plate = request.getParameter("license_plate");
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		Car car = carservice.search_CarbyLicense_plate(license_plate);
		User user = userservice.search_UserById(user_id);
		if (car != null && car.getUser_id() == user_id) {
			request.setAttribute("car", car);
			request.setAttribute("user", user);
			request.getRequestDispatcher("/WEB-INF/views/UpdateSellCar.jsp").forward(request, response);
		} else {
			List<Car> cars = carservice.search_CarbyUserId(user_id);
			request.setAttribute("searchMyCarwarning", "！该车牌号码不存在");
			request.setAttribute("user", user);
			request.setAttribute("cars", cars);
			request.getRequestDispatcher("/WEB-INF/views/SellPage.jsp").forward(request, response);
		}
	}

	// 按价格升序排列
	public void toporderCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = userservice.search_UserById(user_id);
		List<Car> cars = carservice.order_CarTop();
		List<Car> cars_re = new ArrayList<Car>();
		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			SellPremission sellPremission = admirSevice.searchByC_id(car.getCar_id());
			if (sellPremission != null && sellPremission.getPremission() == 1 && sellPremission.getReleasing() == 1) {
				cars_re.add(car);
			}
		}
		request.setAttribute("searchcarsuccessmessage", "回到主页");
		request.setAttribute("cars", cars_re);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/UserHomepage.jsp").forward(request, response);
	}

	// 按价格降序排列
	public void droporderCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = userservice.search_UserById(user_id);
		List<Car> cars_re = new ArrayList<Car>();
		List<Car> cars = carservice.order_CarDrop();
		for (int i = 0; i < cars.size(); i++) {
			Car car = cars.get(i);
			SellPremission sellPremission = admirSevice.searchByC_id(car.getCar_id());
			if (sellPremission != null && sellPremission.getPremission() == 1 && sellPremission.getReleasing() == 1) {
				cars_re.add(car);
			}
		}
		request.setAttribute("searchcarsuccessmessage", "回到主页");
		request.setAttribute("cars", cars_re);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/UserHomepage.jsp").forward(request, response);
	}

	// 查看车辆的详细信息
	public void detailsCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User buyer = userservice.search_UserById(user_id);
		Car car = carservice.search_CarbyCarId(car_id);
		User seller = userservice.search_UserById(car.getUser_id());
		List<TalkMessage> messages = new ArrayList<TalkMessage>();
		messages = talkservice.searchMessageByCar_id(car_id);
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
			// System.out.println(sub_messages.get(i).getContent());
		}
		request.setAttribute("messages", sub_messages);
		request.setAttribute("car", car);
		request.setAttribute("buyer", buyer);
		request.setAttribute("seller", seller);
		request.getRequestDispatcher("/WEB-INF/views/CarDetails.jsp").forward(request, response);
	}

	// 查看我的一台车及评论
	public void seeOneMyCar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int car_id = Integer.parseInt(request.getParameter("car_id"));
		Car car = carservice.search_CarbyCarId(car_id);
		List<TalkMessage> messages = talkservice.searchMessageByCar_id(car_id);
		User user = userservice.search_UserById(user_id);
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
			// System.out.println(sub_messages.get(i).getContent());
		}
		request.setAttribute("messages", sub_messages);
		request.setAttribute("car", car);
		request.setAttribute("user", user);
		request.getRequestDispatcher("/WEB-INF/views/SeeMyCar.jsp").forward(request, response);
	}
}
