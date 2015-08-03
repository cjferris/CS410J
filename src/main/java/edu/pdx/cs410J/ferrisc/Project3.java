package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.*;

/**
 * @author - Chris Ferris
 * This program manages phone calls into a phone bill.
 * The main class for the CS410J Phone Bill Project.
 * Options:  -README - Prints this message
 *           -print - Prints out the inputted phone call.
 *           -textFile file - allows file read/write operations.
 *               If call argumentfilename.contentEquals("-")s are present, it should be written to the file."
 *               Otherwise, it loads the contents of the file.
 * 0: Customer name (any string of characters).
 * 1: Caller number (###-###-#### format).
 * 2: Callee number (###-###-#### format).
 * 3: Start date and  time of call (##/##/#### format).
 * 4: Start time of call (##:## 12-hour format).
 * 5: Start time marker of call (AM or PM).
 * 6: End Date and end time of call (##/##/#### format)
 * 7: End time of call (##:## 12-hour format).
 * 8: End time marker of call (AM or PM).
 */
public class Project3 {

	public static boolean checkCommandLineArguments(String[] args) {
		boolean argumentsAreGood = true;
		for ( int i = 0; i < 8; ++i ) {
			switch ( i ) {
				case 0:
				case 1:
					if ( args[i].length() != 12 ) {
						err.println("Error: Error: Phone number is the wrong length.");
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
										err.println("Error: Phone number has improper characters.");
									}
									break;
								case 3:
								case 7:
									if ( args[i].charAt(j) != '-' ) {
										argumentsAreGood = false;
										err.println("Error: Phone number has improper characters.");
									}
									break;
							}
						}
					}
					break;
				case 2:
				case 5:
					if ( !(args[i].length() > 7 && args[i].length() < 11) ) {
						argumentsAreGood = false;
						err.println("Error: Date is the wrong length.");
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
										err.println("Error: Date has improper characters.");
									}
									break;
								case 2:
								case 5:
									if ( args[i].charAt(j) != '/' ) {
										argumentsAreGood = false;
										err.println("Error: Date has improper characters.");
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
										err.println("Error: Date has improper characters.");
									break;
								case 1:
								case 3:
									if ( args[i].charAt(j) != '/' )
										err.println("Error: Date has improper characters.");
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
										err.println("Error: Date has improper characters.");
									}
									break;
								case 1:
									if ( !((args[i].charAt(j) == '/' && (args[i].charAt(j + 1) >= '0' && args[i].charAt(j + 1) <= '9'))
											       || (args[i].charAt(j + 1) == '/' && (args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9'))) ) {
										argumentsAreGood = false;
										err.println("Error: Date has improper characters.");
									}
									break;
								case 4:
									if ( args[i].charAt(j) != '/' ) {
										argumentsAreGood = false;
										err.println("Error: Date has improper characters.");
									}
									break;
								default:
									break;
							}
						}
					}
					break;
				case 3:
				case 6:
					if ( !(args[i].length() > 3 && args[i].length() < 6) ) {
						argumentsAreGood = false;
						err.println("Error: Time is the wrong length.");
					} else if ( args[i].length() == 5 ) {
						if ( args[i].charAt(0) > '1' ) {
							argumentsAreGood = false;
							err.println("Error: Time is the wrong format.");
						} else if ( args[i].charAt(0) == '1' && !(args[i].charAt(1) <= '2') ) {
							argumentsAreGood = false;
							err.println("Error: Time is the wrong format.");
						}
						for ( int j = 0; j < 5; ++j ) {
							switch ( j ) {    // ##:## case
								case 0:
								case 1:
								case 3:
								case 4:
									if ( !(args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9') ) {
										argumentsAreGood = false;
										err.println("Error: Time has improper characters.");
									}
									break;
								case 2:
									if ( args[i].charAt(j) != ':' ) {
										argumentsAreGood = false;
										err.println("Error: Time has improper characters.");
									}
									break;
							}
						}
					} else if ( args[i].length() == 4 ) {
						for ( int j = 0; j < 4; ++j ) {
							switch ( j ) {    // #:## case
								case 0:
								case 2:
								case 3:
									if ( !(args[i].charAt(j) >= '0' && args[i].charAt(j) <= '9') ) {
										argumentsAreGood = false;
										err.println("Error: Time has improper characters.");
									}
									break;
								case 1:
									if ( args[i].charAt(j) != ':' ) {
										argumentsAreGood = false;
										err.println("Error: Time has improper characters.");
									}
									break;
							}
						}
					}
					break;
				case 4:
				case 7:
					if ( !(args[i].toUpperCase().contentEquals("AM") || args[i].toUpperCase().contentEquals("PM")) ) {
						argumentsAreGood = false;
						err.println("Error: Time marker has improper characters.");
					}
					break;
			}
		}
		return argumentsAreGood;
	}

	public static void README() {
		out.println(" * @author - Chris Ferris\n" +
				            " * This program manages phone calls into a phone bill.\n" +
				            " * The main class for the CS410J Phone Bill Project.\n" +
				            " * Options:  -README - Prints this message\n" +
				            " *           -print - Prints out the inputted phone call.\n" +
				            " *           -textFile file - allows file read/write operations.\n" +
				            " *               If file exists, it parses the file then writes back to it.\n" +
				            " *               Otherwise, it just writes to the file.\n" +
										" *           -pretty file - Writes the phone bill, in a readable format to the file." +
										" *               Must contain -textFile file option also." +
				            " * 0: Customer name (any string of characters).\n" +
				            " * 1: Caller number (###-###-#### format).\n" +
				            " * 2: Callee number (###-###-#### format).\n" +
				            " * 3: Start date and  time of call (##/##/#### format).\n" +
				            " * 4: Start time of call (##:## 12-hour format).\n" +
				            " * 5: Start time marker of call (AM or PM).\n" +
				            " * 6: End Date and end time of call (##/##/#### format)\n" +
				            " * 7: End time of call (##:## 12-hour format).\n" +
				            " * 8: End time marker of call (AM or PM).");
		exit(1);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Class c = AbstractPhoneBill.class;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
		String[] temp;
		String name = null;
		File file = null, pFile = null;
		boolean isPrint = false, isTextFile = false, isPretty = false;
		int options = 0;
		PhoneCall call = null;
		PhoneBill bill = null;
		if ( args.length == 1 && args[0].contentEquals("-README") )
			README();
		else if ( args.length < 9 ) {
			err.println("Error: Missing command line arguments");
			exit(-1);
		}
		for ( String s : args ) {
			if ( s.equals("-print") ) {
				isPrint = true;
				++options;
			} else if ( s.equals("-textFile") ) {
				isTextFile = true;
				options += 2;
			} else if ( s.equals("-pretty") ) {
				isPretty = true;
				options += 2;
			}
		}

		temp = new String[args.length - options];
		int index = 0;
		for ( int i = 0; i < args.length; ++i ) {
			if ( args[i].equals("-print") ) {
				isPrint = true;
				continue;
			} else if ( args[i].equals("-textFile") ) {
				++i;
				file = new File(args[i]);
				continue;
			} else if ( args[i].equals("-pretty") ) {
				++i;
				pFile = new File(args[i]);
				continue;
			} else if ( name == null )
				name = args[i];
			else if ( name != null )
				temp[index++] = args[i];
		}

		if( isPretty && !isTextFile) {
			err.println("Error: Pretty option without textFile option.");
			exit(-1);
		} else if ( isPretty || isTextFile ) {
			if ( !(file.toString().endsWith(".txt") || pFile.toString().contentEquals("-") || pFile.toString().endsWith(".txt")) ) {
				err.println("Error: Filename is the wrong format");
				exit(-1);
			}
			if ( isPretty && !(pFile.exists() ) ) {
				try {
					pFile.createNewFile();
				} catch ( IOException e ) {
					err.println("IOException Error.");
				}
			} if ( !(file.exists() ) ) {
				try {
					file.createNewFile();
					bill = new PhoneBill(name);
				} catch ( IOException e ) {
					err.println("IOException Error.");
				}
			} else {
				try {
					if ( !new Scanner(file).nextLine().equals(name) )
						err.println("Error: Customer name given does not match name in " + file.toString());
				} catch ( FileNotFoundException e ) {
					err.println("Error: File not found");
				}
				try {
					bill = (PhoneBill) new TextParser(file).parse();
				} catch ( ParserException e ) {
					err.println();
				}
			}
		}
		if ( checkCommandLineArguments(temp) ) {
			if ( bill == null )
				bill = new PhoneBill(name);
			call = new PhoneCall(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6], temp[7]);
			bill.addPhoneCall(call);
		} else {
			exit(-1);
		}
		if ( isTextFile ) {
			try {
				new TextDumper(file).dump(bill);
			} catch ( IOException e ) {
				err.println();
			}
		}
		if ( isPretty ) {
			try {
				new PrettyPrinter(pFile).dump(bill);
			} catch ( IOException e ) {
				err.println();
			}
		}
		if ( isPrint )
			out.println(bill.toString() + '\n' + call.toString());
		exit(1);
	}
}

