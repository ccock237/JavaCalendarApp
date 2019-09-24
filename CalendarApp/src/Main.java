/*
	Assignment:		Assignment 5B
	Programmer:		Clayton Cockrell
	Date:		    12/04/17
	Description:	This is the Main java class file. It is used to start the Calendar Application.
	Status:         Program compiles without error, and passes all unit tests
*/

public class Main {
    public static void main(String[] args) {
        // Start the application
        MyCalendarApp application = new MyCalendarApp();
        // Initialize default events
        application.initializeDefaultEvents();
    }
}
