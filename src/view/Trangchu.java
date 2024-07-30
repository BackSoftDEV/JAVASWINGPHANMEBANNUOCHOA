/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.awt.Color;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JPanel;
/**
 *
 * @author NGUYEN DINH BACH
 */
public class Trangchu extends javax.swing.JFrame {
 private int userRole;

    /**
     * Creates new form Trangchu
     */
   public Trangchu(int userRole) {
        this.userRole = userRole;
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(this.MAXIMIZED_BOTH);
        showPanel(new formtrangchu());
    }
      public void showPanel(JPanel n) {
        if (RolePermissions.hasPermission(userRole, n.getClass())) {
            jpn.removeAll();
            jpn.add(n);
            jpn.validate();
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Bạn không có quyền truy cập phần này.");
        }
    }

    public void setColorPanel(JPanel jp) {
//    jp.setBackground(new java.awt.Color(0,204,51));
        jp.setBackground(new Color(169, 169, 169));
    }

    public void resetColorPanel(JPanel jp) {

        jp.setBackground(new Color(242, 242, 242));
    }
    public class RolePermissions {
    private static final Map<Integer, Set<Class<? extends JPanel>>> permissions = new HashMap<>();

    static {
        // Định nghĩa quyền cho mỗi vai trò
        Set<Class<? extends JPanel>> role1Permissions = new HashSet<>();
        role1Permissions.add(formtrangchu.class);
        role1Permissions.add(formKhachhang.class);
        role1Permissions.add(formhoadon.class);
        role1Permissions.add(formnhanvien.class);
        role1Permissions.add(formthongke.class);
        role1Permissions.add(formsanpham.class);
        role1Permissions.add(formkhuyenmai.class);
        role1Permissions.add(formbanhang.class);
        permissions.put(1, role1Permissions);

        Set<Class<? extends JPanel>> role2Permissions = new HashSet<>();
        role2Permissions.add(formtrangchu.class);
        role2Permissions.add(formKhachhang.class);
        role2Permissions.add(formhoadon.class);
        role2Permissions.add(formsanpham.class);
        role2Permissions.add(formbanhang.class);
        permissions.put(2, role2Permissions);
    }

