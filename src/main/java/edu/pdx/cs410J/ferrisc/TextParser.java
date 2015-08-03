package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Christopher on 7/20/2015.
 */
public class TextParser implements PhoneBillParser {
	protected static File file;

	public TextParser(File file) { this.file = file;}

	@Override
	public AbstractPhoneBill parse() throws ParserException {
		Scanner read = null;
		PhoneBill phoneBill = null;
		try {
			read = new Scanner(file);
		} catch ( FileNotFoundException e ) {
			System.err.println("Error: " + file + " does not exist");
		}
		if(read.hasNext())
			phoneBill = new PhoneBill(read.nextLine());
		while ( read.hasNext() ) {
			String[] temp = new String[8];
			for ( int i = 0; i < 8; ++i ) {
				if ( i == 2 || i == 5 ) {
					String time[] = read.nextLine().split(" ");
					String date[] = time[0].split("/");
					date[2] = "20" + date[2];
					time[0] = date[0] + '/' + date[1] + '/' + date[2];
					temp[i] = time[0];
					++i;
					temp[i] = time[1];
					++i;
					temp[i] = time[2];
				} else
					temp[i] = read.nextLine();
			}
				if ( Project3.checkCommandLineArguments(temp) ) {
					PhoneCall call = new PhoneCall(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);
					phoneBill.addPhoneCall(call);
				} else
					throw new ParserException("Could not complete file parsing");
			}
		return phoneBill;
	}
}
