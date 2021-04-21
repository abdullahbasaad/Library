package bcu.cmp5332.librarysystem.commands;

import bcu.cmp5332.librarysystem.main.LibraryException;
import bcu.cmp5332.librarysystem.model.Library;
import bcu.cmp5332.librarysystem.model.Patron;
import java.io.IOException;
import java.time.LocalDate;

public class AddPatron implements Command {

    private final String name;
    private final String phone;
    private final String email;
    private boolean hideFlag;

    public AddPatron(String name, String phone, String email, boolean hideFlag) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.hideFlag = hideFlag;   
    }
    
    /**
     * Executes adding a new patron to the system. It calls another method "addPatronTrans" to add the patron
     * to the transaction's file as well.
     */
    @Override
    public void execute(Library library, LocalDate currentDate) throws LibraryException {
        int lastIndex = library.getPatrons().size() - 1;
        int maxId = library.getPatrons().get(lastIndex).getId();
        Patron patron = new Patron(++maxId, name, phone, email, hideFlag);
        library.addPatron(patron);
        try {
			library.addPatronTrans(patron);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String res = "Patron #" + patron.getId() + " added.";
        System.out.println(res);
        Library.showMessage(res,true);
    }
}
