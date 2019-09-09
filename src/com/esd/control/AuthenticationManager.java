package com.esd.control;

import com.esd.pojo.Login;
import java.util.List;





/**
 *
 * @author Admin
 */
public interface AuthenticationManager {
    
    public Login login(String userName, String password) throws RuntimeException;
    public String addUser(Login login);
    public String changePassword(Login login);
    public List<Login> getusers();
    public String deleteUser(String username);
      public String deleteUser2(String username);

}

