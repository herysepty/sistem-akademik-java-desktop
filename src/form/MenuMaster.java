/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*

 */
package form;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author HERY
 */
public class MenuMaster extends JFrame implements ActionListener{
    
    JPanel panel1 = new JPanel();
    
    JLabel labelJudul = new JLabel("SMK TRIGUNA UTAMA");
    JMenuBar bar;
    JMenu menuMaster, menuAkademik, menuLaporan;
    JMenuItem itemMenuSiswa,itemMenuPelajaran, itemMenuPelanggaran, itemMenuLaporanMhs, itemMenuJadwal,itemMenuUjian, itemMenuAbsensi, itemMenuNilai, itemMenuKasus;
    JDesktopPane desktopPane = new JDesktopPane();
    JInternalFrame internalMahasiswa, internalEntryNilai, internalLaporanMhs;
    
    public MenuMaster(){
        super("SMK TRIGUNA UTAMA");
        setSize(400, 200);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        labelJudul.setBounds(15, 30, 150, 25);
        
        panel1.setLayout(null);
        panel1.setBounds(30, 30, 520, 250);
        //panel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Data Pelajaran"));
        
        //mendeklarasika menu
        
        bar= new JMenuBar();
        setJMenuBar(bar);
        add(desktopPane);
        
        menuMaster = new JMenu("Master");
        menuAkademik = new JMenu("Akademik");
        menuLaporan = new JMenu("Laporan");
        
        itemMenuSiswa = new JMenuItem("Siswa");
        itemMenuPelajaran = new JMenuItem("Mata  Pelajaran");
        itemMenuPelanggaran = new JMenuItem("Point Pelanggaran");
        itemMenuLaporanMhs = new JMenuItem("Laporan Absensi");
        itemMenuJadwal = new JMenuItem("Jadwal Pelajaran");
        itemMenuUjian = new JMenuItem("Jadwal Ujian");
        itemMenuAbsensi = new JMenuItem("Absensi");
        itemMenuNilai = new JMenuItem("Nilai");
        itemMenuKasus = new JMenuItem("Lakukan Pelanggaran");
        
        menuMaster.add(itemMenuSiswa);
        menuMaster.add(itemMenuPelajaran);
        menuMaster.add(itemMenuPelanggaran);
        menuAkademik.add(itemMenuAbsensi);
        menuAkademik.add(itemMenuJadwal);
        menuAkademik.add(itemMenuUjian);
        menuAkademik.add(itemMenuNilai);
        menuAkademik.add(itemMenuKasus);
        menuLaporan.add(itemMenuLaporanMhs);
        
        
        bar.add(menuMaster);
        bar.add(menuAkademik);
        bar.add(menuLaporan);
        
        panel1.add(labelJudul);
        
        //Listener
        itemMenuSiswa.addActionListener(this);
        itemMenuPelajaran.addActionListener(this);
        itemMenuPelanggaran.addActionListener(this);
        itemMenuJadwal.addActionListener(this);
        itemMenuUjian.addActionListener(this);
        itemMenuAbsensi.addActionListener(this);
        itemMenuNilai.addActionListener(this);
        itemMenuKasus.addActionListener(this);
        itemMenuLaporanMhs.addActionListener(this);
        show();
    }
    
    public static void main (String args[]){
        try{
            javax.swing.UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
            
        }catch (Exception e){
            
        }
        new MenuMaster();
    }
    

    public void actionPerformed(ActionEvent e) {
    if (e.getSource()==itemMenuSiswa){
            internalMahasiswa = new FrmSiswa();
            desktopPane.add(internalMahasiswa);
         }    
        if (e.getSource()==itemMenuPelajaran){
            internalMahasiswa = new FrmPelajaran();
            desktopPane.add(internalMahasiswa);
         }    
     if (e.getSource()==itemMenuPelanggaran){
            internalMahasiswa = new FrmEntryKasus();
            desktopPane.add(internalMahasiswa);
         } 
          if (e.getSource()==itemMenuJadwal){
            internalMahasiswa = new FrmJadwal();
            desktopPane.add(internalMahasiswa);
         } 
       if (e.getSource()==itemMenuUjian){
            internalMahasiswa = new FrmUjian();
            desktopPane.add(internalMahasiswa);
         } 
       if (e.getSource()==itemMenuAbsensi){
            internalMahasiswa = new FrmAbsensi();
            desktopPane.add(internalMahasiswa);
         }
       if (e.getSource()==itemMenuNilai){
            internalMahasiswa = new FrmNilai();
            desktopPane.add(internalMahasiswa);
         }
       if (e.getSource()==itemMenuKasus){
            internalMahasiswa = new FrmKasus();
            desktopPane.add(internalMahasiswa);
         }
       if (e.getSource()==itemMenuLaporanMhs){
            internalLaporanMhs = new CetakLaporan();
            desktopPane.add(internalLaporanMhs);
         }
    }
    
}
