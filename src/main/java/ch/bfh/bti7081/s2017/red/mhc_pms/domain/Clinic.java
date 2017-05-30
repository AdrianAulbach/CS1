package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The Clinics class represents a real world medical clinic.
 *
 * @author Florian Rindlisbacher
 */
@Entity
@Table(name = "clinic")
public class Clinic extends PersistentObject {

    private String name;

    @OneToMany(mappedBy = "clinic")
    private List<Location> locations = new ArrayList<>();

    /*
     * @return returns clinic locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /*
     * @return returns clinic name
     */
    public String getName() {
        return name;
    }

    /*
     * @param name sets clinic name
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
     * @param location adds clinic location 
     */
    public void add(Location location) {
        this.locations.add(location);
    }

    /*
     * @param location remove clinic location
     * 
     * TODO: throw exception if its last location in List
     * Should removed location be returned????
     */
    public void remove(Location location) {
        this.locations.remove(location);
    }
}
