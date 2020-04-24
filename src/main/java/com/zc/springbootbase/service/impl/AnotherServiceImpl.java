package com.zc.springbootbase.service.impl;

import com.zc.springbootbase.dao.AccessMapper;
import com.zc.springbootbase.model.Access;
import com.zc.springbootbase.service.AnotherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhanchang
 * @version 1.0
 * @date 2020/4/9 17:57
 */

@Service

public class AnotherServiceImpl implements AnotherService {

    @Resource
    public AccessMapper accessMapper;

    @Override
    public List<Access> queryAll() {
        return null;
    }

    @Override
    public Access queryByName(String name) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public int insert(Access access) {
        return accessMapper.insert(access);
    }
}
