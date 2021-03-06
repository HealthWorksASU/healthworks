
import java.sql.*;
import javax.swing.*;
import java.util.*;
import java.text.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import org.jdesktop.swingx.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Scott
 */
public class PatientPanel extends javax.swing.JFrame {

    private final String HOST = "jdbc:derby://localhost:1527/information";
    private String uName = "healthworks";
    private String password = "healthworks";
    //private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private Vector<String> bpV,bpLow,bpHigh,sugarV,sugarL,weightV,weightL,pres,obs;
    private String bloodPress;
    private String user;
    private PatientDB patient;
    private String sAvg,bpHighAvg,bpLowAvg,wAvg;
    DecimalFormat fmt = new DecimalFormat("#.##");
    private JXMonthView mv;
    private boolean reschedFlag;
    private String nextAppointment,confirm;
    /**
     * Creates new form PatientPanel
     */
    public PatientPanel() {
        initComponents();
        mv = datePick.getMonthView();
        java.util.Date da = new java.util.Date();
        mv.setLowerBound(da);
        int y,d;
        int m = da.getMonth();
        y= da.getYear();
        d = da.getDate();
        System.out.println(da.toString());
        System.out.println(d + " "+ m+ " "+y);
        switch(m)
        {
            case 9:
                m = 0;
                y++;
                break;
            case 10:
                m = 1;
                y++;
                break;
            case 11:
                m=2;
                y++;
                break;
            default:
                m+=3;
        }        
            System.out.println(new java.util.Date(y,m,d).toString());
            mv.setUpperBound(new java.util.Date(y,m,d));

    }
    
