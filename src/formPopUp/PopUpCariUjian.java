/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package formPopUp;

import control.KoneksiDB;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hery Septyadi
 */
public class PopUpCariUjian extends JDialog implements MouseListener{

    JTable tblUjian = new JTable();
    JScrollPane scrollTableUjian = new JScrollPane();
    DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{},
            new String[]{"kode Ujian", "Kode Pelajaran", "Nama Pelajaran","Tanggal","Ruangan","Jenis Ujian","Tahun Ajaran","Semester"});
    String vKdUjian, vNmMatpel;
    public PopUpCariUjian(Frame parent, boolean modal){ // INI KONSTRUKTOR
        super(parent,modal);
        setTitle("Data Ujian");
        setSize(600, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        //Setting table mahasiwa
        tblUjian.setModel(tblModel);
        scrollTableUjian.setViewportView(tblUjian);
        scrollTableUjian.setAutoscrolls(true);
        //setbounds
        scrollTableUjian.setBounds(15, 15, 555, 200);
        getContentPane().setLayout(null);
        getContentPane().add(scrollTableUjian);
        //action listener
        tblUjian.addMouseListener(this);
        isiTable();
    }

    public String getvKdUjian() {
        return vKdUjian;
    }

    public void setvKdUjian(String vKdUjian) {
        this.vKdUjian = vKdUjian;
    }

    public String getvNmMatpel() {
        return vNmMatpel;
    }

    public void setvNmMatpel(String vNmMatpel) {
        this.vNmMatpel = vNmMatpel;
    }

 
        public void bersihTabel(){
        int i = tblUjian.getRowCount()-1;
        for(;i>=0;i--){
            tblModel.removeRow(i);
        }
    }
    public void isiTable() {
        bersihTabel();
        ResultSet rs = null;
        Connection con = null;
        Statement st = null;
        Object data[]= new Object[8];
        try{
            con = new KoneksiDB().openConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM ujian");
            while(rs.next()){
                data[0] = rs.getString("kd_ujian");
                data[1] = rs.getString("kd_pel");
                data[2] = rs.getString("nm_pel");
                data[3] = rs.getString("tgl_Ujian");
                data[4] = rs.getString("ruang");
                data[5] = rs.getString("jenis_ujian");
                data[6] = rs.getString("thn_ujian");
                data[7] = rs.getString("smt_ujian");
                tblModel.addRow(data);
            }
            st.close(); con.close();
        }catch (Exception e){}
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==tblUjian){
            if(e.getClickCount()==1){
                int row = tblUjian.getSelectedRow();
                setvKdUjian(tblModel.getValueAt(row, 0).toString());
                setvNmMatpel(tblModel.getValueAt(row, 2).toString());
                dispose();
            }
        }
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

}
