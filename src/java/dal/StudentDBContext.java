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
import modal.Room;
import modal.Student;

/**
 *
 * @author ADMIN
 */
public class StudentDBContext extends DBContext {

    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT [sid],[sname],[gender],[dob],[phonenumber],[cardnumber],[address]\n"
                    + "  FROM [Student]";

            PreparedStatement stm = conn.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                s.setPhonenumber(rs.getString("phonenumber"));
                s.setCardnumber(rs.getString("cardnumber"));
                s.setAddress(rs.getString("address"));
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    public int count(Boolean gender, String sname) {
        try {
            String sql = "select COUNT(*) \n"
                    + "from Student s\n"
                    + "  where (1=1) ";
            HashMap<Integer, Object[]> params = new HashMap<>();
            int paramIndex = 0;

            if (gender != null) {
                sql += "and s.gender = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Boolean.class.getTypeName();
                param[1] = gender;
                params.put(paramIndex, param);
            }
            if (sname != null) {
                sql += "and s.sname like '%' +?+'%'";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = sname;
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

    public ArrayList<Student> search(Boolean gender, String sname, int index) {
        ArrayList<Student> students = new ArrayList<>();
        try {
            String c = " ";

            HashMap<Integer, Object[]> params = new HashMap<>();
            int paramIndex = 0;

            if (gender != null) {
                c += "and s.gender = ? ";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Boolean.class.getTypeName();
                param[1] = gender;
                params.put(paramIndex, param);
            }
            if (sname != null) {
                c += "and s.sname like '%'+?+'%'";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = String.class.getTypeName();
                param[1] = sname;
                params.put(paramIndex, param);
            }
            String sql = "with x as (select ROW_NUMBER() over (order by s.sid asc) as stt ,s.[sid],[sname],[gender],[dob],[phonenumber],[cardnumber],[address], r.rname\n"
                    + " FROM [Student] s \n"
                    + "	left join [Contract] c on c.[sid] = s.[sid]\n"
                    + "	left join Room r on r.rid = c.rid\n"
                    + "	WHERE (1=1)"+c+")\n"
                    + "	select * from x as n where n.stt between ?*5-4 and ?*5";

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
            }
            stm.setInt(paramIndex + 1, index);
            stm.setInt(paramIndex + 2, index);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                s.setPhonenumber(rs.getString("phonenumber"));
                s.setCardnumber(rs.getString("cardnumber"));
                s.setAddress(rs.getString("address"));
                Room r = new Room();
                r.setRname(rs.getString("rname"));
                s.setRoom(r);
                students.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return students;
    }

    /*public static void main(String[] args) {
        StudentDBContext sdb = new StudentDBContext();
        ArrayList<Student> students = sdb.search(null, null, 1);
        for (Student s : students) {
            System.out.println(s.getId() + s.getName()+s.getRoom().getRname());
        }
    }*/

    public Student getStudent(int sid) {
        try {
            String sql = "SELECT [sid],[sname],[gender],[dob],[phonenumber],[cardnumber],[address]\n"
                    + "  FROM [Student] \n"
                    + " WHERE sid = ? ";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, sid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Student s = new Student();
                s.setId(rs.getInt("sid"));
                s.setName(rs.getString("sname"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("dob"));
                s.setPhonenumber(rs.getString("phonenumber"));
                s.setCardnumber(rs.getString("cardnumber"));
                s.setAddress(rs.getString("address"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insert(Student student) {
        try {
            String sql = "INSERT INTO [Student]([sname],[gender],[dob],[phonenumber],[cardnumber],[address])\n"
                    + "     VALUES(?,?,?,?,?,?)";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, student.getName());
            stm.setBoolean(2, student.isGender());
            stm.setDate(3, student.getDob());
            stm.setString(4, student.getPhonenumber());
            stm.setString(5, student.getCardnumber());
            stm.setString(6, student.getAddress());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void delete(int sid) {
        try {
            String sql = "DELETE FROM [Student]\n"
                    + "      WHERE sid = ? ";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1, sid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(Student student) {
        try {
            String sql = "UPDATE [Student]\n"
                    + "   SET [sname] = ?\n"
                    + "      ,[gender] = ?\n"
                    + "      ,[dob] = ?\n"
                    + "      ,[phonenumber] = ?\n"
                    + "      ,[cardnumber] = ?\n"
                    + "      ,[address] = ?\n"
                    + " WHERE sid = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, student.getName());
            stm.setBoolean(2, student.isGender());
            stm.setDate(3, (Date) student.getDob());
            stm.setString(4, student.getPhonenumber());
            stm.setString(5, student.getCardnumber());
            stm.setString(6, student.getAddress());
            stm.setInt(7, student.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StudentDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
