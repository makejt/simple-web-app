package org.example.util;

import java.sql.*;

import static org.example.util.AppConstants.*;

public class DBUtils {
    public static Connection getConnection(String URL) {

        Connection connection = null;

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        return connection;
    }


    public static void release(Connection connection, Statement st, PreparedStatement prst, ResultSet rs) {

        try {

            if (connection != null) {
                connection.close();
            }

            if (st != null) {
                st.close();
            }

            if (prst != null) {
                prst.close();
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