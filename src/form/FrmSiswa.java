/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package form;

import com.toedter.calendar.JDateChooser;
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

//import javax.swing.JLabel;
//import javax.swing.JTextField;

/**
 *
 * @author Hery Septyadi | 1211501224
 */
public class FrmSiswa extends JInternalFrame implements ActionListener, MouseListener {
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    
    JLabel labelNIS = new JLabel("NIS");
    JLabel labelNama = new JLabel("Nama Siswa");
    JLabel lbltempatTglLahir = new JLabel("Tempat/Tanggal Lahir");
    JLabel lblJenKel = new JLabel("Jenis Kelamin");
    JLabel lblAlamat = new JLabel("Alamat");
    JLabel labelKelas = new JLabel("Kelas");
    
    JTextField textFieldNIS = new JTextField();
    JTextField textFieldNama = new JTextField();
    JTextArea textAreaAlamat = new JTextArea();
    JScrollPane jscrollpane = new JScrollPane();
    JRadioButton rbLakiLaki = new JRadioButton("Laki-Laki");
    JRadioButton rbPerempuan = new JRadioButton("Perempuan");
    ButtonGroup bg = new ButtonGroup();
    JTextField txtTempat = new JTextField();
    //JTextField txtTglLahir = new JTextField();
    String[] listKelas = new String[]{"X","XI","XII"};
    JComboBox kelas = new JComboBox(listKelas);
    
    JDateChooser calenderLahir = new JDateChooser();
    
    
    
    //Button
    JButton buttonTampil = new JButton("Simpan");
    JButton buttonTampilUbah = new JButton("Ubah");
    JButton buttonTampilHapus =new JButton("Hapus");
    JButton buttonKeluar  = new JButton("Keluar");
    JButton buttonBatal = new JButton("Batal");

    JTable tbl_Siswa = new JTable();
    JScrollPane jScrollPaneTabelSiswa = new JScrollPane();
    DefaultTableModel tableModel = new  DefaultTableModel(new Object [][]{}, new String[]{"NIS","NAMA","Tempat Lahir","Tanggal Lahir","Jenis Kelamin","Alamat","KELAS"});
    
