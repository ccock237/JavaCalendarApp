/*
	Assignment:		Assignment 5A
	Programmer:		Clayton Cockrell
	Date:			11/19/17
	Description:	This is the MyDate java class file. It contains all the methods and fields
	                necessary to keep track of a date object. This class is used in the MyEvent class
	                to keep track of start and end dates for events.
	Status:         Program compiles without error, and passes all unit test.

	Header:     a.  When a callers sets a date the most accurate way is to start broad and narrow in to the exact date.
	                For example, set the year first (broad) then the month (narrower) then the day (narrow). This ensures
	                that we get an accurate date. On the other hand, going from narrow to broad will cause problems.
	                Setting the date first (narrow) then the month (broader) then the year (broad) will have issues. When
	                setting the date this way, it can not be known if our day is a valid date until we reach the year and
	                then error checking would have to be implemented.

	            b.  Validation is used in each setter method to eliminate invalid arguments. When an invalid argument is
	                caught, the setter method will not alter the value the setter is meant to alter and returns False
	                meaning it failed.

	            c.  The day of the date will be altered if an invalid date is given. The day is always reduced to a number
	                that is a valid day for the date. For example if the previous date was 1/30/2011 and the month was
	                changed to 2 (February), 2/30/2011 is not a valid date. Since 2011 is not a leap year the last day of
	                February is 28. So, the day would be subtracted by 2 to get the valid date 2/28/2011.
*/

public class MyDate implements MyDateInterface {

    // Constants
    private static final int BEGINNING_DOW = 6;

    // Day, Month, and Year variables
    private int day;
    private int month;
    private int year;
    // Array of Days in each Month
    private int[] monthDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    // DOW object
    private DOW dayOfWeek;

    /*
        Name:           MyDate
        Input:          None
        Output:         None
        Description:    The MyDate primary constructor initializes the private
                        variables used in the class.
    */
    public MyDate() {
        day = DAY_MIN;
        month = MONTH_MIN;
        year = YEAR_MIN;
        dayOfWeek = getDOW(month, day, year);
    }

    /*
        Name:           MyDate
        Input:          month, day, and year
        Output:         None
        Description:    The MyDate constructor sets the date variables to
                        the month, day, and year provided.
    */
    public MyDate(int month_set, int day_set, int year_set) {
        day = DAY_MIN;
        month = MONTH_MIN;
        year = YEAR_MIN;
        dayOfWeek = getDOW(month, day, year);
        // If setting the date fails, set the defaults
        if (!setDate(month_set, day_set, year_set)) {
            day = DAY_MIN;
            month = MONTH_MIN;
            year = YEAR_MIN;
            dayOfWeek = getDOW(month, day, year);
        }
    }

    /*
        Name:           MyDate
        Input:          MyDate object
        Output:         None
        Description:    The MyDate constructor sets the date variables to the
                        month, day and year variables of the provided MyDate
                        object.
    */
    public MyDate(MyDate copyDate) {
        day = DAY_MIN;
        month = MONTH_MIN;
        year = YEAR_MIN;
        dayOfWeek = getDOW(month, day, year);
        // If setting the date fails, set the defaults
        if (!setDate(copyDate.getMonth(), copyDate.getDay(), copyDate.getYear())) {
            day = DAY_MIN;
            month = MONTH_MIN;
            year = YEAR_MIN;
            dayOfWeek = getDOW(month, day, year);
        }
    }


    /*
        Name:           getDay
        Input:          None
        Output:         The value of the day variable
        Description:    The getDay method returns the value of the
                        day variable.
    */
    public int getDay() {
        return(day);
    }

    /*
        Name:           getMonth
        Input:          None
        Output:         The value of the month variable
        Description:    The getMonth method returns the value of the
                        month variable.
    */
    public int getMonth() {
        return(month);
    }

    /*
        Name:           getYear
        Input:          None
        Output:         The value of the year variable
        Description:    The getYear method returns the value of the
                        year variable.
    */
    public int getYear() {
        return(year);
    }

