package mvc.bean;

public class BackMessage {
	private Integer b_id;
	private Integer m_id;
	private Integer send_id;
	private String content;
	private String send_name;
	public Integer getB_id() {
		return b_id;
	}
	public void setB_id(Integer b_id) {
		this.b_id = b_id;
	}
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public BackMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	@Override
	public String toString() {
		return "BackMessage [b_id=" + b_id + ", m_id=" + m_id + ", send_id=" + send_id + ", content=" + content
				+ ", send_name=" + send_name + "]";
	}
	public BackMessage(Integer b_id, Integer m_id, Integer send_id, String send_name,String content) {
		super();
		this.b_id = b_id;
		this.m_id = m_id;
		this.send_id = send_id;
		this.content = content;
		this.send_name = send_name;
	}
	
}
