package com.huawei.classroom.student.sport.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.huawei.classroom.student.sport.service.BallService;
import com.huawei.classroom.student.sport.service.SwimService;
import com.huawei.classroom.student.sport.service.WaterService;
/**
 * 会员卡工具类
 * 
 * @author 
 * 
 */
public class CardUtil {
	Map<String, VipCard> cards = new HashMap<String, VipCard>(); // 所有手机卡的列表
	Map<String, List<ConsumInfo>> consumInfos = new HashMap<String, List<ConsumInfo>>(); // 所有手机卡消费记录的列表
	// 方便测试增加set，get方法
	public Map<String, List<ConsumInfo>> getConsumInfos() {
		return consumInfos;
	}

	public void setConsumInfos(Map<String, List<ConsumInfo>> consumInfos) {
		this.consumInfos = consumInfos;
	}

	List<Scene> scenes = new ArrayList<Scene>();
	
	private int typeNum;

	public int getTypeNum() {
		return typeNum;
	}

	public void setTypeNum(int typeNum) {
		this.typeNum = typeNum;
	}

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
		Set<String> numbers = cards.keySet();
		Iterator<String> it = numbers.iterator();
		while (it.hasNext()) {
			String searchNum = it.next();
			if (searchNum.equals(number)
					&& (cards.get(searchNum)).getPassWord().equals(passWord)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 查找指定卡号是否已注册
	 * 
	 * @param searchNumber
	 * @return 未注册：false 已注册：true
	 */
	public boolean isExistCard(String searchNumber) {

		Set<String> numbers = cards.keySet();
		Iterator<String> it = numbers.iterator();
		while (it.hasNext()) {
			String number = it.next();
			if (searchNumber.equals(number)) {
				return true;
			}
		}
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
	 * 添加新卡
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
		Set<String> numbers = consumInfos.keySet();
		Iterator<String> it = numbers.iterator();
		List<ConsumInfo> infos = new ArrayList<ConsumInfo>();
		boolean isExist = false; // 现有消费列表中是否存在此卡号消费记录，是：true 否：false
		while (it.hasNext()) {
			if (it.next().equals(number)) {
				// 消费集合中已有该卡号消费记录，则找到该卡号的消费记录集合，添加一条即可
				infos = consumInfos.get(number);
				infos.add(info);
				isExist = true;
				System.out.println("已添加一条消费记录。");
				break;
			}
		}
		// 该集合中没有此卡号消费记录，则添加
		if (!isExist) {
			infos.add(info);
			consumInfos.put(number, infos);
			System.out.println("不存在此卡的消费记录，已添加一条消费记录。");
		}
		// 方便测试，增加消费信息的设置
//		this.setConsumInfos(consumInfos);
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
		VipCard card = cards.get(number); // 获取此卡对象
		ServicePackage pack = card.getSerPackage(); // 获取此卡所属套餐
		Random random = new Random();
		int ranNum = 0;
		int temp = 0;  //记录各场景中实际消费数据
		do{
			
			ranNum = random.nextInt(6);// 生成一个0~5之前的随机数
			this.setTypeNum(ranNum);
			Scene scene = scenes.get(ranNum); //获取该序号所对应的场景
			switch (ranNum) { 
			//序号为0或1为通话场景
			case 0:
			case 1:
				// 判断该卡所属套餐是否支持游泳功能
				if (pack instanceof SwimService) {
					// 执行游泳方法
					System.out.println(scene.getDescription());
					SwimService callService = (SwimService) pack;
					try {
						temp = callService.swim(scene.getData(), card);
					} catch (Exception e) {							
						e.printStackTrace();
					}
					// 添加一条消费记录
					
					addConsumInfo(number, new ConsumInfo(number,scene.getType(), temp));
					
					break;
				} else {
					// 如果该卡套餐不支持游泳功能，则重新生成随机数选择其他场景
					continue;
				}
				//序号为2或3为矿泉水场景
			case 2:
			case 3:
				// 判断该卡所属套餐是否支持矿泉水功能
				if (pack instanceof WaterService) {
					// 执行发短信方法
					System.out.println(scene.getDescription());
					WaterService sendService = (WaterService) pack;
					try {
						temp = sendService.water(scene.getData(), card);
					} catch (Exception e) {													
						e.printStackTrace();
					}
					// 添加一条消费记录
					addConsumInfo(number, new ConsumInfo(number,
							scene.getType(), temp));
					break;
				} else {
					// 如果该卡套餐不支持矿泉水功能，则重新生成随机数选择其他场景
					continue;
				}
				//序号为4或5为发羽毛球场景
			case 4:
			case 5:
				// 判断该卡所属套餐是否支持羽毛球功能
				if (pack instanceof BallService) { 
					System.out.println(scene.getDescription());
					BallService netService = (BallService) pack;
					// 执行羽毛球方法
					try {
						temp = netService.ballPlay(scene.getData(), card);
					} catch (Exception e) {						
						e.printStackTrace();
					}
					// 添加一条消费记录
					addConsumInfo(number, new ConsumInfo(number,
							scene.getType(), temp));
					break;
				} else {
					// 如果该卡套餐不支持羽毛球功能，则重新生成随机数选择其他场景
					continue;
				}				
			}	
			break;
		}while(true);
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
