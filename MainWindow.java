
/**
 * Main UI
 *
 * CMSC 335
 * Project 4
 * @author Leo Wack
 * Fall 2015
 * IDE: Netbeans 8.0.2             
 */

package project4;

import javax.swing.*;
import java.io.*;
import java.util.concurrent.Future;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.Timer;
import java.awt.event.*;

public class MainWindow extends javax.swing.JFrame {

    private File outputDir = null;
    private DataRetriever blockGenerator = new HttpDataRetriever();
    private ExecutorDataProcessor blockProcessor = null;
    private int blockCount = 1;
    private int finishedCount = 0;
    private int threadCount = 1;
    private final JFileChooser fc = new JFileChooser();
    private ArrayList<Future> futureList = new ArrayList<>();
    private AnalysisAlgorithm algorithm = new NaiveAnalysisAlgorithm();
        
    public MainWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        dirLabel = new javax.swing.JLabel();
        dirField = new javax.swing.JTextField();
        blockLabel = new javax.swing.JLabel();
        blockSlider = new javax.swing.JSlider();
        logLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logField = new javax.swing.JTextArea();
        browseButton = new javax.swing.JButton();
        goButton = new javax.swing.JButton();
        threadLabel = new javax.swing.JLabel();
        threadSlider = new javax.swing.JSlider();
        algorithmCheck = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data Analyzer");

        dirLabel.setText("Output Directory:");

        blockLabel.setText("Block Count:");

        blockSlider.setMajorTickSpacing(2);
        blockSlider.setMaximum(10);
        blockSlider.setMinimum(1);
        blockSlider.setMinorTickSpacing(1);
        blockSlider.setPaintLabels(true);
        blockSlider.setPaintTicks(true);
        blockSlider.setValue(1);
        blockSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                blockSliderStateChanged(evt);
            }
        });

        logLabel.setText("Log:");

        logField.setColumns(20);
        logField.setRows(5);
        jScrollPane1.setViewportView(logField);

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goButtonActionPerformed(evt);
            }
        });

        threadLabel.setText("Thread Count:");

        threadSlider.setMajorTickSpacing(1);
        threadSlider.setMaximum(5);
        threadSlider.setMinimum(1);
        threadSlider.setMinorTickSpacing(1);
        threadSlider.setPaintLabels(true);
        threadSlider.setPaintTicks(true);
        threadSlider.setValue(1);
        threadSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                threadSliderStateChanged(evt);
            }
        });

        algorithmCheck.setText("Enable Optimized Algorithm");
        algorithmCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algorithmCheckActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(dirLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dirField, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(blockLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(blockSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(threadLabel)
                                .addGap(11, 11, 11)
                                .addComponent(threadSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(browseButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(goButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(logLabel)
                        .addGap(345, 345, 345)
                        .addComponent(algorithmCheck)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dirLabel)
                    .addComponent(dirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(blockSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(blockLabel)
                            .addComponent(goButton)
                            .addComponent(threadLabel)
                            .addComponent(threadSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logLabel)
                    .addComponent(algorithmCheck))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        //set filechooser options
        fc.setFileSelectionMode(fc.DIRECTORIES_ONLY);   
        fc.setAcceptAllFileFilterUsed(false);
        fc.showOpenDialog(this);
              
        outputDir = new File(fc.getSelectedFile().toString());
        dirField.setText(fc.getSelectedFile().toString());      //set textfield to match chosen path
   
        
    }//GEN-LAST:event_browseButtonActionPerformed
    //Slider action
    //Sets block count to current slider value
    private void blockSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_blockSliderStateChanged
        blockCount = blockSlider.getValue();   
    }//GEN-LAST:event_blockSliderStateChanged
    //Go button
    //Performs analysis on selected number of blocks
    private void goButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goButtonActionPerformed
            outputDir = new File(dirField.getText());   //sets file path to text field input (should match filechooser)
            try{
                if(!outputDir.exists())
                    throw new FileNotFoundException();  //blank or non-existent file path
                blockProcessor = new ExecutorDataProcessor(outputDir, algorithm, threadCount);
                logField.setText(null);
                for(int i=1;i<blockCount+1;i++){
                    logField.append("Retrieving data block "+i+"\n");
                    logField.append("Analyzing data block "+i+"..."+"\n");
                    //Process data and output
                    try{
                    //Create new data block
                    File inputFile = blockGenerator.retrieveDataBlock();
                    //Add returned futures to list
                    futureList.add(blockProcessor.processDataBlock(inputFile));
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                logField.append("Submitted "+blockCount+" blocks\n");
                //Timer for checking future list
                Timer t = new Timer(2500, null);                        
                t.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent a){                        
                        Iterator<Future> itr = futureList.iterator();
                        logField.append("Checking for completed blocks...\n");
                        while (itr.hasNext()){
                            Future f = itr.next();
                            try{
                                if (f.isDone()){
                                    logField.append("Completed analysis on data block, results can be found at "+f.get().toString()+"\n");
                                    itr.remove();
                                    finishedCount++;
                                }
                                if (finishedCount == blockCount){
                                    t.stop();
                                    finishedCount = 0;
                                    blockProcessor.shutdown();
                                    logField.append("Block analysis complete");
                                }
                            }
                            catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
                //start timer
                t.start();   
            }
            catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(getContentPane(), "Please choose an existing directory");
            }
    }//GEN-LAST:event_goButtonActionPerformed

    private void threadSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_threadSliderStateChanged
        threadCount = threadSlider.getValue();
    }//GEN-LAST:event_threadSliderStateChanged

    private void algorithmCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algorithmCheckActionPerformed
        if (!algorithmCheck.isSelected())
            algorithm = new NaiveAnalysisAlgorithm();
        else if(algorithmCheck.isSelected())          
            algorithm = new OptimizedAnalysisAlgorithm();
    }//GEN-LAST:event_algorithmCheckActionPerformed

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox algorithmCheck;
    private javax.swing.JLabel blockLabel;
    private javax.swing.JSlider blockSlider;
    private javax.swing.JButton browseButton;
    private javax.swing.JTextField dirField;
    private javax.swing.JLabel dirLabel;
    private javax.swing.JButton goButton;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logField;
    private javax.swing.JLabel logLabel;
    private javax.swing.JLabel threadLabel;
    private javax.swing.JSlider threadSlider;
    // End of variables declaration//GEN-END:variables
}
