/**
 * Package business that includes the class Person.
 * 
 * @author 
 * @version 2.0
 */
package business;

/**
 * Class Person that includes name, geographic data, date of birth, phone
 * number, email, health plan beneficiary number, bank account number, date of
 * start on the company, department, url of the image, personal code of a person
 * and a increment variable to designate a counter.
 * 
 * @author
 * @version 3.0
 */
public class Person {

    /**
     * Represents name of a person
     */
    private String name;

    /**
     * Represents geographic data of person
     */
    private String geographic_data;

    /**
     * Represents date of birth of person
     */
    private String dob;

    /**
     * Represents phone number of person
     */
    private String phone;

    /**
     * Represents email of person
     */
    private String email;

    /**
     * Represents health plan beneficiary number of person
     */
    private String ssn;

    /**
     * Represents bank account number of person
     */
    private String bankAccNum;

    /**
     * Represents date of start on the company of person
     */
    private String dateOfStart;

    /**
     * Represents department of person
     */
    private String depart;

    /**
     * Represents url of the image of the profile person
     */
    private String imageTxt;

    /**
     * Represents personal code of the profile person
     */
    private int personalCode;
    /**
     * Creates an increment to designate a counter (starting from 0) to the personal
     * code on each person
     */
    static int increment = 0;

    /**
     * Constructor with no fields
     */
    public Person() {

    }

    /**
     * Constructor of class Person
     * 
     * @param name            Name of the new person to create
     * @param geographic_data Geographic data of the new person to create
     * @param dob             Date of birth of the new person to create
     * @param phone           Phone of the new person to create
     * @param email           Email of the new person to create
     * @param ssn             Health plan beneficiary number of the new person to
     *                        create
     * @param bankAccNum      Bank account number of the new person to create
     * @param dateOfStart     Date of start in the company of the new person to
     *                        create
     * @param depart          Date of start in the company of the new person to
     *                        create
     * @param imageTxt        Image url of profile new person to create
     * @param personalCode    Personal code of the new person to create
     */
    public Person(String name, String geographic_data, String dob, String phone, String email, String ssn,
	    String bankAccNum, String dateOfStart, String depart, String imageTxt, int personalCode) {
	this.name = name;
	this.geographic_data = geographic_data;
	this.dob = dob;
	this.phone = phone;
	this.email = email;
	this.ssn = ssn;
	this.bankAccNum = bankAccNum;
	this.dateOfStart = dateOfStart;
	this.depart = depart;
	this.imageTxt = imageTxt;
	this.personalCode = personalCode;
	increment++;

    }

    /**
     * Get the name of person
     * 
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Set the name of person
     * 
     * @param name the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Get the geographic_data of person
     * 
     * @return the geographic_data
     */
    public String getGeographic_data() {
	return geographic_data;
    }

    /**
     * Set the geographic_data of person
     * 
     * @param geographic_data the geographic_data to set
     */
    public void setGeographic_data(String geographic_data) {
	this.geographic_data = geographic_data;
    }

    /**
     * Get the date of birth of person
     * 
     * @return the dob
     */
    public String getDob() {
	return dob;
    }

    /**
     * Set the date of birth of person
     * 
     * @param dob the dob to set
     */
    public void setDob(String dob) {
	this.dob = dob;
    }

    /**
     * Get the phone number of person
     * 
     * @return the phone
     */
    public String getPhone() {
	return phone;
    }

    /**
     * Set the phone number of person
     * 
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
	this.phone = phone;
    }

    /**
     * Get the email of person
     * 
     * @return the email
     */
    public String getEmail() {
	return email;
    }

    /**
     * Set the email of person
     * 
     * @param email the email to set
     */
    public void setEmail(String email) {
	this.email = email;
    }

    /**
     * Get the health plan beneficiary number of person
     * 
     * @return the ssn
     */
    public String getSsn() {
	return ssn;
    }

    /**
     * Set the health plan beneficiary number of person
     * 
     * @param ssn the ssn to set
     */
    public void setSsn(String ssn) {
	this.ssn = ssn;
    }

    /**
     * Get the bank account number of person
     * 
     * @return the bankAccNum
     */
    public String getBankAccNum() {
	return bankAccNum;
    }

    /**
     * Set the bank account number of person
     * 
     * @param bankAccNum the bankAccNum to set
     */
    public void setBankAccNum(String bankAccNum) {
	this.bankAccNum = bankAccNum;
    }

    /**
     * Get the date of start on the company of person
     * 
     * @return the dateOfStart
     */
    public String getDateOfStart() {
	return dateOfStart;
    }

    /**
     * Set the date of start on the company of person
     * 
     * @param dateOfStart the dateOfStart to set
     */
    public void setDateOfStart(String dateOfStart) {
	this.dateOfStart = dateOfStart;
    }

    /**
     * Get the department of person
     * 
     * @return the depart
     */
    public String getDepart() {
	return depart;
    }

    /**
     * Set the department of person
     * 
     * @param depart the depart to set
     */
    public void setDepart(String depart) {
	this.depart = depart;
    }

    /**
     * Get url of the image of person
     * 
     * @return the imageTxt
     */
    public String getImageTxt() {
	return imageTxt;
    }

    /**
     * Set url of the image of person
     * 
     * @param imageTxt the imageTxt to set
     */
    public void setImageTxt(String imageTxt) {
	this.imageTxt = imageTxt;
    }

    /**
     * Get the personalCode of person
     * 
     * @return the personalCode
     */
    public int getPersonalCode() {
	return personalCode;
    }

    /**
     * Set the personalCode of person
     * 
     * @param personalCode the personalCode to set
     */
    public void setPersonalCode(int personalCode) {
	this.personalCode = personalCode;
    }

    /**
     * Get the increment designated to person
     * 
     * @return the increment
     */
    public static int getIncrement() {
	return increment;
    }

    /**
     * Set the increment designated to person
     * 
     * @param increment the increment to set
     */
    public static void setIncrement(int increment) {
	Person.increment = increment;
    }

}
