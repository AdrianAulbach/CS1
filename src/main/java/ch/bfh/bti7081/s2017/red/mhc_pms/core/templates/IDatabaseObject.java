package ch.bfh.bti7081.s2017.red.mhc_pms.core.templates;

/**
 * The Interface IDatabaseObject.
 */
public interface IDatabaseObject
{
	
	/**
	 * Persists the database object in database.
	 */
	public void persist();
	
	/**
	 * Restores the database object from database.
	 */
	public void restore();
}
