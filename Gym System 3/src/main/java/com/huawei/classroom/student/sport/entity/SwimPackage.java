package com.huawei.classroom.student.sport.entity;

import com.huawei.classroom.student.sport.common.Common;
import com.huawei.classroom.student.sport.service.SwimService;
import com.huawei.classroom.student.sport.service.WaterService;


/**
 * 游泳套餐
 * 
 * @author 
 * 
 */
public class SwimPackage extends ServicePackage implements SwimService,
		WaterService {
	private int swimTime; // 游泳时间(小时)
	private int waterNum; // 饮用矿泉水数量（瓶）

	public int getswimTime() {
		return swimTime;
	}

	public void setswimTime(int swimTime) {
		this.swimTime = swimTime;
	}

	public int getwaterNum() {
		return waterNum;
	}

	public void setwaterNum(int waterNum) {
		this.waterNum = waterNum;
	}

	public SwimPackage() {
		//套餐数据初始化
		this.swimTime = 20;
		this.waterNum = 10;
		this.price = 100.0;
	}

	public SwimPackage(int swimTime, int waterNum) {
		super();
		this.swimTime = swimTime;
		this.waterNum = waterNum;
	}

	/**
	 * 显示套餐详情
	 */
	public void showInfo() {
		System.out.println("游泳套餐：游泳时长为" + this.swimTime + "小时/月，矿泉水数为"
				+ this.waterNum + "瓶/月，资费为" + this.price + "元/月。");
	}
    
	public int swim(int minCount, VipCard card) throws Exception{
		int temp = minCount; 
		for(int i=0;i<minCount;i++){
			if(this.swimTime-card.getrealSwimTime()>=1){
				//第一种情况：套餐剩余游泳时间可以支持游泳运动1小时				
				card.setrealSwimTime(card.getrealSwimTime()+1); //实际游泳时间加1h				
			}else if(card.getMoney()>=5){
				//第二种情况:套餐游泳时间已用完，账户余额可以支付1h游泳时间，使用账户余额支付
				card.setrealSwimTime(card.getrealSwimTime()+1); //实际游泳时长加1h 
				card.setMoney(Common.sub(card.getMoney(),5)); //账户余额消费5元（1h游泳费用）
				card.setConsumAmount(card.getConsumAmount() + 5);
			}else{
				temp = i; //记录实际游泳小时数
				throw new Exception("本次已游泳"+i+"小时,您的余额不足，请充值后再使用！");				
			}
		}
		return temp;
	}
		
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
