//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
//import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DaMania
 */
public class CreateGraph extends javax.swing.JFrame {

    /**
     * Creates new form CreateGraph
     */
    private ArrayList<String> bloodPressureDates=new ArrayList<String>();
    private ArrayList<String> weightDates=new ArrayList<String>();
    private ArrayList<Double> bloodPressureSystolicEntries=new ArrayList<Double>();
    private ArrayList<Double> bloodPressureDiastolicEntries=new ArrayList<Double>();
    private ArrayList<Double> weightEntries=new ArrayList<Double>();
    
    private ArrayList<String> sugarDates=new ArrayList<String>();
    private ArrayList<Double> sugarEntries=new ArrayList<Double>();
    
    ChartPanel chartPanel;
    public CreateGraph()
    {
        initComponents();
        ArrayList<String> weightLogs= new ArrayList<String>();
        ArrayList<String> bloodPressureLogs = new ArrayList<String>();
        ArrayList<String> sugarLogs = new ArrayList<String>();
        //Populate with test data
        bloodPressureLogs.add("[2013/04/17 10:55 AM] 90/225mmHg");
        bloodPressureLogs.add("[2013/04/16 10:55 AM] 86/215mmHg");
        bloodPressureLogs.add("[2013/04/10 10:55 AM] 84/205mmHg");
        bloodPressureLogs.add("[2013/03/31 10:55 AM] 82/195mmHg");
        bloodPressureLogs.add("[2013/03/26 10:55 AM] 75/185mmHg");
        bloodPressureLogs.add("[2012/04/17 10:55 AM] 65/175mmHg");
        bloodPressureLogs.add("[2012/02/17 10:55 AM] 54/165mmHg");
        bloodPressureLogs.add("[2010/04/17 10:55 AM] 45/155mmHg");
        bloodPressureLogs.add("[2005/04/17 10:55 AM] 35/145mmHg");
        bloodPressureLogs.add("[1996/04/17 10:55 AM] 20/135mmHg");
        //
        weightLogs.add("[2013/04/17 10:55 AM] 160kg");
        weightLogs.add("[2013/04/16 10:55 AM] 186kg");
        weightLogs.add("[2013/04/10 10:55 AM] 134kg");
        weightLogs.add("[2013/03/31 10:55 AM] 182kg");
        weightLogs.add("[2013/03/26 10:55 AM] 125kg");
        weightLogs.add("[2012/04/17 10:55 AM] 165kg");
        weightLogs.add("[2012/02/17 10:55 AM] 134kg");
        weightLogs.add("[2010/04/17 10:55 AM] 145kg");
        weightLogs.add("[2005/04/17 10:55 AM] 165kg");
        weightLogs.add("[1996/04/17 10:55 AM] 120kg");
        
        sugarLogs.add("[2013/04/17 10:55 AM] 160mmol/L");
        sugarLogs.add("[2013/04/16 10:55 AM] 186mmol/L");
        sugarLogs.add("[2013/04/10 10:55 AM] 134mmol/L");
        sugarLogs.add("[2013/03/31 10:55 AM] 182mmol/L");
        sugarLogs.add("[2013/03/26 10:55 AM] 125mmol/L");
        sugarLogs.add("[2012/04/17 10:55 AM] 165mmol/L");
        sugarLogs.add("[2012/02/17 10:55 AM] 134mmol/L");
        sugarLogs.add("[2010/04/17 10:55 AM] 145mmol/L");
        sugarLogs.add("[2005/04/17 10:55 AM] 165mmol/L");
        sugarLogs.add("[1996/04/17 10:55 AM] 120mmol/L");
        
        ParseLogs(weightLogs, bloodPressureLogs,sugarLogs);
        updateChart();
    }
    public CreateGraph(ArrayList<String> bloodPressureLogs, ArrayList<String> weightLogs, ArrayList<String> sugarLogs, int defaultGraphIndex) {
        initComponents();
        //Parse
        GraphTypeSelector.setSelectedIndex(defaultGraphIndex);
        ParseLogs(weightLogs,bloodPressureLogs,sugarLogs);
        updateChart();
    }
    
