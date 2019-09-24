/*
	Assignment:		Assignment 5A
	Programmer:		Clayton Cockrell
	Date:		    11/19/17
	Description:	This is the MyTime java class file. It contains the methods and fields
	                necessary to keep track of a time object. This class is used in the MyEvent class
	                to keep track of start and end times for events.
	Status:         Program compiles without error, and passes all unit tests
*/

public class MyTime implements MyTimeInterface {

    private final String AM_PM_RESET = "0";
    // Declare the variables for the time
    private int hours;
    private int minutes;
    private int seconds;
    // The AM_or_PM variable is used when the user sets the time in standard format
    private String AM_or_PM;

    /*
        Name:           MyTime
        Input:          None
        Output:         None
        Description:    The Primary MyTime constructor initializes the private
                        variables used in the class.
    */
    public MyTime() {
        hours = HOURS_MIN;
        minutes = MINUTES_MIN;
        seconds = SECONDS_MIN;
        AM_or_PM = AM_PM_RESET;
    }

    /*
        Name:           MyTime
        Input:          hour, minute, and second
        Output:         None
        Description:    This MyTime constructor sets the time from the arguments
                        provided.
    */
    public MyTime(int hours_set, int minutes_set, int seconds_set) {
        if (setTime(hours_set, minutes_set, seconds_set)) {
            AM_or_PM = AM_PM_RESET;
        }
        else {
            hours = HOURS_MIN;
            minutes = MINUTES_MIN;
            seconds = SECONDS_MIN;
            AM_or_PM = AM_PM_RESET;
        }
    }

    /*
        Name:           MyTime
        Input:          hour, minute, and second
        Output:         None
        Description:    This MyTime constructor sets the time from the arguments
                        provided with AM or PM.
    */
    public MyTime(int hours_set, int minutes_set, int seconds_set, String AM_PM) {
        if (!setTime(hours_set, minutes_set, seconds_set, AM_PM)) {
            hours = HOURS_MIN;
            minutes = MINUTES_MIN;
            seconds = SECONDS_MIN;
            AM_or_PM = AM_PM_RESET;
        }
    }

    /*
        Name:           MyTime
        Input:          None
        Output:         None
        Description:    This MyTime constructor sets the time from a MyTime
                        object argument.
    */
    public MyTime(MyTime copyFromMyTime) {
        // If setting the time fails, set the defaults
        if (setTime(copyFromMyTime.getHours(), copyFromMyTime.getMinutes(), copyFromMyTime.getSeconds())) {
            AM_or_PM = AM_PM_RESET;
        }
        else {
            hours = HOURS_MIN;
            minutes = MINUTES_MIN;
            seconds = SECONDS_MIN;
            AM_or_PM = AM_PM_RESET;
        }
    }

    /*
        Name:           GetHours
        Input:          None
        Output:         The value of the hours variable
        Description:    The GetHours method returns the value of the
                        hours variable.
    */
    public int getHours() {
        return(hours);
    }

    /*
        Name:           GetMinutes
        Input:          None
        Output:         The value of the minutes variable
        Description:    The GetMinutes method returns the value of the
                        minutes variable.
    */
    public int getMinutes() {
        return(minutes);
    }

    /*
        Name:           GetSeconds
        Input:          None
        Output:         The value of the seconds variable
        Description:    The GetSeconds method returns the value of the
                        seconds variable.
    */
    public int getSeconds() {
        return(seconds);
    }

    /*
        Name:           SetHours
        Input:          The hour with AM or PM
        Output:         None
        Description:    The SetHours method sets the hour of the time to the
                        input provided with AM or PM.
    */
    public void setHours(int hours_set, String AM_PM) {
        // Validate the hours_set variable
        if (Validate(hours_set, HOURS_MIN, HOURS_MAX)) {
            hours = hours_set;
        }
        // Validate the AM_PM variable
        if (AM_PM.toUpperCase().equals("PM") || AM_PM.toUpperCase().equals("AM")) {
            AM_or_PM = AM_PM.toUpperCase();
        }
    }

