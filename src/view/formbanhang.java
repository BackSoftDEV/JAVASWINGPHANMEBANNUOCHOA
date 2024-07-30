/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import Connect.DBConnect;
import Model.HoaDonCT;
import Model.HoaDonCTfull;
import Model.HoaDonCuaBang;
import Model.HoaDonTest;
import Model.SanPham;
import Model.khachhang;
import Model.khuyenmai;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import service.SanPhamService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import main.FormDangnhap;
import service.HoaDonService;
import service.KhachhangService;
import service.UserDao;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import service.KhuyenmaiService;

/**
 *
 * @author NGUYEN DINH BACH
 */
public class formbanhang extends javax.swing.JPanel {

    DefaultTableModel modelgiohang;
    private HoaDonService hds = new HoaDonService();
    private List<HoaDonTest> HDT = new ArrayList<>();
    private List<HoaDonCT> HDCT = new ArrayList<>();
    private SanPhamService sps = new SanPhamService();
    private DefaultTableModel model = new DefaultTableModel();
    private List<SanPham> SP_REPO = sps.getAllSanPham();
    private List<HoaDonCuaBang> HD_REPO = hds.GetAll();
    private Map<String, List<SanPham>> gioHangMap = new HashMap<>();
    private UserDao Dao = new UserDao();
    private KhuyenmaiService kmService = new KhuyenmaiService();
    private formkhuyenmai khuyenMaiForm;

    /**
     * Creates new form fromHoadon
     */
    public formbanhang() {
        initComponents();
        loadHoadon();
        loadSanPham();
        LoadGioHang(selectedMaHoaDon);
        FormDangnhap dangnhapForm = new FormDangnhap();
        dangnhapForm.DangNhap();

        this.khuyenMaiForm = khuyenMaiForm;

        // Lấy giá trị tên đăng nhập từ biến tĩnh và truyền vào setNhanVienFullName
        String username = FormDangnhap.username;
        setNhanVienFullName(username);
        txtTienKhach.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                updateTienThua();
            }

            public void removeUpdate(DocumentEvent e) {
                updateTienThua();
            }

