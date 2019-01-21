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
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author DanZai
 */


public class LoginController implements Initializable{
    
    public static String id_user_data;
    public static String username_data;
    public static String password_data;
    public static String nama_data;
    public static String nim_data;

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton loginButton;

    @FXML
    private ImageView loading;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loading.setVisible(false);
    }
    
    
    @FXML
//    void loginAction(ActionEvent event) {
    void loginAction(Event event) throws IOException, SQLException {
        loading.setVisible(true);
        PauseTransition pt = new PauseTransition();
        pt.setDuration(Duration.seconds(1));
        pt.setOnFinished(ev -> {
            try {
                java.sql.Connection conn = (Connection)Config.configDB();
                ResultSet st = conn.createStatement().executeQuery("SELECT * FROM admin where username = '"+username.getText()+"'and password ='"+String.valueOf(password.getText())+"'");
//                ResultSet st = conn.createStatement().executeQuery("SELECT * FROM admin where username = 'danzai'and password ='123'");

                if(st.next()){
                    id_user_data    = st.getString("id_user");
                    username_data   = st.getString("username");
                    password_data   = st.getString("password");
                    nama_data       = st.getString("nama");
                    nim_data        = st.getString("nim");
                    
                    UserData.id_user = id_user_data;
                    UserData.username = username_data;
                    UserData.password = password_data;
                    UserData.nama = nama_data;
                    UserData.nim = nim_data;
                    
                    loginButton.getScene().getWindow().hide();

                    Stage dashboard = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/Dashboard.fxml"));
                    
                    Scene scene = new Scene(root);
                    dashboard.setScene(scene);
                    dashboard.show();
                    dashboard.setResizable(false);
                }else{
                    System.out.println("Username atau Password salah");
                }            
            } catch (SQLException ex) {
                System.out.println("Ada masalah, silahkan coba kembali");
                ex.printStackTrace();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
//            script diatas udah bener & fix
//            tapi saya komen, karena biar mudah untuk develop (gak usah login)
            
//                try{
//                    loginButton.getScene().getWindow().hide();
//                    Stage dashboard = new Stage();
//                    Parent root = FXMLLoader.load(getClass().getResource("/FXML/Dashboard.fxml"));
//                    Scene scene = new Scene(root);
//                    dashboard.setScene(scene);
//                    dashboard.show();
//                    dashboard.setResizable(false);
//                } catch (IOException ex) {
//                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
//                }
                    
        });
        pt.play();
        
//        try {
//            java.sql.Connection conn = (Connection)Config.configDB();
//            ResultSet st = conn.createStatement().executeQuery("SELECT * FROM admin where username = '"+username.getText()+"'and password ='"+String.valueOf(password.getText())+"'");
//            
//            if(st.next()){
//                loginButton.getScene().getWindow().hide();
//                
//                Stage dashboard = new Stage();
//                Parent root = FXMLLoader.load(getClass().getResource("/FXML/Dashboard.fxml"));        
//                Scene scene = new Scene(root);
//                dashboard.setScene(scene);
//                dashboard.show();
//                dashboard.setResizable(false);
//            }else{
//                System.out.println("Username atau Password salah");
//            }            
//        } catch (SQLException ex) {
//            System.out.println("Ada masalah, silahkan coba kembali");
//            ex.printStackTrace();
//        }

//        loginButton.getScene().getWindow().hide();
//        
//        Stage dashboard = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Dashboard.fxml"));        
//        Scene scene = new Scene(root);
//        dashboard.setScene(scene);
//        dashboard.show();
//        dashboard.setResizable(false);
    }
    
}
