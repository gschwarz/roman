package org.example.roman;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Roman
{
    /*
	Req. 1:
	
	Roman numerals are based on seven symbols:

	Symbol	Value
	I	1
	V	5
	X	10
	L	50
	C	100
	D	500
	M	1,000

	Req. 2:
	
	Numbers are formed by combining symbols together and adding the values. 
	For example, MMVI is 1000 + 1000 + 5 + 1 = 2006. Generally, symbols are 
	placed in order of value, starting with the largest values. 
	
	Req. 3:
	
	When smaller 
	values precede larger values, the smaller values are subtracted from the 
	larger values, and the result is added to the total. For example:
	 MCMXLIV = 1000 + (1000 - 100) + (50 - 10) + (5 - 1) = 1944.

	 Req 4:
	?The symbols "I", "X", "C", and "M" can be repeated three times 
		in succession, but no more. (They may appear four times if the 
		third and fourth are separated by a smaller value, such as XXXIX.) 
		"D", "L", and "V" can never be repeated.
		
	Req. 5:
	?"I" can be subtracted from "V" and "X" only. "X" can be subtracted
		from "L" and "C" only. "C" can be subtracted from "D" and "M" only. 
		"V", "L", and "D" can never be subtracted.
		
	Req.6:
	?Only one small-value symbol may be subtracted from any large-value 
		symbol.
		
	Req. 7: (Is it really necessary???)
	?A number written in [16]Arabic numerals can be broken into digits. 
		For example, 1903 is composed of 1, 9, 0, and 3. To write the Roman
		numeral, each of the non-zero digits should be treated separately. 
		In the above example, 1,000 = M, 900 = CM, and 3 = III. Therefore, 
		1903 = MCMIII.

	Req. 8:
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
	private static	List<String> NumberList = new ArrayList<String>();
	static {
		NumberList.add( "I" );
		NumberList.add( "V" );
		NumberList.add( "X" );
		NumberList.add( "L" );
		NumberList.add( "C" );
		NumberList.add( "D" );
		NumberList.add( "M" );
	};
	
	private Map<String, String> numberMap = new HashMap<String, String>();
	private Map<String, Fraction> mineralMap = new HashMap<String, Fraction>();
	
	public boolean bigger( char a, char b )
	{
		return roman( "" + a ) > roman( "" + b );
	}
	
	public void forbidInvalidSubstraction( char a, char b )
	{
		int r1 = roman( ""+a );
		int r2 = roman( ""+b );
		if ( r1 == 5 || r1 == 50 || r1 == 500 )
		{
			 throw new RuntimeException( "Cannot substract '" + a + "' from '" + b + "'" );
		}					
		if ( r1 == 1 && r2 > 10 )
		{
			 throw new RuntimeException( "Cannot substract '" + a + "' from '" + b + "'" );
		}
		if ( r1 == 10 && r2 > 100 )
		{
			 throw new RuntimeException( "Cannot substract '" + a + "' from '" + b + "'" );
		}
		if ( r1 == 100 && r2 > 1000 )
		{
			 throw new RuntimeException( "Cannot substract '" + a + "' from '" + b + "'" );
		}
	}
	public int roman( String s )
	{
		System.out.println("Invoking roman( s = "+s+")");
		s = s.trim();
		int len = s.length();
		switch( len )
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
					default: throw new RuntimeException( "Did not understand roman literal '" + s + "'" );
				}
			}
			case 2: {
				char a = s.charAt( 0 );
				char b = s.charAt( 1 );
				if ( bigger( b, a ) )
				{
					forbidInvalidSubstraction( a, b );
					int r1 = roman( ""+a );
					int r2 = roman( ""+b );
					return r2 - r1;
				}
				else {
					int r1 = roman( ""+a );
					int r2 = roman( ""+b );
					if ( a == b )
					{
						if ( r1 == 5 || r1 == 50 || r1 == 500 )
						{
							 throw new RuntimeException( "Cannot add '" + a + "' to '" + b + "'" );
						}
					}
					return r1 + r2;
				}
			}
			default: // bigger than 2
			{
				int k = len / 2;
				int repeated = 1;
				int subtracting = 0;
				char subtracted = '-';
				for ( int i = 0; i < len - 1; i++ )
				{
					char a = s.charAt( i );
					char b = s.charAt( i + 1 );
					
					if ( bigger( b, a ) )
					{
						k = i > 0 ? i : i + 2; // cut off before or after if there is nothing before
						forbidInvalidSubstraction( a, b );
						if ( ++subtracting > 1 )
						{
							throw new RuntimeException( "Cannot subtract '"+subtracted+"' from '"+a+"' and then '" + a + "' from '"+b+"'" );
						}
						else {
							subtracted = a;
						}
						if ( repeated > 1 ) // trying to subract a repeated symbol
						{
							throw new RuntimeException( "Cannot subtract '"+a+"' more than once from '"+b+"'" );
						}
					}
					else {
						subtracting = 0;
					}
					if ( a == b )
					{
						if ( ++repeated > 3 )
						{
							throw new RuntimeException( "Cannot repeat symbol '" + a + "' more than 3 times" );
						}
					}
					else {
						repeated = 1;
					}
				}
				String s1 = s.substring( 0, k );
				String s2 = s.substring( k );
				System.out.println("Divided into s1 = '"+s1+"' and s2 = '"+s2+"'");
				return roman( s1 ) + roman( s2 );
			}
		}
	}
	public void complainAbout( String line )
	{
		System.out.println( "I have no idea what you are talking about when you say '"+line+"'");
	}
	public void interpret( String line )
	{
		line = line.trim();
		String [] str = line.split(" ");
		int len = str.length;
		switch ( len )
		{
			case 0: 
				break; // empty line, do nothing
			case 1:
			case 2: 
				complainAbout( line );
				break;
			case 3:
				if ( ! str[1].equals("is"))
				{
					complainAbout( line );
				}
				else if ( !NumberList.contains(str[2])) 
				{
					System.out.println("Unknown roman symbol " + str[2]);
				}
				else {
					numberMap.put(str[0], str[2]);	
				}
				break;
			default:
			{
				if ( str[ len - 1 ].equals( "?" ) ) // how much or how many...
				{
					if ( ! str[ 0 ].equals( "how" ) )
					{
						complainAbout( line );
						return;
					}
					else if ( str[ 1 ].equals( "much" ) )
					{
						if ( ! str[ 2 ].equals( "is" ) )
						{
							complainAbout( line );
							return;
						}
						else {
							String extraterrestrialNumber = "";
							String roman = "";
							Set numberSet = numberMap.keySet();
							for ( int i = 3; i < len - 1; i++ )
							{
								String word = str[ i ];
								if ( numberSet.contains( word ) )
								{
									extraterrestrialNumber += word + " ";
									roman += numberMap.get( word );
								}
							}
							System.out.println( extraterrestrialNumber + "is " + this.roman( roman ) );
						}
					}
					else if ( str[ 1 ].equals( "many" ) )
					{
						if ( ! str[ 2 ].equals( "Credits" ) )
						{
							complainAbout( line );
							return;
						}
						else if ( ! str[ 3 ].equals( "is" ) ) {
							complainAbout( line );
							return;
						}
						else {
							String extraterrestrialNumber = "";
							String roman = "";
							Set numberSet = numberMap.keySet();
							for ( int i = 3; i < len - 2; i++ )
							{
								String word = str[ i ];
								if ( numberSet.contains( word ) )
								{
									extraterrestrialNumber += word + " ";
									roman += numberMap.get( word );
								}
							}
							String mineral = str[ len - 2 ];
							Fraction fraction = mineralMap.get( mineral );
							if ( fraction == null )
							{
								System.out.println("Mineral "+ mineral +" not priced");
								return;
							}
							System.out.println( extraterrestrialNumber + mineral + " is " + this.roman( roman ) + " Credits" );
						}
					}
					else {
						complainAbout( line );
						return;
					}
				}
				else {
					Set numberSet = numberMap.keySet();
					if ( len < 5 )
					{
						complainAbout( line );
						return;
					}
					else if ( ! numberSet.contains( str[0] ) )
					{
						complainAbout( line );
						return;
					}
					String mineral = null;
					boolean isPresent = false;
					boolean isNumberPresent = false;
					int number = 0, denominator = 0;
					String roman = "";
					for ( String word : str )
					{
						if ( numberSet.contains( word ) )
						{
							roman += numberMap.get( word );
						}
						else {
							if ( mineral == null )
							{
								mineral = word;
								denominator = this.roman( roman );
							}
							else if ( ! isPresent )
							{
								if ( ! word.equals( "is" ) )
								{
									complainAbout( line );
									return;
								}
								else {
									isPresent = true;
								}
							}
							else if ( ! isNumberPresent )
							{
								try {
									number = Integer.valueOf( word );
									isNumberPresent = true;
								}
								catch ( NumberFormatException nfe ) // isNumberPresent == false
								{
									complainAbout( line );
									return;
								} 
							}
							else {  // isNumberPresent == true
								if ( ! word.equals( "Credits" ) )
								{
									complainAbout( line );
									return;
								}
								else {
									mineralMap.put( mineral, new Fraction( number, denominator ) );
								}
							}
						}
					}
				}
			}
		}
	}
    public static void main( String [] args )
		throws IOException
    {		
		Roman r = new Roman();
		System.out.println("Extraterrestial Intercommunication Module");
		System.out.println("Enter your messages:");
		BufferedReader in = new BufferedReader( new InputStreamReader( System.in ) );
		for ( ;; )
		{
			String s = in.readLine();
			if ( s == null || s.length() == 0 )
			{
				break;
			}
			r.interpret( s );
		}
    }
}
