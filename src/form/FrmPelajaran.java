/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package form;

import control.KoneksiDB;
import control.ManipulasiData;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import javax.swing.JLabel;
//import javax.swing.JTextField;

/**
 * www.herys.tk
 * @author Hery Septyadi | 1211501224
 */
public class FrmPelajaran extends JInternalFrame implements ActionListener, MouseListener {
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JLabel labelKode = new JLabel("Kode :");
    JLabel labelNama = new JLabel("Pelajaran:");
    JLabel labelKelas = new JLabel("Singkatan:");
    
    JTextField textFieldKode = new JTextField();
    JTextField textFieldPelajaran = new JTextField();
    JTextField textFieldSing = new JTextField();
    
    JButton buttonTampil = new JButton("Simpan");
    JButton buttonTampilUbah = new JButton("Ubah");
    JButton buttonTampilHapus =new JButton("Hapus");
    JButton buttonKeluar  = new JButton("Keluar");
    JButton buttonBatal = new JButton("Batal");

    JTable tbl_Siswa = new JTable();
    JScrollPane jScrollPaneTabelSiswa = new JScrollPane();
    DefaultTableModel tableModel = new  DefaultTableModel(new Object [][]{}, new String[]{"Kode", "Pelajran", "Singkatan"});
    
    ManipulasiData manData;
    public FrmPelajaran(){
        this.setTitle("Form Pelajaran");
        this.setSize(600, 550);
      //  this.setLocationRelativeTo(null);
        setLocation(d.width/2-getWidth()/2, d.height/2-getHeight()/2);
        setClosable(true);
        setIconifiable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        manData = new ManipulasiData();
        setAwalform();
        
        //setting table
        tbl_Siswa.setModel(tableModel);
        jScrollPaneTabelSiswa.setViewportView(tbl_Siswa);
        jScrollPaneTabelSiswa.setAutoscrolls(true);
        tbl_Siswa.addMouseListener(this);
        
        //isi table
        isiTable();
        
        labelKode.setBounds(15, 30, 150, 25);
        labelNama.setBounds(15, 70, 150, 25);
        labelKelas.setBounds(15, 110, 150, 25);
        
        textFieldKode.setBounds(170, 30, 150, 25);
        textFieldPelajaran.setBounds(170, 70, 150, 25);
        textFieldSing.setBounds(170, 110, 150, 25);

        jScrollPaneTabelSiswa.setBounds(15, 30, 490, 150);

        buttonTampil.setBounds(20, 180, 80, 25);
        buttonTampil.addActionListener(this);
        buttonTampilUbah.setBounds(110, 180, 80, 25);
        buttonTampilUbah.addActionListener(this);
        buttonTampilHapus.setBounds(210, 180, 80, 25);
        buttonTampilHapus.addActionListener(this);
        buttonKeluar.setBounds(410, 180, 80, 25);
        buttonKeluar.addActionListener(this);
        buttonBatal.setBounds(310, 180, 80, 25);
        buttonBatal.addActionListener(this);

        panel1.setLayout(null);
        panel1.setBounds(30, 30, 520, 250);
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Pelajaran"));

        //jpanel2 for Buttom

        panel2.setLayout(null);
        panel2.setBounds(30, 300, 520, 200);
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));


        panel1.add(labelKode);
        panel1.add(labelNama);
        panel1.add(labelKelas);
        panel1.add(textFieldKode);
        panel1.add(textFieldPelajaran);
        panel1.add(textFieldSing);

        panel2.add(jScrollPaneTabelSiswa);

        panel1.add(buttonTampil);
        panel1.add(buttonTampilUbah);
        panel1.add(buttonTampilHapus);
        panel1.add(buttonKeluar);
        panel1.add(buttonBatal);
        this.setLayout(null);
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.show();

    }
    
   public void setAwalform(){
   textFieldKode.setText(manData.generateKdPel());
   buttonTampil.setEnabled(true);
   buttonBatal.setEnabled(false);
   buttonKeluar.setEnabled(true);
   buttonTampilHapus.setEnabled(false);
   buttonTampilUbah.setEnabled(false);
   textFieldPelajaran.setText("");
   textFieldSing.setText("");

   }

    public void bersihTable(){
    int row = tbl_Siswa.getRowCount()-1;
    while(row>=0){
        tableModel.removeRow(row);
        row--;
        }
    }


    public void isiTable(){
        bersihTable();
        ResultSet rs = null;
        Connection con = null;
        Statement st = null;
        Object data[] = new Object[3];
        try{
        con = new KoneksiDB().openConnection();
        st = con.createStatement();
        rs = st.executeQuery("Select * FROM pelajaran");
        while(rs.next()){
         data[0]= rs.getString("kd_pel");
         data[1]= rs.getString("nm_pel");
         data[2]= rs.getString("sing_pel");
         tableModel.addRow(data);

        }
        }catch (SQLException ex){
            System.out.println("Error di Query isi Table"+ex.getMessage());
        }


    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonTampil){

            String SQL = "INSERT INTO pelajaran VALUES ('"
                    +textFieldKode.getText()+"','"
                    +textFieldPelajaran.getText()+"','"
                    +textFieldSing.getText()+"')";
            
         if(manData.QUERY(SQL)){
             JOptionPane.showMessageDialog(rootPane, "Data Berhasi Disimpan");
             isiTable();
             setAwalform();
            
        }else{
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Disimpan");
        }

        }
        
