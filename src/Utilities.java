
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * This class contains a few helper functions.
 */

/**
 *
 * @author Scott
 */
public class Utilities {
    
    //Takes an SQL result set, a list to store all UserInfos from the result set,
    //and a list to store only UserInfos whose "doctor" member matches the doctorAccountName
    //parameter and adds UserInfos to the list accordingly.
    //Overload for default argument
    public static void helperStoreUserData(ResultSet rs,
                                                ArrayList<UserInfo> allList,
                                               ArrayList<UserInfo> myList,
                                               String matchString) throws SQLException
    {
        helperStoreUserData(rs,allList,myList,matchString,"doctor");
    }
    public static void helperStoreUserData(ResultSet rs,
                                               ArrayList<UserInfo> allList,
                                               ArrayList<UserInfo> myList,
                                               String matchString,
                                               String matchTableColumn) throws SQLException
    {
        while(rs.next())
            {
                UserInfo user=new UserInfo(rs.getString("firstname"),rs.getString("lastname"),rs.getString("username"));
                allList.add(user);
                if (rs.getString(matchTableColumn).equals(matchString))
                {
                   myList.add(user);   
                }
            }
    }
    
    //Adds all UserInfos in the source whose first and last name equal match to the listView list
    public static void helperListMatch(String match, ArrayList<UserInfo> source,ArrayList<UserInfo> listView)
    {
        for(UserInfo elem : source)
        {
            if ((elem.firstname+" "+elem.lastname).toLowerCase().contains(match.toLowerCase())==true)
            {
                listView.add(elem);
            }
        }
    }
    
    //Updates the listView and UIList component with contents matching the search field from either the allList or myList, depending on the category selector.
    public static void updateGenericPersonnelListing(javax.swing.JComboBox categorySelector,
                                                        javax.swing.JList UIList,
                                                        javax.swing.JTextField searchField,
                                                        ArrayList<UserInfo> allList,
                                                        ArrayList<UserInfo> myList,
                                                        final ArrayList<UserInfo> listView)
    {
        listView.clear();
        helperListMatch(searchField.getText(),(categorySelector.getSelectedIndex()==0)? myList : allList, listView);
        UIList.setModel(new javax.swing.AbstractListModel() {
            @Override
            public int getSize() { return listView.size(); }
            @Override
            public Object getElementAt(int i) {UserInfo j=listView.get(i); return j.firstname+' '+j.lastname; }
            });
    }
    
    public static class RunnableFrameDisposer implements Runnable
    {
        javax.swing.JFrame toDispose;
        public RunnableFrameDisposer(javax.swing.JFrame _toDispose)
        {
            toDispose=_toDispose;
        }
        public void run()
        {
            toDispose.dispose();
        }
    }
}
