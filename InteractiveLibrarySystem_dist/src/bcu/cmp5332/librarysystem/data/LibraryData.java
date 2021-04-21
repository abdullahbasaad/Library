package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

/**
 * Loads and stores all data from\to the system and different store files.
 * If any transaction store's file size is greater than zero that indicates the system was shutting down in proper 
 * way so, the system will display a dialog message tells the user that there is some updates are not store yet.
 * a user can accept or refuse the updates.
 * @author abdjory
 */
public class LibraryData {
	static File RESOURCE1 = new File("./resources/data/book_trans.txt");
	static File RESOURCE2 = new File("./resources/data/patron_trans.txt");
	static File RESOURCE3 = new File("./resources/data/loan_trans.txt");
	
    private static final List<DataManager> dataManagers = new ArrayList<>();
    
    // runs only once when the object gets loaded to memory
    static {
        dataManagers.add(new BookDataManager());
        dataManagers.add(new PatronDataManager());
        dataManagers.add(new LoanDataManager());
        
        if (checkFileTrans()) {
        	int opcion = JOptionPane.showConfirmDialog(null, "Would you like to update the database, or you are going to lose the updates ?", "Confirm", JOptionPane.YES_NO_OPTION);
    		if (opcion == 0) { //The ISSUE is here
    			dataManagers.add(new BookTransDataManager());
    			dataManagers.add(new PatronTransDataManager());
    			dataManagers.add(new LoanTransDataManager());
    		}
        }
    }
    
    /**
     * Calls two methods, one to load data and one to clear all transaction files.
     * @return the library with all the system data.
     * @throws LibraryException
     * @throws IOException
     */
    public static Library load() throws LibraryException, IOException {
        Library library = new Library();
        for (DataManager dm : dataManagers) {
            dm.loadData(library);
        }
        if (checkFileTrans()) {
        	library.clearTransFiles();
        }
        return library;
    }
    
    /**
     * Stores all data from the library into different files.
     * @param library gets all data from the library.
     * @throws IOException
     */
    public static void store(Library library) throws IOException {
        for (DataManager dm : dataManagers) {
            dm.storeData(library);
        }
    }
    
    /**
     * Returns true if at least one file size is greater than zero.
     * @return
     */
    static boolean checkFileTrans() {
    	if ((RESOURCE1.length() > 0) || (RESOURCE2.length() > 0) || (RESOURCE3.length() > 0)) {
    		return true;
    	}
    	return false;
    }   
}
