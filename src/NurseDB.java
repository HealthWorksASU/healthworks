
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * A database manager for nurse accounts.
 */

/**
 *
 * @author DaMania
 */
public class NurseDB extends UserDB
{
    public NurseDB(String _user)
    {
       super(_user);
       this.userTableName="NURSES";
    }
    
    //Gets the doctor's used verification code
    public String getDoctor() throws SQLException
    {
        return queryField("doctor");
    }
    //Sets the doctor's used verification code
    public void setDoctor(String docAccountName) throws SQLException
    {
        ensureConnection();
        PreparedStatement prep = con.prepareStatement("UPDATE "+userTableName+" SET doctor = ? WHERE username = ?");

        prep.setString(1,docAccountName);
        prep.setString(2,user);
        prep.executeUpdate();
    }
}
