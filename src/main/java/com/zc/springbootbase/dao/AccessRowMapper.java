package com.zc.springbootbase.dao;

import com.zc.springbootbase.model.Access;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccessRowMapper implements RowMapper<Access> {

	@Override
	public Access mapRow(ResultSet rs, int rowNum) throws SQLException {
		Access access = new Access();
		access.setId(rs.getInt("id"));
		access.setUsername(rs.getString("username"));
		access.setShopName(rs.getString("shop_name"));
		return access;
	}

}
