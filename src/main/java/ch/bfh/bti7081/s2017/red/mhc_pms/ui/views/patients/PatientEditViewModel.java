/**
 * File: ch.bfh.bti7081.s2017.red::PatientEditViewModel.java
 *
 * @author Aleistar Markóczy
 */
package ch.bfh.bti7081.s2017.red.mhc_pms.ui.views.patients;

/**
 * The Class PatientEditViewModel.
 */
public class PatientEditViewModel
{
	private long id = -1;
	private String firstName;
    private String lastName;
    private String street;
    private String zipCode;
    private String city;
    private String phone;
    private String mobile;
    private String email;
    
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getStreet()
	{
		return street;
	}
	public String getZipCode()
	{
		return zipCode;
	}
	public String getCity()
	{
		return city;
	}
	public String getPhone()
	{
		return phone;
	}
	public String getMobile()
	{
		return mobile;
	}
	public String getEmail()
	{
		return email;
	}
	public void setFirstName(String aFirstName)
	{
		firstName = aFirstName;
	}
	public void setLastName(String aLastName)
	{
		lastName = aLastName;
	}
	public void setStreet(String aStreet)
	{
		street = aStreet;
	}
	public void setZipCode(String aZipCode)
	{
		zipCode = aZipCode;
	}
	public void setCity(String aCity)
	{
		city = aCity;
	}
	public void setPhone(String aPhone)
	{
		phone = aPhone;
	}
	public void setMobile(String aMobile)
	{
		mobile = aMobile;
	}
	public void setEmail(String aEmail)
	{
		email = aEmail;
	}
	public long getId()
	{
		return id;
	}
	public void setId(long aId)
	{
		id = aId;
	}
    
}
