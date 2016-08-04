/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import control.ManipulasiData;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JInternalFrame;

/**
 *
 * @author BTI
 */
public class CetakLaporan extends JInternalFrame implements ActionListener{
    
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    
    JButton btnCetak = new JButton("CETAK");
    ManipulasiData ctrlMan ;
    
    public CetakLaporan(){
        super("LAPORAN");
        setSize(250, 250);
        setLocation(d.width / 2 - getWidth() / 2, d.height / 2 - getHeight() / 2);
        setClosable(true);
        setIconifiable(true);
        
        ctrlMan = new ManipulasiData();
        
        btnCetak.addActionListener(this);
        
        btnCetak.setBounds(70, 80, 100, 30);
        
        getContentPane().setLayout(null);
        getContentPane().add(btnCetak);
        
        show();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnCetak){
            ctrlMan.CetakLaporanAbsensi("reportAbsensi");
        }
    }
}
