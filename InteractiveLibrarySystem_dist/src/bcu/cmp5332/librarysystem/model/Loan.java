package bcu.cmp5332.librarysystem.model;

import java.time.LocalDate;

public class Loan {
    
    private Patron patron;
    private Book book;
    private LocalDate startDate;
    private LocalDate dueDate;
    
    public Loan(Patron patron, Book book, LocalDate startDate, LocalDate dueDate) {
        this.patron = patron;
        this.book = book;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

	/**
	 * Gets a patron's object
	 * @return patron object
	 */
    public Patron getPatron() {
		return patron;
	}

	/**
	 * Gets a book's object
	 * @return Book object
	 */
    public Book getBook() {
		return book;
	}

	/**
	 * Gets a start loan's date.
	 * @return Local date
	 */
    public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Gets a loan's due date
	 * @return Local date
	 */
    public LocalDate getDueDate() {
		return dueDate;
	}

	/**
	 * Sets a loan's patron
	 * @param patron object
	 */
    public void setPatron(Patron patron) {
		this.patron = patron;
	}

	/**
	 * Sets a loan's book
	 * @param book object
	 */
    public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * Sets a loan's start date
	 * @param startDate Local date
	 */
    public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Sets a loan's due date
	 * @param dueDate Local date
	 */
    public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
                                 
}
