package mvc.bean;

public class CarAndUser {
	private User user;
	private Car car;
	public CarAndUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CarAndUser(User user, Car car) {
		super();
		this.user = user;
		this.car = car;
	}
	@Override
	public String toString() {
		return "CarAndUser [user=" + user + ", car=" + car + "]";
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
}
