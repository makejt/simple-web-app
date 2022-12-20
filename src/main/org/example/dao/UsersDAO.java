package org.example.dao;

import org.example.model.User;
import org.example.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

public class UsersDAO extends AbstractDAO<User>{

    @Override
    public boolean insert(User user) {
        return false;
    }
    @Override
    public boolean update(User user) {
        return false;
    }
    @Override
    public boolean delete(User user) {
        return false;
    }
    @Override
    public User getById(User user) {
        return null;
    }
    @Override
    public Set<User> getAll() {
        return null;
    }

    public User getByEmail(String email) {
        Connection connection = DBUtils.getConnection();
        String select = "SELECT * FROM users WHERE email = '" + email + "'";

        Statement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(select);

            if (rs.next()) {
                user = new User();
                user.setEmail(email);
                user.setId(rs.getInt(1));
                user.setName(rs.getString("name"));
                user.setPsw(rs.getString("password"));
            } else {
                System.out.println ("User is not found by email" + email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            DBUtils.release(connection, stmt, null, null);
        }

        return user;
    }
}