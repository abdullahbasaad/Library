package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import java.time.LocalDate;
import java.io.File;
import java.io.IOException;

import java.util.Scanner;

public class LoanTransDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/loan_trans.txt";
	private int patronId;
	private int bookId;
    
	/**
	 * Loads Loan's data transactions from the transactions' file into the system. If get error while loading, the system will give
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
    
    @Override
    public void storeData(Library library) throws IOException {
    }
}
