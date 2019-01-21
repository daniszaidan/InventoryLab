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
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 *
 * @author DanZai
 */
public class FormAdminEditController implements Initializable{
    public static String id_user_val;
    
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXButton editButton;

    @FXML
    private ImageView loading;

    @FXML
    private JFXTextField nama;

    @FXML
    private JFXTextField nim;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loading.setVisible(false);
        id_user_val = UserData.id_user_edit;
        
//        id_user.setText(UserData.id_user_edit);
        username.setText(UserData.username_edit);
        password.setText(UserData.password_edit);
        nama.setText(UserData.nama_edit);
        nim.setText(UserData.nim_edit);
    }

    @FXML
    void editAction(ActionEvent event) {
        System.out.println(id_user_val);
        loading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            try{
                String sql = "UPDATE admin SET "
                        + "username = '"+username.getText()+"', "
                        + "password = '"+password.getText()+"', "
                        + "nama     = '"+nama.getText()+"', "
                        + "nim      = '"+nim.getText()+"' "
                        + "WHERE id_user = '"+id_user_val+"'";
                java.sql.Connection conn = (Connection)Config.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                
//                try{
//                    FXMLLoader.load(getClass().getResource("/FXML/BerandaPage.fxml"));
//                }catch(Exception z){
//                    z.printStackTrace();
//                }
//                
                editButton.getScene().getWindow().hide();
            }catch(Exception e){
                System.out.println("Gagal update : " + e.getMessage());
            }
        });
        pt.play();
    }
}
