-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 21 Jan 2019 pada 01.06
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `admin`
--

CREATE TABLE `admin` (
  `id_user` int(10) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `nim` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `admin`
--

INSERT INTO `admin` (`id_user`, `username`, `password`, `nama`, `nim`) VALUES
(1, 'admin', 'admin', 'Admin Default', 4444),
(2, 'danzai', '123', 'Danis Zaidan', 4079),
(3, 'test', 'test', 'Test User', 777),
(14, 'fadliwibu', 'zeeb', 'Fadli Hidayatullah', 4001),
(15, 'derah', 'derah123', 'Dedi Rahmansyah', 670617401),
(16, 'fajar', 'fajar123', 'Fajar Satya Sanjaya', 670617402),
(17, 'verin', 'verin123', 'Maria Averine', 670617403),
(18, 'hisyam', 'hisyam123', 'Muhammad Hisyam Fadhil', 670617404),
(19, 'haris', 'haris123', 'Haris Wedira', 670617405),
(20, 'danis', 'danzai123', 'Danis Zaidan', 670617406),
(21, 'raka', 'raka123', 'Raka Daffa Arival', 670617407),
(22, 'nindy', 'nindy123', 'Nindy Haris Putri', 670617408),
(23, 'ravi', 'ravi123', 'Ravi Mahvunda', 670616401),
(24, 'rhoni', 'rhoni123', 'Rhoni Septian', 670616402);

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` int(10) NOT NULL,
  `kode_barang` varchar(100) NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `merk` varchar(100) NOT NULL,
  `kondisi` varchar(100) NOT NULL,
  `tanggal_masuk` varchar(10) NOT NULL,
  `harga_barang` int(50) NOT NULL,
  `keterangan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `kode_barang`, `nama_barang`, `merk`, `kondisi`, `tanggal_masuk`, `harga_barang`, `keterangan`) VALUES
(19, 'P2', 'Proyektor', 'Lenovo', 'Normal', '2017-02-15', 2000000, 'Berwarna hitam'),
(20, 'C1', 'CPU', 'Lenovo', 'Normal', '2016-10-25', 1600000, 'baik'),
(21, 'P1', 'Proyektor', 'Lenovo', 'Normal', '2017-02-15', 2000000, 'Berwarna hitam'),
(23, 'M1', 'Monitor ', 'Lenovo', 'Normal', '2018-10-31', 1500000, 'baik'),
(24, 'M2', 'Monitor', 'Lenovo', 'Normal', '2018-10-25', 1500000, 'baik '),
(25, 'MS1', 'Mouse', 'Lenovo', 'Normal', '2017-10-22', 100000, 'Lampu tidak menyala'),
(26, 'MS2', 'Mouse', 'Lenovo', 'Normal', '2017-10-22', 100000, 'Baik'),
(27, 'H1', 'Headset', 'JBL', 'Normal', '2017-12-24', 150000, 'Baik'),
(28, 'H2', 'Mouse', 'JBL', 'Normal', '2017-12-24', 1500000, 'Volume tidak maksimal'),
(29, 'SP1', 'Speaker', 'JBL', 'Normal', '2018-01-15', 200000, 'Baik'),
(30, 'MJ1', 'Meja', 'Olympic', 'Normal', '2016-01-10', 200000, 'Bersih'),
(31, 'MJ2', 'Meja', 'Olympic', 'Normal', '2016-01-26', 200000, 'Bersih'),
(32, '12', 'awdawd', 'awda', 'Rusak', '2018-12-05', 123123, '121212j1nk12jn1kj2n1k jk1n2k12  kjn');

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` int(10) NOT NULL,
  `id_user` int(10) NOT NULL,
  `id_barang` int(10) NOT NULL,
  `nama_peminjam` varchar(255) NOT NULL,
  `nim_peminjam` int(10) NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `jumlah` int(100) NOT NULL,
  `tanggal_pinjam` varchar(10) NOT NULL,
  `tanggal_kembali` varchar(10) NOT NULL,
  `status_pengembalian` int(10) NOT NULL,
  `keterangan_pinjam` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `peminjaman`
--

INSERT INTO `peminjaman` (`id_peminjaman`, `id_user`, `id_barang`, `nama_peminjam`, `nim_peminjam`, `no_telp`, `alamat`, `jumlah`, `tanggal_pinjam`, `tanggal_kembali`, `status_pengembalian`, `keterangan_pinjam`) VALUES
(4, 2, 25, 'Nabila Ridhanti', 8768, '0867354', 'Cikoneng', 1, '2018-12-05', '2018-12-09', 0, 'Untuk tubes'),
(5, 2, 29, 'Sevia', 674030, '09238', 'Baleendah', 1, '2018-12-05', '2018-12-10', 0, 'Untuk presentasi'),
(6, 2, 23, 'Danis', 670237, '08293', 'Bojongsoang', 1, '2018-12-05', '2018-12-05', 0, 'Untuk apa');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_user`);

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`),
  ADD KEY `id_user` (`id_user`,`id_barang`),
  ADD KEY `id_barang` (`id_barang`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `admin`
--
ALTER TABLE `admin`
  MODIFY `id_user` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT untuk tabel `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id_peminjaman` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `peminjaman_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `admin` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `peminjaman_ibfk_2` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
