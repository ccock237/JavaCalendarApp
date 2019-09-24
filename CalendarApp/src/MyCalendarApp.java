/*
	Assignment:		Assignment 5B
	Programmer:		Clayton Cockrell
	Date:		    12/04/17
	Description:	This is the MyCalendarApp java class file. It contains the methods and fields
	                necessary to display and manage a calendar application.
	Status:         Program compiles without error, and passes all unit tests
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class MyCalendarApp extends JFrame {
    private int currentMonth;
    private int currentYear;
    private MyCalendar calendar;
    private String[] getMonthName = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private ArrayList<MyEvent> baseEvents = new ArrayList<>();

    /*
        Name:           MyCalendarApp
        Input:          None
        Output:         None
        Description:    This MyCalendarApp constructor initializes the class fields and creates a
                        JFrame.
    */
    public MyCalendarApp() {
        calendar = new MyCalendar();
        currentMonth = LocalDateTime.now().getMonthValue();
        currentYear = LocalDateTime.now().getYear();
        this.setTitle("My Calendar App");
        drawCalendar();
    }

    /*
        Name:           initializeDefaultEvents
        Input:          None
        Output:         True or False
        Description:    The initializeDefaultEvents method adds predefined events for the
                        calendar. The method returns True on success and False on fail.
    */
    public boolean initializeDefaultEvents() {
        // Default Events
        MyEvent event1 = new MyEvent("Thanksgiving", "Thanksgiving is celebrated on this day.", new MyDate(11, 23, 2017), new MyTime(0, 0, 0), 1439);
        MyEvent event2 = new MyEvent("Black Friday", "Black Friday is a day when all companies have huge sales on their products.", new MyDate(11, 24, 2017), new MyTime(0, 0, 0), 1439);
        MyEvent event3 = new MyEvent("Buy Christmas Presents", "Go buy Christmas presents.", new MyDate(11, 23, 2017), new MyTime(12, 0, 0), 1000);
        MyEvent event4 = new MyEvent("Get Family Christmas Presents", "Go get family Christmas presents.", new MyDate(12, 11, 2017), new MyTime(10, 0, 0), 1000);
        MyEvent event5 = new MyEvent("Christmas Eve", "This is the day before Christmas.", new MyDate(12, 24, 2017), new MyTime(), 1439);
        MyEvent event6 = new MyEvent("Christmas", "This is the day Christmas is celebrated.", new MyDate(12, 25, 2017), new MyTime(), 1439);
        MyEvent event7 = new MyEvent("Get Fireworks", "Get fireworks for New Years day.", new MyDate(12, 30, 2017), new MyTime(), 1439);
        MyEvent event8 = new MyEvent("New Years Eve", "This is the day before New Years day.", new MyDate(12, 31, 2017), new MyTime(), 1439);
        MyEvent event9 = new MyEvent("New Years", "This is the day New Years is celebrated.", new MyDate(1, 1, 2018), new MyTime(), 1439);
        calendar.addEvent(event1);
        calendar.addEvent(event2);
        calendar.addEvent(event3);
        calendar.addEvent(event4);
        calendar.addEvent(event5);
        calendar.addEvent(event6);
        calendar.addEvent(event7);
        calendar.addEvent(event8);
        calendar.addEvent(event9);
        baseEvents.add(event1);
        baseEvents.add(event2);
        baseEvents.add(event3);
        baseEvents.add(event4);
        baseEvents.add(event5);
        baseEvents.add(event6);
        baseEvents.add(event7);
        baseEvents.add(event8);
        baseEvents.add(event9);
        boolean success = false;
        if (!calendar.getEvents().isEmpty() && baseEvents.isEmpty()) success = true;
        return success;
    }

    /*
        Name:           placeComponent
        Input:          A JPanel object, Component object, X position, Y position, padding array, and column span
        Output:         None
        Description:    The placeComponent method adds a Component object to a JPanel object. The
                        Component object is placed bases on the X and Y position specified in the
                        arguments. A GridBagLayout is used  to place the Component object.
    */
    private void placeComponent(JPanel panel, Component component, int xPos, int yPos, int[] padding, int colSpan) {
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        layoutConstraints.gridx = xPos;
        layoutConstraints.gridy = yPos;
        layoutConstraints.gridwidth = colSpan;
        layoutConstraints.insets = new Insets(padding[0], padding[1], padding[2], padding[3]);
        panel.add(component, layoutConstraints);
    }

    /*
        Name:           convertDaysToButtons
        Input:          A month and year
        Output:         An ArrayList object
        Description:    The convertDaysToButtons method loops through all the events in the
                        calendar and create JButtons for each day coloring the background
                        of the button blue if the day has an event. It also handles events
                        that span many days and event many months.
    */
    private ArrayList<JButton> convertDaysToButtons(int month, int year) {
        // Declare object and variables needed in method
        ArrayList<JButton> calendarDay = new ArrayList<>();
        HashMap<String, Integer> subtractDuration = new HashMap<>();
        MyDate temp = new MyDate(month, 1, year);
        int size = 45;
        int minutesLeft = 0;
        boolean shouldAdd = false;
        MyEvent tempEvent = new MyEvent();

        if (calendar.removeAllEvents()) {
            for (MyEvent traverse : baseEvents) {
                calendar.addEvent(traverse);
            }
        }

        // Create an ArrayList of JButtons for each day in the calendar in the month
        for (int count = 1; count <= temp.getNumOfDaysInMonth(); count++) {
            // Create the JButton for the day
            JButton daySquare = new JButton(String.format("%d", count));
            daySquare.setPreferredSize(new Dimension(95, 95));
            daySquare.setFont(new Font("SansSerif", Font.PLAIN, size));

            // Check if the current day has an event
            if (calendar.getEvents(month, count, year).isEmpty() && minutesLeft < 1440) {
                // No event on this day
                daySquare.setBackground(Color.WHITE);
            } else {
                // Check each event on this day for spanning over a day
                for (MyEvent currEvent : calendar.getEvents(month, count, year)) {
                    minutesLeft = (currEvent.getEventTimeDuration() + (currEvent.getEventTimeStart().getHours() * 60) + currEvent.getEventTimeStart().getMinutes());
                    if (minutesLeft >= 1440) {
                        if (count == temp.getNumOfDaysInMonth()) {
                            // Spans the month
                            tempEvent = new MyEvent(currEvent);
                            tempEvent.setEventDate(new MyDate(month + 1, 1, year));
                            tempEvent.setEventTimeStart(new MyTime(0, 0, 0));
                            if (!subtractDuration.isEmpty()) subtractDuration.put(currEvent.getEventName(), subtractDuration.get(currEvent.getEventName()) + (currEvent.getEventTimeStart().getHours() * 60) + currEvent.getEventTimeStart().getMinutes());
                            else subtractDuration.put(currEvent.getEventName(), (currEvent.getEventTimeStart().getHours() * 60) + currEvent.getEventTimeStart().getMinutes());
                            tempEvent.setEventTimeDuration(currEvent.getEventTimeDuration() - subtractDuration.get(currEvent.getEventName()));
                            baseEvents.add(tempEvent);
                        }
                        else {
                            // Add event that spans over a day
                            tempEvent = new MyEvent(currEvent);
                            tempEvent.setEventDate(new MyDate(month, count + 1, year));
                            tempEvent.setEventTimeStart(new MyTime(0, 0, 0));
                            subtractDuration.put(currEvent.getEventName(), (1440 - (currEvent.getEventTimeStart().getHours() * 60) + currEvent.getEventTimeStart().getMinutes()));
                            tempEvent.setEventTimeDuration(currEvent.getEventTimeDuration() - (subtractDuration.get(currEvent.getEventName())));
                            calendar.addEvent(tempEvent);
                        }
                    }
                }
                daySquare.setBackground(Color.CYAN);
            }
            // Check if the current day is the current day by users current day
            if (month == LocalDateTime.now().getMonthValue() && count == LocalDateTime.now().getDayOfMonth()) {
                daySquare.setForeground(Color.RED);
            }
            // Add the JButton to the ArrayList
            calendarDay.add(daySquare);
        }
        return calendarDay;
    }

    /*
        Name:           drawCalendar
        Input:          None
        Output:         None
        Description:    The drawCalendar method create many components for display the
                        calendar and add them to a JPanel object. The JPanel object is
                        added to a JFrame object that displays the calendar.
    */
    private void drawCalendar() {
        // Revalidate the JFrame
        this.revalidate();

        // Convert days in current month to JButtons for displaying
        ArrayList<JButton> days = convertDaysToButtons(currentMonth, currentYear);
        // Objects and variables needed in the method
        GridBagConstraints layoutConst = new GridBagConstraints();
        JPanel panel = new JPanel();
        int padding = 5;

        // Set the layout of the JFrame
        this.setLayout(new GridBagLayout());
        // Set the layout of the JPanel
        panel.setLayout(new GridBagLayout());

        // Display the month and year at the top
        JLabel month_year = new JLabel(getMonthName[currentMonth] + " " + currentYear + " " + new String(Character.toChars(9660)));
        // Add mouse listener to label
        month_year.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Display a dialog when clicked
                JDialog dialog = new JDialog();
                JPanel panel = new JPanel();
                GridBagConstraints layoutConstraints = new GridBagConstraints();
                JButton okButton = new JButton("OK");
                JLabel monthLabel = new JLabel("Select Month");
                JLabel yearLabel = new JLabel("Select Year");
                JComboBox<Integer> yearSelect = new JComboBox<>();
                JComboBox<String> monthSelect = new JComboBox<>();
                // Set font sizes
                yearSelect.setFont(new Font("SansSerif", Font.PLAIN, 20));
                monthSelect.setFont(new Font("SansSerif", Font.PLAIN, 20));
                monthLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
                yearLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
                okButton.setFont(new Font("SansSerif", Font.PLAIN, 20));

                // Add items to year combobox
                for (int i = MyDate.YEAR_MIN; i <= MyDate.YEAR_MAX; i++) {
                    yearSelect.addItem(i);
                }
                // Add items to month combobox
                for (String mon : getMonthName) {
                    if (!mon.equals("")) {
                        monthSelect.addItem(mon);
                    }
                }

                // Set the layout
                panel.setLayout(new GridBagLayout());
                // place components
                placeComponent(panel, monthLabel, 0, 0, new int[]{10, 10, 10, 10}, 1);
                placeComponent(panel, yearLabel, 1, 0, new int[]{10, 10, 10, 10}, 1);
                placeComponent(panel, monthSelect, 0, 1, new int[]{10, 10, 10, 10}, 1);
                placeComponent(panel, yearSelect, 1, 1, new int[]{10, 10, 10, 10}, 1);
                placeComponent(panel, okButton, 0, 2, new int[]{10, 10, 10, 10}, 2);
                // Setup dialog and display
                dialog.add(panel);
                dialog.pack();
                dialog.setVisible(true);

                // When the okButton is clicked
                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Set the current month and year to selection
                        currentMonth = (monthSelect.getSelectedIndex() + 1);
                        currentYear = yearSelect.getItemAt(yearSelect.getSelectedIndex());
                        drawCalendar();
                    }
                });

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Change color and cursor when hovering
                month_year.setForeground(Color.BLUE);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Change color and cursor back when not hovering
                month_year.setForeground(Color.BLACK);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        month_year.setFont(new Font("SansSerif", Font.BOLD, 40));
        // Place the label
        placeComponent(panel, month_year, 2, 0, new int[]{padding, padding, padding, padding}, 5);

        // Put the days of the week
        String[] daysoftheweek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        int position = 1;
        for (String dow : daysoftheweek) {
            JLabel newLabel = new JLabel(dow);
            newLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            placeComponent(panel, newLabel, position, 1, new int[] {padding, padding, padding, padding}, 1);
            position++;
        }

        // Get the day of week of the first day of the month
        int setX = (new MyDate(currentMonth, 1, currentYear).getDOW().getValue() + 1);
        int setY = 2;

        for (JButton day : days) {
            // Place the days
            placeComponent(panel, day, setX, setY, new int[]{padding, padding, padding, padding}, 1);
            // If setX position is greater than Saturday
            if (setX >= 7) {
                // Move down one row
                setY++;
                // Set setX position to Sunday
                setX = 1;
            } else {
                // Move one day of the week
                setX++;
            }
            // Add click event listener to each day JButton
            day.addActionListener(new ButtonClick());
        }

        // Setup JButton for display the current date when clicked
        JButton currDate = new JButton("Curr Month");
        currDate.setFont(new Font("SansSerif", Font.PLAIN, 20));
        currDate.addActionListener(new DrawCurrentMonth());
        placeComponent(panel, currDate, 0, 3, new int[] {10, 10, 10, 10}, 1);

        // Setup JButton for creating new events
        JButton create = new JButton("Create");
        create.setFont(new Font("SansSerif", Font.PLAIN, 20));
        create.addActionListener(new CreateEvent());
        layoutConst.gridx = 0;
        layoutConst.gridy = setY + 1;
        layoutConst.insets = new Insets(10, 10, 10, 10);
        panel.add(create, layoutConst);
        // Previous and Next Buttons
        JButton prevMonth = new JButton("<");
        prevMonth.setFont(new Font("SansSerif", Font.PLAIN, 30));
        prevMonth.addActionListener(new ChangeMonth());
        layoutConst.gridx = 0;
        layoutConst.gridy = 4;
        layoutConst.insets = new Insets(10, 10, 10, 10);
        panel.add(prevMonth, layoutConst);
        JButton nextMonth = new JButton(">");
        nextMonth.setFont(new Font("SansSerif", Font.PLAIN, 30));
        nextMonth.addActionListener(new ChangeMonth());
        layoutConst.gridx = 8;
        layoutConst.gridy = 4;
        layoutConst.insets = new Insets(10, 10, 10, 10);
        panel.add(nextMonth, layoutConst);


        this.getContentPane().removeAll();
        this.add(panel);
        this.getContentPane().invalidate();
        this.getContentPane().validate();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }


    /*** EVENT AND ACTION LISTENERS ***/

    /*
        Name:           ButtonClick
        Description:    The ButtonClick class is used to handle the click event of each
                        JButton day object in the calendar. When this event occurs, the class
                        creates a JDialog object that is used to paint a graph of the events
                        for that day. Upon another JButton click event from this dialog box,
                        the class will produce another dialog box with the event information
                        in text.
    */
    private class ButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the text of the button that was clicked
            String buttonClicked = e.getActionCommand();
            // Setup a dialog box
            JDialog dialog = new JDialog();
            GridBagConstraints layoutConst = new GridBagConstraints();
            ArrayList<MyEvent> events = calendar.getEvents(currentMonth, Integer.parseInt(buttonClicked), currentYear);
            int[] startTemp = new int[events.size()];
            int[] endTemp = new int[events.size()];
            String[] nameTemp = new String[events.size()];
            String[] eventInfo = new String[events.size()];

            if (!events.isEmpty()) {
                // Get the events information
                startTemp = new int[events.size()];
                endTemp = new int[events.size()];
                nameTemp = new String[events.size()];
                int index = 0;

                for (MyEvent event : events) {
                    startTemp[index] = (((event.getEventTimeStart().getHours() * 60) + event.getEventTimeStart().getMinutes()) / 2);
                    endTemp[index] = ((event.getEventTimeDuration() / 2));
                    nameTemp[index] = event.getEventName();
                    eventInfo[index] = event.toString();
                    index++;
                }
            }

            final int size = events.size();
            final int[] start = new int[size];
            final int[] end = new int[size];
            final String[] name = new String[size];
            System.arraycopy(startTemp, 0, start, 0, startTemp.length);
            System.arraycopy(endTemp, 0, end, 0, endTemp.length);
            System.arraycopy(nameTemp, 0, name, 0, nameTemp.length);

            class DrawEventGraph extends JPanel {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D graphic = (Graphics2D) g;
                    // Draw background
                    graphic.setColor(Color.WHITE);
                    graphic.fillRect(0, 0, 725, 500);
                    // Draw number line
                    graphic.setColor(Color.BLACK);
                    graphic.drawLine(0, 40, 725, 40);
                    // Draw "Hours in Day" label
                    graphic.setFont(new Font("SansSerif", Font.BOLD, 15));
                    graphic.drawString("Hours in Day", 336, 20);
                    // Draw the hours marker on number line
                    int setXPos = 0;
                    for (int i = 0; i <= MyTime.HOURS_MAX; i++) {
                        graphic.drawString(String.format("%d", i), setXPos, 38);
                        if (i == 0) setXPos += 25;
                        else if (i < 10) setXPos += 31;
                        else setXPos += 30;
                    }

                    // Draw each event on graph
                    int setYPos = 50;
                    for (int j = 0; j < size; j++) {
                        graphic.setColor(new Color(0, 160, 180));
                        graphic.fillRect( start[j], setYPos, end[j] + 5, 30);
                        graphic.setColor(Color.WHITE);
                        graphic.drawString(name[j], start[j] + 10, setYPos + 20);
                        setYPos += 50;
                    }
                }
            }

            DrawEventGraph eventGraph = new DrawEventGraph();
            eventGraph.setLayout(new GridBagLayout());
            JButton getTextButton = new JButton("Text Mode");
            getTextButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
            getTextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JDialog newDialog = new JDialog();
                    JTextArea textArea = new JTextArea();
                    String addString = "";

                    // Add event information to textArea
                    for (int k = 0; k < eventInfo.length; k++) {
                        addString += (eventInfo[k] + "\n\n");
                    }

                    textArea.setText(addString);
                    textArea.setEditable(false);
                    newDialog.add(textArea);
                    newDialog.pack();
                    newDialog.setVisible(true);
                }
            });
            layoutConst.gridx = 0;
            layoutConst.gridy = 0;
            layoutConst.insets = new Insets(400, 0, 0, 0);
            eventGraph.add(getTextButton, layoutConst);

            // Display the dialog box
            dialog.add(eventGraph);
            dialog.setSize(740, 500);
            dialog.setVisible(true);
        }
    }

    /*
        Name:           DrawCurrentMonth
        Description:    The DrawCurrentMonth class is used to handle the click event of the
                        "current month" JButton object. When this event occurs, the class
                        set the currentMonth and currentYear variables to the users current
                        local month and year. The class then redraws the calendar by calling
                        drawCalendar()
    */
    private class DrawCurrentMonth implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentMonth = LocalDateTime.now().getMonthValue();
            currentYear = LocalDateTime.now().getYear();
            drawCalendar();
        }
    }

    /*
        Name:           CreateEvent
        Description:    The CreateEvent class is used to handle the click event of the
                        "create" JButton object. When this event occurs, the class
                        creates a dialog box with text fields for event information.
                        The user is to put their custom event information into the text
                        fields and click the another "create" JButton. When that "create"
                        JButton is clicked, the class create and adds a new event with the
                        information provided and redraws the calendar by calling drawCalendar()
    */
    private class CreateEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Create a the dialog, text fields, and labels
            JDialog dialog = new JDialog();
            JLabel eventNameLabel = new JLabel("Event Name:");
            JTextField eventName = new JTextField();
            JLabel eventDescLabel = new JLabel("Event Description:");
            JTextField eventDesc = new JTextField();
            JLabel eventDateLabel = new JLabel("Event Date (e.g. '1/1/2017'):");
            JTextField eventDate = new JTextField();
            JLabel eventStartTimeLabel = new JLabel("Event Start Time (e.g. '12:30:00'):");
            JTextField eventStartTime = new JTextField();
            JLabel eventDurationLabel = new JLabel("Event Duration (e.g. '30'):");
            JTextField eventDuration = new JTextField();
            JButton createEvent = new JButton("Create");
            JPanel newPanel = new JPanel();
            GridBagConstraints layoutConst = new GridBagConstraints();

            // Set the JLabel and JButton font
            eventNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            eventDescLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            eventDateLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            eventStartTimeLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            eventDurationLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
            createEvent.setFont(new Font("SansSerif", Font.PLAIN, 20));
            // Set JTextField size and font
            eventName.setPreferredSize(new Dimension(200, 30));
            eventName.setFont(new Font("SansSerif", Font.PLAIN, 25));
            eventDesc.setPreferredSize(new Dimension(200, 30));
            eventDesc.setFont(new Font("SansSerif", Font.PLAIN, 25));
            eventDate.setPreferredSize(new Dimension(200, 30));
            eventDate.setFont(new Font("SansSerif", Font.PLAIN, 25));
            eventStartTime.setPreferredSize(new Dimension(200, 30));
            eventStartTime.setFont(new Font("SansSerif", Font.PLAIN, 25));
            eventDuration.setPreferredSize(new Dimension(100, 30));
            eventDuration.setFont(new Font("SansSerif", Font.PLAIN, 25));

            // Set JPanel layout
            newPanel.setLayout(new GridBagLayout());
            // The component padding
            int[] padding = {10, 10, 10, 10};
            // Add the components to the JPanel
            placeComponent(newPanel, eventNameLabel, 0, 0, padding, 1);
            placeComponent(newPanel, eventName, 1, 0, padding, 1);
            placeComponent(newPanel, eventDescLabel, 0, 1, padding, 1);
            placeComponent(newPanel, eventDesc, 1, 1, padding, 1);
            placeComponent(newPanel, eventDateLabel, 0, 2, padding, 1);
            placeComponent(newPanel, eventDate, 1, 2, padding, 1);
            placeComponent(newPanel, eventStartTimeLabel, 0, 3, padding, 1);
            placeComponent(newPanel, eventStartTime, 1, 3, padding, 1);
            placeComponent(newPanel, eventDurationLabel, 0, 4, padding, 1);
            placeComponent(newPanel, eventDuration, 1, 4, padding, 1);
            placeComponent(newPanel, createEvent, 0, 5, padding, 1);

            // Display the dialog box
            dialog.add(newPanel);
            dialog.pack();
            dialog.setVisible(true);

            // When createEvent JButton clicked, add a new event with information provided in JTextFields
            createEvent.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Get the text from the textfields
                    String name = eventName.getText();
                    String desc = eventDesc.getText();
                    // Split the text into arrays with numbers
                    String[] date = eventDate.getText().split("/");
                    String[] time = eventStartTime.getText().split(":");
                    // Get the duration
                    int duration = Integer.parseInt(eventDuration.getText());
                    // Create a MyDate and MyTime object from the text array
                    MyDate newDate = new MyDate(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
                    MyTime newTime = new MyTime(Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
                    // Add the data to a MyEvent object
                    MyEvent newEvent = new MyEvent(name, desc, newDate, newTime, duration);
                    // Add the MyEvent object to the calendar
                    calendar.addEvent(newEvent);
                    baseEvents.add(newEvent);
                    // Close the previous dialog
                    dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
                    // Redraw the calendar with the new event
                    drawCalendar();
                }
            });
        }
    }

    /*
        Name:           ChangeMonth
        Description:    The ChangeMonth class is used to handle the click event of the
                        "<" and ">" JButtons. When this event occurs, the class
                        sets the currentMonth to the next month or the previous month.
                        The "<" JButton will set to the previous month, while the ">"
                        JButton will set to the next month. After the month is set, the
                        class redraws the calendar by calling drawCalendar()
    */
    private class ChangeMonth implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("<")) {
                // Previous month
                if (currentMonth > 1) {
                    currentMonth--;
                    drawCalendar();
                }
                else {
                    currentYear--;
                    currentMonth = 12;
                    drawCalendar();
                }
            }
            else {
                // Next month
                if (currentMonth < 12) {
                    currentMonth++;
                    drawCalendar();
                }
                else {
                    currentYear++;
                    currentMonth = 1;
                    drawCalendar();
                }
            }
        }
    }
}
