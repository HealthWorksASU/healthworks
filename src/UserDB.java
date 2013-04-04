import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/* A generic manager for a user database.
 */

/**
 *
 * @author Scott
 */
abstract class UserDB {
    protected static final String databaseHOST="jdbc:derby://localhost:1527/information";
    protected static final String databaseUserName="healthworks";
    protected static final String databaseUserPassword="healthworks";
    protected String userTableName; //Overriding classes should change this
    
    protected String user;
    protected Connection con;
            
    public UserDB(String _user)
    {
        user=_user;
    }
    
    protected void ensureConnection() throws SQLException
    {
        if (con==null || !con.isValid(1) || con.isClosed())
        {
            con=DriverManager.getConnection(databaseHOST,databaseUserName,databaseUserPassword);
        }
    }
    
    public boolean accountExists() throws SQLException
    {
        ensureConnection();
        PreparedStatement prep = con.prepareStatement("SELECT * FROM "+userTableName+" WHERE username= ? ", 
                ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        prep.setString(1,user);
        ResultSet rs = prep.executeQuery();
        return rs.isBeforeFirst();
    }
    
    public void createAccount(String firstName, String lastName, String password, String email) throws SQLException
    {
        
        ensureConnection();
        PreparedStatement prep = con.prepareStatement("INSERT INTO "+userTableName+"(USERNAME,PASSWORD,EMAIL,FIRSTNAME,LASTNAME) VALUES(?,?,?,?,?)");

        prep.setString(1,user);
        prep.setString(2,password);
        prep.setString(3,email);
        prep.setString(4,firstName);
        prep.setString(5,lastName);
        
        prep.executeUpdate();
    }
    public void setAccountDetails(String firstName, String lastName, String password, String email) throws SQLException
    {
        ensureConnection();
        PreparedStatement prep = con.prepareStatement("UPDATE "+userTableName+" SET password = ?, email = ?, firstname = ?, lastname = ? WHERE username = ?");

        prep.setString(1,password);
        prep.setString(2,email);
        prep.setString(3,firstName);
        prep.setString(4,lastName);
        prep.setString(5,user);
        
        prep.executeUpdate();
    }
    
    public void deleteUser() throws SQLException
    {
        ensureConnection();
        PreparedStatement prep = con.prepareStatement("DELETE FROM "+userTableName+" WHERE username = ?");
        prep.setString(1,user);
        prep.executeUpdate();
    }
    
    public ResultSet getFromAllAccounts(String dataToGet) throws SQLException
    {
        Connection tempCon = DriverManager.getConnection(databaseHOST,databaseUserName,databaseUserPassword);
        PreparedStatement prep = tempCon.prepareStatement("SELECT "+dataToGet+" FROM "+userTableName,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = prep.executeQuery();
        return rs;
    }
    
    private String queryField(String field) throws SQLException
    {
        ensureConnection();
        PreparedStatement prep = con.prepareStatement("SELECT "+field+" FROM "+userTableName+" WHERE username = ?");
        prep.setString(1,user);
        ResultSet rs=prep.executeQuery();
        rs.next();
        return rs.getString(field);
    }
    
    public String getEmail() throws SQLException
    {
        return queryField("email");
    }
    
    private String getPassword() throws SQLException
    {
        return queryField("PASSWORD");
    }
    
    public boolean verifyPassword(String inputPW) throws SQLException
    {
        return inputPW.compareTo(getPassword())==0;
    }
}
