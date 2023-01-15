package org.example.dao;

import org.example.model.Region;
import org.example.model.User;
import org.example.util.DBUtils;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static org.example.util.AppConstants.DB_URL1;

public class RegionDAO extends AbstractDAO<Region>{

    @Override
    public boolean insert(Region region) {

        String sql = "INSERT INTO regions (name, population, square) VALUES (?, ?, ?)";

        try(Connection connection = DBUtils.getConnection(DB_URL1);
            PreparedStatement pstmt = connection.prepareStatement(sql);) {

            pstmt.setString(1, region.getName());
            pstmt.setInt(2, region.getPopulation());
            pstmt.setInt(3, region.getSquare());

            if (pstmt.executeUpdate() == 1) {
                System.out.println("Region was added in DB successfully");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean update(Region region) {
        return false;
    }

    @Override
    public Region getById(Region region) {
        return null;
    }

    @Override
    public boolean delete(Region region) {
        return false;
    }

    public Region getById(int id) {
        Statement st = null;
        ResultSet rs = null;
        Region region = null;

        Connection connection = DBUtils.getConnection(DB_URL1);

        String select = "SELECT * FROM region WHERE id = '" + id + "'";

        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);

            if (rs.next()) {
                region = new Region();
                region.setId(id);
                region.setName(rs.getString("name"));
                region.setPopulation(rs.getInt("population"));
                region.setSquare(rs.getInt("square"));
            } else {
                System.out.println ("Region is not found by id " + id);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            DBUtils.release(connection, st, null, rs);
        }
        return region;

    }
    @Override
    public Set<Region> getAll() {
        Set<Region> regions = new HashSet<Region>();
        Region region = null;
        Statement st = null;
        ResultSet rs = null;
        Connection connection = null;

        String selectAll = "SELECT * FROM region";

        try {
            connection = DBUtils.getConnection(DB_URL1);
            st = connection.createStatement();
            rs = st.executeQuery(selectAll);

            while (rs.next()) {
               region = new Region();
                region.setId(rs.getInt("id"));
                region.setName(rs.getString("name"));
                region.setPopulation(rs.getInt("population"));
                region.setSquare(rs.getInt("square"));
                regions.add(region);
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
        return regions;
    }

    public static void main(String[] args) throws Exception{

        RegionDAO regionDAO = new RegionDAO();
        System.out.println(regionDAO.getAll().toString());

    }


}