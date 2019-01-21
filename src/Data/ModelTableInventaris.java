/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author DanZai
 */
public class ModelTableInventaris {
    
    String no, id_barang, kode_barang, nama_barang, merk, kondisi, tanggal_masuk, harga_barang, keterangan;

    public ModelTableInventaris(String no, String id_barang, String kode_barang, String nama_barang, String merk, String kondisi, String tanggal_masuk, String harga_barang, String keterangan) {
        this.no = no;
        this.id_barang = id_barang;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.merk = merk;
        this.kondisi = kondisi;
        this.tanggal_masuk = tanggal_masuk;
        this.harga_barang = harga_barang;
        this.keterangan = keterangan;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getTanggal_masuk() {
        return tanggal_masuk;
    }

    public void setTanggal_masuk(String tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }

    public String getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(String harga_barang) {
        this.harga_barang = harga_barang;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    
    
    
    
}
