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
    private String username;
    private String latestBP,latestSugar,latestWeight;
        
    public PatientDB(String username)
    {
        super(username);
        this.userTableName="PATIENTS";
        this.username = username;
    }
    
    public void createTable() throws SQLException
    {
        String createTable = "CREATE TABLE PATIENTS_"+username+" (id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "bp VARCHAR(255), sugar VARCHAR(255), weight VARCHAR(255), drugs VARCHAR(255), observations VARCHAR(2500), "
                        + "LOWBP VARCHAR(2500), HIGHBP VARCHAR(2500), SUGARTIME VARCHAR(2500), WEIGHTTIME VARCHAR(2500))";
        PreparedStatement prep = con.prepareStatement(createTable);
        prep.executeUpdate();
    }
    
    public void setPersonalInfo(String first, String last,String primePhone, 
            String otherPhone, String gender, String status, String age, 
            String birth, String pEmail) throws SQLException
    {
        setField("firstname",first);
        setField("lastname",last);
        setField("primephone",primePhone);
        setField("otherphone",otherPhone);
        setField("dob",birth);
        setField("age",age);
        setField("gender",gender);
        setField("status",status);
        setField("personalemail",pEmail);
    }
    public void setAddress(String add, String city, String state, String zip) throws SQLException
    {
        setField("address",add);
        setField("city",city);
        setField("state",state);
        setField("zip",zip);
    }
    public void setEmergencyInfo(String name, String number, String relation) throws SQLException
    {
        setField("emergencyname",name);
        setField("emergencyph",number);
        setField("emergencyrel",relation);
    }
    public void setInsuranceInfo(String insurance, String name, String birth, 
            String ssn, String relation, String phone, String policy, 
            String group, String date) throws SQLException
    {
        setField("insurance",insurance);
        setField("insuredname",name);
        setField("dateofbirth",birth);
        setField("socsec",ssn);
        setField("relation",relation);
        setField("phonenum",phone);
        setField("policynum",policy);
        setField("groupnum",group);
        setField("effdate",date);
    }
    public void setDoctor(String doctor) throws SQLException
    {
        setField("doctor",doctor);
    }
    
    public void setBP(String bp, String bpHigh, String bpLow)
    {
        try
        {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO PATIENTS_"+username+" (BP, LOWBP, HIGHBP) VALUES(?,?,?)");
 
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
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO PATIENTS_"+username+" (SUGARTIME, SUGAR) VALUES(?,?)");
 
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
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO PATIENTS_"+username+" (WEIGHTTIME, WEIGHT) VALUES(?,?)");
 
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
        String remove = "DELETE FROM PATIENTS_"+username+" WHERE WEIGHTTIME = \'"+toRemove+"\'";
        
        try
        {
            PreparedStatement prep=con.prepareStatement(remove);
            prep.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }  
    }
    public boolean searchAndDeleteSugar(String toRemove)
    {
        String remove = "DELETE FROM PATIENTS_"+username+" WHERE SUGARTIME = \'"+toRemove+"\'";
        
        try
        {
            PreparedStatement prep=con.prepareStatement(remove);
            prep.executeUpdate();
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
            PreparedStatement prep=con.prepareStatement(remove);
            prep.executeUpdate();
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
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
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
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
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
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
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
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
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
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
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
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
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
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
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
