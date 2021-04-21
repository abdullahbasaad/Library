package bcu.cmp5332.librarysystem.model;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created the class to test some modifications that have been done on the book and patron classes.
 * @author abdjory
 *
 */
public class PatronBookTest {
    
	Patron patron = new Patron(51,"Patron test","22201251","patronTest@hotmail.com",false);	
	Book book = new Book(51, "Test Unit project", "Asma group", "2020", "Asma group", false);
	
	@Test
	public void testEmail() {
		patron.setEmail("patron@hotmail.com");
		assertEquals("patron@hotmail.com",patron.getEmail());
    }
	
	@Test
	public void testBookAvailability() {
		assertEquals("Available..",book.getStatus());
    }
	
	@Test
	public void testPublisher() {
		book.setPublisher("AsmaGroup Ltd");
		assertEquals("AsmaGroup Ltd",book.getPublisher());
    }
	
	@Test
	public void testGetAuthor() {
		assertEquals("Asma group",book.getAuthor());
    }
	
	@Test
	public void testGetPhone() {
		assertEquals("22201251",patron.getPhone());
    }
}
