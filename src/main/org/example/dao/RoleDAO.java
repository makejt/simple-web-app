package org.example.dao;

import org.example.model.Role;
import org.example.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static org.example.util.AppConstants.DB_URL;

public class RoleDAO extends AbstractDAO {

    @Override
    public boolean insert(Object o) {
        return false;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public boolean delete(Object o) {
        return false;
    }

    @Override
    public Object getById(Object o) {
        return null;
    }

    @Override
    public Set<Role> getAll() {
        Set<Role> roles = new HashSet<Role>();
        Role role = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;

        String selectAll = "SELECT * FROM roles";

        try {
            connection = DBUtils.getConnection(DB_URL);
            st = connection.createStatement();
            rs = st.executeQuery(selectAll);

            while (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("descr"));
                roles.add(role);
            }
        } catch (RuntimeException exception) {
            System.out.println(exception);
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            DBUtils.release(connection, st, null, rs);
        }
        return roles;
    }

    public Set<Role> getRolesByUserId(int id) {
        Set<Role> roles = new HashSet<Role>();
        Role role = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;

        String selectAll = "SELECT * FROM roles WHERE roles.id IN (SELECT role_id FROM users_roles WHERE user_id = '" + id + "')";

        try {
            connection = DBUtils.getConnection(DB_URL);
            st = connection.createStatement();
            rs = st.executeQuery(selectAll);

            while (rs.next()) {
                role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
                role.setDescription(rs.getString("descr"));
                roles.add(role);
            }
        } catch (RuntimeException exception) {
            System.out.println(exception);
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            DBUtils.release(connection, st, null, rs);
        }
        return roles;
    }
}