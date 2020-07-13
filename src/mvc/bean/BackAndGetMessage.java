package mvc.bean;

import java.util.List;

public class BackAndGetMessage {
	private List<BackMessage> backMessages;
	private TalkMessage talkMessage;
	private Car car;
	public BackAndGetMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BackAndGetMessage(List<BackMessage> backMessages, TalkMessage talkMessage, Car car) {
		super();
		this.backMessages = backMessages;
		this.talkMessage = talkMessage;
		this.car = car;
	}
	public List<BackMessage> getBackMessages() {
		return backMessages;
	}
	public void setBackMessages(List<BackMessage> backMessages) {
		this.backMessages = backMessages;
	}
	public TalkMessage getTalkMessage() {
		return talkMessage;
	}
	public void setTalkMessage(TalkMessage talkMessage) {
		this.talkMessage = talkMessage;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	@Override
	public String toString() {
		return "BackAndGetMessage [backMessages=" + backMessages + ", talkMessage=" + talkMessage + ", car=" + car
				+ "]";
	}
	
	
}
