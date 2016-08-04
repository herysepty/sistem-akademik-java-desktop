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
 * @author Administrator
 */
public class PopUpCariKasus extends JDialog implements MouseListener{

    JTable tblSiswa = new JTable();
    JScrollPane scrollTableSiswa = new JScrollPane();
    DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{},
            new String[]{"Kode", "Nama Point","Singkatan","Bobot Point"});
    String vKd_kasus;
    public PopUpCariKasus(Frame parent, boolean modal){ // INI KONSTRUKTOR
        super(parent,modal);
        setTitle("Data Siswa");
        setSize(440, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        //Setting table mahasiwa
        tblSiswa.setModel(tblModel);
        scrollTableSiswa.setViewportView(tblSiswa);
        scrollTableSiswa.setAutoscrolls(true);
        //setbounds
        scrollTableSiswa.setBounds(15, 15, 400, 200);
        getContentPane().setLayout(null);
        getContentPane().add(scrollTableSiswa);
        //action listener
        tblSiswa.addMouseListener(this);
        isiTable();
    }

        public void bersihTabel(){
        int i = tblSiswa.getRowCount()-1;
        for(;i>=0;i--){
            tblModel.removeRow(i);
        }
    }
    public void isiTable() {
        bersihTabel();
        ResultSet rs = null;
        Connection con = null;
        Statement st = null;
        Object data[]= new Object[4];
        try{
            con = new KoneksiDB().openConnection();
            st = con.createStatement();
            rs = st.executeQuery("Select * FROM point");
            while(rs.next()){
             data[0]= rs.getString("kd_point");
             data[1]= rs.getString("nm_point");
             data[2]= rs.getString("sing_point");
             data[3]= rs.getString("bsr_point");
             tblModel.addRow(data);
            }
            st.close(); con.close();
        }catch (Exception e){}
    }

    public String getvKd_kasus() {
        return vKd_kasus;
    }

    public void setvKd_kasus(String vKd_kasus) {
        this.vKd_kasus = vKd_kasus;
    }






    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==tblSiswa){
            if(e.getClickCount()==1){
                int row = tblSiswa.getSelectedRow();
                setvKd_kasus(tblModel.getValueAt(row, 0).toString());
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
