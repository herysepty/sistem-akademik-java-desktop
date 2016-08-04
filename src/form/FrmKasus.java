/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package form;

import com.toedter.calendar.JDateChooser;
import control.KoneksiDB;
import control.ManipulasiData;
import formPopUp.PopUpCariSiswa;
import formPopUp.PopUpCariKasus;
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
import java.text.SimpleDateFormat;
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

/**
 *
 * @author Hery Septyadi | 1211501224
 */
public class FrmKasus extends JInternalFrame implements ActionListener, MouseListener {
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JLabel labelTanggal = new JLabel("Tanggal");
    JLabel labelNis = new JLabel("NIS");
    JLabel labelKdKasus = new JLabel("Kode Pelanggaran");
    
    JTextField textFieldNis = new JTextField();
    JTextField textFieldKdKasus = new JTextField();
    //JTextField textFieldTanggal = new JTextField();
    JDateChooser Calender = new JDateChooser();

    JButton buttonTampil = new JButton("Simpan");
    JButton buttonTampilUbah = new JButton("Ubah");
    JButton buttonTampilHapus =new JButton("Hapus");
    JButton buttonKeluar  = new JButton("Keluar");
    JButton buttonBatal = new JButton("Batal");
    JButton btnCariSiswa = new JButton("Cari");
    JButton btnCariKasus = new JButton("Cari");
    

    JTable tbl_Kasus = new JTable();
    JScrollPane jScrollPaneTabelMahasiswa = new JScrollPane();
    DefaultTableModel tableModel = new  DefaultTableModel(new Object [][]{}, new String[]{"NIS", "Kode Kasus","Tanggal"});
    
