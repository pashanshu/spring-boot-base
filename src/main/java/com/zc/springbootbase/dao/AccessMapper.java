package com.zc.springbootbase.dao;

import com.zc.springbootbase.model.Access;
import com.zc.springbootbase.model.AccessExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccessMapper {
    int countByExample(AccessExample example);

    int deleteByExample(AccessExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Access record);

    int insertSelective(Access record);

    List<Access> selectByExample(AccessExample example);

    Access selectByPrimaryKey(Integer id);
    
    Access selectPaging(Integer id);

    int updateByExampleSelective(@Param("record") Access record, @Param("example") AccessExample example);

    int updateByExample(@Param("record") Access record, @Param("example") AccessExample example);

    int updateByPrimaryKeySelective(Access record);

    int updateByPrimaryKey(Access record);
}