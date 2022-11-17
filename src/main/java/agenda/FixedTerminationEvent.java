package agenda;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Description : A repetitive event that terminates after a given date, or after
 * a given number of occurrences
 */
public class FixedTerminationEvent extends RepetitiveEvent {


    /**
     * Constructs a fixed terminationInclusive event ending at a given date
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param terminationInclusive the date when this event ends
     */
    private LocalDate terminationInclusive = null;
    private long numberOfOccurrences = 0;

    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, LocalDate terminationInclusive) {
        super(title, start, duration, frequency);
        this.terminationInclusive = terminationInclusive;
    }

    /**
     * Constructs a fixed termination event ending after a number of iterations
     *
     * @param title the title of this event
     * @param start the start time of this event
     * @param duration the duration of this event
     * @param frequency one of :
     * <UL>
     * <LI>ChronoUnit.DAYS for daily repetitions</LI>
     * <LI>ChronoUnit.WEEKS for weekly repetitions</LI>
     * <LI>ChronoUnit.MONTHS for monthly repetitions</LI>
     * </UL>
     * @param numberOfOccurrences the number of occurrences of this repetitive event
     */
    public FixedTerminationEvent(String title, LocalDateTime start, Duration duration, ChronoUnit frequency, long numberOfOccurrences) {
        super(title, start, duration, frequency);
        this.numberOfOccurrences = numberOfOccurrences;
    }

    /*******************************
     *
     * @return the termination date of this repetitive event
     */
    public LocalDate getTerminationDate() {
        if(terminationInclusive == null && numberOfOccurrences > 0){
            return getStart().toLocalDate().plus(numberOfOccurrences - 1, getFrequency());
        }
        return terminationInclusive;
    }

    public long getNumberOfOccurrences() {
        if(numberOfOccurrences == 0 ){
            return ((ChronoUnit.DAYS.between(getStart().toLocalDate(), terminationInclusive)) / (getFrequency().getDuration().toDays())) + 1;
        }
        return numberOfOccurrences;
    }

    @Override
    public boolean isInDay(LocalDate aDay) {
        boolean isInDay = false;
        if(getStart().toLocalDate().isEqual(aDay)) {
            isInDay = true;
        }else {
            if (!getExceptions().contains(aDay)) {
                LocalDate dateOccurence = getStart().toLocalDate();
                for (int i = 0; i < getNumberOfOccurrences(); i++) {
                    dateOccurence.plus(1, getFrequency());
                    isInDay = dateOccurence.isEqual(aDay);
                }
            }
        }
        return isInDay;
    }

}