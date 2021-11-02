/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modal.Contract;
import modal.Room;
import modal.Student;
import modal.TypeOfRoom;

/**
 *
 * @author ADMIN
 */
public class ContractDBContext extends DBContext {

    public ArrayList<Contract> search(String sname, Date from, Date to, int pagesize, int pageindex) {
        ArrayList<Contract> contracts = new ArrayList<>();
        try {
            String c = " ";
            HashMap<Integer, Object[]> params = new HashMap<>();
            int paramIndex = 0;

            if (sname != null) {
                c += "and s.sname like '%'+?+'%'";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = sname;
                params.put(paramIndex, param);
            }
            if (from != null) {
                c += "AND and c.[from] >= ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Date.class.getTypeName();
                param[1] = from;
                params.put(paramIndex, param);
            }
            if (to != null) {
                c += "AND and c.[from] <= ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Date.class.getTypeName();
                param[1] = to;
                params.put(paramIndex, param);
            }
            String sql = "select * from (select ROW_NUMBER() over (order by c.id asc) as stt,c.id,('HD '+r.rname) as [name], s.sname,s.phonenumber, s.cardnumber,s.address,s.dob,s.gender,tor.price,tor.[name] as type, c.deposit, c.[from]\n"
                    + "from [Contract] c\n"
                    + "inner join [Student] s on s.sid = c.sid\n"
                    + "inner join [Room] r on r.rid = c.rid\n"
                    + "inner join TypeOfRoom tor on tor.id = r.id\n"
                    + "where (1=1)" + c + ") ctr \n"
                    + "where ctr.stt between (?-1)*?+1 and ?*?";
            PreparedStatement stm = conn.prepareStatement(sql);
            for (Map.Entry<Integer, Object[]> entry : params.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if (type.equals(String.class.getName())) {
                    stm.setString(index, value[1].toString());
                }
                if (type.equals(Date.class.getName())) {
                    stm.setDate(index, (Date) value[1]);
                }
            }
            stm.setInt(paramIndex + 1, pageindex);
            stm.setInt(paramIndex + 2, pagesize);
            stm.setInt(paramIndex + 3, pageindex);
            stm.setInt(paramIndex + 4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                //TypeOfRoom
                TypeOfRoom typeofroom = new TypeOfRoom();
                typeofroom.setName(rs.getString("type"));
                typeofroom.setPrice(rs.getDouble("price"));
                //Room
                Room room = new Room();
                room.setTypeofroom(typeofroom);
                //Student
                Student student = new Student();
                student.setName(rs.getString("sname"));
                student.setPhonenumber(rs.getString("phonenumber"));
                student.setCardnumber(rs.getString("cardnumber"));
                student.setAddress(rs.getString("address"));
                student.setDob(rs.getDate("dob"));
                student.setGender(rs.getBoolean("gender"));
                //Contract
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setName(rs.getString("name"));
                contract.setDeposit(rs.getDouble("deposit"));
                contract.setFrom(rs.getDate("from"));
                contract.setStudent(student);
                contract.setRoom(room);
                contracts.add(contract);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContractDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contracts;
    }

    public int count(String sname, Date from, Date to) {

        try {
            String c = " ";
            HashMap<Integer, Object[]> params = new HashMap<>();
            int paramIndex = 0;

            if (sname != null) {
                c += "and s.sname like '%'+?+'%'";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = sname;
                params.put(paramIndex, param);
            }
            if (from != null) {
                c += "AND and c.[from] >= ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Date.class.getTypeName();
                param[1] = from;
                params.put(paramIndex, param);
            }
            if (to != null) {
                c += "AND and c.[from] <= ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Date.class.getTypeName();
                param[1] = to;
                params.put(paramIndex, param);
            }
            String sql = "select COUNT(*) as count\n"
                    + "from [Contract] c\n"
                    + "inner join [Student] s on s.sid = c.sid\n"
                    + "inner join [Room] r on r.rid = c.rid\n"
                    + "where (1=1)" + c;
            PreparedStatement stm = conn.prepareStatement(sql);
            for (Map.Entry<Integer, Object[]> entry : params.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if (type.equals(String.class.getName())) {
                    stm.setString(index, value[1].toString());
                }
                if (type.equals(Date.class.getName())) {
                    stm.setDate(index, (Date) value[1]);
                }
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("count");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ContractDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Contract getContractByID(int cid) {
        try {
            String sql = "select c.id,('HD '+r.rname) as [name], s.sname,s.phonenumber, s.cardnumber,s.address,s.dob,s.gender,r.rname, tor.price,tor.[name] as [type], c.deposit, c.[from]\n"
                    + "from [Contract] c\n"
                    + "inner join [Student] s on s.sid = c.sid\n"
                    + "inner join [Room] r on r.rid = c.rid\n"
                    + "inner join TypeOfRoom tor on tor.id = r.id\n"
                    + "where c.id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, cid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                //TypeOfRoom
                TypeOfRoom typeofroom = new TypeOfRoom();
                typeofroom.setName(rs.getString("type"));
                typeofroom.setPrice(rs.getDouble("price"));
                //Room
                Room room = new Room();
                room.setRname(rs.getString("rname"));
                room.setTypeofroom(typeofroom);
                //Student
                Student student = new Student();
                student.setName(rs.getString("sname"));
                student.setPhonenumber(rs.getString("phonenumber"));
                student.setCardnumber(rs.getString("cardnumber"));
                student.setAddress(rs.getString("address"));
                student.setDob(rs.getDate("dob"));
                student.setGender(rs.getBoolean("gender"));
                //Contract
                Contract contract = new Contract();
                contract.setId(rs.getInt("id"));
                contract.setName(rs.getString("name"));
                contract.setDeposit(rs.getDouble("deposit"));
                contract.setFrom(rs.getDate("from"));
                contract.setStudent(student);
                contract.setRoom(room);
                return contract;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ContractDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insert(Contract c) {
        try {
            conn.setAutoCommit(false);
            //update condition of room
            String sql_set_room = "UPDATE [Room]\n"
                    + "   SET [condition] = 0\n"
                    + " WHERE Room.rid = ?";
            PreparedStatement stm_set_room = conn.prepareStatement(sql_set_room);
            stm_set_room.setInt(1, c.getRoom().getRid());
            stm_set_room.executeUpdate();
            //insert student
            String sql_set_student = "INSERT INTO [Student]\n"
                    + "           ([sname]\n"
                    + "           ,[gender]\n"
                    + "           ,[dob]\n"
                    + "           ,[phonenumber]\n"
                    + "           ,[cardnumber]\n"
                    + "           ,[address])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm_set_student = conn.prepareStatement(sql_set_student);
            stm_set_student.setString(1, c.getStudent().getName());
            stm_set_student.setBoolean(2, c.getStudent().isGender());
            stm_set_student.setDate(3, c.getStudent().getDob());
            stm_set_student.setString(4, c.getStudent().getPhonenumber());
            stm_set_student.setString(5, c.getStudent().getCardnumber());
            stm_set_student.setString(6, c.getStudent().getAddress());
            stm_set_student.executeUpdate();
            //get sid
            String sql_get_sid = "SELECT @@IDENTITY as sid";
            PreparedStatement stm_get_sid = conn.prepareStatement(sql_get_sid);
            ResultSet rs = stm_get_sid.executeQuery();
            if (rs.next()) {
                c.getStudent().setId(rs.getInt("sid"));
            }
            //insert contract
            String sql_set_contract = "INSERT INTO [Contract]\n"
                    + "           ([sid]\n"
                    + "           ,[rid]\n"
                    + "           ,[deposit]\n"
                    + "           ,[from])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm_set_contract = conn.prepareStatement(sql_set_contract);
            stm_set_contract.setInt(1, c.getStudent().getId());
            stm_set_contract.setInt(2, c.getRoom().getRid());
            stm_set_contract.setDouble(3, c.getDeposit());
            stm_set_contract.setDate(4, c.getFrom());
            stm_set_contract.executeUpdate();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ContractDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ContractDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ContractDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*public static void main(String[] args) {
        ContractDBContext cdb = new ContractDBContext();
        Contract contract = cdb.getContractByID(1);
        System.out.println(contract.getName());
       
    }*/
}