    public PatientPanel(String userName, String pass)
    {
        initComponents();
        try
        {
            user = userName;
            Connection con = DriverManager.getConnection(HOST,uName,password);
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM PATIENTS WHERE USERNAME=\'"+user+"\'";
            rs = stmt.executeQuery(sql);
            rs.next();
            
            name.setText(rs.getString("Firstname") + " "+ rs.getString("Lastname"));
            address.setText("<html>"+rs.getString("Address")+",<BR>"+rs.getString("city")
                    +" "+rs.getString("state")+"-"+rs.getString("zip")+"<BR>Phone "+rs.getString("Primephone"));
            
            sql = "SELECT * FROM PATIENTS_"+userName;
            rs = stmt.executeQuery(sql);
            
            con.close();
            
            CommentsViewPane.setText("");
            AddObservationPaneTextArea.setText("");
            patient = new PatientDB(userName);
  
            bpV = patient.getBP();
            sugarV = patient.getSugar();
            weightV = patient.getWeight();
            pres = patient.getDrugs();
            obs = patient.getObservations();
            sAvg = fmt.format(patient.getSugarAverage());
            wAvg = fmt.format(patient.getWeightAverage());
            double[] a = patient.getBPAverage();
            bpHighAvg = fmt.format(a[1]);
            bpLowAvg = fmt.format(a[0]);
            
            bp.setText(patient.getLatestBP());
            sugar.setText(patient.getLatestSugar());
            weight.setText(patient.getLatestWeight());
            
            patient.closeConnection(); //We can close it here for efficiency because it'll simply reopen if necessary
            
            Iterator i = obs.iterator();
            while(i.hasNext())
                CommentsViewPane.getDocument().insertString(CommentsViewPane.getCaretPosition(), (String)i.next(), null);
            sugarAvg.setText(sAvg);
            bpAve.setText(bpHighAvg + "/" + bpLowAvg);
            weightAvg.setText(wAvg);
            
            bpList.setListData(bpV);
            sugarList.setListData(sugarV);
            weightList.setListData(weightV);
            PrescriptionList.setListData(pres);  
            
            mv = datePick.getMonthView();
            java.util.Date da = new java.util.Date();
            int y,d;
            int m = da.getMonth();
            y= da.getYear();
            d = da.getDate();
            mv.setLowerBound(new java.util.Date(y,m,d+1));
            System.out.println(da.toString());
            System.out.println(d + " "+ m+ " "+y);
            switch(m)
            {
                case 9:
                    m = 0;
                    y++;
                    break;
                case 10:
                    m = 1;
                    y++;
                    break;
                case 11:
                    m=2;
                    y++;
                    break;
                default:
                    m+=3;
            }        
            System.out.println(new java.util.Date(y,m,d).toString());
            mv.setUpperBound(new java.util.Date(y,m,d));
            
            nextAppointment = patient.getNextAppointment();
            if(nextAppointment.equals("--"))
            {
                confirm = "";
                cancelApp.setEnabled(false);
            }
            else
            {
                confirm = patient.getAppointmentConfirmation();
                cancelApp.setEnabled(true);
            }
            
            nextApp.setText(nextAppointment + confirm);
            lastApp.setText(patient.getLastAppointment());
            
            popupDeniedAppointment();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this,"Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
            //this.dispose();
            return;
        }
        catch(BadLocationException e)
        {
            JOptionPane.showMessageDialog(this, e);
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
        PrescriptionList = new javax.swing.JList();
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
        newBP = new javax.swing.JButton();
        CreateBPGraphButton = new javax.swing.JButton();
        deleteBP = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        bpAve = new javax.swing.JLabel();
        SugarLevelPanel = new javax.swing.JPanel();
        CreateSLGraphButton = new javax.swing.JButton();
        SugarLevelPane = new javax.swing.JScrollPane();
        sugarList = new javax.swing.JList();
        MonthlySLAverageLabel = new javax.swing.JLabel();
        RecentSLEntriesLabel = new javax.swing.JLabel();
        newSugar = new javax.swing.JButton();
        deleteSugar = new javax.swing.JButton();
        sugarAvg = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        WeightPanel = new javax.swing.JPanel();
        WeightCreateGraphButton = new javax.swing.JButton();
        WeightEntriesScrollPane = new javax.swing.JScrollPane();
        weightList = new javax.swing.JList();
        WeightMonthlyAverageLabel = new javax.swing.JLabel();
        RecentWeightEntriesLabel = new javax.swing.JLabel();
        newWeight = new javax.swing.JButton();
        deleteWeight = new javax.swing.JButton();
        weightAvg = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        CommentsViewScrollPane = new javax.swing.JScrollPane();
        CommentsViewPane = new javax.swing.JTextPane();
        logout = new javax.swing.JButton();
        address = new javax.swing.JLabel();
        updatePersonalInfo = new javax.swing.JButton();
        bp = new javax.swing.JLabel();
        sugar = new javax.swing.JLabel();
        weight = new javax.swing.JLabel();
        scheduleApp = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        datePick = new org.jdesktop.swingx.JXDatePicker();
        nextApp = new javax.swing.JLabel();
        lastApp = new javax.swing.JLabel();
        cancelApp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(670, 752));
        setPreferredSize(new java.awt.Dimension(670, 752));
        setResizable(false);
        setSize(new java.awt.Dimension(670, 752));

        PrescriptionsLabel.setText("Prescriptions");

        PrescriptionList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Isoniazid", "Thallium", "Benzodiazepine", "Atropine" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        PrescriptionsPane.setViewportView(PrescriptionList);

        CommentsObservationsLabel.setText("Comments/Observations");

        AddObservationPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        AddObservationPaneTextArea.setColumns(20);
        AddObservationPaneTextArea.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        AddObservationPaneTextArea.setLineWrap(true);
        AddObservationPaneTextArea.setRows(5);
        AddObservationPaneTextArea.setText("Quisque a vestibulum tortor? ");
        AddObservationPaneTextArea.setWrapStyleWord(true);
        AddObservationPaneTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AddObservationPaneTextAreaKeyPressed(evt);
            }
        });
        AddObservationPane.setViewportView(AddObservationPaneTextArea);

        SendObservationButton.setText("Send");
        SendObservationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SendObservationButtonActionPerformed(evt);
            }
        });

        LatestBloodPressureLabel.setText("Blood Pressure: ");

        LatestSugarLevelLabel.setText("Sugar Level: ");

        LatestWeightLabel.setText("Weight: ");

        LatestStatisticsLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        LatestStatisticsLabel.setText("Latest Statistics");

        MonthlyAverageBloodPressureLabel.setText("10 Entry Average:");

        bpList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "[2013-02-09 8:04 PM] 90 mmHg", "[2013-02-08 9:27 PM] 103.5 mmHg", "[2013-02-07 10:13 AM] 91.1 mmHg", "[2013-02-06 5:07 PM] 89.3 mmHg", "[2013-02-05 4:03 PM] 86.4 mmHg", " ", " " };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        RecentBloodPressureEntriesPane.setViewportView(bpList);

        RecentBPEntriesLabel.setText("Recent Blood Pressure Entries");

        newBP.setText("New Entry");
        newBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBPActionPerformed(evt);
            }
        });

        CreateBPGraphButton.setText("Create Graph");
        CreateBPGraphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateBPGraphButtonActionPerformed(evt);
            }
        });

        deleteBP.setText("Delete Selected");
        deleteBP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBPActionPerformed(evt);
            }
        });

        jLabel1.setText("mmHg");

        bpAve.setText("jLabel3");

        javax.swing.GroupLayout BloodPressurePanelLayout = new javax.swing.GroupLayout(BloodPressurePanel);
        BloodPressurePanel.setLayout(BloodPressurePanelLayout);
        BloodPressurePanelLayout.setHorizontalGroup(
            BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BloodPressurePanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(newBP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(CreateBPGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
            .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RecentBloodPressureEntriesPane)
                .addContainerGap())
            .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                .addGroup(BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(MonthlyAverageBloodPressureLabel)
                        .addGap(7, 7, 7)
                        .addComponent(bpAve)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(RecentBPEntriesLabel))
                    .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(deleteBP)))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        BloodPressurePanelLayout.setVerticalGroup(
            BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BloodPressurePanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MonthlyAverageBloodPressureLabel)
                    .addComponent(jLabel1)
                    .addComponent(bpAve))
                .addGap(6, 6, 6)
                .addGroup(BloodPressurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newBP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateBPGraphButton, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addComponent(RecentBPEntriesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RecentBloodPressureEntriesPane, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteBP))
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

        MonthlySLAverageLabel.setText("10 Entry Average:");

        RecentSLEntriesLabel.setText("Recent Sugar Level Entries");

        newSugar.setText("New Entry");
        newSugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newSugarActionPerformed(evt);
            }
        });

        deleteSugar.setText("Delete Selected");
        deleteSugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSugarActionPerformed(evt);
            }
        });

        sugarAvg.setText("jLabel2");

        jLabel2.setText("mmol/L");

        javax.swing.GroupLayout SugarLevelPanelLayout = new javax.swing.GroupLayout(SugarLevelPanel);
        SugarLevelPanel.setLayout(SugarLevelPanelLayout);
        SugarLevelPanelLayout.setHorizontalGroup(
            SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                .addGroup(SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                        .addContainerGap(212, Short.MAX_VALUE)
                        .addComponent(CreateSLGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                        .addGroup(SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(RecentSLEntriesLabel))
                            .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(newSugar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(MonthlySLAverageLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sugarAvg)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)))
                        .addGap(0, 147, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SugarLevelPanelLayout.createSequentialGroup()
                .addComponent(SugarLevelPane)
                .addContainerGap())
            .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                .addComponent(deleteSugar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SugarLevelPanelLayout.setVerticalGroup(
            SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SugarLevelPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MonthlySLAverageLabel)
                    .addComponent(sugarAvg)
                    .addComponent(jLabel2))
                .addGap(7, 7, 7)
                .addGroup(SugarLevelPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(CreateSLGraphButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(newSugar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(RecentSLEntriesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SugarLevelPane, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteSugar))
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

        WeightMonthlyAverageLabel.setText("10 Entry Average:");

        RecentWeightEntriesLabel.setText("Recent Weight Entries");

        newWeight.setText("New Entry");
        newWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newWeightActionPerformed(evt);
            }
        });

        deleteWeight.setText("Delete Selected");
        deleteWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteWeightActionPerformed(evt);
            }
        });

        weightAvg.setText("jLabel3");

        jLabel4.setText("kg");

        javax.swing.GroupLayout WeightPanelLayout = new javax.swing.GroupLayout(WeightPanel);
        WeightPanel.setLayout(WeightPanelLayout);
        WeightPanelLayout.setHorizontalGroup(
            WeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeightPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(WeightMonthlyAverageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(weightAvg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, WeightPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(WeightCreateGraphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(WeightPanelLayout.createSequentialGroup()
                .addGroup(WeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(WeightPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(WeightEntriesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))
                    .addGroup(WeightPanelLayout.createSequentialGroup()
                        .addGroup(WeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(WeightPanelLayout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(newWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(WeightPanelLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(RecentWeightEntriesLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(WeightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deleteWeight)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        WeightPanelLayout.setVerticalGroup(
            WeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(WeightPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(WeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(WeightMonthlyAverageLabel)
                    .addComponent(weightAvg)
                    .addComponent(jLabel4))
                .addGap(7, 7, 7)
                .addGroup(WeightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(WeightCreateGraphButton, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(newWeight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(RecentWeightEntriesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(WeightEntriesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteWeight))
        );

        DataPane.addTab("Weight", WeightPanel);

        name.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        name.setText("Luis Gonzalez");

        CommentsViewPane.setEditable(false);
        CommentsViewPane.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        CommentsViewPane.setText("[DrHouse] Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut accumsan orci in tellus rutrum tempus. \n[You] Donec non quam justo, at scelerisque ante? \n[DrHouse] Aliquam erat volutpat. Donec porttitor, elit sit amet accumsan consequat, massa augue varius nulla, quis ornare quam quam a erat.");
        CommentsViewScrollPane.setViewportView(CommentsViewPane);

        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        address.setText("<html>200 S Ash Ave,<BR>Phoenix AZ - 85011<BR>Phone (480)-555-5555<html>");

        updatePersonalInfo.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        updatePersonalInfo.setText("Edit Personal Info");
        updatePersonalInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePersonalInfoActionPerformed(evt);
            }
        });

        bp.setText("--");

        sugar.setText("--");

        weight.setText("--");

        scheduleApp.setText("Schedule Appointment");
        scheduleApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleAppActionPerformed(evt);
            }
        });

        jLabel3.setText("Next Appointment:");

        jLabel5.setText("Last Appointment:");

        datePick.setEnabled(false);
        datePick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePickActionPerformed(evt);
            }
        });

        nextApp.setText("--");

        lastApp.setText("--");

        cancelApp.setText("Cancel Next Appointment");
        cancelApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAppActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updatePersonalInfo)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(LatestSugarLevelLabel)
                                            .addComponent(LatestBloodPressureLabel)
                                            .addComponent(LatestWeightLabel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(bp)
                                            .addComponent(sugar)
                                            .addComponent(weight)))
                                    .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(65, 65, 65)
                        .addComponent(name))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(nextApp))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(lastApp))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(scheduleApp)
                                        .addGap(18, 18, 18)
                                        .addComponent(datePick, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(DataPane, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(LatestStatisticsLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(SendObservationButton)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(AddObservationPane, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                    .addComponent(CommentsViewScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(logout)
                                    .addComponent(PrescriptionsPane, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CommentsObservationsLabel, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PrescriptionsLabel, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addComponent(cancelApp))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(PrescriptionsLabel))
                            .addComponent(logout))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PrescriptionsPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(name))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updatePersonalInfo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(scheduleApp)
                            .addComponent(datePick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nextApp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(lastApp)))
                    .addComponent(cancelApp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DataPane, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(CommentsObservationsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CommentsViewScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddObservationPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SendObservationButton)))
                .addGap(19, 19, 19))
        );

        DataPane.getAccessibleContext().setAccessibleName("Blood Pressure");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void popupDeniedAppointment()
    {
        if(confirm.equals("(Denied)"))
            JOptionPane.showMessageDialog(this, "Your appointment on "+nextAppointment+"\nhas been denied because your doctor is busy then. \nPlease reschedule your appointment as soon as possible. Thank you");
    }
    private void CreateBPGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateBPGraphButtonActionPerformed
        // TODO add your handling code here:
        new CreateGraph(new ArrayList<String>(bpV),new ArrayList<String>(weightV),new ArrayList<String>(sugarV),0).setVisible(true);
    }//GEN-LAST:event_CreateBPGraphButtonActionPerformed

    private void CreateSLGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateSLGraphButtonActionPerformed
        // TODO add your handling code here:
        new CreateGraph(new ArrayList<String>(bpV),new ArrayList<String>(weightV),new ArrayList<String>(sugarV),2).setVisible(true);
    }//GEN-LAST:event_CreateSLGraphButtonActionPerformed

    private void WeightCreateGraphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WeightCreateGraphButtonActionPerformed
        // TODO add your handling code here:
        new CreateGraph(new ArrayList<String>(bpV),new ArrayList<String>(weightV),new ArrayList<String>(sugarV),1).setVisible(true);
    }//GEN-LAST:event_WeightCreateGraphButtonActionPerformed

    private void newBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBPActionPerformed
        //JOptionPane to be implemented here
        String lower,upper;
        JFrame frame = new JFrame();
        try
        {
            lower = JOptionPane.showInputDialog(frame, "Enter lower blood pressure bound (mmHg):");
            while(Double.parseDouble(lower)<50 || Double.parseDouble(lower) > 230)
                lower = JOptionPane.showInputDialog(frame, "Enter a VALID LOWER blood pressure(50-230) mmHg:");
            
            upper = JOptionPane.showInputDialog(frame, "Enter an upper blood pressure bound (mmHg):");
            while(Double.parseDouble(upper)<50 || Double.parseDouble(upper)>230 || (Double.parseDouble(upper) < Double.parseDouble(lower)))
                upper = JOptionPane.showInputDialog(frame, "Enter a VALID UPPER blood pressure(50-230) mmHg:");
            
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd hh:mm a").format(Calendar.getInstance().getTime());
            String time  = "["+timeStamp+"] "+lower+"/"+upper+"mmHg";
            bpV.add(time);
            //bpLow.add(lower);
            //bpHigh.add(upper);
            bpList.setListData(bpV);
            patient.setBP(time,upper,lower);
            bp.setText(patient.getLatestBP());
            double[] a = patient.getBPAverage();
            bpHighAvg = fmt.format(a[1]);
            bpLowAvg = fmt.format(a[0]);
            bpAve.setText(bpHighAvg + "/" + bpLowAvg);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,"Enter only numbers"); 
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_newBPActionPerformed

    private void newSugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newSugarActionPerformed
        String sugarEntry;
        JFrame frame = new JFrame();
        try
        {
            sugarEntry = JOptionPane.showInputDialog(frame, "Enter sugar level (mmol/L):");
            while(Double.parseDouble(sugarEntry)<0)
                sugarEntry = JOptionPane.showInputDialog(frame, "Enter a VALID sugar level mmol/L:");
            
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd hh:mm a").format(Calendar.getInstance().getTime());
            String time = "["+timeStamp+"] "+sugarEntry+"mmol/L";
            sugarV.add(time);
            //sugarL.add(sugarEntry);
            sugarList.setListData(sugarV);
            patient.setSugar(time,sugarEntry);
            //patient.setSugarTime(sugarV);
            sugar.setText(patient.getLatestSugar());
            sAvg = fmt.format(patient.getSugarAverage());
            sugarAvg.setText(sAvg);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,"Enter only numbers"); 
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_newSugarActionPerformed

    private void newWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newWeightActionPerformed
       String weightEntry;
        JFrame frame = new JFrame();
        try
        {
            weightEntry = JOptionPane.showInputDialog(frame, "Enter weight (kg):");
            while(Double.parseDouble(weightEntry)<0 || Double.parseDouble(weightEntry) > 400)
                weightEntry = JOptionPane.showInputDialog(frame, "Enter a VALID weight kg:");
            
            String timeStamp = new SimpleDateFormat("yyyy/MM/dd hh:mm a").format(Calendar.getInstance().getTime());
            String time = "["+timeStamp+"] "+weightEntry+"kg";
            weightV.add(time);
            //weightL.add(weightEntry);
            this.weightList.setListData(weightV);
            patient.setWeight(weightEntry,time);
            weight.setText(patient.getLatestWeight());
            wAvg = fmt.format(patient.getWeightAverage());
            weightAvg.setText(wAvg);
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(this,"Enter only numbers"); 
        }
        catch(SQLException e)
        {
            System.out.println("Unable to establish SQL connection. Please check your network settings.\nDetails: "+e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_newWeightActionPerformed

    private void updatePersonalInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePersonalInfoActionPerformed
        new PatientEditInfo(user).setVisible(true);
    }//GEN-LAST:event_updatePersonalInfoActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Do you want to log out?", "Logout",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
        {
            try {patient.closeConnection();}
            catch (SQLException e) {}
            
            this.dispose();
            new LoginScreen().setVisible(true);
        }  
    }//GEN-LAST:event_logoutActionPerformed

    private void deleteWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteWeightActionPerformed
        String select = (String)weightList.getSelectedValue();
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this?", "Delete Weight Entry",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
        {
            if(patient.searchAndDeleteWeight(select))
            {
                JOptionPane.showMessageDialog(this, "Entry Deleted");
                weightV = patient.getWeight();
                weightList.setListData(weightV);
                weight.setText(patient.getLatestWeight());
                wAvg = fmt.format(patient.getWeightAverage());
                weightAvg.setText(wAvg);
            }
            else
                JOptionPane.showMessageDialog(this, "Entry could not be deleted");
        }
    }//GEN-LAST:event_deleteWeightActionPerformed

    private void deleteSugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSugarActionPerformed
        String select = (String)sugarList.getSelectedValue();
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this?", "Delete Sugar Entry",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
        {
            if(patient.searchAndDeleteSugar(select))
            {
                JOptionPane.showMessageDialog(this, "Entry Deleted");
                sugarV = patient.getSugar();
                sugarList.setListData(sugarV);
                sugar.setText(patient.getLatestSugar());
                sAvg = fmt.format(patient.getSugarAverage());
                sugarAvg.setText(sAvg);
            }
            else
                JOptionPane.showMessageDialog(this, "Entry could not be deleted");
        }
    }//GEN-LAST:event_deleteSugarActionPerformed

    private void deleteBPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBPActionPerformed
        String select = (String)bpList.getSelectedValue();
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this?", "Delete BP Entry",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
        {
            if(patient.searchAndDeleteBP(select))
            {
                JOptionPane.showMessageDialog(this, "Entry Deleted");
                bpV = patient.getBP();
                bpList.setListData(bpV);
                bp.setText(patient.getLatestBP());
                double[] a = patient.getBPAverage();
                bpHighAvg = fmt.format(a[1]);
                bpLowAvg = fmt.format(a[0]);
                bpAve.setText(bpHighAvg + "/" + bpLowAvg);
            }
            else
                JOptionPane.showMessageDialog(this, "Entry could not be deleted");
        }
    }//GEN-LAST:event_deleteBPActionPerformed

    private void AddObservationPaneTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddObservationPaneTextAreaKeyPressed
        try
        {
            String chat = AddObservationPaneTextArea.getText();
            if(evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER)
            {
                if(chat.equals(""))
                    JOptionPane.showMessageDialog(this, "Please enter an observasation first");
                else
                {
                    chat = "\n["+patient.getFirstName()+"] "+chat;
                    obs.add(chat);
                    CommentsViewPane.getDocument().insertString(CommentsViewPane.getCaretPosition(),chat,null);
                    AddObservationPaneTextArea.setText("");
                    patient.setObservations(chat);
                }
            }
        }
        catch(BadLocationException e)
        {
            JOptionPane.showMessageDialog(this, e);
        } 
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_AddObservationPaneTextAreaKeyPressed

    private void SendObservationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SendObservationButtonActionPerformed
        try
        {
            String chat = AddObservationPaneTextArea.getText();
            if(chat.equals(""))
                JOptionPane.showMessageDialog(this, "Please enter in the field first");
            else
            {
                chat = "\n["+patient.getFirstName()+"] "+chat;
                obs.add(chat);
                CommentsViewPane.getDocument().insertString(CommentsViewPane.getCaretPosition(),chat,null);
                AddObservationPaneTextArea.setText("");
                patient.setObservations(chat);
            }
        }
        catch(BadLocationException e)
        {
            JOptionPane.showMessageDialog(this, e);
        } 
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_SendObservationButtonActionPerformed

    private void scheduleAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleAppActionPerformed
        boolean flag = true; reschedFlag = false;
        if(confirm.equals("(Denied)"))
            reschedFlag = true;
        else if(!nextApp.getText().equals("--"))
        {
            int response = JOptionPane.showConfirmDialog(this, "Do you want Reschedule your appointment?", "Reschedule",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (response == JOptionPane.NO_OPTION)
                flag = false;
            else
                reschedFlag = true;
        }
        
        if(flag)
        {
            datePick.setEnabled(true);
            scheduleApp.setEnabled(false);
        }
        
    }//GEN-LAST:event_scheduleAppActionPerformed

    private void datePickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePickActionPerformed
        java.util.Date d = mv.getSelectionDate();
        String dat = Integer.toString(d.getMonth()+1)+"/"+Integer.toString(d.getDate())+"/"+Integer.toString(d.getYear()+1900);
        
        if(d.getDay() == 0 || d.getDay() == 6)
        {
            JOptionPane.showMessageDialog(this, "The clinic is closed on weekends, please select a week day.");
            scheduleApp.setEnabled(true);
            datePick.setEnabled(false);
            return;
        }
        
        String[] times = {"8:00 AM","9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "2:00 PM", "3:00 PM"};
        String timeSelect;
        try
        {
            timeSelect = (String)JOptionPane.showInputDialog(this, ("Select an appointment time on "+datePick.getEditor().getText()), 
                    "Appointment Time", JOptionPane.QUESTION_MESSAGE,null, times,"Select a time");
            
            if(timeSelect == null)
            {
                scheduleApp.setEnabled(true);
                datePick.setEnabled(false);
                return;
            }
            
            if(reschedFlag)
            {
                if(!patient.deleteAppointment(nextAppointment))
                {
                    JOptionPane.showMessageDialog(this, "Appointment could not be rescheduled");
                    scheduleApp.setEnabled(true);
                    datePick.setEnabled(false);
                    return;
                }
                reschedFlag = false;
            }
            
            patient.scheduleAppointment((dat+" "+timeSelect), "Not Confirmed");
            nextAppointment = patient.getNextAppointment();
            if(nextAppointment.equals("--"))
            {
                confirm = "";
                cancelApp.setEnabled(false);
            }
            else
            {
                confirm = patient.getAppointmentConfirmation();
                cancelApp.setEnabled(true);
            }
            
            nextApp.setText(nextAppointment + confirm);
            
            scheduleApp.setEnabled(true);
            datePick.setEnabled(false);
        }    
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, e);
        }
        catch(NullPointerException e) 
        {
            JOptionPane.showMessageDialog(this, e);
            scheduleApp.setEnabled(true);
            datePick.setEnabled(false);
        }
    }//GEN-LAST:event_datePickActionPerformed

    private void cancelAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAppActionPerformed
        int response = JOptionPane.showConfirmDialog(this, "Do you want to cancel the appointment?", "Cancel Appointment",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION)
        {
            if(patient.deleteAppointment(nextAppointment))
            {
                nextAppointment = patient.getNextAppointment();
                if(nextAppointment.equals("--"))
                    confirm = "";
                else
                    confirm = patient.getAppointmentConfirmation();
                nextApp.setText(nextAppointment + confirm);
                scheduleApp.setEnabled(true);
                datePick.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Appointment cancelled");
                cancelApp.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cancelAppActionPerformed

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
                new PatientPanel().setVisible(true);
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
    private static javax.swing.JList PrescriptionList;
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
    private static javax.swing.JLabel address;
    private static javax.swing.JLabel bp;
    private javax.swing.JLabel bpAve;
    private javax.swing.JList bpList;
    private javax.swing.JButton cancelApp;
    private org.jdesktop.swingx.JXDatePicker datePick;
    private javax.swing.JButton deleteBP;
    private javax.swing.JButton deleteSugar;
    private static javax.swing.JButton deleteWeight;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lastApp;
    protected static javax.swing.JButton logout;
    private static javax.swing.JLabel name;
    private javax.swing.JButton newBP;
    private static javax.swing.JButton newSugar;
    private static javax.swing.JButton newWeight;
    private javax.swing.JLabel nextApp;
    private javax.swing.JButton scheduleApp;
    private static javax.swing.JLabel sugar;
    private javax.swing.JLabel sugarAvg;
    private static javax.swing.JList sugarList;
    private javax.swing.JButton updatePersonalInfo;
    private static javax.swing.JLabel weight;
    private javax.swing.JLabel weightAvg;
    private javax.swing.JList weightList;
    // End of variables declaration//GEN-END:variables
}
