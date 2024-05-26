/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package apu.y2s1.pms.pm;

import apu.y2s1.pms.DataAbstract;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jeslyn
 */
public class PM_ReportStatus extends javax.swing.JFrame {

    DataAbstract table = new DataAbstract("Submissions.txt");
    DataAbstract combobox = new DataAbstract("Students.txt");
    DataAbstract lecturer = new DataAbstract("Lecturers.txt");
    DataAbstract assessment = new DataAbstract("Assessments.txt");

    /**
     * Creates new form PM_Dashboard
     */
    public PM_ReportStatus() {
        initComponents();
        StatusBar.setMinimum(0);
        StatusBar.setMaximum(100);
        Sort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateComboBox();
                Table(SubmissionTable.getSelectedRow());
            }
        });
        Lecture.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateComboBox();
                Table(SubmissionTable.getSelectedRow());
            }
        });
        LoadData();
        Table(-1);
    }

    private void LoadData() {
        HashSet<String> intakeSet = new HashSet<>();
        for (int i = 1; i <= 20; i++) {
            String[] row = combobox.getRow(i);
            if (row != null && row.length > 4) {
                String intake = row[4]; 
                if (!intake.isEmpty() && !intakeSet.contains(intake)) {
                    intakeSet.add(intake);
                    Sort.addItem(intake);
                }
            }
        }
            for (int l = 1; l <= 20; l++) {
                String[] row = lecturer.getRow(l);
                if (row != null && row.length > 1) {
                    Lecture.addItem(row[1]);
                }
            }
        }
    
    

    private void Table(int selected) {
        DefaultTableModel model = (DefaultTableModel) SubmissionTable.getModel();
        model.setRowCount(0);

        List<String[]> allRows = table.getAllRows();

        String selectedSortItem = Sort.getSelectedItem().toString();
        String selectedLecturerItem = Lecture.getSelectedItem().toString();
        List<String> matchedValues = new ArrayList<>();

        if (!allRows.isEmpty()) {
            int progress;
            if (selected != -1) {
                String[] selectedRowData = allRows.get(selected);
                progress = calculateProgressForSelectedRow(selectedRowData);
                Percentage.setText(progress + "%");
            } else {
                progress = 0;
                Percentage.setText("0%");
            }

            StatusBar.setValue(progress);

            if (!allRows.isEmpty() && selectedSortItem.equals("All") && selectedLecturerItem.equals("All")) {
                for (String[] row : allRows) {
                    String[] rowData = new String[9];
                    System.arraycopy(row, 0, rowData, 0, 7);
                    System.arraycopy(row, 11, rowData, 7, 2);
                    model.addRow(rowData);
                }
            } else if (!selectedSortItem.equals("All")) {
                for (int i = 1; i <= 20; i++) {
                    String[] row = combobox.getRow(i);
                    if (row != null && row.length > 4 && row[4].equals(selectedSortItem)) {
                        matchedValues.add(row[0]);
                    }
                }

                for (String[] rowData : allRows) {
                    if (matchedValues.contains(rowData[1])) {
                        String[] data = new String[9];
                        System.arraycopy(rowData, 0, data, 0, 7);
                        System.arraycopy(rowData, 11, data, 7, 2);
                        model.addRow(data);
                    }
                }
            } else if (!selectedLecturerItem.equals("All")) {
                for (int i = 1; i <= 20; i++) {
                    String[] row = assessment.getRow(i);
                    if (row != null && row.length > 5 && (row[3].equals(selectedLecturerItem) || row[4].equals(selectedLecturerItem) || row[5].equals(selectedLecturerItem))) {
                        matchedValues.add(row[0]);
                    }
                }

                for (String[] rowData : allRows) {
                    if (matchedValues.contains(rowData[2])) {
                        String[] data = new String[9];
                        System.arraycopy(rowData, 0, data, 0, 7);
                        System.arraycopy(rowData, 11, data, 7, 2);
                        model.addRow(data);
                    }
                }
            }
            Amount.setText(String.valueOf(model.getRowCount()));
        }
    }

    private int calculateProgressForSelectedRow(String[] selectedRowData) {
        int totalColumnsToCheck = 11;
        int nonNullCount = 0;

        if (selectedRowData.length > totalColumnsToCheck) {
            for (int i = 0; i < totalColumnsToCheck; i++) {
                if (selectedRowData[i] != null && !selectedRowData[i].equals("-")) {
                    nonNullCount++;
                }
            }
        }
        return (int) Math.round(((double) nonNullCount / totalColumnsToCheck) * 100);
    }

    private void updateComboBox() {
        String selectedSortItem = Sort.getSelectedItem().toString();
        String selectedLecturerItem = Lecture.getSelectedItem().toString();

        if (!selectedSortItem.equals("All")) {
            Lecture.setEnabled(false);
        } else {
            Lecture.setEnabled(true);
        }

        if (!selectedLecturerItem.equals("All")) {
            Sort.setEnabled(false);
        } else {
            Sort.setEnabled(true);
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

        Home = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        SubmissionTable = new javax.swing.JTable();
        Sort = new javax.swing.JComboBox<>();
        StatusBar = new javax.swing.JProgressBar();
        Percentage = new javax.swing.JTextField();
        Amount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Complete = new javax.swing.JButton();
        Lecture = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Home.setBackground(new java.awt.Color(255, 190, 152));
        Home.setFont(new java.awt.Font("Georgia", 2, 14)); // NOI18N
        Home.setText("HOME");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });
        getContentPane().add(Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jLabel1.setBackground(new java.awt.Color(255, 190, 152));
        jLabel1.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("STATUS OF REPORT");
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 40));

        jLabel3.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jLabel3.setText("Intake:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jLabel4.setText("Search ID:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        getContentPane().add(Search, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 140, -1));

        SubmissionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Submission.ID", "Student.ID", "Assessement.ID", "Submission.D", "Presentation.DT", "Presentation.Slot", "Status", "Req.Extension.D", "NewSub.Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SubmissionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SubmissionTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(SubmissionTable);
        if (SubmissionTable.getColumnModel().getColumnCount() > 0) {
            SubmissionTable.getColumnModel().getColumn(0).setResizable(false);
            SubmissionTable.getColumnModel().getColumn(1).setResizable(false);
            SubmissionTable.getColumnModel().getColumn(2).setResizable(false);
            SubmissionTable.getColumnModel().getColumn(3).setResizable(false);
            SubmissionTable.getColumnModel().getColumn(4).setResizable(false);
            SubmissionTable.getColumnModel().getColumn(5).setResizable(false);
            SubmissionTable.getColumnModel().getColumn(6).setResizable(false);
            SubmissionTable.getColumnModel().getColumn(7).setResizable(false);
            SubmissionTable.getColumnModel().getColumn(8).setResizable(false);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 930, 340));

        Sort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        getContentPane().add(Sort, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 160, -1));

        StatusBar.setBackground(new java.awt.Color(255, 178, 165));
        StatusBar.setFont(new java.awt.Font("Sitka Small", 1, 14)); // NOI18N
        getContentPane().add(StatusBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 560, 850, 20));

        Percentage.setBackground(new java.awt.Color(239, 207, 186));
        Percentage.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        getContentPane().add(Percentage, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 500, 70, -1));

        Amount.setBackground(new java.awt.Color(239, 207, 186));
        Amount.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        getContentPane().add(Amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 500, 70, -1));

        jLabel7.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        jLabel7.setText("TOTAL AMOUNT OF REPORT");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, -1, -1));

        jLabel2.setFont(new java.awt.Font("Baskerville Old Face", 1, 18)); // NOI18N
        jLabel2.setText("SELECTED REPORT PROGRESS");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 510, -1, -1));

        jLabel6.setBackground(new java.awt.Color(239, 207, 186));
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, 930, 130));

        Complete.setFont(new java.awt.Font("Segoe Print", 1, 10)); // NOI18N
        Complete.setText("COMPLETED REPORT");
        Complete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CompleteActionPerformed(evt);
            }
        });
        getContentPane().add(Complete, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 70, -1, -1));

        Lecture.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        getContentPane().add(Lecture, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 70, 120, -1));

        jLabel8.setFont(new java.awt.Font("Segoe Print", 1, 12)); // NOI18N
        jLabel8.setText("Lecturer:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/apu/y2s1/pms/pm/img/Functionpage.png"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        DefaultTableModel table = (DefaultTableModel) SubmissionTable.getModel();
        TableRowSorter<DefaultTableModel> search = new TableRowSorter<>(table);
        SubmissionTable.setRowSorter(search);
        String field = Search.getText().toLowerCase();
        search.setRowFilter(RowFilter.regexFilter("(?i)" + field, 0, 1, 2));
    }//GEN-LAST:event_SearchActionPerformed

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        PM_FunctionPage home = new PM_FunctionPage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_HomeActionPerformed

    private void SubmissionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SubmissionTableMouseClicked
        int selectedRow = SubmissionTable.getSelectedRow();
        if (selectedRow != -1) {
            Table(selectedRow);
        }
    }//GEN-LAST:event_SubmissionTableMouseClicked

    private void CompleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CompleteActionPerformed
        DefaultTableModel model = (DefaultTableModel) SubmissionTable.getModel();
        model.setRowCount(0);

        List<String[]> allRows = table.getAllRows();
        for (String[] row : allRows) {
            if (row[6].equals("Graded")) {
                String[] rowData = new String[9];
                System.arraycopy(row, 0, rowData, 0, 7);
                System.arraycopy(row, 11, rowData, 7, 2);
                model.addRow(rowData);
            }
        }
        Amount.setText(String.valueOf(model.getRowCount()));
    }//GEN-LAST:event_CompleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorReportTablewing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PM_ReportStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PM_ReportStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PM_ReportStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PM_ReportStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PM_ReportStatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Amount;
    private javax.swing.JButton Complete;
    private javax.swing.JButton Home;
    private javax.swing.JComboBox<String> Lecture;
    private javax.swing.JTextField Percentage;
    private javax.swing.JTextField Search;
    private javax.swing.JComboBox<String> Sort;
    private javax.swing.JProgressBar StatusBar;
    private javax.swing.JTable SubmissionTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