    /*
        Name:           SetHours
        Input:          The hour
        Output:         True or False
        Description:    The SetHours method sets the hour of the time to the
                        input provided.
    */
    public boolean setHours(int hours_set) {
        boolean success = false;
        // Validate the hours_set variables
        if (Validate(hours_set, HOURS_MIN, HOURS_MAX)) {
            hours = hours_set;
            AM_or_PM = "0";
            success = true;
        }
        return success;
    }

    /*
        Name:           SetMinutes
        Input:          The minute
        Output:         True or False
        Description:    The SetMinutes method sets the minute of the time to the
                        input provided.
    */
    public boolean setMinutes(int minutes_set) {
        boolean success = false;
        // Validate the minutes_set variable
        if (Validate(minutes_set, MINUTES_MIN, MINUTES_MAX)) {
            minutes = minutes_set;
            success = true;
        }
        return success;
    }

    /*
        Name:           SetSeconds
        Input:          The second
        Output:         True or False
        Description:    The SetSeconds method sets the second of the time to the
                        input provided.
    */
    public boolean setSeconds(int seconds_set) {
        boolean success = false;
        // Validate the seconds_set variable
        if (Validate(seconds_set, SECONDS_MIN, SECONDS_MAX)) {
            seconds = seconds_set;
            success = true;
        }
        return success;
    }

    /*
        Name:           SetTime
        Input:          The hour, minute, second, and AM or PM
        Output:         True or False
        Description:    The SetTime method sets the hour, minute, and second of the time to the
                        input provided with AM or PM.
    */
    public boolean setTime(int hours_set, int minutes_set, int seconds_set, String AM_PM) {
        boolean success = false;
        // Validate the hours_set, minutes_set, and seconds_set variables
        if (Validate(hours_set, HOURS_MIN, HOURS_MAX) && Validate(minutes_set, MINUTES_MIN, MINUTES_MAX) && Validate(seconds_set, SECONDS_MIN, SECONDS_MAX)) {
            hours = hours_set;
            minutes = minutes_set;
            seconds = seconds_set;
            success = true;
        }
        // Validate the AM_PM variable
        if (AM_PM.toUpperCase().equals("PM") || AM_PM.toUpperCase().equals("AM")) {
            AM_or_PM = AM_PM.toUpperCase();
        }
        return success;
    }

    /*
        Name:           SetTime
        Input:          The hour, minute, and second
        Output:         True or False
        Description:    The SetTime method sets the hour, minute, and second of the time to the
                        input provided.
    */
    public boolean setTime(int hours_set, int minutes_set, int seconds_set) {
        boolean success = false;
        // Validate the hours_set, minutes_set, and seconds_set variables
        if (Validate(hours_set, HOURS_MIN, HOURS_MAX) && Validate(minutes_set, MINUTES_MIN, MINUTES_MAX) && Validate(seconds_set, SECONDS_MIN, SECONDS_MAX)) {
            hours = hours_set;
            minutes = minutes_set;
            seconds = seconds_set;
            AM_or_PM = "0";
            success = true;
        }
        return success;
    }

    /*
        Name:           PrintUniversal
        Input:          None
        Output:         The time
        Description:    The PrintUniversal method prints the time in universal format.
    */
    public String printUniversal() {
        String universalFormat = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        System.out.println(universalFormat);
        return universalFormat;
    }

    /*
        Name:           PrintStandard
        Input:          None
        Output:         The time
        Description:    The PrintStandard method prints the time in standard format.
    */
    public String printStandard() {
        String midday = "";
        if (hours > 12) {
            AM_or_PM = "PM";
            hours -= 12;
        }
        else if (hours == 12 && AM_or_PM.equals("0")) {
            AM_or_PM = "PM";
        }
        else if (hours == 0) {
            AM_or_PM = "AM";
            hours = 12;
        }
        else if (AM_or_PM.equals("0")){
            AM_or_PM = "AM";
        }

        midday = AM_or_PM;

        String standardFormat = String.format("%02d:%02d:%02d %2s", hours, minutes, seconds, midday);
        System.out.println(standardFormat);
        return standardFormat;
    }

