package bcu.cmp5332.librarysystem.gui;

import bcu.cmp5332.librarysystem.commands.DeletePatron;
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
* Deletes patron from the system.
* @author abdjory
*
*/

@SuppressWarnings("serial")
public class DeletePatronWindow extends JFrame implements ActionListener {
    private MainWindow mw;
    private JTextField patronIdText = new JTextField();
    private ImageIcon icon;
    private JButton deleteBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");

    public DeletePatronWindow(MainWindow mw) {
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
        topPanel.setLayout(new GridLayout(1, 2));
        topPanel.add(new JLabel("  Patron ID : "));
        topPanel.add(patronIdText);       

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
        	deletePatron();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }
    
    /**
     * Deletes a patron if the validation method returns false. Create object from "DeleteBook" class
     * then implement the execute method to delete a specific patron by id.
     */
    private void deletePatron() {
		if (!validateForm()) { 
			try {
				Command delPatron = new DeletePatron(Integer.parseInt(patronIdText.getText()));
				delPatron.execute(mw.getLibrary(), LocalDate.now());
				mw.displayPatrons();
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
    private boolean validateForm() {	    	
    	if(patronIdText.getText().length() == 0){
    		Library.showMessage("Patron id is mandatory!",false);
    		patronIdText.setBackground(Color.lightGray);
    		patronIdText.requestFocusInWindow();
    		return true;
    	}
    	return false;
    }
}
