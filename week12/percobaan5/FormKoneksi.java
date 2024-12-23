import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.mariadb.jdbc.Driver;

public class FormKoneksi extends javax.swing.JFrame {

    private static Connection sConnection;

    private DefaultTableModel mModel;

    public FormKoneksi() {
        initComponents();
        setmTable();
    }

    private static void openConnectionSultan() {
        if (sConnection == null) {
            try {
                String url = "jdbc:mariadb://localhost:3306/pbo";
                String user = "root";
                String password = "";
                DriverManager.registerDriver(new Driver());
                sConnection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setmTable() {
        mModel = new DefaultTableModel();
        mModel.addColumn("ID");
        mModel.addColumn("Nama");
        mModel.addColumn("Alamat");
        mModel.addColumn("Telepon");
        this.mTable.setModel(mModel);
        getDataTable();
    }

    private void getDataTable() {
        mModel.getDataVector().removeAllElements();
        mModel.fireTableDataChanged();
        try {
            openConnectionSultan();
            Statement s = sConnection.createStatement();
            String query = "SELECT * FROM anggota";
            ResultSet r = s.executeQuery(query);
            while (r.next()) {
                Object[] o = new Object[4];
                o[0] = r.getString("id");
                o[1] = r.getString("nama");
                o[2] = r.getString("alamat");
                o[3] = r.getString("telp");
                mModel.addRow(o);
            }
            r.close();
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan" + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        mPanel = new javax.swing.JPanel();
        mLblNama = new javax.swing.JLabel();
        mLblAlamat = new javax.swing.JLabel();
        mLblTelepon = new javax.swing.JLabel();
        mTxtFieldTelepon = new javax.swing.JTextField();
        mTxtFieldAlamat = new javax.swing.JTextField();
        mTxtFieldNama = new javax.swing.JTextField();
        mScrlPanel = new javax.swing.JScrollPane();
        mTable = new javax.swing.JTable();
        mBtnAdd = new javax.swing.JButton();
        mBtnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mLblNama.setText("Nama");

        mLblAlamat.setText("Alamat");

        mLblTelepon.setText("Telepon");

        mTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

        }, new String[] { "ID", "Nama", "Alamat", "Telepon" }) {
            boolean[] canEdit = new boolean[] { false, false, false, false };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        mScrlPanel.setViewportView(mTable);

        mBtnAdd.setText("Add");
        mBtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mBtnAddActionPerformed(evt);
            }
        });

        mBtnRefresh.setText("Refresh");
        mBtnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mBtnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mPanelLayout = new javax.swing.GroupLayout(mPanel);
        mPanel.setLayout(mPanelLayout);
        mPanelLayout.setHorizontalGroup(mPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mPanelLayout.createSequentialGroup().addContainerGap().addGroup(mPanelLayout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mScrlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                        .addGroup(mPanelLayout.createSequentialGroup()
                                .addGroup(mPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(mLblTelepon).addComponent(mLblAlamat).addComponent(mLblNama))
                                .addGap(18, 18, 18)
                                .addGroup(mPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(mTxtFieldNama)
                                        .addComponent(mTxtFieldAlamat, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(mTxtFieldTelepon, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(mPanelLayout.createSequentialGroup().addComponent(mBtnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(mBtnRefresh).addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap()));
        mPanelLayout.setVerticalGroup(mPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mPanelLayout.createSequentialGroup().addContainerGap()
                        .addGroup(mPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(mLblNama)
                                .addComponent(mTxtFieldNama, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(mPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(mLblAlamat)
                                .addComponent(mTxtFieldAlamat, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(mPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(mLblTelepon)
                                .addComponent(mTxtFieldTelepon, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(mScrlPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 137,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(mPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(mBtnAdd).addComponent(mBtnRefresh))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                mPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(mPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap()));

        pack();
    }

    private void mBtnAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mBtnAddActionPerformed
        openConnectionSultan();
        String sqlQuery = "INSERT INTO anggota (nama, alamat, telp) VALUES ('" + this.mTxtFieldNama.getText() + "', '"
                + this.mTxtFieldAlamat.getText() + "', '" + this.mTxtFieldTelepon.getText() + "')";
        try {
            PreparedStatement mStatement = sConnection.prepareStatement(sqlQuery);
            mStatement.executeUpdate();
            mStatement.close();
            JOptionPane.showMessageDialog(this, "Data Berhasil Ditambah");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan" + e.getMessage());
        }
    }

    private void mBtnRefreshActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_mBtnRefreshActionPerformed
        getDataTable();
    }


    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormKoneksi.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormKoneksi.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormKoneksi.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormKoneksi.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormKoneksi().setVisible(true);
            }
        });
    }

    private javax.swing.JButton mBtnAdd;
    private javax.swing.JButton mBtnRefresh;
    private javax.swing.JLabel mLblAlamat;
    private javax.swing.JLabel mLblNama;
    private javax.swing.JLabel mLblTelepon;
    private javax.swing.JPanel mPanel;
    private javax.swing.JScrollPane mScrlPanel;
    private javax.swing.JTable mTable;
    private javax.swing.JTextField mTxtFieldAlamat;
    private javax.swing.JTextField mTxtFieldNama;
    private javax.swing.JTextField mTxtFieldTelepon;

}