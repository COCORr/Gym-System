package com.huawei.classroom.student.sport.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.huawei.classroom.student.sport.common.Common;
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
		for (String number : numbers) {
			if (number.equals(searchNumber)) {
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
		VipCard card; // 要查询的卡
		int remainSwimTime;
		int remainwaterNum;
		int remainBallTime;
		StringBuffer meg = new StringBuffer();
			card = cards.get(searchNumber);
			meg.append("您的卡号是" + searchNumber + "，套餐内剩余：\n");
			ServicePackage pack = card.getSerPackage();
			if (pack instanceof SwimPackage) {
				//向下转型为游泳套餐对象
				SwimPackage cardPack = (SwimPackage) pack;
				// 游泳套餐，查询套餐内剩余的通话时长和短信条数
				remainSwimTime = cardPack.getswimTime() > card
						.getrealSwimTime() ? cardPack.getswimTime()
						- card.getrealSwimTime() : 0;
				meg.append("游泳时长：" + remainSwimTime + "小时\n");
				remainwaterNum = cardPack.getwaterNum() > card
						.getrealWaterNum() ? cardPack.getwaterNum()
						- card.getrealWaterNum() : 0;
				meg.append("矿泉水数量：" + remainwaterNum + "瓶");
			} else if (pack instanceof BallPackage) {
				//向下转型为羽毛球套餐对象
				BallPackage cardPack = (BallPackage) pack;
				// 羽毛球套餐：查询套餐内剩余的羽毛球时间
				remainBallTime = cardPack.getballTime() > card.getrealBallTime() ? cardPack
						.getballTime() - card.getrealBallTime() : 0;
				meg.append("羽毛球时长：" +remainBallTime + "小时");
				remainwaterNum = cardPack.getwaterNum() > card
						.getrealWaterNum() ? cardPack.getwaterNum()
						- card.getrealWaterNum() : 0;
				meg.append("矿泉水数：" + remainwaterNum + "瓶");
			} else if (pack instanceof GoldPackage) {
				//向下转型为黄金套餐对象
				GoldPackage cardPack = (GoldPackage) pack;
				// 黄金套餐：查询套餐内剩余的游泳时间、羽毛球时间、矿泉水数
				remainSwimTime = cardPack.getswimTime() > card
						.getrealSwimTime() ? cardPack.getswimTime()
						- card.getrealSwimTime() : 0;
				meg.append("游泳时长：" + remainSwimTime + "小时\n");
				remainwaterNum = cardPack.getwaterNum()> card
						.getrealWaterNum() ? cardPack.getwaterNum()
						- card.getrealWaterNum() : 0;
				meg.append("矿泉水数：" + remainwaterNum + "瓶\n");
				remainBallTime = cardPack.getballTime() > card.getrealBallTime() ? cardPack
						.getballTime() - card.getrealBallTime() : 0;
				meg.append("羽毛球时长：" + remainBallTime + "小时");
			}
			System.out.println(meg);
	}

	/**
	 * 查询指定卡当月消费详单
	 * 
	 * @param searchNumber
	 */
	public void showAmountDetail(String searchNumber) {
		VipCard card; // 要查询的卡
		StringBuffer meg = new StringBuffer();
		card = cards.get(searchNumber);
		meg.append("您的卡号：" + card.getCardNumber() + ",当月账单：\n");
		meg.append("套餐资费：" + card.getSerPackage().getPrice() + "元\n");
		meg.append("合计：" + Common.dataFormat(card.getConsumAmount()) + "元\n");
		meg.append("账户余额：" + Common.dataFormat(card.getMoney()) + "元");
		// 显示本月消费详细信息
		System.out.println(meg);
	}

	
	/**
	 * 指定卡号换套餐
	 * 
	 * @param number
	 * @param packType
	 */
	public void changingPack(String number, String packNum) {
		VipCard card; // 指定的手机卡
		ServicePackage pack; // 要换的套餐
		if (isExistCard(number)) {
			card = cards.get(number);
			// 获取要换的套餐对象
			switch (packNum) {
			case "1":
				pack = new SwimPackage();
				break;
			case "2":
				pack = new BallPackage();
				break;
			default:
				pack = new GoldPackage();
				break;
			}		
			if (!(card.getSerPackage().getClass().getName().equals(pack.getClass().getName()))) {
				// 该卡余额中减去当月套餐资费
				
				
				if (card.getMoney() >= pack.getPrice()) {
					
					if (card.getMoney() >= pack.getPrice()) {
						card.setMoney(card.getMoney() - pack.getPrice());
						// 换套餐
						card.setSerPackage(pack);
						// 当月实际使用数据清零
						card.setrealBallTime(0);
						card.setrealSwimTime(0);
						card.setrealWaterNum(0);
						// 当月消费金额设置为新套餐月资费
						card.setConsumAmount(pack.getPrice());
					System.out.print("更换套餐成功！");
					pack.showInfo();
				} else {
					System.out.println("对不起，您的余额不足以支付新套餐本月资费，请充值后再办理更换套餐业务！");
					return;
				}
			} else {
				System.out.println("对不起，您已经是该套餐用户，无需换套餐！");
			}

		} else {
			System.out.println("对不起，该卡号未注册，不能换套餐！");
		}
		}
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
		VipCard card; // 指定的手机卡
		if (money < 50) {
			System.out.println("对不起，最低充值金额为50元！");
			return;
		}
			card = cards.get(number);
			card.setMoney(card.getMoney() + money);
			System.out.println("充值成功，当前话费余额为" + Common.dataFormat(card.getMoney()) + "元。");
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
	}
	
	//打印消费记录
	public void printConsumInfo(String number){
		Writer fileWriter = null;
		try {
			 fileWriter = new FileWriter(number+"消费记录.txt");			
			Set<String> numbers = consumInfos.keySet();
			Iterator<String> it = numbers.iterator();
			List<ConsumInfo> infos = new ArrayList<ConsumInfo>();//存储指定卡所有消费记录
			boolean isExist = false; // 现有消费列表中是否存在此卡号消费记录，是：true 否：false
			while (it.hasNext()) {
				if (it.next().equals(number)) {
					infos = consumInfos.get(number);
					isExist = true;
					break;
				}
			}
			if(isExist){
				//存在 此卡消费记录，写入文本文件
				StringBuffer content = new StringBuffer("******"+number+"消费记录******\n");
				content.append("序号\t类型\t数据（游泳（小时）/羽毛球（小时）/矿泉水（瓶））\n");
				for(int i=0;i<infos.size();i++){
					ConsumInfo info = infos.get(i);
					content.append((i+1)+".\t"+info.getType()+"\t"+info.getConsumData()+"\n");
				}
				fileWriter.write(content.toString());
				fileWriter.flush();
				
				System.out.println("消费记录打印完毕！");
			}else{
				System.out.println("对不起，不存在此号码的消费记录，不能打印！");
			}			
		} catch (IOException e) {			
			e.printStackTrace();
		}finally{
			if(fileWriter!=null){
				try {
					fileWriter.close();
				} catch (IOException e) {					
					e.printStackTrace();
				}
			}
		}
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
					addConsumInfo(number, new ConsumInfo(number,
							scene.getType(), temp));
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
		Reader rd = null;
		try {
			rd = new FileReader("套餐资费说明.txt");
			 char[] content = new char[1024];
			 int len = 0;
			 StringBuffer sb = new StringBuffer();
				while((len=rd.read(content))!=-1){
					sb.append(content,0,len);  //拼接字符串
				}
				System.out.println(sb);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

