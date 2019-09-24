/*
	Assignment:		Assignment 5A
	Programmer:		Clayton Cockrell
	Date:		    11/19/17
	Description:	This is the MyCalendar java class file. It contains the methods and fields
	                necessary to keep track of a calendar object. This class is used in the Calendar
	                Application.
	Status:         Program compiles without error, and passes all unit tests
*/

import java.util.ArrayList;
import java.util.Comparator;

public class MyCalendar implements MyCalendarInterface{
    // Private MyCalendar Fields
    private ArrayList<MyEvent> events;

    /*
        Name:           MyCalendar
        Input:          None
        Output:         None
        Description:    The MyCalendar constructor initializes the events ArrayList.
    */
    public MyCalendar() {
        events = new ArrayList<>();
    }

    /*
        Name:           MyCalendar
        Input:          None
        Output:         None
        Description:    This MyCalendar constructor initializes the events ArrayList
                        to the ArrayList from the MyCalendar object argument.
    */
    public MyCalendar(MyCalendar calendar) {
        events = new ArrayList<>(calendar.getEvents());
    }

    /*
        Name:           addEvent
        Input:          A MyEvent object
        Output:         True or False
        Description:    The addEvent method adds the MyEvent object argument to the
                        ArrayList handled by the MyCalendar class.
    */
    public boolean addEvent(MyEvent event) {
        boolean success = false;
        if (event.isValid()) {
            events.add(event);
            success = true;
        }
        return success;
    }

    /*
        Name:           removeEvent
        Input:          A MyEvent object
        Output:         True or False
        Description:    The removeEvent method removes the MyEvent object argument from
                        the ArrayList handled by the MyCalendar class.
    */
    public boolean removeEvent(MyEvent event) {
        boolean success = false;
        if (events.indexOf(event) >= 0) {
            events.remove(event);
            if (events.indexOf(event) < 0) success = true;
        }
        return success;
    }

    /*
        Name:           removeEvent
        Input:          int index
        Output:         True or False
        Description:    The removeEvent method removes the MyEvent object from
                        the ArrayList by the index position provided.
    */
    public boolean removeEvent(int index) {
        boolean success = false;
        MyEvent removeEvent = events.get(index);
        events.remove(index);
        if (removeEvent != events.get(index)) success = true;
        return success;
    }

    /*
        Name:           getEvent
        Input:          None
        Output:         A MyEvent object
        Description:    The getEvent method returns a MyEvent object in the
                        index position provided.
    */
    public MyEvent getEvent(int index) {
        events.sort(new Compare());
        return events.get(index);
    }

    /*
        Name:           getEvents
        Input:          None
        Output:         An ArrayList
        Description:    The getEvents method returns an ArrayList of MyEvent object in
                        chronological order. First by date and then by time.
    */
    public ArrayList<MyEvent> getEvents() {
        events.sort(new Compare());
        return events;
    }

    /*
        Name:           getEvents
        Input:          None
        Output:         An ArrayList
        Description:    This getEvents method returns an ArrayList of MyEvent object in
                        chronological order with the same date as the MyDate object argument.
    */
    public ArrayList<MyEvent> getEvents(MyDate myDate) {
        ArrayList<MyEvent> eventsByDate = new ArrayList<>();
        events.sort(new Compare());
        for (MyEvent event : events) {
            if (event.getEventDate().compareTo(myDate) == 0) {
                // The event eventDate is equal to the MyDate argument
                eventsByDate.add(event);
            }
        }
        return eventsByDate;
    }

    /*
        Name:           getEvents
        Input:          None
        Output:         An ArrayList
        Description:    This getEvents method returns an ArrayList of MyEvent object in
                        chronological order with the same date as the arguments provided.
    */
    public ArrayList<MyEvent> getEvents(int mn, int dy, int yr) {
        ArrayList<MyEvent> eventsByDate = new ArrayList<>();
        events.sort(new Compare());
        for (MyEvent event : events) {
            if (event.getEventDate().getMonth() == mn && event.getEventDate().getDay() == dy && event.getEventDate().getYear() == yr) {
                // The event eventDate is equal to the month, day, and year arguments
                eventsByDate.add(event);
            }
        }
        return eventsByDate;
    }

    /*
        Name:           getEvents
        Input:          None
        Output:         An ArrayList
        Description:    This getEvents method returns an ArrayList of MyEvent object in
                        chronological order with the same month and year as the arguments provided.
    */
    public ArrayList<MyEvent> getEvents(int mn, int yr) {
        ArrayList<MyEvent> eventsByDate = new ArrayList<>();
        events.sort(new Compare());
        for (MyEvent event : events) {
            if (event.getEventDate().getMonth() == mn && event.getEventDate().getYear() == yr) {
                // The event eventDate is equal to the month and year arguments
                eventsByDate.add(event);
            }
        }
        return eventsByDate;
    }


    public boolean removeAllEvents() {
        boolean success = false;
        events.removeAll(events);
        if (events.isEmpty()) success = true;
        return success;
    }
}

/*
	Assignment:		Assignment 5A
	Programmer:		Clayton Cockrell
	Date:		    11/19/17
	Description:	This is the Compare java class file. It contains the methods and fields
	                necessary to sort a Collection or ArrayList. It is used in the MyCalendar
	                java class to sort the events ArrayList.
*/
class Compare implements Comparator<MyEvent> {
    /*
        Name:           compare
        Input:          2 MyEvent object
        Output:         -1 for less than, 0 for equal to, and 1 for greater than
        Description:    The compare method compares MyEvents a and b. If a is less
                        than b, the method returns -1. If a is equal to b, the method
                        returns 0. If a is greater than b, the method returns 1.
    */
    @Override
    public int compare(MyEvent a, MyEvent b) {
        return a.compareTo(b);
    }
}