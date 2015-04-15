package org.example.roman;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Roman App.
 */
public class RomanTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RomanTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RomanTest.class );
    }

    public void testRomanOneLetter()
	{
		Roman r = new Roman();
		assertTrue( r.roman("") == 0 );
		assertTrue( r.roman("I") == 1 );
		assertTrue( r.roman("V") == 5 );
		assertTrue( r.roman("X") == 10 );
		assertTrue( r.roman("L") == 50 );
		assertTrue( r.roman("C") == 100 );
		assertTrue( r.roman("D") == 500 );
		assertTrue( r.roman("M") == 1000 );
	}
	
	public void testRomanOneLetterUnknown()
	{
		Roman r = new Roman();
		boolean exception = false;
		try {
			assertTrue( r.roman("P") == 1000 );
		}
		catch( RuntimeException re)
		{
			exception = true;
		}
		assertTrue( exception );
	}
	
	public void testRomanTwoLetters()
	{
		Roman r = new Roman();
		assertTrue( r.roman("IV") == 4 );
		assertTrue( r.roman("II") == 2 );
		assertTrue( r.roman("VI") == 6 );
		assertTrue( r.roman("IX") == 9 );
		assertTrue( r.roman("XI") == 11 );
		assertTrue( r.roman("XX") == 20 );
		assertTrue( r.roman("XL") == 40 );
		assertTrue( r.roman("CC") == 200 );
		assertTrue( r.roman("CI") == 101 );
		assertTrue( r.roman("XC") == 90 );
		assertTrue( r.roman("XD") == 490 );
		assertTrue( r.roman("CM") == 900 );
		assertTrue( r.roman("MC") == 1100 );
		assertTrue( r.roman("MX") == 1010 );
		assertTrue( r.roman("MV") == 1005 );
		assertTrue( r.roman("MI") == 1001 );
	}
}