//        //Ubah
        if(e.getSource()==buttonTampilUbah){

            String SQL ="UPDATE pelajaran SET "
                + "nm_pel = '"+textFieldPelajaran.getText()+"',"
                + "sing_pel = '"+textFieldSing.getText()+"'"
                +"WHERE kd_pel = '"+textFieldKode.getText()+"'";
            
            if(manData.QUERY(SQL)){
                JOptionPane.showMessageDialog(rootPane, "Data Berhasi Diubah");
                isiTable();
                setAwalform();

           }else{
               JOptionPane.showMessageDialog(rootPane, "Data Gagal Diubah");
           }

        }
//        //delete
         if(e.getSource()==buttonTampilHapus){
         String SQL ="DELETE FROM pelajaran WHERE kd_pel='"+textFieldKode.getText()+"'";
            if(manData.QUERY(SQL)){
                JOptionPane.showMessageDialog(rootPane, "Data Berhasi hapus");
                isiTable();
                setAwalform();

           }else{
               JOptionPane.showMessageDialog(rootPane, "Data Gagal hapus");
           }

        }
         
//         //keluar
         if(e.getSource()==buttonKeluar){
             
             dispose();
         }
//         //batal
         if(e.getSource()==buttonBatal){
             
             textFieldKode.setText(manData.generateKdPel());
             textFieldKode.setEnabled(true);
             textFieldPelajaran.setText("");
             textFieldSing.setText("");
             buttonTampil.setEnabled(true);
             buttonTampilUbah.setEnabled(false);
             buttonBatal.setEnabled(false);
             buttonTampilHapus.setEnabled(false);
             buttonKeluar.setEnabled(true);
             isiTable();
             
         }
    }

    public void mouseClicked(MouseEvent e) {
        buttonTampil.setEnabled(false);
        buttonTampilUbah.setEnabled(true);
        buttonTampilHapus.setEnabled(true);
        buttonBatal.setEnabled(true);
        buttonKeluar.setEnabled(true);
        textFieldKode.setEnabled(false);
        

        // TODO add your handling code here:
            if(e.getSource()==tbl_Siswa){
                    if(e.getClickCount()==1){
                     int row = tbl_Siswa.getSelectedRow();
                     textFieldKode.setText(tableModel.getValueAt(row, 0).toString());
                     textFieldPelajaran.setText(tableModel.getValueAt(row, 1).toString());
                     textFieldSing.setText(tableModel.getValueAt(row, 2).toString());
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
