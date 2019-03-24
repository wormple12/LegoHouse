/*
 */
package com.mycompany.legohouse.data.SQL_Impl;

import com.mycompany.legohouse.data.DataAccessException;
import com.mycompany.legohouse.data.UserDAO;
import com.mycompany.legohouse.logic.help_classes.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO_SQL implements UserDAO {

    @Override
    public User getUser(String username, String password) throws DataAccessException {
        try {
            Connection c = DBConnector.getConnection();
                    
            String query = "SELECT * FROM users "
                    + "WHERE username = ? "
                    + "AND password = ?;";
            PreparedStatement stmt = c.prepareStatement( query );
            stmt.setString( 1, username );
            stmt.setString( 2, password );
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String email = rs.getString("email");
                int admin = rs.getInt("admin");
                return new User(username, password, email, (admin == 1));
            } else {
                throw new SQLException("The username or password you entered is not correct.");
            }
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

    @Override
    public void registerUser(User user) throws DataAccessException {
        try {
            Connection c = DBConnector.getConnection();
            PreparedStatement preparedStmt;
            String query
                    = " insert into users (username, password, email, admin) VALUES(?,?,?,?)";
            preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, user.getUsername());
            preparedStmt.setString(2, user.getPassword());
            preparedStmt.setString(3, user.getEmail());
            preparedStmt.setBoolean(4, user.getAdmin());
            preparedStmt.execute();

            preparedStmt.close();
        } catch (SQLException ex) {
            throw new DataAccessException(ex);
        }
    }

}
