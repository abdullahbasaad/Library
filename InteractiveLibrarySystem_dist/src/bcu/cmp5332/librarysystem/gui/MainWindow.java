package bcu.cmp5332.librarysystem.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import bcu.cmp5332.librarysystem.data.BookDataManager;
import bcu.cmp5332.librarysystem.data.LoanDataManager;
import bcu.cmp5332.librarysystem.data.PatronDataManager;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;

/**
 * DEveloped the class to include many functions to display different information.
 * @author abdjory
 *
 */

@SuppressWarnings("serial")
public class MainWindow extends JFrame implements ActionListener {	
	final static String RESOURCE1 = "./resources/data/book_trans.txt";
	final static String RESOURCE2 = "./resources/data/patron_trans.txt";
	final static String RESOURCE3 = "./resources/data/loan_trans.txt";
	
    private JMenuBar menuBar;
    
    private JMenu adminMenu;
    private JMenu booksMenu;
    private JMenu membersMenu;
    private JMenu historyMenu;
    private JMenu aboutMenu;
    
    private ImageIcon icon;
    private ImageIcon exitIcon;
    private ImageIcon viewIcon;
    private ImageIcon addBookIcon;
    private ImageIcon deleteBookIcon;
    private ImageIcon issueBookIcon;
    private ImageIcon returnBookIcon;
    private ImageIcon renewBookIcon;
    private ImageIcon deletePatronIcon;
    private ImageIcon addPatronIcon;
    private ImageIcon viewBorrowedIcon;
    private ImageIcon historyIcon;
    private ImageIcon booksOnLoanIcon;
    private ImageIcon aboutIcon;
    
    private JMenuItem adminExit;
    private JMenuItem booksView;
    private JMenuItem booksAdd;
    private JMenuItem booksDel;	
    private JMenuItem booksIssue;
    private JMenuItem booksReturn;
    private JMenuItem booksRenew;
    private JMenuItem memView;
    private JMenuItem memAdd;
    private JMenuItem memDel;   
    private JMenuItem hisView;
    private JMenuItem patronBooks;
    private JMenuItem borrowedBooks;
    private JMenuItem aboutItem;
    
    private Library library;

    public MainWindow(Library library) {
        initialize();
        this.library = library;
    }
    
