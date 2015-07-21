package edu.pdx.cs410J.ferrisc;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests the functionality in the {@link Project1} main class.
 */
public class Project1Test extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {return invokeMain( Project1.class, args );}

  /**
   * Tests that invoking the main method with no arguments issues an error
   */
  @Test
  public void test1NoCommandLineArguments() {
    MainMethodResult result = invokeMain();
    assertEquals(new Integer(-1), result.getExitCode());
    assertTrue(result.getErr().contains( "Missing command line arguments" ));
  }

	/**
	 * Test that README functions correctly
	 */
	@Test
	public void test2README() {
		MainMethodResult result = invokeMain(Project1.class, "-README");
		assertTrue(result.getOut().contains("Chris Ferris"));
		assertEquals(new Integer(1), result.getExitCode());
	}

	@Test
	public void test3CallerPhoneNumberContainsNonIntegers() {
		MainMethodResult result = invokeMain(Project1.class, "Test3", "ABC-456-7890", "234-567-8901", "03/03/2015", "12:00", "05/04/2015", "16:00");
		assertEquals(new Integer(-1), result.getExitCode());
		assertTrue(result.getErr().contains("Phone number has improper characters."));
	}

	@Test
	public void test4StartTimeMalformed() {
		MainMethodResult result = invokeMain(Project1.class, "Test4", "123-456-7890", "234-567-8901", "03/03/2015", "12:XX", "05/04/2015", "16:00");
		assertEquals(new Integer(-1), result.getExitCode());
		assertTrue(result.getErr().contains("Time has improper characters."));
	}

	@Test
	public void test5EndTimeMalformed() {
		MainMethodResult result = invokeMain(Project1.class, "Test5", "123-456-7890", "234-567-8901", "03/03/2015", "12:00", "05/04/1", "16:00");
		assertEquals(new Integer(-1), result.getExitCode());
		assertTrue(result.getErr().contains("Date is the wrong length."));
	}

	@Test
	public void test6UnknownCommandLineOption() {
		MainMethodResult result = invokeMain(Project1.class, "-fred", "Test6", "123-456-7890", "234-567-8901", "03/03/2015", "12:00", "05/04/2015", "16:00");
		assertEquals(new Integer(-1), result.getExitCode());
		assertTrue(result.getErr().contains("Error: Unknown command line arguments"));
	}

	@Test
	public void test7UnknownCommandLineArgument() {
		MainMethodResult result = invokeMain(Project1.class, "Test6", "123-456-7890", "234-567-8901", "03/03/2015", "12:00", "05/04/2015", "16:00", "fred");
		assertEquals(new Integer(-1), result.getExitCode());
		assertTrue(result.getErr().contains("Error: Unknown command line arguments"));
	}

	@Test
	public void test8Print() {
		MainMethodResult result = invokeMain(Project1.class, "-print", "Test8", "123-456-7890", "234-567-8901", "03/03/2015", "12:00", "05/04/2015", "16:00");
		assertEquals(new Integer(1), result.getExitCode());
		assertTrue(result.getOut().contains("Test8"));
	}

	@Test
	public void test9MultiWordNamePrint() {
		MainMethodResult result = invokeMain(Project1.class, "-print", "Test 9", "123-456-7890", "234-567-8901", "03/03/2015", "12:00", "05/04/2015", "16:00");
		assertEquals(new Integer(1), result.getExitCode());
		assertTrue(result.getOut().contains("Test 9"));
	}

	@Test
	public void test10NoEndDateOrTime() {
		MainMethodResult result = invokeMain(Project1.class, "Test10", "123-456-7890", "234-567-8901", "03/03/2015", "12:00");
		assertEquals(new Integer(-1), result.getExitCode());
		assertTrue(result.getErr().contains( "Missing command line arguments" ));
	}

}