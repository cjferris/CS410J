package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.AbstractPhoneCall;
import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Christopher
 */
public class PhoneBill extends AbstractPhoneBill {

	protected String Customer;
	protected Collection<AbstractPhoneCall> phoneCalls;


	/**
	 * PhoneBill - constructor for phone bill.
	 * @param customer
	 */
	public PhoneBill(String customer) {
		this.Customer = customer;
		phoneCalls = new ArrayList<AbstractPhoneCall>();
	}

	@Override
	public String getCustomer() {
		return Customer;
	}

	@Override
	public void addPhoneCall(AbstractPhoneCall PhoneCall) {
		phoneCalls.add(PhoneCall);
	}

	@Override
	public Collection getPhoneCalls() {
		return phoneCalls;
	}

	/**
	 * Print - Prints the details of the phone bill.
	 */
	public void Print() {
		System.out.println(this.toString());
		for(AbstractPhoneCall call : phoneCalls)
			System.out.println(call.toString());
	}
}
