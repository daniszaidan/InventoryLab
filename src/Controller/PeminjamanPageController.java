/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Connection.Config;
import Data.InventarisData;
import Data.ModelTableInventaris;
import Data.ModelTablePeminjaman;
import Data.PeminjamanData;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
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
public class PeminjamanPageController implements Initializable{
    
    public static String id_peminjaman_data;
    public static String id_user_data;
    public static String nama_data;
    public static String id_barang_data;
    public static String nama_peminjam_data;
    public static String nim_peminjam_data;
    public static String no_telp_data;
    public static String alamat_data;
    public static String nama_barang_data;
    public static String jumlah_data;
    public static String tanggal_pinjam_data;
    public static String tanggal_kembali_data;
    public static String status_pengembalian_data;
    public static String keterangan_pinjam_data;
    
    @FXML
    private AnchorPane homePage;

    @FXML
    private TableView<ModelTablePeminjaman> tablePeminjaman;

    @FXML
    private TableColumn<ModelTablePeminjaman, Integer> col_no;

    @FXML
    private TableColumn<ModelTablePeminjaman, Integer> col_id_peminjaman;
    
    @FXML
    private TableColumn<ModelTablePeminjaman, Integer> col_id_user;
    
    @FXML
    private TableColumn<ModelTablePeminjaman, String> col_aslab;

    @FXML
    private TableColumn<ModelTablePeminjaman, Integer> col_id_barang;

    @FXML
    private TableColumn<ModelTablePeminjaman, String> col_nama;
    
    @FXML
    private TableColumn<ModelTablePeminjaman, Integer> col_nim;

    @FXML
    private TableColumn<ModelTablePeminjaman, String> col_no_telp;

    @FXML
    private TableColumn<ModelTablePeminjaman, String> col_alamat;

    @FXML
    private TableColumn<ModelTablePeminjaman, String> col_barang;

    @FXML
    private TableColumn<ModelTablePeminjaman, Integer> col_jumlah;

    @FXML
    private TableColumn<ModelTablePeminjaman, String> col_tanggal_pinjam;
    
    @FXML
    private TableColumn<ModelTablePeminjaman, String> col_tanggal_kembali;
    
    @FXML
    private TableColumn<ModelTablePeminjaman, String> col_status_pengembalian;
    
    @FXML
    private TableColumn<ModelTablePeminjaman, String> col_keterangan_pinjam;

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
    
