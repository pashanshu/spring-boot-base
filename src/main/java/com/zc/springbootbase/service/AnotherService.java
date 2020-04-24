package com.zc.springbootbase.service;

import com.zc.springbootbase.model.Access;

import java.util.List;

/**
 * @author zhanchang
 * @version 1.0
 * @date 2020/4/9 17:56
 */
public interface AnotherService {
    public List<Access> queryAll();
    public Access queryByName(String name);
    public int insert(Access access);
}
