/*
	Assignment:		Assignment 5A
	Programmer:		Clayton Cockrell
	Date:			11/19/17
	Description:	This is the MyCalendarInterface java interface file. It contains all the method and field
	                declarations for the MyCalendar java class file.
*/

import java.util.ArrayList;

public interface MyCalendarInterface {
    /*
        Name:           addEvent
        Input:          A MyEvent object
        Output:         True or False
        Description:    The addEvent method adds the MyEvent object argument to the
                        ArrayList handled by the MyCalendar class.
    */
    public boolean addEvent(MyEvent event);

    /*
        Name:           removeEvent
        Input:          A MyEvent object
        Output:         True or False
        Description:    The removeEvent method removes the MyEvent object argument from
                        the ArrayList handled by the MyCalendar class.
    */
    public boolean removeEvent(MyEvent event);

    /*
        Name:           removeEvent
        Input:          int index
        Output:         True or False
        Description:    The removeEvent method removes the MyEvent object from
                        the ArrayList by the index position provided.
    */
    public boolean removeEvent(int index);

    /*
        Name:           getEvents
        Input:          None
        Output:         An ArrayList
        Description:    The getEvents method returns an ArrayList of MyEvent object in
                        chronological order. First by date and then by time.
    */
    public ArrayList<MyEvent> getEvents();

    /*
        Name:           getEvents
        Input:          None
        Output:         An ArrayList
        Description:    This getEvents method returns an ArrayList of MyEvent object in
                        chronological order with the same date as the MyDate object argument.
    */
    public ArrayList<MyEvent> getEvents(MyDate myDate);

    /*
        Name:           getEvents
        Input:          None
        Output:         An ArrayList
        Description:    This getEvents method returns an ArrayList of MyEvent object in
                        chronological order with the same date as the arguments provided.
    */
    public ArrayList<MyEvent> getEvents(int mn, int dy, int yr);
}
