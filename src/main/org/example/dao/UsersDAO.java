package org.example.dao;

import org.example.model.User;
import org.example.util.DBUtils;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import static org.example.util.AppConstants.DB_URL;
public class UsersDAO extends AbstractDAO<User>{
    @Override
    public boolean insert(User user) {

        String sql = "INSERT INTO user (name, email, password) VALUES (?, ?, ?)";

        try(Connection connection = DBUtils.getConnection(DB_URL);
            PreparedStatement pstmt = connection.prepareStatement(sql);) {

          pstmt.setString(1, user.getName());
          pstmt.setString(2, user.getEmail());
          pstmt.setString(3, user.getPsw());

          if (pstmt.executeUpdate() == 1) {
              System.out.println("User was added in DB successfully");
              return true;
          }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
        Set<User> users = new HashSet<User>();
        User user = null;
        Statement st = null;
        ResultSet rs = null;

        Connection connection = null;

        String selectAll = "SELECT user.id, user.name, email, password, is_active, created_ts, updated_ts, " +
                "offices.id as office_id FROM user JOIN offices ON id_office = offices.id";

        try {
            connection = DBUtils.getConnection(DB_URL);
            st = connection.createStatement();
            rs = st.executeQuery(selectAll);

            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPsw(rs.getString("password"));
                user.setOffice(new OfficeDAO().getOfficeById(rs.getInt("office_id")));
                user.set_active(rs.getString("is_active").equalsIgnoreCase("Y"));
                user.setCreatedTs(rs.getTimestamp("created_ts"));
                user.setUpdateTs(rs.getTimestamp("updated_ts"));
                user.setRoles(new RoleDAO().getRolesByUserId(rs.getInt("id")));
                users.add(user);
            }
        }
        catch (RuntimeException exception) {
            System.out.println(exception);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        finally {
            DBUtils.release(connection, st, null, rs);
        }
        return users;
    }
    public User getByEmail(String email) {
        User user = null;
        Statement st = null;
        ResultSet rs = null;

        Connection connection = DBUtils.getConnection(DB_URL);

        String select = "SELECT * FROM user WHERE email = '" + email + "'";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);

            if (rs.next()) {
                user = new User();
                user.setEmail(email);
                user.setId(rs.getInt(1));
                user.setName(rs.getString("name"));
                user.setPsw(rs.getString("password"));
            } else {
                System.out.println ("User is not found by email " + email);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DBUtils.release(connection, st, null, rs);
        }
        return user;
    }
}