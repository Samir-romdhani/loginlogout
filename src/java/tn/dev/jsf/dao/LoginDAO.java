/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.dev.jsf.dao;

/**
 *
 * @author user
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tn.dev.jsf.util.DataConnect;

public class LoginDAO {

    public static boolean validate(String user, String password) {
        //Connection con = null;
        //PreparedStatement ps = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DataConnect.getConnection();
            String ordre_sql = "SELECT uname, password FROM users2 WHERE uname = ? AND password = ?";

            //ps = con.prepareStatement("Select uname, password from users2 where uname = ? and password = ?");
            ps = con.prepareStatement(ordre_sql);
            ps.setString(1, user);
            ps.setString(2, password);

            rs = ps.executeQuery(ordre_sql);

            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
}

