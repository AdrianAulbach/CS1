package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Room class represents a unique identified room and is attached to a
 * location.
 *
 * @author Florian Rindlisbacher
 */
@Entity
@Table(name = "room")
public class Room extends PersistentObject {

    private String floor;
    private String room;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    /*
     * @return returns floor name
     */
    public String getFloor() {
        return floor;
    }

    /*
	 * @return returns room name
     */
    public String getRoom() {
        return room;
    }

    /*
     * @return returns location object
     */
    public Location getLocation() {
        return location;
    }

    /*
     * @param floor sets floor number/level
     */
    public void setFloor(String floor) {
        this.floor = floor;
    }

    /*
     * @param room sets room number or name as String
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /*
     * @param location sets location object
     */
    public void setLocation(Location location) {
        this.location = location;
    }
}
