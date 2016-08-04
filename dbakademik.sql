-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.5.25a - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             8.1.0.4589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for dbakademik
CREATE DATABASE IF NOT EXISTS `dbakademik` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbakademik`;


-- Dumping structure for table dbakademik.absensi
CREATE TABLE IF NOT EXISTS `absensi` (
  `no_abs` varchar(5) NOT NULL,
  `tgl_absen` varchar(10) DEFAULT NULL,
  `sts_absen` varchar(5) DEFAULT NULL COMMENT 'Status Absensi',
  `kls_absen` varchar(8) DEFAULT NULL,
  `thn_ajar` varchar(9) DEFAULT NULL COMMENT 'Alasan Absensi',
  `semester` varchar(5) DEFAULT NULL,
  `nis` varchar(5) DEFAULT NULL,
  `nm_siswa` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`no_abs`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbakademik.absensi: ~3 rows (approximately)
/*!40000 ALTER TABLE `absensi` DISABLE KEYS */;
INSERT INTO `absensi` (`no_abs`, `tgl_absen`, `sts_absen`, `kls_absen`, `thn_ajar`, `semester`, `nis`, `nm_siswa`) VALUES
	('A0002', '2013-12-16', 'Ijin', 'X', '2012/2013', 'Genap', 'S0002', 'Septy'),
	('A0003', '2013-12-18', 'Ijin', 'X', '2012/2013', 'Gasal', 'S0001', 'Hery Septyadi'),
	('A0004', '2013-12-18', 'Ijin', 'XII', '2012/2013', 'Genap', 'S0002', 'Septy');
/*!40000 ALTER TABLE `absensi` ENABLE KEYS */;


-- Dumping structure for table dbakademik.jadwal
CREATE TABLE IF NOT EXISTS `jadwal` (
  `kd_jadwal` varchar(5) NOT NULL,
  `kelas` varchar(8) NOT NULL,
  `hari` varchar(50) NOT NULL,
  `jam` varchar(5) NOT NULL,
  `thn_ajar` varchar(9) NOT NULL,
  `semester` varchar(5) NOT NULL,
  `kd_pel` varchar(5) NOT NULL,
  PRIMARY KEY (`kd_jadwal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='jadwal pelajaran';

-- Dumping data for table dbakademik.jadwal: ~2 rows (approximately)
/*!40000 ALTER TABLE `jadwal` DISABLE KEYS */;
INSERT INTO `jadwal` (`kd_jadwal`, `kelas`, `hari`, `jam`, `thn_ajar`, `semester`, `kd_pel`) VALUES
	('J0001', 'IX-1', 'Senin', '1', '2010/2011', 'Gasal', 'P0001'),
	('J0002', 'IX-2', 'Senin, Selasa, Rabu, Kamis, Jumat, Sabtu', '5', '2010/2011', 'Gasal', '');
/*!40000 ALTER TABLE `jadwal` ENABLE KEYS */;


