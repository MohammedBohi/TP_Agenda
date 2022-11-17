package agenda;

//import jdk.vm.ci.meta.Local;

import java.util.*;
import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive Event
 */
public class RepetitiveEvent extends Event {
    /**
     * Constructs a repetitive event
     *
     * @param title the title of this event
     * @param start the start of this event
     * @param duration myDuration in seconds
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     */
    private final ChronoUnit frequency;
    private final Set<LocalDate> exceptions = new TreeSet<>();
    public RepetitiveEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency) {
        super(title, start, duration);
        this.frequency = frequency;
    }

    /**
     * Adds an exception to the occurrence of this repetitive event
     *
     * @param date the event will not occur at this date
     */
    public void addException(LocalDate date) {
        this.exceptions.add(date);
    }

    /**
     *
     * @return the type of repetition
     */
    public ChronoUnit getFrequency() {
        return frequency;
    }

    public Set<LocalDate> getExceptions() {
        return exceptions;
    }
    @Override
    public boolean isInDay(LocalDate aDay) {
        boolean isInDay = false;
        LocalDate dateOccurence=super.getStart().toLocalDate();
        do {
            if (exceptions.contains(aDay)) {isInDay=false;
                break;
            }
            LocalDate debut=dateOccurence;
            LocalDate fin = debut.plusDays(super.getDuration().toDays());
            isInDay = ((aDay.isEqual(debut))||(aDay.isEqual(fin))) || (aDay.isAfter(debut) && aDay.isBefore(fin));
            dateOccurence= dateOccurence.plusDays(getFrequency().getDuration().toDays());

        }
        while(ChronoUnit.DAYS.between(dateOccurence,aDay) >= 0);

        return isInDay;
    }

}