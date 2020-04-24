package com.zc.springbootbase.service;

import com.zc.springbootbase.model.Access;

import java.util.List;

public interface TestService {
    public List<Access> queryAll();
    public Access queryByName(String name);
    public int insert(Access access);
    public void asyncCall();
}
