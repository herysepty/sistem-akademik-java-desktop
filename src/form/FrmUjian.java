/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package form;

import com.toedter.calendar.JDateChooser;
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
public class FrmUjian extends JInternalFrame implements ActionListener, MouseListener {
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    
    JLabel labelKdUjian = new JLabel("kode Ujian");
    JLabel labelKdMatPel = new JLabel("Kode Mata Pelajaran");
    JLabel labelNamaMatPel = new JLabel("Nama Mata Pelajaran");
    JLabel labelKelas = new JLabel("Kelas");
    JLabel labelTanggalUjian = new JLabel("Tanggal Ujian");
    JLabel labelDurasi = new JLabel("Durasi");
    JLabel labelJam = new JLabel("Jam Ke");
    JLabel labelRuang = new JLabel("Ruang");
    JLabel labelJenisUjian = new JLabel("Jenis Ujian");
    JLabel labelSemester = new JLabel("Smester");
    JLabel labelAjaran = new JLabel("Tahun Ajaran:");
    JLabel labelSamDeng = new JLabel("s/d");
    
    TextField txtFieldKdUjian = new TextField();
    TextField txtFieldKdMatPel = new TextField();
    TextField txtFieldNmPel = new TextField();
    String[] listKelas = new String[]{"X","XI","XII"};
    JComboBox kelas = new JComboBox(listKelas);
    //TextField txtFieldTglUjian = new TextField();
    
    JDateChooser Calender = new JDateChooser();
    
    JRadioButton rb60 = new JRadioButton("60");
    JRadioButton rb90 = new JRadioButton("90");
    JRadioButton rb120 = new JRadioButton("120");
    ButtonGroup bg = new ButtonGroup();
    
    String[] listJamAwal = new String[]{"07:00","09:30"};
    JComboBox jamAwal = new JComboBox(listJamAwal);
    String[] listJamAkhir = new String[]{"09:00","11:30"};
    JComboBox jamKeAkhir = new JComboBox(listJamAkhir);
    String[] listRuang = new String[]{"1","2","3","4","5"};
    JComboBox ruang = new JComboBox(listRuang);
    
    JRadioButton rbUTS = new JRadioButton("UTS");
    JRadioButton rbUAS = new JRadioButton("UAS");
    ButtonGroup bgJenisUjian = new ButtonGroup();
    
    String[] listSemester = new String[]{"Gasal","Genap"};
    JComboBox Semester = new JComboBox(listSemester);
    String[] listthnAjaran = new String[]{"2010/2011","2011/2012","2012/2013","2013/2014"};
    JComboBox thnAjaran = new JComboBox(listthnAjaran);

    JButton buttonTampil = new JButton("Simpan");
    JButton buttonTampilUbah = new JButton("Ubah");
    JButton buttonTampilHapus =new JButton("Hapus");
    JButton buttonKeluar  = new JButton("Keluar");
    JButton buttonBatal = new JButton("Batal");
    JButton btnCari = new JButton("Cari");

    JTable tbl_mahasiswa = new JTable();
    JScrollPane jScrollPaneTabelMahasiswa = new JScrollPane();
    DefaultTableModel tableModel = new  DefaultTableModel(new Object [][]{}, new String[]{"Kode Jadwal Ujian","Kode Pelajaran", "Pelajaran","Kelas","Tanggal",
        "Durasi","Mulai","Selesai","Ruang","Jenis Ujian","Semester","Tahun Ajaran"});
    
