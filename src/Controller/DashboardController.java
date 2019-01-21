/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author DanZai
 */
public class DashboardController implements Initializable{
    
    @FXML
    private AnchorPane sidebar;

    @FXML
    private JFXButton beranda_button;

    @FXML
    private JFXButton inventaris_button;

    @FXML
    private JFXButton peminjaman_button;

    @FXML
    private JFXButton aslab_button;

    @FXML
    private AnchorPane header;
    
    @FXML
    private Label nama;
    
    @FXML
    public AnchorPane blankPage;
    public AnchorPane home;
    
    public void setNode(Node node){
        blankPage.getChildren().clear();
        blankPage.getChildren().add((Node) node);
        
        FadeTransition ft = new FadeTransition(Duration.millis(500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        beranda_button.setStyle("-fx-background-color: rgba(66, 133, 244, 0.5); -fx-text-fill: #ffffff; -fx-cursor: hand;");
        inventaris_button.setStyle("-fx-cursor: hand;");
        peminjaman_button.setStyle("-fx-cursor: hand;");
        aslab_button.setStyle("-fx-cursor: hand;");
        createPage();
        
        nama.setText("Hi, "+UserData.nama);
    }
    
    public void createPage() {
        try {
            home = FXMLLoader.load(getClass().getResource("/FXML/BerandaPage.fxml"));
            setNode(home);
        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void halamanAslab(){
        try {
            home = FXMLLoader.load(getClass().getResource("/FXML/AslabPage.fxml"));
            setNode(home);
        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    @FXML
    void beranda_page(ActionEvent event) {
        System.out.println("beranda_page");
        beranda_button.setStyle("-fx-background-color: rgba(66, 133, 244, 0.5); -fx-text-fill: #ffffff; -fx-cursor: hand;");
        inventaris_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        peminjaman_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        aslab_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        
        try {
            home = FXMLLoader.load(getClass().getResource("/FXML/BerandaPage.fxml"));
            setNode(home);
        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }

    @FXML
    void inventaris_page(ActionEvent event) {
        System.out.println("inventaris_page");
        beranda_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        inventaris_button.setStyle("-fx-background-color: rgba(66, 133, 244, 0.5); -fx-text-fill: #ffffff; -fx-cursor: hand;");
        peminjaman_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        aslab_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        
        try {
            home = FXMLLoader.load(getClass().getResource("/FXML/InventarisPage.fxml"));
            setNode(home);
            
//            Pop Up window : 
//            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/InventarisPage.fxml"));
//            Stage stage = new Stage(StageStyle.DECORATED);
//            stage.setTitle("Test Title");
//            stage.setScene(new Scene(parent));
//            stage.show();
        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    @FXML
    void peminjaman_page(ActionEvent event) {
        System.out.println("peminjaman_page");
        beranda_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        inventaris_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        peminjaman_button.setStyle("-fx-background-color: rgba(66, 133, 244, 0.5); -fx-text-fill: #ffffff; -fx-cursor: hand;");
        aslab_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        
        try {
            home = FXMLLoader.load(getClass().getResource("/FXML/PeminjamanPage.fxml"));
            setNode(home);
        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    @FXML
    void aslab_page(ActionEvent event) {
        System.out.println("aslab_page");
        beranda_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        inventaris_button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #767676; -fx-cursor: hand;");
        peminjaman_button.setStyle("-fx-background-color: #ffffff ; -fx-text-fill: #767676; -fx-cursor: hand;");
        aslab_button.setStyle("-fx-background-color: rgba(66, 133, 244, 0.5); -fx-text-fill: #ffffff; -fx-cursor: hand;");
        
        try {
            home = FXMLLoader.load(getClass().getResource("/FXML/AslabPage.fxml"));
            setNode(home);
        } catch (IOException ex) {
//            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void logoutAction(){
        try{
            header.getScene().getWindow().hide();
            
            Stage dashboard = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/LoginMain.fxml"));
            
            Scene scene = new Scene(root);
            dashboard.setScene(scene);
            dashboard.show();
            dashboard.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
