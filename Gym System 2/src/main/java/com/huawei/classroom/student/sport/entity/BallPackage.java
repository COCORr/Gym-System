package com.huawei.classroom.student.sport.entity;

/**
 * 羽毛球套餐
 * 
 * @author 
 * 
 */

public class BallPackage extends ServicePackage {
	private int ballTime; // 羽毛球时间（小时）
	private int waterNum; // 饮用矿泉水数量（瓶）
	
	public BallPackage() {
        //套餐数据初始化
		this.ballTime = 20;
		this.waterNum = 10;
		this.setPrice(80.0);

	}

	public BallPackage(int ballTime,int waterNum) {
		super();
		this.ballTime = ballTime;
		this.waterNum = waterNum;
	}
	
	public int getballTime() {
		return ballTime;
	}

	public void setballTime(int ballTime) {
		this.ballTime = ballTime;
	}
	public int getwaterNum() {
		return waterNum;
	}

	public void setwaterNum(int waterNum) {
		this.waterNum = waterNum;
	}
	

	@Override
	public void showInfo() {
		System.out.println("羽毛球套餐：羽毛球时间是" + this.ballTime + "小时/月,矿泉水数为"
				+ this.waterNum + "瓶/月，资费为"+ this.price + "元/月。");
	}

}
