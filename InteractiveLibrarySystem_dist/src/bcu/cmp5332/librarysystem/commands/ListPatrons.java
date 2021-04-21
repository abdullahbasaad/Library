package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.model.Patron;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.main.LibraryException;

import java.time.LocalDate;
import java.util.List;

public class ListPatrons implements Command {
	
	/**
	 * Uses a patron's list to display the details for each patron. It uses a method "getDetailsShort" from patron class 
	 * to get a patron details. It displays all patrons except the deleted patrons.
	 */
	 @Override
	 public void execute(Library library, LocalDate currentDate) throws LibraryException {
	      List<Patron> patrons = library.getPatrons();
	      int count = 0;
	      for (Patron patron : patrons) {
	    	  if (!patron.getHideFlag()) {
	    		  System.out.println(patron.getDetailsShort());
	    		  System.out.println("=========================");
	    	  }else {
	    		  count+=1;
	    	  }
	      }
	      System.out.println(patrons.size()-count + " patron(s)");
	 }
}
