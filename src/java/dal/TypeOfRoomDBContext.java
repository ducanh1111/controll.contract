/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.TypeOfRoom;

/**
 *
 * @author ADMIN
 */
public class TypeOfRoomDBContext extends DBContext {

    public ArrayList<TypeOfRoom> getTypes() {
        ArrayList<TypeOfRoom> list = new ArrayList<>();
        try {
            String sql = "select * from typeofroom";

            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TypeOfRoom tor = new TypeOfRoom();
                tor.setId(rs.getInt(1));
                tor.setName(rs.getString(2));
                tor.setPrice(rs.getDouble(3));
                list.add(tor);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TypeOfRoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    /*public static void main(String[] args) {
        TypeOfRoomDBContext t = new TypeOfRoomDBContext();
        ArrayList<TypeOfRoom> list = t.getStudents();
        for (TypeOfRoom ty : list) {
            System.out.println(ty.getName());
        }
    }*/

}
