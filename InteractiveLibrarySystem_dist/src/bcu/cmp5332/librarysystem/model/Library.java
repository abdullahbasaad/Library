package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.data.DataManager;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Library {
    
    private final int loanPeriod = 7;
    private final int maxBorrowing = 3;
    private final Map<Integer, Patron> patrons = new TreeMap<>();
    private final Map<Integer, Book> books = new TreeMap<>();
    private final Map<String, Loan> loans = new TreeMap<>();
    List<Integer> patronBorrowedBooks = new ArrayList<Integer>();
    
    final static String RESOURCE1 = "./resources/data/book_trans.txt";
	final static String RESOURCE2 = "./resources/data/patron_trans.txt";
	final static String RESOURCE3 = "./resources/data/loan_trans.txt";
	static ImageIcon up = new ImageIcon("src/images/thumUp.png");
	static ImageIcon down = new ImageIcon("src/images/thumDown.png");
	
    public int getLoanPeriod() {
        return loanPeriod;
    }

    public List<Book> getBooks() {
        List<Book> out = new ArrayList<>(books.values());
        return Collections.unmodifiableList(out);
    }
    
    /**
     * Deletes a specific book, hide a book in the system by updating the showing flag.
     * @param book object
     */
    public void delBooks(Book book) {
    	/* Completed delete
    		books.values().removeIf(bookId::equals);
    		List<Book> out = new ArrayList<>(books.values());
        	return Collections.unmodifiableList(out);
    	**/
    	
    	if (book.getHideFlag() == false){
    		book.setHideFlag(true);
    		getBooks();
    		String msg = "Book #" + book.getId() + " deleted.";
    		showMessage(msg, true);
    		System.out.println("Book #" + book.getId() + " deleted.");
    	}else {
    		System.out.println("The book is already deleted!");
    		showMessage("The book is already deleted!", false);
    	}
    }
    
    /**
     * Returns how many books borrowed each patrons and did not return them.
     * @param patronId integer
     * @return integer
     */
    public int getCountBooksBorrowing(int patronId) {
    	int count = 0;
    	for (Loan loan : getLoans()) {
    		if (loan.getPatron().getId() == patronId) {
    			count += 1;
    		}
    	}
    	return count;
    }
    
    public int getMaxBorrowing() {
    	return maxBorrowing;
    }
    
    /**
     * Deletes a patron, hide a patron in the system by updating the showing flag.
     * @param patron object
     */
    public void delPatrons(Patron patron) {
    	boolean hasBooks = false;
    	for (Loan loan : getLoans()) {
    		if (loan.getPatron().getId() == patron.getId()) {
    			hasBooks = true;
    			break;
    		}
    	}
    	
    	if (hasBooks) {
    		showMessage(patron.getName()+" has some books so, you can not delete it!", false);
    	}else {
	    	if (patron.getHideFlag() == false){
	    		patron.setHideFlag(true);
	    		getPatrons();
	    		String msg = "Patron #" + patron.getId() + " deleted.";
	    		System.out.println("Patron #" + patron.getId() + " deleted.");
	    		showMessage(msg, true);
	    	}else {
	    		System.out.println("The patron is already deleted!..");
	    		showMessage("The patron is already deleted!", false);
	    	}
    	}
    }
    
    public List<Loan> getLoans() {
        List<Loan> out = new ArrayList<>(loans.values());
        return Collections.unmodifiableList(out);
    }
    
    public List<Patron> getPatrons() {
        List<Patron> out = new ArrayList<>(patrons.values());
        return Collections.unmodifiableList(out);
    }

    public Book getBookByID(int id) throws LibraryException {
    	if (!books.containsKey(id)) {
            throw new LibraryException("There is no such book with that ID.");
            
    	}
        return books.get(id);
    }

    public Patron getPatronByID(int id) throws LibraryException {
    	if (!patrons.containsKey(id)) {
            throw new LibraryException("There is no such patron with that ID.");
        }
        return patrons.get(id);
    }

    public void addBook(Book book) {
        if (books.containsKey(book.getId())) {
            //throw new IllegalArgumentException("Duplicate book ID: "+book.getId());
        	System.out.println("Duplicate book ID.");
        }else {
        	books.put(book.getId(), book);
        }
    }

    public void addPatron(Patron patron) {
    	 if (patrons.containsKey(patron.getId())) {
             //throw new IllegalArgumentException("Duplicate patron ID: " + patron.getId());
             System.out.println("Duplicate patron ID: " + patron.getId());
         }else {
        	 patrons.put(patron.getId(), patron);
         }    
    }
    
    public void addLoan(int pat,Loan loan) {
    	 /*if (loans.containsKey(pat)) {
             throw new IllegalArgumentException("Duplicate Loans.");
         }*/
         loans.put(loan.getBook().getTitle(), loan);
    }
    
    /**
     * Adds, removes a book from a borrowing list depends on the operation's type.
     * 1 - Add
     * 2 - Delete
     * @param opr integer
     * @param bookId integer
     */
    public void addRemoveBroowedBook(int opr, int bookId) {	
    	if (opr == 1) {
    		patronBorrowedBooks.add(bookId);
    	}else {
    		for (int i = 0; i<patronBorrowedBooks.size(); i++) {
            	if (patronBorrowedBooks.get(i) == bookId) {
            		patronBorrowedBooks.remove(i);
            	}
            }  
    	}
    }
    
    /**
     * Stores some data such as return's book transaction or renew a book into a "loans history" file.
     * @param opr integer
     * @param loan object
     * @throws IOException Librarie's exception
     */
    public void storeLonsHistory(int opr, Loan loan) throws IOException {
    	final String RESOURCE = "./resources/data/loans_history.txt";
    	String oprType = "";
	    FileWriter fw = new FileWriter(RESOURCE,true);
	    
	    if (opr == 1) {
	    	oprType = "Renew";
	    }else {
	    	oprType = "Return";
	    }
	    String line = oprType+","+loan.getPatron().getId() + ","+loan.getBook().getId() + ","+loan.getStartDate() + ","+loan.getDueDate();
	    fw.write(line);
	    fw.write("\n");
	    fw.close();	
	    if (opr == 2) {
	    	loan.getBook().returnToLibrary();
	    	loans.values().removeIf(loan::equals);
	    }  
    }
    
    /**
     * Store books into "Book trans" transaction's file in case the system closed in proper way to restore it later.
     * @param book object
     * @throws IOException Librarie's exception
     */
    public void addBooksTrans(Book book) throws IOException {
    	final String RESOURCE = "./resources/data/book_trans.txt";
	    FileWriter fw = new FileWriter(RESOURCE,true);
	    String line = book.getId()+DataManager.SEPARATOR+book.getTitle()+DataManager.SEPARATOR+book.getAuthor()+DataManager.SEPARATOR+book.getPublicationYear()+  DataManager.SEPARATOR+book.getPublisher()+DataManager.SEPARATOR+book.getHideFlag()+DataManager.SEPARATOR;
	    fw.write(line);
	    fw.write("\n");
	    fw.close();	 
    }
    
    /**
     * Stores patrons into "patron trans" transaction's file in case the system closed in proper way to restore it later.
     * @param patron object
     * @throws IOException Librarie's exception
     */
    public void addPatronTrans(Patron patron) throws IOException {
    	final String RESOURCE = "./resources/data/patron_trans.txt";
	    FileWriter fw = new FileWriter(RESOURCE,true);
	    String line = patron.getId()+DataManager.SEPARATOR+patron.getName()+DataManager.SEPARATOR+patron.getPhone()+DataManager.SEPARATOR+patron.getEmail()+DataManager.SEPARATOR+patron.getHideFlag()+DataManager.SEPARATOR;
	    fw.write(line);
	    fw.write("\n");
	    fw.close();	 
    }
    
    /**
     * Stores loans into "loan trans" transacion's file in case the system closed in proper way to restore it later.
     * @param loan object
     * @throws IOException Librarie's exception
     */
    public void addLoanTrans(Loan loan) throws IOException {
    	final String RESOURCE = "./resources/data/loan_trans.txt";
	    FileWriter fw = new FileWriter(RESOURCE,true);
	    String line = loan.getPatron().getId()+DataManager.SEPARATOR+loan.getBook().getId()+DataManager.SEPARATOR+loan.getStartDate()+DataManager.SEPARATOR+loan.getDueDate()+DataManager.SEPARATOR;
	    fw.write(line);
	    fw.write("\n");
	    fw.close();	 
    }
    
    /**
     * Clears all transactions' files if the system closed in proper way to avoid the redundancy in the data.
     */
    public void clearTransFiles() {
	    try {
			FileChannel.open(Paths.get(RESOURCE1), StandardOpenOption.WRITE).truncate(0).close();
			FileChannel.open(Paths.get(RESOURCE2), StandardOpenOption.WRITE).truncate(0).close();
			FileChannel.open(Paths.get(RESOURCE3), StandardOpenOption.WRITE).truncate(0).close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Shows messages for each transaction in the system
     * @param msg String dialog message
     * @param res boolean operation's success status
     */
    public static void showMessage (String msg, boolean res) {
    	if (res == true) {
    		JOptionPane.showMessageDialog(null, msg, 
	                "Message", JOptionPane.INFORMATION_MESSAGE, up);
    	}else {
    		JOptionPane.showMessageDialog(null, msg, 
	                "Message", JOptionPane.INFORMATION_MESSAGE, down);
    	}   			
    }
}
