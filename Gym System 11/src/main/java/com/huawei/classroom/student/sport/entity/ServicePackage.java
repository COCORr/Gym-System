package com.huawei.classroom.student.sport.entity;
/**
 * 橙梦体育馆套餐
 * @author 
 *
 */
public abstract class ServicePackage {	
	protected double price;  //套餐月资费(元)
     
    public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	//显示套餐数据
    public abstract void showInfo();
}

