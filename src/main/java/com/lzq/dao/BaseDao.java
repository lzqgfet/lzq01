package com.lzq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao {
	// private static String url = "jdbc:mysql://localhost:3306/" +
	// "javaweb?useUnicode=true&characterEncoding=utf-8";
	// private static String user = "root";
	// private static String password = "root";

	public static Connection getConnection() {
		Connection conn = null;
		/*
		 * try { Class.forName("com.mysql.jdbc.Driver"); conn =
		 * DriverManager.getConnection(url, user, password); } catch
		 * (ClassNotFoundException e) { e.printStackTrace(); } catch
		 * (SQLException e) { e.printStackTrace(); }
		 */
		conn = C3P0Util.getConnection();
		return conn;
	}

	public static Statement getStatement(Connection conn) {
		Statement stat = null;
		try {
			stat = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stat;
	}

	/*
	 * 数据库数据添加insert、删除delete、修改update
	 */
	public static int executeUpdate(String sql) {
		Connection conn = getConnection();
		Statement stat = getStatement(conn);
		int n = -1;
		try {
			n = stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stat);
		}

		return n;
	}

	/*
	 * 数据库数据查询select
	 */
	public static List<Map<String, Object>> executeQuery(String sql) {
		Connection conn = getConnection();
		Statement stat = getStatement(conn);
		ResultSet rs = null;
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			rs = stat.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= count; i++) {
					String colname = rsmd.getColumnName(i);
					Object value = rs.getObject(colname);
					map.put(colname, value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(conn, stat, rs);
		}
		return list;

	}

	/*
	 * ִ执行多条操作（ 添加insert、删除delete、修改update）
	 */
	public static int[] executeBatch(List<String> list) {
		Connection conn = BaseDao.getConnection();
		Statement stat = BaseDao.getStatement(conn);
		for (String sql : list) {
			try {
				stat.addBatch(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		int[] arr = null;
		try {
			arr = stat.executeBatch();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, stat);
		}
		return arr;
	}

	public static void close(Connection conn, Statement stat) {
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				C3P0Util.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn, Statement stat, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				C3P0Util.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn, PreparedStatement prep, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (prep != null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				C3P0Util.close(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
