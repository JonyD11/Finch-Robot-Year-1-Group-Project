package DancingFinch;

public class convertor
{
	public static int HexaToDec(String s)
	{
		int dec = 0;
		try
		{
			s.toUpperCase();
			dec = Integer.parseInt(s, 16);
		}
		catch(NumberFormatException ne)
		{
			System.out.println("Invalid Input");
		}
		return dec;
	}
	
	public static String HexaToBin(String s)
	{
		int dec = 0;
		String bin = "0";
		try
		{
			s.toUpperCase();
			dec = Integer.parseInt(s, 16);
			bin = Integer.toBinaryString(dec);
			
		}
		catch(NumberFormatException ne)
		{
			System.out.println("Invalid Input");
		}
		return bin;
	}
}