    /*
        Name:           isValid
        Input:          None
        Output:         True or False
        Description:    The isValid method tests the current time. If the
                        current time is a valid time, the method returns
                        True; otherwise, it returns False.
    */
    public boolean isValid() {
        boolean success = false;
        if (Validate(hours, HOURS_MIN, HOURS_MAX) && Validate(minutes, MINUTES_MIN, MINUTES_MAX) && Validate(seconds, SECONDS_MIN, SECONDS_MAX)) success = true;
        return success;
    }

    /*
        Name:           isValid
        Input:          An hour, minute, and second
        Output:         True or False
        Description:    The isValid method tests a time. If the
                        time is a valid time, the method returns
                        True; otherwise, it returns False.
    */
    public boolean isValid(int newHours, int newMins, int newSecs) {
        boolean success = false;
        if (Validate(newHours, HOURS_MIN, HOURS_MAX) && Validate(newMins, MINUTES_MIN, MINUTES_MAX) && Validate(newSecs, SECONDS_MIN, SECONDS_MAX)) success = true;
        return success;
    }

    /*
            Name:           compareTo
            Input:          MyTime object
            Output:         -1 for less than, 0 for equal to, and 1 for greater than
            Description:    The compareTo method takes in a MyTime object and compares it
                            with the current MyTime object. The method returns 1 if the
                            MyTime argument is less than the current MyTime object. The
                            method returns 0 if the MyTime objects are equal. The method
                            returns -1 if the MyTime argument is greater than the current
                            MyTime object.
        */
    public int compareTo(MyTime otherMyTime) {
        int compares = 2;
        // Compare the hours
        if (this.getHours() > otherMyTime.getHours()) {
            // The argument MyTime is less than the current MyTime object
            compares = 1;
        }
        else if (this.getHours() < otherMyTime.getHours()) {
            // The argument MyTime is greater than the current MyTime object
            compares = -1;
        }
        else {
            // The hours are equal check the minutes
            if (this.getMinutes() > otherMyTime.getMinutes()) {
                // The argument MyTime is less than the current MyTime object
                compares = 1;
            }
            else if (this.getMinutes() < otherMyTime.getMinutes()) {
                // The argument MyTime is greater than the current MyTime object
                compares = -1;
            }
            else {
                // The minutes are equal check the seconds
                if (this.getSeconds() > otherMyTime.getSeconds()) {
                    // The argument MyTime is less than the current MyTime object
                    compares = 1;
                }
                else if (this.getSeconds() < otherMyTime.getSeconds()) {
                    // The argument MyTime is greater than the current MyTime object
                    compares = -1;
                }
                else {
                    // The seconds are equal
                    compares = 0;
                }
            }
        }
        return compares;
    }

    /*
        Name:           doesTimeSpanOverlap
        Input:          MyTime object and a minute duration
        Output:         True or False
        Description:    The doesTimeSpanOverlap method takes in a MyTime object
                        and adds the duration minutes and checks if the argument
                        MyTime object is greater than the current MyTime object.
    */
    public boolean doesTimeSpanOverlap(MyTime ckTime, int durationMins) {
        MyTime test = new MyTime(ckTime);
        int newMinutes = test.getMinutes() + durationMins;
        int quotient = 0;
        int addHours = 0;
        boolean success = false;

        // Validate that the initial ckTime is less than or equal to the current MyTime object
        if (this.compareTo(test) >= 0) {
            if (newMinutes > MINUTES_MAX) {
                quotient = (newMinutes / (MINUTES_MAX + 1));
                addHours = quotient;
                newMinutes -= ((MINUTES_MAX + 1) * quotient);
            }

            if (addHours > 0) {
                if (test.setHours((test.getHours() + addHours))) {
                    // Set the new minutes
                    if (test.setMinutes(newMinutes)) {
                        // Check if the argument time is greater than the current time
                        if (this.compareTo(test) == -1 || this.compareTo(test) == 0) {
                            success = true;
                        }
                    }
                }
            } else {
                // Set the new minutes
                if (test.setMinutes(newMinutes)) {
                    // Check if the argument time is greater than the current time
                    if (this.compareTo(test) == -1 || this.compareTo(test) == 0) {
                        success = true;
                    }
                }
            }
        }
        return success;
    }

