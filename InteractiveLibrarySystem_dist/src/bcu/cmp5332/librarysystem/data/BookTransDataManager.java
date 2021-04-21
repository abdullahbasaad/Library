package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Book;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BookTransDataManager implements DataManager {
    
    private final String RESOURCE = "./resources/data/book_trans.txt";
    
    /**
     * Loads all book's data into the system from the transaction's file in case something goes wrong while the system is running. 
     * We keep a backup data in separate file and uses when we need it.
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

	@Override
	public void storeData(Library library) throws IOException {
	}
}
