package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.Renew;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Renews class to renew a borrowed book for a patron.
 * @author abdjory
 *
 */

@SuppressWarnings("serial")
public class RenewWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField patronIdText = new JTextField();
    private JTextField bookIdText = new JTextField();
    private ImageIcon icon;

    private JButton renewBtn = new JButton("Renew");
    private JButton cancelBtn = new JButton("Cancel");

    public RenewWindow(MainWindow mw) {
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

        setTitle("Renew");
        icon = new ImageIcon("src/images/lib.png");
        setIconImage(icon.getImage());
        
        setSize(300, 120);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 2));
        topPanel.add(new JLabel("  Patron ID : "));
        topPanel.add(patronIdText);
        topPanel.add(new JLabel("  Book ID : "));
        topPanel.add(bookIdText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(renewBtn);
        bottomPanel.add(cancelBtn);

        renewBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == renewBtn) {
            renewBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }
    
    /**
     * Renews a book if the validation method returns false. Create object from "Renew" class
     * then implement the execute method to renew a specific book for a patron.
     */
    private void renewBook() {
        try {
        	if (!validateForm()) {
                Command renewBook = new Renew(Integer.parseInt(bookIdText.getText()), Integer.parseInt(patronIdText.getText()));
                renewBook.execute(mw.getLibrary(), LocalDate.now());
                mw.displayBooks();
                this.setVisible(false);
        	}    
        } catch (NumberFormatException | LibraryException e){
        	Library.showMessage("Invalid number!",false);
		} 
    }
    
    /**
     * Validates a form, all mandatory fields should be entered. It returns false if the validation successes 
     * and true if unsuccessful. 
     * @return boolean's value.
     */
    private boolean validateForm(){	    	
    	if(patronIdText.getText().length() == 0){
    		Library.showMessage("Patron is mandatory!",false);
    		patronIdText.setBackground(Color.lightGray);
    		patronIdText.requestFocusInWindow();
    		return true;
    	}

    	if(bookIdText.getText().length() == 0){
    		Library.showMessage("Book is mandatory!",false);
    		bookIdText.setBackground(Color.lightGray);
    		bookIdText.requestFocusInWindow();
    		return true;
    	}
	  
    	return false;
    }

}
