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
	
	public void failureTestFor( int value, String roman )
	{
		Roman r = new Roman();
		boolean failed = false;
		try {
			assertEquals( value, r.roman( roman ) );
		}
		catch( RuntimeException re )
		{
			failed = true;
		}
		assertTrue( failed );
	}
	
	public void testRomanOneLetterUnknown()
	{
		failureTestFor( 1000, "P" );
	}
	
	public void testRomanTwoLetters()
	{
		Roman r = new Roman();
		assertTrue( r.roman("IV") == 4 );
		assertTrue( r.roman("II") == 2 );
		assertTrue( r.roman("VI") == 6 );
		assertTrue( r.roman("IX") == 9 );
		assertTrue( r.roman("XI") == 11 );
		assertTrue( r.roman("XV") == 15 );
		assertTrue( r.roman("XX") == 20 );
		assertTrue( r.roman("XL") == 40 );
		assertTrue( r.roman("CC") == 200 );
		assertTrue( r.roman("IC") == 99 );
		assertTrue( r.roman("CI") == 101 );
		assertTrue( r.roman("XC") == 90 );
		assertTrue( r.roman("XD") == 490 );
		assertTrue( r.roman("CM") == 900 );
		assertTrue( r.roman("MC") == 1100 );
		assertTrue( r.roman("MX") == 1010 );
		assertTrue( r.roman("MV") == 1005 );
		assertTrue( r.roman("MI") == 1001 );
	}
	
	
	public void testRomanTwoLettersFail()
	{
		Roman r = new Roman();
		failureTestFor( 5, "VX" );
		failureTestFor( 0, "VV" );
		failureTestFor( 40, "VL" );
		failureTestFor( 40, "LL" );
		failureTestFor( 95, "VC" );
		failureTestFor( 50, "LC" );
		failureTestFor( 495, "VD" );
		failureTestFor( 495, "DD" );
		failureTestFor( 950, "LM" );
		failureTestFor( 990, "XM" );
		failureTestFor( 999, "IM" );
	}
	
	public void testRoman3Letters()
	{
		Roman r = new Roman();
		assertEquals( 7, r.roman("VII") );
		assertEquals( 3, r.roman("III") );
		assertEquals( 12, r.roman("XII") );
		assertEquals( 16, r.roman("XVI") );
		assertEquals( 19, r.roman("XIX") );
		assertEquals( 21, r.roman("XXI") );
		assertEquals( 25, r.roman("XXV") );
		assertEquals( 30, r.roman("XXX") );
		assertEquals( 41, r.roman("XLI") );
		assertEquals( 45, r.roman("XLV") );
		assertEquals( 300, r.roman("CCC") );
		assertEquals( 201, r.roman("CCI") );
		assertEquals( 205, r.roman("CCV") );
		assertEquals( 210, r.roman("CCX") );
		assertEquals( 250, r.roman("CCL") );
		assertEquals( 190, r.roman("CXC") );
		assertEquals( 491, r.roman("XDI") );
		assertEquals( 495, r.roman("XDV") );
		assertEquals( 901, r.roman("CMI") );
		assertEquals( 905, r.roman("CMV") );
		assertEquals( 1002, r.roman("MII") );
		assertEquals( 1101, r.roman("MCI") );
		assertEquals( 1105, r.roman("MCV") );
		assertEquals( 1110, r.roman("MCX") );
		assertEquals( 1150, r.roman("MCL") );
		assertEquals( 1200, r.roman("MCC") );
		assertEquals( 1501, r.roman("MDI") );
		assertEquals( 1505, r.roman("MDV") );
		assertEquals( 1510, r.roman("MDX") );
		assertEquals( 1550, r.roman("MDL") );
		assertEquals( 1600, r.roman("MDC") );
		assertEquals( 1011, r.roman("MXI") );
		assertEquals( 1015, r.roman("MXV") );
		assertEquals( 1020, r.roman("MXX") );
		assertEquals( 1051, r.roman("MLI") );
		assertEquals( 1055, r.roman("MLV") );
		assertEquals( 1060, r.roman("MLX") );
		assertEquals( 1006, r.roman("MVI") );
	}
	
	public void testRoman3LettersFail()
	{
		Roman r = new Roman();
		failureTestFor( 4, "IVI" );
		failureTestFor( 11, "VVI" );
		failureTestFor( 15, "VVVº" );
		failureTestFor( 98, "IIC" );
		failureTestFor( 3, "IIV" );
		failureTestFor( 8, "IIX" );
		failureTestFor( 10, "IXI" );
		failureTestFor( 19, "IXX" );
		failureTestFor( 39, "IXL" );
		failureTestFor( 199, "ICC" );
		failureTestFor( 100, "ICI" );
		failureTestFor( 30, "XXL" );
		failureTestFor( 89, "IXC" );
		failureTestFor( 489, "IXD" );
		failureTestFor( 899, "ICM" );
		failureTestFor( 899, "IMC" );
		failureTestFor( 1099, "MIC" );
		failureTestFor( 1009, "IMX" );
		failureTestFor( 1009, "MIX" );
		failureTestFor( 1004, "IMV" );
		failureTestFor( 1004, "MIV" );
		failureTestFor( 1000, "IMI" );
	}
}
