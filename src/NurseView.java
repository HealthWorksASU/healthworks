import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scott
 */
public class NurseView extends javax.swing.JFrame {

    
    private String loginName="NOACCOUNT";
    private String loginPassword="NOPASSWORD";
    
    private ArrayList<UserInfo> myPatients=new ArrayList<UserInfo>();
    private ArrayList<UserInfo> allPatients=new ArrayList<UserInfo>();
    
    private ArrayList<UserInfo> myDoctors=new ArrayList<UserInfo>();
    private ArrayList<UserInfo> allDoctors=new ArrayList<UserInfo>();
    
    private ArrayList<UserInfo> patientView=new ArrayList<UserInfo>();
    private ArrayList<UserInfo> doctorView=new ArrayList<UserInfo>();
    
    private NurseDB nurseDBManager;
    /**
     * Test constructor only
     */
    public NurseView() {
        initComponents();
        //Populate with mock data
        SignedInAsNotifier.setText("TEST MODE ONLY");
        String[] s_allPatientsFirst={"Michael","Dan","Robert","Shawn","Olga","Eileen","Joan"};
        String[] s_allPatientsLast={"Cook","McDonald","Seavey","Boehm","Adkins","Barkley","Pangburn"};
        String[] s_allPatientsUser={"mcook","ddonald","rseavey","sboehm","olgirl203","ebarkley","jpang"};
        
        String[] s_allDoctorsFirst={"Joy","Amanda","Argentina","Tiesha","Vivian","Gina","Elaine"};
        String[] s_allDoctorsLast={"Kopec","Davis","Benitez","Wheaton","Hess","Sickler","Glenn"};
        String[] s_allDoctorsUser={"jkepec","adavis","abenitez","twheat","vhess","ginasick2923","eglenn"};
        for (int i=0;i<s_allPatientsFirst.length;i++)
        {
            UserInfo patient=new UserInfo(s_allPatientsFirst[i],s_allPatientsLast[i],s_allPatientsUser[i]);
            UserInfo nurse=new UserInfo(s_allDoctorsFirst[i],s_allDoctorsLast[i],s_allDoctorsUser[i]);
            allPatients.add(patient);
            allDoctors.add(nurse);
            if(i<4)
            {
                myPatients.add(patient);
                myDoctors.add(nurse);
            }
        }
        Collections.sort((List)myPatients);
        Collections.sort((List)allPatients);
        Collections.sort((List)myDoctors);
        Collections.sort((List)allDoctors);
        
        UpdateDoctorScrollList();
        UpdatePatientScrollList();
    }
    
    public NurseView(String accountName, String accountPassword) {
        this.initComponents();
        this.loginName=accountName;
        this.loginPassword=accountPassword;
        this.SignedInAsNotifier.setText("Signed in as: "+accountName);
        //Attempt to retrieve information from database
        try{
            nurseDBManager=new NurseDB(accountName);
            String assignedDoctor=nurseDBManager.getDoctor();
            ResultSet rs;
            //Get all doctors.
            DoctorDB tempDoctor=new DoctorDB("");
            rs = tempDoctor.getFromAllAccounts("username, firstname, lastname");
            Utilities.helperStoreUserData(rs,this.allDoctors,this.myDoctors,assignedDoctor,"username"); //All doctors whose username matches assignedDoctor
            tempDoctor.closeConnection();
            //Get all patients
            PatientDB tempPatient=new PatientDB("");
            rs = tempPatient.getFromAllAccounts("username, firstname, lastname, doctor");
            Utilities.helperStoreUserData(rs,this.allPatients,this.myPatients,accountName);
            tempPatient.closeConnection();
            
            Collections.sort((List)this.myPatients);
            Collections.sort((List)this.allPatients);
            Collections.sort((List)this.myDoctors);
            Collections.sort((List)this.allDoctors);
            
            UpdateDoctorScrollList();
            UpdatePatientScrollList();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this,"Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            java.awt.EventQueue.invokeLater(new Utilities.RunnableFrameDisposer(this));
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
        InfoTabs = new javax.swing.JTabbedPane();
        PatientPane = new javax.swing.JPanel();
        PatientScrollList = new javax.swing.JScrollPane();
        PatientList = new javax.swing.JList();
        ViewInfoButton = new javax.swing.JButton();
        PatientCategorySelector = new javax.swing.JComboBox();
        PatientSearchField = new javax.swing.JTextField();
        PatientAddButton = new javax.swing.JButton();
        PatientDeleteButton = new javax.swing.JButton();
        DoctorPane = new javax.swing.JPanel();
        DoctorScrollList = new javax.swing.JScrollPane();
        DoctorList = new javax.swing.JList();
        DoctorSearchField = new javax.swing.JTextField();
        DoctorCategorySelector = new javax.swing.JComboBox();
        DoctorEmailButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        LogoutButton.setText("Logout");
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });

