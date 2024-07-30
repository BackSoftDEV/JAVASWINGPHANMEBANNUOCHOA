/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Model.khuyenmai;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.KhuyenmaiService;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class formkhuyenmai extends javax.swing.JPanel {

    private DefaultTableModel model;
    private KhuyenmaiService repoKM;
    
    
    public formkhuyenmai() {
         initComponents();
        this.repoKM = new KhuyenmaiService();
        model = (DefaultTableModel) tblbangkhuyenmai.getModel();
        fillTable();
    }
        void fillTable() {
        ArrayList<khuyenmai> listKM = repoKM.getAll();
        model.setRowCount(0);

        for (khuyenmai kM : listKM) {
            String donVi = kM.getDonVi().equals("Giảm %") ? "Giảm %" : "Giảm tiền";
            String trangThai = kM.isTrangthai() ? "Đang hoạt động" : "Kết thúc";

            Object[] data = {
                kM.getMakm(), kM.getTenkm(), kM.getNgaybatdau(), kM.getNgaykeythuc(), kM.getGiatri(), donVi,
                trangThai 
            };

            model.addRow(data);
        }
    }

    public khuyenmai readForm() {
        khuyenmai km = new khuyenmai();
        km.setMakm(Integer.parseInt(txtmakm.getText()));
        km.setTenkm(txttenkm.getText());
        km.setNgaybatdau(txtNgaybatdau.getText());
        km.setNgaykeythuc(txtNgayketthuc.getText());
        km.setGiatri(Integer.parseInt(txtgiatrigiam.getText()));
        km.setDonVi(rdoGiamPhanTram.isSelected() ? "Giảm %" : "Giảm tiền");
        km.setTrangthai(cbbtrangthai.getSelectedItem().toString().equals("Đang hoạt động"));
        return km;
    }

    public boolean checkDuLieu() {
        if (txtmakm.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập Mã Khuyến Mãi.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txttenkm.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập Tên Khuyến Mãi.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txttenkm.getText().trim().length() > 255) {
            JOptionPane.showMessageDialog(null, "Tên Khuyến Mãi không được vượt quá 255 ký tự.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (!rdoGiamPhanTram.isSelected()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn Đơn Vị.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtNgaybatdau.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập Ngày Bắt Đầu.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if (txtNgayketthuc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập Ngày Kết Thúc.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.parse(txtNgaybatdau.getText().trim());
            dateFormat.parse(txtNgayketthuc.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Định dạng ngày không hợp lệ.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    void getDataRow() {
        int selectedRow = tblbangkhuyenmai.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
    txtmakm.setText(tblbangkhuyenmai.getValueAt(selectedRow, 0).toString());
        txttenkm.setText(tblbangkhuyenmai.getValueAt(selectedRow, 1).toString());
        txtNgaybatdau.setText(tblbangkhuyenmai.getValueAt(selectedRow, 2).toString());
        txtNgayketthuc.setText(tblbangkhuyenmai.getValueAt(selectedRow, 3).toString());

        txtgiatrigiam.setText(tblbangkhuyenmai.getValueAt(selectedRow, 4).toString());

        String donVi = tblbangkhuyenmai.getValueAt(selectedRow, 5).toString();
            rdoGiamPhanTram.setSelected(true);

        String trangThai = tblbangkhuyenmai.getValueAt(selectedRow, 6).toString();
        if (trangThai.equals("Đang hoạt động")) {
            cbbtrangthai.setSelectedItem("Đang hoạt động");
        } else {
            cbbtrangthai.setSelectedItem("Kết thúc");
        }
    }

    void clearForm() {
        txtmakm.setText("");
        txttenkm.setText("");
        txtNgaybatdau.setText("");
        txtNgayketthuc.setText("");
        txtgiatrigiam.setText("");
        rdoGiamPhanTram.setSelected(true);
        cbbtrangthai.setSelectedIndex(0);
    }
    
    
    public khuyenmai getPromotionDetails(String maKM) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 0).toString().equals(maKM)) {
                khuyenmai km = new khuyenmai();
                km.setMakm(Integer.parseInt(model.getValueAt(i, 0).toString()));
                km.setTenkm(model.getValueAt(i, 1).toString());
                km.setGiatri(Integer.parseInt(model.getValueAt(i, 4).toString()));
                km.setDonVi(model.getValueAt(i, 5).toString());
                return km;
            }
        }
        return null;
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
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblbangkhuyenmai = new javax.swing.JTable();
        btnadd = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btndelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtmakm = new javax.swing.JTextField();
        txttimkiembangkhuyenmai = new javax.swing.JTextField();
        txttenkm = new javax.swing.JTextField();
        txtgiatri = new javax.swing.JTextField();
        txtNgaybatdau = new javax.swing.JTextField();
        txtNgayketthuc = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cbbtrangthai = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdoGiamPhanTram = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        txtgiatrigiam = new javax.swing.JTextField();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Bảng Khuyến Mãi"));

        tblbangkhuyenmai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Khuyến Mãi", "Tên Khuyến Mãi", "Ngày bắt đầu", "Ngày kết thúc", "Giá trị", "Đơn vị", "Trạng thái"
            }
        ));
        tblbangkhuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblbangkhuyenmaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblbangkhuyenmai);

        btnadd.setText("ADD");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btnedit.setText("EDIT");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        jLabel6.setText("Ngày Bắt Đầu");

        btndelete.setText("DELETE");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("Ngày kết thúc");

        jLabel8.setText("Tìm Kiếm");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 255));
        jLabel1.setText("Quản Lí Khuyến Mãi");

        cbbtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang hoạt động", "Kết thúc" }));
        cbbtrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbtrangthaiActionPerformed(evt);
            }
        });

        jLabel2.setText("MãKH");

        jLabel3.setText("TênKH");

        jLabel4.setText("Giá trị");

        jLabel5.setText("Trạng thái");

        jLabel9.setText("Đơn vị");

        buttonGroup1.add(rdoGiamPhanTram);
        rdoGiamPhanTram.setSelected(true);
        rdoGiamPhanTram.setText("Giảm %");
        rdoGiamPhanTram.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoGiamPhanTramActionPerformed(evt);
            }
        });

        jLabel10.setText("Giá trị Giảm");

        txtgiatrigiam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgiatrigiamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rdoGiamPhanTram))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txttenkm, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(txtmakm, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtgiatri, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(34, 34, 34)))
                                .addGap(94, 94, 94)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6)
                                        .addComponent(btnadd))
                                    .addComponent(jLabel5)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttimkiembangkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtgiatrigiam, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnedit))
                            .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtmakm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txttenkm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtgiatri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtNgaybatdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtNgayketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnedit)
                    .addComponent(btndelete)
                    .addComponent(btnadd)
                    .addComponent(jLabel10)
                    .addComponent(txtgiatrigiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rdoGiamPhanTram))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txttimkiembangkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbbtrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbtrangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbtrangthaiActionPerformed

    private void tblbangkhuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblbangkhuyenmaiMouseClicked
        getDataRow();
    }//GEN-LAST:event_tblbangkhuyenmaiMouseClicked

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        if (!checkDuLieu()) {
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm khuyến mãi?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (repoKM.them(readForm()) != 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                fillTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công");
            }
        }
    }//GEN-LAST:event_btnaddActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        int selectedRow = tblbangkhuyenmai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần xóa.");
            return;
        }

        int makm = Integer.parseInt(tblbangkhuyenmai.getValueAt(selectedRow, 0).toString());
        int dialogResult = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dữ liệu này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

        if (dialogResult == JOptionPane.YES_OPTION) {
            if (repoKM.xoa(makm)) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                fillTable();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        }
    }//GEN-LAST:event_btndeleteActionPerformed

    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        if (!checkDuLieu()) {
            return;
        }

        int selectedRow = tblbangkhuyenmai.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dữ liệu cần cập nhật.");
            return;
        }

        int makm = Integer.parseInt(tblbangkhuyenmai.getValueAt(selectedRow, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật dữ liệu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (repoKM.sua(makm, readForm()) != 0) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                fillTable();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        }
    }//GEN-LAST:event_btneditActionPerformed

    private void rdoGiamPhanTramActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoGiamPhanTramActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoGiamPhanTramActionPerformed

    private void txtgiatrigiamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgiatrigiamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgiatrigiamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbtrangthai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoGiamPhanTram;
    private javax.swing.JTable tblbangkhuyenmai;
    private javax.swing.JTextField txtNgaybatdau;
    private javax.swing.JTextField txtNgayketthuc;
    private javax.swing.JTextField txtgiatri;
    private javax.swing.JTextField txtgiatrigiam;
    private javax.swing.JTextField txtmakm;
    private javax.swing.JTextField txttenkm;
    private javax.swing.JTextField txttimkiembangkhuyenmai;
    // End of variables declaration//GEN-END:variables
}
