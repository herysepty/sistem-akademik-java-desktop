/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package form;

import control.KoneksiDB;
import control.ManipulasiData;
import formPopUp.PopUpCariPelajaran;
import java.awt.Dimension;
import java.awt.TextField;
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
public class FrmJadwal extends JInternalFrame implements ActionListener, MouseListener {
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    
    JLabel labelAjaran = new JLabel("Tahun Ajaran:");
    JLabel labelSemester = new JLabel("Smester");
    JLabel labelHari = new JLabel("Hari");
    JLabel labelKelas = new JLabel("Kelas");
    JLabel labelJam = new JLabel("Jam Ke");
    JLabel labelPelajaran = new JLabel("Kode Pelajaran");
    
    TextField kdJadwal = new TextField();
    String[] listthnAjaran = new String[]{"2010/2011","2011/2012","2012/2013","2013/2014"};
    JComboBox thnAjaran = new JComboBox(listthnAjaran);
    String[] listSemester = new String[]{"Gasal","Genap"};
    JComboBox Semester = new JComboBox(listSemester);
    String[] listJam = new String[]{"1","2","3","4","5","6","7","8","9","10"};
    JComboBox jamKe = new JComboBox(listJam);
    String[] listKelas = new String[]{"X","XI","XII"};
    JComboBox kelas = new JComboBox(listKelas);
   
    TextField pelajaran = new TextField();
    
    JCheckBox chkSenin = new JCheckBox("Senin");
    JCheckBox chkSelasa = new JCheckBox("Selasa");
    JCheckBox chkRabu = new JCheckBox("Rabu");
    JCheckBox chkKamis = new JCheckBox("Kamis");
    JCheckBox chkJumat = new JCheckBox("Jumat");
    JCheckBox chkSabtu = new JCheckBox("Sabtu");
    //Button
    JButton buttonTampil = new JButton("Simpan");
    JButton buttonTampilUbah = new JButton("Ubah");
    JButton buttonTampilHapus =new JButton("Hapus");
    JButton buttonKeluar  = new JButton("Keluar");
    JButton buttonBatal = new JButton("Batal");
    JButton btnCari = new JButton("Cari");

    JTable tbl_mahasiswa = new JTable();
    JScrollPane jScrollPaneTabelMahasiswa = new JScrollPane();
    DefaultTableModel tableModel = new  DefaultTableModel(new Object [][]{}, new String[]{"Kode Jadwal","Tahun Ajaran", "Semester","Hari","kelas","Jam","Pelajaran"});
    
    ManipulasiData manData;
    public FrmJadwal(){
        this.setTitle("Form Jadwal Pelajaran");
        this.setSize(600, 700);
      //  this.setLocationRelativeTo(null);
        setLocation(d.width/2-getWidth()/2, d.height/2-getHeight()/2);
        setClosable(true);
        setIconifiable(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //setting table
        tbl_mahasiswa.setModel(tableModel);
        jScrollPaneTabelMahasiswa.setViewportView(tbl_mahasiswa);
        jScrollPaneTabelMahasiswa.setAutoscrolls(true);
        tbl_mahasiswa.addMouseListener(this);
        
        manData = new ManipulasiData();
        setAwalform();
        isiTable();

        labelAjaran.setBounds(15, 30, 150, 25);
        labelSemester.setBounds(15, 70, 150, 25);
        labelHari.setBounds(15, 110, 150, 25);
        labelKelas.setBounds(15, 190, 150, 25);
        labelJam.setBounds(15, 230, 150, 25);
        labelPelajaran.setBounds(15, 280, 150, 25);
            
        thnAjaran.setBounds(170, 30, 150, 25);
        Semester.setBounds(170, 70, 150, 25);
        jamKe.setBounds(170, 230, 100, 25);
        kelas.setBounds(170, 190, 100, 25);
        pelajaran.setBounds(170, 280, 150, 25);
    
        chkSenin.setBounds(170, 110, 70, 25);
        chkSelasa.setBounds(250, 110, 70, 25);
        chkRabu.setBounds(320, 110, 70, 25);
        chkKamis.setBounds(170, 150, 70, 25);
        chkJumat.setBounds(250, 150, 70, 25);
        chkSabtu.setBounds(320, 150, 70, 25);

        jScrollPaneTabelMahasiswa.setBounds(15, 30, 490, 150);

        buttonTampil.setBounds(20, 340, 80, 25);
        buttonTampil.addActionListener(this);
        buttonTampilUbah.setBounds(110, 340, 80, 25);
        buttonTampilUbah.addActionListener(this);
        buttonTampilHapus.setBounds(210, 340, 80, 25);
        buttonTampilHapus.addActionListener(this);
        buttonKeluar.setBounds(410, 340, 80, 25);
        buttonKeluar.addActionListener(this);
        buttonBatal.setBounds(310, 340, 80, 25);
        buttonBatal.addActionListener(this);
        btnCari.setBounds(330, 280, 60, 25);
        btnCari.addActionListener(this);

        panel1.setLayout(null);
        panel1.setBounds(30, 30, 520, 380);
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Jadwal Pelajaran"));

        panel2.setLayout(null);
        panel2.setBounds(30, 420, 520, 200);
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));

