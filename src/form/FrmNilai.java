/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package form;

import control.KoneksiDB;
import control.ManipulasiData;
import formPopUp.PopUpCariSiswa;
import formPopUp.PopUpCariUjian;
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
import javax.swing.JTabbedPane;
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
public class FrmNilai extends JInternalFrame implements ActionListener, MouseListener {
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panelBtn = new JPanel();
    
    JTabbedPane tab;
    
    JLabel labelAjaran = new JLabel("Tahun Ajaran:");
    JLabel labelSemester = new JLabel("Smester");
    JLabel labelKelas = new JLabel("Kelas");
    JLabel labelNilaiDriUjian = new JLabel("Jenis Ujian");
    JLabel labelKodeUjian = new JLabel("Kode Ujian");
    JLabel labelNmPel = new JLabel("Nama Pelajaran");
    JLabel labelPelajaran = new JLabel("Pelajaran");
    JLabel labelNis = new JLabel("NIS");
    JLabel labelSiswa = new JLabel("Siswa");
    JLabel labelNilai = new JLabel("Nilai");
    
    String[] listthnAjaran = new String[]{"2010/2011","2011/2012","2012/2013","2013/2014"};
    JComboBox thnAjaran = new JComboBox(listthnAjaran);
    String[] listSemester = new String[]{"Gasal","Genap"};
    JComboBox Semester = new JComboBox(listSemester);
    String[] listKelas = new String[]{"IX-1","IX-2","IX-3"};
    JComboBox kelas = new JComboBox(listKelas);
   
    JRadioButton rbUTS = new JRadioButton("UTS");
    JRadioButton rbUAS = new JRadioButton("UAS");
   
    ButtonGroup bg = new ButtonGroup();
    
    TextField txtNilai = new TextField();
    TextField txtKodeUjian = new TextField();
    TextField txtnamaPel = new TextField();
    TextField txtNis = new TextField();
    TextField txtNmSiswa = new TextField();
//Button
    JButton buttonTampil = new JButton("Simpan");
    JButton buttonTampilUbah = new JButton("Ubah");
    JButton buttonTampilHapus =new JButton("Hapus");
    JButton buttonKeluar  = new JButton("Keluar");
    JButton buttonBatal = new JButton("Batal");
    JButton btnCariUjian = new JButton("Cari");
    JButton btnCariSiswa = new JButton("Cari");
    JTable tbl_mahasiswa = new JTable();
    JScrollPane jScrollPaneTabelMahasiswa = new JScrollPane();
    DefaultTableModel tableModel = new  DefaultTableModel(new Object [][]{}, new String[]{"Tahun Ajaran", "Semester","kelas","Jenis Ujian","Kode Ujian","Nama Pelajaran","nis","Nama Siswa","nilai"});
    