    /*
        Name:           setDay
        Input:          The day
        Output:         True or False
        Description:    The setDay method sets the day to the
                        input provided if it is between a min
                        and max value and returns True; otherwise,
                        the function returns False.
    */
    public boolean setDay(int set_day) {
        boolean success = false;
        // Validate the set_day variable
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if (Validate(set_day, DAY_MIN, DAY_MAX)) {
                    day = set_day;
                    success = true;
                }
                break;
            case 2:
                isLeapYear();
                if (Validate(set_day, DAY_MIN, monthDays[month - 1])) {
                    day = set_day;
                    success = true;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (Validate(set_day, DAY_MIN, DAY_MAX - 1)) {
                    day = set_day;
                    success = true;
                }
                break;
        }
        dayOfWeek = getDOW(month, day, year);
        return success;
    }

    /*
        Name:           setMonth
        Input:          The month
        Output:         True or False
        Description:    The setMonth method sets the month to the
                        input provided if it is between a min and
                        max value and returns True; otherwise, the
                        function returns False.
    */
    public boolean setMonth(int set_month) {
        boolean success = false;
        // Validate the set_month variables
        if (Validate(set_month, MONTH_MIN, MONTH_MAX)) {
            switch (day) {
                case 29:
                    // Check if the new month is February
                    if (!(!isLeapYear() && set_month == 2)) {
                        month = set_month;
                        dayOfWeek = getDOW(month, day, year);
                        success = true;
                    }
                    break;
                case 30:
                    if (set_month != 2) {
                        month = set_month;
                        dayOfWeek = getDOW(month, day, year);
                        success = true;
                    }
                    break;
                case 31:
                    switch (set_month) {
                        case 2:
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            success = false;
                            break;
                        default:
                            month = set_month;
                            dayOfWeek = getDOW(month, day, year);
                            success = true;
                            break;
                    }
                    break;
                default:
                    month = set_month;
                    success = true;
                    break;
            }

        }
        return success;
    }

    /*
        Name:           setYear
        Input:          The year
        Output:         True or False
        Description:    The setYear method sets the year to the
                        input provided if it is between a min and
                        max value and returns True; otherwise, the
                        function returns False.
    */
    public boolean setYear(int set_year) {
        boolean success = false;
        // Validate the set_year variable
        if (Validate(set_year, YEAR_MIN, YEAR_MAX)) {
            // Make sure no non-leap year date is February 29th
            if (!(!isLeapYear(set_year) && month == 2 && day == 29)) {
                year = set_year;
                dayOfWeek = getDOW(month, day, year);
                success = true;
            }
        }
        return success;
    }

    /*
        Name:           setDate
        Input:          The month, day, and year
        Output:         None
        Description:    The setDate method sets the month, day, and year of the time to the
                        input provided if they are between a min and max value and returns
                        True; otherwise, the function returns False.
    */
    public boolean setDate(int set_month, int set_day, int set_year) {
        boolean success = false;
        // Validate the set_month, set_day, and set_year variables
        // This does not update the day, month, or year variables if the set_* value is invalid
        // setDate(1, 1, -1) will return false because setYear(-1) returns false
        if (this.setYear(set_year) && this.setMonth(set_month) && this.setDay(set_day)) {
            success = true;
            dayOfWeek = getDOW(month, day, year);
        }
        return success;
    }

    /*
        Name:           printShort
        Input:          None
        Output:         The date
        Description:    The printShort method prints the date in MM/DD/YY format.
    */
    public String printShort() {
        year %= 100;
        System.out.format("%02d/%02d/%02d", month, day, year);
        return(String.format("%02d/%02d/%02d", month, day, year));
    }

    /*
        Name:           printLong
        Input:          None
        Output:         The date
        Description:    The printLong method prints the date in MM/DD/YYYY format.
    */
    public String printLong() {
        System.out.format("%02d/%02d/%04d", month, day, year);
        return(String.format("%02d/%02d/%04d", month, day, year));
    }

