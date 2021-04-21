package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.Command;
import bcu.cmp5332.librarysystem.commands.Return;
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
 * Returns class to return a book to the system.
 * @author abdjory
 *
 */

@SuppressWarnings("serial")
public class ReturnWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField patronIdText = new JTextField();
    private JTextField bookIdText = new JTextField();
    private ImageIcon icon;
    private JButton returnBtn = new JButton("Return");
    private JButton cancelBtn = new JButton("Cancel");

    public ReturnWindow(MainWindow mw) {
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

        setTitle("Return");
        mw.setResizable(false);
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
        bottomPanel.add(returnBtn);
        bottomPanel.add(cancelBtn);

        returnBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);
        setResizable(false);
    }
 
	@Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == returnBtn) {
            returnBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }
    
    /**
     * Returns a book if the validation method returns false. Create an object from "Return" class
     * then implement the execute method to return a book.
     */
    private void returnBook() {
        try {
        	if (!validateForm()) {
                Command returnBook = new Return(Integer.parseInt(bookIdText.getText()), Integer.parseInt(patronIdText.getText()));
                returnBook.execute(mw.getLibrary(), LocalDate.now());
                mw.displayBooks();
                this.setVisible(false);
        	}    
        } catch (NumberFormatException | LibraryException e){
        	Library.showMessage("Invalid number!",false);		} 
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
