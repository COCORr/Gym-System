package com.huawei.classroom.student.sport.entity;

/**
 * 羽毛球套餐
 * 
 * @author 
 * 
 */
public class BallPackage {
	private int ballTime; // 羽毛球时间（小时）
	private int waterNum;

	
	public BallPackage() {
        //套餐数据初始化
		this.ballTime = 20;
		this.waterNum=10;
		
	}

	public BallPackage(int ballTime,int waterNum) {
		super();
		this.ballTime = ballTime;
		this.waterNum=waterNum;
	

	}
	
	public int getballTime() {
		return ballTime;
	}

	public void setballTime(int ballTime) {
		this.ballTime = ballTime;
	}

	public int getBallTime() {
		return ballTime;
	}

	public void setBallTime(int ballTime) {
		this.ballTime = ballTime;
	}

	public int getWaterNum() {
		return waterNum;
	}

	public void setWaterNum(int waterNum) {
		this.waterNum = waterNum;
	}
	


	
}
