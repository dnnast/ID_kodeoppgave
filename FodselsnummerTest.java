/**
* The FodselsnummerTest program implements test to check correctness of Fodselsnummer program.
*
* @author Anastasiia Danshina
* @version 1.0
* @since 2020-04-27
*/

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FodselsnummerTest {
	private Fodselsnummer fsn;

	@Before
	public void setUp() throws Exception {
		fsn = new Fodselsnummer();
	}

	@After
	public void tearDown() throws Exception {
		fsn = null;
	}

	@Test
	public void testValidateFodselsnummer() {
		// wrong length - checkBirthDateFormat method has to return false
	    assertFalse("validateFodselsnummer method Failed", fsn.validateFodselsnummer("321523356982"));
	    // wrong length - checkBirthDateFormat method has to return false
	    assertFalse("validateFodselsnummer method Failed", fsn.validateFodselsnummer("143356982"));
	    // valid fodselsnummer - checkBirthDateFormat method has to return true
	    assertTrue("validateFodselsnummer method Failed", fsn.validateFodselsnummer("09123356982"));
	}

	@Test
	public void testCheckBirthDateFormat() {
		// wrong day format - checkBirthDateFormat method has to return false
	    assertFalse("checkBirthDateFormat method Failed", fsn.checkBirthDateFormat("32123356982"));
	    // wrong month format - checkBirthDateFormat method has to return false
	    assertFalse("checkBirthDateFormat method Failed", fsn.checkBirthDateFormat("14133356982"));
	    // wrong number of days in non leap year - checkBirthDateFormat method has to return false
	    assertFalse("checkBirthDateFormat mathod Failed", fsn.checkBirthDateFormat("29020556982"));
	    // valid date format - checkBirthDateFormat method has to return true
	    assertTrue("checkBirthDateFormat mathod Failed", fsn.checkBirthDateFormat("09123356982"));
	}

	@Test
	public void testCheckControlDigits() {
		// wrong first control digit - checkControlDigits method has to return false
	    assertFalse("checkControlDigits mathod Failed", fsn.checkControlDigits("09123356992"));
	    // wrong second control digit - checkControlDigits method has to return false
	    assertFalse("checkControlDigits mathod Failed", fsn.checkControlDigits("09123356987"));
        // valid control digits - checkControlDigits method has to return true
	    assertTrue("checkControlDigits mathod Failed", fsn.checkControlDigits("09123356982"));
	}

}
