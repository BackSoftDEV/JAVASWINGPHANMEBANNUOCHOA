/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.khachhang;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import service.KhachhangService;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class formKhachhang extends javax.swing.JPanel {

    DefaultTableModel dtm = new DefaultTableModel();
    KhachhangService ds = new KhachhangService();
    khachhang k = new khachhang();
    ArrayList<khachhang> kh = new ArrayList<>();

    /**
     * Creates new form fromKhachhang
     */
    public formKhachhang() {
        initComponents();
        loadData();
    }

    public void loadData() {
        dtm = (DefaultTableModel) tblbangkhachhang.getModel();
        dtm.setRowCount(0);
        for (khachhang a : ds.Getall()) {
            Object[] k = new Object[7];
            k[0] = a.getMakh();
            k[1] = a.getTen();
            k[2] = a.getNgaysinh();
            k[3] = a.getGioitinh();
            k[4] = a.getSdt();
            k[5] = a.getDiachi();
            k[6] = a.getEmail();

            dtm.addRow(k);
        }
    }

    public khachhang getmodel() {
        try {
            khachhang a = new khachhang();
//            a.setMakh(Integer.parseInt(txtma.getText()));
            a.setTen(txthoten.getText());
            a.setNgaysinh(txtngaysinh.getText());
            String gioiTinh = "";
            if (rbonam.isSelected()) {
                gioiTinh = "Nam";
            } else if (rbonu.isSelected()) {
                gioiTinh = "Nữ";
            }
            a.setGioitinh(gioiTinh);
            a.setSdt(txtsdt.getText());
            a.setDiachi(txtdiachi.getText());
            a.setEmail(txtemail.getText());
            return a;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public khachhang getmodel1() {
        try {
            khachhang a = new khachhang();
            a.setMakh(Integer.parseInt(txtma.getText()));
            a.setTen(txthoten.getText());
            a.setNgaysinh(txtngaysinh.getText());
            String gioiTinh = "";
            if (rbonam.isSelected()) {
                gioiTinh = "Nam";
            } else if (rbonu.isSelected()) {
                gioiTinh = "Nữ";
            }
            a.setGioitinh(gioiTinh);
            a.setSdt(txtsdt.getText());
            a.setDiachi(txtdiachi.getText());
            a.setEmail(txtemail.getText());
            return a;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setmodel(khachhang s) {
        try {
            txtma.setText(String.valueOf(s.getMakh()));
            txthoten.setText(s.getTen());
            txtngaysinh.setText(s.getNgaysinh());
            String gioiTinh = s.getGioitinh();
            if (gioiTinh.equals("Nam")) {
                rbonam.setSelected(true);
            } else if (gioiTinh.equals("Nữ")) {
                rbonu.setSelected(true);
            }
            txtsdt.setText(String.valueOf(s.getSdt()));
            txtdiachi.setText(s.getDiachi());
            txtemail.setText(s.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        txtma = new javax.swing.JTextField();
        txthoten = new javax.swing.JTextField();
        txtsdt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtngaysinh = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtdiachi = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblbangkhachhang = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnthem = new javax.swing.JButton();
        btnsua = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        btnclear = new javax.swing.JButton();
        txttimkiemttkhachhang = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rbonu = new javax.swing.JRadioButton();
        rbonam = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Khách Hàng"));

        txtma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmaActionPerformed(evt);
            }
        });

        txtsdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsdtActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mã KH");

        jLabel4.setText("Họ tên");

        jLabel5.setText("Giới tính ");

        jLabel6.setText("Ngày sinh");

        jLabel2.setText("Địa chỉ");

        jLabel8.setText("SDT");

        txtdiachi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdiachiActionPerformed(evt);
            }
        });

        tblbangkhachhang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblbangkhachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Họ tên", "Ngày sinh", "Giới tính", "SĐT", "Địa chỉ", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblbangkhachhang.setShowGrid(true);
        tblbangkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbangkhachhangMouseClicked(evt);
            }
        });
        tblbangkhachhang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblbangkhachhangKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblbangkhachhang);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnthem.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        btnthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-add-24.png"))); // NOI18N
        btnthem.setText("ADD");
        btnthem.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthemActionPerformed(evt);
            }
        });

        btnsua.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        btnsua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Save as.png"))); // NOI18N
        btnsua.setText("EDIT");
        btnsua.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnsua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsuaActionPerformed(evt);
            }
        });

        btnxoa.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        btnxoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-delete-24.png"))); // NOI18N
        btnxoa.setText("DELETE");
        btnxoa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnxoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnxoaActionPerformed(evt);
            }
        });

        btnclear.setFont(new java.awt.Font("Sitka Text", 1, 12)); // NOI18N
        btnclear.setText("Clear");
        btnclear.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnthem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnclear, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthem, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsua)
                    .addComponent(btnxoa, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclear))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txttimkiemttkhachhang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemttkhachhangActionPerformed(evt);
            }
        });
        txttimkiemttkhachhang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemttkhachhangKeyReleased(evt);
            }
        });

        jLabel1.setText("Tìm Kiếm ");

        jLabel11.setText("Thông Tin Chi Tiết");

        buttonGroup1.add(rbonu);
        rbonu.setText("Nữ");

        buttonGroup1.add(rbonam);
        rbonam.setText("Nam");

        jLabel9.setText("Email");

        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 0, 51));
        jLabel7.setText("Quản Lý Khách Hàng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6))
                                        .addGap(72, 72, 72)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(87, 87, 87)
                                        .addComponent(rbonam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbonu)))
                                .addGap(69, 69, 69)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(40, 40, 40)
                                        .addComponent(txtsdt))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel2))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtdiachi)
                                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttimkiemttkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txthoten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbonam)
                            .addComponent(rbonu)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(24, 24, 24)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel2)
                        .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtngaysinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txttimkiemttkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmaActionPerformed

    private void txtsdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsdtActionPerformed

    private void txtdiachiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdiachiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdiachiActionPerformed

    private void tblbangkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbangkhachhangMouseClicked
        // TODO add your handling code here:
        int index = tblbangkhachhang.getSelectedRow();
        setmodel(ds.Getall().get(index));
    }//GEN-LAST:event_tblbangkhachhangMouseClicked

    private void tblbangkhachhangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblbangkhachhangKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblbangkhachhangKeyReleased

    private void btnthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthemActionPerformed
        ds.add(getmodel());
        loadData();
        JOptionPane.showMessageDialog(this, "đã thêm ");
    }//GEN-LAST:event_btnthemActionPerformed

    private void btnsuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsuaActionPerformed
        //        // TODO add your handling code here:
        ds.Edit(getmodel1());
        loadData();
        JOptionPane.showMessageDialog(this, "đã sửa ");
    }//GEN-LAST:event_btnsuaActionPerformed

    private void btnxoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnxoaActionPerformed
        // TODO add your handling code here:
        int i = tblbangkhachhang.getSelectedRow();
        if (i >= 0) {
            ds.Delete(getmodel1());
            loadData();

//        ds.Delete(i);
            JOptionPane.showMessageDialog(this, "đã xóa ");
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một khách hàng để xóa");
        }
    }//GEN-LAST:event_btnxoaActionPerformed

    private void btnclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclearActionPerformed
        //        // TODO add your handling code here:
        txthoten.setText("");
        txtdiachi.setText("");
        txtma.setText("");
        txtngaysinh.setText("");
        txtsdt.setText("");
        rbonam.setSelected(true);
    }//GEN-LAST:event_btnclearActionPerformed

    private void txttimkiemttkhachhangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemttkhachhangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemttkhachhangActionPerformed

    private void txttimkiemttkhachhangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemttkhachhangKeyReleased
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) tblbangkhachhang.getModel();
        TableRowSorter<DefaultTableModel> ab = new TableRowSorter<>(dtm);
        tblbangkhachhang.setRowSorter(ab);
        ab.setRowFilter(RowFilter.regexFilter(txttimkiemttkhachhang.getText()));
    }//GEN-LAST:event_txttimkiemttkhachhangKeyReleased

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnclear;
    private javax.swing.JButton btnsua;
    private javax.swing.JButton btnthem;
    private javax.swing.JButton btnxoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbonam;
    private javax.swing.JRadioButton rbonu;
    private javax.swing.JTable tblbangkhachhang;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txthoten;
    private javax.swing.JTextField txtma;
    private javax.swing.JTextField txtngaysinh;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttimkiemttkhachhang;
    // End of variables declaration//GEN-END:variables
}
