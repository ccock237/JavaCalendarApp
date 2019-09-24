/*
	Assignment:		Assignment 5A
	Programmer:		Clayton Cockrell
	Date:		    11/19/17
	Description:	This is the MyEvent java class file. It contains the methods and fields
	                necessary to keep track of an event object. This class is used in the MyCalendar class
	                to keep track of a users events.
	Status:         Program compiles without error, and passes all unit tests
*/

public class MyEvent implements MyEventInterface {

    // Private field variables
    private String   eventName = "";
    private String   eventDesc = "";
    private MyDate   eventDate;
    private MyTime   eventTimeStart;
    private int      eventTimeDuration;

    /*
        Name:           MyEvent
        Input:          None
        Output:         None
        Description:    The MyEvent primary constructor initializes the private
                        variables used in the class.
    */
    public MyEvent() {
        eventName = "";
        eventDesc = "";
        eventDate = new MyDate();
        eventTimeStart = new MyTime();
        eventTimeDuration = 0;
    }

    /*
        Name:           MyEvent
        Input:          The event name, description, start date, start time, and duration
        Output:         None
        Description:    The MyEvent constructor initializes the private
                        variables to the arguments.
    */
    public MyEvent(String name, String desc, MyDate dateStart, MyTime timeStart, int duration) {
        // Initialize the private fields
        eventName = "";
        eventDesc = "";
        eventDate = new MyDate();
        eventTimeStart = new MyTime();
        eventTimeDuration = 0;
        // Validate the arguments
        if (dateStart.isValid() && timeStart.isValid() && duration >= 0) {
            eventName = name;
            eventDesc = desc;
            eventDate = new MyDate(dateStart);
            eventTimeStart = new MyTime(timeStart);
            eventTimeDuration = duration;
        }
    }

    /*
        Name:           MyEvent
        Input:          MyEvent object
        Output:         None
        Description:    The MyEvent constructor initializes the private
                        variables from the object argument.
    */
    public MyEvent(MyEvent copyEvent) {
        eventName = new String(copyEvent.getEventName());
        eventDesc = new String(copyEvent.getEventDesc());
        eventDate = new MyDate(copyEvent.getEventDate());
        eventTimeStart = new MyTime(copyEvent.getEventTimeStart());
        eventTimeDuration = copyEvent.getEventTimeDuration();
    }

    /*
        Name:           getEventName
        Input:          None
        Output:         The event name
        Description:    The getEventName method returns the event name.
    */
    public String getEventName() {
        return eventName;
    }

    /*
        Name:           getEventDescription
        Input:          None
        Output:         The event description
        Description:    The getEventDescription method returns the event description.
    */
    public String getEventDesc() {
        return eventDesc;
    }

    /*
        Name:           getEventDate
        Input:          None
        Output:         The event start date
        Description:    The getEventDate method returns the event start date.
    */
    public MyDate getEventDate() {
        return eventDate;
    }

    /*
        Name:           getEventTimeStart
        Input:          None
        Output:         The event start time
        Description:    The getEventTimeStart method returns the event start time.
    */
    public MyTime getEventTimeStart() {
        return eventTimeStart;
    }

    /*
        Name:           getEventTimeDuration
        Input:          None
        Output:         The event time duration
        Description:    The getEventTimeDuration method returns the event time duration.
    */
    public int getEventTimeDuration() {
        return eventTimeDuration;
    }

    /*
        Name:           setEventName
        Input:          Event name
        Output:         True on success
        Description:    The setEventName method sets the event name to the argument
                        provided.
    */
    public boolean setEventName(String set_name) {
        eventName = set_name;
        return true;
    }

    /*
        Name:           setEventDescription
        Input:          Event description
        Output:         True on success
        Description:    The setEventDescription method sets the event description to the
                        argument provided.
    */
    public boolean setEventDesc(String set_desc) {
        eventDesc = set_desc;
        return true;
    }

    /*
        Name:           setEventDate
        Input:          MyDate object
        Output:         True or False
        Description:    The setEventDate method sets the event start date to the
                        MyDate object argument provided.
    */
    public boolean setEventDate(MyDate set_date) {
        boolean success = false;
        if (set_date.isValid()) {
            eventDate = new MyDate(set_date);
            success = true;
        }
        return success;
    }

    /*
        Name:           setEventTimeStart
        Input:          MyTime object
        Output:         True or False
        Description:    The setEventTimeStart method sets the event start time to the
                        MyTime object argument provided.
    */
    public boolean setEventTimeStart(MyTime set_time) {
        boolean success = false;
        if (set_time.isValid()) {
            eventTimeStart = new MyTime(set_time);
            success = true;
        }
        return success;
    }

