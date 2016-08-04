/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package form;

import com.toedter.calendar.JDateChooser;
import control.KoneksiDB;
import control.ManipulasiData;
import formPopUp.PopUpCariSiswa;
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
public class FrmAbsensi extends JInternalFrame implements ActionListener, MouseListener {
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    
    JLabel labelNIS = new JLabel("NIS");
    JLabel labelNmSiswa = new JLabel("Nama Siswa");
    JLabel labelTanggal = new JLabel("Tanggal");
    JLabel labelKet = new JLabel("Keterangan");
    JLabel labelKelas = new JLabel("Kelas");
    JLabel labelAjaran = new JLabel("Tahun Ajaran:");
    JLabel labelSemester = new JLabel("Smester");
    
    String[] listthnAjaran = new String[]{"2010/2011","2011/2012","2012/2013","2013/2014"};
    JComboBox thnAjaran = new JComboBox(listthnAjaran);
    String[] listSemester = new String[]{"Gasal","Genap"};
    JComboBox Semester = new JComboBox(listSemester);
    String[] listKelas = new String[]{"X","XII","XII"};
    JComboBox kelas = new JComboBox(listKelas);
   
    TextField txtNoAbs = new TextField();
    TextField txtNis = new TextField();
    TextField txtNmSiswa = new TextField();
    //TextField txtTanggal = new TextField();
    
    JDateChooser Calender = new JDateChooser();
    
    
    JRadioButton rbHadir = new JRadioButton("Hadir");
    JRadioButton rbIjin = new JRadioButton("Ijin");
    JRadioButton rbSakit = new JRadioButton("Sakit");
    ButtonGroup bg = new ButtonGroup();

    //Button
    JButton buttonTampil = new JButton("Simpan");
    JButton buttonTampilUbah = new JButton("Ubah");
    JButton buttonTampilHapus =new JButton("Hapus");
    JButton buttonKeluar  = new JButton("Keluar");
    JButton buttonBatal = new JButton("Batal");
    JButton btnCari = new JButton("Cari");

    JTable tbl_mahasiswa = new JTable();
    JScrollPane jScrollPaneTabelMahasiswa = new JScrollPane();
    DefaultTableModel tableModel = new  DefaultTableModel(new Object [][]{}, new String[]{"Kode Absen","NIS","Nama Siswa", "Tanggal","Ket","kelas","Tahun Ajaran","Semester"});
    