    private void ParseLogs(ArrayList<String> weightLogs, ArrayList<String> bloodPressureLogs, ArrayList<String> sugarLogs)
    {
        Pattern bloodPressureLog = Pattern.compile("\\[([^\\]]*)\\] ([0-9\\.]+)/([0-9.]+)mmHg");
        Pattern weightLog = Pattern.compile("\\[([^\\]]*)\\] ([0-9\\.]+)kg");
        Pattern sugarLog = Pattern.compile("\\[([^\\]]*)\\] ([0-9\\.]+)mmol/L");
        for(String toParse : bloodPressureLogs)
        {
            //Specific to blood pressure
            Matcher bloodPressureMatcher = bloodPressureLog.matcher(toParse);
            String date="";
            double readingSystolic=0;
            double readingDiastolic=0;
            if (bloodPressureMatcher.matches())
            {
                date=bloodPressureMatcher.group(1);
                readingSystolic= Double.parseDouble(bloodPressureMatcher.group(2));
                readingDiastolic= Double.parseDouble(bloodPressureMatcher.group(3));
            }
            else
            {
                System.out.println("Error: Invalid blood pressure reading "+toParse);
            }
            bloodPressureSystolicEntries.add(readingSystolic);
            bloodPressureDiastolicEntries.add(readingDiastolic);
            bloodPressureDates.add(date);
        }
        
        for(String toParse : weightLogs)
        {
            Matcher weightMatcher = weightLog.matcher(toParse);
            String date="";
            double readingWeight=0;
            if (weightMatcher.matches())
            {
                date=weightMatcher.group(1);
                readingWeight= Double.parseDouble(weightMatcher.group(2));
            }
            else
            {
                System.out.println("Error: Invalid weight reading "+toParse);
            }
            weightEntries.add(readingWeight);
            weightDates.add(date);
        }
        for(String toParse : sugarLogs)
        {
            Matcher sugarMatcher = sugarLog.matcher(toParse);
            String date="";
            double readingSugar=0;
            if (sugarMatcher.matches())
            {
                date=sugarMatcher.group(1);
                readingSugar= Double.parseDouble(sugarMatcher.group(2));
            }
            else
            {
                System.out.println("Error: Invalid sugar reading "+toParse);
            }
            sugarEntries.add(readingSugar);
            sugarDates.add(date);
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

        GraphExportButton = new javax.swing.JButton();
        GraphTypeSelector = new javax.swing.JComboBox();
        GraphPeriodSelector = new javax.swing.JComboBox();
        GraphFrame = new javax.swing.JInternalFrame();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        GraphExportButton.setText("Export Graph");
        GraphExportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GraphExportButtonActionPerformed(evt);
            }
        });

        GraphTypeSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Blood Pressure Graph", "Weight Graph", "Sugar Graph" }));
        GraphTypeSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GraphTypeSelectorActionPerformed(evt);
            }
        });

        GraphPeriodSelector.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Last Two Weeks", "Last Month", "Last Three Months", "Last Year", "Last 3 Years", "All" }));
        GraphPeriodSelector.setSelectedIndex(1);
        GraphPeriodSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GraphPeriodSelectorActionPerformed(evt);
            }
        });

        GraphFrame.setResizable(true);
        GraphFrame.setVisible(true);

        org.jdesktop.layout.GroupLayout GraphFrameLayout = new org.jdesktop.layout.GroupLayout(GraphFrame.getContentPane());
        GraphFrame.getContentPane().setLayout(GraphFrameLayout);
        GraphFrameLayout.setHorizontalGroup(
            GraphFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );
        GraphFrameLayout.setVerticalGroup(
            GraphFrameLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 334, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(177, 177, 177)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(GraphTypeSelector, 0, 137, Short.MAX_VALUE)
                    .add(GraphPeriodSelector, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 68, Short.MAX_VALUE)
                .add(GraphExportButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(GraphFrame)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(GraphTypeSelector, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(GraphPeriodSelector, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(GraphExportButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(7, 7, 7)
                .add(GraphFrame))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void GraphTypeSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GraphTypeSelectorActionPerformed

        updateChart();
    }//GEN-LAST:event_GraphTypeSelectorActionPerformed

    private void GraphPeriodSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GraphPeriodSelectorActionPerformed

        updateChart();
    }//GEN-LAST:event_GraphPeriodSelectorActionPerformed

    private void GraphExportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GraphExportButtonActionPerformed
// TODO add your handling code here:
        Image im = this.chartPanel.createImage(this.GraphFrame.getWidth(), this.GraphFrame.getHeight());
        BufferedImage buffer = new BufferedImage(this.GraphFrame.getWidth(),this.GraphFrame.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buffer.createGraphics();
        this.chartPanel.getChart().draw(g,new Rectangle2D.Double(0,0,this.GraphFrame.getWidth(),this.GraphFrame.getHeight()));
        g.dispose();
        
        JFileChooser fc = new JFileChooser();
        int returnVal=fc.showOpenDialog(this);
        if (returnVal==JFileChooser.APPROVE_OPTION)
        {
            try {
                File file = fc.getSelectedFile();
                ImageIO.write(buffer, "png", file);
                JOptionPane.showMessageDialog(this,"The graph has been successfully exported.");
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(this,"There was an error opening or saving the file.\nDetails: "+e.getMessage());
            }
        }
    }//GEN-LAST:event_GraphExportButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CreateGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateGraph.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateGraph().setVisible(true);
            }
        });
    }
    
    private void updateChart()
    {
        String periodIndex=GraphPeriodSelector.getSelectedItem().toString();
        String typeIndex=GraphTypeSelector.getSelectedItem().toString();
        java.util.Calendar cal=Calendar.getInstance();
        String chartTitle;
        String chartUnits;
        
        if (periodIndex.equals("Last Two Weeks"))
        {
            cal.add(java.util.Calendar.WEEK_OF_YEAR, -2);
        }
        else if (periodIndex.equals("Last Month"))
        {
            cal.add(java.util.Calendar.MONTH, -1);
        }
        else if (periodIndex.equals("Last Three Months"))
        {
            cal.add(java.util.Calendar.MONTH, -3);
        }
        else if (periodIndex.equals("Last Year"))
        {
            cal.add(java.util.Calendar.YEAR, -1);
        }
        else if (periodIndex.equals("Last 3 Years"))
        {
            cal.add(java.util.Calendar.YEAR, -3);
        }
        else if (periodIndex.equals("All"))
        {
            cal.add(java.util.Calendar.YEAR, -999);
        }
        Date cutoff=cal.getTime();
        
        ArrayList<String> dates;
        ArrayList<Double> entries;
        
        if (typeIndex.equals("Blood Pressure Graph"))
        {
            chartTitle="Blood Pressure";
            chartUnits="mmHg";
            dates=bloodPressureDates;
            entries=bloodPressureSystolicEntries;
            
        }
        else if (typeIndex.equals("Weight Graph"))
        {
            chartTitle="Weight";
            chartUnits="kg";
            dates=weightDates;
            entries=weightEntries;
        }
        else
        {
            chartTitle="Sugar Level";
            chartUnits="mmol/L";
            dates=sugarDates;
            entries=sugarEntries;
        }
            
        JFreeChart chart = createLineChart(chartTitle,chartUnits,dates, entries, cutoff);
        ChartPanel cpanel = new ChartPanel(chart);
        cpanel.setMouseZoomable(true,false);
        this.chartPanel=cpanel;
        GraphFrame.setContentPane(cpanel);
    }
    
    private JFreeChart createLineChart(String chartTitle, String chartUnits, ArrayList<String> dataDates, ArrayList<Double> dataEntries, Date cutoff)
    {
        SimpleDateFormat dispFormatter=new SimpleDateFormat("yyyy/MM/dd hh:mm a");
        TimeSeries s1 = new TimeSeries(chartTitle);
        int i=0;
        
        for(String toParse : dataDates)
        {     
            try
            {
                java.util.Date d=dispFormatter.parse(toParse);
                if (cutoff.before(d))
                { s1.addOrUpdate(new org.jfree.data.time.Second(d),dataEntries.get(i)); }
            }
            catch (ParseException e) {}
            i++;
        }
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(s1);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                chartTitle,
                "Date", chartUnits,
                dataset,
                true,
                true,
                false);
        ((DateAxis)chart.getXYPlot().getDomainAxis()).setDateFormatOverride(new SimpleDateFormat("yyyy/MM/dd hh:mm"));
        return chart;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GraphExportButton;
    private javax.swing.JInternalFrame GraphFrame;
    private javax.swing.JComboBox GraphPeriodSelector;
    private javax.swing.JComboBox GraphTypeSelector;
    // End of variables declaration//GEN-END:variables
}
