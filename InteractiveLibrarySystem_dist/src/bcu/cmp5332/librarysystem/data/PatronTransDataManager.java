package bcu.cmp5332.librarysystem.data;

import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PatronTransDataManager implements DataManager {
	
    private final String RESOURCE = "./resources/data/patron_trans.txt";
    
    /**
	 * Loads patron's data transactions from the transactions' file into the system. If get error while loading, the system will give
	 * more details about the error.
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
                    String name  = properties[1];
                    String phone = properties[2];
                    String email = properties[3];
                    boolean hideFlag = Boolean.parseBoolean(properties[4]);
                    Patron patron = new Patron(id,name, phone, email, hideFlag);
                    library.addPatron(patron);
                } catch (NumberFormatException ex) {
                	if (RESOURCE.length() > 0) {
                		throw new LibraryException("Unable to parse patron id " + properties[0] + " on line " + line_idx
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