    /*
        Name:           updateTime
        Input:          New time to add
        Output:         MyTime object
        Description:    The updateTime method takes in delta hours, minutes,
                        and seconds and add these to the current time. The method
                        return True on success and False on failure.
    */
    public MyTime updateTime(int deltaHours, int deltaMinutes, int deltaSeconds) {
        MyTime newTime = null;
        boolean success = false;
        int quotient = 0;

        // Adjust the arguments for carrying over
        if (deltaSeconds > SECONDS_MAX || (deltaSeconds * -1) > SECONDS_MAX) {
            // Update the minutes and seconds
            quotient = (deltaSeconds / (SECONDS_MAX + 1));
            deltaSeconds -= ((SECONDS_MAX + 1) * quotient);
            deltaMinutes += quotient;
        }

        if (deltaMinutes > MINUTES_MAX || (deltaMinutes * -1) > MINUTES_MAX) {
            // Update the hours and minutes
            quotient = (deltaMinutes / (MINUTES_MAX + 1));
            deltaMinutes -= ((MINUTES_MAX + 1) * quotient);
            deltaHours += quotient;
        }

        if (deltaHours > HOURS_MAX || (deltaHours * -1) > HOURS_MAX) {
            // Update the day and hour
            quotient = (deltaHours / (HOURS_MAX + 1));
            deltaHours -= ((HOURS_MAX + 1) * quotient);
            // Update the day ...

        }

        // Change the second
        if (deltaSeconds < 0) {
            if (Validate((deltaSeconds * -1), SECONDS_MIN, SECONDS_MAX)) {
                // Subtract seconds
                if ((deltaSeconds * -1) > seconds) {
                    // Adjust the seconds
                    seconds = (seconds + deltaSeconds) + (SECONDS_MAX + 1);
                    // Subtract 1 minute
                    minutes -= 1;
                    if (minutes < MINUTES_MIN) {
                        // Change to valid hour
                        minutes += MINUTES_MAX + 1;
                        // Subtract 1 hour
                        hours -= 1;
                        if (hours < HOURS_MIN) {
                            // Change to valid hour
                            hours += HOURS_MAX + 1;
                        }
                    }
                } else {
                    // Subtract
                    seconds += deltaSeconds;
                }
                success = true;
            }
            else {
                success = false;
            }
        } else {
            if (Validate(deltaSeconds, SECONDS_MIN, SECONDS_MAX)) {
                // Add seconds
                seconds += deltaSeconds;
                if (seconds > SECONDS_MAX) {
                    seconds -= SECONDS_MAX + 1;
                    // Adjust minute
                    minutes += 1;
                    if (minutes > MINUTES_MAX) {
                        // Change to valid hour
                        minutes -= MINUTES_MAX + 1;
                        // Subtract 1 hour
                        hours += 1;
                        if (hours > HOURS_MAX) {
                            // Change to valid hour
                            hours -= HOURS_MAX + 1;
                        }
                    }
                }
                success = true;
            }
            else {
                success = false;
            }
        }
        // Change the minute
        if (deltaMinutes < 0) {
            if (Validate((deltaMinutes * -1), MINUTES_MIN, MINUTES_MAX)) {
                // Subtract minutes
                if ((deltaMinutes * -1) > minutes) {
                    // Adjust the minutes
                    minutes = (minutes + deltaMinutes) + (MINUTES_MAX + 1);
                    // Subtract 1 hour
                    hours -= 1;
                    if (hours < HOURS_MIN) {
                        // Change to valid hour
                        hours += HOURS_MAX + 1;
                    }
                } else {
                    // Subtract
                    minutes += deltaMinutes;
                }
                success = true;
            }
            else {
                success = false;
            }
        } else {
            if (Validate(deltaMinutes, MINUTES_MIN, MINUTES_MAX)) {
                // Add minutes
                minutes += deltaMinutes;
                if (minutes > MINUTES_MAX) {
                    minutes -= MINUTES_MAX + 1;
                    // Adjust hours
                    hours += 1;
                    if (hours > HOURS_MAX) {
                        // Change to valid hour
                        hours -= HOURS_MAX + 1;
                    }
                }
                success = true;
            }
            else {
                success = false;
            }
        }
        // Change the hour
        if (deltaHours < 0) {
            if (Validate((deltaHours * -1), HOURS_MIN, HOURS_MAX)) {
                // Subtract hours
                if ((deltaHours * -1) > hours) {
                    // Adjust the hours
                    hours += deltaHours;
                    hours += HOURS_MAX + 1;
                    // Adjust the day
                } else {
                    // Subtract
                    hours += deltaHours;
                }
                success = true;
            }
            else {
                success = false;
            }
        } else {
            if (Validate(deltaHours, HOURS_MIN, HOURS_MAX)) {
                // Add hours
                hours += deltaHours;
                if (hours > HOURS_MAX) {
                    // Adjust hours
                    hours -= HOURS_MAX + 1;
                    // Adjust day
                }
                success = true;
            }
            else {
                success = false;
            }
        }
        if (success) newTime = new MyTime(hours, minutes, seconds);
        return newTime;
    }

