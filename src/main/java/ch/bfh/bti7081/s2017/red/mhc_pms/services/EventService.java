package ch.bfh.bti7081.s2017.red.mhc_pms.services;

import ch.bfh.bti7081.s2017.red.mhc_pms.domain.Event;
import java.util.Date;
import java.util.List;

/**
 * The event service allows to manage calendar events.
 *
 * @author Samuel Egger
 */
public interface EventService {

    /**
     * Returns all events within the given date range of the specified user.
     *
     * @param start the range start date
     * @param end the range end date
     * @param userId the user of the event (attendee or owner)
     * @return a list of events
     */
    List<Event> findEventsByDateRange(Date start, Date end, int userId);

    /**
     * Finds the event with the given id.
     *
     * @param eventId the event id
     * @return the event with the given id or null
     */
    Event findEventById(int eventId);

    /**
     * Save or update an event.
     *
     * @param event the event
     */
    void saveOrUpdateEvent(Event event);

    /**
     * Delete an event.
     *
     * @param eventId the id of the event to delete
     */
    void deleteEvent(int eventId);
}
