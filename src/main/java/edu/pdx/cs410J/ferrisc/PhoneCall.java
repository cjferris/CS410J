package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Christopher on 7/8/2015.
 */
public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall> {

	protected String  Caller;
	protected String  Callee;
	protected String  StartTimeString;
	protected String  EndTimeString;
	protected Date    StartTime;
	protected Date    EndTime;
	protected int     CallLength;

	/**
	 * PhoneCall - constructor for phone call.
	 * @param caller
	 * @param callee
	 * @param startDateString
	 * @param startTimeString
	 * @param startTimeMark
	 * @param endDateString
	 * @param endTimeString
	 *  @param endTimeMark
	 */
	public PhoneCall(String caller, String callee, String startDateString, String startTimeString, String startTimeMark,
	                 String endDateString, String endTimeString, String endTimeMark) {
		this.Caller =           caller;
		this.Callee =           callee;
		this.StartTimeString =  startDateString + " " + startTimeString + " " + startTimeMark;
		this.EndTimeString =    endDateString + " " + endTimeString + " " + endTimeMark;
		this.StartTime =        getStartTime();
		this.EndTime =          getEndTime();
		this.CallLength = (int) ((EndTime.getTime() - StartTime.getTime())/60000L);
		if(CallLength < 0)
			System.err.println("Error: End time occurs before start time.");
	}

	@Override
	public String getCaller() {
		return Caller;
	}

	@Override
	public String getCallee() {
		return Callee;
	}

	@Override
	public Date getStartTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		try {
			return dateFormat.parse(StartTimeString);
		} catch ( ParseException e ) {
			System.err.println("Error: Start Time could not parse.");
		}
		return super.getStartTime();
	}

	@Override
	public String getStartTimeString() {
		String formattedDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(StartTime);
		return formattedDate;
	}

	@Override
	public Date getEndTime() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		try {
			return dateFormat.parse(EndTimeString);
		} catch ( ParseException e ) {
			System.err.println("Error: Start Time could not parse.");
		}
		return super.getEndTime();
	}

	@Override
	public String getEndTimeString() {
		String formattedDate = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(EndTime);
		return formattedDate;
	}

	/**
	 * Compares this object with the specified object for order.  Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 * <p>
	 * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
	 * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
	 * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
	 * <tt>y.compareTo(x)</tt> throws an exception.)
	 * <p>
	 * <p>The implementor must also ensure that the relation is transitive:
	 * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
	 * <tt>x.compareTo(z)&gt;0</tt>.
	 * <p>
	 * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
	 * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
	 * all <tt>z</tt>.
	 * <p>
	 * <p>It is strongly recommended, but <i>not</i> strictly required that
	 * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
	 * class that implements the <tt>Comparable</tt> interface and violates
	 * this condition should clearly indicate this fact.  The recommended
	 * language is "Note: this class has a natural ordering that is
	 * inconsistent with equals."
	 * <p>
	 * <p>In the foregoing description, the notation
	 * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
	 * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
	 * <tt>0</tt>, or <tt>1</tt> according to whether the value of
	 * <i>expression</i> is negative, zero or positive.
	 *
	 * @param o the object to be compared.
	 * @return a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object.
	 * @throws NullPointerException if the specified object is null
	 * @throws ClassCastException   if the specified object's type prevents it
	 *                              from being compared to this object.
	 */
	@Override
	public int compareTo(PhoneCall o) {
		int compare;
		if(this.StartTime.getTime() < o.StartTime.getTime())
			compare = -1;
		else if(this.StartTime.getTime() > o.StartTime.getTime())
			compare = 1;
		else {
			if(this.Caller.compareTo(o.getCaller()) < 0)
				compare = -1;
			else if(this.Caller.compareTo(o.getCaller()) > 0)
				compare = 1;
			else
				compare = 0;
		}
		return compare;
	}

	protected String toString(String [] string, String gap) {
		String build = "";
		for(String s: string)
			build += s + gap;
		build = build.substring(0, build.length()-1);
		return build;
	}

	protected String [] ensureLength(String [] string) {
		for(int i=0; i<string.length; ++i){
			if(string[i].length() < 2)
				string[i] = "0" + string[i];
		}
		return string;
	}

	@Override
	public String toString() {
		String [] Time = getStartTimeString().split(" ");
		String [] date = Time[0].split("/"), time = Time[1].split(":");
		String start, end;
		Time[0] = toString(ensureLength(date), "/");
		Time[1] = toString(ensureLength(time), ":");
		start = toString(Time, " ");
		Time = getEndTimeString().split(" ");
		date = Time[0].split("/");
		time = Time[1].split(":");
		Time[0] = toString(ensureLength(date), "/");
		Time[1] = toString(ensureLength(time), ":");
		end = toString(Time, " ");
		return Caller + '\t' + Callee + '\t' + start + '\t' + end + "\t" + CallLength;
	}
}