    /*
        Name:           Validate
        Input:          The check value, lowest value, and highest value
        Output:         True or False
        Description:    The Validate method returns True or False depending on if the check value
                        entered is between the lowest and highest values. This includes the check
                        value being equal to either the lowest or highest value.
    */
    private boolean Validate(int checkValue, int lowestValue, int highestValue) {
        boolean validated = false;
        if (checkValue >= lowestValue && checkValue <= highestValue) validated = true;
        return validated;
    }

    /*
        Name:           isLeapYear
        Input:          None
        Output:         True or False
        Description:    The isLeapYear method calls the below isLeapYear passing this.year as the
                        argument. This method return True or False if this.year is a Leap Year.
    */
    public boolean isLeapYear() {
        boolean leapYear = false;
        if (isLeapYear(this.year)) leapYear = true;
        return leapYear;
    }

    /*
        Name:           isLeapYear
        Input:          The year
        Output:         True or False
        Description:    The isLeapYear method test the passed in argument. If the argument year is
                        a Leap Year, the method will return True; otherwise, the method returns False.
    */
    public boolean isLeapYear(int testYear) {
        // Constant values for determining the Leap Year
        final int CHECK_EVEN = 0;
        final int LEAP_YEAR_TEST_4 = 4;
        final int LEAP_YEAR_TEST_100 = 100;
        final int LEAP_YEAR_TEST_400 = 400;
        boolean isLeap = false;
        // Set February's day count to 28
        monthDays[1] = 28;
        if (testYear % LEAP_YEAR_TEST_4 == CHECK_EVEN) {
            if (testYear % LEAP_YEAR_TEST_100 == CHECK_EVEN) {
                if (testYear % LEAP_YEAR_TEST_400 == CHECK_EVEN) {
                    isLeap = true;
                    // Set February's day count to 29
                    monthDays[1] = 29;
                }
            } else {
                isLeap = true;
                // Set February's day count to 29
                monthDays[1] = 29;
            }
        }
        return isLeap;
    }

    /*
        Name:           getNumOfDaysInMonth
        Input:          None
        Output:         Number of days in the current month
        Description:    The getNumOfDaysInMonth method calls the getNumOfDaysInMonth and passes this.month
                        as the argument. The method returns the number of days from the current month.
    */
    public int getNumOfDaysInMonth() {
        return getNumOfDaysInMonth(this.month);
    }

    /*
        Name:           getNumOfDaysInMonth
        Input:          the month
        Output:         Number of days in the current month
        Description:    The getNumOfDaysInMonth method takes in a integer month and returns that months number
                        of days.
    */
    public int getNumOfDaysInMonth(int mon) {
        /*
            Call isLeapYear() to check if the year is a leap year; therefore, making the days in February
            29 instead of 28.
        */
        isLeapYear();
        if (mon > 12) {
            mon = 12;
        }
        return monthDays[mon - 1];
    }

    /*
        Name:           isValid
        Input:          None
        Output:         True or False
        Description:    The isValid method tests the current date. If the
                        current date is a valid date, the method returns
                        True; otherwise, it returns False.
    */
    public boolean isValid() {
        return isValid(month, day, year);
    }

    /*
        Name:           isValid
        Input:          A month, day and year
        Output:         True or False
        Description:    The isValid method tests a date. If the
                        date is a valid date, the method returns
                        True; otherwise, it returns False.
    */
    public boolean isValid(int newMonth, int newDay, int newYear) {
        boolean success = false;
        if (Validate(newYear, YEAR_MIN, YEAR_MAX) && Validate(newMonth, MONTH_MIN, MONTH_MAX) && Validate(newDay, DAY_MIN, DAY_MAX)) success = true;
        return success;
    }

