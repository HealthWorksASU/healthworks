
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.lang.Exception;
import java.util.Collections;
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scott
 */


public class DoctorView extends javax.swing.JFrame {
    
    private String loginName="NOACCOUNT";
    private String loginPassword="NOPASSWORD";
    private Connection con=null;
    private HashMap<String,String> nurseNameMapping=new HashMap<String,String>(); //Maps nurses "FirstName LastName" to the user account
    private HashMap<String,String> patientNameMapping=new HashMap<String,String>(); //Ditto for patients
    
    private class UserInfo implements Comparable {
        public int compareTo(Object b){UserInfo c=(UserInfo)b;return (firstname+" "+lastname+" "+username).compareTo(c.firstname+" "+c.lastname+" "+c.username);}
        public String firstname; public String lastname; public String username; 
        public UserInfo(String f, String l, String u){firstname=f;lastname=l;username=u;}
    }
    
    private ArrayList<UserInfo> myPatients=new ArrayList<UserInfo>();
    private ArrayList<UserInfo> allPatients=new ArrayList<UserInfo>();
    private ArrayList<UserInfo> myNurses=new ArrayList<UserInfo>();
    private ArrayList<UserInfo> allNurses=new ArrayList<UserInfo>();
    private ArrayList<UserInfo> nurseView=new ArrayList<UserInfo>();
    private ArrayList<UserInfo> patientView=new ArrayList<UserInfo>();
    
    /**
     * Creates new form DoctorView
     */
    public DoctorView() {
        initComponents();
        //Constructor for test. Populates with TEST data. Actual constructor accepting two strings should be called in actual application.
        //Test initializers
        SignedInAsNotifier.setText("TEST MODE ONLY");
        String[] s_allPatientsFirst={"Michael","Dan","Robert","Shawn","Olga","Eileen","Joan"};
        String[] s_allPatientsLast={"Cook","McDonald","Seavey","Boehm","Adkins","Barkley","Pangburn"};
        String[] s_allPatientsUser={"mcook","ddonald","rseavey","sboehm","olgirl203","ebarkley","jpang"};
        
        String[] s_allNursesFirst={"Joy","Amanda","Argentina","Tiesha","Vivian","Gina","Elaine"};
        String[] s_allNursesLast={"Kopec","Davis","Benitez","Wheaton","Hess","Sickler","Glenn"};
        String[] s_allNursesUser={"jkepec","adavis","abenitez","twheat","vhess","ginasick2923","eglenn"};
        for (int i=0;i<s_allPatientsFirst.length;i++)
        {
            UserInfo patient=new UserInfo(s_allPatientsFirst[i],s_allPatientsLast[i],s_allPatientsUser[i]);
            UserInfo nurse=new UserInfo(s_allNursesFirst[i],s_allNursesLast[i],s_allNursesUser[i]);
            allPatients.add(patient);
            allNurses.add(nurse);
            nurseNameMapping.put(nurse.firstname+" "+nurse.lastname,nurse.username);
            patientNameMapping.put(patient.firstname+" "+patient.lastname,patient.username);
            if(i<4)
            {
                myPatients.add(patient);
                myNurses.add(nurse);
            }
        }
        Collections.sort((List)myPatients);
        Collections.sort((List)allPatients);
        Collections.sort((List)myNurses);
        Collections.sort((List)allNurses);
        
        UpdateNurseScrollList();
        UpdatePatientScrollList();
     }
    