            public void insertUpdate(DocumentEvent e) {
                updateTienThua();
            }
        });

    }

    private double tinhTongTienGioHang() {
        double tongTien = 0;
        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            Object value = tblgiohang.getValueAt(i, 5);
            if (value != null) {
                try {
                    tongTien += Double.parseDouble(value.toString());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ ở hàng " + (i + 1) + ", cột 6", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        return tongTien;
    }

// Update remaining amount after applying discount
    private void updateTienThua() {
        String maKM = txtMaKM.getText();
        if (maKM.isEmpty()) {
            updateTienThua(0.0, false);
        } else {
            try {
                double giaTriGiam = Double.parseDouble(txtGiaTriGiam.getText());
                boolean isPercent = true; // Assuming the discount is percentage
                updateTienThua(giaTriGiam, isPercent);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Giá trị giảm giá không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                updateTienThua(0.0, false);
            }
        }
    }

// Update remaining amount after applying discount
    private void updateTienThua(double giaTriGiam, boolean isPercent) {
        String tienKhachDuaStr = txtTienKhach.getText();
        if (tienKhachDuaStr.isEmpty()) {
            lbTienThua.setText("");
            return;
        }
        try {
            double tienKhachDua = Double.parseDouble(tienKhachDuaStr);
            double tongTien = tinhTongTienGioHang();
            double tienGiam = isPercent ? (tongTien * giaTriGiam / 100) : giaTriGiam;
            double tongTienSauGiam = tongTien - tienGiam;
            double tienThua = tienKhachDua - tongTienSauGiam;
            lbTienThua.setText(String.format("%.2f", tienThua));
        } catch (NumberFormatException e) {
            lbTienThua.setText("");
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số tiền hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public double tinhTongTienSauGiam(double tongTien, double giaTriGiam, boolean isPercent) {
        if (isPercent) {
            return tongTien - (tongTien * giaTriGiam / 100);
        } else {
            return tongTien - giaTriGiam;
        }
    }

// Apply discount and update UI
    private void applyDiscount(khuyenmai km) {
        double tongTien = tinhTongTienGioHang();
        double giaTriGiam = km.getGiatri();
        boolean isPercent = true;
        double tienGiam = isPercent ? (tongTien * giaTriGiam / 100) : giaTriGiam;

        double tongTienSauGiam = tongTien - tienGiam;

        txtTienGiam.setText(String.format("%.2f", tienGiam));
        lbTongTien.setText(String.format("%.2f", tongTienSauGiam));

        updateTienThua(giaTriGiam, isPercent);
    }

// Display discount information
    public void hienThiThongTinKhuyenMai(khuyenmai km) {
        if (km != null) {
            txtMaKM.setText(String.valueOf(km.getMakm()));
            txtTenKM.setText(km.getTenkm());
            txtGiaTriGiam.setText(String.valueOf(km.getGiatri()));
            applyDiscount(km);
        }
    }
//      public void hientienThuakophieu(double tongTienSauGiam ) {
//        double tongTien = tinhTongTienGioHang();
//
//        // Hiển thị tổng tiền lên label
//        lbTongTien.setText(String.format("%.2f", tongTien));
//
//        // Lấy giá trị từ textField txtTienKhach
//        String tienKhachDuaStr = txtTienKhach.getText();
//
//        // Kiểm tra nếu không có giá trị nhập vào, thoát khỏi phương thức
//        if (tienKhachDuaStr.isEmpty()) {
//            return;
//        }
//
//        // Parse tiền khách đưa từ textField txtTienKhach
//        double tienKhachDua = Double.parseDouble(tienKhachDuaStr);
//
//        // Tính tiền thừa và hiển thị lên label
//        double tienThua = tienKhachDua - tongTien;
//        lbTienThua.setText(String.format("%.2f", tienThua));
//     }
//
//
//    public void hientienThua(double tongTienSauGiam , double tienGiam) {
//
//// Lấy giá trị từ textField txtTienKhach
//    String tienKhachDuaStr = txtTienKhach.getText();
//
//    // Kiểm tra nếu không có giá trị nhập vào, thoát khỏi phương thức
//    if (tienKhachDuaStr.isEmpty()) {
//        lbTienThua.setText("");
//        return;
//    }
//
//    // Parse tiền khách đưa từ textField txtTienKhach
//    double tienKhachDua = Double.parseDouble(tienKhachDuaStr);
//
//    // Tính tiền thừa và hiển thị lên label
//    double tienThua = tienKhachDua - tinhTongTienGioHang()+ tienGiam;
//    lbTienThua.setText(String.format("%.2f", tienThua));
//
//
//    }
//    
//    
//    public void hienThiThongTinKhuyenMai(khuyenmai km) {
//        if (km != null) {
//            txtMaKM.setText(String.valueOf(km.getMakm()));
//            txtTenKM.setText(km.getTenkm());
//            txtGiaTriGiam.setText(String.valueOf(km.getGiatri()));
//        }
//    }
//
//    public double tinhTongTienSauGiam(double tongTien, double giaTriGiam, boolean isPercent) {
//        if (isPercent) {
//        return tongTien - (tongTien * giaTriGiam / 100);
//    } else {
//        return tongTien - giaTriGiam;
//    }

//    }
    public void ThemKhandSDT(String tenKhachHang, String sdt) {
        String sqlInsertKhachHang = "INSERT INTO KHACHHANG (TENKH, SDT, NGAYSINH, GIOITINH, DIACHI, EMAIL, TRANGTHAI) VALUES (?, ?, '2000-01-01', 'Unknown', 'Không xác định', 'Không xác định', 1)";

        // Kiểm tra tính hợp lệ của tên và số điện thoại
        if (tenKhachHang == null || tenKhachHang.trim().isEmpty() || tenKhachHang.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(null, "Tên khách hàng không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (sdt == null || !sdt.matches("\\d{1,11}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Vui lòng kiểm tra lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sqlInsertKhachHang, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, tenKhachHang);
            statement.setString(2, sdt);
            statement.executeUpdate();

            // Lấy mã khách hàng tự sinh
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int maKhachHang = generatedKeys.getInt(1);
                System.out.println("Mã khách hàng mới thêm: " + maKhachHang);
            }

            JOptionPane.showMessageDialog(null, "Thêm khách hàng thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi thêm khách hàng. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setNhanVienFullName(String username) {
        String fullName = UserDao.getUserFullName(username);
        txtnhanvien.setText(fullName);
    }

    // Sự kiện "focus lost" của textField txtTienKhach
    private String selectedMaHoaDon = null;

    public static int themHoaDonVaoDatabase(boolean trangThai) {
        String sql = "INSERT INTO HOADON (TrangThai) VALUES (?)";
        int generatedId = -1;

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setBoolean(1, trangThai);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1); // Lấy mã hóa đơn vừa tạo
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return generatedId;

    }

    public void loadHoadon() {
        DefaultTableModel dtm = (DefaultTableModel) tblhoadoncho.getModel();
        dtm.setRowCount(0);

        HDT.clear();

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement("SELECT MAHD, TrangThai, FORMAT(NGAYTAO, 'dd/MM/yyyy') AS NgayTao FROM HOADON WHERE TrangThai = ?")) {
            statement.setInt(1, 1); // Chỉ lấy các hóa đơn chưa thanh toán (Trạng thái = 1)
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int maHD = resultSet.getInt("MAHD");
                String ngaytao = resultSet.getString("NgayTao");
                String trangThai = "Chờ thanh toán";

                Object[] row = new Object[]{maHD, trangThai, ngaytao};
                dtm.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void xoaHoaDonDaThanhToan(int maHD) {
        DefaultTableModel dtmHoaDon = (DefaultTableModel) tblhoadoncho.getModel();
        int rowCount = dtmHoaDon.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            int maHoaDon = (int) dtmHoaDon.getValueAt(i, 0);
            if (maHoaDon == maHD) {
                dtmHoaDon.removeRow(i);
                break;
            }
        }
    }

    public void loadSanPham() {
        model = (DefaultTableModel) tblSanPham.getModel();
        ArrayList<SanPham> lstSanPham = sps.getAllSanPham();
        model.setRowCount(0);
        for (SanPham sp : lstSanPham) {
            model.addRow(new Object[]{sp.getMaSP(), sp.getTenSp(), sp.getDonGia(), sp.getSoLuong(), sp.getDungTich(), sp.getMuiHuong(), sp.getThuongHieu(), sp.isTrangThai() ? "Còn hàng" : "Hết hàng", sp.getHinhAnh(), sp.getNgayTao(), sp.getNgaySua()});
        }
    }
    private List<Object[]> gioHangItems = new ArrayList<>();

    private void capNhatTongTien() {
        DefaultTableModel dtm = (DefaultTableModel) tblgiohang.getModel();
        double tongTien = 0;
        for (int i = 0; i < dtm.getRowCount(); i++) {
            double giaBan = Double.parseDouble(dtm.getValueAt(i, 3).toString());
            int soLuong = Integer.parseInt(dtm.getValueAt(i, 4).toString());
            tongTien += giaBan * soLuong;
        }
        lbTongTien.setText(String.format("%.2f", tongTien));
    }

    public void LoadGioHang(String maHoaDon) {
        DefaultTableModel dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        // Load giỏ hàng của mã hóa đơn từ danh sách HDCT
        for (HoaDonCT hoaDonCT : HDCT) {
            if (hoaDonCT.getMahd().equals(maHoaDon)) {
                String maHoadon = hoaDonCT.getMahd();
                String maSanPham = hoaDonCT.getMaSanPhamct();
                String tenSanPham = getTenSanPham(maSanPham);
                Integer giaBan = hoaDonCT.getGiaban();
                double soLuong = hoaDonCT.getSoluong();

                // Tính tổng tiền từ số lượng và giá bán
                double tongTien = soLuong * giaBan;

                Object[] row = new Object[]{maHoadon, maSanPham, tenSanPham, giaBan, soLuong, tongTien};
                dtm.addRow(row); // Thêm mục vào bảng tblgiohang
            }
        }

        double tongTienGioHang = tinhTongTienGioHang();
        lbTongTien.setText(String.valueOf(tongTienGioHang));
        lbTienThua.setText(String.valueOf(0));
    }

    private void themChiTietHoaDon(int maHoaDon, SanPham sp) {
        String sql = "INSERT INTO HOADONCT (MAHD, MASPCT, SOLUONG, DONGIA, TONGTIEN, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, maHoaDon);
            statement.setString(2, sp.getMaSP()); // Giả định rằng bạn có phương thức getMaSPCT() trong SanPham
            statement.setInt(3, sp.getSoLuong());
            statement.setDouble(4, sp.getDonGia());
            statement.setDouble(5, sp.getSoLuong() * sp.getDonGia());
            statement.setBoolean(6, true); // Giả định rằng trạng thái là true khi thanh toán
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void upgiohang(String maHoaDon) {
        DefaultTableModel dtm = (DefaultTableModel) tblgiohang.getModel();
        dtm.setRowCount(0);

        List<SanPham> gioHang = gioHangMap.get(maHoaDon);
        if (gioHang != null) {
            for (SanPham sp : gioHang) {
                int soLuong = sp.getSoLuong();
                double donGia = sp.getDonGia();
                double tongTien = soLuong * donGia; // Tính tổng tiền
                Object[] row = new Object[]{maHoaDon, sp.getMaSP(), sp.getTenSp(), donGia, soLuong, tongTien};
                dtm.addRow(row);
            }
        }
        capNhatTongTien(); // Cập nhật tổng tiền sau khi cập nhật giỏ hàng
    }

    private void xuLyChonHoaDon(String maHoaDon) {
        if (!gioHangMap.containsKey(maHoaDon)) {
            gioHangMap.put(maHoaDon, new ArrayList<>());
        }
        // Hiển thị giỏ hàng tương ứng
        upgiohang(maHoaDon);
    }

    public void thanhToanHoaDon() {
        int selectedRow = tblhoadoncho.getSelectedRow();
        if (selectedRow >= 0) {
            int maHoaDon = (int) tblhoadoncho.getValueAt(selectedRow, 0);

            // Cập nhật trạng thái hóa đơn
            updateTrangThaiHoaDon(maHoaDon, false);

            // Thêm chi tiết hóa đơn cho mỗi sản phẩm trong giỏ hàng
            List<SanPham> gioHang = gioHangMap.get(String.valueOf(maHoaDon));
            if (gioHang != null) {
                for (SanPham sp : gioHang) {
                    themChiTietHoaDon(maHoaDon, sp);
                }
            }

            // Xóa dòng trong bảng tblhoadon
            DefaultTableModel dtmHoaDon = (DefaultTableModel) tblhoadoncho.getModel();
            dtmHoaDon.removeRow(selectedRow);

            // Xóa hóa đơn đã thanh toán khỏi bảng tblhoadoncho
            xoaHoaDonDaThanhToan(maHoaDon);
        }
    }

    public int updateTrangThaiHoaDon(int maHoaDon, boolean trangThai) {
        String sql = "UPDATE HOADON SET TrangThai = ? WHERE MAHD = ?";
        int rowsAffected = 0;

        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setBoolean(1, trangThai);
            statement.setInt(2, maHoaDon);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rowsAffected;
    }

    public void huyHoaDon(int maHoaDon) {
        String sqlDeleteHoaDonCT = "DELETE FROM HOADONCT WHERE MAHD = ?";
        String sqlDeleteHoaDon = "DELETE FROM HOADON WHERE MAHD = ?";

        try (Connection con = DBConnect.getConnection()) {
            // Bắt đầu một giao dịch
            con.setAutoCommit(false);

            try (PreparedStatement statementCT = con.prepareStatement(sqlDeleteHoaDonCT); PreparedStatement statementHD = con.prepareStatement(sqlDeleteHoaDon)) {

                // Xóa chi tiết hóa đơn trước
                statementCT.setInt(1, maHoaDon);
                statementCT.executeUpdate();

                // Sau đó xóa hóa đơn
                statementHD.setInt(1, maHoaDon);
                statementHD.executeUpdate();

                // Xác nhận giao dịch
                con.commit();
            } catch (SQLException ex) {
                // Hủy bỏ giao dịch nếu có lỗi xảy ra
                con.rollback();
                ex.printStackTrace();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // Xóa dòng dữ liệu đã chọn trong bảng tblhoadon
        DefaultTableModel dtmHoaDon = (DefaultTableModel) tblhoadoncho.getModel();
        int selectedRow = tblhoadoncho.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy đơn hàng?", "Xác nhận hủy đơn hàng", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Trả lại số lượng sản phẩm trong giỏ hàng về số lượng sản phẩm trong tblsanpham
                traLaiSanPham(maHoaDon);

                // Xóa dòng dữ liệu của hóa đơn và cập nhật trạng thái hóa đơn trong phương thức này
                dtmHoaDon.removeRow(selectedRow);

                // Làm sạch bảng tblgiohang
                DefaultTableModel dtmGioHang = (DefaultTableModel) tblgiohang.getModel();
                int rowCount = dtmGioHang.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    dtmGioHang.removeRow(i);
                }

                JOptionPane.showMessageDialog(null, "Hóa đơn đã được hủy.");
                txtkhachhang.setText("");
                txtsdt.setText("");
                lbTongTien.setText("");
                txtTienKhach.setText("");
                lbTienThua.setText("");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để hủy.");
        }
    }

    private void traLaiSanPham(int maHoaDon) {
        List<SanPham> gioHang = gioHangMap.get(String.valueOf(maHoaDon));
        if (gioHang != null) {
            for (SanPham sp : gioHang) {
                String maSanPham = sp.getMaSP();
                int soLuongTraLai = sp.getSoLuong();

                // Cập nhật số lượng sản phẩm trong bảng sản phẩm
                int rowCount = tblSanPham.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    String maSP = tblSanPham.getValueAt(i, 0).toString();
                    if (maSP.equals(maSanPham)) {
                        int soLuongHienCoSP = Integer.parseInt(tblSanPham.getValueAt(i, 3).toString());
                        soLuongHienCoSP += soLuongTraLai; // Tăng số lượng hiện có lên bằng số lượng trả lại
                        tblSanPham.setValueAt(soLuongHienCoSP, i, 3); // Cập nhật đúng cột số lượng
                        System.out.println("Updated quantity for product " + maSanPham + ": " + soLuongHienCoSP);
                        // Cập nhật số lượng trong cơ sở dữ liệu SQL
                        updateSanPhamSoLuong(maSanPham, soLuongHienCoSP);
                        break;
                    }
                }
            }
            // Xóa giỏ hàng sau khi trả lại sản phẩm
            gioHangMap.remove(String.valueOf(maHoaDon));
        }
        // Cập nhật lại bảng sản phẩm
        ((DefaultTableModel) tblSanPham.getModel()).fireTableDataChanged();
    }

    public void traHang() {
    int selectedRow = tblgiohang.getSelectedRow();
    if (selectedRow >= 0) {
        String maSanPham = tblgiohang.getValueAt(selectedRow, 1).toString();
        SanPham sp = SP_REPO.stream()
                .filter(s -> s.getMaSP().equals(maSanPham))
                .findFirst()
                .orElse(null);

        if (sp == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int selectedHoaDon = tblhoadoncho.getSelectedRow();
        if (selectedHoaDon == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn trước khi chọn sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String maHoaDon = tblhoadoncho.getValueAt(selectedHoaDon, 0).toString();

        List<SanPham> gioHang = gioHangMap.get(maHoaDon);
        SanPham gioHangSpToDecrement = gioHang.get(selectedRow);
        int soLuongHienCo = gioHangSpToDecrement.getSoLuong();

        // Hiện ra ô input để người dùng nhập số lượng trả
        String soLuongStr = JOptionPane.showInputDialog("Nhập số lượng sản phẩm muốn trả:");
        if (soLuongStr == null || soLuongStr.isEmpty()) {
            return; // Thoát khỏi phương thức nếu người dùng không nhập gì
        }

        int soLuongTra = 0;
        try {
            soLuongTra = Integer.parseInt(soLuongStr);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Số lượng nhập không hợp lệ.", "Lỗi định dạng số", JOptionPane.ERROR_MESSAGE);
            return; // Thoát khỏi phương thức nếu xảy ra lỗi định dạng số
        }

        if (soLuongTra <= 0 || soLuongTra > soLuongHienCo) {
            JOptionPane.showMessageDialog(this, "Số lượng trả không hợp lệ hoặc vượt quá số lượng hiện có trong giỏ hàng.", "Lỗi số lượng", JOptionPane.ERROR_MESSAGE);
            return; // Thoát khỏi phương thức nếu số lượng nhập không hợp lệ
        }

        if (soLuongTra < soLuongHienCo) {
            soLuongHienCo -= soLuongTra;
            gioHangSpToDecrement.setSoLuong(soLuongHienCo);

            // Tăng số lượng sản phẩm trong bảng sản phẩm
            int rowCount = tblSanPham.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maSP = tblSanPham.getValueAt(i, 0).toString();
                if (maSP.equals(maSanPham)) {
                    int soLuongHienCoSP = Integer.parseInt(tblSanPham.getValueAt(i, 3).toString());
                    soLuongHienCoSP += soLuongTra; // Tăng số lượng hiện có lên số lượng trả
                    tblSanPham.setValueAt(soLuongHienCoSP, i, 3); // Cập nhật đúng cột số lượng
updateSanPhamSoLuong(maSanPham, soLuongHienCoSP);
                    break;
                }
            }

            // Cập nhật lại bảng sản phẩm
            ((DefaultTableModel) tblSanPham.getModel()).fireTableDataChanged();

            // Cập nhật lại tổng tiền dans le giỏ hàng
            double donGia = gioHangSpToDecrement.getDonGia();
            double tongTienMoi = soLuongHienCo * donGia;
            tblgiohang.setValueAt(tongTienMoi, selectedRow, 5); // Cập nhật cột tổng tiền
            tblgiohang.setValueAt(soLuongHienCo, selectedRow, 4); // Cập nhật số lượng trong giỏ hàng

            // Cập nhật lại bảng giỏ hàng
            ((DefaultTableModel) tblgiohang.getModel()).fireTableDataChanged();
        } else {
            // Trả lại toàn bộ sản phẩm về kho khi số lượng trả bằng số lượng hiện có
            int rowCount = tblSanPham.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String maSP = tblSanPham.getValueAt(i, 0).toString();
                if (maSP.equals(maSanPham)) {
                    int soLuongHienCoSP = Integer.parseInt(tblSanPham.getValueAt(i, 3).toString());
                    soLuongHienCoSP += soLuongHienCo; // Tăng số lượng hiện có lên bằng số lượng trả lại
                    tblSanPham.setValueAt(soLuongHienCoSP, i, 3); // Cập nhật đúng cột số lượng
                    updateSanPhamSoLuong(maSanPham, soLuongHienCoSP);
                    break;
                }
            }

            // Cập nhật lại bảng sản phẩm
            ((DefaultTableModel) tblSanPham.getModel()).fireTableDataChanged();

            // Xóa sản phẩm khỏi giỏ hàng
            gioHang.remove(selectedRow);

            // Cập nhật lại bảng giỏ hàng
            ((DefaultTableModel) tblgiohang.getModel()).removeRow(selectedRow);
            ((DefaultTableModel) tblgiohang.getModel()).fireTableDataChanged();

            JOptionPane.showMessageDialog(this, "Đã trả lại hết sản phẩm này về giỏ giỏ.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        capNhatTongTien();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        txtkhachhang = new javax.swing.JTextField();
        txtnhanvien = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTienKhach = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        txtTenKM = new javax.swing.JTextField();
        txtGiaTriGiam = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbbHinhThucThanhToan = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnGiamGia = new javax.swing.JButton();
        txtTienGiam = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lbTienThua = new javax.swing.JTextField();
        btnXoaGiamGia = new javax.swing.JButton();
        btnthanhtoanfull = new javax.swing.JButton();
        btntaohd = new javax.swing.JButton();
        btnhuydon = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txttimkiemsanpham = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblgiohang = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblhoadoncho = new javax.swing.JTable();

        jPanel1.setPreferredSize(new java.awt.Dimension(807, 630));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel14.setText("Số ĐT");

        jLabel15.setText("Tên Khách Hàng");

        jLabel16.setText("Nhân Viên Bán");

        txtnhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnhanvienActionPerformed(evt);
            }
        });

        jLabel8.setText("Tổng Tiền");

        jLabel11.setText("Tiền Khách Đưa");

        jLabel12.setText("Tiền Thừa");

        jLabel2.setText("Tên KM");

        jLabel3.setText("Giá Trị Giảm");

        jLabel5.setText("Hình Thức Thanh Toán");

        cbbHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền Mặt", "Chuyển Khoản" }));

        jLabel6.setText("Tiền Giảm");

        btnGiamGia.setText("Giảm Giá");
        btnGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiamGiaActionPerformed(evt);
            }
        });

        jLabel1.setText("Mã KM");

        lbTienThua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbTienThuaActionPerformed(evt);
            }
        });

        btnXoaGiamGia.setText("Xóa Giảm Giá");
        btnXoaGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaGiamGiaActionPerformed(evt);
            }
        });

        btnthanhtoanfull.setBackground(new java.awt.Color(0, 204, 255));
        btnthanhtoanfull.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthanhtoanfull.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon-cart.png"))); // NOI18N
        btnthanhtoanfull.setText("Thanh Toán");
        btnthanhtoanfull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthanhtoanfullActionPerformed(evt);
            }
        });

        btntaohd.setBackground(new java.awt.Color(0, 255, 204));
        btntaohd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btntaohd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add.png"))); // NOI18N
        btntaohd.setText("Tạo Hóa Đơn Chờ");
        btntaohd.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btntaohd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntaohdActionPerformed(evt);
            }
        });

        btnhuydon.setBackground(new java.awt.Color(255, 51, 51));
        btnhuydon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnhuydon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-delete-24.png"))); // NOI18N
        btnhuydon.setText("Hủy đơn chờ");
        btnhuydon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhuydonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14)
                            .addComponent(jLabel8))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(txtnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnXoaGiamGia))
                            .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cbbHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnGiamGia)))))
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel12)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnhuydon, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(btntaohd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnthanhtoanfull, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtnhanvien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtkhachhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTienKhach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaGiamGia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGiamGia))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGiaTriGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnthanhtoanfull, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntaohd, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnhuydon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel10))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel4.setText("Tìm Kiếm");

        txttimkiemsanpham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemsanphamActionPerformed(evt);
            }
        });
        txttimkiemsanpham.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemsanphamKeyReleased(evt);
            }
        });

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã SP", "Tên SP", "Đơn giá", "Số lượng", "Dung tích", "Mùi hương", "Thương hiệu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblSanPham);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chờ chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblgiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Mã SP", "Tên SP", "Đơn Giá ", "Số Lượng", "Tổng Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblgiohang.setShowGrid(true);
        tblgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgiohangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblgiohang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addComponent(txttimkiemsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txttimkiemsanpham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn Chờ"));

        tblhoadoncho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Trạng thái", "Ngày tạo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblhoadoncho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadonchoMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblhoadoncho);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtnhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnhanvienActionPerformed

    }//GEN-LAST:event_txtnhanvienActionPerformed
    private void updateSanPhamSoLuong(String maSanPham, int soLuongMoi) {
        String sql = "UPDATE SANPHAMCT SET SOLUONG = ? WHERE MASPCT = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, soLuongMoi);
            statement.setString(2, maSanPham);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật số lượng sản phẩm trong cơ sở dữ liệu.", "Lỗi cập nhật", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow >= 0) {
            String maSanPham = tblSanPham.getValueAt(selectedRow, 0).toString();
            SanPham sp = SP_REPO.stream()
                    .filter(s -> s.getMaSP().equals(maSanPham))
                    .findFirst()
                    .orElse(null);

            if (sp == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int soLuongHienCo = 0;
            try {
                soLuongHienCo = Integer.parseInt(tblSanPham.getValueAt(selectedRow, 3).toString());
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm không hợp lệ.", "Lỗi định dạng số", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu xảy ra lỗi định dạng số
            }

            if (soLuongHienCo <= 0) {
                JOptionPane.showMessageDialog(this, "Sản phẩm này đã hết hàng!", "Thông báo", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu số lượng sản phẩm bằng 0 hoặc ít hơn
            }

            int selectedHoaDon = tblhoadoncho.getSelectedRow();
            if (selectedHoaDon == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn trước khi chọn sản phẩm.", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String maHoaDon = tblhoadoncho.getValueAt(selectedHoaDon, 0).toString();

            // Kiểm tra xem hóa đơn đã tồn tại trong giỏ hàng chưa
            if (!gioHangMap.containsKey(maHoaDon)) {
                gioHangMap.put(maHoaDon, new ArrayList<>());
            }

            List<SanPham> gioHang = gioHangMap.get(maHoaDon);

            // Kiểm tra nếu sản phẩm đã tồn tại trong giỏ hàng
            boolean isExisted = false;
            for (SanPham gioHangSp : gioHang) {
                if (gioHangSp.getMaSP().equals(maSanPham)) {
                    gioHangSp.setSoLuong(gioHangSp.getSoLuong() + 1);
                    isExisted = true;
                    break;
                }
            }

            if (!isExisted) {
                sp.setSoLuong(1);
                gioHang.add(sp);
            }

            // Trừ đi số lượng sản phẩm sau khi thêm vào giỏ hàng
            soLuongHienCo--;
            tblSanPham.setValueAt(soLuongHienCo, selectedRow, 3);
            // Cập nhật số lượng trong cơ sở dữ liệu SQL
            updateSanPhamSoLuong(maSanPham, soLuongHienCo);

            // Cập nhật lại bảng sản phẩm
            ((DefaultTableModel) tblSanPham.getModel()).fireTableDataChanged();

            // Hiển thị giỏ hàng trong bảng tblgiohang
            DefaultTableModel dtmGioHang = (DefaultTableModel) tblgiohang.getModel();
            dtmGioHang.setRowCount(0);

            for (SanPham gioHangSp : gioHang) {
                Object[] row = new Object[]{maHoaDon, gioHangSp.getMaSP(), gioHangSp.getTenSp(), gioHangSp.getDonGia(), gioHangSp.getSoLuong(), gioHangSp.getDonGia() * gioHangSp.getSoLuong()};
                dtmGioHang.addRow(row);
            }
            capNhatTongTien();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để thêm vào giỏ hàng.", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_tblSanPhamMouseClicked


    private void tblhoadonchoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadonchoMouseClicked

        int selectedRow = tblhoadoncho.getSelectedRow();
        if (selectedRow >= 0) {
            String maHoaDonStr = tblhoadoncho.getValueAt(selectedRow, 0).toString();
            xuLyChonHoaDon(maHoaDonStr);
        }
    }//GEN-LAST:event_tblhoadonchoMouseClicked

    private void btnthanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhtoanActionPerformed
        // TODO add your handling code here: lỗi

    }//GEN-LAST:event_btnThanhtoanActionPerformed

    private void btnthanhtoanfullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthanhtoanfullActionPerformed
        double tongTien = tinhTongTienGioHang();

// Hiển thị tổng tiền lên label
        lbTongTien.setText(String.format("%.2f", tongTien));

// Parse tiền khách đưa từ textField txtTienKhach
        double tienKhachDua = 0;
        try {
            tienKhachDua = Double.parseDouble(txtTienKhach.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

// Tính tiền thừa và hiển thị lên label
        double tienThua = tienKhachDua - tongTien;
        if (tienThua < 0) {
            JOptionPane.showMessageDialog(this, "Khách chưa đưa đủ tiền!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        } else {
            lbTienThua.setText(String.format("%.2f", tienThua));
            // Xử lý thanh toán và cập nhật dữ liệu vào cơ sở dữ liệu ở đây
            String tenKhachHang = txtkhachhang.getText();
            String sdt = txtsdt.getText();

            if (!tenKhachHang.isEmpty() && !sdt.isEmpty()) {
                ThemKhandSDT(tenKhachHang, sdt);
            } else {

            }

            //JOptionPane.showMessageDialog(this, "Thanh toán thành công, tiền thừa là: " + String.format("%.2f", tienThua), "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }

        int selectedRow = tblhoadoncho.getSelectedRow();
        if (selectedRow >= 0) {
            SwingUtilities.invokeLater(() -> {
                DefaultTableModel model = (DefaultTableModel) tblhoadoncho.getModel();
                if (selectedRow < model.getRowCount()) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thanh toán đơn hàng?", "Xác nhận hủy đơn hàng", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        try {
                            int maHoaDon = (int) tblhoadoncho.getValueAt(selectedRow, 0);
                            thanhToanHoaDon();

                            if (selectedRow < model.getRowCount()) {
                                model.removeRow(selectedRow);
                                JOptionPane.showMessageDialog(null, "Hóa đơn đã được thanh toán.");
                            } else {
                                JOptionPane.showMessageDialog(null, "thanh toán thành công.");
                                txtkhachhang.setText("");
                                txtsdt.setText("");
                                lbTongTien.setText("");
                                txtTienKhach.setText("");
                                lbTienThua.setText("");
                                txtMaKM.setText("");
                                txtTenKM.setText("");
                                txtGiaTriGiam.setText("");
                                txtTienGiam.setText("");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi xử lý hóa đơn. Vui lòng thử lại.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Dữ liệu hóa đơn đã thay đổi, vui lòng thử lại.");
                }
            });
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để thanh toán.");
        }


    }//GEN-LAST:event_btnthanhtoanfullActionPerformed

    private void txttimkiemsanphamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemsanphamKeyReleased
        // TODO add your handling code here:
        // TODO add your handling code here:
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        TableRowSorter<DefaultTableModel> ab = new TableRowSorter<>(dtm);
        tblSanPham.setRowSorter(ab);
        ab.setRowFilter(RowFilter.regexFilter(txttimkiemsanpham.getText()));
    }//GEN-LAST:event_txttimkiemsanphamKeyReleased

    private void txttimkiemsanphamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemsanphamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemsanphamActionPerformed

    private void tblgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseClicked
        traHang();

    }//GEN-LAST:event_tblgiohangMouseClicked

    private void btnGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiamGiaActionPerformed
        String maKhuyenMai = txtMaKM.getText();
        String tienKhachDuaStr = txtTienKhach.getText();
        KhuyenmaiService khuyenMaiService = new KhuyenmaiService();
        khuyenmai km = khuyenMaiService.getKhuyenMaiByMa(maKhuyenMai);
        if (km != null) {
            hienThiThongTinKhuyenMai(km);
            double giaTriGiam = km.getGiatri();
            boolean isPercent = km.getDonVi().equals("%");
            double tongTien = tinhTongTienGioHang();

            // Calculate discount value
            double tienGiam = (tongTien * giaTriGiam / 100);

            // Calculate total amount after discount
            double tongTienSauGiam = tongTien - tienGiam;

            // Display the discount value
            txtTienGiam.setText(String.format("%.2f", tienGiam));

            // Update the total amount label
            lbTongTien.setText(String.format("%.2f", tongTienSauGiam));

            // Update the customer's change
            double tienKhachDua = Double.parseDouble(tienKhachDuaStr);
            double tienThua = tienKhachDua - tongTienSauGiam;
            lbTienThua.setText(String.format("%.2f", tienThua));

        } else {
            JOptionPane.showMessageDialog(formbanhang.this, "Mã khuyến mãi không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGiamGiaActionPerformed

    private void btnXoaGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaGiamGiaActionPerformed
        txtMaKM.setText("");
        txtTenKM.setText("");
        txtGiaTriGiam.setText("");
        txtTienGiam.setText("");
        double tongTien = tinhTongTienGioHang();

        lbTongTien.setText(String.format("%.2f", tongTien));

        double tienKhachDua = Double.parseDouble(txtTienKhach.getText());
        double tienThua = tienKhachDua - tongTien;
        lbTienThua.setText(String.format("%.2f", tienThua));
    }//GEN-LAST:event_btnXoaGiamGiaActionPerformed

    private void btnhuydonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhuydonActionPerformed
        int selectedRow = tblhoadoncho.getSelectedRow();
        if (selectedRow >= 0) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy đơn hàng?", "Xác nhận hủy đơn hàng", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int maHoaDon = (int) tblhoadoncho.getValueAt(selectedRow, 0);
                huyHoaDon(maHoaDon); // Xóa dòng dữ liệu của hóa đơn và cập nhật trạng thái hóa đơn trong phương thức này
                //                huyTrangThaiHoaDon(maHoaDon, false); // Đổi trạng thái thành 0 (false)

                txtkhachhang.setText("");
                txtsdt.setText("");
                lbTongTien.setText("");
                txtTienKhach.setText("");
                lbTienThua.setText("");

            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để hủy.");
        }
    }//GEN-LAST:event_btnhuydonActionPerformed

    private void btntaohdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntaohdActionPerformed

        //        themHoaDonVaoDatabase(true);
        //        loadHoadon(); // Lấy dữ liệu từ giao diện người dùng
        boolean trangThai = true;

        // Thêm hóa đơn vào cơ sở dữ liệu
        int maHD = themHoaDonVaoDatabase(trangThai);

        if (maHD != -1) {
            // Xóa hóa đơn đã thanh toán khỏi bảng
            xoaHoaDonDaThanhToan(maHD);

            // Tải lại danh sách hóa đơn
            loadHoadon();
        }

    }//GEN-LAST:event_btntaohdActionPerformed

    private void lbTienThuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbTienThuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbTienThuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiamGia;
    private javax.swing.JButton btnXoaGiamGia;
    private javax.swing.JButton btnhuydon;
    private javax.swing.JButton btntaohd;
    private javax.swing.JButton btnthanhtoanfull;
    private javax.swing.JComboBox<String> cbbHinhThucThanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField lbTienThua;
    private javax.swing.JTextField lbTongTien;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTable tblhoadoncho;
    private javax.swing.JTextField txtGiaTriGiam;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTienGiam;
    private javax.swing.JTextField txtTienKhach;
    private javax.swing.JTextField txtkhachhang;
    private javax.swing.JTextField txtnhanvien;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txttimkiemsanpham;
    // End of variables declaration//GEN-END:variables

    private String getTenSanPham(String maSanPham) {
        String tenSanPham = null;
        String sql = "SELECT TENSP FROM SANPHAM WHERE MASP = ?";
        try (Connection con = DBConnect.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, maSanPham);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                tenSanPham = rs.getString("TENSP");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return tenSanPham;
    }

}
