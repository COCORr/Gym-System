package com.huawei.classroom.student.sport.entity;


/**
 * 消费信息
 * @author 
 *
 */
public class ConsumInfo {
	private String cardNumber;  //卡号
	private String type;  //消费类型：游泳、羽毛球、矿泉水
	private int consumData;   //消费数据   游泳：小时(h)   羽毛球：小时(h)   矿泉水：瓶
	
	public ConsumInfo(){}
	public ConsumInfo(String cardNumber, String type, int consumData) {
		super();
		this.cardNumber = cardNumber;
		this.type = type;
		this.consumData = consumData;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getConsumData() {
		return consumData;
	}
	public void setConsumData(int consumData) {
		this.consumData = consumData;
	}	
}