    public Library getLibrary() {
        return library;
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
        exitIcon = new ImageIcon("src/images/exit.png");
        viewIcon = new ImageIcon("src/images/view.png");
        addBookIcon = new ImageIcon("src/images/add_book.png");
        deleteBookIcon = new ImageIcon("src/images/delete_book.png");
        issueBookIcon = new ImageIcon("src/images/issue_book.png");
        returnBookIcon = new ImageIcon("src/images/return_book.png");
        renewBookIcon = new ImageIcon("src/images/renew_book.png");
        addPatronIcon = new ImageIcon("src/images/add_patron.png");
        deletePatronIcon = new ImageIcon("src/images/delete_patron.png");
        viewBorrowedIcon = new ImageIcon("src/images/view_borrowed.png");
        historyIcon = new ImageIcon("src/images/history.png");    
        booksOnLoanIcon = new ImageIcon("src/images/borrowed_books.png");
        aboutIcon = new ImageIcon("src/images/about.png");
        
        
        setTitle("Library Management System");     
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);
        adminExit.setIcon(exitIcon);
        adminExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));

        // adding booksMenu menu and menu items
        booksMenu = new JMenu("Books");
        menuBar.add(booksMenu);

        booksView = new JMenuItem("View");
        booksView.setIcon(viewIcon);
        booksView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        booksView.addActionListener(this);
        
        booksAdd = new JMenuItem("Add");
        booksAdd.setIcon(addBookIcon);
        booksAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        booksAdd.addActionListener(this);      
        booksDel = new JMenuItem("Delete");
        booksDel.setIcon(deleteBookIcon);
        booksDel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        booksDel.addActionListener(this);      
        booksIssue = new JMenuItem("Issue");
        booksIssue.setIcon(issueBookIcon);
        booksIssue.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        booksIssue.addActionListener(this);       
        booksReturn = new JMenuItem("Return");
        booksReturn.setIcon(returnBookIcon);
        booksReturn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
        booksReturn.addActionListener(this);
        booksRenew  = new JMenuItem("Renew");
        booksRenew.setIcon(renewBookIcon);
        booksRenew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        booksRenew.addActionListener(this);
        
        borrowedBooks  = new JMenuItem("Book Details");
        borrowedBooks.setIcon(booksOnLoanIcon);
        borrowedBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,ActionEvent.CTRL_MASK));
        borrowedBooks.addActionListener(this);
        
        booksMenu.add(booksView);
        booksMenu.addSeparator();
        booksMenu.add(booksAdd);
        booksMenu.add(booksDel);
        booksMenu.add(booksIssue);
        booksMenu.add(booksReturn);
        booksMenu.add(booksRenew);
        booksMenu.add(borrowedBooks);
        
        // adding membersMenu menu and menu items
        membersMenu = new JMenu("Members");
        menuBar.add(membersMenu);

        memView = new JMenuItem("View");
        memView.setIcon(viewIcon);
        memView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.ALT_MASK));
        memDel = new JMenuItem("Delete");
        memAdd = new JMenuItem("Add");
        memAdd.setIcon(addPatronIcon);
        memAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.ALT_MASK));
        memDel = new JMenuItem("Delete");
        memDel.setIcon(deletePatronIcon);
        memDel.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.ALT_MASK));
        patronBooks = new JMenuItem("Patron Details");
        patronBooks.setIcon(viewBorrowedIcon);
        patronBooks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.ALT_MASK));
        membersMenu.add(memView);
        membersMenu.addSeparator();
        membersMenu.add(memAdd);
        membersMenu.add(memDel);
        membersMenu.add(patronBooks);

        memView.addActionListener(this);
        memAdd.addActionListener(this);
        memDel.addActionListener(this);
        patronBooks.addActionListener(this);
        
        historyMenu = new JMenu("History");
        menuBar.add(historyMenu);
        hisView = new JMenuItem("Loans History");       
        historyMenu.add(hisView);
        hisView.addActionListener(this);
        hisView.setIcon(historyIcon);
        hisView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.ALT_MASK));
        
        aboutMenu = new JMenu("About");
        menuBar.add(aboutMenu);
        aboutItem = new JMenuItem("About");
        aboutMenu.add(aboutItem);
        aboutItem.addActionListener(this);
        aboutItem.setIcon(aboutIcon);
        aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T,ActionEvent.CTRL_MASK));
        
        setSize(1000, 500);
        setIconImage(icon.getImage());
        
        setVisible(true);
        setResizable(false);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }	

        
    
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {
        	storeTempData();
        	library.clearTransFiles();
            System.exit(0);
        } else if (ae.getSource() == booksView) {
            displayBooks();
            
        } else if (ae.getSource() == booksAdd) {
            new AddBookWindow(this);
            
        } else if (ae.getSource() == booksDel) {
        	new DeleteBookWindow(this);
            
        } else if (ae.getSource() == booksIssue) {
        	new IssueWindow(this);
            
        } else if (ae.getSource() == booksReturn) {
        	new ReturnWindow(this);
        
        } else if (ae.getSource() == booksRenew) {
        	new RenewWindow(this);
        
        } else if (ae.getSource() == borrowedBooks) {
        	booksOnLoan();
        	
        } else if (ae.getSource() == memView) {
            displayPatrons();
            
        } else if (ae.getSource() == memAdd) {
            new AddPatronWindow(this);
            
        } else if (ae.getSource() == memDel) {
            new DeletePatronWindow(this);
        
        } else if (ae.getSource() == memDel) {
            patronBooks();
         
        } else if (ae.getSource() == patronBooks) {
            patronBooks();
            
        } else if (ae.getSource() == hisView) {
        	displayLonsHistory();
        
	    } else if (ae.getSource() == aboutItem) {
	    	new AboutWindow(this);
	    } 
    }
    
    /**
     * Calls a "storeTempData" to store all data in the files.
     * this method will fire when the user uses a window's button to close the window. 
     */
    @Override
    public void dispose() {
    	storeTempData();       
        library.clearTransFiles();
        super.dispose();
        System.exit(0);
    }
    
    /**
     * Calls store method to store books, loans and patrons' files.
     */
    public void storeTempData() {
    	BookDataManager bdm   = new BookDataManager();
        LoanDataManager ldm   = new LoanDataManager();
        PatronDataManager pdm = new PatronDataManager();
		try {
			bdm.storeData(library);
			ldm.storeData(library);
			pdm.storeData(library);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Displays all books except the deleted them.
     */
	public void displayBooks() {
        List<Book> booksList = library.getBooks();
        TableColumn tColumn1,tColumn2;
        // headers for the table
        String[] columns = new String[]{"Title", "Author", "Pubisher year", "Publisher", "Status","Patron","Due date"};
        
        Object[][] data = new Object[booksList.size()][7];
        int j = 0;
        for (int i = 0; i < booksList.size(); i++) {
            Book book = booksList.get(i);
            if (!book.getHideFlag()) {
            	data[j][0] = "  "+book.getId()+" - "+book.getTitle();
                data[j][1] = "  "+book.getAuthor();
                data[j][2] = "  "+book.getPublicationYear();
                data[j][3] = "  "+book.getPublisher();    
                data[j][4] = "  "+book.getStatus();
                
                if (book.isOnLoan()) {
                	data[j][5] =book.getLoan().getPatron().getId()+"-"+ book.getLoan().getPatron().getName();
                	data[j][6] = book.getDueDate();
                }else {
                	data[j][5] = null;
                	data[j][6] = null;
                }
                
                j += 1;
            } 
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
    	    @Override
    	    public boolean isCellEditable(int row, int column) {
    	        return false;
    	    }
    	};
    	
        JTable table = new JTable(data, columns);
        table.setModel(tableModel);
        
        tColumn1 = table.getColumnModel().getColumn(5);
        tColumn2 = table.getColumnModel().getColumn(6);
        tColumn1.setCellRenderer(new ColumnColorRenderer(Color.lightGray, Color.red));
        tColumn2.setCellRenderer(new ColumnColorRenderer(Color.lightGray, Color.red));
        table.setForeground(Color.BLUE);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();
    }	
    
    /**
     * Displays the history data for all transactions.
     */
	public void displayLonsHistory() {  	
    	final String RESOURCE = "./resources/data/loans_history.txt";
    	Path path = Paths.get("./resources/data/loans_history.txt");
    	String[] columns = new String[]{"Transaction type", "Patron", "Book", "Start date", "Due date"};
    	int lines = 0;
    	
		try {
			lines = (int) Files.lines(path).count();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
    	Object[][] data = new Object[lines][5];
    		
    	try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int i = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(",", -1);
                String opr = properties[0];
                int patid = Integer.parseInt(properties[1]);
                int bokid = Integer.parseInt(properties[2]);
                String StartDate = properties[3];
                String DueDate = properties[4];
              
            	data[i][0] = opr;
                try {
					data[i][1] = library.getPatronByID(patid).getName();
				} catch (LibraryException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                try {
					data[i][2] = library.getBookByID(bokid).getTitle();
				} catch (LibraryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                data[i][3] = StartDate;
                data[i][4] = DueDate;
                                
                i += 1;
            } 
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
    	
    	DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
       	    @Override
       	    public boolean isCellEditable(int row, int column) {
       	        return false;
       	    }
       	};	   	
    	    
        JTable table = new JTable(data, columns);
        table.setModel(tableModel);
        table.setForeground(Color.BLUE);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();   	
    }	
    
	/**
	 * Displays all patrons' details except the deleted them.
	 */
    public void displayPatrons() {
        List<Patron> patronsList = library.getPatrons();
        // headers for the table
        String[] columns = new String[]{"Name", "Phone", "Email", "No of books"};
        int j = 0;
        Object[][] data = new Object[patronsList.size()][4];
        for (int i = 0; i < patronsList.size(); i++) {
            Patron patron = patronsList.get(i);
            if (!patron.getHideFlag()) {
            	data[j][0] = "  "+patron.getId()+" - "+patron.getName();
                data[j][1] = "  "+patron.getPhone();
                data[j][2] = "  "+patron.getEmail();
                data[j][3] = "  "+booksboorows(patron);
                
                j += 1;
            } 
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
    	    @Override
    	    public boolean isCellEditable(int row, int column) {
    	        return false;
    	    }
    	};
    	      
        JTable table = new JTable(data, columns);
        table.setModel(tableModel);
        table.setForeground(Color.BLUE);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();  
    }
    
    /**
     * Returns how many books borrowed for each patron.
     * @param patron Passes a patron as an object to get how many books his borrowed and did not return them.
     * @return number of borrowed books.
     */
    int booksboorows(Patron patron) {
    	int count = 0;
    	List<Loan> loanList = library.getLoans();
    	
    	for (int i = 0; i < loanList.size(); i++) {    
    		Loan loan = loanList.get(i);
    		
            if (patron == loan.getPatron()) 
            	count += 1;
    	}   	
    	return count;
    }
    
    /**
     * Displays each patron and his borrowing books.
     */
    public void patronBooks () {
    	List<Loan> loanList = library.getLoans();
        // headers for the table
        String[] columns = new String[]{"Patron", "Phone", "Email", "Book", "Start date", "End date"};
        int j = 0;
        Object[][] data = new Object[loanList.size()][6];
        
        for (int i = 0; i < loanList.size(); i++) {
            Loan loan = loanList.get(i);
            
        	data[j][0] = "  "+loan.getPatron().getName();
            data[j][1] = "  "+loan.getPatron().getPhone();
            data[j][2] = "  "+loan.getPatron().getEmail();
            data[j][3] = "  "+loan.getBook().getTitle();
            data[j][4] = "  "+loan.getStartDate();
            data[j][5] = "  "+loan.getDueDate();
            
            j += 1;
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
    	    @Override
    	    public boolean isCellEditable(int row, int column) {
    	        return false;
    	    }
    	};
    	      
        JTable table = new JTable(data, columns);
        table.setModel(tableModel);
        table.setForeground(Color.BLUE);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();  
    }
    
    /**
     * Displays each patron and his borrowing books.
     */
    public void booksOnLoan () {
    	List<Loan> loanList = library.getLoans();
        // headers for the table
        String[] columns = new String[]{"ID", "Title", "Author", "Pubisher year", "Publisher", "Patron", "Start date", "Due date"};
        
        Object[][] data = new Object[loanList.size()][8];
        int j = 0;
        for (int i = 0; i < loanList.size(); i++) {
            Loan loan = loanList.get(i);
            Book book = loan.getBook();
            
            if (!book.getHideFlag() && book.isOnLoan()) {
            	data[j][0] = "  "+book.getId();
                data[j][1] = "  "+book.getTitle();
                data[j][2] = "  "+book.getAuthor();
                data[j][3] = "  "+book.getPublicationYear();
                data[j][4] = "  "+book.getPublisher();
                data[j][5] = "  "+book.getLoan().getPatron().getName();
                data[j][6] = "  "+book.getLoan().getStartDate();
                data[j][7] = "  "+book.getDueDate();
            }
            
            j += 1;
        }
        
        DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
    	    @Override
    	    public boolean isCellEditable(int row, int column) {
    	        return false;
    	    }
    	};
    	      
        JTable table = new JTable(data, columns);
        table.setModel(tableModel);
        table.setForeground(Color.BLUE);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        this.revalidate();  
    }
}

