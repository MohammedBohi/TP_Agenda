package agenda;

import java.time.LocalDate;
import java.util.*;

/**
 * Description : An agenda that stores events
 */
public class Agenda {
    private  List<Event> listeEvent= new ArrayList<Event>();
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */

    public void addEvent(Event e) {
       this.listeEvent.add(e);
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    public List<Event> eventsInDay(LocalDate day) {
     List<Event> currentEvent=new ArrayList<Event>();
        for( Event e : this.listeEvent){
           if(e.getStart().toLocalDate().equals(day))
               currentEvent.add(e);
        }
        return currentEvent;
    }
    public List<Event> findByTitle(String title) {
        List<Event> events=new ArrayList<Event>();
        for (Event e: this.listeEvent){
            if(e.getTitle().equals(title)){
                events.add(e);
            }
        }
        return events;
    }

    public boolean isFreeFor(Event e) {
        boolean isFree = true;
        for(Event event: listeEvent) {
            if(event.coincides(e)){
                isFree = false;
                break;
            }
        }
        return isFree;
    }

}
