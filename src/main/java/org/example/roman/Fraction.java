package org.example.roman;

public class Fraction
{
	private int m_num, m_den;
	public static int gcd( int u, int v )
	{
            u = Math.abs( u );
            v = Math.abs( v );
            while ( v != 0 ) 
            {
                int r = u % v;
                u = v;
                v = r;
            }
            return u;
	}
	protected void simplify()
	{
            if ( m_num != 0 && m_den != 0 )
            {
                    int gcd = gcd( m_num, m_den );
                    if ( gcd != 0 )
                    {
                        m_num /= gcd;
                        m_den /= gcd;
                    }
            }
	}
	public Fraction( int num, int den )
	{
            m_num = num;
            m_den = den;
            simplify();
	}
	public int num() { return m_num; }
	public int den() { return m_den; }
	public boolean equals( Object other ) 
	{
		if ( other instanceof Fraction ) 
			return equals( (Fraction) other );
		else return false;
	}
	public boolean equals( Fraction other ) 
	{ return m_num == other.m_num && m_den == other.m_den; }
	public int hashCode() { return m_num + m_den; }
	public String toString()
	{
		// return "" + m_num + "/" + m_den;
		if ( m_num == 0 || m_den == 1 ) return "" + m_num;
		else {
			int num = m_num, den = m_den, decimals = 0;
			String result = "";
			for ( boolean first = true, point = false, 
					intDigit = false, nonZero = false; 
				  num != 0 && decimals < 60; first = false )
			{
				int x = num / den;
				if ( x == 0 ) 
				{
					if ( ! point )
					{
						point = true;
						if ( ! intDigit ) 
						{
								intDigit = true;
								result += "0";
						}
						result += ".";
						nonZero = false;
					}
					else {
						if ( ! nonZero ) result += "0";
						else nonZero = false;
						decimals++;
					}
					num *= 10;				
				}
				else {
					nonZero = true;
					result += first ? x : Math.abs( x );
					num -= x * den;
					if ( ! point ) intDigit = true;
				}
			}
			return result;
		}
	}
	public Fraction add( int i ) 
	{ return new Fraction( m_num + i * m_den, m_den ); }
	public Fraction add( Fraction f )
	{ 
		return new Fraction( f.num() * m_den + f.den() * m_num, 
                                    m_den * f.den() );
	}
	public Fraction subtract( int i ) 
	{ return new Fraction( m_num - i * m_den, m_den ); }
	public Fraction subtract( Fraction f )
	{ 
		return new Fraction( f.den() * m_num - f.num() * m_den, 
                                    m_den * f.den() );
	}
	public Fraction multiply( int i )
	{ return new Fraction( m_num * i, m_den ); }
	public Fraction multiply( Fraction f )
	{ return new Fraction( f.num() * m_num, m_den * f.den() ); }
	public Fraction divide( int i )
	{ return new Fraction( m_num, m_den * i ); }
	public Fraction divide( Fraction f )
	{ return new Fraction( f.den() * m_num, m_den * f.num() ); }
	public Fraction inverse() { return new Fraction( m_den, m_num ); }
	public int compare( Fraction f ) 
	{ return m_num * f.den() - f.num() * m_den; }
	public Fraction exp( int n )
	{
		if ( n < 0 ) return inverse().exp( -n );
		else if ( n == 0 ) return new Fraction( 1, 1 );
		else if ( n == 1 ) return this;
		else {
			Fraction ret = exp( n / 2 );
			ret = ret.multiply( ret );
			return ( n % 2 == 0 ) ? ret : ret.multiply( this );
		}
	}
}
	