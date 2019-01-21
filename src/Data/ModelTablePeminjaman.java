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
public class ModelTablePeminjaman {
    
    String no, id_peminjaman, id_user, nama, id_barang, nama_peminjam, nim_peminjam, no_telp, alamat, nama_barang, jumlah, tanggal_pinjam, tanggal_kembali, status_pengembalian, keterangan_pinjam;

    public ModelTablePeminjaman(String no, String id_peminjaman, String id_user, String nama, String id_barang, String nama_peminjam, String nim_peminjam, String no_telp, String alamat, String nama_barang, String jumlah, String tanggal_pinjam, String tanggal_kembali, String status_pengembalian, String keterangan_pinjam) {
        this.no = no;
        this.id_peminjaman = id_peminjaman;
        this.id_user = id_user;
        this.nama = nama;
        this.id_barang = id_barang;
        this.nama_peminjam = nama_peminjam;
        this.nim_peminjam = nim_peminjam;
        this.no_telp = no_telp;
        this.alamat = alamat;
        this.nama_barang = nama_barang;
        this.jumlah = jumlah;
        this.tanggal_pinjam = tanggal_pinjam;
        this.tanggal_kembali = tanggal_kembali;
        this.status_pengembalian = status_pengembalian;
        this.keterangan_pinjam = keterangan_pinjam;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getId_peminjaman() {
        return id_peminjaman;
    }

    public void setId_peminjaman(String id_peminjaman) {
        this.id_peminjaman = id_peminjaman;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId_barang() {
        return id_barang;
    }

    public void setId_barang(String id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_peminjam() {
        return nama_peminjam;
    }

    public void setNama_peminjam(String nama_peminjam) {
        this.nama_peminjam = nama_peminjam;
    }

    public String getNim_peminjam() {
        return nim_peminjam;
    }

    public void setNim_peminjam(String nim_peminjam) {
        this.nim_peminjam = nim_peminjam;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTanggal_pinjam() {
        return tanggal_pinjam;
    }

    public void setTanggal_pinjam(String tanggal_pinjam) {
        this.tanggal_pinjam = tanggal_pinjam;
    }

    public String getTanggal_kembali() {
        return tanggal_kembali;
    }

    public void setTanggal_kembali(String tanggal_kembali) {
        this.tanggal_kembali = tanggal_kembali;
    }

    public String getStatus_pengembalian() {
        return status_pengembalian;
    }

    public void setStatus_pengembalian(String status_pengembalian) {
        this.status_pengembalian = status_pengembalian;
    }

    public String getKeterangan_pinjam() {
        return keterangan_pinjam;
    }

    public void setKeterangan_pinjam(String keterangan_pinjam) {
        this.keterangan_pinjam = keterangan_pinjam;
    }

    
}
