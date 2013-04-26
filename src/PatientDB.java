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
        ensureConnection();
        String createTable = "CREATE TABLE PATIENTS_"+username+" (id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                        + "bp VARCHAR(255), sugar VARCHAR(255), weight VARCHAR(255), drugs VARCHAR(255), observations VARCHAR(2500), "
                        + "LOWBP VARCHAR(2500), HIGHBP VARCHAR(2500), SUGARTIME VARCHAR(2500), WEIGHTTIME VARCHAR(2500))";
        PreparedStatement prep = con.prepareStatement(createTable);
        prep.executeUpdate();
    }
    public void deleteTable() throws SQLException
    {
        PreparedStatement prep = con.prepareStatement("DROP TABLE PATIENTS_"+username);
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
    
    public void setBP(String bp, String bpHigh, String bpLow) throws SQLException
    {
        ensureConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO PATIENTS_"+username+" (BP, LOWBP, HIGHBP) VALUES(?,?,?)");
 
                pstmt.setString(1,bp);
                pstmt.setString(2,bpLow);
                pstmt.setString(3,bpHigh);
                pstmt.executeUpdate();
    }
    public void setSugar(String sugarTime, String sugar) throws SQLException
    {
           ensureConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO PATIENTS_"+username+" (SUGARTIME, SUGAR) VALUES(?,?)");
 
                pstmt.setString(1,sugarTime);
                pstmt.setString(2,sugar);
                pstmt.executeUpdate();

    }
    public void setWeight(String weight, String weightTime) throws SQLException
    {
        ensureConnection();
            
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO PATIENTS_"+username+" (WEIGHTTIME, WEIGHT) VALUES(?,?)");
 
                pstmt.setString(1,weightTime);
                pstmt.setString(2,weight);
                pstmt.executeUpdate();

    }
    public boolean searchAndDeleteWeight(String toRemove)
    {
        String remove = "DELETE FROM PATIENTS_"+username+" WHERE WEIGHTTIME = \'"+toRemove+"\'";

        try
        {
            ensureConnection();
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
            ensureConnection();
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
        String remove = "DELETE FROM PATIENTS_"+username+" WHERE BP = \'"+toRemove+"\'";
        
        try
        {
            ensureConnection();
            PreparedStatement prep=con.prepareStatement(remove);
            prep.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
            return false;
        }  
    }
    public void setDrugs(String drugs) throws SQLException
    {
        ensureConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO PATIENTS_"+username+" (DRUGS) VALUES(?)");
 
                pstmt.setString(1,drugs);
                pstmt.executeUpdate();
    }
     public boolean searchAndDeletePrescription(String toRemove)
    {
        String remove = "DELETE FROM PATIENTS_"+username+" WHERE DRUGS = \'"+toRemove+"\'";
        
        try
        {
            ensureConnection();
            PreparedStatement prep=con.prepareStatement(remove);
            prep.executeUpdate();
            return true;
        }
        catch(SQLException e)
        {
            return false;
        }  
    }
    public void setObservations(String observation) throws SQLException
    {
        ensureConnection();
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO PATIENTS_"+username+" (OBSERVATIONS) VALUES(?)");
 
                pstmt.setString(1,observation);
                pstmt.executeUpdate();
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
            ensureConnection();
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
            ensureConnection();
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
                latest = bplow.lastElement() + "/" + bphigh.lastElement() + " mmHg";
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return latest;
    }
    public double[] getBPAverage()
    {
        
        Vector<String> bplow = new Vector();
        Vector<String> bphigh = new Vector();
        double lAve=0,hAve = 0;
        try
        {
            ensureConnection();
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
            while(rs.next())
            {
                bplow.add(rs.getString("LOWBP"));
                bphigh.add(rs.getString("HIGHBP"));
            }
            
            bplow.removeAll(Collections.singleton(null));
            bphigh.removeAll(Collections.singleton(null));
            
            double sumHigh = 0, sumLow = 0;
            int count = 0;
            if(!bphigh.isEmpty()) 
            {
                for(int i = bplow.size()-1;i>=0 && count<10;i--)
                {
                    sumHigh += Double.parseDouble(bphigh.get(i));
                    sumLow += Double.parseDouble(bplow.get(i));
                    count++;
                }
                
                if(bplow.size() <= 10)
                {
                    lAve = sumLow/bplow.size();
                    hAve = sumHigh/bphigh.size();
                }
                else
                {
                    hAve = sumHigh/10;
                    lAve = sumLow/10;
                }
            }
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        double[] ave = new double[2];
        ave[1] = lAve;
        ave[0] = hAve;
        
        return ave;
    }
    public Vector<String> getSugar()
    {
        Vector<String> sugarV = new Vector();
        try
        {
            ensureConnection();
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
            ensureConnection();
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
    public double getSugarAverage()
    {
        Vector<String> sugar = new Vector();
        double ave=0;;
        try
        {
            ensureConnection();
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
            while(rs.next())
                sugar.add(rs.getString("SUGAR"));
            
            double sum = 0;
            int count = 0;
            sugar.removeAll(Collections.singleton(null));
            if(!sugar.isEmpty())
            {
                for(int i = sugar.size()-1;i>=0 && count<10;i--)
                {
                    sum += Double.parseDouble(sugar.get(i));
                    count++;
                }
                
                if(sugar.size() <= 10)
                    ave = sum/sugar.size();
                else
                    ave = sum/10;
            }
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return ave;
    }
    public Vector<String> getWeight()
    {
        Vector<String> weightV = new Vector();
        try
        {
            ensureConnection();
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
            ensureConnection();
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
    public double getWeightAverage()
    {
        Vector<String> weight = new Vector();
        double ave=0;
        try
        {
            ensureConnection();
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
            while(rs.next())
                weight.add(rs.getString("WEIGHT"));
            
            weight.removeAll(Collections.singleton(null));
            
            int count = 0;
            double sum = 0;
            if(!weight.isEmpty())
                for(int i = weight.size()-1;i>=0 && count<10;i--)
                {
                    sum += Double.parseDouble(weight.get(i));
                    count++;
                }
                
                if(weight.size() <= 10)
                    ave = sum/weight.size();
                else
                    ave = sum/10;    
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return ave;
    }
    public Vector<String> getDrugs()
    {
        Vector<String> pres = new Vector();
        try
        {
            ensureConnection();
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
        Vector<String> obs = new Vector();
        try
        {
            ensureConnection();
            PreparedStatement prep=con.prepareStatement("SELECT * FROM PATIENTS_"+username);
            ResultSet rs=prep.executeQuery();
            while(rs.next())
                obs.add(rs.getString("OBSERVATIONS"));
            
            obs.removeAll(Collections.singleton(null));
           
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
        
        return obs;
    }
    
}
