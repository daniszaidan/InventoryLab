/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Config;
import Data.ModelTableAdmin;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author DanZai
 */
public class AslabPageController implements Initializable{
    
    public static String id_user_data;
    public static String username_data;
    public static String password_data;
    public static String nama_data;
    public static String nim_data;
    
    @FXML
    private AnchorPane homePage;

    @FXML
    protected TableView<ModelTableAdmin> tableAdmin;

    @FXML
    private TableColumn<ModelTableAdmin, Integer> col_id_user;

    @FXML
    private TableColumn<ModelTableAdmin, Integer> col_no;
    
    @FXML
    private TableColumn<ModelTableAdmin, String> col_username;

    @FXML
    private TableColumn<ModelTableAdmin, String> col_password;

    @FXML
    private TableColumn<ModelTableAdmin, String> col_nama;

    @FXML
    private TableColumn<ModelTableAdmin, String> col_nim;

    @FXML
    private ContextMenu actionMenu;

    @FXML
    private MenuItem refreshData;

    @FXML
    private MenuItem editData;

    @FXML
    private MenuItem deleteData;

    @FXML
    private JFXButton insertButton;

    @FXML
    private Pane titlePage;
    
    ObservableList<ModelTableAdmin> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column();
        loadData();
    }
    
    private void column(){
        col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
        col_id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        col_nama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        col_nim.setCellValueFactory(new PropertyValueFactory<>("nim"));
    }
    
    public void loadData(){
        oblist.clear();
        
        try {
            java.sql.Connection conn = (Connection)Config.configDB();
            ResultSet st = conn.createStatement().executeQuery("SELECT * FROM admin");
            int no = 1;
            while (st.next()) {
                oblist.add(new ModelTableAdmin(
                        Integer.toString(no++), 
                        st.getString("id_user"),
                        st.getString("username"), 
                        st.getString("password"), 
                        st.getString("nama"), 
                        st.getString("nim")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Ada masalah, silahkan coba kembali");
            ex.printStackTrace();
        }
        tableAdmin.setItems(oblist);
    }

    @FXML
    void editDataAdmin(ActionEvent event) {
        try {
            ModelTableAdmin selectedForEdit = tableAdmin.getSelectionModel().getSelectedItem();
            
            id_user_data    = selectedForEdit.getId_user();
            username_data   = selectedForEdit.getUsername();
            password_data   = selectedForEdit.getPassword();
            nama_data       = selectedForEdit.getNama();
            nim_data        = selectedForEdit.getNim();
            
            UserData.id_user_edit = id_user_data;
            UserData.username_edit = username_data;
            UserData.password_edit = password_data;
            UserData.nama_edit = nama_data;
            UserData.nim_edit = nim_data;
            
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/FormAdminEdit.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Test Title");
            stage.setScene(new Scene(parent));
            stage.show();
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.runLater(() -> {
                        System.out.println("Application Closed by click to Close Button(X)");
                        loadData();
                        UserData.id_user_edit = null;
                        UserData.username_edit = null;
                        UserData.password_edit = null;
                        UserData.nama_edit = null;
                        UserData.nim_edit = null;
                    });
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void hapusDataAdmin(ActionEvent event) {
        ModelTableAdmin selectedForDelete = tableAdmin.getSelectionModel().getSelectedItem();
        if (selectedForDelete == null) {
            System.out.println("Tidak ada data yang dipilih");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setContentText("Are you sure want to User " + selectedForDelete.getNama()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            try {
                System.out.println(selectedForDelete.getId_user());
                java.sql.Connection conn = (Connection)Config.configDB();
                int st = conn.createStatement().executeUpdate("DELETE FROM admin WHERE username = '"+selectedForDelete.getUsername()+"'");
                if(st == 1){
                    System.out.println("Data berhasil dihapus");
                    oblist.remove(selectedForDelete);
                    loadData();
                }else{
                    System.out.println("Data gagal dihapus");
                }
            } catch (SQLException ex) {
                System.out.println("Ada masalah, silahkan coba kembali");
                ex.printStackTrace();
            }
        } else {
            System.out.println("Gak jadi delete data");
        }
    }

    @FXML
    void insertDataAdmin(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/FormAdminInsert.fxml"));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setTitle("Test Title");
            stage.setScene(new Scene(parent));
            stage.show();
            stage.setOnHiding(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    Platform.runLater(() -> {
                        System.out.println("Application Closed by click to Close Button(X)");
                        loadData();
                    });
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void refreshDataAdmin(ActionEvent event) {
        loadData();
    }
    
    
    
}
