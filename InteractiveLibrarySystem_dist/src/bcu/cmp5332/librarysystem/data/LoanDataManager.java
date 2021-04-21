package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import java.time.LocalDate;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Scanner;

public class LoanDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/loans.txt";
	private int patronId;
	private int bookId;
    
	/**
	 * Loads Loan's data from the store files into the system. If get error while loading, the system will give
	 * more details about the error.
	 */
    @Override
    public void loadData(Library library) throws IOException, LibraryException {    	
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                	patronId = Integer.parseInt(properties[0]);
                    bookId   = Integer.parseInt(properties[1]);
					LocalDate curDate = LocalDate.parse(properties[2]);
                    LocalDate dueDate = LocalDate.parse(properties[3]);
					Book bok = library.getBookByID(bookId);
					Patron pat = library.getPatronByID(patronId);
                    Loan loan = new Loan(pat,bok, curDate, dueDate);
                    library.getBookByID(bookId).setLoan(loan);
                    library.addLoan(patronId,loan);
                } catch (NumberFormatException ex) {
                    if (RESOURCE.length() > 0) {
                    	throw new LibraryException("Unable to parse loan " + properties[0] + " on line " + line_idx
                                + "\nError: " + ex);
                    }
                }
                line_idx++;
            }
        }
    }
    
    /**
     * Stores a lone details into a file with the same format.
     */
    @Override
    public void storeData(Library library) throws IOException {
    	File file = new java.io.File(RESOURCE);   	
    	if(file.exists() && !file.canWrite()){           
    		Library.showMessage("Loan's file exists and it is read only, so, the system will rollback any changes",false); 
        }else {
	        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
	            for (Loan loan : library.getLoans()) {
	                out.print(loan.getPatron().getId() + SEPARATOR);
	                out.print(loan.getBook().getId() + SEPARATOR);
	                out.print(loan.getStartDate() + SEPARATOR);
	                out.print(loan.getDueDate() + SEPARATOR);
	                out.println();
	            }
	        }
        }
    }
}
