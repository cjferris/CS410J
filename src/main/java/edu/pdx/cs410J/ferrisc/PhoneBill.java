package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.AbstractPhoneCall;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Christopher
 */
public class PhoneBill extends AbstractPhoneBill {

	protected String Customer;
	protected Collection<PhoneCall> phoneCalls;


	/**
	 * PhoneBill - constructor for phone bill.
	 * @param customer
	 */
	public PhoneBill(String customer) {
		this.Customer = customer;
		phoneCalls = new ArrayList<PhoneCall>();
	}

	@Override
	public String getCustomer() {
		return Customer;
	}

	@Override
	public void addPhoneCall(AbstractPhoneCall phoneCall) {
		phoneCalls.add((PhoneCall) phoneCall);
		if(phoneCalls.size() > 1)
			((List<PhoneCall>) phoneCalls).sort((c1, c2) -> c1.compareTo(c2));
	}

	@Override
	public Collection<PhoneCall> getPhoneCalls() {
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
