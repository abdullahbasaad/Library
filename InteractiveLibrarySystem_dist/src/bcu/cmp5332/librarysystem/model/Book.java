package bcu.cmp5332.librarysystem.model;

import bcu.cmp5332.librarysystem.main.LibraryException;
import java.time.LocalDate;

public class Book {
    
    private int id;
    private String title;
    private String author;
    private String publicationYear;
    private String publisher;
    private boolean hideFlag;
    private Loan loan;
    

    public Book(int id, String title, String author, String publicationYear, String publisher, boolean hideFlag) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.hideFlag = hideFlag;
    }

    public Book() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns book id
	 * @return integer
	 */
    public int getId() {
        return id;
    }

    /**
	 * Sets book id
	 * @param Id Integer.
	 */
    public void setId(int Id) {
        this.id = Id;
    }
    
    /**
     * Gets a book's title.
     * @return String
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Returns if a book is deleted or not.
     * @return boolean
     */
    public boolean getHideFlag() {
    	return hideFlag;
    }
    
    /**
     * Sets a flag for deletion. 
     * @param value boolean.
     */
    public void setHideFlag (boolean value) {
    	hideFlag = value;
    }
    
    /**
     * Sets a book's title
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Gets an author
     * @return String
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Sets author name
     * @param author String
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets publisher's year
     * @return String
     */
    public String getPublicationYear() {
        return publicationYear;
    }

    /**
     * Sets publisher's year
     * @param publicationYear String
     */
    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }
	
    /**
     * Gets publisher's name
     * @return String
     */
    public String getPublisher() {
        return publisher;
    }
    
    /**
     * Sets publisher's name
     * @param publisher String
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    /**
     * Gets book short details.
     * @return String
     */
    public String getDetailsShort() {
        return "Book #" + id + " - " + title;
    }

    /**
     * Gets long book details
     * @return String
     */
    public String getDetailsLong() {
    	return "Book #" + id + "\n"+ " Title: "+ title+ "\n"+ " Author: "+ author+ "\n"+ " Publication Year: "+publicationYear+"\n"+" Publisher: "+publisher;
    }
    
    /**
     * Returns a boolean if a book on loan return true
     * @return boolean
     */
    public boolean isOnLoan() {
        return (loan != null);
    }
    
    /**
     * Gets book's availability.
     * @return String
     */
    public String getStatus() {
    	if (!isOnLoan()) {
    		return "Available..";
    	}else {
    		return "Unavailable..";
    	}
    }

    /**
     * Gets due date
     * @return Local date
     */
    public LocalDate getDueDate() {
        return loan.getDueDate();
    }
    
    /**
     * Sets a due date
     * @param dueDate Local date
     * @throws LibraryException library's exception
     */
    public void setDueDate(LocalDate dueDate) throws LibraryException {
        loan.setDueDate(dueDate);
    }
    
    /**
     * Sets a book starts borrow date
     * @param startDate Local date
     * @throws LibraryException library's exception
     */
    public void setStartDate(LocalDate startDate) throws LibraryException {
        loan.setStartDate(startDate);
    }
    
    /**
     * Gets a book loan's object
     * @return Loan object
     */
    public Loan getLoan() {
        return loan;
    }
    
    /**
     * Gets a book loan's object
     * @param loan object
     */
    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    /**
     * Sets a loan to null if a book is returned.
     */
    public void returnToLibrary() {
        loan = null;
    }
}
