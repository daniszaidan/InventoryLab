/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Config;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author DanZai
 */
public class FormAdminInsertController implements Initializable{

    @FXML
    private JFXTextField username;
    
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField nama;

    @FXML
    private JFXTextField nim;
    
    @FXML
    private JFXButton insertButton;

    @FXML
    private ImageView loading;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loading.setVisible(false);
    }

    @FXML
    void insertAction(ActionEvent event) {
        loading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            try{
                String username_val = username.getText();
                String password_val = password.getText();
                String nama_val     = nama.getText();
                String nim_val      = nim.getText();
                
                System.out.println(username_val + ", " + password_val + ", " + nama_val + ", " + nim_val);
                
                String sql = "INSERT INTO admin VALUES (null, '"+username_val+"', '"+password_val+"' , '"+nama_val+"', '"+nim_val+"')";
                java.sql.Connection conn = (Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                
                System.out.println("Berhasil insert");
                username.setText(null);
                password.setText(null);
                nama.setText(null);
                nim.setText(null);
                insertButton.getScene().getWindow().hide();
            }catch(Exception e){
                System.out.println("Gagal insert : " + e.getMessage());
            }
        });
        pt.play();
    }
}
