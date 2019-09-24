// Scott G. Edwards, 10/27/2017
// Assignment 4d
// CPT 236 Fall 2017



/**
 * Created by sedward3 on 10/2/2017.
 */


public interface MyDateInterface  extends Comparable<MyDate>
{

    public  static final    int MONTH_MIN   =   1;
    public  static final    int MONTH_MAX   =   12;
    public  static final    int DAY_MIN     =   1;
    public  static final    int DAY_MAX     =   31;     // Simplification - replaced by getNumOfDaysInMonth()
    public  static final    int YEAR_MIN    =   2000;
    public  static final    int YEAR_MAX    =   2099;

    public  static final    int NUM_DAYS_IN_NORMAL_YEAR =   365;
    public  static final    int NUM_DAYS_IN_LEAP_YEAR   =   NUM_DAYS_IN_NORMAL_YEAR + 1;

    public  static final    int MONTH_FEB   =   2;

    public  static final    boolean PRINT_LONG  =   true;
    public  static final    boolean PRINT_SHORT =   false;

    public  static  final   int COMPARE_TO_LT       =   -1;  // Note: is now "<= COMPARE_TO_LT"
    public  static  final   int COMPARE_TO_EQ       =   0;
    public  static  final   int COMPARE_TO_GT       =   +1;  // Note: is now ">= COMPARE_TO_GT"

    // Reference/Validation:  https://www.timeanddate.com/date/weekday.html
    public static   final   int DOW_DEFAULT_MONTH   =   MONTH_MIN;  // Code is assuming 1st month
    public static   final   int DOW_DEFAULT_DAY     =   DAY_MIN;    // Code is assuming 1st day (of first month)
//    public static   final   int DOW_DEFAULT_YEAR    =   2017;
//    public static   final   int DOW_DEFAULT_WKDAY   =   0;          // 1/1/2017 is Sunday (0)
    public static   final   int DOW_DEFAULT_YEAR    =   YEAR_MIN;
    public static   final   int DOW_DEFAULT_WKDAY   =   6;          // 1/1/2000 is Saturday (6)




//  Removed - "interface" does not like private variables nor constructors
//        private                 int month;
//        private                 int day;
//        private                 int year;
//
//        Constructor
//        public MyDate();



    // Routine:     setMonth()
    // Purpose:     "Setter" for Month
    // Input:       newMonth    [MONTH_MIN..MONTH_MAX]
    // Output:      true --> valid "newMonth" parameter
    // Description: This function sets the value of the "month" variable.
    //              The input value is ranged checked for valid values, and
    //              if invalid, then the current/previous internal value
    //              is not updated.
    public boolean setMonth(int newMonth);

    // Routine: "Getter" for Month
    public int getMonth();

    // Routine:  "Setter" for Day
    public boolean setDay(int newDay);

    // Routine: "Getter" for Day
    public int getDay();

    // Routine:  "Setter" for Year
    public boolean setYear(int newYear);

    // Routine: "Getter" for Year
    public int getYear();

    // Routine: "Setter" for date - setDate()
    public boolean setDate(int newMonth, int newDay, int newYear);

    // Routine:  printShort() - get date in short format "MM/DD/YY"
    public String printShort();

    // Routine:  printLong() - get date in short format "MM/DD/YYYY"
    public String printLong();

    // Routine to check internal year to see if it is a leap year
    public boolean isLeapYear();

    // Routine to check specified year (parm "yr") to see if it is a leap year
    public boolean isLeapYear(int yr);

    // Routine to get the number of days in the month for the current month & year
    public int getNumOfDaysInMonth();

    // Routine to get the number of days in the month for the specified month & current year
    public int getNumOfDaysInMonth(int mn);

    // Routine to get the number of days in the month for the specified month & year
//Future
//    public int getNumOfDaysInMonth(int mn, int yr);

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    // 4c New prototypes
    ///////////////////////////////////////////////////////////////////////////////////////////////////


    // Add new Constructors
    //public MyDate(int mon, int dy, int yr);           // Explicit M/D/Y setting
    //public MyDate(MyDate copyDate);                   // Copy M/D/Y from copyDate



    // Routine:     isValid()
    // Purpose:     Validates the current instance variables for date
    // Input:       none                (current instance variables)
    // Output:      boolean             true (should always be true since already validated, but check anyway)
    // Description: This function re-validates the current instance variables for date

    // Routine to validate that the current time is valid
    public boolean isValid();


    // Routine:     isValid()
    // Purpose:     Validates the specified variables for date
    // Input:       M/D/Y               month/day/year
    // Output:      boolean             true if the specified M/D/Y is a valid date
    // Description: This function validates the specified M/D/Y variables as a valid date

    public boolean isValid(int newMonth, int newDay, int newYear);


    // Routine:     compareTo()
    // Purpose:     Compare two MyDate instances
    // Input:       otherDate           2nd MyDate instance
    // Output:      "Difference between values"
    // Indicators:  <= COMPARE_TO_LT (-1)  this.Time < otherTime
    //              == COMPARE_TO_EQ (0)   this.Time == otherTime
    //              >= COMPARE_TO_GT (+1)  this.Time > otherTime
    // Description: This function compare two MyDate instances and returns a result indicating
    //              if this.Date is before (LT) or after (GT), or the same date (EQ)

    // Include "@Override" when populate in MyDate
    @Override public int compareTo(MyDate otherMyDate);     //  (this - otherMyDate) : Neg:LessThan, 0:Equal, Pos:GreaterThan
                                                            // Do not include "@Override" when populate in MyDate


    // Routine:     toString()
    // Purpose:     Java "standard" object status string that is unique for this object
    // Input:       none
    // Output:      String         return value from MyDate.pringShort()
    // Description: This function returns a status string in a manner that is compatible with other
    //              Java objects

    // Routine to print information
    @Override public String toString();                     // Call MyDate.printShort()


    // Routine:     getDOW()
    // Purpose:     Return the Day-of-Week for the current instance's M/D/Y.
    // Input:       none            (internal variables)
    // Output:      DOW             instance of DOW class containing the day-of-week
    // Description: This function calculates the DOW for the current M/D/Y date

    // Return the day of the week for the current Date
    public DOW getDOW();


    // Routine:     getDOW()
    // Purpose:     Return the Day-of-Week for the specified M/D/Y.
    // Input:       M/D/Y           three integers specifying the [Month, Day, Year]
    // Output:      DOW             instance of DOW class containing the day-of-week
    // Description: This function calculates the DOW for the specified M/D/Y date.
    //              Internal values of the current MyDate class are not modified/updated.

    // Assumption:  For #4d - only good dates are being passed (e.g., no error checking & no error return)

    // Return the day of the week for the indicated Date
    public DOW getDOW(int mn, int dy, int yr);              // FIXED - corrected parameters


    // Routine:     getMonth1stDOW()
    // Purpose:     Return the Day-of-Week for the current instance's month & year, with day=1,
    // Input:       none            (internal variables)
    // Output:      DOW             instance of DOW class containing the day-of-week
    // Description: This function calculates the DOW for the current month & year, with day=1
    //              Internal values of the current MyDate class are not modified/updated.

    // Return the day of the week for the current date's first day of the month
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



}   //  end interface MyDateInterface
