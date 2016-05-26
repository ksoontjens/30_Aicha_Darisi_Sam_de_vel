public class oef5 
{
	public static void main (String args[])
	{
		for (int getal=3;getal<100;getal++)
		{
		boolean ispriem=true;
		for (int deler=2;deler<getal;deler++)
		{
		if (getal % deler==0)
		{
		ispriem=false;
		}
		}
		if (ispriem==true) System.out.println(getal + "is een priemgetal");
		}

	}
}