    /*
        Name:           compareTo
        Input:          MyDate object
        Output:         -1 for less than, 0 for equal to, and 1 for greater than
        Description:    The compareTo method takes in a MyDate object and compares it
                        with the current MyDate object. The method returns -1 if the
                        MyDate argument is less than the current MyDate object. The
                        method returns 0 if the MyDate objects are equal. The method
                        returns 1 if the MyDate argument is greater than the current
                        MyDate object.
    */
    public int compareTo(MyDate otherMyDate) {
        int compares = 2;
        // Compare the years
        if (this.getYear() > otherMyDate.getYear()) {
            // The argument MyDate is less than the current MyDate object
            compares = 1;
        }
        else if (this.getYear() < otherMyDate.getYear()) {
            // The argument MyDate is greater than the current MyDate object
            compares = -1;
        }
        else {
            // The years are equal check the month
            if (this.getMonth() > otherMyDate.getMonth()) {
                // The argument MyDate is less than the current MyDate object
                compares = 1;
            }
            else if (this.getMonth() < otherMyDate.getMonth()) {
                // The argument MyDate is greater than the current MyDate object
                compares = -1;
            }
            else {
                // The months are equal check the day
                if (this.getDay() > otherMyDate.getDay()) {
                    // The argument MyDate is less than the current MyDate object
                    compares = 1;
                }
                else if (this.getDay() < otherMyDate.getDay()) {
                    // The argument MyDate is greater than the current MyDate object
                    compares = -1;
                }
                else {
                    // The days are equal
                    compares = 0;
                }
            }
        }
        return compares;
    }

    /*
        Name:           getDOW
        Input:          None
        Output:         The DOW object of the date
        Description:    The getDOW method returns the DOW object for the
                        current date.
    */
    public DOW getDOW() {
        return getDOW(month, day, year);
    }

    /*
        Name:           getDOW
        Input:          A month, day, and year
        Output:         The DOW object of the date
        Description:    The getDOW method returns the DOW object for the
                        date specified.
    */
    public DOW getDOW(int mn, int dy, int yr) {
        int startYr = YEAR_MIN;
        int startMn = MONTH_MIN;
        int startDy = DAY_MIN;
        int dayAmount = 0;
        int set_DOW = -1;

        while (startYr != yr) {
            dayAmount += getNumOfDaysInYear(startYr);
            startYr++;
        }

        while (startMn != mn) {
            dayAmount += getNumOfDaysInMonth(startMn);
            startMn++;
        }

        while (startDy != dy) {
            dayAmount++;
            startDy++;
        }

        set_DOW = ((dayAmount + BEGINNING_DOW) % 7);

        return new DOW(set_DOW);
    }

    /*
        Name:           getMonth1stDOW
        Input:          None
        Output:         The DOW object for the first day in the month
        Description:    The getMonth1stDOW method returns the DOW object for the
                        first day of the month.
    */
    public DOW getMonth1stDOW() {
        int startYr = YEAR_MIN;
        int startMn = MONTH_MIN;
        int dayAmount = 0;
        int set_DOW = -1;

        while (startYr != year) {
            dayAmount += getNumOfDaysInYear(startYr);
            startYr++;
        }

        while (startMn != month) {
            dayAmount += getNumOfDaysInMonth(startMn);
            startMn++;
        }

        set_DOW = ((dayAmount + BEGINNING_DOW) % 7);

        return new DOW(set_DOW);
    }

    /*
        Name:           getNumOfDaysInYear
        Input:          None
        Output:         The number of days in current year
        Description:    The getNumOfDaysInYear method returns the number of days
                        in the current year.
    */
    public int getNumOfDaysInYear() {
        return getNumOfDaysInYear(year);
    }

    /*
        Name:           getNumOfDaysInYear
        Input:          year
        Output:         The number of days in year
        Description:    The getNumOfDaysInYear method returns the number of days in
                        the year specified.
    */
    public int getNumOfDaysInYear(int yr) {
        int daysInYear = 0;
        if (isLeapYear(yr)) {
            daysInYear = 366;
        } else {
            daysInYear = 365;
        }
        return daysInYear;
    }

    /*
        Name:           toString
        Input:          None
        Output:         The string format of the current date
        Description:    The toString method returns the string format of the current date.
    */
    public String toString() {
        return String.format("%02d/%02d/%04d", month, day, year);
    }
}