    ManipulasiData manData;
    public FrmNilai(){
        this.setTitle("Form Nilai");
        this.setSize(600, 700);
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
        bg.add(rbUTS);
        bg.add(rbUAS);
        
        labelAjaran.setBounds(15, 30, 150, 25);
        labelSemester.setBounds(15, 70, 150, 25);
        labelKelas.setBounds(15, 110, 150, 25);
        labelNilaiDriUjian.setBounds(15, 150, 150, 25);
        labelKodeUjian.setBounds(15, 190, 150, 25);
        labelNmPel.setBounds(15, 230, 150, 25);
        labelNis.setBounds(15, 270, 150, 25);
        labelSiswa.setBounds(15, 310, 150, 25);
        labelNilai.setBounds(15, 350, 150, 25);
        
        thnAjaran.setBounds(170, 30, 150, 25);
        Semester.setBounds(170, 70, 150, 25);
        kelas.setBounds(170, 110, 150, 25);
        rbUTS.setBounds(170, 150, 70, 25);
        rbUAS.setBounds(250, 150, 70, 25);
        txtKodeUjian.setBounds(170, 190, 80, 25);
        txtnamaPel.setBounds(170, 230, 150, 25);
        txtNis.setBounds(170, 270, 80, 25);
        txtNmSiswa.setBounds(170, 310, 150, 25);
        txtNilai.setBounds(170, 340, 80, 25);

        jScrollPaneTabelMahasiswa.setBounds(15, 30, 490, 150);

        buttonTampil.setBounds(10, 30, 80, 25);
        buttonTampil.addActionListener(this);
        buttonTampilUbah.setBounds(10, 70, 80, 25);
        buttonTampilUbah.addActionListener(this);
        buttonTampilHapus.setBounds(10, 110, 80, 25);
        buttonTampilHapus.addActionListener(this);
        buttonBatal.setBounds(10, 150, 80, 25);
        buttonBatal.addActionListener(this);
        buttonKeluar.setBounds(10, 190, 80, 25);
        buttonKeluar.addActionListener(this);
        
        btnCariSiswa.setBounds(260, 270, 60, 25);
        btnCariSiswa.addActionListener(this);
        btnCariUjian.setBounds(260, 190, 60, 25);
        btnCariUjian.addActionListener(this);

        panel1.setLayout(null);
        panel1.setBounds(30, 30, 400, 430);
        panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Nilai"));

        panel2.setLayout(null);
        panel2.setBounds(30, 460, 520, 200);
        panel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data"));
        
        panelBtn.setLayout(null);
        panelBtn.setBounds(450, 30, 100, 430);
        panelBtn.setBorder(javax.swing.BorderFactory.createTitledBorder("Action"));

        panel1.add(labelAjaran);
        panel1.add(labelSemester);
        panel1.add(labelKelas);
        panel1.add(labelNilaiDriUjian);
        panel1.add(labelKodeUjian);
        panel1.add(labelNmPel);
        panel1.add(labelNis);
        panel1.add(labelSiswa);
        panel1.add(labelNilai);
        
        panel1.add(thnAjaran);
        panel1.add(Semester);
        panel1.add(txtNilai);
        panel1.add(kelas);
        panel1.add(rbUAS);
        panel1.add(rbUTS);
        panel1.add(txtKodeUjian);
        panel1.add(txtnamaPel);
        panel1.add(txtNis);
        panel1.add(txtNmSiswa);
        panel1.add(txtNilai);
        
        panel2.add(jScrollPaneTabelMahasiswa);

        panelBtn.add(buttonTampil);
        panelBtn.add(buttonTampilUbah);
        panelBtn.add(buttonTampilHapus);
        panelBtn.add(buttonKeluar);
        panelBtn.add(buttonBatal);
        
        panel1.add(btnCariSiswa);
        panel1.add(btnCariUjian);
        
        this.setLayout(null);
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panelBtn);
        this.show();

    }

   public void setAwalform(){
        buttonTampil.setEnabled(true);
        buttonTampilUbah.setEnabled(false);
        buttonTampilHapus.setEnabled(false);
        buttonBatal.setEnabled(false);
        buttonKeluar.setEnabled(true);
        
        txtnamaPel.setEnabled(false);
        txtNmSiswa.setEnabled(false);
        thnAjaran.setSelectedIndex(0);
        Semester.setSelectedItem(0);
        kelas.setSelectedItem(0);
        rbUTS.setSelected(true);
        rbUAS.setSelected(false);
        txtKodeUjian.setText("");
        txtnamaPel.setEnabled(true);
        txtnamaPel.setText("");
        txtNis.setText("");
        txtNmSiswa.setEnabled(true);
        txtNmSiswa.setText("");
        txtNilai.setText("");
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
        Object data[] = new Object[9];
        try{
        con = new KoneksiDB().openConnection();
        st = con.createStatement();
        rs = st.executeQuery("Select * FROM nilai");
        while(rs.next()){
         data[0]= rs.getString("thn_ajaran");
         data[1]= rs.getString("semester");
         data[2]= rs.getString("kelas");
         data[3]= rs.getString("Ujian_dari");
         data[4]= rs.getString("kd_ujian");
         data[5]= rs.getString("nm_pel");
         data[6]= rs.getString("nis");
         data[7]= rs.getString("nm_siswa");
         data[8]= rs.getString("nilai");
         tableModel.addRow(data);
        }
        }catch (SQLException ex){
            System.out.println("Error di Query isi Table"+ex.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonTampil){
            String jenisUjian="";
            if(rbUAS.isSelected()){
                jenisUjian= rbUAS.getText();
            }else{
                jenisUjian=rbUTS.getText();
            }

            String SQL = "INSERT INTO nilai VALUES ('"
                    +thnAjaran.getSelectedItem()+"','"
                    +Semester.getSelectedItem()+"','"
                    +kelas.getSelectedItem()+"','"
                    +jenisUjian+"','"
                    +txtKodeUjian.getText()+"','"
                    +txtnamaPel.getText()+"','"
                    +txtNis.getText()+"','"
                    +txtNmSiswa.getText()+"','"
                    +txtNilai.getText()+"')";
            
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
            String jenisUjian="";
            if(rbUAS.isSelected()){
                jenisUjian= rbUAS.getText();
            }else{
                jenisUjian=rbUTS.getText();
            }
            String SQL ="UPDATE nilai SET "
                + "thn_ajaran = '"+thnAjaran.getSelectedItem()+"',"
                + "semester = '"+Semester.getSelectedItem()+"',"
                + "kelas = '"+kelas.getSelectedItem()+"',"
                + "ujian_dari = '"+jenisUjian+"',"
                + "nm_pel = '"+txtnamaPel.getText()+"',"
                + "nm_siswa = '"+txtNmSiswa.getText()+"',"
                + "nilai = '"+txtNilai.getText()+"'"
                +"WHERE kd_ujian = '"+txtKodeUjian.getText()+"' && nis ='"+txtNis.getText()+"'";
            
            if(manData.QUERY(SQL)){
                JOptionPane.showMessageDialog(rootPane, "Data Berhasi Diubah");
                isiTable();
                setAwalform();

           }else{
               JOptionPane.showMessageDialog(rootPane, "Data Gagal Diubah");
           }
        }
         if(e.getSource()==buttonTampilHapus){
         String SQL ="DELETE FROM nilai WHERE kd_ujian = '"+txtKodeUjian.getText()+"' && nis ='"+txtNis.getText()+"'";
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
         }
         if(e.getSource()==btnCariUjian){
             PopUpCariUjian dialog = new PopUpCariUjian(new JFrame(), true);
             dialog.setVisible(true);
             txtKodeUjian.setText(dialog.getvKdUjian());
             txtnamaPel.setText(dialog.getvNmMatpel());  
             txtnamaPel.setEnabled(false);
         }
         if(e.getSource()==btnCariSiswa){
             PopUpCariSiswa dialog = new PopUpCariSiswa(new JFrame(), true);
             dialog.setVisible(true);
             txtNis.setText(dialog.getNis());
             txtNmSiswa.setText(dialog.getvNamaSiswa());
             txtNmSiswa.setEnabled(false);
         }            
    }

    public void mouseClicked(MouseEvent e) {
        buttonTampil.setEnabled(false);
        buttonTampilUbah.setEnabled(true);
        buttonTampilHapus.setEnabled(true);
        buttonBatal.setEnabled(true);
        buttonKeluar.setEnabled(true);
        txtnamaPel.setEnabled(false);
        txtNmSiswa.setEnabled(false);
        // TODO add your handling code here:
           if(e.getSource()==tbl_mahasiswa){
           if(e.getClickCount()==1){
                int row = tbl_mahasiswa.getSelectedRow();
                thnAjaran.setSelectedItem(tableModel.getValueAt(row, 0).toString());
                Semester.setSelectedItem(tableModel.getValueAt(row, 1).toString());
                kelas.setSelectedItem(tableModel.getValueAt(row, 2).toString());
                if(tableModel.getValueAt(row, 3).toString().equals("UTS")){
                     rbUTS.setSelected(true);
                 }else{
                     rbUAS.setSelected(true);
                 }
                 txtKodeUjian.setText(tableModel.getValueAt(row, 4).toString());
                 txtnamaPel.setText(tableModel.getValueAt(row, 5).toString());
                 txtNis.setText(tableModel.getValueAt(row, 6).toString());
                 txtNmSiswa.setText(tableModel.getValueAt(row, 7).toString());
                 txtNilai.setText(tableModel.getValueAt(row, 8).toString());
          
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
