package org.example.roman;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Roman
{
    /*
	Roman numerals are based on seven symbols:

	Symbol	Value
	I	1
	V	5
	X	10
	L	50
	C	100
	D	500
	M	1,000

	Numbers are formed by combining symbols together and adding the values. 
	For example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are 
	placed in order of value, starting with the largest values. When smaller 
	values precede larger values, the smaller values are subtracted from the 
	larger values, and the result is added to the total. For example:
	 MCMXLIV = 1000 + (1000 - 100) + (50 - 10) + (5 - 1) = 1944.

	?The symbols "I", "X", "C", and "M" can be repeated three times 
		in succession, but no more. (They may appear four times if the 
		third and fourth are separated by a smaller value, such as XXXIX.) 
		"D", "L", and "V" can never be repeated.
	?"I" can be subtracted from "V" and "X" only. "X" can be subtracted
		from "L" and "C" only. "C" can be subtracted from "D" and "M" only. 
		"V", "L", and "D" can never be subtracted.
	?Only one small-value symbol may be subtracted from any large-value 
		symbol.
	?A number written in [16]Arabic numerals can be broken into digits. 
		For example, 1903 is composed of 1, 9, 0, and 3. To write the Roman
		numeral, each of the non-zero digits should be treated separately. 
		In the above example, 1,000 = M, 900 = CM, and 3 = III. Therefore, 
		1903 = MCMIII.

	Input to your program consists of lines of text detailing your notes on 
	the conversion between intergalactic units and roman numerals.

	You are expected to handle invalid queries appropriately.

	Test input:
	glob is I
	prok is V
	pish is X
	tegj is L
	glob glob Silver is 34 Credits
	glob prok Gold is 57800 Credits
	pish pish Iron is 3910 Credits
	how much is pish tegj glob glob ?
	how many Credits is glob prok Silver ?
	how many Credits is glob prok Gold ?
	how many Credits is glob prok Iron ?
	how much wood could a woodchuck chuck if a woodchuck could chuck wood ?

	Test Output:
	pish tegj glob glob is 42
	glob prok Silver is 68 Credits
	glob prok Gold is 57800 Credits
	glob prok Iron is 782 Credits
	I have no idea what you are talking about

    */
	public boolean bigger( char a, char b )
	{
		return roman( "" + a ) > roman( "" + b );
	}
	public int roman( String s )
	{
		s = s.trim();
		switch( s.length() )
		{
			case 0:  return 0; 
			case 1: {
				switch( s )
				{
					case "I": return 1;
					case "V": return 5;
					case "X": return 10;
					case "L": return 50;
					case "C": return 100;
					case "D": return 500;
					case "M": return 1000;
				}
			}
			case 2: {
				char a = s.charAt( 0 );
				char b = s.charAt( 1 );
				if ( bigger( b, a ) )
				{
					return roman( ""+b ) - roman( ""+a );
				}
				else {
					return roman( ""+a ) + roman( ""+b );
				}
			}
		}
		throw new RuntimeException( "Did not understand roman literal '" + s + "'" );
	}
    public static void main( String [] args )
		throws IOException
    {	
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		for ( ;; )
		{
			String s = in.readLine();
			if ( s == null || s.length() == 0 )
			{
				break;
			}
			System.out.println( s );
		}
    }
}
