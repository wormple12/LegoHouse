/*
 */

package com.mycompany.legohouse.data;

import com.mycompany.legohouse.logic.help_classes.User;

/**
 * @author Simon Asholt Norup
 */
public interface UserDAO {
    
    public User getUser(String username, String password) throws DataAccessException;
    
    public void registerUser(User user) throws DataAccessException;

}
