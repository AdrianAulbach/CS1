package ch.bfh.bti7081.s2017.red.mhc_pms.domain;

/**
 * Base class for all persistent objects.
 * 
 * @author Samuel Egger
 */
public abstract class PersistentObject {

    private Long id = null;

    public Long getId() {
        return this.id;
    }

    @SuppressWarnings("unused")
    public void setId(final Long id) {
        this.id = id;
    }
}
