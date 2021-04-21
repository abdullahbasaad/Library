package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.IOException;
import java.time.LocalDate;


/**
 * Implements the borrowing function.
 * @author abdjory
 *
 */

public class Borrow implements  Command{	
	private int bookID;
	private int patronID;
	
	public Borrow(int bookid, int patronid){
		this.bookID = bookid;
		this.patronID = patronid;
	}
	
	/**
	 * Executes the borrowing function. It makes some checks to make sure the books is available 
	 * and a patron not exceed the allowing books number. The method will add a new record in the 
	 * loan's list. 
	 */
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException { 
		Book bookObj = library.getBookByID(this.bookID);
		Patron patronObj = library.getPatronByID(this.patronID);
		
		if (bookObj.getHideFlag() == true) {
			System.out.println("Sorry the book is deleted!..");
			Library.showMessage("Sorry the book is deleted!..",false);
		}else if (patronObj.getHideFlag() == true) {
			System.out.println("Sorry, the patron is deleted!..");
			Library.showMessage("Sorry, the patron is deleted!..",false);
		}
		else {
			if (bookObj.isOnLoan() == true) {
				System.out.println("Sorry, the book is borrowed!!..");
				Library.showMessage("Sorry, the book is borrowed!!..",false);
			}else {		
				if (library.getCountBooksBorrowing(patronID)+1 > library.getMaxBorrowing()) {
					System.out.println("Sorry!.., you have reached the limit for borrowing");
					Library.showMessage("Sorry!.., you have reached the limit for borrowing",false);
				}else {
					Loan loan = new Loan(patronObj,bookObj,currentDate, currentDate.plusDays(library.getLoanPeriod()));
					library.getLoans();
					library.addLoan(patronID, loan);
					bookObj.setLoan(loan);
					try {
						library.addLoanTrans(loan);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					library.addRemoveBroowedBook(1, this.bookID);
					System.out.println("The operation has been done");
					Library.showMessage("The operation has been done",true);
				}	
			}
		}	
	}
}
