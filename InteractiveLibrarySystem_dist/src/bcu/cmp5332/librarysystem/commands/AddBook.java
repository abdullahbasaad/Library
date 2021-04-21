package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.IOException;
import java.time.LocalDate;


public class AddBook implements  Command {

    private final String title;
    private final String author;
    private final String publicationYear;
    private final String publisher;
    private boolean hideFlag;

    public AddBook(String title, String author, String publicationYear, String publisher, boolean hideFlag) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.hideFlag = false;
    }
    
    /**
     * Implements adding a new book to the system. It calls another method "addBooksTrans" to add the book
     * to the transaction's file as well.
     */
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        int lastIndex = library.getBooks().size() - 1;
        int maxId = library.getBooks().get(lastIndex).getId();
        Book book = new Book(++maxId, title, author, publicationYear, publisher, hideFlag);
        library.addBook(book);
        try {
			library.addBooksTrans(book);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String res = "Book #" + book.getId() + " added.";
        System.out.println(res);
        Library.showMessage(res,true);       
    }
}
