package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="event")
public class Event extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date start;

	private Date end;

	private String description;

	private Doctor owner;
	
	@ManyToMany
	private List<Person> attendees;

	private String title;

	public void addAttendee(Person person) {
		attendees.add(person);
	}
	
	public void removeAtendee(Person person){
		attendees.remove(person);
	}
	
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Doctor getOwner() {
		return owner;
	}

	public void setOwner(Doctor owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Person> getAttendees() {
		return attendees;
	}

	
}
