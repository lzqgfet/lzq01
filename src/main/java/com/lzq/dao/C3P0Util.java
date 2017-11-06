package com.lzq.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Util {
	private static DataSource ds = new ComboPooledDataSource();

	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				// 回复默认状态
				conn.setAutoCommit(true);
				// 放回到连接池中
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