    /*
        Name:           updateStandardTime
        Input:          New time to add
        Output:         A MyTime object
        Description:    The updateStandardTime method takes in delta hours, minutes,
                        and seconds and add these to the current time. The method
                        return True on success and False on failure. This method updates
                        by validating the time is in standard time.
    */
    public MyTime updateStandardTime(int deltaHours, int deltaMinutes, int deltaSeconds) {
        MyTime newTime = null;
        boolean success = false;
        int quotient = 0;

        // Adjust the arguments for carrying over
        if (deltaSeconds > SECONDS_MAX || (deltaSeconds * -1) > SECONDS_MAX) {
            // Update the minutes and seconds
            quotient = (deltaSeconds / (SECONDS_MAX + 1));
            deltaSeconds -= ((SECONDS_MAX + 1) * quotient);
            deltaMinutes += quotient;
        }

        if (deltaMinutes > MINUTES_MAX || (deltaMinutes * -1) > MINUTES_MAX) {
            // Update the hours and minutes
            quotient = (deltaMinutes / (MINUTES_MAX + 1));
            deltaMinutes -= ((MINUTES_MAX + 1) * quotient);
            deltaHours += quotient;
        }

        if (deltaHours > HOURS_NOON || (deltaHours * -1) > HOURS_NOON) {
            // Update the day and hour
            quotient = (deltaHours / HOURS_NOON);
            deltaHours -= (HOURS_NOON * quotient);
            // Change the midday the number of times that the hours passes 12 AM and 12 PM
            for (int twelvehours = 0; twelvehours < quotient; twelvehours++) {
                if (AM_or_PM.equals("AM")) {
                    AM_or_PM = "PM";
                } else if (AM_or_PM.equals("PM")) {
                    AM_or_PM = "AM";
                    // Update the day ...

                }
            }
        }

        // Change the second
        if (deltaSeconds < 0) {
            if (Validate((deltaSeconds * -1), SECONDS_MIN, SECONDS_MAX)) {
                // Subtract seconds
                if ((deltaSeconds * -1) > seconds) {
                    // Adjust the seconds
                    seconds = (seconds + deltaSeconds) + SECONDS_MAX;
                    // Subtract 1 minute
                    minutes -= 1;
                    if (minutes < MINUTES_MIN) {
                        // Change to valid hour
                        minutes += MINUTES_MAX;
                        // Subtract 1 hour
                        hours -= 1;
                        if (hours <= HOURS_MIN) {
                            // Change to valid hour
                            hours += HOURS_NOON;
                            if (AM_or_PM.equals("AM")) AM_or_PM = "PM";
                            else if (AM_or_PM.equals("PM")) AM_or_PM = "AM";
                        }
                    }
                } else {
                    // Subtract
                    seconds += deltaSeconds;
                }
                success = true;
            }
            else {
                success = false;
            }
        } else {
            if (Validate(deltaSeconds, SECONDS_MIN, SECONDS_MAX)) {
                // Add seconds
                seconds += deltaSeconds;
                if (seconds > SECONDS_MAX) {
                    seconds -= SECONDS_MAX + 1;
                    // Adjust minute
                    minutes += 1;
                    if (minutes > MINUTES_MAX) {
                        // Change to valid hour
                        minutes -= MINUTES_MAX + 1;
                        // Subtract 1 hour
                        hours += 1;
                        if (hours > HOURS_NOON) {
                            // Change to valid hour
                            hours -= HOURS_NOON;
                            if (AM_or_PM.equals("AM")) AM_or_PM = "PM";
                            else if (AM_or_PM.equals("PM")) AM_or_PM = "AM";
                        }
                    }
                }
                success = true;
            }
            else {
                success = false;
            }
        }
        // Change the minute
        if (deltaMinutes < 0) {
            if (Validate((deltaMinutes * -1), MINUTES_MIN, MINUTES_MAX)) {
                // Subtract minutes
                if ((deltaMinutes * -1) > minutes) {
                    // Adjust the minutes
                    minutes = (minutes + deltaMinutes) + MINUTES_MAX;
                    // Subtract 1 hour
                    hours -= 1;
                    if (hours <= HOURS_MIN) {
                        // Change to valid hour
                        hours += HOURS_NOON;
                        if (AM_or_PM.equals("AM")) AM_or_PM = "PM";
                        else if (AM_or_PM.equals("PM")) AM_or_PM = "AM";
                    }
                } else {
                    // Subtract
                    minutes += deltaMinutes;
                }
                success = true;
            }
            else {
                success = false;
            }
        } else {
            if (Validate(deltaMinutes, MINUTES_MIN, MINUTES_MAX)) {
                // Add minutes
                minutes += deltaMinutes;
                if (minutes > MINUTES_MAX) {
                    minutes -= MINUTES_MAX + 1;
                    // Adjust hours
                    hours += 1;
                    if (hours > HOURS_NOON) {
                        // Change to valid hour
                        hours -= HOURS_NOON;
                        if (AM_or_PM.equals("AM")) AM_or_PM = "PM";
                        else if (AM_or_PM.equals("PM")) AM_or_PM = "AM";
                    }
                }
                success = true;
            }
            else {
                success = false;
            }
        }
        // Change the hour
        if (deltaHours < 0) {
            if (Validate((deltaHours * -1), HOURS_MIN, HOURS_NOON)) {
                // Subtract hours
                if ((deltaHours * -1) >= hours) {
                    // Adjust the hours
                    hours += deltaHours;
                    if (hours == HOURS_MIN) {
                        // Set to 12
                        hours = HOURS_NOON;
                    } else {
                        hours += HOURS_NOON;
                    }
                    if (AM_or_PM.equals("AM")) AM_or_PM = "PM";
                    else if (AM_or_PM.equals("PM")) AM_or_PM = "AM";
                } else {
                    // Subtract
                    hours += deltaHours;
                }
                success = true;
            }
            else {
                success = false;
            }
        } else {
            if (Validate(deltaHours, HOURS_MIN, HOURS_NOON)) {
                // Add hours
                hours += deltaHours;
                if (hours > HOURS_NOON) {
                    // Adjust hours
                    hours -= HOURS_NOON;
                    if (AM_or_PM.equals("AM")) AM_or_PM = "PM";
                    else if (AM_or_PM.equals("PM")) AM_or_PM = "AM";
                }
                success = true;
            }
            else {
                success = false;
            }
        }
        if (success) newTime = new MyTime(hours, minutes, seconds);
        return newTime;
    }

    /*
        Name:           toString
        Input:          None
        Output:         The string format of the current time
        Description:    The toString method returns the string format of the current time.
    */
    public String toString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /*
        Name:           Validate
        Input:          The check value, lowest value, and highest value
        Output:         True or False
        Description:    The Validate method return True or False depending on if the check value
                        entered is between the lowest and highest values. This includes the check
                        value being equal to either the lowest or highest value.
    */
    private boolean Validate(int checkValue, int lowestValue, int highestValue) {
        boolean validated = false;
        if (checkValue >= lowestValue && checkValue <= highestValue) validated = true;

        return validated;
    }
}