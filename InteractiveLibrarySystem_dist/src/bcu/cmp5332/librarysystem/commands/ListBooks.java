package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListBooks implements Command {
	
	/**
	 * Uses a book's list to display a details for each book. It uses a method "getDetailsShort" from book class 
	 * to get a book details. It displays all books except deleted books.
	 */
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        List<Book> books = library.getBooks();
        int count = 0;
        for (Book book : books) {
        	if (!book.getHideFlag()) {
        		System.out.println(book.getDetailsShort());
        	}else {
        		count += 1;
        	}
        }
        System.out.println(books.size() - count + " book(s)");
    }
}
