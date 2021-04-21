package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

/**
 * Implements the deleting function.
 * @author abdjory
 *
 */
public class DeletePatron implements  Command {

    private final int patronId;

    public DeletePatron(int patronid) {
        this.patronId = patronid;
    }
    
    /**
     * Calls a "delPatrons" method and passes a patron as object. It checks that the patron is existing in the
     * system or not.
     */
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        Patron patronObj = library.getPatronByID(patronId);
    	library.delPatrons(patronObj);
    }
}
