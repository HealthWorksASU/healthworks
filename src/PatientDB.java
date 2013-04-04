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

public class PatientDB extends UserDB
{
    private final String HOST = "jdbc:derby://localhost:1527/information";
    private String uName = "healthworks";
    private String password = "healthworks";
    private Connection con;
    private Statement stmt;
    private ResultSet rs, rsPat;  
    private PreparedStatement prep;
    private String username;
    private String sql;
    private String latestBP,latestSugar,latestWeight;
        
    public PatientDB(String username)
    {
        super(username);
        this.userTableName="PATIENTS";
        this.username = username;
        //to open the PATIENTS table to store information
        try
        {
            con = DriverManager.getConnection(HOST,uName,password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            //con.setAutoCommit(false);
   
            sql = "SELECT * FROM P"+username;
            
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
    public void setBP(String bp, String bpHigh, String bpLow)
    {
        try
        {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO P"+username+" (BP, LOWBP, HIGHBP) VALUES(?,?,?)");
 
                pstmt.setString(1,bp);
                pstmt.setString(2,bpLow);
                pstmt.setString(3,bpHigh);
                pstmt.executeUpdate();

        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public void setSugar(String sugarTime, String sugar)
    {
        try
        {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO P"+username+" (SUGARTIME, SUGAR) VALUES(?,?)");
 
                pstmt.setString(1,sugarTime);
                pstmt.setString(2,sugar);
                pstmt.executeUpdate();

        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public void setWeight(String weight, String weightTime)
    {
        try
        {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO P"+username+" (WEIGHTTIME, WEIGHT) VALUES(?,?)");
 
                pstmt.setString(1,weightTime);
                pstmt.setString(2,weight);
                pstmt.executeUpdate();

        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
    public boolean searchAndDeleteWeight(String toRemove)
    {
        String remove = "DELETE FROM P"+username+" WHERE WEIGHTTIME = \'"+toRemove+"\'";
        
        try
        {
            stmt.executeUpdate(remove);
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }  
    }
    public boolean searchAndDeleteSugar(String toRemove)
    {
        String remove = "DELETE FROM P"+username+" WHERE SUGARTIME = \'"+toRemove+"\'";
        
        try
        {
            stmt.executeUpdate(remove);
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }  
    }
    public boolean searchAndDeleteBP(String toRemove)
    {
        String remove = "DELETE FROM P"+username+" WHERE BP = \'"+toRemove+"\'";
        
        try
        {
            stmt.executeUpdate(remove);
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }  
    }
    public void setDrugs(String drugs)
    {
        try
        {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO P"+username+" (DRUGS) VALUES(?)");
 
                pstmt.setString(1,drugs);
                pstmt.executeUpdate();

        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            return;
        }
    }
     public boolean searchAndDeletePrescription(String toRemove)
    {
        String remove = "DELETE FROM P"+username+" WHERE DRUGS = \'"+toRemove+"\'";
        
        try
        {
            stmt.executeUpdate(remove);
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }  
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
        Vector<String> bpV = new Vector();
        try
        {
            rs = stmt.executeQuery(sql);
            while(rs.next())
                bpV.add(rs.getString("BP"));
            
            bpV.removeAll(Collections.singleton(null));
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return bpV;
    }
    public String getLatestBP()
    {
        Vector<String> bplow = new Vector();
        Vector<String> bphigh = new Vector();
        String latest = "--";
        try
        {
            rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                bplow.add(rs.getString("LOWBP"));
                bphigh.add(rs.getString("HIGHBP"));
            }
            
            bplow.removeAll(Collections.singleton(null));
            bphigh.removeAll(Collections.singleton(null));
            
            if(!bphigh.isEmpty()) 
                latest = bphigh.lastElement() + "/" + bplow.lastElement() + " mmHg";
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return latest;
    }
    public Vector<String> getSugar()
    {
        Vector<String> sugarV = new Vector();
        try
        {
            rs = stmt.executeQuery(sql);
            while(rs.next())
                sugarV.add(rs.getString("SUGARTIME"));
            
            sugarV.removeAll(Collections.singleton(null));
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return sugarV;
    }
    public String getLatestSugar()
    {
        Vector<String> sugar = new Vector();
        String latest = "--";
        try
        {
            rs = stmt.executeQuery(sql);
            while(rs.next())
                sugar.add(rs.getString("SUGAR"));
            
            sugar.removeAll(Collections.singleton(null));
            if(!sugar.isEmpty())
                latest = sugar.lastElement() + " mmol/L";
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return latest;
    }
    public Vector<String> getWeight()
    {
        Vector<String> weightV = new Vector();
        try
        {
            rs = stmt.executeQuery(sql);
            while(rs.next())
                weightV.add(rs.getString("WEIGHTTIME"));
           
            weightV.removeAll(Collections.singleton(null));
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return weightV;
    }
    public String getLatestWeight()
    {
        Vector<String> weight = new Vector();
        String latest = "--";
        try
        {
            rs = stmt.executeQuery(sql);
            while(rs.next())
                weight.add(rs.getString("WEIGHT"));
            
            weight.removeAll(Collections.singleton(null));
            
            if(!weight.isEmpty())
                latest = weight.lastElement() + " kg";    
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return latest;
    }
    public Vector<String> getDrugs()
    {
        Vector<String> pres = new Vector();
        try
        {
            rs = stmt.executeQuery(sql);
            while(rs.next())
                pres.add(rs.getString("DRUGS"));
            
            pres.removeAll(Collections.singleton(null));
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return pres;
    }
    public Vector<String> getObservations()
    {
        return new Vector<String>();
    }
    
}
