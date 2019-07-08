package com.huawei.classroom.student.sport.entity;
/**
 * 会员卡
 * @author 
 *
 */
public class VipCard {
	private String cardNumber;  //卡号
	private String userName;  //用户名
	private String passWord;  //密码	
	private ServicePackage serPackage;  //所属套餐
	private double consumAmount;  //当月消费金额
	private double money;  //账户余额
	private int realSwimTime;  //实际游泳时长（小时）
	private int realBallTime;  //实际羽毛球时长（小时）
	private int realWaterNum;  //实际饮用矿泉水数（瓶）
	
	public VipCard(){}

	public VipCard(String userName, String passWord, String cardNumber,
			ServicePackage serPackage, double consumAmount, double money) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.cardNumber = cardNumber;
		this.serPackage = serPackage;
		this.consumAmount = consumAmount;
		this.money = money;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public ServicePackage getSerPackage() {
		return serPackage;
	}

	public void setSerPackage(ServicePackage serPackage) {
		this.serPackage = serPackage;
	}

	public double getConsumAmount() {
		return consumAmount;
	}

	public void setConsumAmount(double consumAmount) {
		this.consumAmount = consumAmount;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getrealSwimTime() {
		return realSwimTime;
	}

	public void setrealSwimTime(int realSwimTime) {
		this.realSwimTime = realSwimTime;
	}

	public int getrealBallTime() {
		return realBallTime;
	}

	public void setrealBallTime(int realBallTime) {
		this.realBallTime = realBallTime;
	}

	public int getrealWaterNum() {
		return realWaterNum;
	}

	public void setrealWaterNum(int realWaterNum) {
		this.realWaterNum = realWaterNum;
	}
	
	/**
	 * 显示卡信息
	 */
	public void showMeg(){
		System.out.println("卡号："+this.cardNumber+" 用户名："+this.userName+" 当前余额："+this.money+"元。");
		this.serPackage.showInfo();
	}	
}
