package com.zc.springbootbase.service.impl;

import com.zc.springbootbase.config.ExecutorConfig;
import com.zc.springbootbase.dao.AccessMapper;
import com.zc.springbootbase.model.Access;
import com.zc.springbootbase.service.AnotherService;
import com.zc.springbootbase.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Service
public class TestServiceImpl implements TestService {
	private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

	@Resource
	AccessMapper accessMapper;
	@Autowired
	AnotherService anotherServiceImpl;
	
	@Override
	public List<Access> queryAll() {
		// TODO Auto-generated method stub
		Access access =accessMapper.selectByPrimaryKey(1);
		ArrayList<Access> list = new ArrayList<Access>();
		list.add(access);
		return list;
	}

	@Override
	public Access queryByName(String name) {
		if("".equals(name)||name==null){
			return null;
		}
		
		Access access =new Access();
		access.setUsername("zhanchang");
		access.setCreateTime(new Timestamp(System.currentTimeMillis()).toString()); 		
		return access;
	}


	@Override
	@Transactional(transactionManager="transactionManager",rollbackFor = Exception.class)
	public int insert(Access access) {
		// TODO Auto-generated method stub
		Access accessOne = new Access();
		accessOne.setUsername("aaaaone");
		Access accessTwo = new Access();
		accessOne.setUsername("aaaaTwo");
		try {
			anotherServiceImpl.insert(accessOne);
		}catch (Exception e){
			System.out.println("the function is rollback");
		}

		int i= accessMapper.insert(accessTwo);
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * @Description :
	 * @Author: zc
	 * @Date : 2020/4/23 15:14
	*/
	@Override
	@Async("asyncServiceExecutor")
	public void asyncCall(){
		logger.info("start executeAsync");
		try{
			Thread.sleep(10000);
		}catch(Exception e){
			e.printStackTrace();
		}
		logger.info("end executeAsync");
	}

}
