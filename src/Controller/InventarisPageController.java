/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Config;
import Data.InventarisData;
import Data.ModelTableInventaris;
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
public class InventarisPageController implements Initializable{
    
    public static String id_barang_data;
    public static String kode_barang_data;
    public static String nama_barang_data;
    public static String merk_data;
    public static String kondisi_data;
    public static String tanggal_masuk_data;
    public static String harga_barang_data;
    public static String keterangan_data;
    
    @FXML
    private AnchorPane homePage;

    @FXML
    private Pane titlePage;

    @FXML
    private JFXButton insertButton;

    @FXML
    private TableView<ModelTableInventaris> tableInventaris;

    @FXML
    private TableColumn<ModelTableInventaris, Integer> col_no;

    @FXML
    private TableColumn<ModelTableInventaris, Integer> col_id_barang;

    @FXML
    private TableColumn<ModelTableInventaris, String> col_kode_barang;

    @FXML
    private TableColumn<ModelTableInventaris, String> col_nama_barang;

    @FXML
    private TableColumn<ModelTableInventaris, String> col_merk;

    @FXML
    private TableColumn<ModelTableInventaris, String> col_kondisi;

    @FXML
    private TableColumn<ModelTableInventaris, String> col_tanggal_masuk;

    @FXML
    private TableColumn<ModelTableInventaris, String> col_harga_barang;

    @FXML
    private TableColumn<ModelTableInventaris, String> col_keterangan;

    @FXML
    private ContextMenu actionMenu;

    @FXML
    private MenuItem refreshData;

    @FXML
    private MenuItem editData;

    @FXML
    private MenuItem deleteData;
    
    ObservableList<ModelTableInventaris> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column();
        loadData();
    }
    
    private void column(){
        col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
        col_id_barang.setCellValueFactory(new PropertyValueFactory<>("id_barang"));
        col_kode_barang.setCellValueFactory(new PropertyValueFactory<>("kode_barang"));
        col_nama_barang.setCellValueFactory(new PropertyValueFactory<>("nama_barang"));
        col_merk.setCellValueFactory(new PropertyValueFactory<>("merk"));
        col_kondisi.setCellValueFactory(new PropertyValueFactory<>("kondisi"));
        col_tanggal_masuk.setCellValueFactory(new PropertyValueFactory<>("tanggal_masuk"));
        col_harga_barang.setCellValueFactory(new PropertyValueFactory<>("harga_barang"));
        col_keterangan.setCellValueFactory(new PropertyValueFactory<>("keterangan"));
        
    }
    
    public void loadData(){
        oblist.clear();
        
        try {
            java.sql.Connection conn = (Connection)Config.configDB();
            ResultSet st = conn.createStatement().executeQuery("SELECT * FROM barang");
            int no = 1;
            while (st.next()) {
                oblist.add(new ModelTableInventaris(
                        Integer.toString(no++), 
                        st.getString("id_barang"), 
                        st.getString("kode_barang"), 
                        st.getString("nama_barang"), 
                        st.getString("merk"), 
                        st.getString("kondisi"), 
                        st.getString("tanggal_masuk"), 
                        st.getString("harga_barang"), 
                        st.getString("keterangan")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Ada masalah, silahkan coba kembali");
            ex.printStackTrace();
        }
        tableInventaris.setItems(oblist);
    }

    @FXML
    void editDataInventaris(ActionEvent event) {
        try{
            ModelTableInventaris selectedForEdit = tableInventaris.getSelectionModel().getSelectedItem();
            id_barang_data      = selectedForEdit.getId_barang();
            kode_barang_data    = selectedForEdit.getKode_barang();
            nama_barang_data    = selectedForEdit.getNama_barang();
            merk_data           = selectedForEdit.getMerk();
            kondisi_data        = selectedForEdit.getKondisi();
            tanggal_masuk_data  = selectedForEdit.getTanggal_masuk();
            harga_barang_data   = selectedForEdit.getHarga_barang();
            keterangan_data     = selectedForEdit.getKeterangan();
            
            InventarisData.id_barang_edit       = id_barang_data;
            InventarisData.kode_barang_edit     = kode_barang_data;
            InventarisData.nama_barang_edit     = nama_barang_data;
            InventarisData.merk_edit            = merk_data;
            InventarisData.kondisi_edit         = kondisi_data;
            InventarisData.tanggal_masuk_edit   = tanggal_masuk_data;
            InventarisData.harga_barang_edit    = harga_barang_data;
            InventarisData.keterangan_edit      = keterangan_data;
            
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/FormInventarisEdit.fxml"));
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
                        InventarisData.id_barang_edit       = null;
                        InventarisData.kode_barang_edit     = null;
                        InventarisData.nama_barang_edit     = null;
                        InventarisData.merk_edit            = null;
                        InventarisData.kondisi_edit         = null;
                        InventarisData.tanggal_masuk_edit   = null;
                        InventarisData.harga_barang_edit    = null;
                        InventarisData.keterangan_edit      = null;
                    });
                }
            });
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void hapusDataInventaris(ActionEvent event) {
        ModelTableInventaris selectedForDelete = tableInventaris.getSelectionModel().getSelectedItem();
        if (selectedForDelete == null) {
            System.out.println("Tidak ada data yang dipilih");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setContentText("Are you sure want to User " + selectedForDelete.getNama_barang()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            try {
                java.sql.Connection conn = (Connection)Config.configDB();
                int st = conn.createStatement().executeUpdate("DELETE FROM barang WHERE id_barang = '"+selectedForDelete.getId_barang()+"'");
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
    void insertDataInventaris(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/FormInventarisInsert.fxml"));
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
    void refreshDataInventaris(ActionEvent event) {
        loadData();
    }
    
}
