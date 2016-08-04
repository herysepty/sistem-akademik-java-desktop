/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * berfungnsi Untuk Memanipulasi Data
 */
package control;

import java.io.File;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author HERY
 */
public class ManipulasiData {
    KoneksiDB koneksi;
    Connection conn;
    Statement stt;
    
    public ManipulasiData(){
        koneksi = new KoneksiDB();
    }
    
    public boolean QUERY(String SQL){
        try{
            
            conn = koneksi.openConnection();
            stt = conn.createStatement();
            stt.executeUpdate(SQL);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public void CetakLaporanAbsensi(String namaFileLaporan) {
        String path = "";
        path = getClass().getResource("/report/"+namaFileLaporan+".jasper").getPath();
        System.out.println("path = " + path);
        try {
            conn = koneksi.openConnection();
            HashMap parameter = new HashMap();

            File reportFile = new File(path);
            System.err.println("path: " + path );
            JasperReport jr = (JasperReport) JRLoader.loadObject(reportFile.getPath());
            JasperPrint jp = JasperFillManager.fillReport(jr, parameter, conn);

            JasperViewer.viewReport(jp, false);
            JasperViewer.setDefaultLookAndFeelDecorated(true);
            
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, jdbcConnection);
// JasperViewer.viewReport(jasperPrint);
 
            //stt.close(); conn.close();
        } catch (Exception error) {
            System.out.println("ERROR PrintLaporan = " + error.getMessage());
        }
    }
    
        public String generateKdPel(){
        String kode="";
        int h,i;
        try{
            conn = koneksi.openConnection();
            stt = conn.createStatement();
            String SQL = "SELECT kd_pel FROM pelajaran ORDER BY kd_pel DESC";
            ResultSet resultSet = stt.executeQuery(SQL);
            if(resultSet.next()){
                kode=resultSet.getString("kd_pel");
                kode = kode.substring(1);
                i = Integer.parseInt(kode)+1;
                h = String.valueOf(i).length();
                switch(h){
                    case 1:
                        kode = "P000"+i;
                                break;
                        case 2:
                        kode = "P00"+i;
                                break;
                            case 3:
                        kode = "P0"+i;
                                break;
                                case 4:
                        kode = "P"+i;
                                break;
                                   
                }
                
            }else{
                kode = "P0001";
            }
                
            
        }catch (Exception e){
            
        }
        return kode;
    }
        
        
        //kode Siswa
        public String generateNIS(){
        String kode="";
        int h,i;
        try{
            conn = koneksi.openConnection();
            stt = conn.createStatement();
            String SQL = "SELECT nis FROM siswa ORDER BY nis DESC";
            ResultSet resultSet = stt.executeQuery(SQL);
            if(resultSet.next()){
                kode=resultSet.getString("nis");
                kode = kode.substring(1);
                i = Integer.parseInt(kode)+1;
                h = String.valueOf(i).length();
                switch(h){
                    case 1:
                        kode = "S000"+i;
                                break;
                        case 2:
                        kode = "S00"+i;
                                break;
                            case 3:
                        kode = "S0"+i;
                                break;
                                case 4:
                        kode = "S"+i;
                                break;
                                   
                }
                
            }else{
                kode = "S0001";
            }
                
            
        }catch (Exception e){
            
        }
        return kode;
    }
       
        public String generatePelanggaran(){
        String kode="";
        int h,i;
        try{
            conn = koneksi.openConnection();
            stt = conn.createStatement();
            String SQL = "SELECT kd_point FROM point ORDER BY kd_point DESC";
            ResultSet resultSet = stt.executeQuery(SQL);
            if(resultSet.next()){
                kode=resultSet.getString("kd_point");
                kode = kode.substring(1);
                i = Integer.parseInt(kode)+1;
                h = String.valueOf(i).length();
                switch(h){
                    case 1:
                        kode = "P000"+i;
                                break;
                        case 2:
                        kode = "P00"+i;
                                break;
                            case 3:
                        kode = "P0"+i;
                                break;
                            case 4:
                                kode = "P"+i;
                }
                
            }else{
                kode = "P0001";
            }
                
            
        }catch (Exception e){
            
        }
        return kode;
    }
        
    public String generateKdAbsen(){
        String kode="";
        int h,i;
        try{
            conn = koneksi.openConnection();
            stt = conn.createStatement();
            String SQL = "SELECT no_abs FROM absensi ORDER BY no_abs DESC";
            ResultSet resultSet = stt.executeQuery(SQL);
            if(resultSet.next()){
                kode=resultSet.getString("no_abs");
                kode = kode.substring(1);
                i = Integer.parseInt(kode)+1;
                h = String.valueOf(i).length();
                switch(h){
                    case 1:
                        kode = "A000"+i;
                                break;
                        case 2:
                        kode = "A00"+i;
                                break;
                            case 3:
                        kode = "A0"+i;
                                break;
                            case 4:
                                kode = "A"+i;
                }
                
            }else{
                kode = "A0001";
            }
                
            
        }catch (Exception e){
            
        }
        return kode;
    }
public String generateKdJadwal(){
        String kode="";
        int h,i;
        try{
            conn = koneksi.openConnection();
            stt = conn.createStatement();
            String SQL = "SELECT kd_jadwal FROM jadwal ORDER BY kd_jadwal DESC";
            ResultSet resultSet = stt.executeQuery(SQL);
            if(resultSet.next()){
                kode=resultSet.getString("kd_jadwal");
                kode = kode.substring(1);
                i = Integer.parseInt(kode)+1;
                h = String.valueOf(i).length();
                switch(h){
                    case 1:
                        kode = "J000"+i;
                                break;
                        case 2:
                        kode = "J00"+i;
                                break;
                            case 3:
                        kode = "J0"+i;
                                break;
                            case 4:
                                kode = "J"+i;
                }
                
            }else{
                kode = "J0001";
            }
                
            
        }catch (Exception e){
            
        }
        return kode;
    }    
public String generateKdUjian(){
        String kode="";
        int h,i;
        try{
            conn = koneksi.openConnection();
            stt = conn.createStatement();
            String SQL = "SELECT kd_ujian FROM ujian ORDER BY kd_ujian DESC";
            ResultSet resultSet = stt.executeQuery(SQL);
            if(resultSet.next()){
                kode=resultSet.getString("kd_ujian");
                kode = kode.substring(2);
                i = Integer.parseInt(kode)+1;
                h = String.valueOf(i).length();
                switch(h){
                    case 1:
                        kode = "UJ00"+i;
                                break;
                        case 2:
                        kode = "UJ0"+i;
                                break;
                            case 4:
                                kode = "UJ"+i;
                }
                
            }else{
                kode = "UJ001";
            }
                
            
        }catch (Exception e){
            
        }
        return kode;
    } 



}