-- Dumping structure for table dbakademik.kasus
CREATE TABLE IF NOT EXISTS `kasus` (
  `nis` varchar(5) NOT NULL,
  `kd_kasus` varchar(5) NOT NULL,
  `tgl_kasus` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`kd_kasus`,`nis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbakademik.kasus: ~1 rows (approximately)
/*!40000 ALTER TABLE `kasus` DISABLE KEYS */;
INSERT INTO `kasus` (`nis`, `kd_kasus`, `tgl_kasus`) VALUES
	('S0001', 'P0001', '2013-12-04');
/*!40000 ALTER TABLE `kasus` ENABLE KEYS */;


-- Dumping structure for table dbakademik.mhs
CREATE TABLE IF NOT EXISTS `mhs` (
  `nim` varchar(10) DEFAULT NULL,
  `nama` varchar(20) DEFAULT NULL,
  `alamat` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbakademik.mhs: ~0 rows (approximately)
/*!40000 ALTER TABLE `mhs` DISABLE KEYS */;
/*!40000 ALTER TABLE `mhs` ENABLE KEYS */;


-- Dumping structure for table dbakademik.nilai
CREATE TABLE IF NOT EXISTS `nilai` (
  `thn_ajaran` varchar(9) NOT NULL,
  `semester` varchar(6) NOT NULL,
  `kelas` varchar(5) NOT NULL,
  `ujian_dari` varchar(3) NOT NULL,
  `kd_ujian` varchar(5) NOT NULL,
  `nm_pel` varchar(25) NOT NULL,
  `nis` varchar(5) NOT NULL,
  `nm_siswa` varchar(25) NOT NULL,
  `nilai` int(3) NOT NULL,
  PRIMARY KEY (`nis`,`kd_ujian`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbakademik.nilai: ~3 rows (approximately)
/*!40000 ALTER TABLE `nilai` DISABLE KEYS */;
INSERT INTO `nilai` (`thn_ajaran`, `semester`, `kelas`, `ujian_dari`, `kd_ujian`, `nm_pel`, `nis`, `nm_siswa`, `nilai`) VALUES
	('2010/2011', 'Genap', 'IX-2', 'UTS', 'UJ001', 'Ilmu Pengetahuan Alam', 'S0001', 'Hery ', 32),
	('2010/2011', 'Gasal', 'IX-1', 'UTS', 'UJ002', 'Bahasa Indonesia', 'S0001', 'Hery Septyadi', 87),
	('2010/2011', 'Gasal', 'IX-1', 'UTS', 'UJ003', 'Bahasa Indonesia', 'S0002', 'Septy', 56);
/*!40000 ALTER TABLE `nilai` ENABLE KEYS */;


-- Dumping structure for table dbakademik.pelajaran
CREATE TABLE IF NOT EXISTS `pelajaran` (
  `kd_pel` varchar(5) NOT NULL,
  `nm_pel` varchar(25) NOT NULL,
  `sing_pel` varchar(10) NOT NULL,
  PRIMARY KEY (`kd_pel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbakademik.pelajaran: ~4 rows (approximately)
/*!40000 ALTER TABLE `pelajaran` DISABLE KEYS */;
INSERT INTO `pelajaran` (`kd_pel`, `nm_pel`, `sing_pel`) VALUES
	('P0001', 'Agama Islam', 'Agama Isla'),
	('P0002', 'Bahasa Indonesia', 'B. Ind'),
	('P0003', 'Ilmu Pengetahuan Alam', 'IPA'),
	('P0004', 'Ilmu Pengetahuan Sosial', 'IPS');
/*!40000 ALTER TABLE `pelajaran` ENABLE KEYS */;


-- Dumping structure for table dbakademik.point
CREATE TABLE IF NOT EXISTS `point` (
  `kd_point` varchar(5) NOT NULL,
  `nm_point` varchar(25) NOT NULL,
  `sing_point` varchar(10) NOT NULL,
  `bsr_point` varchar(25) NOT NULL,
  PRIMARY KEY (`kd_point`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='point pelanggaran';

-- Dumping data for table dbakademik.point: ~2 rows (approximately)
/*!40000 ALTER TABLE `point` DISABLE KEYS */;
INSERT INTO `point` (`kd_point`, `nm_point`, `sing_point`, `bsr_point`) VALUES
	('P0001', 'Membuat Keributan', 'Gaduh', '15'),
	('P0002', 'Pulang Tanpa izin', 'PTI', '15');
/*!40000 ALTER TABLE `point` ENABLE KEYS */;


-- Dumping structure for table dbakademik.siswa
CREATE TABLE IF NOT EXISTS `siswa` (
  `nis` varchar(5) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `tempat_lahir` varchar(25) NOT NULL,
  `tgl_lahir` varchar(10) NOT NULL,
  `jenkel` varchar(10) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `kelas` varchar(5) NOT NULL,
  PRIMARY KEY (`nis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbakademik.siswa: ~1 rows (approximately)
/*!40000 ALTER TABLE `siswa` DISABLE KEYS */;
INSERT INTO `siswa` (`nis`, `nama`, `tempat_lahir`, `tgl_lahir`, `jenkel`, `alamat`, `kelas`) VALUES
	('S0002', 'Septy', 'Kuningan', '1994-09-06', 'Perempuan', 'Jakarta', 'XII');
/*!40000 ALTER TABLE `siswa` ENABLE KEYS */;


-- Dumping structure for table dbakademik.ujian
CREATE TABLE IF NOT EXISTS `ujian` (
  `kd_ujian` varchar(5) NOT NULL DEFAULT '',
  `kd_pel` varchar(5) NOT NULL,
  `nm_pel` varchar(25) NOT NULL,
  `kls_ujian` varchar(8) NOT NULL,
  `tgl_ujian` varchar(10) NOT NULL DEFAULT '',
  `durasi` varchar(3) NOT NULL,
  `jam_awal` varchar(8) NOT NULL,
  `jam_akhir` varchar(8) NOT NULL,
  `ruang` varchar(8) NOT NULL,
  `jenis_ujian` varchar(3) NOT NULL,
  `thn_ujian` varchar(9) NOT NULL,
  `smt_ujian` varchar(5) NOT NULL,
  PRIMARY KEY (`kd_ujian`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table dbakademik.ujian: ~4 rows (approximately)
/*!40000 ALTER TABLE `ujian` DISABLE KEYS */;
INSERT INTO `ujian` (`kd_ujian`, `kd_pel`, `nm_pel`, `kls_ujian`, `tgl_ujian`, `durasi`, `jam_awal`, `jam_akhir`, `ruang`, `jenis_ujian`, `thn_ujian`, `smt_ujian`) VALUES
	('UJ001', 'P0003', 'Ilmu Pengetahuan Alam', 'IX-1', '1992', '90', '09:30', '11:30', '2', 'UTS', '2011/2012', 'Genap'),
	('UJ002', 'P0002', 'Bahasa Indonesia', 'IX-1', '2013', '90', '07:00', '09:00', '5', 'UAS', '2012/2013', 'Gasal'),
	('UJ003', 'P0002', 'Bahasa Indonesia', 'IX-1', '1993', '120', '09:30', '11:30', '3', 'UAS', '2012/2013', 'Genap'),
	('UJ004', 'P0002', 'Bahasa Indonesia', 'X', '2013-12-14', '120', '07:00', '11:30', '4', 'UAS', '2012/2013', 'Genap');
/*!40000 ALTER TABLE `ujian` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
