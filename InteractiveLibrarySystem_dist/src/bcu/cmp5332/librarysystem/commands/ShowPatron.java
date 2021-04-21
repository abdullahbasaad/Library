package bcu.cmp5332.librarysystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;

/**
 * Created the class to show a patron details
 * @author abdjory
 *
 */
public class ShowPatron implements Command {
	
private int patronId;
	
	public ShowPatron (int patronid){
		this.patronId = patronid;
	}
	
	/*
	 * Executes a display patron's function "getDetailsShort" from the patron class if the book is not
     * deleted.
	 */
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException { 
        if (!library.getPatronByID(patronId).getHideFlag()) {
        	System.out.print(library.getPatronByID(patronId).getDetailsShort());
        }else {
        	System.out.print("The patron is deleted!");
        	Library.showMessage("The patron is deleted!",false);
        }
    }

}