    /*
        Name:           setEventTimeDuration
        Input:          Event time duration
        Output:         True or False
        Description:    The getEventTimeDuration method sets the event time duration
                        to the argument provided.
    */
    public boolean setEventTimeDuration(int set_duration) {
        boolean success = false;
        if ( set_duration >= 0) {
            eventTimeDuration = set_duration;
            success = true;
        }
        return success;
    }

    /*
        Name:           compareTo
        Input:          MyEvent object
        Output:         -1 for less than, 0 for equal to, and 1 for greater than
        Description:    The compareTo method takes in a MyEvent object and compares it
                        with the current MyEvent object. The method returns 1 if the
                        MyEvent argument is less than the current MyEvent object. The
                        method returns 0 if the MyEvent objects are equal. The method
                        returns -1 if the MyEvent argument is greater than the current
                        MyEvent object.
    */
    public int compareTo(MyEvent otherMyEvent) {
        int compares = 2;
        if (eventDate != null || otherMyEvent.getEventDate() != null || eventTimeStart != null || otherMyEvent.getEventTimeStart() != null) {
            // Compare the dates
            int dateCompares = eventDate.compareTo(otherMyEvent.getEventDate());
            // Compare the times
            int timeCompares = eventTimeStart.compareTo(otherMyEvent.getEventTimeStart());
            if (dateCompares == 0) {
                // The dates are equal
                compares = timeCompares;
            } else {
                compares = dateCompares;
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
        return eventTimeStart.doesTimeSpanOverlap(ckTime, durationMins);
    }

    /*
        Name:           doesTimeSpanOverlap
        Input:          The hour, minute, second, and minute duration
        Output:         True or False
        Description:    The doesTimeSpanOverlap method takes in an hour, minute, and
                        second and adds the duration minutes and checks if the argument
                        MyTime object is greater than the current MyTime object.
    */
    public boolean doesTimeSpanOverlap(int hr, int mn, int sec, int durationMins) {
        MyTime test = new MyTime(hr, mn, sec);
        return eventTimeStart.doesTimeSpanOverlap(test, durationMins);
    }

    /*
        Name:           updateTime
        Input:          An hour, minute, and second to add
        Output:         MyTime object
        Description:    The updateTime method takes in delta hours, minutes,
                        and seconds and add these to the current time. The method
                        return True on success and False on failure.
    */
    public MyTime updateTime(int deltaHours, int deltaMinutes, int deltaSeconds) {
        return eventTimeStart.updateTime(deltaHours, deltaMinutes, deltaSeconds);
    }

    /*
        Name:           isValid
        Input:          None
        Output:         True or False
        Description:    The isValid method tests the current event. If the
                        current event is a valid event, the method returns
                        True; otherwise, it returns False.
    */
    public boolean isValid() {
        return isValid(eventName, eventDesc, eventDate, eventTimeStart, eventTimeDuration);
    }

    /*
        Name:           isValid
        Input:          The name, description, date, time, and duration
        Output:         True or False
        Description:    The isValid method tests an event. If the
                        event is a valid event, the method returns
                        True; otherwise, it returns False.
    */
    public boolean isValid(String name, String desc, MyDate dateStart, MyTime timeStart, int duration) {
        boolean success = false;
        if (name != null && desc != null) {
            if (!name.equals("") && !desc.equals("") && dateStart.isValid() && timeStart.isValid() && duration >= 0) {
                success = true;
            }
        }
        return success;
    }

    /*
        Name:           getDOW
        Input:          None
        Output:         The DOW object of the date
        Description:    The getDOW method returns the DOW object for the
                        current date.
    */
    public DOW getDOW() {
        return eventDate.getDOW();
    }

    /*
        Name:           getDOW
        Input:          A month, day, and year
        Output:         The DOW object of the date
        Description:    The getDOW method returns the DOW object for the
                        date specified.
    */
    public DOW getDOW(int mn, int dy, int yr) {
        return eventDate.getDOW(mn, dy, yr);
    }

    /*
        Name:           getMonth1stDOW
        Input:          None
        Output:         The DOW object for the first day in the month
        Description:    The getMonth1stDOW method returns the DOW object for the
                        first day of the month.
    */
    public DOW getMonth1stDOW() {
        return eventDate.getMonth1stDOW();
    }

    /*
        Name:           getNumOfDaysInYear
        Input:          None
        Output:         The number of days in current year
        Description:    The getNumOfDaysInYear method returns the number of days
                        in the current year.
    */
    public int getNumOfDaysInYear() {
        return eventDate.getNumOfDaysInYear();
    }

    /*
        Name:           getNumOfDaysInYear
        Input:          The year
        Output:         The number of days in current year
        Description:    The getNumOfDaysInYear method returns the number of days
                        in the specified year.
    */
    public int getNumOfDaysInYear(int yr) {
        return eventDate.getNumOfDaysInYear(yr);
    }

    /*
        Name:           doEventsOverlap
        Input:          A MyEvent object
        Output:         True or False
        Description:    The doEventsOverlap method returns True or False depending on
                        if the argument MyEvent overlaps the current event.
    */
    public boolean doEventsOverlap(MyEvent ckEvent) {
        boolean success = false;

        if (eventDate.compareTo(ckEvent.getEventDate()) == COMPARE_TO_EQ) {
            // Calculate the end times
            MyTime eventTimeEnd = new MyTime(eventTimeStart);
            eventTimeEnd.updateTime(0, eventTimeDuration, 0);
            MyTime ckEventEndTime = new MyTime(ckEvent.getEventTimeStart());
            ckEventEndTime.updateTime(0, ckEvent.getEventTimeDuration(), 0);
            // Check if this event's end time is greater than or equal to the ckEvent's start time
            if (eventTimeEnd.compareTo(ckEvent.getEventTimeStart()) == COMPARE_TO_GT || eventTimeEnd.compareTo(ckEvent.getEventTimeStart()) == COMPARE_TO_EQ) {
                // Check if this event's start time is less than or equal to the ckEvent's end time
                if (eventTimeStart.compareTo(ckEventEndTime) == COMPARE_TO_LT || eventTimeStart.compareTo(ckEventEndTime) == COMPARE_TO_EQ) {
                    // The times overlap
                    success = true;
                }
            }
        }

        return success;
    }

    /*
        Name:           toString
        Input:          None
        Output:         A string of the event information
        Description:    The toString method returns the current events information.
    */
    @Override
    public String toString() {
        // Configure end time
        int[] end = getEndDateAndTime();
        // Set the end time and date
        MyTime endTime = new MyTime(end[0], end[1], end[2]);
        MyDate endDate = new MyDate(end[3], end[4], end[5]);

        return String.format("Event Name: %s\nEvent Description: %s\nEvent Start: %s %s\nEvent End: %s %s", eventName, eventDesc, eventDate.printLong(), eventTimeStart.printUniversal(), endDate.printLong(), endTime.printUniversal());
    }

    /*
        Name:           getEndDateAndTime
        Input:          None
        Output:         An int array with the end date and time variables
        Description:    The getEndDateAndTime method calculates and returns an int
                        array holding each variable to create a MyTime and MyDate
                        object for the end date and time.
    */
    private int[] getEndDateAndTime() {
        int timeHour = eventTimeStart.getHours();
        int timeMin = eventTimeStart.getMinutes();
        int timeSec = eventTimeStart.getSeconds();
        int dateYear = eventDate.getYear();
        int dateMonth = eventDate.getMonth();
        int dateDay = eventDate.getDay();
        int addHours = 0;
        int addDays = 0;
        int addMonths = 0;
        int addYears = 0;
        // Add the minutes
        timeMin +=  eventTimeDuration;
        if (timeMin > MyTime.MINUTES_MAX) {
            // Get the number of hours to add
            addHours = timeMin / (MyTime.MINUTES_MAX + 1);
            // Add the hours and remove the minutes
            timeHour += addHours;
            timeMin -= (MyTime.MINUTES_MAX + 1) * addHours;
            if (timeHour > MyTime.HOURS_MAX) {
                // Get the number of days to add
                addDays = timeHour / (MyTime.HOURS_MAX + 1);
                // Add the days and remove the hours
                dateDay += addDays;
                timeHour -= (MyTime.HOURS_MAX + 1) * addDays;

                while (dateDay > 28) {
                    if (dateDay > eventDate.getNumOfDaysInMonth(dateMonth + addMonths)) {
                        // Increment the month and remove the days
                        dateDay -= eventDate.getNumOfDaysInMonth(dateMonth + addMonths);
                        addMonths++;
                    }
                    else {
                        break;
                    }
                }
                dateMonth += addMonths;

                if (dateMonth > MyDate.MONTH_MAX) {
                    // Get the number of years to add
                    addYears = dateMonth / MyDate.MONTH_MAX;
                    // Add the years and remove the months
                    dateYear += addYears;
                    dateMonth -= MyDate.MONTH_MAX * addYears;
                }
            }
        }
        // Return for new MyTime(timeHour, timeMin, timeSec) and new MyDate(dateMonth, dateDay, dateYear)
        return new int[] {timeHour, timeMin, timeSec, dateMonth, dateDay, dateYear};
    }

}
