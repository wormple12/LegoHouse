/*
 */
package com.mycompany.legohouse.data.SQL_Impl;

import com.mycompany.legohouse.data.DataAccessException;
import com.mycompany.legohouse.data.OrderDAO;
import com.mycompany.legohouse.logic.help_classes.Brick;
import com.mycompany.legohouse.logic.help_classes.House;
import com.mycompany.legohouse.logic.help_classes.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderDAO_SQL implements OrderDAO {

    @Override
    public void addOrder(Order order) throws DataAccessException {
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement preparedStmt;
            String query
                    = "insert into orders (width, length, height, ordered, user_id) VALUES(?,?,?,?,?)";
            preparedStmt = c.prepareStatement(query);

            House house = order.getHouse();
            preparedStmt.setInt(1, house.getWidth());
            preparedStmt.setInt(2, house.getLength());
            preparedStmt.setInt(3, house.getHeight());
            preparedStmt.setObject(4, order.getOrdered(), java.sql.JDBCType.TIMESTAMP);
            int user_id = getUserID(order.getCustomer());
            preparedStmt.setInt(5, user_id);
            preparedStmt.execute();

            preparedStmt.close();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public ArrayList<Order> getUserOrders(String username) throws DataAccessException {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection c = DBConnector.getConnection();
            try (Statement stmt = c.createStatement()) {

                String query = "select orders.* from orders "
                        + "natural join users "
                        + "where username = '" + username + "';";
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    Order order = getOrder(username, rs);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return orders;
    }

    @Override
    public ArrayList<Order> getAllOrders() throws DataAccessException {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            Connection c = DBConnector.getConnection();
            try (Statement stmt = c.createStatement()) {
                String query = "select username, orders.* from orders "
                        + "natural join users;";
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String username = rs.getString("username");
                    Order order = getOrder(username, rs);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return orders;
    }

    private Order getOrder(String username, ResultSet rs) throws SQLException {
        int id = rs.getInt("order_id");
        int width = rs.getInt("width");
        int length = rs.getInt("length");
        int height = rs.getInt("height");
        House house = new House(width, length, height);
        LocalDateTime ordered = rs.getObject("ordered", LocalDateTime.class);
        LocalDateTime shipped = rs.getObject("shipped", LocalDateTime.class);
        Order order = new Order(id, username, house, ordered, shipped);
        return order;
    }

    @Override
    public void shipOrder(int id) throws DataAccessException {
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement preparedStmt;
            String query
                    = "UPDATE orders SET shipped=? WHERE order_id=?;";
            preparedStmt = c.prepareStatement(query);

            preparedStmt.setObject(1, LocalDateTime.now(), java.sql.JDBCType.TIMESTAMP);
            preparedStmt.setInt(2, id);
            preparedStmt.execute();

            preparedStmt.close();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public ArrayList<Brick> getValidBrickTypes() throws DataAccessException {
        ArrayList<Brick> types = new ArrayList<>();
        try {
            Connection c = DBConnector.getConnection();
            try (Statement stmt = c.createStatement()) {
                String query = "SELECT * FROM valid_brick_types;";
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int width = rs.getInt("width");
                    int length = rs.getInt("length");
                    types.add(new Brick(width, length));
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
        return types;
    }

    private int getUserID(String username) throws SQLException {
        Connection c = DBConnector.getConnection();
        try (Statement stmt = c.createStatement()) {

            String query = "select user_id from users where username = '" + username + "';";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                return rs.getInt("user_id");
            }
        }
        throw new IllegalArgumentException();
    }

}
