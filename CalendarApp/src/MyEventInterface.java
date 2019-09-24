// Scott G. Edwards, 10/29/2017
// Assignment 4e
// CPT 236 Fall 2017


/**
 * Created by sedward3 on 10/2/2017.
 */


public interface MyEventInterface extends Comparable<MyEvent>       // 11/4/17 Updated: to "MyEvent" (from "MyTime")
{
    ////////////////////////////////////////////////////////////////////
    //  4e Assumptions:
    //      1) No event lasts more than a day. 
    //      2) All event times will not cross a midnight barrier
    ////////////////////////////////////////////////////////////////////
    

    // This interface is intended to implement the "MyEvent" class


    public  static  final   int COMPARE_TO_LT       =   -1;  // Note: is now "<= COMPARE_TO_LT"
    public  static  final   int COMPARE_TO_EQ       =   0;
    public  static  final   int COMPARE_TO_GT       =   +1;  // Note: is now ">= COMPARE_TO_GT"



    // Assume the following private variables
/*    
    private String   eventName = "";        // Name of the event                [ Valid:  should never be NULL AND (length > 0) ]
    private String   eventDesc = "";        // Long description of the event    [ Valid:  should never be NULL AND (length > 0) ]

    private MyDate   eventDate;             // Date of the event

    private MyTime   eventTimeStart;        // Start of the event
    private int      eventTimeDuration;     // Event durations in minutes       // *** Updated variable type ***
*/

    // Implement Getters() and Setters for the above with prefix "get" and "set"
    

    ////////////////////////////////////////////////////////////////////
    // Constuctors
    ////////////////////////////////////////////////////////////////////
/*    
    public MyEvent();
    public MyEvent(String name, String desc, MyDate dateStart, MyTime timeStart, int duration);
    public MyEvent(MyEvent copyEvent);
*/


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  Class methods
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // METHOD:  compareTo()
    // INPUT:   none
    // Routine:     compareTo()
    // Purpose:     Compare two MyEvent instances by starting date & time
    // Input:       otherEvent           2nd MyEvent instance
    // Output:      "Difference between values"
    // Indicators:  <= COMPARE_TO_LT (-1)  this.Time < otherTime
    //              == COMPARE_TO_EQ (0)   this.Time == otherTime
    //              >= COMPARE_TO_GT (+1)  this.Time > otherTime
    // Description: This function compare two MyEvent instances and returns a result indicating
    //              if this.Event is before (LT) or after (GT), or the same date (EQ)
    //              Note - return  COMPARE_TO_EQ if MyDate and MyTime variables are "the same"

    // Routine to compare current time to otherMyEvent (shows error until MyEvent class defined)
    //@Override
    public int compareTo(MyEvent otherMyEvent);     //  (this - otherMyEvent) : Neg:LessThan, 0:Equal, Pos:GreaterThan
    // Do not include "@Override" when populate in MyEvent


    // Routine:     toString()
    // Purpose:     Java "standard" object status string that is unique for this object
    // Input:       none
    // Output:      String         return value from MyEvent.printShort()
    // Description: This function returns a status string in a manner that is compatible with other
    //              Java objects
    //              Generate a text summary of the event (use MyDate.printLong() and MyTime.printUniversal() ) [free form otherwise]

    // Routine to print information
    @Override public String toString();


    // Routine:     doesTimeSpanOverlap()
    // Purpose:     Determine if a time span overlaps the current MyEvent instance's time
    // Input:       MyTime          start time for timespan
    //              int             number of minutes in time span
    // Output:      boolean         true if any overlap of time span to current instance's time
    // Description: This function checks to see if there is overlay

    // Routine to determine if range of time ckTime..ckTime+durationMIns overlaps current time
    public boolean doesTimeSpanOverlap(MyTime ckTime, int durationMins);


    // Routine:     doesTimeSpanOverlap()
    // Purpose:     Determine if a time span overlaps the current instance's time
    // Input:       int (*3)        H:M:S - Hour:Minute:Second
    //              int             number of minutes in time span
    // Output:      boolean         true if any overlap of time span to current instance's time
    // Description: This function returns
    //              Java objects

