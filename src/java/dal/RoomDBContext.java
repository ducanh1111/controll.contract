/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.Room;
import modal.TypeOfRoom;

/**
 *
 * @author ADMIN
 */
public class RoomDBContext extends DBContext {

    public ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            String sql = "select r.rid,r.rname,r.condition,t.id,t.[name],t.price\n"
                    + "from Room r\n"
                    + "inner join TypeOfRoom t on t.id = r.id";
            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                r.setCondition(rs.getBoolean("condition"));
                TypeOfRoom tor = new TypeOfRoom();
                tor.setId(rs.getInt("id"));
                tor.setName(rs.getString("name"));
                tor.setPrice(rs.getDouble("price"));
                r.setTypeofroom(tor);
                rooms.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }

    public ArrayList<Room> search(Boolean condition, String typeofroom, double price, int pagesize, int pageindex) {
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            String c = " ";

            HashMap<Integer, Object[]> params = new HashMap<>();
            int paramIndex = 0;

            if (condition != null) {
                c += "and r.condition = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Boolean.class.getTypeName();
                param[1] = condition;
                params.put(paramIndex, param);
            }
            if (!typeofroom.equals("all")) {
                c += "and t.name like '%'+?+'%'";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = typeofroom;
                params.put(paramIndex, param);
            }
            if (price != -1) {
                c += "and t.price <= ?";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = double.class.getTypeName();
                param[1] = price;
                params.put(paramIndex, param);
            }

            String sql = "select *\n"
                    + "from (select ROW_NUMBER() over (order by rid asc) as stt, r.rid,r.rname,r.condition,t.id,t.[name],t.price\n"
                    + "		from Room r\n"
                    + "		inner join TypeOfRoom t on t.id = r.id "
                    + "where (1=1) " + c + " ) t\n"
                    + "where \n"
                    + "stt between (?-1)*?+1 and ?*?";

            PreparedStatement stm = conn.prepareStatement(sql);
            for (Map.Entry<Integer, Object[]> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if (type.equals(Boolean.class.getName())) {
                    stm.setBoolean(key, (Boolean) value[1]);
                }
                if (type.equals(String.class.getName())) {
                    stm.setString(key, value[1].toString());
                }
                if (type.equals(double.class.getName())) {
                    stm.setDouble(key, (double) value[1]);
                }
            }
            stm.setInt(paramIndex + 1, pageindex);
            stm.setInt(paramIndex + 2, pagesize);
            stm.setInt(paramIndex + 3, pageindex);
            stm.setInt(paramIndex + 4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                r.setCondition(rs.getBoolean("condition"));
                TypeOfRoom tor = new TypeOfRoom();
                tor.setId(rs.getInt("id"));
                tor.setName(rs.getString("name"));
                tor.setPrice(rs.getDouble("price"));
                r.setTypeofroom(tor);
                rooms.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }

    public int count(Boolean condition, String typeofroom, int price) {
        try {
            String sql = "select COUNT(*)\n"
                    + "		from Room r\n"
                    + "		inner join TypeOfRoom t on t.id = r.id "
                    + "where (1=1) ";

            HashMap<Integer, Object[]> params = new HashMap<>();
            int paramIndex = 0;

            if (condition != null) {
                sql += "and r.condition = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Boolean.class.getTypeName();
                param[1] = condition;
                params.put(paramIndex, param);
            }
            if (!typeofroom.equals("all")) {
                sql += "and t.name like '%'+?+'%'";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = typeofroom;
                params.put(paramIndex, param);
            }
            if (price != -1) {
                sql += "and t.price <= ?";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = int.class.getTypeName();
                param[1] = price;
                params.put(paramIndex, param);
            }

            PreparedStatement stm = conn.prepareStatement(sql);
            for (Map.Entry<Integer, Object[]> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if (type.equals(Boolean.class.getName())) {
                    stm.setBoolean(key, (Boolean) value[1]);
                }
                if (type.equals(String.class.getName())) {
                    stm.setString(key, value[1].toString());
                }
                if (type.equals(int.class.getName())) {
                    stm.setInt(key, (int) value[1]);
                }
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Room getRoom(int rid) {
        try {
            String sql = "select r.rid,r.rname,r.condition,t.id,t.[name],t.price from Room r \n"
                    + "inner join TypeOfRoom t on t.id = r.id\n"
                    + "where r.rid = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, rid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                TypeOfRoom tor = new TypeOfRoom();
                tor.setId(rs.getInt("id"));
                tor.setName(rs.getString("name"));
                tor.setPrice(rs.getDouble("price"));
                Room r = new Room();
                r.setRid(rs.getInt("rid"));
                r.setRname(rs.getString("rname"));
                r.setTypeofroom(tor);
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insert(Room room) {
        try {
            String sql = "INSERT INTO [Room]\n"
                    + "           ([rname]\n"
                    + "           ,[condition]\n"
                    + "           ,[id])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, room.getRname());
            stm.setBoolean(2, room.getCondition());
            stm.setInt(3, room.getTypeofroom().getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Room room) {
        try {
            String sql = "UPDATE [Room]\n"
                    + "   SET [rname] = ?\n"
                    + "      ,[id] = ?\n"
                    + " WHERE Room.rid = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, room.getRname());
            stm.setInt(2, room.getTypeofroom().getId());
            stm.setInt(3, room.getRid());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }


    /*public static void main(String[] args) {
        RoomDBContext rdb = new RoomDBContext();
        ArrayList<Room> rooms = rdb.search(true, "all", -1, 10, 1);
        for (Room r : rooms) {
            System.out.println(r.getRname());
        }
    }*/
}
