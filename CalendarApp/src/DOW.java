// Scott G. Edwards, 10/11/2017
// Assignment 4b
// CPT 236 Fall 2017


/**
 * Created by sedward3 on 10/11/2017.
 */
public class DOW implements Comparable<DOW>
{
    // following made public in order to allow for outside to directly access, although realistically should only be DOWTest.java

    public  static  final   int         DOW_MIN     =   0;          // Sunday
    public  static  final   int         DOW_MAX     =   6;          // Saturday
    public  static  final   int         DOW_BASE     =   DOW_MAX+1;  // 7: #days in week
    public  static  final   int         DOW_Default =   DOW_MIN;
    public  static  final   int         DOW_Invalid =   DOW_MAX+1;  // Index to get "Invalid Entries"

    public  static  final   int         DOW_LT      =   -1;
    public  static  final   int         DOW_EQ      =   0;
    public  static  final   int         DOW_GT      =   +1;

    public  static  final   String[]    dowStrShort =   { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Inv" };

    public  static  final   String[]    dowStrLong  =   { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Invalid" };

    public  static  final   boolean DOW_INVALID_IMPLEMENTED =   false;      // True ==> DDW_INVALID available in setVBalue

    private             int dayOfWeek;      //  0..6 ==> Sun..Sat


    // Default constructor
    public DOW()
    {
        dayOfWeek =   DOW_Default;
    }   //  end DOW()

    // Default constructor
    public DOW(int dow)
    {
        dayOfWeek =   DOW_Default;
        setValue(dow);

    }   //  end DOW()


    // Method to use in all "setters" to validate value w.r.t. min & max (inclusive)
    private boolean validateRange(int value, int min, int max)
    {
        if ( (value >= min) && (value <= max) )
            return true;
        else
            return false;

    }   //  end validateRange()


    private boolean validateRange(int value)
    {
        return ( validateRange(value, DOW_MIN, DOW_MAX) );
    }   //  end validateRange()


    // Method to inc/dec day offset to current value,
    //  updates value in place and returns new value
    public int addDays(int daysToAdd)
    {
        int dow =   dayOfWeek + daysToAdd;      // Calculate new DOW

        //	0: 0,1,2,3,4,5,6    1: 0,1,2,3,4,5,6   2: 0,1,2,3,4,5,6

        // move to being positive (e.g.:  dayOfWeek +=  -28 )
        while ( dow < 0 )
            dow +=  DOW_BASE;

        // Now, make sure that the value is in valid range
        dow %=  DOW_BASE;

        dayOfWeek   =   dow;

        return ( dayOfWeek );

    }   //  end addDays()


    //@Override
    public int compareTo(DOW otherDOW)     //  (this - otherMyDate) : Neg:LessThan, 0:Equal, Pos:GreaterThan
    {
//??        return ( dayOfWeek.compareTo( otherDOW.getValue() ));

        if ( dayOfWeek == otherDOW.getValue() )
            return  (DOW_EQ);        // Equals

        if ( dayOfWeek > otherDOW.getValue() )
            return  (DOW_GT);       // Greater Than

        //if ( dayOfWeek < otherDOW.getValue() )
            return  (DOW_LT);       // Less Than

    }   //  end compareTo()

    // Getter
    public int getValue()
    {
        return ( dayOfWeek );
    }   //  end getOrdinal()


    // Setter
    public boolean setValue(int dow)
    {
        if( validateRange(dow))
        {
            dayOfWeek = dow;
            return(true);
        }
        else if ( dow == DOW_Invalid )
        {
            dayOfWeek = DOW_Invalid;
            return(true);
        }
            return (false);

    }   //  end getOrdinal()


    // Get short string for current dow
    String getShortStr(int dow)
    {
        if ( !validateRange(dow) )
            dow =   DOW_Invalid;
        return ( dowStrShort[dow] );
    }   //  end getShortStr()

    String getShortStr()
    {
        return ( getShortStr(dayOfWeek) );
    }   //  end getShortStr()


    // Get long string for current dow
    String getLongStr(int dow)
    {
        if ( !validateRange(dow) )
            dow =   DOW_Invalid;
        return ( dowStrLong[dow] );
    }   //  end getLongStr()

    String getLongStr()
    {
        return ( getLongStr(dayOfWeek) );
    }   //  end getLongStr()



}   //  end class DOW