    ManipulasiData manData;
    public FrmKasus(){
        this.setTitle("Form Data Pelanggaran");
        this.setSize(600, 550);
      //  this.setLocationRelativeTo(null);
        setLocation(d.width/2-getWidth()/2, d.height/2-getHeight()/2);
        setClosable(true);
        setIconifiable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        manData = new ManipulasiData();

        //setting table
        tbl_Kasus.setModel(tableModel);
        jScrollPaneTabelMahasiswa.setViewportView(tbl_Kasus);
        jScrollPaneTabelMahasiswa.setAutoscrolls(true);
        tbl_Kasus.addMouseListener(this);
        
        //isi table
        isiTable();
        setAwalform();

        labelNis.setBounds(15, 30, 150, 25);
        labelKdKasus.setBounds(15, 70, 150, 25);
        labelTanggal.setBounds(15, 110, 150, 25);
        
        textFieldNis.setBounds(170, 30, 70, 25);
        textFieldKdKasus.setBounds(170, 70, 70, 25);
       Calender.setBounds(170, 110, 150, 25);
        
        

        jScrollPaneTabelMahasiswa.setBounds(15, 30, 490, 150);


        buttonTampil.setBounds(20, 170, 80, 25);
        buttonTampil.addActionListener(this);
        buttonTampilUbah.setBounds(110, 170, 80, 25);
        buttonTampilUbah.addActionListener(this);
        buttonTampilHapus.setBounds(210, 170, 80, 25);
        buttonTampilHapus.addActionListener(this);
        buttonKeluar.setBounds(410, 170, 80, 25);
        buttonKeluar.addActionListener(this);
        buttonBatal.setBounds(310, 170, 80, 25);
        buttonBatal.addActionListener(this);
        btnCariSiswa.setBounds(250, 30, 70, 25);
        btnCariSiswa.addActionListener(this);
        btnCariKasus.setBounds(250, 70, 70, 25);
        btnCariKasus.addActionListener(this);

        panel1.setLayout(null);
        panel1.setBounds(30, 30, 520, 250);
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Kasus"));

        //jpanel2 for Buttom

        panel2.setLayout(null);
        panel2.setBounds(30, 300, 520, 200);
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));

        panel1.add(labelTanggal);
        panel1.add(labelKdKasus);
        panel1.add(labelNis);
        
        panel1.add(Calender);
        panel1.add(textFieldNis);
        panel1.add(textFieldKdKasus);
       
        panel2.add(jScrollPaneTabelMahasiswa);

        panel1.add(buttonTampil);
        panel1.add(buttonTampilUbah);
        panel1.add(buttonTampilHapus);
        panel1.add(buttonKeluar);
        panel1.add(buttonBatal);
        panel1.add(btnCariSiswa);
        panel1.add(btnCariKasus);
        this.setLayout(null);
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.show();

    }

   public void setAwalform(){
             buttonTampil.setEnabled(true);
             buttonTampilUbah.setEnabled(false);
             buttonBatal.setEnabled(false);
             buttonTampilHapus.setEnabled(false);
             buttonKeluar.setEnabled(true);
             textFieldKdKasus.setText("");
             textFieldNis.setText("");
             Calender.setDate(null);
   }

    public void bersihTable(){
    int row = tbl_Kasus.getRowCount()-1;
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
        rs = st.executeQuery("Select * FROM kasus");
        while(rs.next()){
         data[0]= rs.getString("nis");
         data[1]= rs.getString("kd_kasus");
         data[2]= rs.getString("tgl_kasus");
         tableModel.addRow(data);

        }
        }catch (SQLException ex){
            System.out.println("Error di Query isi Table"+ex.getMessage());
        }


    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonTampil){

            String SQL = "INSERT INTO kasus VALUES ('"
                    +textFieldNis.getText()+"','"
                    +textFieldKdKasus.getText()+"','"
                    +new SimpleDateFormat("yyyy-MM-dd").format(Calender.getDate())+"')";
            
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

            String SQL ="UPDATE kasus SET "
                + "tgl_kasus = '"+new SimpleDateFormat("yyyy-MM-dd").format(Calender.getDate())+"'"
                +"WHERE nis = '"+textFieldNis.getText()+"' && kd_kasus='"+textFieldKdKasus.getText()+"'";
            
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
         String SQL ="DELETE FROM kasus WHERE nis = '"+textFieldNis.getText()+"' && kd_kasus ='"+textFieldKdKasus.getText()+"'";
            if(manData.QUERY(SQL)){
                JOptionPane.showMessageDialog(rootPane, "Data Berhasi hapus");
                isiTable();
                setAwalform();

           }else{
               JOptionPane.showMessageDialog(rootPane, "Data Gagal hapus");
           }

        }

         if(e.getSource()==buttonKeluar){
             
             dispose();
         }

         if(e.getSource()==buttonBatal){
             setAwalform();
             isiTable();
         }
             if(e.getSource()==btnCariSiswa){
             PopUpCariSiswa dialog = new PopUpCariSiswa(new JFrame(), true);
             dialog.setVisible(true);
             textFieldNis.setText(dialog.getNis());
         }
                      if(e.getSource()==btnCariKasus){
             PopUpCariKasus dialog = new PopUpCariKasus(new JFrame(), true);
             dialog.setVisible(true);
             textFieldKdKasus.setText(dialog.getvKd_kasus());
         }
    }

    public void mouseClicked(MouseEvent e) {
        buttonTampil.setEnabled(false);
        buttonTampilUbah.setEnabled(true);
        buttonTampilHapus.setEnabled(true);
        buttonBatal.setEnabled(true);
        buttonKeluar.setEnabled(true);
        textFieldKdKasus.setEnabled(false);
        textFieldNis.setEnabled(false);
        
            if(e.getSource()==tbl_Kasus){
                    if(e.getClickCount()==1){
                     int row = tbl_Kasus.getSelectedRow();
                     textFieldNis.setText(tableModel.getValueAt(row, 0).toString());
                     textFieldKdKasus.setText(tableModel.getValueAt(row, 1).toString());
                     //textFieldTanggal.setText(tableModel.getValueAt(row, 2).toString());
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