    ManipulasiData manData;
    public FrmUjian(){
        this.setTitle("Form Jadwal Ujian");
        this.setSize(710, 700);
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
        
        isiTable();
        manData = new ManipulasiData();
        setAwalform();
        
        bg.add(rb60);
        bg.add(rb90);
        bg.add(rb120);
        
        bgJenisUjian.add(rbUTS);
        bgJenisUjian.add(rbUAS);
        
        labelKdUjian.setBounds(30, 30, 130, 25);
        labelKdMatPel.setBounds(340, 30, 130, 25);
        labelNamaMatPel.setBounds(340, 70, 130, 25);
        labelKelas.setBounds(30, 70, 130, 25);
        labelTanggalUjian.setBounds(30, 110, 130, 25);
        labelDurasi.setBounds(30, 150, 130, 25);
        labelJam.setBounds(30, 190, 130, 25);
        labelRuang.setBounds(30, 230, 130, 25);
        labelJenisUjian.setBounds(30, 270, 130, 25);
        labelSemester.setBounds(30, 310, 130, 25);
        labelAjaran.setBounds(30, 350, 130, 25);
        
        txtFieldKdUjian.setBounds(170, 30, 150, 25);
        txtFieldKdMatPel.setBounds(480, 30, 80, 25);
        txtFieldNmPel.setBounds(480, 70, 150, 25);
        kelas.setBounds(170, 70, 100, 25);
        Calender.setBounds(170, 110, 150, 25);
        rb60.setBounds(170, 150, 50, 25);
        rb90.setBounds(220, 150, 50, 25);
        rb120.setBounds(270, 150, 50, 25);
        jamAwal.setBounds(170, 190, 60, 25);
        labelSamDeng.setBounds(235, 190, 60, 25);
        jamKeAkhir.setBounds(260, 190, 60, 25);
        ruang.setBounds(170, 230, 60, 25);
        rbUTS.setBounds(170, 270, 70, 25);
        rbUAS.setBounds(250, 270, 70, 25);
        Semester.setBounds(170, 310, 150, 25);
        thnAjaran.setBounds(170, 350, 150, 25);
        
        jScrollPaneTabelMahasiswa.setBounds(15, 30, 620, 110);

        buttonTampil.setBounds(70, 20, 80, 25);
        buttonTampil.addActionListener(this);
        buttonTampilUbah.setBounds(170, 20, 80, 25);
        buttonTampilUbah.addActionListener(this);
        buttonTampilHapus.setBounds(270, 20, 80, 25);
        buttonTampilHapus.addActionListener(this);
        buttonBatal.setBounds(370, 20, 80, 25);
        buttonBatal.addActionListener(this);
        buttonKeluar.setBounds(470, 20, 80, 25);
        buttonKeluar.addActionListener(this);
        btnCari.setBounds(570, 30, 60, 25);
        btnCari.addActionListener(this);

        panel1.setLayout(null);
        panel1.setBounds(30, 30, 650, 400);
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Jadwal Ujian"));

        panel2.setLayout(null);
        panel2.setBounds(30, 490, 650, 150);
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        
        panel3.setLayout(null);
        panel3.setBounds(30, 430, 650,60);
        panel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Action"));

        panel1.add(labelAjaran);
        panel1.add(labelDurasi);
        panel1.add(labelJam);
        panel1.add(labelJenisUjian);
        panel1.add(labelKdMatPel);
        panel1.add(labelKdUjian);
        panel1.add(labelKelas);
        panel1.add(labelNamaMatPel);
        panel1.add(labelRuang);
        panel1.add(labelSemester);
        panel1.add(labelTanggalUjian);  
        panel1.add(labelSamDeng);
        panel1.add(txtFieldKdMatPel);
        panel1.add(txtFieldKdUjian);
        panel1.add(txtFieldNmPel);
        panel1.add(kelas);
        panel1.add(Calender);
        panel1.add(rb120);
        panel1.add(rb60); 
        panel1.add(rb90); 
        panel1.add(rb120); 
        panel1.add(rbUTS); 
        panel1.add(rbUAS);
        panel1.add(jamAwal);
        panel1.add(jamKeAkhir);
        panel1.add(ruang);
        panel1.add(Semester);
        panel1.add(thnAjaran);
        
        panel2.add(jScrollPaneTabelMahasiswa);

        panel3.add(buttonTampil);
        panel3.add(buttonTampilUbah);
        panel3.add(buttonTampilHapus);
        panel3.add(buttonKeluar);
        panel3.add(buttonBatal);
        panel1.add(btnCari);
        
        this.setLayout(null);
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel3);
        this.show();
    }
    
   public void setAwalform(){
       txtFieldKdUjian.setText(manData.generateKdUjian());
       txtFieldKdMatPel.setText("");
       txtFieldNmPel.setEnabled(false);
       txtFieldNmPel.setText("");
       Calender.setDate(null);
       kelas.setSelectedIndex(0);
       thnAjaran.setSelectedIndex(0);
       jamAwal.setSelectedIndex(0);
       jamKeAkhir.setSelectedIndex(0);
       rb60.setSelected(true);
       rb90.setSelected(false);
       rb120.setSelected(false);
       rbUAS.setSelected(false);
       rbUTS.setSelected(true);
       buttonTampil.setEnabled(true);
       buttonBatal.setEnabled(false);
       buttonKeluar.setEnabled(true);
       buttonTampilHapus.setEnabled(false);
       buttonTampilUbah.setEnabled(false);
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
        Object data[] = new Object[13];
        try{
        con = new KoneksiDB().openConnection();
        st = con.createStatement();
        rs = st.executeQuery("Select * FROM ujian");
        while(rs.next()){
                data[0]= rs.getString("kd_ujian");
                data[1]= rs.getString("kd_pel");
                data[2]= rs.getString("nm_pel");
                data[3]= rs.getString("kls_ujian");
                data[4]= rs.getString("tgl_ujian");
                data[5]= rs.getString("durasi");
                data[6]= rs.getString("jam_awal");
                data[7]= rs.getString("jam_akhir");
                data[8]= rs.getString("ruang");
                data[9]= rs.getString("jenis_ujian");
                data[10]= rs.getString("thn_ujian");
                data[11]= rs.getString("smt_ujian");
                tableModel.addRow(data);

               }
        }catch (SQLException ex){
            System.out.println("Error di Query isi Table"+ex.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonTampil){
            String durasi="", jenUjian="";
             if(rb60.isSelected()){
                durasi= rb60.getText();
            }
             if(rb90.isSelected()){
                durasi= rb90.getText();
            }
             if(rb120.isSelected()){
                durasi= rb120.getText();
            }
            if(rbUAS.isSelected()){
                jenUjian=rbUAS.getText();
            } 
            if(rbUTS.isSelected()){
                jenUjian=rbUTS.getText();
            }
            String SQL = "INSERT INTO ujian VALUES ('"
                    +txtFieldKdUjian.getText()+"','"
                    +txtFieldKdMatPel.getText()+"','"
                    +txtFieldNmPel.getText()+"','"
                    +kelas.getSelectedItem()+"','"
                    +new SimpleDateFormat("yyyy-MM-dd").format(Calender.getDate())+"','"
                    +durasi+"','"
                    +jamAwal.getSelectedItem()+"','"
                    +jamKeAkhir.getSelectedItem()+"','"
                    +ruang.getSelectedItem()+"','"
                    +jenUjian+"','"
                    +thnAjaran.getSelectedItem()+"','"
                    +Semester.getSelectedItem()+"')";
         if(manData.QUERY(SQL)){
             JOptionPane.showMessageDialog(rootPane, "Data Berhasi Disimpan");
             isiTable();
             setAwalform(); 
        }else{
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Disimpan");
        }
}
                //Ubah
        if(e.getSource()==buttonTampilUbah){
            String durasi="", jenUjian="";
             if(rb60.isSelected()){
                durasi= rb60.getText();
            }
             if(rb90.isSelected()){
                durasi= rb90.getText();
            }
             if(rb120.isSelected()){
                durasi= rb120.getText();
            }
            if(rbUAS.isSelected()){
                jenUjian=rbUAS.getText();
            } 
            if(rbUTS.isSelected()){
                jenUjian=rbUTS.getText();
            }
            String SQL ="UPDATE ujian SET "
                    +"kd_pel='"+txtFieldKdMatPel.getText()+"',"
                    +"nm_pel='"+txtFieldNmPel.getText()+"',"
                    +"kls_ujian='"+kelas.getSelectedItem()+"',"
                    +"tgl_ujian='"+new SimpleDateFormat("yyyy-MM-dd").format(Calender.getDate())+"',"
                    +"durasi='"+durasi+"',"
                    +"jam_awal='"+jamAwal.getSelectedItem()+"',"
                    +"jam_akhir='"+jamKeAkhir.getSelectedItem()+"',"
                    +"ruang='"+ruang.getSelectedItem()+"',"
                    +"jenis_ujian='"+jenUjian+"',"
                    +"thn_ujian='"+thnAjaran.getSelectedItem()+"',"
                    +"smt_ujian='"+Semester.getSelectedItem()+"'"
                    +"WHERE kd_ujian = '"+txtFieldKdUjian.getText()+"'";
            
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
         String SQL ="DELETE FROM ujian WHERE kd_ujian='"+txtFieldKdUjian.getText()+"'";
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
             setAwalform();
             txtFieldKdUjian.setEnabled(true);
             txtFieldKdMatPel.setEnabled(true);
             txtFieldNmPel.setEnabled(true);
             isiTable(); 
         }
         if(e.getSource()==btnCari){
             PopUpCariPelajaran dialog = new PopUpCariPelajaran(new JFrame(), true);
             dialog.setVisible(true);
             txtFieldKdMatPel.setText(dialog.getKd_pel());
             txtFieldNmPel.setText(dialog.getNamaPel());
             txtFieldNmPel.setEnabled(false);
         }
    }

    public void mouseClicked(MouseEvent e) {
        txtFieldKdUjian.setEnabled(false);
        txtFieldNmPel.setEnabled(false);
        buttonTampil.setEnabled(false);
        buttonTampilUbah.setEnabled(true);
        buttonTampilHapus.setEnabled(true);
        buttonBatal.setEnabled(true);
        buttonKeluar.setEnabled(true);
            if(e.getSource()==tbl_mahasiswa){
                    if(e.getClickCount()==1){
                     int row = tbl_mahasiswa.getSelectedRow();
                     txtFieldKdUjian.setText(tableModel.getValueAt(row, 0).toString());
                     txtFieldKdMatPel.setText(tableModel.getValueAt(row, 1).toString());
                     txtFieldNmPel.setText(tableModel.getValueAt(row, 2).toString());
                     kelas.setSelectedItem(tableModel.getValueAt(row, 3).toString());
                   //  txtFieldTglUjian.setText(tableModel.getValueAt(row, 4).toString());
                    if(tableModel.getValueAt(row, 5).toString().equals("60")){
                        rb60.setSelected(true);
                    }
                    if(tableModel.getValueAt(row, 5).toString().equals("90")){
                        rb90.setSelected(true);
                    }
                    if(tableModel.getValueAt(row, 5).toString().equals("60")){
                        rb120.setSelected(true);
                    }
                     jamAwal.setSelectedItem(tableModel.getValueAt(row, 6).toString());
                     jamKeAkhir.setSelectedItem(tableModel.getValueAt(row, 7).toString());
                     ruang.setSelectedItem(tableModel.getValueAt(row,8).toString());
                     if(tableModel.getValueAt(row, 9).toString().equals("UTS")){
                        rbUTS.setSelected(true);
                    }
                     if(tableModel.getValueAt(row, 9).toString().equals("UAS")){
                        rbUAS.setSelected(true);
                    }
                     thnAjaran.setSelectedItem(tableModel.getValueAt(row, 10).toString());
                     Semester.setSelectedItem(tableModel.getValueAt(row, 11).toString());
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
