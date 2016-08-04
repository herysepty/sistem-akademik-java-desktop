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
public class PopUpCariPelajaran extends JDialog implements MouseListener{

    JTable tblPelajaran = new JTable();
    JScrollPane scrollTablePelajaran = new JScrollPane();
    DefaultTableModel tblModel = new DefaultTableModel(new Object[][]{},
            new String[]{"nis", "nama", "kelas"});
    String kd_pel, namaPel;
    public PopUpCariPelajaran(Frame parent, boolean modal){ // INI KONSTRUKTOR
        super(parent,modal);
        setTitle("Data Pelajaran");
        setSize(440, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(this);
        //Setting table mahasiwa
        tblPelajaran.setModel(tblModel);
        scrollTablePelajaran.setViewportView(tblPelajaran);
        scrollTablePelajaran.setAutoscrolls(true);
        //setbounds
        scrollTablePelajaran.setBounds(15, 15, 400, 200);
        getContentPane().setLayout(null);
        getContentPane().add(scrollTablePelajaran);
        //action listener
        tblPelajaran.addMouseListener(this);
        isiTable();
    }

        public void bersihTabel(){
        int i = tblPelajaran.getRowCount()-1;
        for(;i>=0;i--){
            tblModel.removeRow(i);
        }
    }
    public void isiTable() {
        bersihTabel();
        ResultSet rs = null;
        Connection con = null;
        Statement st = null;
        Object data[]= new Object[3];
        try{
            con = new KoneksiDB().openConnection();
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM pelajaran");
            while(rs.next()){
                data[0] = rs.getString("kd_pel");
                data[1] = rs.getString("nm_pel");
                data[2] = rs.getString("sing_pel");
                tblModel.addRow(data);
            }
            st.close(); con.close();
        }catch (Exception e){}
    }



    public String getKd_pel() {
        return kd_pel;
    }

    public void setKd_pel(String kd_pel) {
        this.kd_pel = kd_pel;
    }

   public String getNamaPel() {
        return namaPel;
    }

    public void setNamaPel(String namaPel) {
        this.namaPel = namaPel;
    }



    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==tblPelajaran){
            if(e.getClickCount()==1){
                int row = tblPelajaran.getSelectedRow();
                setKd_pel(tblModel.getValueAt(row, 0).toString());
                setNamaPel(tblModel.getValueAt(row, 1).toString());
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
