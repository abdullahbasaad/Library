package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.AddBook;
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

@SuppressWarnings("serial")
public class AddBookWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField titleText = new JTextField();
    private JTextField authText = new JTextField();
    private JTextField pubDateText = new JTextField();
    private JTextField publisherText = new JTextField();
    private ImageIcon icon;

    private JButton addBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    public AddBookWindow(MainWindow mw) {
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

        setTitle("Add");
        icon = new ImageIcon("src/images/lib.png");
        setSize(300, 250);
        setIconImage(icon.getImage());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(5, 2));
        topPanel.add(new JLabel("  Title : "));
        topPanel.add(titleText);
        topPanel.add(new JLabel("  Author : "));
        topPanel.add(authText);
        topPanel.add(new JLabel("  Publishing year : "));
        topPanel.add(pubDateText);
        topPanel.add(new JLabel("  Publisher : "));
        topPanel.add(publisherText);

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

        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void addBook() {
        try {        	
        	if (!validateForm()) {
        		Command addBook = new AddBook(titleText.getText(), authText.getText(), pubDateText.getText(), publisherText.getText(), false);
                addBook.execute(mw.getLibrary(), LocalDate.now());
                mw.displayBooks();
                this.setVisible(false);
        	}
        } catch (LibraryException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Validates a form, all mandatory fields should be entered. Publisher year should be entered in a specific format, 
     * 4 digits, letters not allowed.
     * @return
     */
    private boolean validateForm(){	  
    	
    	if(titleText.getText().length() == 0){
    		Library.showMessage("Title is mandatory!",false);      	
    		titleText.setBackground(Color.lightGray);
    		titleText.requestFocusInWindow();
    		return true;
    	}

    	if(authText.getText().length() == 0){
    		Library.showMessage("Author is mandatory!",false);   
    		authText.setBackground(Color.lightGray);
    		authText.requestFocusInWindow();
    		return true;
    	}
    	
    	if(publisherText.getText().length() == 0){ 
    		Library.showMessage("Publisher is mandatory!",false);   
    		publisherText.setBackground(Color.lightGray);
    		publisherText.requestFocusInWindow();
    		return true;
    	}
    	
    	if(pubDateText.getText().length() > 0){
    		
    		if (pubDateText.getText().length() > 4) {
    			Library.showMessage("Invalid year number!",false);    
    			pubDateText.setBackground(Color.lightGray);
  			    pubDateText.requestFocusInWindow();
    			return true;
    		}else {
    			try {  
        		    Integer.parseInt(pubDateText.getText());  
        		    return false;
        		  } catch(NumberFormatException e){  
        			  Library.showMessage("Year should be number 4 digit!",false);          	   		  
        			  pubDateText.setBackground(Color.lightGray);
        			  pubDateText.requestFocusInWindow();
        		  }  
    		}
    		return true;
    	}   	
    	return false;
    }
}
