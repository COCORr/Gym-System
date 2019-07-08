package com.huawei.classroom.student.sport.entity;

import com.huawei.classroom.student.sport.common.Common;
import com.huawei.classroom.student.sport.service.BallService;

/**
 * 羽毛球套餐
 * 
 * @author 
 * 
 */


public class BallPackage extends ServicePackage implements BallService{
	private int ballTime; // 羽毛球时间（小时）
	private int waterNum; // 饮用矿泉水数量（瓶）
	
	public BallPackage() {
        //套餐数据初始化
		this.ballTime = 20;
		this.waterNum = 10;
		this.price = 80.0;
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

	/**
	 * 提供羽毛球服务
	 */
	public int ballPlay(int ballTime, VipCard card) throws Exception {
		int temp = ballTime;
		for(int i=0;i<ballTime;i++){
			if(this.ballTime-card.getrealBallTime()>=1){
				//第一种情况：套餐剩余羽毛球时间可以支持羽毛球运动1小时			
				card.setrealBallTime(card.getrealBallTime()+1); //实际使用羽毛球时间加1h			
			}else if(card.getMoney()>=4){
				//第二种情况:套餐羽毛球时间已用完，账户余额可以支付1h羽毛球时间，使用账户余额支付
				card.setrealBallTime(card.getrealBallTime()+1); //实际使用羽毛球时间加1h
				card.setMoney(Common.sub(card.getMoney(),4)); //账户余额消费4元（1h羽毛球费用）
				card.setConsumAmount(card.getConsumAmount() + 4);
			}else{
				temp = i;
				throw new Exception("本次已使用羽毛球时间"+i+"小时,您的余额不足，请充值后再使用！");
			}
		}
		return temp;
	}


	/**
	 * 提供矿泉水服务
	 */
	public int water(int waterNum, VipCard card) throws Exception {
		int temp = waterNum;
		for(int i=0;i<waterNum;i++){
			if(this.waterNum-card.getrealWaterNum()>=1){
				//第一种情况：套餐剩余矿泉水可以支持提供1瓶矿泉水			
				card.setrealWaterNum(card.getrealWaterNum()+1); //实际饮用矿泉水加1				
			}else if(card.getMoney()>=3){
				//第二种情况:套餐免费矿泉水数已用完，账户余额可以支付1瓶矿泉水，使用账户余额支付
				card.setrealWaterNum(card.getrealWaterNum()+1); 
				card.setMoney(Common.sub(card.getMoney(),3)); //账户余额消费3元（1瓶矿泉水费用）
				card.setConsumAmount(card.getConsumAmount() + 3);
			}else{
				temp = i;
				throw new Exception("本次已饮用矿泉水"+i+"瓶,您的余额不足，请充值后再使用！");
			}
		}
		return temp;
	}
}
