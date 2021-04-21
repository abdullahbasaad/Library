package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.IOException;
import java.time.LocalDate;


/**
 * Implements a renew borrowed book's function.
 */
public class Renew implements  Command{
	
	private int bookID;
	private int patronID;
	
	public Renew(int bookid, int patronid){
		this.bookID = bookid;
		this.patronID = patronid;
	}
	
	/**
     * Executes a renew book's function by making some checks on the renew's operation:
     * 1- make sure the book is not deleted.
     * 2- make sure the book is borrowed by the current patron.
     * 3- make sure the patron record is active and not deleted.
     * 4- store the old transaction data in the history file as a renew's transaction.
     */
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException { 
		Book bookObj = library.getBookByID(this.bookID);
		Patron patronObj = library.getPatronByID(this.patronID);
		
		if (bookObj.getHideFlag() == true) {
			System.out.println("The book is deleted!..");
			Library.showMessage("The book is deleted!..",false);
		}else if(patronObj.getHideFlag() == true) {
			System.out.println("The patron is deleted!..");
			Library.showMessage("The patron is deleted!..",false);
		}
		else {
			if (bookObj.isOnLoan()) {
				if (bookObj.getLoan().getPatron().getId() == this.patronID) {
					try {
						library.storeLonsHistory(1,bookObj.getLoan());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					bookObj.getLoan().setStartDate(currentDate);
					bookObj.getLoan().setDueDate(currentDate.plusDays(library.getLoanPeriod()));
					System.out.println("The operation has been done");
					Library.showMessage("The operation has been done",true);	
				}else {
					System.out.println("The book is with another patron, so you can not renew it!!..");
					Library.showMessage("The book is with another patron, so you can not renew it!!..",false);	
				}
			}else {
				System.out.println("The book is already in the library..");
				Library.showMessage("The book is already in the library..",false);	
			}
		}	
	}
}


