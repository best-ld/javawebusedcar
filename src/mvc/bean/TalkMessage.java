package mvc.bean;

public class TalkMessage {
	private Integer m_id;
	private Integer send_id;
	private Integer get_id;
	private Integer car_id;
	private String content;
	public Integer getM_id() {
		return m_id;
	}
	public void setM_id(Integer m_id) {
		this.m_id = m_id;
	}
	public Integer getSend_id() {
		return send_id;
	}
	public void setSend_id(Integer send_id) {
		this.send_id = send_id;
	}
	public Integer getGet_id() {
		return get_id;
	}
	public void setGet_id(Integer get_id) {
		this.get_id = get_id;
	}
	public Integer getCar_id() {
		return car_id;
	}
	public void setCar_id(Integer car_id) {
		this.car_id = car_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public TalkMessage(Integer m_id, Integer send_id, Integer get_id, Integer car_id, String content) {
		super();
		this.m_id = m_id;
		this.send_id = send_id;
		this.get_id = get_id;
		this.car_id = car_id;
		this.content = content;
	}
	public TalkMessage() {
		super();
	}
	
	public String toString() {
		return "TalkMessage [m_id=" + m_id + ", send_id=" + send_id + ", get_id=" + get_id + ", car_id=" + car_id
				+ ", content=" + content + "]";
	}
	
}
