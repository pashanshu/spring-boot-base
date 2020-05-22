package com.zc.springbootbase.service.impl;

import com.zc.springbootbase.service.JdbcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author zhanchang
 * @version 1.0
 * @date 2020/5/13 10:38
 */
@Service
@Slf4j
public class JdbcServiceImpl implements JdbcService {

    @Override
    public void getFromDb() {
      int i =1;
      i =2;
    }
}