        SignedInAsNotifier.setText("Signed in as: NurseMary");

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

        PatientAddButton.setText("Add Patient");
        PatientAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientAddButtonActionPerformed(evt);
            }
        });

        PatientDeleteButton.setText("Delete Patient\n");
        PatientDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PatientDeleteButtonActionPerformed(evt);
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
                        .addGroup(PatientPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ViewInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PatientAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PatientDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
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
                    .addGroup(PatientPaneLayout.createSequentialGroup()
                        .addComponent(ViewInfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PatientAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PatientDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        InfoTabs.addTab("Patient", PatientPane);

        DoctorList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Dr. Robert House", "Dr. Rachna Singh", "Dr. Joe Smith" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        DoctorScrollList.setViewportView(DoctorList);

        DoctorSearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                DoctorSearchFieldKeyTyped(evt);
            }
        });

        DoctorCategorySelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "My Doctors", "All Doctors" }));
        DoctorCategorySelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoctorCategorySelectorActionPerformed(evt);
            }
        });

        DoctorEmailButton.setText("Email Doctor");
        DoctorEmailButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoctorEmailButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout DoctorPaneLayout = new javax.swing.GroupLayout(DoctorPane);
        DoctorPane.setLayout(DoctorPaneLayout);
        DoctorPaneLayout.setHorizontalGroup(
            DoctorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoctorPaneLayout.createSequentialGroup()
                .addGroup(DoctorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DoctorPaneLayout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(DoctorCategorySelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DoctorPaneLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(DoctorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(DoctorSearchField)
                            .addComponent(DoctorScrollList, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DoctorEmailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        DoctorPaneLayout.setVerticalGroup(
            DoctorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DoctorPaneLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(DoctorSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DoctorPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DoctorScrollList, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DoctorEmailButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DoctorCategorySelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        InfoTabs.addTab("Doctors", DoctorPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(SignedInAsNotifier)
                .addGap(18, 18, 18)
                .addComponent(LogoutButton))
            .addComponent(InfoTabs, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LogoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SignedInAsNotifier))
                .addGap(18, 18, 18)
                .addComponent(InfoTabs))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpdateDoctorScrollList()
    {
        Utilities.updateGenericPersonnelListing(DoctorCategorySelector,DoctorList,DoctorSearchField,allDoctors,myDoctors,doctorView);
    }
    
    private void UpdatePatientScrollList()
    {
       Utilities.updateGenericPersonnelListing(PatientCategorySelector,PatientList,PatientSearchField,allPatients,myPatients,patientView);
    }
    
    
    private void DoctorEmailButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoctorEmailButtonActionPerformed
        int i=DoctorList.getSelectedIndex();
        if (i==-1)
        {
            JOptionPane.showMessageDialog(this, "Please select a doctor to email.");
            return;
        }
        UserInfo doctor=doctorView.get(i);
        try
        {
            DoctorDB ddb=new DoctorDB(doctorView.get(i).username);
            if(!ddb.accountExists())
            {
                JOptionPane.showMessageDialog(this,"Unfortunately, the doctor selected no longer exists.");
                return;
            }
            String docEmail=ddb.getEmail();
            try
            {
                Runtime.getRuntime().exec("mailto:"+docEmail);
            }
            catch (IOException e)
            {
                JOptionPane.showMessageDialog(this,"Unable to launch mail program.\nPlease manually email: "+docEmail);
            }
            ddb.closeConnection();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(this,"Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
        }
    }//GEN-LAST:event_DoctorEmailButtonActionPerformed

    private void PatientAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientAddButtonActionPerformed
        new PatientRegister(this).setVisible(true);
    }//GEN-LAST:event_PatientAddButtonActionPerformed
   
    public void PatientAddedByRegisterForm(UserInfo patient)
    {
        allPatients.add(patient);
        myPatients.add(patient);
        Collections.sort((List)this.myPatients);
        Collections.sort((List)this.allPatients);
        UpdatePatientScrollList();
    }
    private void ViewInfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewInfoButtonActionPerformed
        try
        {
            String first = nurseDBManager.getFirstName(); 
            int i=PatientList.getSelectedIndex();
            if (i==-1)
            {
                JOptionPane.showMessageDialog(this, "Please select a patient to view.");
                return;
            }
            new PatientPanel_NurseView(loginName,loginPassword,patientView.get(i).username,first).setVisible(true);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this,e);
        }
                
    }//GEN-LAST:event_ViewInfoButtonActionPerformed

    private void DoctorSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DoctorSearchFieldKeyTyped
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                UpdateDoctorScrollList();
            }});
    }//GEN-LAST:event_DoctorSearchFieldKeyTyped

    private void PatientSearchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PatientSearchFieldKeyTyped
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                UpdatePatientScrollList();
            }});
    }//GEN-LAST:event_PatientSearchFieldKeyTyped

    private void PatientCategorySelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientCategorySelectorActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                UpdatePatientScrollList();
            }});
    }//GEN-LAST:event_PatientCategorySelectorActionPerformed

    private void DoctorCategorySelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoctorCategorySelectorActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                UpdateDoctorScrollList();
            }});
    }//GEN-LAST:event_DoctorCategorySelectorActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Do you want to log out?", "Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
        {
            try {nurseDBManager.closeConnection();}
            catch (SQLException e) {}
            
            this.dispose();
            new LoginScreen().setVisible(true);
        }  
    }//GEN-LAST:event_LogoutButtonActionPerformed

    private void PatientDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PatientDeleteButtonActionPerformed
       int i=PatientList.getSelectedIndex();
        if (i==-1) 
        {
            JOptionPane.showMessageDialog(this, "Please first select a patient to delete.");
            return;
        }
        
        UserInfo patient=patientView.get(i);
        int confirm=JOptionPane.showConfirmDialog(this, "Are you sure you want to delete the account "+
              patient.username+" belonging to " + patient.firstname + " " + patient.lastname +  "?");
        if (confirm==JOptionPane.YES_OPTION){
                myPatients.remove(patient);
                allPatients.remove(patient);
                PatientDB ndb = new PatientDB(patient.username);
                try
                {
                    if (ndb.accountExists())
                    {
                        ndb.deleteUser();
                        ndb.deleteTable();
                        UpdatePatientScrollList();
                        JOptionPane.showMessageDialog(this, "Successfully deleted "+patient.username+".");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Could not delete "+patient.username+" because the user has already been deleted.");
                    }
                    ndb.closeConnection();
                }
                catch(SQLException e)
                {
                    JOptionPane.showMessageDialog(this, "Could not delete "+patient.username+" due to network problems.\nDetails: "+e.getMessage());
                }
            }
    }//GEN-LAST:event_PatientDeleteButtonActionPerformed

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
                new NurseView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox DoctorCategorySelector;
    private javax.swing.JButton DoctorEmailButton;
    private javax.swing.JList DoctorList;
    private javax.swing.JPanel DoctorPane;
    private javax.swing.JScrollPane DoctorScrollList;
    private javax.swing.JTextField DoctorSearchField;
    private javax.swing.JTabbedPane InfoTabs;
    private javax.swing.JButton LogoutButton;
    private static javax.swing.JButton PatientAddButton;
    private javax.swing.JComboBox PatientCategorySelector;
    private static javax.swing.JButton PatientDeleteButton;
    private javax.swing.JList PatientList;
    private javax.swing.JPanel PatientPane;
    private javax.swing.JScrollPane PatientScrollList;
    private javax.swing.JTextField PatientSearchField;
    private javax.swing.JLabel SignedInAsNotifier;
    private static javax.swing.JButton ViewInfoButton;
    // End of variables declaration//GEN-END:variables
}
