package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookDataManager implements DataManager {
    
    private final String RESOURCE = "./resources/data/books.txt";
    
    /**
     * Loads books' data from a file into the system.
     */
    @Override
    public void loadData(Library library) throws IOException, LibraryException {
        try (Scanner sc = new Scanner(new File(RESOURCE))) {
            int line_idx = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] properties = line.split(SEPARATOR, -1);
                try {
                    int id = Integer.parseInt(properties[0]);
                    String title = properties[1];
                    String author = properties[2];
                    String publicationYear = properties[3];
                    String publisher = properties[4];
                    boolean hideFlag = Boolean.parseBoolean(properties[5]);
                    Book book = new Book(id, title, author, publicationYear, publisher, hideFlag);
                    
                    library.addBook(book);
                } catch (NumberFormatException ex) {
                	if (RESOURCE.length() > 0) {
                		throw new LibraryException("Unable to parse book id " + properties[0] + " on line " + line_idx
                        + "\nError: " + ex);
                	}
                }
                line_idx++;
            }
        }
    }
    
    /**
     * Stores all books data from the system into a book file to save the status of the system.
     */
    @Override
    public void storeData(Library library) throws IOException {
    	File file = new java.io.File(RESOURCE);   	
    	if(file.exists() && !file.canWrite()){           
    		Library.showMessage("Book's file exists and it is read only, so, the system will rollback any changes",false); 
        }else {
	    	try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))) {
	            for (Book book : library.getBooks()) {
	                out.print(book.getId() + SEPARATOR);
	                out.print(book.getTitle() + SEPARATOR);
	                out.print(book.getAuthor() + SEPARATOR);
	                out.print(book.getPublicationYear() + SEPARATOR);
	                out.print(book.getPublisher() + SEPARATOR);
	                out.print(book.getHideFlag() + SEPARATOR);
	                out.println();
	            }
	    	}
        }
    }
}