        panel1.add(labelAjaran);
        panel1.add(labelSemester);
        panel1.add(labelHari);
        panel1.add(labelKelas);
        panel1.add(labelJam);
        panel1.add(labelPelajaran);
        
        panel1.add(thnAjaran);
        panel1.add(Semester);
        panel1.add(jamKe);
        panel1.add(kelas);
        panel1.add(pelajaran);
        
        panel1.add(chkSenin);
        panel1.add(chkSelasa);
        panel1.add(chkRabu);
        panel1.add(chkKamis);
        panel1.add(chkJumat);
        panel1.add(chkSabtu);
        
        panel2.add(jScrollPaneTabelMahasiswa);

        panel1.add(buttonTampil);
        panel1.add(buttonTampilUbah);
        panel1.add(buttonTampilHapus);
        panel1.add(buttonKeluar);
        panel1.add(buttonBatal);
        panel1.add(btnCari);
        
        this.setLayout(null);
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.show();
    }
    
   public void setAwalform(){
      kdJadwal.setText(manData.generateKdJadwal());
      thnAjaran.setSelectedIndex(0);
      Semester.setSelectedIndex(0);
      chkSenin.setSelected(false);
      chkSelasa.setSelected(false);
      chkRabu.setSelected(false);
      chkKamis.setSelected(false);
      chkJumat.setSelected(false);
      chkSabtu.setSelected(false);
      kelas.setSelectedIndex(0);
      jamKe.setSelectedIndex(0);
      pelajaran.setText("");
      
      buttonTampil.setEnabled(true);
      buttonTampilUbah.setEnabled(false);
      buttonTampilHapus.setEnabled(false);
      buttonBatal.setEnabled(false);
      buttonKeluar.setEnabled(true);
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
        Object data[] = new Object[7];
        try{
        con = new KoneksiDB().openConnection();
        st = con.createStatement();
        rs = st.executeQuery("Select * FROM jadwal");
        while(rs.next()){
            data[0]= rs.getString("kd_jadwal");
            data[1]= rs.getString("thn_ajar");
            data[2]= rs.getString("Semester");
            data[3]= rs.getString("hari");
            data[4]= rs.getString("kelas");
            data[5]= rs.getString("jam");
            data[6]= rs.getString("kd_pel");
            tableModel.addRow(data);
           }
        }catch (SQLException ex){
            System.out.println("Error di Query isi Table"+ex.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonTampil){
            String  vHari="";  
           if(chkSenin.isSelected())
               vHari= chkSenin.getText()+", ";
           if(chkSelasa.isSelected())
               vHari=vHari+ chkSelasa.getText()+", ";
           if(chkRabu.isSelected())
               vHari=vHari+ chkRabu.getText()+", ";
           if(chkKamis.isSelected())
               vHari=vHari+ chkKamis.getText()+", ";
           if(chkJumat.isSelected())
               vHari=vHari+ chkJumat.getText()+", ";
           if(chkSabtu.isSelected())
               vHari=vHari+ chkSabtu.getText()+", ";
            vHari = vHari.substring(0, vHari.length()-2);
            String SQL = "INSERT INTO jadwal VALUES ('"
                    +kdJadwal.getText()+"', '"
                    +kelas.getSelectedItem()+"', '"
                    +vHari+"', '"
                    +jamKe.getSelectedItem()+"', '"
                    +thnAjaran.getSelectedItem()+"', '"
                    +Semester.getSelectedItem()+"', '"
                    +pelajaran.getText()+"')";
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
            String  vHari=""; 
           if(chkSenin.isSelected())
               vHari= chkSenin.getText()+", ";
           if(chkSelasa.isSelected())
               vHari=vHari+ chkSelasa.getText()+", ";
           if(chkRabu.isSelected())
               vHari=vHari+ chkRabu.getText()+", ";
           if(chkKamis.isSelected())
               vHari=vHari+ chkKamis.getText()+", ";
           if(chkJumat.isSelected())
               vHari=vHari+ chkJumat.getText()+", ";
           if(chkSabtu.isSelected())
               vHari=vHari+ chkSabtu.getText()+", ";
            vHari = vHari.substring(0, vHari.length()-2);  
            String SQL ="UPDATE jadwal SET "
                + "kelas = '"+kelas.getSelectedItem()+"',"
                + "hari = '"+vHari+"',"
                + "jam = '"+jamKe.getSelectedItem()+"',"
                + "thn_ajar = '"+thnAjaran.getSelectedItem()+"',"
                + "semester = '"+Semester.getSelectedItem()+"',"
                + "kd_pel = '"+pelajaran.getText()+"'"
                +"WHERE kd_jadwal = '"+kdJadwal.getText()+"'";
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
         String SQL ="DELETE FROM jadwal WHERE kd_jadwal='"+kdJadwal.getText()+"'";
            if(manData.QUERY(SQL)){
                JOptionPane.showMessageDialog(rootPane, "Data Berhasi hapus");
                isiTable();
                setAwalform();
           }else{
               JOptionPane.showMessageDialog(rootPane, "Data Gagal hapus");
           }
        }       
         //keluar
         if(e.getSource()==buttonKeluar){
             
             dispose();
         }
        //batal
         if(e.getSource()==buttonBatal){
            setAwalform();  
         }
         if(e.getSource()==btnCari){
             PopUpCariPelajaran dialog = new PopUpCariPelajaran(new JFrame(), true);
              dialog.setVisible(true);
              pelajaran.setText(dialog.getKd_pel());
         }
    }

    public void mouseClicked(MouseEvent e) {
        buttonTampil.setEnabled(false);
        buttonTampilUbah.setEnabled(true);
        buttonTampilHapus.setEnabled(true);
        buttonBatal.setEnabled(true);
        buttonKeluar.setEnabled(true);
            if(e.getSource()==tbl_mahasiswa){
                    if(e.getClickCount()==1){
                     int row = tbl_mahasiswa.getSelectedRow();
                     kdJadwal.setText(tableModel.getValueAt(row, 0).toString());
                     thnAjaran.setSelectedItem(tableModel.getValueAt(row, 1).toString());
                     Semester.setSelectedItem(tableModel.getValueAt(row, 2).toString());
                    chkSenin.setSelected(false);
                    chkSelasa.setSelected(false);
                    chkRabu.setSelected(false);
                    chkKamis.setSelected(false);
                    chkJumat.setSelected(false);
                    chkSabtu.setSelected(false);
                    String[] vHari = tableModel.getValueAt(row, 3).toString().split(", ");
                        for(int i=0; i<vHari.length;i++){
                            if(vHari[i].equalsIgnoreCase(chkSenin.getText())){
                                chkSenin.setSelected(true);
                           }
                           if(vHari[i].equalsIgnoreCase(chkSelasa.getText())){
                                chkSelasa.setSelected(true);
                           }
                           if(vHari[i].equalsIgnoreCase(chkRabu.getText())){
                                chkRabu.setSelected(true);
                           }
                           if(vHari[i].equalsIgnoreCase(chkKamis.getText())){
                                chkKamis.setSelected(true);
                           }
                           if(vHari[i].equalsIgnoreCase(chkJumat.getText())){
                                chkJumat.setSelected(true);
                           }
                           if(vHari[i].equalsIgnoreCase(chkSabtu.getText())){
                                chkSabtu.setSelected(true);
                           }
                    }
                kelas.setSelectedItem(tableModel.getValueAt(row, 4).toString());
                jamKe.setSelectedItem(tableModel.getValueAt(row, 5).toString());
                pelajaran.setText(tableModel.getValueAt(row, 6).toString());
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
