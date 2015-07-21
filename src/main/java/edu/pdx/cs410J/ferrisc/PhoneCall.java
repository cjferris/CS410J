package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.AbstractPhoneCall;

/**
 * Created by Christopher on 7/8/2015.
 */
public class PhoneCall extends AbstractPhoneCall {

	protected String Caller;
	protected String Callee;
	protected String StartTimeString;
	protected String EndTimeString;

	/**
	 * PhoneCall - constructor for phone call.
	 * @param caller
	 * @param callee
	 * @param startDateString
	 * @param startTimeString
	 * @param endDateString
	 * @param endTimeString
	 */
	public PhoneCall(String caller, String callee, String startDateString, String startTimeString, String endDateString, String endTimeString) {
		this.Caller = caller;
		this.Callee = callee;
		this.StartTimeString = startDateString + " " + startTimeString;
		this.EndTimeString = endDateString + " " + endTimeString;
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
	public String getStartTimeString() {return StartTimeString;}

	@Override
	public String getEndTimeString() {return EndTimeString;}
}
