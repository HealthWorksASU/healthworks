import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * A database manager interface for doctor accounts.
 */

/**
 *
 * @author DaMania
 */
public class DoctorDB extends UserDB
{
    public DoctorDB(String _user)
    {
        super(_user);
        this.userTableName="DOCTORS";
        System.out.println("DOCTOR CONSTRUCTOR:"+userTableName);
    }
    
    //Gets the doctor's used verification code
    public String getVerificationCode() throws SQLException
    {
       ensureConnection();
        PreparedStatement prep = con.prepareStatement("SELECT verification FROM "+userTableName+" WHERE username= ? ", 
                ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        prep.setString(1,user);
        ResultSet rs = prep.executeQuery();
        return rs.getString("verification");
    }
    //Sets the doctor's used verification code
    public void setVerificationCode(String code) throws SQLException
    {
        ensureConnection();
        PreparedStatement prep = con.prepareStatement("UPDATE "+userTableName+" SET verification = ? WHERE username = ?");

        prep.setString(1,code);
        prep.setString(2,user);
        prep.executeUpdate();
    }
    
    //Returns true if the verification code was valid and was taken, false otherwise
    public boolean takeVerificationCode(String code) throws SQLException
    {
        PreparedStatement prep = con.prepareStatement("SELECT * FROM VERIFICATION_TABLES WHERE verification_code = ? ", 
                ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        prep.setString(1,code);
        ResultSet rs=prep.executeQuery();        
        if(!rs.isBeforeFirst()) {
            return false;
        }
        else
        {
            prep = con.prepareStatement("DROP ? ON VERIFICATION_CODES");
            prep.setString(1,code);
            prep.executeUpdate();
            return true;
        }
    }
}
