package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Base class for all persistent objects.
 * 
 * @author Samuel Egger
 */
@Entity
public abstract class PersistentObject  implements Serializable {

    @Id
    @GeneratedValue
    private Long id = null;

    public Long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
    public void setId(final Long id) {
        this.id = id;
    }
}
