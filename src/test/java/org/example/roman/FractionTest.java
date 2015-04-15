package org.example.roman;

import junit.framework.*; 

public class FractionTest extends TestCase
{
    public FractionTest( String name ) { super( name ); }
        // public final void setUp() {}
	public static void main( String [] args )
	{
		testDivisionBy3();
		testDivisionBy10();
		testExp();
	}
    public static void testDivisionBy3()
	{
		Fraction q = new Fraction( 1, 3 );
                assertEquals( "0.33333333", ("" + q).substring( 0, 10 ) );
		assertEquals( "1", "" + q.multiply( 3 ) );
                assertEquals( new Fraction( 1, 1 ), q.multiply( 3 ) );
	}
	public static void testDivisionBy10()
	{
		Fraction q = new Fraction( 1, 10 );
		Fraction q2 = q.add( q );
		Fraction q4 = q2.add( q2 );
		Fraction q8 = q4.add( q4 );
		Fraction q10 = q8.add( q2 );
		assertEquals( new Fraction( 2, 10 ), q2 );
		assertEquals( new Fraction( 4, 10 ), q4 );
		assertEquals( new Fraction( 8, 10 ), q8 );
		assertEquals( new Fraction( 10, 10 ), q10 );
	}
        public static Fraction arctan( Fraction x, int times )
        {
            Fraction q = x;
            for ( int i = 0; i < times; i++ )
            {
                int expon = 2 * i + 3;
                Fraction elem = x.exp( expon ).multiply( 
                                                new Fraction( 1, expon ) );
                if ( i % 2 == 0 ) q = q.subtract( elem );
                else q = q.add( elem );
            }
            return q;
        }
        public static void testExp()
	{
            Fraction half = new Fraction( 1, 2 );
            assertEquals( new Fraction( 4, 1 ), half.exp( -2 ) );
            assertEquals( new Fraction( 2, 1 ), half.exp( -1 ) );
            assertEquals( new Fraction( 1, 1 ), half.exp( 0 ) );
            assertEquals( new Fraction( 1, 2 ), half.exp( 1 ) );
            assertEquals( new Fraction( 1, 4 ), half.exp( 2 ) );
            assertEquals( new Fraction( 1, 8 ), half.exp( 3 ) );
            assertEquals( new Fraction( 1, 16 ), half.exp( 4 ) );
            assertEquals( new Fraction( 1, 32 ), half.exp( 5 ) );
            assertEquals( new Fraction( 1, 64 ), half.exp( 6 ) );
            assertEquals( new Fraction( 1, 128 ), half.exp( 7 ) );
            assertEquals( new Fraction( 1, 256 ), half.exp( 8 ) );
            assertEquals( new Fraction( 1, 512 ), half.exp( 9 ) );
            assertEquals( new Fraction( 1, 1024 ), half.exp( 10 ) );
            assertEquals( "0.0009765625", "" + new Fraction( 1, 1024 ) );
            for ( int i = 1; i < 100; i++ )
            {
                int pow = 2;
                for ( int j = 1; j < i; j++ ) pow *= 2;
                assertEquals( new Fraction( 1, pow ), half.exp( i ) );
            }
	}
    
}
