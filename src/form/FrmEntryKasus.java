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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

//import javax.swing.JLabel;
//import javax.swing.JTextField;

/**
 *
 * @author Hery Septyadi | 1211501224
 */
public class FrmEntryKasus extends JInternalFrame implements ActionListener, MouseListener {
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JLabel labelNim = new JLabel("Kode Pelanggaran:");
    JLabel labelNama = new JLabel("Point:");
    JLabel labelKelas = new JLabel("Singkatan:");
    JLabel labelBobotPoint = new JLabel("Bobot Point:");
    
    JTextField textFieldNim = new JTextField();
    JTextField textFieldNama = new JTextField();
    JTextField textFieldKelas = new JTextField();
    JTextField textFieldBobotPoint = new JTextField();

    //Button
    JButton buttonTampil = new JButton("Simpan");
    JButton buttonTampilUbah = new JButton("Ubah");
    JButton buttonTampilHapus =new JButton("Hapus");
    JButton buttonKeluar  = new JButton("Keluar");
    JButton buttonBatal = new JButton("Batal");

    JTable tbl_mahasiswa = new JTable();
    JScrollPane jScrollPaneTabelMahasiswa = new JScrollPane();
    DefaultTableModel tableModel = new  DefaultTableModel(new Object [][]{}, new String[]{"Kode", "Nama Point","Singkatan","Bobot Point"});
    
    ManipulasiData manData;
    public FrmEntryKasus(){
        this.setTitle("Form Entri Pelanggaran");
        this.setSize(600, 550);
      //  this.setLocationRelativeTo(null);
        setLocation(d.width/2-getWidth()/2, d.height/2-getHeight()/2);
        setClosable(true);
        setIconifiable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        manData = new ManipulasiData();
        setAwalform();
        //setting table
        tbl_mahasiswa.setModel(tableModel);
        jScrollPaneTabelMahasiswa.setViewportView(tbl_mahasiswa);
        jScrollPaneTabelMahasiswa.setAutoscrolls(true);
        tbl_mahasiswa.addMouseListener(this);
        
        //isi table
        isiTable();

        
        labelNim.setBounds(15, 30, 150, 25);
        labelNama.setBounds(15, 70, 150, 25);
        labelKelas.setBounds(15, 110, 150, 25);
        labelBobotPoint.setBounds(15, 150, 150, 25);
        
        textFieldNim.setBounds(170, 30, 150, 25);
        textFieldNama.setBounds(170, 70, 150, 25);
        textFieldKelas.setBounds(170, 110, 150, 25);
        textFieldBobotPoint.setBounds(170, 150, 150, 25);

        jScrollPaneTabelMahasiswa.setBounds(15, 30, 490, 150);


        buttonTampil.setBounds(20, 200, 80, 25);
        buttonTampil.addActionListener(this);
        buttonTampilUbah.setBounds(110, 200, 80, 25);
        buttonTampilUbah.addActionListener(this);
        buttonTampilHapus.setBounds(210, 200, 80, 25);
        buttonTampilHapus.addActionListener(this);
        buttonKeluar.setBounds(410, 200, 80, 25);
        buttonKeluar.addActionListener(this);
        buttonBatal.setBounds(310, 200, 80, 25);
        buttonBatal.addActionListener(this);

        panel1.setLayout(null);
        panel1.setBounds(30, 30, 520, 250);
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Point Pelanggaran"));

        //jpanel2 for Buttom

        panel2.setLayout(null);
        panel2.setBounds(30, 300, 520, 200);
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));


        panel1.add(labelNim);
        panel1.add(labelNama);
        panel1.add(labelKelas);
        panel1.add(labelBobotPoint);
        panel1.add(textFieldNama);
        panel1.add(textFieldNim);
        panel1.add(textFieldKelas);
        panel1.add(textFieldBobotPoint);
       
          panel2.add(jScrollPaneTabelMahasiswa);

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
   buttonTampil.setEnabled(true);
   buttonBatal.setEnabled(false);
   buttonKeluar.setEnabled(true);
   buttonTampilHapus.setEnabled(false);
   buttonTampilUbah.setEnabled(false);
   textFieldNim.setText(manData.generatePelanggaran());
   textFieldNama.setText("");
   textFieldKelas.setText("");
   textFieldBobotPoint.setText("");
   }

    public void bersihTable(){
    int row = tbl_mahasiswa.getRowCount()-1;
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
        Object data[] = new Object[4];
        try{
        con = new KoneksiDB().openConnection();
        st = con.createStatement();
        rs = st.executeQuery("Select * FROM point");
        while(rs.next()){
         data[0]= rs.getString("kd_point");
         data[1]= rs.getString("nm_point");
         data[2]= rs.getString("sing_point");
         data[3]= rs.getString("bsr_point");
         tableModel.addRow(data);

        }
        }catch (SQLException ex){
            System.out.println("Error di Query isi Table"+ex.getMessage());
        }


    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonTampil){
           

            String SQL = "INSERT INTO point VALUES ('"
                    +textFieldNim.getText()+"','"
                    +textFieldNama.getText()+"','"
                    +textFieldKelas.getText()+"','"
                    +textFieldBobotPoint.getText()+"')";
            
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
            
            String SQL ="UPDATE point SET "
                + "nm_point = '"+textFieldNama.getText()+"',"
                + "sing_point = '"+textFieldKelas.getText()+"',"
                + "bsr_point = '"+textFieldBobotPoint.getText()+"'"
                +"WHERE kd_point = '"+textFieldNim.getText()+"'";
            
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
         String SQL ="DELETE FROM point WHERE kd_point='"+textFieldNim.getText()+"'";
            if(manData.QUERY(SQL)){
                JOptionPane.showMessageDialog(rootPane, "Data Berhasi hapus");
                isiTable();
                setAwalform();

           }else{
               JOptionPane.showMessageDialog(rootPane, "Data Gagal hapus");
           }

        }
//         
//         //keluar
         if(e.getSource()==buttonKeluar){     
             dispose();
         }
//         //batal
         if(e.getSource()==buttonBatal){
             
             setAwalform();
             isiTable();
             
         }
    }

    public void mouseClicked(MouseEvent e) {
        buttonTampil.setEnabled(false);
        buttonTampilUbah.setEnabled(true);
        buttonTampilHapus.setEnabled(true);
        buttonBatal.setEnabled(true);
        buttonKeluar.setEnabled(true);
        textFieldNim.setEnabled(false);
        

        // TODO add your handling code here:
            if(e.getSource()==tbl_mahasiswa){
                    if(e.getClickCount()==1){
                     int row = tbl_mahasiswa.getSelectedRow();
                     textFieldNim.setText(tableModel.getValueAt(row, 0).toString());
                     textFieldNama.setText(tableModel.getValueAt(row, 1).toString());
                     textFieldKelas.setText(tableModel.getValueAt(row, 2).toString());
                     textFieldBobotPoint.setText(tableModel.getValueAt(row, 3).toString());
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
