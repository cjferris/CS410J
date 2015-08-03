package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Christopher on 7/20/2015.
 */
public class TextDumper implements PhoneBillDumper {
	protected static File file;

	public TextDumper(File file){
		this.file = file;
	}

	@Override
	public void dump(AbstractPhoneBill abstractPhoneBill) throws IOException {
		PrintWriter write = new PrintWriter(file);
		write.println(abstractPhoneBill.getCustomer());
		PhoneBill bill = (PhoneBill)abstractPhoneBill;
		for(PhoneCall call : bill.getPhoneCalls()){
			write.println(call.getCaller());
			write.println(call.getCallee());
			write.println(call.getStartTimeString());
			write.println(call.getEndTimeString());
		}
		write.close();
	}
}
