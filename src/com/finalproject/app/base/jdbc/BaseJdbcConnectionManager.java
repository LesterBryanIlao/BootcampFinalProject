package com.finalproject.app.base.jdbc;

import java.sql.Connection;

public interface BaseJdbcConnectionManager {
	public Connection getConnection();
	public void closeConnection();
}
