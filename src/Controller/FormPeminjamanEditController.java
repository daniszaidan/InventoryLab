/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Connection.Config;
import static Controller.FormInventarisEditController.LOCAL_DATE;
import Data.PeminjamanData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.util.Duration;

/**
 *
 * @author DanZai
 */
public class FormPeminjamanEditController implements Initializable{
    
    public static String id_peminjaman_val;
    public static String id_user_val;
    public static String id_barang_val;
    
    @FXML
    private JFXTextField nama_peminjam;

    @FXML
    private JFXTextField nim_peminjam;

    @FXML
    private JFXTextField no_telp;

    @FXML
    private JFXDatePicker tanggal_pinjam;
    
    @FXML
    private JFXDatePicker tanggal_kembali;

    @FXML
    private JFXComboBox<String> id_barang;

    @FXML
    private JFXTextArea keterangan_pinjam;

    @FXML
    private JFXButton editButton;

    @FXML
    private ImageView loading;

    @FXML
    private JFXTextArea alamat;

    @FXML
    private JFXTextField jumlah;
    
    @FXML
    private Label nama_aslab;
    
    @FXML
    private JFXToggleButton status_pengembalian;

    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Hari    : " + LOCAL_DATE(PeminjamanData.tanggal_pinjam_edit).getDayOfWeek());
        System.out.println("Bulan   : " + LOCAL_DATE(PeminjamanData.tanggal_pinjam_edit).getMonthValue());
        System.out.println("Tahun   : " + LOCAL_DATE(PeminjamanData.tanggal_pinjam_edit).getYear());
        
        loading.setVisible(false);
        nama_aslab.setText("Aslab : " + UserData.nama);
        id_peminjaman_val   = PeminjamanData.id_peminjaman_edit;
        id_user_val         = UserData.id_user;
        
        int status = 0;
        switch (PeminjamanData.status_pengembalian_edit){
            case "Masih Dipinjam":
                status = 0;
                break;
            case "Sudah Kembali":
                status = 1;
                break;
            default:
                status = 0;
                break;
        }
        
        if(status == 1){
            status_pengembalian.setSelected(true);
            System.out.println("true");
        }else{
            status_pengembalian.setSelected(false);
            System.out.println("false");
        }
        
//        String[] id_barang_split = id_barang_val.split(", ");
//        System.out.println(id_barang_split[0]);
        
        try {
            java.sql.Connection conn = (Connection)Config.configDB();
            ResultSet st = conn.createStatement().executeQuery("SELECT * FROM  barang");
            int no = 1;
            while (st.next()) {
               id_barang.getItems().add(new String(st.getString(3) + " , " + st.getString(1)));
            }
        } catch (SQLException ex) {
            System.out.println("Ada masalah, silahkan coba kembali");
            ex.printStackTrace();
        }
        
        String set_tanggal_kembali;
        if(PeminjamanData.tanggal_kembali_edit == null){
            set_tanggal_kembali = null;
        }else{
            set_tanggal_kembali = PeminjamanData.tanggal_pinjam_edit;
        }

        nama_peminjam.setText(PeminjamanData.nama_peminjam_edit);
        nim_peminjam.setText(PeminjamanData.nim_peminjam_edit);
        no_telp.setText(PeminjamanData.no_telp_edit);
        alamat.setText(PeminjamanData.alamat_edit);
        id_barang.setValue(PeminjamanData.nama_barang_edit);
        jumlah.setText(PeminjamanData.jumlah_edit);
        tanggal_pinjam.setValue(LOCAL_DATE(PeminjamanData.tanggal_pinjam_edit));
        tanggal_kembali.setValue(LOCAL_DATE(set_tanggal_kembali));
        keterangan_pinjam.setText(PeminjamanData.keterangan_pinjam_edit);
        
//        String[] split = id_barang_val.split(", ");
//        System.out.println(split[1]);
//        System.out.println(id_barang_val);
    }

    @FXML
    void editAction(ActionEvent event) {
        loading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            try{  
                String id_user_val              = UserData.id_user;
                String nama_peminjam_val        = nama_peminjam.getText();
                String nim_peminjam_val         = nim_peminjam.getText();
                String no_telp_val              = no_telp.getText();
                String alamat_val               = alamat.getText();
                String id_barang_val            = id_barang.getSelectionModel().getSelectedItem().toString();
                String jumlah_val               = jumlah.getText();
                String tanggal_pinjam_val       = tanggal_pinjam.getValue().toString();
                String tanggal_kembali_val      = tanggal_kembali.getValue().toString();
                String keterangan_val           = keterangan_pinjam.getText();
                String status_pengembalian_val  = "";
                if (status_pengembalian.selectedProperty().getValue() == true) {
                    status_pengembalian_val = "1";
                }else{
                    status_pengembalian_val = "0";
                }
                
                String sql = "UPDATE peminjaman SET "
                        + "nama_peminjam        = '"+nama_peminjam_val+"', "
                        + "nim_peminjam         = '"+nim_peminjam_val+"', "
                        + "no_telp              = '"+no_telp_val+"', "
                        + "alamat               = '"+alamat_val+"', "
                        + "jumlah               = '"+jumlah_val+"', "
                        + "tanggal_pinjam       = '"+tanggal_pinjam_val+"', "
                        + "tanggal_kembali      = '"+tanggal_kembali_val+"', "
                        + "status_pengembalian  = '"+status_pengembalian_val+"', "
                        + "keterangan_pinjam    = '"+keterangan_val+"' "
                        + "WHERE id_peminjaman  = '"+id_peminjaman_val+"' ";
                java.sql.Connection conn = (Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                
                editButton.getScene().getWindow().hide();
            }catch(Exception e){
                System.out.println("Gagal update : " + e.getMessage());
            }
        });
        pt.play();
    }
    
}
