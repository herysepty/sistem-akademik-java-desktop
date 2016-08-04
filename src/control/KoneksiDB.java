/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HERY
 */
public class KoneksiDB{
    public Connection openConnection(){
        Connection connect = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/dbakademik", "root","");
            System.out.println("Berhasi Koneksi");
            return connect;
        }catch (Exception e){
            System.out.println("Koneksi Gagal :"+e.getMessage());
            return connect = null;
        }
    }

    //methode Untuk menguji Apakah Koneksi Ke database Bisa di lakukan
//    public static void main(String args[]){
//        new KoneksiDB().openConnection();
//    }
       
}
