/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DaMania
 */
import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

public class PatientDB 
{
    private final String HOST = "jdbc:derby://localhost:1527/information";
    private String uName = "healthworks";
    private String password = "healthworks";
    private Connection con;
    private Statement stmt;
    private ResultSet rs, rsPat;  
    private PreparedStatement prep;
    private String username;
        
    public PatientDB(String username)
    {
        this.username = username;
        //to open the PATIENTS table to store information
        try
        {
            con = DriverManager.getConnection(HOST,uName,password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            //con.setAutoCommit(false);
            
            
            
            //String sql2 = "SELECT * FROM Patients";
            //rs = stmt.executeQuery(sql2);
            
            prep = con.prepareStatement("INSERT INTO PATIENTS(FIRSTNAME,LASTNAME,PRIMEPHONE,OTHERPHONE,DOB,AGE,GENDER,STATUS,PERSONALEMAIL,"+
                    "ADDRESS,CITY,STATE,ZIP,EMERGENCYNAME,EMERGENCYPH,EMERGENCYREL,USERNAME,PASSWORD,EMAIL,DOCTOR," +  
                    "INSURANCE,INSUREDNAME,DATEOFBIRTH,SOCSEC,RELATION,PHONENUM,POLICYNUM,GROUPNUM,EFFDATE)"+
                    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }      
    }
    public void setPersonalInfo(String first, String last,String primePhone, String otherPhone, String gender, String status, String age, String birth, String pEmail)
    {
        try
        {   
            prep.setString(1, first);
            prep.setString(2, last);
            prep.setString(3, primePhone);
            prep.setString(4, otherPhone);
            prep.setString(5, birth);
            prep.setString(6, age);
            prep.setString(7, gender);
            prep.setString(8, status);
            prep.setString(9, pEmail);
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public void setAddress(String add, String city, String state, String zip)
    {
        try
        {         
            prep.setString(10, add);
            prep.setString(11, city);
            prep.setString(12, state);
            prep.setString(13, zip);   
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public void setEmergencyInfo(String name, String number, String relation)
    {
        try
        {
            prep.setString(14, name);
            prep.setString(15, number);
            prep.setString(16, relation);
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public void setInsuranceInfo(String insurance, String name, String birth, String ssn, String relation, String phone, String policy, String group, String date)
    {
        try
        {
            prep.setString(21, insurance);
            prep.setString(22, name);
            prep.setString(23, birth);
            prep.setString(24, ssn);
            prep.setString(25, relation);
            prep.setString(26, phone);
            prep.setString(27, policy);
            prep.setString(28, group);
            prep.setString(29, date);
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public void setRegistrationInfo(String id, String pass, String doctor, String email)
    {
        try
        {  
            prep.setString(17, id);
            prep.setString(18, pass);
            prep.setString(19, email);   
            prep.setString(20, doctor); 
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public void update()
    {
        try
        {
            prep.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public void setBPTIME(Vector<String> bp)
    {
        try
        {
            //PreparedStatement pstmt = con.prepareStatement("UPDATE P"+username+" SET BP = ?");
            String sql ="SELECT BP FROM P"+username;
            rsPat = stmt.executeQuery(sql);
            
            if(!rsPat.next())
            {
                rsPat.moveToInsertRow();
                System.out.println("false");
            }

            Iterator i = bp.iterator();

            while(i.hasNext())
            {
                System.out.println(rsPat.getRow());
                rs.next();
                String b = (String)(i.next());
                System.out.println(b);
                rsPat.updateString("BP",b);
                //rsPat.updateRow();           
            }
            
                //rsPat.updateString("BP", i.next());
            
            //rsPat.updateRow();
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public  void setLowBP(Vector<String> lowbp)
    {
        
    }
    public void setHighBP(Vector<String> highbp)
    {
        
    }
    public void setSugar(Vector<String> sugar)
    {
        
    }
    public void setSugarTime(Vector<String> sugarTime)
    {
        
    }
    public void setWeight(Vector<String> weight)
    {
        
    }
    public void setWeightTime(Vector<String> weightTime)
    {
        
    }
    public void setDrugs(Vector<String> drugs)
    {
        
    }
    public void setObservations(Vector<String> obs)
    {
        
    }
    public Vector<Object> getPersonalInfo()
    {
        return new Vector<Object>();
    }
    public Vector<String> getAddress()
    {
        return new Vector<String>();
    }
    public Vector<String> getEmergencyInfo()
    {
        return new Vector<String>();
    }
    public Vector<String> getInsuranceInfo()
    {
        return new Vector<String>();
    }
    public Vector<String> getRegistrationInfo()
    {
        return new Vector<String>();
    }
    public Vector<String> getBP()
    {
        return new Vector<String>();
    }
    public Vector<String> getSugar()
    {
        return new Vector<String>();
    }
    public Vector<String> getWeight()
    {
        return new Vector<String>();
    }
    public Vector<String> getDrugs()
    {
        return new Vector<String>();
    }
    public Vector<String> getObservations()
    {
        return new Vector<String>();
    }
    
}
