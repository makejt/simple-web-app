package org.example.util;

import java.sql.*;

import static org.example.util.AppConstants.*;

public class DBUtils {
    public static Connection getConnection() {

        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void release(Connection connection, Statement stnt, PreparedStatement pstnt,
                       ResultSet rs) {
        try {
            if (connection != null) {
                connection.close();
            }
            if (stnt != null) {
                stnt.close();
            }
            if (pstnt != null) {
                pstnt.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
}