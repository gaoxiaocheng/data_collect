package com.machine.record.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gehongzhi
 * @Description 类描述：连接sqlServer工具类
 * @date 2018年8月3日
 */
public class SqlServerDBUtil {


    static {
        try {
            String dbDriverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(dbDriverName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection(String url, String username, String password) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

        return conn;
    }

    public static List<Map<String, Object>> findResult(String sql, List<?> params, String url, String username, String password) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();

        try {
            conn = getConnection(url, username, password);
            ps = conn.prepareStatement(sql, 1004, 1007);
            int index = 1;
            if (params != null && !params.isEmpty()) {
                for (int i = 0; i < params.size(); ++i) {
                    ps.setObject(index++, params.get(i));
                }
            }

            rs = ps.executeQuery();
            Map<String, Object> map = null;
            ResultSetMetaData metaData = rs.getMetaData();
            int columnSize = metaData.getColumnCount();

            while (rs.next()) {
                map = new LinkedHashMap();

                for (int i = 0; i < columnSize; ++i) {
                    String columnName = metaData.getColumnName(i + 1);
                    String columnValue = rs.getString(columnName);
                    if (columnValue == null) {
                        columnValue = "";
                    }

                    map.put(columnName, columnValue);
                }

                list.add(map);
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            colseConn(conn, ps, rs);
        }

        return list;
    }

    public static int executeSql(String sql, String url, String username, String password) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        Object rs = null;

        try {
            conn = getConnection(url, username, password);
            ps = conn.prepareStatement(sql);
            count = ps.executeUpdate();
        } catch (SQLException var9) {
            var9.printStackTrace();
        } finally {
            colseConn(conn, ps, (ResultSet) rs);
        }

        return count;
    }

    public static void colseConn(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

    }


}
