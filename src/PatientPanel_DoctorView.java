
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.text.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scott
 */
public class PatientPanel_DoctorView extends javax.swing.JFrame {

    private final String HOST = "jdbc:derby://localhost:1527/information";
    private String uName = "healthworks";
    private String password = "healthworks";
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private Vector<String> bpV,bpLow,bpHigh,sugarV,sugarL,weightV,weightL,pres;
    private String bloodPress;
    private String user,docLogin,docPass;
    private PatientDB patient;
    /**
     * Creates new form PatientPanel
     */
    public PatientPanel_DoctorView() {
        initComponents();
    }
    
    public PatientPanel_DoctorView(String docLogin, String pass, String userName)
    {
        initComponents();
        try
        {
            user = userName;
            con = DriverManager.getConnection(HOST,uName,password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM PATIENTS WHERE USERNAME=\'"+user+"\'";
            rs = stmt.executeQuery(sql);
            rs.next();
            
            this.docLogin = docLogin;
            docPass = pass;
            userLabel.setText(docLogin);
            name.setText(rs.getString("Firstname") + " "+ rs.getString("Lastname"));
            address.setText("<html>"+rs.getString("Address")+",<BR>"+rs.getString("city")
                    +" "+rs.getString("state")+"-"+rs.getString("zip")+"<BR>Phone "+rs.getString("Primephone"));
            
            sql = "SELECT * FROM PATIENTS_"+userName;
            rs = stmt.executeQuery(sql);

            patient = new PatientDB(userName);
  
            bpV = patient.getBP();
            sugarV = patient.getSugar();
            weightV = patient.getWeight();
            pres = patient.getDrugs();
            
            bp.setText(patient.getLatestBP());
            sugar.setText(patient.getLatestSugar());
            weight.setText(patient.getLatestWeight());
            
            bpList.setListData(bpV);
            sugarList.setListData(sugarV);
            weightList.setListData(weightV);
            prescriptionList.setListData(pres);
            
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this,"Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            //this.dispose();
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

        PrescriptionsLabel = new javax.swing.JLabel();
        PrescriptionsPane = new javax.swing.JScrollPane();
        prescriptionList = new javax.swing.JList();
        CommentsObservationsLabel = new javax.swing.JLabel();
        AddObservationPane = new javax.swing.JScrollPane();
        AddObservationPaneTextArea = new javax.swing.JTextArea();
        SendObservationButton = new javax.swing.JButton();
        LatestBloodPressureLabel = new javax.swing.JLabel();
        LatestSugarLevelLabel = new javax.swing.JLabel();
        LatestWeightLabel = new javax.swing.JLabel();
        LatestStatisticsLabel = new javax.swing.JLabel();
        DataPane = new javax.swing.JTabbedPane();
        BloodPressurePanel = new javax.swing.JPanel();
        MonthlyAverageBloodPressureLabel = new javax.swing.JLabel();
        RecentBloodPressureEntriesPane = new javax.swing.JScrollPane();
        bpList = new javax.swing.JList();
        RecentBPEntriesLabel = new javax.swing.JLabel();
        CreateBPGraphButton = new javax.swing.JButton();
        SugarLevelPanel = new javax.swing.JPanel();
        CreateSLGraphButton = new javax.swing.JButton();
        SugarLevelPane = new javax.swing.JScrollPane();
        sugarList = new javax.swing.JList();
        MonthlySLAverageLabel = new javax.swing.JLabel();
        RecentSLEntriesLabel = new javax.swing.JLabel();
        WeightPanel = new javax.swing.JPanel();
        WeightCreateGraphButton = new javax.swing.JButton();
        WeightEntriesScrollPane = new javax.swing.JScrollPane();
        weightList = new javax.swing.JList();
        WeightMonthlyAverageLabel = new javax.swing.JLabel();
        RecentWeightEntriesLabel = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        CommentsViewScrollPane = new javax.swing.JScrollPane();
        CommentsViewPane = new javax.swing.JTextPane();
        back = new javax.swing.JButton();
        address = new javax.swing.JLabel();
        bp = new javax.swing.JLabel();
        sugar = new javax.swing.JLabel();
        weight = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        addPres = new javax.swing.JButton();
        deletePres = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(651, 752));
        setResizable(false);

        PrescriptionsLabel.setText("Prescriptions");

        prescriptionList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Isoniazid", "Thallium", "Benzodiazepine", "Atropine" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        PrescriptionsPane.setViewportView(prescriptionList);

        CommentsObservationsLabel.setText("Comments/Observations");

        AddObservationPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        AddObservationPaneTextArea.setColumns(20);
        AddObservationPaneTextArea.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        AddObservationPaneTextArea.setLineWrap(true);
        AddObservationPaneTextArea.setRows(5);
        AddObservationPaneTextArea.setText("Quisque a vestibulum tortor? ");
        AddObservationPaneTextArea.setWrapStyleWord(true);
        AddObservationPane.setViewportView(AddObservationPaneTextArea);

        SendObservationButton.setText("Send");

        LatestBloodPressureLabel.setText("Blood Pressure: ");

        LatestSugarLevelLabel.setText("Sugar Level: ");

        LatestWeightLabel.setText("Weight: ");

        LatestStatisticsLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LatestStatisticsLabel.setText("Latest Statistics");

        MonthlyAverageBloodPressureLabel.setText("30-day Average: 88.86 mmHg");

        bpList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "[2013-02-09 8:04 PM] 90 mmHg", "[2013-02-08 9:27 PM] 103.5 mmHg", "[2013-02-07 10:13 AM] 91.1 mmHg", "[2013-02-06 5:07 PM] 89.3 mmHg", "[2013-02-05 4:03 PM] 86.4 mmHg", " ", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        RecentBloodPressureEntriesPane.setViewportView(bpList);

        RecentBPEntriesLabel.setText("Recent Blood Pressure Entries");

        CreateBPGraphButton.setText("Create Graph");
        CreateBPGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateBPGraphButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BloodPressurePanelLayout = new javax.swing.GroupLayout(BloodPressurePanel);
        BloodPressurePanel.setLayout(BloodPressurePanelLayout);
        BloodPressurePanelLayout.setHorizontalGroup(
            BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                .addGroup(BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CreateBPGraphButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RecentBloodPressureEntriesPane, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                            .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                                .addGroup(BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MonthlyAverageBloodPressureLabel)
                                    .addComponent(RecentBPEntriesLabel))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        BloodPressurePanelLayout.setVerticalGroup(
            BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                .addComponent(MonthlyAverageBloodPressureLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreateBPGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RecentBPEntriesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RecentBloodPressureEntriesPane, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        DataPane.addTab("Blood Pressure", BloodPressurePanel);

        CreateSLGraphButton.setText("Create Graph");
        CreateSLGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateSLGraphButtonActionPerformed(evt);
            }
        });

        sugarList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "[2013-02-09 8:04 PM] 1.86 mmol/L", "[2013-02-08 9:27 PM] 1.93 mmol/L", "[2013-02-07 10:13 AM] 2.15 mmol/L", "[2013-02-06 5:07 PM] 2.3 mmol/L", "[2013-02-05 4:03 PM] 1.6 mmol/L", " ", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        SugarLevelPane.setViewportView(sugarList);

        MonthlySLAverageLabel.setText("30-day Average: 2.97 mmol/L");

        RecentSLEntriesLabel.setText("Recent Sugar Level Entries");

        javax.swing.GroupLayout SugarLevelPanelLayout = new javax.swing.GroupLayout(SugarLevelPanel);
        SugarLevelPanel.setLayout(SugarLevelPanelLayout);
        SugarLevelPanelLayout.setHorizontalGroup(
            SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SugarLevelPanelLayout.createSequentialGroup()
                .addGroup(SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(SugarLevelPane, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, SugarLevelPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(RecentSLEntriesLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(CreateSLGraphButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MonthlySLAverageLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SugarLevelPanelLayout.setVerticalGroup(
            SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                .addComponent(MonthlySLAverageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreateSLGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RecentSLEntriesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SugarLevelPane, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        DataPane.addTab("Sugar Level", SugarLevelPanel);

        WeightCreateGraphButton.setText("Create Graph");
        WeightCreateGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WeightCreateGraphButtonActionPerformed(evt);
            }
        });

        weightList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "[2013-02-09 8:04 PM] 60 kg", "[2013-02-08 9:27 PM] 58 kg", "[2013-02-07 10:13 AM] 62 kg", "[2013-02-06 5:07 PM] 64 kg", "[2013-02-05 4:03 PM] 59 kg", " ", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        WeightEntriesScrollPane.setViewportView(weightList);

        WeightMonthlyAverageLabel.setText("30-day Average: 58 kg");

        RecentWeightEntriesLabel.setText("Recent Weight Entries");

        javax.swing.GroupLayout WeightPanelLayout = new javax.swing.GroupLayout(WeightPanel);
        WeightPanel.setLayout(WeightPanelLayout);
        WeightPanelLayout.setHorizontalGroup(
            WeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeightPanelLayout.createSequentialGroup()
                .addGroup(WeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WeightPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(WeightEntriesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                    .addGroup(WeightPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(WeightMonthlyAverageLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(WeightCreateGraphButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(WeightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RecentWeightEntriesLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        WeightPanelLayout.setVerticalGroup(
            WeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeightPanelLayout.createSequentialGroup()
                .addComponent(WeightMonthlyAverageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WeightCreateGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RecentWeightEntriesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WeightEntriesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addContainerGap())
        );

        DataPane.addTab("Weight", WeightPanel);

        name.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        name.setText("Luis Gonzalez");

        CommentsViewPane.setEditable(false);
        CommentsViewPane.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        CommentsViewPane.setText("[DrHouse] Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut accumsan orci in tellus rutrum tempus. \n[You] Donec non quam justo, at scelerisque ante? \n[DrHouse] Aliquam erat volutpat. Donec porttitor, elit sit amet accumsan consequat, massa augue varius nulla, quis ornare quam quam a erat.");
        CommentsViewScrollPane.setViewportView(CommentsViewPane);

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        address.setText("<html>200 S Ash Ave,<BR>Phoenix AZ - 85011<BR>Phone (480)-555-5555<html>");

        bp.setText("--");

        sugar.setText("--");

        weight.setText("--");

        userLabel.setText("docID");

        addPres.setText("Add");
        addPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPresActionPerformed(evt);
            }
        });

        deletePres.setText("Delete");
        deletePres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(SendObservationButton)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(LatestSugarLevelLabel)
                                    .addComponent(LatestWeightLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bp)
                                    .addComponent(sugar)
                                    .addComponent(weight)))
                            .addComponent(LatestStatisticsLabel)
                            .addComponent(DataPane, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(LatestBloodPressureLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(name)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CommentsViewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(AddObservationPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CommentsObservationsLabel)
                                    .addComponent(PrescriptionsLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(PrescriptionsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(addPres)
                                            .addComponent(deletePres))))
                                .addContainerGap(33, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(userLabel)
                                .addGap(17, 17, 17))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(back)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userLabel)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(back)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(name)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PrescriptionsLabel)))))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addPres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deletePres)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CommentsObservationsLabel)
                        .addGap(7, 7, 7)
                        .addComponent(CommentsViewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddObservationPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PrescriptionsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LatestStatisticsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LatestBloodPressureLabel)
                            .addComponent(bp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LatestSugarLevelLabel)
                            .addComponent(sugar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(LatestWeightLabel)
                            .addComponent(weight))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(DataPane, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SendObservationButton)
                .addGap(31, 31, 31))
        );

        DataPane.getAccessibleContext().setAccessibleName("Blood Pressure");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CreateBPGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateBPGraphButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CreateBPGraphButtonActionPerformed

    private void CreateSLGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateSLGraphButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CreateSLGraphButtonActionPerformed

    private void WeightCreateGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WeightCreateGraphButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WeightCreateGraphButtonActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed

            this.dispose();
            new DoctorView(docLogin,docPass).setVisible(true);  
    }//GEN-LAST:event_backActionPerformed

    private void addPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPresActionPerformed
        String presEntry;
        JFrame frame = new JFrame();
        try
        {
            presEntry = JOptionPane.showInputDialog(frame, "Enter new prescription:");
            
            pres.add(presEntry);
            prescriptionList.setListData(pres);
            patient.setDrugs(presEntry);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,"Enter only numbers"); 
        }
    }//GEN-LAST:event_addPresActionPerformed

    private void deletePresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePresActionPerformed
        String select = (String)prescriptionList.getSelectedValue();
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this?", "Delete Prescription",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
        {
            if(patient.searchAndDeletePrescription(select))
            {
                JOptionPane.showMessageDialog(this, "Entry Deleted");
                pres = patient.getDrugs();
                prescriptionList.setListData(pres);
            }
            else
                JOptionPane.showMessageDialog(this, "Entry could not be deleted");
        }
    }//GEN-LAST:event_deletePresActionPerformed

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
            java.util.logging.Logger.getLogger(PatientPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientPanel_DoctorView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane AddObservationPane;
    private javax.swing.JTextArea AddObservationPaneTextArea;
    private javax.swing.JPanel BloodPressurePanel;
    private javax.swing.JLabel CommentsObservationsLabel;
    private javax.swing.JTextPane CommentsViewPane;
    private javax.swing.JScrollPane CommentsViewScrollPane;
    private javax.swing.JButton CreateBPGraphButton;
    private javax.swing.JButton CreateSLGraphButton;
    private javax.swing.JTabbedPane DataPane;
    private javax.swing.JLabel LatestBloodPressureLabel;
    private javax.swing.JLabel LatestStatisticsLabel;
    private javax.swing.JLabel LatestSugarLevelLabel;
    private javax.swing.JLabel LatestWeightLabel;
    private javax.swing.JLabel MonthlyAverageBloodPressureLabel;
    private javax.swing.JLabel MonthlySLAverageLabel;
    private javax.swing.JLabel PrescriptionsLabel;
    private javax.swing.JScrollPane PrescriptionsPane;
    private javax.swing.JLabel RecentBPEntriesLabel;
    private javax.swing.JScrollPane RecentBloodPressureEntriesPane;
    private javax.swing.JLabel RecentSLEntriesLabel;
    private javax.swing.JLabel RecentWeightEntriesLabel;
    private javax.swing.JButton SendObservationButton;
    private javax.swing.JScrollPane SugarLevelPane;
    private javax.swing.JPanel SugarLevelPanel;
    private javax.swing.JButton WeightCreateGraphButton;
    private javax.swing.JScrollPane WeightEntriesScrollPane;
    private javax.swing.JLabel WeightMonthlyAverageLabel;
    private javax.swing.JPanel WeightPanel;
    private javax.swing.JButton addPres;
    private static javax.swing.JLabel address;
    protected static javax.swing.JButton back;
    private static javax.swing.JLabel bp;
    private javax.swing.JList bpList;
    private javax.swing.JButton deletePres;
    private static javax.swing.JLabel name;
    private static javax.swing.JList prescriptionList;
    private static javax.swing.JLabel sugar;
    private static javax.swing.JList sugarList;
    private javax.swing.JLabel userLabel;
    private static javax.swing.JLabel weight;
    private javax.swing.JList weightList;
    // End of variables declaration//GEN-END:variables
}