    public DoctorView(String accountName, String accountPassword)
    {
        initComponents();
        loginName=accountName;
        loginPassword=accountPassword;
        SignedInAsNotifier.setText("Signed in as: "+accountName);
        //Attempt to retrieve information from database
        final String HOST = "jdbc:derby://localhost:1527/information";
        String uName = "healthworks";
        String password = "healthworks";
        try{
            con = DriverManager.getConnection(HOST,uName,password);
            PreparedStatement prep = con.prepareStatement("SELECT username, firstname, lastname, doctor FROM NURSES",
                        ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            prep.setString(1,accountName);
            ResultSet rs = prep.executeQuery();
            while(rs.next())
            {
                UserInfo nurse=new UserInfo(rs.getString("firstname"),rs.getString("lastname"),rs.getString("username"));
                allNurses.add(nurse);
                if (rs.getString("doctor").equals(accountName))
                {
                   myNurses.add(nurse);   
                }
                nurseNameMapping.put(nurse.firstname+" "+nurse.lastname,nurse.username);
            }
            //Get all patients
            prep=con.prepareStatement("SELECT username, firstname, lastname, doctor FROM Patients",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=prep.executeQuery();
            while(rs.next())
            {
                UserInfo patient=new UserInfo(rs.getString("firstname"),rs.getString("lastname"),rs.getString("username"));
                allPatients.add(patient);
                if (rs.getString("doctor").equals(accountName))
                {
                   myPatients.add(patient);   
                }
                patientNameMapping.put(patient.firstname+" "+patient.lastname,patient.username);
            }
            
            Collections.sort((List)myPatients);
            Collections.sort((List)allPatients);
            Collections.sort((List)myNurses);
            Collections.sort((List)allNurses);
            UpdateNurseScrollList();
            UpdatePatientScrollList();
            
            
        }
        catch(SQLException e)
        {
        JOptionPane.showMessageDialog(this,"Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
        this.dispose();
        return;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LogoutButton = new javax.swing.JButton();
        SignedInAsNotifier = new javax.swing.JLabel();
        ActionsPane = new javax.swing.JTabbedPane();
        PatientPane = new javax.swing.JPanel();
        PatientScrollList = new javax.swing.JScrollPane();
        PatientList = new javax.swing.JList();
        ViewInfoButton = new javax.swing.JButton();
        PatientCategorySelector = new javax.swing.JComboBox();
        PatientSearchField = new javax.swing.JTextField();
        NursePane = new javax.swing.JPanel();
        NurseScrollList = new javax.swing.JScrollPane();
        NurseList = new javax.swing.JList();
        NurseSearchField = new javax.swing.JTextField();
        NurseCategorySelector = new javax.swing.JComboBox();
        NurseAddButton = new javax.swing.JButton();
        NurseDeleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LogoutButton.setText("Logout");
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });

        SignedInAsNotifier.setText("Signed in as: ");

        PatientList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Adam Jones", "Michael Stevens", "Kenneth Shive", "Edward Pearsall", "Richard Conti", "John Cephas", "Justin Cook", "Stephen Brand", "Richard Garcia", "Patrick Collard", "Cecil Cortez", "Kevin Appel", "Eugenio Zimmerman", "Roy Kapoor", "Richard Swensen", "Gregory Dawson", "Joseph Sasso", "Richard Pfister", "Robert Wood", "Miles Kelly", "Shaun Lovell", "Paul Gonzales", "Patrick Lacayo", "Martin Price", "Steven Fisher", "Rick Daugherty" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        PatientScrollList.setViewportView(PatientList);

        ViewInfoButton.setText("View Patient Info");
        ViewInfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewInfoButtonActionPerformed(evt);
            }
        });

        PatientCategorySelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "My Patients", "All Patients" }));
        PatientCategorySelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientCategorySelectorActionPerformed(evt);
            }
        });

        PatientSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                PatientSearchFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PatientPaneLayout = new javax.swing.GroupLayout(PatientPane);
        PatientPane.setLayout(PatientPaneLayout);
        PatientPaneLayout.setHorizontalGroup(
            PatientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PatientPaneLayout.createSequentialGroup()
                .addGroup(PatientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PatientPaneLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(PatientCategorySelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PatientPaneLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(PatientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(PatientSearchField)
                            .addComponent(PatientScrollList, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ViewInfoButton)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        PatientPaneLayout.setVerticalGroup(
            PatientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PatientPaneLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(PatientSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PatientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PatientPaneLayout.createSequentialGroup()
                        .addComponent(PatientScrollList, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PatientCategorySelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ViewInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        ActionsPane.addTab("Patient", PatientPane);

        NurseList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Patricia Flanagan", "Margaret Swinney", "Shonta Claudio", "Elizabeth Allard", "Ila Miller", "Patricia Laramie", "Helen Anderson", "Gertie Ramsey", "Jane Thompson", "Alisha Wood", "Jennifer Oberg", "Allison Bunch", "Louis Leech", "Frances Uribe", "Mae Allen", "Clara Ruel", "Dinah Lucius", "Leslie Holgate", "Christine Mims", "Carrie Jackson", "Martha Winnett", "Berniece Cracraft", "Debra Murray", "Dorothy Moore", "Mildred Beach" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        NurseScrollList.setViewportView(NurseList);

        NurseSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                NurseSearchFieldKeyTyped(evt);
            }
        });

        NurseCategorySelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "My Nurses", "All Nurses" }));
        NurseCategorySelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NurseCategorySelectorActionPerformed(evt);
            }
        });

        NurseAddButton.setText("Add New Nurse");
        NurseAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NurseAddButtonActionPerformed(evt);
            }
        });

        NurseDeleteButton.setText("Delete Nurse");
        NurseDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NurseDeleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NursePaneLayout = new javax.swing.GroupLayout(NursePane);
        NursePane.setLayout(NursePaneLayout);
        NursePaneLayout.setHorizontalGroup(
            NursePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NursePaneLayout.createSequentialGroup()
                .addGroup(NursePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(NursePaneLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(NurseCategorySelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(NursePaneLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(NursePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NurseSearchField)
                            .addComponent(NurseScrollList, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(NursePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NurseAddButton, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                            .addComponent(NurseDeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        NursePaneLayout.setVerticalGroup(
            NursePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NursePaneLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(NurseSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(NursePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NurseScrollList, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(NursePaneLayout.createSequentialGroup()
                        .addComponent(NurseAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(NurseDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NurseCategorySelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        ActionsPane.addTab("Nurse", NursePane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(SignedInAsNotifier)
                .addGap(18, 18, 18)
                .addComponent(LogoutButton))
            .addComponent(ActionsPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SignedInAsNotifier))
                .addGap(18, 18, 18)
                .addComponent(ActionsPane))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private static void helperListMatch(String match, ArrayList<UserInfo> source,ArrayList<UserInfo> listView)
    {
        for(UserInfo elem : source)
        {
            if ((elem.firstname+" "+elem.lastname).toLowerCase().contains(match.toLowerCase())==true)
            {
                listView.add(elem);
            }
        }
    }
    
    private void updateGenericPersonnelListing(javax.swing.JComboBox categorySelector, javax.swing.JList UIList, javax.swing.JTextField searchField, ArrayList<UserInfo> allList, ArrayList<UserInfo> myList, final ArrayList<UserInfo> listView)
    {
        listView.clear();
        helperListMatch(searchField.getText(),(categorySelector.getSelectedIndex()==0)? myList : allList, listView);
        UIList.setModel(new javax.swing.AbstractListModel() {
            public int getSize() { return listView.size(); }
            public Object getElementAt(int i) {UserInfo j=listView.get(i); return j.firstname+' '+j.lastname; }
            });
    }
    private void UpdateNurseScrollList()
    {
        updateGenericPersonnelListing(NurseCategorySelector,NurseList,NurseSearchField,allNurses,myNurses,nurseView);
    }
    
    private void UpdatePatientScrollList()
    {
       updateGenericPersonnelListing(PatientCategorySelector,PatientList,PatientSearchField,allPatients,myPatients,patientView);
    }
    
    private void NurseAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NurseAddButtonActionPerformed

        new NurseRegister(loginName,loginPassword).setVisible(true);
    }//GEN-LAST:event_NurseAddButtonActionPerformed

    private void NurseDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NurseDeleteButtonActionPerformed
        String nurseName=(String)NurseList.getSelectedValue();
        if (nurseName!=null)
        {
            int confirm=JOptionPane.showConfirmDialog(this, "Are you sure you want to delete "+nurseName+" from your list of nurses?");
            if (confirm==JOptionPane.YES_OPTION){
                //...
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please select a nurse to delete.");
        }
    }//GEN-LAST:event_NurseDeleteButtonActionPerformed

    private void ViewInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewInfoButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewInfoButtonActionPerformed

    private void NurseCategorySelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NurseCategorySelectorActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateNurseScrollList();
            }});
    }//GEN-LAST:event_NurseCategorySelectorActionPerformed

    private void PatientCategorySelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientCategorySelectorActionPerformed
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdatePatientScrollList();
            }});
    }//GEN-LAST:event_PatientCategorySelectorActionPerformed

    private void NurseSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NurseSearchFieldKeyTyped
        // TODO add your handling code here:
        //UpdateNurseScrollList(Character.toString(evt.getKeyChar()));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateNurseScrollList();
            }});
    }//GEN-LAST:event_NurseSearchFieldKeyTyped

    private void PatientSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PatientSearchFieldKeyTyped
        // TODO add your handling code here:   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdatePatientScrollList();
            }});
    }//GEN-LAST:event_PatientSearchFieldKeyTyped

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        (new LoginScreen()).setVisible(true);
    }//GEN-LAST:event_LogoutButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DoctorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DoctorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DoctorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DoctorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DoctorView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane ActionsPane;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JButton NurseAddButton;
    private javax.swing.JComboBox NurseCategorySelector;
    private javax.swing.JButton NurseDeleteButton;
    private javax.swing.JList NurseList;
    private javax.swing.JPanel NursePane;
    private javax.swing.JScrollPane NurseScrollList;
    private javax.swing.JTextField NurseSearchField;
    private javax.swing.JComboBox PatientCategorySelector;
    private javax.swing.JList PatientList;
    private javax.swing.JPanel PatientPane;
    private javax.swing.JScrollPane PatientScrollList;
    private javax.swing.JTextField PatientSearchField;
    private javax.swing.JLabel SignedInAsNotifier;
    private javax.swing.JButton ViewInfoButton;
    // End of variables declaration//GEN-END:variables
}
