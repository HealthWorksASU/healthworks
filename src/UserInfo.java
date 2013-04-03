/*
 * This class is a helper class which associates a username with a firstname and lastname.
 */

/**
 *
 * @author Scott
 */
public class UserInfo implements Comparable {
    
        public String firstname; 
        public String lastname; 
        public String username; 
        
        public UserInfo(String _firstname, String _lastname, String _username)
        {  
            firstname=_firstname;
            lastname=_lastname;
            username=_username;
        }
        
        public int compareTo(Object b)
        {
            UserInfo c=(UserInfo)b; //This will fail at runtime if the comparison is not between UserInfos.
            return (firstname+" "+lastname+" "+username).compareTo(c.firstname+" "+c.lastname+" "+c.username);
        }
    }