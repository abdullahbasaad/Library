package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.AddPatron;
import bcu.cmp5332.librarysystem.commands.Command;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Adds a new patron.
 * @author abdjory
 *
 */
@SuppressWarnings("serial")
public class AddPatronWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();
    private JTextField emailText = new JTextField();
    private ImageIcon icon;

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    public AddPatronWindow(MainWindow mw) {
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
        setTitle("Add");

        setSize(300, 200);
        setIconImage(icon.getImage());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("  Name : "));
        topPanel.add(nameText);
        topPanel.add(new JLabel("  Phone : "));
        topPanel.add(phoneText);
        topPanel.add(new JLabel("  Email : "));
        topPanel.add(emailText);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(addBtn);
        bottomPanel.add(cancelBtn);

        addBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addPatron();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }
    
    /**
     * Adds a patron if the validation operation return false. Create object from "AddPatron" class
     * then implement the execute method to add a patron .
     */
    private void addPatron() {
        try {

            if (!validateForm()) {
            	Command addPatron = new AddPatron(nameText.getText(), phoneText.getText(), emailText.getText(), false);
                addPatron.execute(mw.getLibrary(), LocalDate.now());
                mw.displayPatrons();
                this.setVisible(false);
            }
            
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Validates a form, all mandatory fields should be entered. It returns false if the validation successes 
     * and true if unsuccessful. 
     * @return boolean value.
     */
    private boolean validateForm(){	    	
    	if(nameText.getText().length() == 0){
    		Library.showMessage("Patron name is mandatory!",false);              	
    		nameText.setBackground(Color.lightGray);
    		nameText.requestFocusInWindow();
    		return true;
    	}

    	if(phoneText.getText().length() == 0){
    		Library.showMessage("Patron's phone is mandatory!",false);           	   		  
    		phoneText.setBackground(Color.lightGray);
    		phoneText.requestFocusInWindow();
    		return true;
    	}
	  
    	return false;
    }

}
