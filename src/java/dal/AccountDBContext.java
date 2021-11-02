/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.Account;
import modal.Feature;

/**
 *
 * @author ADMIN
 */
public class AccountDBContext extends DBContext {

    public Account getAccount(String username, String password) {
        try {
            String sql = "select a.username, a.password, a.displayname, f.fid, f.[url]\n"
                    + "from Account a\n"
                    + "left join RoleAccount ra on ra.username = a.username\n"
                    + "left join [Role] r on r.roleid = ra.roleid\n"
                    + "left join RoleFeature rf on rf.roleid = r.roleid\n"
                    + "left join Feature f on f.fid = rf.fid\n"
                    + "where a.username = ? and a.[password] = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            Account a = null;
            while (rs.next()) {                
                if(a == null){
                    a = new Account();
                    a.setUsername(rs.getString("username"));
                    a.setPassword(rs.getString("password"));
                    a.setDisplayname(rs.getString("displayname"));
                }
                int fid = rs.getInt("fid");
                if(fid != 0){
                    Feature f = new Feature();
                    f.setFid(fid);
                    f.setUrl(rs.getString("url"));
                    a.getFeatures().add(f);
                }
            }
            return a;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
