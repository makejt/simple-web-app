package org.example.dao;

import org.example.model.Office;
import org.example.util.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import static org.example.util.AppConstants.DB_URL;
public class OfficeDAO extends AbstractDAO{
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
    public Office getById(Object o) {


        return null;
    }
    public Office getOfficeById (int id) {
        Office office = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;

        String selectAll = "SELECT * FROM offices WHERE id = '" + id + "'";

        try {
            connection = DBUtils.getConnection(DB_URL);
            st = connection.createStatement();
            rs = st.executeQuery(selectAll);

            while (rs.next()) {
                office = new Office();
                office.setId(rs.getInt("id"));
                office.setName(rs.getString("name"));
                office.setLocation(rs.getString("location"));
                office.setPhone(rs.getString("phone"));
                office.setFax(rs.getString("fax"));
            }
        } catch (RuntimeException exception) {
            System.out.println(exception);
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            DBUtils.release(connection, st, null, rs);
        }
        return office;
    }

    @Override
    public Set<Office> getAll() {
        Set<Office> offices = new HashSet<Office>();
        Office office = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;

        String selectAll = "SELECT * FROM offices";

        try {
            connection = DBUtils.getConnection(DB_URL);
            st = connection.createStatement();
            rs = st.executeQuery(selectAll);

            while (rs.next()) {
                office = new Office();
                office.setId(rs.getInt("id"));
                office.setName(rs.getString("name"));
                office.setLocation(rs.getString("location"));
                office.setPhone(rs.getString("phone"));
                office.setFax(rs.getString("fax"));
                offices.add(office);
            }
        } catch (RuntimeException exception) {
            System.out.println(exception);
        } catch (SQLException exception) {
            System.out.println(exception);
        } finally {
            DBUtils.release(connection, st, null, rs);
        }
        return offices;
    }
}