    ObservableList<ModelTablePeminjaman> oblist = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column();
        loadData();
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
    }
    
    private void column(){
        col_no.setCellValueFactory(new PropertyValueFactory<>("no"));
        col_id_peminjaman.setCellValueFactory(new PropertyValueFactory<>("id_peminjaman"));
        col_id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        col_aslab.setCellValueFactory(new PropertyValueFactory<>("nama"));
        col_id_barang.setCellValueFactory(new PropertyValueFactory<>("id_barang"));
        col_nama.setCellValueFactory(new PropertyValueFactory<>("nama_peminjam"));
        col_nim.setCellValueFactory(new PropertyValueFactory<>("nim_peminjam"));
        col_no_telp.setCellValueFactory(new PropertyValueFactory<>("no_telp"));
        col_alamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        col_barang.setCellValueFactory(new PropertyValueFactory<>("nama_barang"));
        col_jumlah.setCellValueFactory(new PropertyValueFactory<>("jumlah"));
        col_tanggal_pinjam.setCellValueFactory(new PropertyValueFactory<>("tanggal_pinjam"));
        col_tanggal_kembali.setCellValueFactory(new PropertyValueFactory<>("tanggal_kembali"));
        col_status_pengembalian.setCellValueFactory(new PropertyValueFactory<>("status_pengembalian"));
        col_keterangan_pinjam.setCellValueFactory(new PropertyValueFactory<>("keterangan_pinjam"));
    }
    
    public void loadData(){
        oblist.clear();
        
        try {
            java.sql.Connection conn = (Connection)Config.configDB();
            ResultSet st = conn.createStatement().executeQuery("SELECT id_peminjaman, peminjaman.id_user, admin.nama, peminjaman.id_barang, peminjaman.nama_peminjam, peminjaman.nim_peminjam, no_telp, alamat, barang.nama_barang, jumlah, tanggal_pinjam, tanggal_kembali, status_pengembalian, keterangan_pinjam FROM peminjaman, barang, admin WHERE peminjaman.id_user = admin.id_user AND peminjaman.id_barang = barang.id_barang");
            
            int no = 1; 
//            int year = Calendar.getInstance().get(Calendar.YEAR);
//            System.out.println(year);
            Calendar now = Calendar.getInstance();
            String status = null, tanggal = null;
            
            while (st.next()) {
                if(Integer.parseInt(st.getString("status_pengembalian")) == 0){
                    status = "Masih Dipinjam";
                }else if(Integer.parseInt(st.getString("status_pengembalian")) == 1){
                    status = "Sudah Kembali";
                }
                
//                String[] tanggal_kembali_split = st.getString("tanggal_kembali").split("-");
//                System.out.println(tanggal_kembali_split[0]);
//                
//                int bulan = 0;
//                if(now.get(Calendar.MONTH) < Integer.parseInt(tanggal_kembali_split[1])){
////                    tanggal = "sans ae" + now.get(Calendar.MONTH) + tanggal_kembali_split[1];
//                    if(now.get(Calendar.DAY_OF_MONTH) > Integer.parseInt(tanggal_kembali_split[2])){
////                        tanggal = "Kembaliin woi !" + now.get(Calendar.DAY_OF_MONTH) + tanggal_kembali_split[2];
//                        int keterlambatan = now.get(Calendar.DAY_OF_MONTH) - Integer.parseInt(tanggal_kembali_split[2]);
////                        System.out.println("Terlambat : " + keterlambatan);
//                        status = "aTerlambat " + keterlambatan + "Hari";
//                    }
//                } if(now.get(Calendar.MONTH) > Integer.parseInt(tanggal_kembali_split[1])){ //terlambat bulan
////                    tanggal = "Kembaliin woi !" + now.get(Calendar.MONTH) + tanggal_kembali_split[1];
//                    if(now.get(Calendar.DAY_OF_MONTH) < Integer.parseInt(tanggal_kembali_split[2])){
////                        tanggal = "Kembaliin woi !" + now.get(Calendar.DAY_OF_MONTH) + tanggal_kembali_split[2];
//                        int keterlambatan = Integer.parseInt(tanggal_kembali_split[2]) - now.get(Calendar.DAY_OF_MONTH);
////                        System.out.println("Terlambat : " + keterlambatan);
//                        status = "bTerlambat " + keterlambatan + "Hari";
//                    }
//                } if(now.get(Calendar.MONTH) == Integer.parseInt(tanggal_kembali_split[1])){
////                    tanggal = "sama" + now.get(Calendar.MONTH) + tanggal_kembali_split[1];
//                    if(now.get(Calendar.DAY_OF_MONTH) < Integer.parseInt(tanggal_kembali_split[2])){
////                        tanggal = "Kembaliin woi !" + now.get(Calendar.DAY_OF_MONTH) + tanggal_kembali_split[2];
//                        int keterlambatan = Integer.parseInt(tanggal_kembali_split[2]) - now.get(Calendar.DAY_OF_MONTH);
////                        System.out.println("Terlambat : " + keterlambatan);
////                        tanggal = "sama" + keterlambatan;
//                        status = "cTerlambat " + keterlambatan + "Hari";
//                    }
//                }
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = new Date();
                System.out.println(dateFormat.format(date));
                
                oblist.add(new ModelTablePeminjaman(
                        Integer.toString(no++), 
                        st.getString("id_peminjaman"),
                        st.getString("id_user"),
                        st.getString("nama"),
                        st.getString("id_barang"),
                        st.getString("nama_peminjam"),
                        st.getString("nim_peminjam"),
                        st.getString("no_telp"),
                        st.getString("alamat"),
                        st.getString("nama_barang"),
                        st.getString("jumlah"),
                        st.getString("tanggal_pinjam"),
//                        tanggal,
                        st.getString("tanggal_kembali"),
                        status,
                        st.getString("keterangan_pinjam")
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Ada masalah, silahkan coba kembali");
            ex.printStackTrace();
        }
        tablePeminjaman.setItems(oblist);
    }

    @FXML
    void editDataPeminjaman(ActionEvent event) {
        try{
            ModelTablePeminjaman selectedForEdit = tablePeminjaman.getSelectionModel().getSelectedItem();
            
            id_peminjaman_data          = selectedForEdit.getId_peminjaman();
            id_user_data                = selectedForEdit.getId_user();
            nama_data                   = selectedForEdit.getNama();
            id_barang_data              = selectedForEdit.getId_barang();
            nama_peminjam_data          = selectedForEdit.getNama_peminjam();
            nim_peminjam_data           = selectedForEdit.getNim_peminjam();
            no_telp_data                = selectedForEdit.getNo_telp();
            alamat_data                 = selectedForEdit.getAlamat();
            nama_barang_data            = selectedForEdit.getNama_barang();
            jumlah_data                 = selectedForEdit.getJumlah();
            tanggal_pinjam_data         = selectedForEdit.getTanggal_pinjam();
            tanggal_kembali_data        = selectedForEdit.getTanggal_kembali();
            status_pengembalian_data    = selectedForEdit.getStatus_pengembalian();
            keterangan_pinjam_data      = selectedForEdit.getKeterangan_pinjam();
           
            PeminjamanData.id_peminjaman_edit       = id_peminjaman_data;
            PeminjamanData.id_user_edit             = id_user_data;
            PeminjamanData.nama_edit                = nama_data;
            PeminjamanData.id_barang_edit           = id_barang_data;
            PeminjamanData.nama_peminjam_edit       = nama_peminjam_data;
            PeminjamanData.nim_peminjam_edit        = nim_peminjam_data;
            PeminjamanData.no_telp_edit             = no_telp_data;
            PeminjamanData.alamat_edit              = alamat_data;
            PeminjamanData.nama_barang_edit         = nama_barang_data;
            PeminjamanData.jumlah_edit              = jumlah_data;
            PeminjamanData.tanggal_pinjam_edit      = tanggal_pinjam_data;
            PeminjamanData.tanggal_kembali_edit     = tanggal_kembali_data;
            PeminjamanData.status_pengembalian_edit = status_pengembalian_data;
            PeminjamanData.keterangan_pinjam_edit   = keterangan_pinjam_data;
     
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/FormPeminjamanEdit.fxml"));
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
                        PeminjamanData.id_peminjaman_edit       = null;
                        PeminjamanData.id_user_edit             = null;
                        PeminjamanData.nama_edit                = null;
                        PeminjamanData.id_barang_edit           = null;
                        PeminjamanData.nama_peminjam_edit       = null;
                        PeminjamanData.nim_peminjam_edit        = null;
                        PeminjamanData.no_telp_edit             = null;
                        PeminjamanData.alamat_edit              = null;
                        PeminjamanData.nama_barang_edit         = null;
                        PeminjamanData.jumlah_edit              = null;
                        PeminjamanData.tanggal_pinjam_edit      = null;
                        PeminjamanData.tanggal_kembali_edit     = null;
                        PeminjamanData.status_pengembalian_edit = null;
                        PeminjamanData.keterangan_pinjam_edit   = null;
                    });
                }
            });
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void hapusDataPeminjaman(ActionEvent event) {
        ModelTablePeminjaman selectedForDelete = tablePeminjaman.getSelectionModel().getSelectedItem();
        if (selectedForDelete == null) {
            System.out.println("Tidak ada data yang dipilih");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Deleting book");
        alert.setContentText("Are you sure want to User " + selectedForDelete.getNama_peminjam()+ " ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            try {
                java.sql.Connection conn = (Connection)Config.configDB();
                int st = conn.createStatement().executeUpdate("DELETE FROM peminjaman WHERE id_peminjaman = '"+selectedForDelete.getId_peminjaman()+"'");
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
    void insertDataPeminjaman(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/FXML/FormPeminjamanInsert.fxml"));
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
    void refreshDataPeminjaman(ActionEvent event) {
        loadData();
    }
    
}
