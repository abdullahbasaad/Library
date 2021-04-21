package bcu.cmp5332.librarysystem.gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

/**
 * About class.
 * @author abdjory
 *
 */

@SuppressWarnings("serial")
public class AboutWindow  extends JFrame implements ActionListener {
    private MainWindow mw;
    private ImageIcon icon;
    
    public AboutWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }
        
        icon = new ImageIcon("src/images/lib.png"); 
        setTitle("About");
        setSize(500, 300);
        setIconImage(icon.getImage());
        setLocationRelativeTo(mw);
        setVisible(true);
        setResizable(false);
        
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/images/ASMAGROUP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(500, 300, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        setContentPane(new JLabel(imageIcon));        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }  
}