    ManipulasiData manData;
    public FrmSiswa(){
        this.setTitle("Form Siswa");
        this.setSize(600, 630);
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
        bg.add(rbLakiLaki);
        bg.add(rbPerempuan);
        
        labelNIS.setBounds(15, 30, 150, 25);
        labelNama.setBounds(15, 70, 150, 25);
        lbltempatTglLahir.setBounds(15, 110, 150, 25);
        lblJenKel.setBounds(15, 150, 150, 25);
        lblAlamat.setBounds(15, 190, 150, 25);
        labelKelas.setBounds(15, 255, 150, 25);
        
        textFieldNIS.setBounds(170, 30, 150, 25);
        textFieldNama.setBounds(170, 70, 150, 25);
        txtTempat.setBounds(170, 110, 150, 25);
        calenderLahir.setBounds(330, 110, 100, 25);
        rbLakiLaki.setBounds(170, 150, 80, 25);
        rbPerempuan.setBounds(255, 150, 100, 25);
        kelas.setBounds(170, 255, 70, 25);
        textAreaAlamat.setLineWrap(true);
        jscrollpane.setBounds(170, 190, 260, 50);
        jscrollpane.setViewportView(textAreaAlamat);

        jScrollPaneTabelSiswa.setBounds(15, 30, 490, 150);

        buttonTampil.setBounds(20, 300, 80, 25);
        buttonTampil.addActionListener(this);
        buttonTampilUbah.setBounds(110, 300, 80, 25);
        buttonTampilUbah.addActionListener(this);
        buttonTampilHapus.setBounds(210, 300, 80, 25);
        buttonTampilHapus.addActionListener(this);
        buttonKeluar.setBounds(410, 300, 80, 25);
        buttonKeluar.addActionListener(this);
        buttonBatal.setBounds(310, 300, 80, 25);
        buttonBatal.addActionListener(this);

        panel1.setLayout(null);
        panel1.setBounds(30, 30, 520, 350);
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Siswa"));

        //jpanel2 for Buttom

        panel2.setLayout(null);
        panel2.setBounds(30, 400, 520, 200);
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));


        panel1.add(labelNIS);
        panel1.add(labelNama);
        panel1.add(lblAlamat);
        panel1.add(lblJenKel);
        panel1.add(lbltempatTglLahir);
        panel1.add(labelKelas);
        panel1.add(textFieldNama);
        panel1.add(textFieldNIS);
        panel1.add(kelas);
        panel1.add(txtTempat);
        panel1.add(calenderLahir);
        panel1.add(rbLakiLaki);
        panel1.add(rbPerempuan);
        panel1.add(jscrollpane);
        
    
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
   textFieldNIS.setText(manData.generateNIS());
   buttonTampil.setEnabled(true);
   buttonBatal.setEnabled(false);
   buttonKeluar.setEnabled(true);
   buttonTampilHapus.setEnabled(false);
   buttonTampilUbah.setEnabled(false);
   textFieldNIS.setEnabled(true);
   textAreaAlamat.setText("");
   txtTempat.setText("");
   calenderLahir.setDate(null);
   rbLakiLaki.setSelected(true);
   rbPerempuan.setSelected(false);
   textFieldNama.setText("");
   kelas.setSelectedIndex(0);
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
        Object data[] = new Object[7];
        try{
        con = new KoneksiDB().openConnection();
        st = con.createStatement();
        rs = st.executeQuery("Select * FROM siswa");
        while(rs.next()){
         data[0]= rs.getString("nis");
         data[1]= rs.getString("nama");
         data[2]= rs.getString("tempat_lahir");
         data[3]= rs.getString("tgl_lahir");
         data[4]= rs.getString("jenkel");
         data[5]= rs.getString("alamat");
         data[6]= rs.getString("kelas");
         tableModel.addRow(data);

        }
        }catch (SQLException ex){
            System.out.println("Error di Query isi Table"+ex.getMessage());
        }


    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonTampil){
           String jenkel="";
            { if(rbLakiLaki.isSelected())
                jenkel= rbLakiLaki.getText();
            else
                jenkel=rbPerempuan.getText();
            }
            String SQL = "INSERT INTO siswa VALUES ('"
                    +textFieldNIS.getText()+"','"
                    +textFieldNama.getText()+"','"
                    +txtTempat.getText()+"','"
                    +new SimpleDateFormat("yyyy-MM-dd").format(calenderLahir.getDate())+"','"
                    +jenkel+"','"
                    +textAreaAlamat.getText()+"','"
                    +kelas.getSelectedItem()+"')";
            
         if(manData.QUERY(SQL)){
             JOptionPane.showMessageDialog(rootPane, "Data Berhasi Disimpan");
             isiTable();
             setAwalform();
            
        }else{
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Disimpan");
        }

        }
//        
//        //Ubah
        if(e.getSource()==buttonTampilUbah){
            String jenkel="";
            { if(rbLakiLaki.isSelected())
                jenkel= rbLakiLaki.getText();
            else
                jenkel=rbPerempuan.getText();
            }
            String SQL ="UPDATE siswa SET "
                + "nama = '"+textFieldNama.getText()+"',"
                + "kelas = '"+kelas.getSelectedItem()+"',"
                + "tempat_lahir = '" +txtTempat.getText()+"',"
                + "tgl_lahir = '"  +new SimpleDateFormat("yyyy-MM-dd").format(calenderLahir.getDate())+"',"
                + "jenkel = '" +jenkel+"',"
                + "alamat = '" +textAreaAlamat.getText()+"'"
                +"WHERE nis = '"+textFieldNIS.getText()+"'";
            
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
         String SQL ="DELETE FROM siswa WHERE nis='"+textFieldNIS.getText()+"'";
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
        textFieldNIS.setEnabled(false);
        

            if(e.getSource()==tbl_Siswa){
                    if(e.getClickCount()==1){
                     int row = tbl_Siswa.getSelectedRow();
                     textFieldNIS.setText(tableModel.getValueAt(row, 0).toString());
                     textFieldNama.setText(tableModel.getValueAt(row, 1).toString());
                     txtTempat.setText(tableModel.getValueAt(row, 2).toString());
                    // calenderLahir.setDate(tableModel.getDataVector(calenderLahir, 3).toString);
                     if(tableModel.getValueAt(row, 4).toString().equals("Laki-Laki")){
                        rbLakiLaki.setSelected(true);
                     }else{
                        rbPerempuan.setSelected(true);
                     }
                     textAreaAlamat.setText(tableModel.getValueAt(row, 5).toString());
                     kelas.setSelectedItem(tableModel.getValueAt(row, 6).toString());
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
