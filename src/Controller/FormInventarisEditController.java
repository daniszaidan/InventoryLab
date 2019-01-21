/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Connection.Config;
import Data.InventarisData;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class FormInventarisEditController implements Initializable{
    
    public static String id_barang_val;
    
    @FXML
    private JFXTextField kode_barang;

    @FXML
    private JFXTextField nama_barang;

    @FXML
    private JFXTextField merk;

    @FXML
    private JFXButton editButton;

    @FXML
    private ImageView loading;

    @FXML
    private JFXTextField harga_barang;

    @FXML
    private JFXComboBox<String> kondisi;

    @FXML
    private JFXDatePicker tanggal_masuk;

    @FXML
    private JFXTextArea keterangan;
    
    public static final LocalDate LOCAL_DATE (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loading.setVisible(false);
        kondisi.getItems().add(new String("Normal"));
        kondisi.getItems().add(new String("Rusak"));
        kondisi.getItems().add(new String("Lainnya..."));
        
        id_barang_val = InventarisData.id_barang_edit;
        kode_barang.setText(InventarisData.kode_barang_edit);
        nama_barang.setText(InventarisData.nama_barang_edit);
        merk.setText(InventarisData.merk_edit);
        kondisi.setValue(InventarisData.kondisi_edit);
        tanggal_masuk.setValue(LOCAL_DATE(InventarisData.tanggal_masuk_edit));
        harga_barang.setText(InventarisData.harga_barang_edit);
        keterangan.setText(InventarisData.keterangan_edit);
    }

    @FXML
    void editAction(ActionEvent event) {
        loading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            try{
                String kode_barang_val      = kode_barang.getText();
                String nama_barang_val      = nama_barang.getText();
                String merk_val             = merk.getText();
                String kondisi_val          = kondisi.getSelectionModel().getSelectedItem().toString();
                String tanggal_masuk_val    = tanggal_masuk.getValue().toString();
                String harga_barang_val     = harga_barang.getText();
                String keterangan_val       = keterangan.getText();
                
                String sql = "UPDATE barang SET "
                        + "kode_barang      = '"+kode_barang_val+"', "
                        + "nama_barang      = '"+nama_barang_val+"', "
                        + "merk             = '"+merk_val+"', "
                        + "kondisi          = '"+kondisi_val+"', "
                        + "tanggal_masuk    = '"+tanggal_masuk_val+"', "
                        + "harga_barang     = '"+harga_barang_val+"', "
                        + "keterangan       = '"+keterangan_val+"' "
                        + "WHERE id_barang= '"+id_barang_val+"'";
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
