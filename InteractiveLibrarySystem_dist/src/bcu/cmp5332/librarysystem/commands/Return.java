package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Loan;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.IOException;
import java.time.LocalDate;

/**
 * Implements a return borrowed book's function.
 * @author abdjory
 *
 */
public class Return implements  Command{
	
	private int bookID;
	private int patronID;
	
	public Return(int bookid, int patronid){
		this.bookID = bookid;
		this.patronID = patronid;
	}
	
	/**
     * Executes a return book's function by making some checks on the return's operation:
     * 1- make sure the book is not deleted.
     * 2- make sure the book is returned by the current patron who is borrowed it.
     * 3- make sure the patron record is active and not deleted.
     * 4- store the old transaction data in the history file as a return's transaction.
     */
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException { 
		Book bookObj = library.getBookByID(this.bookID);
		Patron patronObj = library.getPatronByID(this.patronID);
		
		if (bookObj.getHideFlag() == true) {
			System.out.println("The book is deleted!..");
			Library.showMessage("The book is deleted!..",false);
		}
		else if(patronObj.getHideFlag() == true) {
			System.out.println("The patron is deleted!..");
			Library.showMessage("The patron is deleted!..",false);
		}
		else {
			if (bookObj.isOnLoan()) {
				Loan loan = bookObj.getLoan();
				if ((loan.getPatron() == patronObj) && (loan.getBook() == bookObj)) {
					try {
						library.storeLonsHistory(2,bookObj.getLoan());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					library.addRemoveBroowedBook(2, this.bookID);
					bookObj.returnToLibrary();
					System.out.println("The operation has been done");
					Library.showMessage("The operation has been done",true);
				}else {
					System.out.println("The book is with another patron, so please correct your intered values!..");
					Library.showMessage("The book is with another patron, so please correct your intered values !!..",false);
				}	
			}else {
				System.out.println("The book is in the library!");
				Library.showMessage("The book is in the library!",false);
			}
		}	
	}
}