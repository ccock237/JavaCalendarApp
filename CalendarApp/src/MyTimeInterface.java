// Scott G. Edwards, 10/2/2017
// Assignment 4c
// CPT 236 Fall 2017

// Note - this interface is based on Assignment #3a


/**
 * Created by sedward3 on 10/2/2017.
 */
public interface MyTimeInterface extends Comparable<MyTime>
{

    // Define the ranges for the variables (ranges shown on above variable declaration)
    public static final int  HOURS_MIN    =   0;
    public static final int  HOURS_MAX    =   23;
    public static final int  MINUTES_MIN  =   0;
    public static final int  MINUTES_MAX  =   59;
    public static final int  SECONDS_MIN  =   0;
    public static final int  SECONDS_MAX  =   59;

    public static final int   HOURS_NOON  =   12;

    public  static  final   int         COMPARE_TO_LT      =   -1;  // Note: is now "<= COMPARE_TO_LT"
    public  static  final   int         COMPARE_TO_EQ      =   0;
    public  static  final   int         COMPARE_TO_GT      =   +1;  // Note: is now ">= COMPARE_TO_GT"


    // AM or PM?    -   future implementation

    // Constructors
    //public MyTime();
    //public MyTime(int hr, int min, int sec);
    // public MyTime(int hr, int min, int sec, boolean trueForPM);
    //public MyTime(MyTime copyTime);


    // Routine:  "setter" for "hours"
    public boolean setHours(int newHours);

    // Routine:  "getter" for "hours"
    public int getHours();

    // Routine:  "setter" for "minutes"
    public boolean setMinutes(int newMins);

    // Routine:  "getter" for "minutes"
    public int getMinutes();

    // Routine:  "setter" for "seconds"
    public boolean setSeconds(int newSecs);

    // Routine:  "setter" for "seconds"
    public int getSeconds();

    // Routine:  "setter" for "hours" and "minutes" and "seconds"
    public boolean setTime(int newHours, int newMins, int newSecs);

    // This functions returns a string indicating "Universal Time" (24 hour) format:  HH:MM:SS (zero fill integers)
    public String printUniversal();

    // This functions returns a string indicating "Standard Time" (12 hour) format:  HH:MM:SS xM (zero fill integers)
    public String printStandard();


    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // 4c New prototypes
    ///////////////////////////////////////////////////////////////////////////////////////////////////


    // Add new constructors
    // public MyTime(int hr, int min, int sec);         // Explicit setting of H:M:S
    // public MyTime(MyTime copyFromMyTime);            // Copy H:M:S from "copyFromMyTime


    // Routine:     isValid()
    // Purpose:     Validates the current instance variables for time
    // Input:       none                (current instance variables)
    // Output:      boolean             true (should always be true since already validated, but check anyway)
    // Description: This function re-validates the current instance variables for time

    // Routine to validate that the current time is valid
    public boolean isValid();
                                                            // Include "@Override" when populate in MyDate

    // Routine:     isValid()
    // Purpose:     Validates the specified variables for date
    // Input:       H:M:S               hour:minute:second
    // Output:      boolean             true if the specified M/D/Y is a valid time
    // Description: This function  validates the specified M/D/Y variables as a valid time

    public boolean isValid(int newHours, int newMins, int newSecs);


    // Routine:     compareTo()
    // Purpose:     Compare two MyTime instances
    // Input:       otherTime           2nd MyTime instance
    // Output:      "Difference between values"
    // Indicators:  <= COMPARE_TO_LT (-1)  this.Time < otherTime
    //              == COMPARE_TO_EQ (0)   this.Time == otherTime
    //              >= COMPARE_TO_GT (+1)  this.Time > otherTime
    // Description: This function compare two MyTime instances and returns a result indicating
    //              if this.Time is before (LT) or after (GT), or the same date (EQ)

    // Routine to compare current time to otherMyTime.
    @Override public int compareTo(MyTime otherMyTime);     //  (this - otherMyTime) : Neg:LessThan, 0:Equal, Pos:GreaterThan
                                                            // Do not include "@Override" when populate in MyTime

    // Routine:     toString()
    // Purpose:     Java "standard" object status string that is unique for this object
    // Input:       none
    // Output:      String         return value from MyTime.printShort()
    // Description: This function returns a status string in a manner that is compatible with other
    //              Java objects

    // Routine to print information
    @Override public String toString();


    // Routine:     doesTimeSpanOverlap()
    // Purpose:     Determine if a time span overlaps the current instance's time
    // Input:       MyTime          start time for timespan
    //              int             number of minutes in time span
    // Output:      boolean         true if any overlap of time span to current instance's time
    // Description: This function checks to see if there is overlay

    // Routine to determine if range of time ckTime..ckTime+durationMIns overlaps current time
    public boolean doesTimeSpanOverlap(MyTime ckTime, int durationMins);


    // Routine:     updateTime()
    // Purpose:     Updates the current time with the specified delta time
    // Input:       dH/dM/dS        deltaHours, deltaMinutes, deltaSeconds
    // Output:      MyTime          return updated time in a MyTime instance
    // Description: Updates the current instance's time.
    //              *** Ignore hours carry out (e.g., no date rollover) ***
    // Revision:    #4e - changed return type from boolean to MyTime

    // routine to update current time
    public MyTime updateTime( int deltaHours, int deltaMinutes, int deltaSeconds);


}   //  end interface MyTimeInterface
