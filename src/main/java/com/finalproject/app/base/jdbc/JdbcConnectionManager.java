package com.finalproject.app.base.jdbc;

import java.sql.Connection;

public interface JdbcConnectionManager {
	public Connection getConnection();
	public void closeConnection();
}