    ManipulasiData manData;
    public FrmAbsensi(){
        this.setTitle("Form Absensi");
        this.setSize(600, 680);
      //this.setLocationRelativeTo(null);
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

        labelNIS.setBounds(15, 30, 150, 25);
        labelNmSiswa.setBounds(15, 70, 150, 25);
        labelTanggal.setBounds(15, 110, 150, 25);
        labelKet.setBounds(15, 150, 150, 25);
        labelKelas.setBounds(15, 190, 150, 25);
        labelAjaran.setBounds(15, 230, 150, 25);
        labelSemester.setBounds(15, 270, 150, 25);
        
        bg.add(rbHadir);
        bg.add(rbIjin);
        bg.add(rbSakit);
        
        txtNis.setBounds(170, 30, 80, 25);
        txtNmSiswa.setBounds(170, 70, 150, 25);
        Calender.setBounds(170, 110, 150, 25);
        rbHadir.setBounds(170, 150, 60, 25);
        rbIjin.setBounds(230, 150, 60, 25);
        rbSakit.setBounds(290, 150, 60, 25);
        kelas.setBounds(170, 190, 100, 25);
        thnAjaran.setBounds(170, 230, 100, 25);
        Semester.setBounds(170, 270, 100, 25);

        jScrollPaneTabelMahasiswa.setBounds(15, 30, 490, 150);

        buttonTampil.setBounds(20, 320, 80, 25);
        buttonTampil.addActionListener(this);
        buttonTampilUbah.setBounds(110, 320, 80, 25);
        buttonTampilUbah.addActionListener(this);
        buttonTampilHapus.setBounds(210, 320, 80, 25);
        buttonTampilHapus.addActionListener(this);
        buttonKeluar.setBounds(410, 320, 80, 25);
        buttonKeluar.addActionListener(this);
        buttonBatal.setBounds(310, 320, 80, 25);
        buttonBatal.addActionListener(this);
        btnCari.setBounds(260, 30, 60, 25);
        btnCari.addActionListener(this);

        panel1.setLayout(null);
        panel1.setBounds(30, 30, 520, 380);
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Absensi"));

        panel2.setLayout(null);
        panel2.setBounds(30, 420, 520, 200);
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));

        panel1.add(labelNIS);
        panel1.add(labelAjaran);
        panel1.add(labelSemester);
        panel1.add(labelTanggal);
        panel1.add(labelKelas);
        panel1.add(labelKet);
        panel1.add(labelNmSiswa);
        
        panel1.add(thnAjaran);
        panel1.add(Semester);
        panel1.add(Calender);
        panel1.add(kelas);
        panel1.add(txtNis);
        panel1.add(rbHadir);
        panel1.add(rbIjin);
        panel1.add(rbSakit);
        panel1.add(txtNmSiswa);
        
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
       txtNoAbs.setText(manData.generateKdAbsen());
       buttonTampil.setEnabled(true);
       buttonBatal.setEnabled(false);
       buttonKeluar.setEnabled(true);
       buttonTampilHapus.setEnabled(false);
       buttonTampilUbah.setEnabled(false);
       txtNis.setText("");
       txtNmSiswa.setText("");
       Calender.setDate(null);
       rbHadir.setSelected(true);
       rbIjin.setSelected(false);
       rbSakit.setSelected(false);
       kelas.setSelectedIndex(0);
       thnAjaran.setSelectedIndex(0);
       Semester.setSelectedIndex(0);
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
        Object data[] = new Object[8];
        try{
        con = new KoneksiDB().openConnection();
        st = con.createStatement();
        rs = st.executeQuery("Select * FROM absensi");
        while(rs.next()){
                data[0]= rs.getString("no_abs");
                data[1]= rs.getString("nis");
                data[2]= rs.getString("nm_siswa");
                data[3]= rs.getString("tgl_absen");
                data[4]= rs.getString("sts_absen");
                data[5]= rs.getString("kls_absen");
                data[6]= rs.getString("thn_ajar");
                data[7]= rs.getString("semester");
                tableModel.addRow(data);
            }
        }catch (SQLException ex){
            System.out.println("Error di Query isi Table"+ex.getMessage());
        }
}

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonTampil){
             String status="";
            { if(rbHadir.isSelected())
                status= rbHadir.getText();
            }
            if(rbIjin.isSelected()){
                status=rbIjin.getText();
            }
            if(rbSakit.isSelected()){
                status=rbSakit.getText();
            }
            String SQL = "INSERT INTO absensi VALUES ('"
                    +txtNoAbs.getText()+"','"
                    +new SimpleDateFormat("yyyy-MM-dd").format(Calender.getDate())+"','"
                    +status+"','"
                    +kelas.getSelectedItem()+"','"
                    +thnAjaran.getSelectedItem()+"','"
                    +Semester.getSelectedItem()+"','"
                    +txtNis.getText()+"','"
                    +txtNmSiswa.getText()+"')";
         if(manData.QUERY(SQL)){
             JOptionPane.showMessageDialog(rootPane, "Data Berhasi Disimpan");
             isiTable();
             setAwalform();
        }else{
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Disimpan");
        }
}   

        if(e.getSource()==buttonTampilUbah){
            String status="";
            { if(rbHadir.isSelected())
                status=rbHadir.getText();
            }
            if(rbIjin.isSelected()){
                status=rbIjin.getText();
            }
            if(rbSakit.isSelected()){
                status=rbSakit.getText();
            }
            String SQL ="UPDATE absensi SET "
                + "nis= '"+txtNis.getText()+"',"
                + "tgl_absen = '"+new SimpleDateFormat("yyyy-MM-dd").format(Calender.getDate())+"',"
                + "sts_absen = '"+status+"',"
                + "kls_absen = '"+kelas.getSelectedItem()+"',"
                + "thn_ajar = '"+thnAjaran.getSelectedItem()+"',"
                + "semester = '"+Semester.getSelectedItem()+"',"
                + "nm_siswa = '"+txtNmSiswa.getText()+"'"
                +"WHERE no_abs = '"+txtNoAbs.getText()+"'";
            if(manData.QUERY(SQL)){
                JOptionPane.showMessageDialog(rootPane, "Data Berhasi Diubah");
                setAwalform();
                isiTable();
           }else{
               JOptionPane.showMessageDialog(rootPane, "Data Gagal Diubah");
           }
        }
     //delete
         if(e.getSource()==buttonTampilHapus){
         String SQL ="DELETE FROM absensi WHERE no_abs='"+txtNoAbs.getText()+"'";
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
         if(e.getSource()==btnCari){
             PopUpCariSiswa dialog = new PopUpCariSiswa(new JFrame(), true);
             dialog.setVisible(true);
             txtNis.setText(dialog.getNis());
             txtNmSiswa.setText(dialog.getvNamaSiswa());
             txtNmSiswa.setEnabled(false);
         }
         //batal
         if(e.getSource()==buttonBatal){
             isiTable(); 
             setAwalform();
             txtNis.setEnabled(true);
         }
    }

    public void mouseClicked(MouseEvent e) {
        buttonTampil.setEnabled(false);
        buttonTampilUbah.setEnabled(true);
        buttonTampilHapus.setEnabled(true);
        buttonBatal.setEnabled(true);
        buttonKeluar.setEnabled(true);
        txtNis.setEnabled(false);
            if(e.getSource()==tbl_mahasiswa){
                if(e.getClickCount()==1){
                    int row = tbl_mahasiswa.getSelectedRow();
                    txtNoAbs.setText(tableModel.getValueAt(row, 0).toString());
                    txtNis.setText(tableModel.getValueAt(row, 1).toString());
                    txtNmSiswa.setText(tableModel.getValueAt(row, 2).toString());
                   // txtTanggal.setText(tableModel.getValueAt(row, 3).toString());             
                    if(tableModel.getValueAt(row, 4).toString().equals("Hadir")){
                        rbHadir.setSelected(true);
                    }
                    if(tableModel.getValueAt(row, 4).toString().equals("Ijin")){
                        rbIjin.setSelected(true);
                    }
                    if(tableModel.getValueAt(row, 4).toString().equals("Sakit")){
                      rbSakit.setSelected(true);  
                    }
                    kelas.setSelectedItem(tableModel.getValueAt(row, 5).toString());
                    thnAjaran.setSelectedItem(tableModel.getValueAt(row, 6).toString());
                    Semester.setSelectedItem(tableModel.getValueAt(row, 7).toString());
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
