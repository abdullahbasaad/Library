package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

/**
 * Implements the deleting book function from the system.
 * @author abdjory
 *
 */
public class DeleteBook implements  Command {

    private final int bookId;

    public DeleteBook(int bookid) {
        this.bookId = bookid;
    }
    
    /**
     * Calls a "delBooks" method and passes the book as object. It checks that the book is existing in the
     * system or not.
     */
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Book bookObj = library.getBookByID(bookId);
    	library.delBooks(bookObj);
    }
}
