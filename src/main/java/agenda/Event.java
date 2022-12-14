package agenda;

import java.time.*;

public class Event {

    /**
     * The myTitle of this event
     */
    private String myTitle;
    
    /**
     * The starting time of the event
     */
    private LocalDateTime myStart;

    /**
     * The durarion of the event 
     */
    private Duration myDuration;


    /**
     * Constructs an event
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     */
    public Event(String title, LocalDateTime start, Duration duration) {
        this.myTitle = title;
        this.myStart = start;
        this.myDuration = duration;
    }

    /**
     * Tests if an event occurs on a given day
     *
     * @param aDay the day to test
     * @return true if the event occurs on that day, false otherwise
     */
    public boolean isInDay(LocalDate aDay) {
       boolean rep=false;
       LocalDate debut=this.getStart().toLocalDate();
       LocalDate fin = this.getStart().plus(getDuration()).toLocalDate();
       rep = ((aDay.isEqual(debut))||(aDay.isEqual(fin))) || (aDay.isAfter(debut) && aDay.isBefore(fin));
        return rep;
    }

    public boolean coincides(Event other) {
        LocalDateTime myEnd = this.getEnd();
        LocalDateTime otherStart = other.getStart();
        LocalDateTime otherEnd = other.getEnd();
        return  otherStart.isAfter(myStart) && otherEnd.isBefore(myEnd) || (otherStart.isEqual(myStart) || otherEnd.isEqual(myEnd));
    }
   
    /**
     * @return the myTitle
     */
    public String getTitle() {
        return myTitle;
    }

    @Override
    public String toString() {
        return "Event{" +
                "myTitle='" + myTitle + '\'' +
                '}';
    }

    /**
     * @return the myStart
     */
    public LocalDateTime getStart() {
        return myStart;
    }


    public LocalDateTime getEnd() { return myStart.plus(myDuration); }


    /**
     * @return the myDuration
     */
    public Duration getDuration() {
        return myDuration;
    }

   
    
}
