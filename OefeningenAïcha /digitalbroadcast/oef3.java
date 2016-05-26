public class oef3
{
	public static void main (String args[])
	{
		double pi=0.0;
		int noemer=1;
		for (int t=0;t<5000;t++)
		{
		pi=pi+(1.0/noemer);
		noemer=noemer+2;
		pi=pi-(1/(double)noemer);
		noemer=noemer+2;
		}
	   pi=pi*4;
	   System.out.println("PI=" +pi);
		
	}
}
