package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

/**
 * Created by Christopher on 7/27/2015.
 */
public class PrettyPrinter implements PhoneBillDumper {

	protected static File file;

	public PrettyPrinter(File file){
		this.file = file;
	}

	@Override
	public void dump(AbstractPhoneBill abstractPhoneBill) throws IOException {
		PrintWriter write;
		if(file.toString().contentEquals("-"))
			write = new PrintWriter(out);
		else
			write = new PrintWriter(file);
		write.println(abstractPhoneBill.toString());
		write.println("Caller Number\tCallee Number\tStart Time\t\t\tEnd Time\t\t\tCall Length(min.)\n" +
									"********************************************************************************************");
		PhoneBill bill = (PhoneBill)abstractPhoneBill;
		bill.getPhoneCalls().forEach(write::println);
		write.close();
	}
}