    public static boolean hasPermission(int role, Class<? extends JPanel> panelClass) {
        return permissions.getOrDefault(role, new HashSet<>()).contains(panelClass);
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

        jpn = new javax.swing.JPanel();
        JPfull = new javax.swing.JPanel();
        JPnew = new javax.swing.JPanel();
        JPtrangchu = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        JPhoadon = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        JPnhanvien = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        JPkhachhang = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        JPthonoke = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        JPexit = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        JPsanpham = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        JPkhuyenmai = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        JPbanhang = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jpn.setLayout(new java.awt.BorderLayout());

        JPnew.setBackground(new java.awt.Color(255, 255, 255));

        JPtrangchu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPtrangchuMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Brick house.png"))); // NOI18N
        jLabel1.setText("Trang chủ");

        javax.swing.GroupLayout JPtrangchuLayout = new javax.swing.GroupLayout(JPtrangchu);
        JPtrangchu.setLayout(JPtrangchuLayout);
        JPtrangchuLayout.setHorizontalGroup(
            JPtrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPtrangchuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPtrangchuLayout.setVerticalGroup(
            JPtrangchuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPtrangchuLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        JPhoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPhoadonMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Lists.png"))); // NOI18N
        jLabel2.setText("Hóa Đơn");

        javax.swing.GroupLayout JPhoadonLayout = new javax.swing.GroupLayout(JPhoadon);
        JPhoadon.setLayout(JPhoadonLayout);
        JPhoadonLayout.setHorizontalGroup(
            JPhoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPhoadonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPhoadonLayout.setVerticalGroup(
            JPhoadonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPhoadonLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        JPnhanvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPnhanvienMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/loggin.png"))); // NOI18N
        jLabel3.setText("Nhân Viên");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JPnhanvienLayout = new javax.swing.GroupLayout(JPnhanvien);
        JPnhanvien.setLayout(JPnhanvienLayout);
        JPnhanvienLayout.setHorizontalGroup(
            JPnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnhanvienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPnhanvienLayout.setVerticalGroup(
            JPnhanvienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnhanvienLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        JPkhachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPkhachhangMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/User group.png"))); // NOI18N
        jLabel4.setText("Khách Hàng");

        javax.swing.GroupLayout JPkhachhangLayout = new javax.swing.GroupLayout(JPkhachhang);
        JPkhachhang.setLayout(JPkhachhangLayout);
        JPkhachhangLayout.setHorizontalGroup(
            JPkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPkhachhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPkhachhangLayout.setVerticalGroup(
            JPkhachhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPkhachhangLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        JPthonoke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPthonokeMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Statistics.png"))); // NOI18N
        jLabel6.setText("Thống Kê");

        javax.swing.GroupLayout JPthonokeLayout = new javax.swing.GroupLayout(JPthonoke);
        JPthonoke.setLayout(JPthonokeLayout);
        JPthonokeLayout.setHorizontalGroup(
            JPthonokeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPthonokeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        JPthonokeLayout.setVerticalGroup(
            JPthonokeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPthonokeLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        JPexit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPexitMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Exit button.png"))); // NOI18N
        jLabel8.setText("Đăng xuất");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout JPexitLayout = new javax.swing.GroupLayout(JPexit);
        JPexit.setLayout(JPexitLayout);
        JPexitLayout.setHorizontalGroup(
            JPexitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPexitLayout.createSequentialGroup()
                .addComponent(jLabel8)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        JPexitLayout.setVerticalGroup(
            JPexitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPexitLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8))
        );

        JPsanpham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPsanphamMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Brief case.png"))); // NOI18N
        jLabel7.setText("Sản Phẩm");

        javax.swing.GroupLayout JPsanphamLayout = new javax.swing.GroupLayout(JPsanpham);
        JPsanpham.setLayout(JPsanphamLayout);
        JPsanphamLayout.setHorizontalGroup(
            JPsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPsanphamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        JPsanphamLayout.setVerticalGroup(
            JPsanphamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPsanphamLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        JPkhuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPkhuyenmaiMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Gift.png"))); // NOI18N
        jLabel5.setText("Khuyến Mãi");

        javax.swing.GroupLayout JPkhuyenmaiLayout = new javax.swing.GroupLayout(JPkhuyenmai);
        JPkhuyenmai.setLayout(JPkhuyenmaiLayout);
        JPkhuyenmaiLayout.setHorizontalGroup(
            JPkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPkhuyenmaiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPkhuyenmaiLayout.setVerticalGroup(
            JPkhuyenmaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPkhuyenmaiLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        JPbanhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JPbanhangMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon-cart.png"))); // NOI18N
        jLabel9.setText("Bán Hàng");

        javax.swing.GroupLayout JPbanhangLayout = new javax.swing.GroupLayout(JPbanhang);
        JPbanhang.setLayout(JPbanhangLayout);
        JPbanhangLayout.setHorizontalGroup(
            JPbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPbanhangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPbanhangLayout.setVerticalGroup(
            JPbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPbanhangLayout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout JPnewLayout = new javax.swing.GroupLayout(JPnew);
        JPnew.setLayout(JPnewLayout);
        JPnewLayout.setHorizontalGroup(
            JPnewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnewLayout.createSequentialGroup()
                .addGroup(JPnewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPnewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JPtrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPthonoke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPkhuyenmai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(JPexit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JPbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        JPnewLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {JPhoadon, JPkhachhang, JPkhuyenmai, JPnhanvien, JPsanpham, JPthonoke, JPtrangchu});

        JPnewLayout.setVerticalGroup(
            JPnewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPnewLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPtrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPkhuyenmai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPthonoke, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JPhoadon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(JPexit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        JPnewLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {JPhoadon, JPkhachhang, JPkhuyenmai, JPnhanvien, JPsanpham, JPthonoke});

        javax.swing.GroupLayout JPfullLayout = new javax.swing.GroupLayout(JPfull);
        JPfull.setLayout(JPfullLayout);
        JPfullLayout.setHorizontalGroup(
            JPfullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPfullLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPnew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPfullLayout.setVerticalGroup(
            JPfullLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPnew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JPfull, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpn, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPfull, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jpn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void JPkhachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPkhachhangMouseClicked
        // TODO add your handling code here:
        setColorPanel(JPkhachhang);
        resetColorPanel(JPbanhang);
        resetColorPanel(JPtrangchu);
        resetColorPanel(JPnhanvien);
        resetColorPanel(JPthonoke);
        resetColorPanel(JPsanpham);
        resetColorPanel(JPkhuyenmai);
        resetColorPanel(JPhoadon);

        showPanel(new formKhachhang());

    }//GEN-LAST:event_JPkhachhangMouseClicked

    private void JPhoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPhoadonMouseClicked
        // TODO add your handling code here:
        setColorPanel(JPhoadon);
        resetColorPanel(JPkhachhang);
        resetColorPanel(JPbanhang);
        resetColorPanel(JPtrangchu);
        resetColorPanel(JPnhanvien);
        resetColorPanel(JPthonoke);
        resetColorPanel(JPkhuyenmai);
        resetColorPanel(JPsanpham);

        showPanel(new formhoadon());
    }//GEN-LAST:event_JPhoadonMouseClicked

    private void JPtrangchuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPtrangchuMouseClicked
        // TODO add your handling code here:
        setColorPanel(JPtrangchu);
        resetColorPanel(JPkhachhang);
        resetColorPanel(JPbanhang);
        resetColorPanel(JPnhanvien);
        resetColorPanel(JPthonoke);
        resetColorPanel(JPsanpham);
        resetColorPanel(JPkhuyenmai);
        resetColorPanel(JPhoadon);

        showPanel(new formtrangchu());
    }//GEN-LAST:event_JPtrangchuMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jLabel3MouseClicked

    private void JPnhanvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPnhanvienMouseClicked
        // TODO add your handling code here:
        setColorPanel(JPnhanvien);
        resetColorPanel(JPkhachhang);
        resetColorPanel(JPbanhang);
        resetColorPanel(JPtrangchu);
        resetColorPanel(JPthonoke);
        resetColorPanel(JPsanpham);
        resetColorPanel(JPkhuyenmai);
        resetColorPanel(JPhoadon);

        showPanel(new formnhanvien());
    }//GEN-LAST:event_JPnhanvienMouseClicked

    private void JPthonokeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPthonokeMouseClicked
        // TODO add your handling code here:
        setColorPanel(JPthonoke);
        resetColorPanel(JPkhachhang);
        resetColorPanel(JPbanhang);
        resetColorPanel(JPtrangchu);
        resetColorPanel(JPnhanvien);
        resetColorPanel(JPsanpham);
        resetColorPanel(JPkhuyenmai);
        resetColorPanel(JPhoadon);

        showPanel(new formthongke());
    }//GEN-LAST:event_JPthonokeMouseClicked

    private void JPsanphamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPsanphamMouseClicked
        // TODO add your handling code here:
        setColorPanel(JPsanpham);
        resetColorPanel(JPkhachhang);
        resetColorPanel(JPbanhang);
        resetColorPanel(JPtrangchu);
        resetColorPanel(JPnhanvien);
        resetColorPanel(JPthonoke);
        resetColorPanel(JPkhuyenmai);
        resetColorPanel(JPhoadon);

        showPanel(new formsanpham());
    }//GEN-LAST:event_JPsanphamMouseClicked

    private void JPkhuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPkhuyenmaiMouseClicked
        // TODO add your handling code here:
        setColorPanel(JPkhuyenmai);
        resetColorPanel(JPkhachhang);
        resetColorPanel(JPbanhang);
        resetColorPanel(JPtrangchu);
        resetColorPanel(JPnhanvien);
        resetColorPanel(JPthonoke);
        resetColorPanel(JPsanpham);
        resetColorPanel(JPhoadon);

        showPanel(new formkhuyenmai());
    }//GEN-LAST:event_JPkhuyenmaiMouseClicked

    private void JPexitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPexitMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_JPexitMouseClicked

    private void JPbanhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPbanhangMouseClicked
        // TODO add your handling code here:
        setColorPanel(JPbanhang);
        resetColorPanel(JPkhachhang);
        resetColorPanel(JPtrangchu);
        resetColorPanel(JPnhanvien);
        resetColorPanel(JPthonoke);
        resetColorPanel(JPsanpham);
        resetColorPanel(JPkhuyenmai);
        resetColorPanel(JPhoadon);

        showPanel(new formbanhang());
    }//GEN-LAST:event_JPbanhangMouseClicked

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
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Trangchu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Trangchu(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPbanhang;
    private javax.swing.JPanel JPexit;
    private javax.swing.JPanel JPfull;
    private javax.swing.JPanel JPhoadon;
    private javax.swing.JPanel JPkhachhang;
    private javax.swing.JPanel JPkhuyenmai;
    private javax.swing.JPanel JPnew;
    private javax.swing.JPanel JPnhanvien;
    private javax.swing.JPanel JPsanpham;
    private javax.swing.JPanel JPthonoke;
    private javax.swing.JPanel JPtrangchu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jpn;
    // End of variables declaration//GEN-END:variables
}
