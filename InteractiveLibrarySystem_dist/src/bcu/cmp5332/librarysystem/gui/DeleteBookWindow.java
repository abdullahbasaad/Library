package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.DeleteBook;
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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Deletes a book from the system.
 * @author abdjory
 *
 */

@SuppressWarnings("serial")
public class DeleteBookWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField bookIdText = new JTextField();
    private ImageIcon icon;
    private JButton deleteBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");

    public DeleteBookWindow(MainWindow mw) {
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

        setTitle("Delete");
        icon = new ImageIcon("src/images/lib.png");
        setIconImage(icon.getImage());
        
        setSize(320, 100);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(1, 3));
        topPanel.add(new JLabel("  Book ID : "));
        topPanel.add(bookIdText);
        
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(deleteBtn);
        bottomPanel.add(cancelBtn);

        deleteBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteBtn) {
            deleteBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }

    /**
     * Deletes a book if the validation operation return false. Create object from "DeleteBook" class
     * then implements the execute method to delete a specific book by id.
     */
    private void deleteBook() {
    	if (!validateForm()) {
            try {
            	int bookid = Integer.parseInt(bookIdText.getText());    
            	Command delBook = new DeleteBook(bookid);
            	delBook.execute(mw.getLibrary(), LocalDate.now());
            	mw.displayBooks();
                this.setVisible(false);  
			} catch (NumberFormatException | LibraryException e){
				Library.showMessage("Invalid number!",false);
			} 
    	}   
    }
    
    /**
     * Validates a form, all mandatory fields should be entered. It returns false if the validation successes 
     * and true if unsuccessful. 
     * @return boolean value.
     */
    private boolean validateForm(){	    	
    	if(bookIdText.getText().length() == 0){
    		Library.showMessage("Book id is mandatory!",false);      
    		bookIdText.setBackground(Color.lightGray);
    		bookIdText.requestFocusInWindow();
    		return true;
    	}  
    	return false;
    }

}
