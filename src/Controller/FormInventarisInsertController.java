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
public class FormInventarisInsertController implements Initializable{
    
    @FXML
    private JFXTextField kode_barang;

    @FXML
    private JFXTextField nama_barang;

    @FXML
    private JFXTextField merk;

    @FXML
    private JFXButton insertButton;

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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loading.setVisible(false);
        kondisi.getItems().add(new String("Normal"));
        kondisi.getItems().add(new String("Rusak"));
        kondisi.getItems().add(new String("Lainnya..."));
    }

    @FXML
    void insertAction(ActionEvent event) {
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
                
//                System.out.println(kode_barang_val + ", " + nama_barang_val + ", " + merk_val + ", " + kondisi_val + ", " + tanggal_masuk_val + ", " + harga_barang_val + ", " + keterangan_val);
                
                String sql = "INSERT INTO barang VALUES (null, '"+kode_barang_val+"', '"+nama_barang_val+"' , '"+merk_val+"', '"+kondisi_val+"', '"+tanggal_masuk_val+"', '"+harga_barang_val+"', '"+keterangan_val+"')";
                java.sql.Connection conn = (Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                
                System.out.println("Berhasil insert");
                kode_barang.setText(null);
                nama_barang.setText(null);
                merk.setText(null);
                kondisi.setPromptText("Kondisi");
                tanggal_masuk.setValue(null);
                harga_barang.setText(null);
                keterangan.setText(null);
                insertButton.getScene().getWindow().hide();
            }catch(Exception e){
                System.out.println("Gagal insert : " + e.getMessage());
            }
        });
        pt.play();
    }
    
}
