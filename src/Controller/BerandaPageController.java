/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Config;
import com.jfoenix.controls.JFXRippler;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author DanZai
 */
public class BerandaPageController implements Initializable{
    
    @FXML
    private AnchorPane homePage;

    @FXML
    private Pane basePane;

    @FXML
    private Pane inventory_button;

    @FXML
    private Label inventory_count;

    @FXML
    private Pane peminjaman_button;

    @FXML
    private Label peminjaman_count;

    @FXML
    private Pane aslab_button;

    @FXML
    private Label aslab_count;

    @FXML
    private Label greeting;
    
     @FXML
    private Label inventory_activity;

    @FXML
    private Label peminjaman_activity;

    @FXML
    private Label aslab_activity;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Date dt = new Date();
        int hours = dt.getHours();
        
        if(hours<12){
            greeting.setText("Good Morning");
        }else if(hours>12 && hours<=17){
            greeting.setText("Good Afternoon");
        }else if(hours>=17 && hours<=21){
            greeting.setText("Good Evening");
        }else if(hours>=21 && hours<=24){
            greeting.setText("Good Night");
        }
        
        try {
            java.sql.Connection conn = (Connection)Config.configDB();
            
            ResultSet st = conn.createStatement().executeQuery("SELECT COUNT(*)FROM peminjaman;");
            while (st.next()) {
                peminjaman_count.setText(st.getString(1) + " Peminjaman");
            }
            
            ResultSet st2 = conn.createStatement().executeQuery("SELECT COUNT(*)FROM barang;");
            while (st2.next()) {
                inventory_count.setText(st2.getString(1) + " Barang");
            }
            
            ResultSet st3 = conn.createStatement().executeQuery("SELECT COUNT(*)FROM admin;");
            while (st3.next()) {
                aslab_count.setText(st3.getString(1) + " Aslab");
            }
            
            
            ResultSet st4 = conn.createStatement().executeQuery("SELECT * FROM `barang` ORDER BY id_barang DESC LIMIT 1;");
            while (st4.next()) {
                inventory_activity.setText(UserData.nama + ", ada barang " + st4.getString("nama_barang") + " baru dengan kode barang : " + st4.getString("kode_barang"));
            }
            
            ResultSet st5 = conn.createStatement().executeQuery("SELECT * FROM `admin` ORDER BY id_user DESC LIMIT 1;");
            while (st5.next()) {
                aslab_activity.setText(UserData.nama + ", ada Aslab terdaftar baru dengan nama " + st5.getString("nama"));
            }
            
            ResultSet st6 = conn.createStatement().executeQuery("SELECT * FROM `peminjaman` ORDER BY id_peminjaman DESC LIMIT 1;");
            while (st6.next()) {
                peminjaman_activity.setText(UserData.nama + ", ada Barang yang dipinjam terbaru oleh " + st6.getString("nama_peminjam"));
            }
        } catch (SQLException ex) {
            peminjaman_count.setText("10 Peminjaman");
            ex.printStackTrace();
        }
        
    }
}
