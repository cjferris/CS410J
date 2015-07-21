package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.AbstractPhoneBill;

import static java.lang.System.err;

/**
 * @author - Chris Ferris
 * This program manages phone calls into a phone bill.
 * The main class for the CS410J Phone Bill Project
 *             0: Customer name (any string of characters).
 *             1: Caller number (###-###-#### format).
 *             2: Callee number (###-###-#### format).
 *             3: Date and start time of call (##/##/#### ##:## 24-hour format).
 *             4: Date and end time of call (##/##/#### ##:## 24-hour format).
 */
public class Project1 {

	public static PhoneBill bill;
	public static PhoneCall call;

	boolean checkCommandLineArguments(String[] args) {
		boolean argumentsAreGood = true;
		for ( int i = 1; i < args.length; ++i ) {
			switch ( i ) {
				case 1:
				case 2:
					if ( args[i].length() != 12 ) {
						err.println("Error: Phone number is the wrong length.");
						argumentsAreGood = false;
					} else {
						for ( int j = 0; j < args[i].length(); ++j ) {
							switch ( j ) {
								case 0:
								case 1:
								case 2:
								case 4:
								case 5:
								case 6:
								case 8:
								case 9:
								case 10:
								case 11:
									if ( !(args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9') ) {
										argumentsAreGood = false;
										err.println("Phone number has improper characters.");
									}
									break;
								case 3:
								case 7:
									if ( args[i].charAt(j) != '-' ) {
										argumentsAreGood = false;
										err.println("Phone number has improper characters.");
									}
									break;
							}
						}
					}
					break;
				case 3:
				case 5:
					if ( !(args[i].length() > 7 && args[i].length() < 11) ) {
						argumentsAreGood = false;
						err.println("Date is the wrong length.");
					} else if ( args[i].length() == 10 ) {
						for ( int j = 0; j < 10; ++j ) {
							switch ( j ) {    // ##/##/#### case
								case 0:
								case 1:
								case 3:
								case 4:
								case 6:
								case 7:
								case 8:
								case 9:
									if ( !(args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9') ) {
										argumentsAreGood = false;
										err.println("Date has improper characters.");
									}
									break;
								case 2:
								case 5:
									if ( args[i].charAt(j) != '/' ) {
										argumentsAreGood = false;
										err.println("Date has improper characters.");
									}
									break;
							}
						}
					} else if ( args[i].length() == 8 ) {
						for ( int j = 0; j < 8; ++j ) {
							switch ( j ) {    // #/#/#### case
								case 0:
								case 2:
								case 4:
								case 5:
								case 6:
								case 7:
									if ( !(args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9') )
										err.println("Date has improper characters.");
									break;
								case 1:
								case 3:
									if ( args[i].charAt(j) != '/' )
										err.println("Date has improper characters.");
									break;
							}
						}
					} else if ( args[i].length() == 9 ) {
						for ( int j = 0; j < 9; ++j ) {
							switch ( j ) {    // ##/#/#### case or
								case 0:         // #/##/#### case
								case 3:
								case 5:
								case 6:
								case 7:
								case 8:
									if ( !(args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9') ) {
										argumentsAreGood = false;
										err.println("Date has improper characters.");
									}
									break;
								case 1:
								case 2:
									if (!((args[i].charAt(j) == '/' && (args[i].charAt(j + 1) >= '0' && args[i].charAt(j + 1) <= '9'))
											|| (args[i].charAt(j + 1) == '/' && (args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9')))) {
										argumentsAreGood = false;
										err.println("Date has improper characters.");
									}
									break;
								case 4:
									if(args[i].charAt(j) != '/') {
										argumentsAreGood = false;
										err.println("Date has improper characters.");
									}
									break;
							}
						}
					}
					break;
				case 4:
				case 6:
					if ( !(args[i].length() > 3 && args[i].length() < 6) ) {
						argumentsAreGood = false;
						err.println("Time is the wrong length.");
					}else if ( args[i].length() == 5 ) {
						for ( int j = 0; j < 5; ++j ) {
							switch ( j ) {    // ##:## case
								case 0:
								case 1:
								case 3:
								case 4:
									if ( !(args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9') ) {
										argumentsAreGood = false;
										err.println("Time has improper characters.");
									}
									break;
								case 2:
									if ( args[i].charAt(j) != ':' ) {
										argumentsAreGood = false;
										err.println("Time has improper characters.");
									}
									break;
							}
						}
					}else if ( args[i].length() == 4 ) {
						for ( int j = 0; j < 4; ++j ) {
							switch ( j ) {    // #:## case
								case 0:
								case 2:
								case 3:
									if ( !(args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9') ) {
										argumentsAreGood = false;
										err.println("Time has improper characters.");
									}
									break;
								case 1:
									if ( args[i].charAt(j) != ':' ) {
										argumentsAreGood = false;
										err.println("Time has improper characters.");
									}
									break;
							}
						}
					}
					break;
			}
		}
		return argumentsAreGood;
	}

	public static void main(String[] args)  {
		Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
		String [] temp;
		if(args.length == 1 && args[0].contentEquals("-README")) {
			System.out.println("/* @author - Chris Ferris\nThis program manages phone calls into a phone bill.\n" +
					" * The main class for the CS410J Phone Bill Project.\n" +
					" * Options -README: Prints this message -print: Prints out the inputted phone call.\n" +
					" *             0: Customer name (any string of characters).\n" +
					" *             1: Caller number (###-###-#### format).\n" +
					" *             2: Callee number (###-###-#### format).\n" +
					" *             3: Start date and  time of call (##/##/#### format).\n" +
					" *             4: Start time of call (##:## 24-hour format).\n" +
					" *             5: End Date and end time of call (##/##/#### format)\n" +
					" *             6: End time of call (##:## 24-hour format).");
			System.exit(1);
		} else if(args.length < 7 || (args[0].contentEquals("-print") && args.length < 8)) {
			err.println("Error: Missing command line arguments");
			System.exit(-1);
		} else if (args.length > 7 && !(args[0].contentEquals("-print"))) {
			err.println("Error: Unknown command line arguments");
			System.exit(-1);
		}
		if(args[0].contentEquals("-print") && args.length == 8) {
			temp = new String[args.length-1];
			System.arraycopy(args, 1, temp, 0, args.length - 1);
		} else {
			temp = new String[args.length];
			System.arraycopy(args, 0, temp, 0, args.length);
		}
		if(!new Project1().checkCommandLineArguments(temp) )
			System.exit(-1);
		bill = new PhoneBill(temp[0]);
		call = new PhoneCall(temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
		bill.addPhoneCall(call);
		if(args[0].contentEquals("-print"))
			System.out.println(bill.toString() + '\n' + call.toString());
		System.exit(1);
	}

}

