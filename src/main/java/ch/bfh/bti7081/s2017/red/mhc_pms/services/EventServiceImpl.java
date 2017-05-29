package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Event;
import java.util.Date;
import java.util.List;

/**
 * {@inheritDoc}
 *
 * @author Samuel Egger
 */
public class EventServiceImpl implements EventService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Event> findEventsByDateRange(Date start, Date end, int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event findEventById(int eventId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOrUpdateEvent(Event event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEvent(int eventId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
