package bcu.cmp5332.librarysystem.model;

public class Patron {
    
    private int id;
    private String name;
    private String phone;
    private String email;
    private boolean hideFlag;
    
    // TODO: implement constructor here
    public Patron(int id, String name, String phone, String email, boolean hideFlag){
    	this.id = id;
    	this.name = name;
    	this.phone = phone;
    	this.email = email;
    	this.hideFlag = hideFlag;
    }
    
    public Patron() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Gets a patron's id
	 * @return integer
	 */
    public int getId() {
        return id;
    }
    
    /**
	 * Sets a patron's id
	 * @param Id integer
	 */
    public void setId(int Id) {
        this.id = Id;
    }
    
    /**
     * Sets a patron's name
     * @return String
     */
    public String getName() {
        return name;
    }
    
    /**
	 * Sets a patron's name
	 * @param name string
	 */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets a patron's phone
     * @return String
     */
    public String getPhone() {
        return phone;
    }
    
    /**
	 * Sets a patron's phone
	 * @param phone string
	 */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * Gets a patron's email
     * @return String
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Sets a patron's email
     * @param eml String
     */
    public void setEmail(String eml) {
        email = eml;
    }
    
    /**
     * Gets a boolean value of deletion's flag
     * @return boolean
     */
    public boolean getHideFlag() {
    	return hideFlag;
    }
    
    /**
     * Sets a deletion's flag
     * @param value boolean
     */
    public void setHideFlag (boolean value) {
    	hideFlag = value;
    }
    
    /**
     * Gets a patron's short details.
     * @return String
     */
    public String getDetailsShort() {
        return "Patron #" + id + "\n" + "Name: "+name+ "\n"+"Phone: "+phone+ "\n"+"Email: "+email;
    }
}
