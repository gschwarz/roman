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
		assertTrue( r.roman("XV") == 15 );
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
	
	public void testRomanTwoLettersFail()
	{
		Roman r = new Roman();
		assertEquals( 5, r.roman("VX") );
		assertTrue( r.roman("VV") == 2 );
		assertTrue( r.roman("VL") == 40 );
		assertTrue( r.roman("VC") == 200 );
		assertTrue( r.roman("LC") == 101 );
		assertTrue( r.roman("IC") == 90 );
		assertTrue( r.roman("VD") == 490 );
		assertTrue( r.roman("LM") == 900 );
		assertTrue( r.roman("XM") == 1010 );
		assertTrue( r.roman("VM") == 1005 );
		assertTrue( r.roman("IM") == 1001 );
	}
	
	public void testRoman3Letters()
	{
		Roman r = new Roman();
		assertTrue( r.roman("VII") == 4 );
		assertTrue( r.roman("III") == 2 );
		assertTrue( r.roman("XVI") == 6 );
		assertTrue( r.roman("XIX") == 9 );
		assertTrue( r.roman("XXI") == 11 );
		assertTrue( r.roman("XXX") == 20 );
		assertTrue( r.roman("XLI") == 40 );
		assertTrue( r.roman("XLV") == 40 );
		assertTrue( r.roman("CCC") == 200 );
		assertTrue( r.roman("CCI") == 101 );
		assertTrue( r.roman("CXC") == 90 );
		assertTrue( r.roman("XDI") == 490 );
		assertTrue( r.roman("XDV") == 490 );
		assertTrue( r.roman("CMI") == 900 );
		assertTrue( r.roman("CMV") == 900 );
		assertTrue( r.roman("MCI") == 1100 );
		assertTrue( r.roman("MCV") == 1100 );
		assertTrue( r.roman("MCX") == 1100 );
		assertTrue( r.roman("MCL") == 1100 );
		assertTrue( r.roman("MCC") == 1100 );
		assertTrue( r.roman("MDI") == 1100 );
		assertTrue( r.roman("MDV") == 1100 );
		assertTrue( r.roman("MDX") == 1100 );
		assertTrue( r.roman("MDL") == 1100 );
		assertTrue( r.roman("MDC") == 1100 );
		assertTrue( r.roman("MXI") == 1010 );
		assertTrue( r.roman("MXV") == 1010 );
		assertTrue( r.roman("MXX") == 1010 );
		assertTrue( r.roman("MLI") == 1010 );
		assertTrue( r.roman("MLV") == 1010 );
		assertTrue( r.roman("MLX") == 1010 );
		assertTrue( r.roman("MVI") == 1005 );
		assertTrue( r.roman("MII") == 1001 );
	}
	
	public void testRoman3LettersFail()
	{
		Roman r = new Roman();
		assertTrue( r.roman("IVI") == 4 );
		assertTrue( r.roman("VVI") == 4 );
		assertTrue( r.roman("VVVº") == 4 );
		assertTrue( r.roman("III") == 2 );
		assertTrue( r.roman("IIV") == 6 );
		assertTrue( r.roman("IIX") == 9 );
		assertTrue( r.roman("IXI") == 11 );
		assertTrue( r.roman("IXX") == 20 );
		assertTrue( r.roman("IXL") == 40 );
		assertTrue( r.roman("ICC") == 200 );
		assertTrue( r.roman("ICI") == 101 );
		assertTrue( r.roman("XXL") == 90 );
		assertTrue( r.roman("IXC") == 90 );
		assertTrue( r.roman("IXD") == 490 );
		assertTrue( r.roman("ICM") == 900 );
		assertTrue( r.roman("IMC") == 1100 );
		assertTrue( r.roman("IMX") == 1010 );
		assertTrue( r.roman("IMV") == 1005 );
		assertTrue( r.roman("IMI") == 1001 );
	}
}
