package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import org.apache.log4j.Logger;

/**
 * Base class for all services.
 *
 * @author Samuel Egger
 */
public abstract class ServiceBase {

    private static final Logger LOGGER = Logger.getRootLogger();
    
    protected Logger getLogger(){
        return LOGGER;
    }
}
