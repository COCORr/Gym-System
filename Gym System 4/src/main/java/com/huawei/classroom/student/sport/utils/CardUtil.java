package com.huawei.classroom.student.sport.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.huawei.classroom.student.sport.entity.BallPackage;
import com.huawei.classroom.student.sport.entity.ConsumInfo;
import com.huawei.classroom.student.sport.entity.GoldPackage;
import com.huawei.classroom.student.sport.entity.Scene;
import com.huawei.classroom.student.sport.entity.ServicePackage;
import com.huawei.classroom.student.sport.entity.SwimPackage;
import com.huawei.classroom.student.sport.entity.VipCard;

/**
 * 会员卡工具类
 * 
 * @author 
 * 
 */
public class CardUtil {
	Map<String, VipCard> cards = new HashMap<String, VipCard>(); // 所有手机卡的列表
	public Map<String, VipCard> getCards() {
		return cards;
	}

	public void setCards(Map<String, VipCard> cards) {
		this.cards = cards;
	}

	Map<String, List<ConsumInfo>> consumInfos = new HashMap<String, List<ConsumInfo>>(); // 所有手机卡消费记录的列表
	List<Scene> scenes = new ArrayList<Scene>();

	// 初始化用户
	public void init() {
		VipCard card1 = new VipCard("苏东", "123", "13965756432",
				new SwimPackage(), 58.0, 42.0);
		VipCard card2 = new VipCard("黄瑶", "123", "13956712467",
				new BallPackage(), 68.0, 32.0);
		VipCard card3 = new VipCard("王三石", "123", "13911568956",
				new GoldPackage(), 78.0, 22.0);
		VipCard card4 = new VipCard("马东", "123", "13924221868",
				new SwimPackage(), 78.0, 2.0);
		card4.setConsumAmount(98.0);
		card4.setrealBallTime(500);
		card4.setrealWaterNum(100);
		cards.put("13965756432", card1);
		cards.put("13956712467", card2);
		cards.put("13911568956", card3);
		cards.put("13924221868", card4);
	}
	
	/**
	 * 使用场景初始化
	 */
	public void initScenes(){	
		scenes.add(new Scene("游泳",1,"日常有氧训练，游泳1小时"));
		scenes.add(new Scene("游泳",3,"教朋友学游泳，消耗3小时"));
		scenes.add(new Scene("喝矿泉水",1,"自己饮用，消耗1瓶"));
		scenes.add(new Scene("喝矿泉水",3,"帮别人带了两瓶，共消耗3瓶水"));		
		scenes.add(new Scene("打羽毛球",1,"独自前往羽毛球馆，与陌生人对打1小时"));
		scenes.add(new Scene("打羽毛球",2,"和朋友一起玩的开心，打了2小时"));	
	}

	/**
	 * 是否存在此卡用户

	 * 
	 * @param number
	 * @param passWord
	 * @return
	 */
	public boolean isExistCard(String number, String passWord) {
		
		return false;
	}
	
	/**
	 * 查找指定卡号是否已注册
	 * 
	 * @param searchNumber
	 * @return 未注册：false 已注册：true
	 */
	public boolean isExistCard(String searchNumber) {
		
		return false;
	}

	/**
	 * 创建卡号（以139开头 11位）
	 * 
	 * @return 生成的随机手机卡号
	 */
	public String createNumber() {
		Random random = new Random();
		boolean isExist = false; // 记录现有用户中是否存在此卡号用户 是：true 否：false
		String number = "";
		int temp = 0;
		do {
			isExist = false; // 标志位重置为false,用于控制外重循环，当生成了
			// 生成的随机数是8位 不能小于10000000，否则重新生成
			do {
				temp = random.nextInt(999);
			} while (temp < 100);
			// 生成之前，前面加“a”
			number = "a" + temp;
			// 和现有用户的卡号比较，不能是重复
			Set<String> cardNumbers = cards.keySet();
			for (String cardNumber : cardNumbers) {
				if (number.equals(cardNumber)) {
					isExist = true;
					break;
				}
			}
		} while (isExist);
		return number;
	}

	/**
	 * 生成指定个数的新卡号列表
	 * 
	 * @param count
	 *            指定个数
	 * @return 卡号列表
	 */
	public String[] getNewNumbers(int count) {

		String[] numbers = new String[count];
		for (int i = 0; i < count; i++) {
			numbers[i] = createNumber();
		}
		return numbers;
	}

	/**
	 * 添加新卡s
	 * 
	 * @param card
	 *            新卡
	 */
	public void addCard(VipCard card) {

		cards.put(card.getCardNumber(), card);
		System.out.print("注册成功！");
		card.showMeg();
	}

	/**
	 * 指定卡号办理退卡
	 * 
	 * @param card
	 */
	public void delCard(String delNumber) {
		
	}

	/**
	 * 查询指定卡套餐余量
	 * 
	 * @param number
	 */
	public void showRemainDetail(String searchNumber) {
		
	}

	/**
	 * 查询指定卡当月消费详单
	 * 
	 * @param searchNumber
	 */
	public void showAmountDetail(String searchNumber) {
		
	}

	
	/**
	 * 指定卡号换套餐
	 * 
	 * @param number
	 * @param packType
	 */
	public void changingPack(String number, String packNum) {
		
	}

	/**
	 * 为指定手机卡充值
	 * 
	 * @param number
	 *            指定充值的卡号
	 * @param money
	 *            充值金额
	 */
	public void chargeMoney(String number, double money) {
		
	}

	/**
	 * 添加一条指定卡的消费记录
	 * 
	 * @param number
	 *            要添加消费记录的卡
	 * @param info
	 *            要添加的消费记录
	 */
	public void addConsumInfo(String number, ConsumInfo info) {
		
	}
	
	//打印消费记录
	public void printConsumInfo(String number){
		
	}
	
	/**
	 * 使用橙梦
	 * @param number 当前卡号
	 * @throws Exception 
	 */
	public void userSport(String number)  {		
		
	}

	/**
	 * 根据套餐序号返回套餐对象
	 * 
	 * @param packNum
	 *            套餐序号
	 * @return 套餐对象
	 */
	public ServicePackage createPack(int packId) {
		ServicePackage pack = null;
		switch (packId) {
		case 1:
			pack = new SwimPackage();
			break;
		case 2:
			pack = new BallPackage();
			break;
		case 3:
			pack = new GoldPackage();
			break;
		}
		return pack;
	}
	
	/**
	 * 显示资费说明
	 */
	public void showDescription(){
		
	}
	
}
