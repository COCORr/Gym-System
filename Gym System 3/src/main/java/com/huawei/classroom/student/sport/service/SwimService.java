package com.huawei.classroom.student.sport.service;

import com.huawei.classroom.student.sport.entity.VipCard;

/**
 * 游泳服务
 * @author 
 *
 */
public interface SwimService {
	//游泳
      public int swim(int minCount,VipCard card) throws Exception;
}
