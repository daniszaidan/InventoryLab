/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Connection.Config;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
public class FormPeminjamanInsertController implements Initializable{
    
    @FXML
    private JFXTextField nama_peminjam;

    @FXML
    private JFXTextField nim_peminjam;

    @FXML
    private JFXTextField no_telp;

    @FXML
    private JFXDatePicker tanggal_kembali;

    @FXML
    private JFXComboBox<String> id_barang;

    @FXML
    private JFXTextArea keterangan_pinjam;

    @FXML
    private JFXButton insertButton;

    @FXML
    private ImageView loading;

    @FXML
    private JFXTextArea alamat;

    @FXML
    private JFXTextField jumlah;

    @FXML
    private JFXDatePicker tanggal_pinjam;
    
    @FXML
    private Label nama_aslab;
    
    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loading.setVisible(false);
        
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        Calendar cal = Calendar.getInstance();
//        System.out.println(dateFormat.format(cal.getTime()));
//        System.out.println(LOCAL_DATE(dateFormat.format(cal.getTime())));
        
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = new Date();
//        System.out.println(dateFormat.format(date));
        nama_aslab.setText(UserData.nama);
        tanggal_pinjam.setValue(LocalDate.now());
        try {
            java.sql.Connection conn = (Connection)Config.configDB();
            ResultSet st = conn.createStatement().executeQuery("SELECT * FROM  barang");
            int no = 1;
            while (st.next()) {
               id_barang.getItems().add(new String(st.getString(3) + ", " + st.getString(1)));
            }
        } catch (SQLException ex) {
            System.out.println("Ada masalah, silahkan coba kembali");
            ex.printStackTrace();
        }
    }

    @FXML
    void insertAction(ActionEvent event) {              
        loading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            try{
                
                String selectWoi = id_barang.getSelectionModel().getSelectedItem().toString();
                String[] idBarang = selectWoi.split(", ");
                System.out.println(idBarang[1]);
                
                String id_user_val          = UserData.id_user;
                String nama_peminjam_val    = nama_peminjam.getText();
                String nim_peminjam_val     = nim_peminjam.getText();
                String no_telp_val          = no_telp.getText();
                String alamat_val           = alamat.getText();
                String barang_val           = idBarang[1];
                String jumlah_val           = jumlah.getText();
                String tanggal_pinjam_val   = tanggal_pinjam.getValue().toString();
                String tanggal_kembali_val  = tanggal_kembali.getValue().toString();
                String keterangan_pinjam_val= keterangan_pinjam.getText();
                
                String sql = "INSERT INTO peminjaman VALUES (null, '"+id_user_val+"', '"+barang_val+"', '"+nama_peminjam_val+"', '"+nim_peminjam_val+"' , '"+no_telp_val+"',"
                        + " '"+alamat_val+"','"+jumlah_val+"','"+tanggal_pinjam_val+"', '"+tanggal_kembali_val+"', '0', '"+keterangan_pinjam_val+"')";
                
                java.sql.Connection conn = (Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                
                System.out.println("Berhasil insert");
                nama_peminjam.setText(null);
                nim_peminjam.setText(null);
                no_telp.setText(null);
                alamat.setText(null);
                id_barang.setPromptText("Barang");
                jumlah.setText(null);
                tanggal_pinjam.setValue(null);
                tanggal_kembali.setValue(null);
                keterangan_pinjam.setText(null);
                insertButton.getScene().getWindow().hide();
            }catch(Exception e){
                System.out.println("Gagal insert : " + e.getMessage());
            }
        });
        pt.play();
    }
    
}
