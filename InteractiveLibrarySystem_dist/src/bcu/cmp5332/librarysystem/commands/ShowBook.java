package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;
import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;

/**
 * Implements to display a data for a specific book.
 * @author abdjory
 *
 */
public class ShowBook implements Command {
	
	private int bookId;
	
	public ShowBook (int bookid){
		this.bookId = bookid;
	}

	/**
     * Executes a display book's function "getDetailsLong" from the book class if the book is not
     * deleted.
     */
	@Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException { 
    	if (!library.getBookByID(bookId).getHideFlag()) {
        	System.out.print(library.getBookByID(bookId).getDetailsLong());
        }else {
        	System.out.println("The book is deleted!");
        	Library.showMessage("The book is deleted!",false);
        }
    	
    }
}