    public boolean doesTimeSpanOverlap(int hr, int mn, int sec, int durationMins);


    // Routine:     updateTime()
    // Purpose:     Updates the current time with the specified delta time
    // Input:       dH/dM/dS        deltaHours, deltaMinutes, deltaSeconds
    // Output:      MyTime          updated time span
    // Description: Updates the current instance's time.
    //              *** Ignore hours carry out (e.g., no date rollover) ***
    //              ***!! Note - MyTimeInterface & MyTime prototypes have changed as of #4e !!****

    // routine to update current time
    public MyTime updateTime( int deltaHours, int deltaMinutes, int deltaSeconds);


    // Routine:     isValid()
    // Purpose:     Validates the current instance variables for evemt
    //                  Specifically the MyDate & MyTime, and that name & description is not empty
    // Input:       none                (current instance variables)
    // Output:      boolean             true if all is valid
    // Description: This function re-validates the current instance variables for event

    // Routine to validate that the current time is valid
    public boolean isValid();


    // Routine:     isValid()
    // Purpose:     Validates the specified variables for date
    // Input:       String              eventName
    //              String              eventDesc
    //              MyDate              eventDate
    //              MyTime              eventTime
    //              int                 eventDuration
    // Output:      boolean             true if the specified M/D/Y is a valid date
    // Description: This function validates the specified parametersas a valid event

    public boolean isValid(String name, String desc, MyDate dateStart, MyTime timeStart, int duration);


    // Routine:     getDOW()
    // Purpose:     Return the Day-of-Week for the current instance's MyDate
    // Input:       none            (internal variables)
    // Output:      DOW             instance of DOW class containing the day-of-week
    // Description: This function calculates the DOW for the current eventDdate

    // Return the day of the week for the current eventDate
    public DOW getDOW();


    // Routine:     getDOW()
    // Purpose:     Return the Day-of-Week for the specified M/D/Y.
    // Input:       M/D/Y           three integers specifying the [Month, Day, Year]
    // Output:      DOW             instance of DOW class containing the day-of-week
    // Description: This function calculates the DOW for the specified M/D/Y date.
    //              Internal values of the current MyDate class are not modified/updated.

    // Assumption:  For #4d - only good dates are being passed (e.g., no error checking & no error return)

    // Return the day of the week for the indicated Date
    public DOW getDOW(int mn, int dy, int yr);


    // Routine:     getMonth1stDOW()
    // Purpose:     Return the Day-of-Week for the current instance's month & year, with day=1,
    // Input:       none            (internal variables)
    // Output:      DOW             instance of DOW class containing the day-of-week
    // Description: This function calculates the DOW for the current month & year, with day=1
    //              Internal values of the current MyDate class are not modified/updated.

    // Return the day of the week for the current eventDate's first day of the month
    public DOW getMonth1stDOW();



    // Routine:     getNumOfDaysInYear()
    // Purpose:     Return the number of days in the year for the current instance.
    // Input:       none            (internal variables)
    // Output:      int             # days (either 365 or 366)
    // Description: This function returns the number of days in the current year
    //              (get # days in year so that Calendar can display properly)

    // Return the number of days in the current year
    public int getNumOfDaysInYear();


    // Routine:     getNumOfDaysInYear()
    // Purpose:     Return the number of days in the year for the specified year
    // Input:       year            year to be used for calculation #days in year.
    // Output:      int             # days (either 365 or 366)
    // Description: This function returns the number of days in the specified year
    //              (get # days in year so that Calendar can display properly)

    // Return the number of days in the s[ecofoed year
    public int getNumOfDaysInYear(int yr);


    // Routine:     doEventsOverlap()
    // Purpose:     Determine if a time span overlaps the current MyEvent instance's time
    // Input:       MyTime          start time for timespan
    //              int             number of minutes in time span
    // Output:      boolean         true if any overlap of time span to current instance's time
    // Description: This function checks to see if there is overlay

    // Routine to determine if range of time ckTime..ckTime+durationMIns overlaps current time
    public boolean doEventsOverlap(MyEvent ckEvent);





}   //  end interface MyEventInterface

