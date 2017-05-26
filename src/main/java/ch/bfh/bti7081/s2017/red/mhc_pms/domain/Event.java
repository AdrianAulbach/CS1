package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The Event class is a timly limited event that has one or more persons attending and is held at a specific point in time
 *
 * @author Florian Rindlisbacher
 */

@Entity
@Table(name="event")
public class Event extends PersistentObject {

	private static final long serialVersionUID = 1L;

	private Date start;
	private Date end;
	private String description;
	
	@OneToOne
	private Doctor owner;
	
	@ManyToMany
	private List<Person> attendees;

	private String title;

	/**
	 * @param person adds person to attendee List
	 */
	public void addAttendee(Person person) {
		attendees.add(person);
	}	
	
	/**
	 * @param person removes person from attendee List
	 */
	public void removeAtendee(Person person){
		attendees.remove(person);
	}
	
	/**
	 * @return returns start date of event
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @param start sets start date of event
	 */
	public void setStart(Date start) {
		this.start = start;
	}
	
	/**
	 * @return returns end date of event
	 */
	public Date getEnd() {
		return end;
	}
	
	/**
	 * @param end sets end date of event
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
	
	/**
	 * @return description returns description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return returns owner of event
	 */
	public Doctor getOwner() {
		return owner;
	}
	
	/**
	 * @param owner sets owner of event
	 */
	public void setOwner(Doctor owner) {
		this.owner = owner;
	}

	/**
	 *  @return returns title of event
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title sets title of event
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return returns List of attendees
	 */
	public List<Person> getAttendees() {
		return attendees;
	}

}
