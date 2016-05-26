public class oef2 
{
	public static void main (String args[])
	{
		int dag,weekdag=0;
		for (dag=1;dag<=28;dag++)
		{
		if(weekdag==0) System.out.print("zondag");
		if(weekdag==1) System.out.print("maandag");
		if(weekdag==2) System.out.print("dinsdag");
		if(weekdag==3) System.out.print("woensdag");
		if(weekdag==4) System.out.print("donderdag");
		if(weekdag==5) System.out.print("vrijdag");
		if(weekdag==6) System.out.print("zaterdag");

		weekdag++;
		if(weekdag>6) weekdag=0;
		System.out.println(dag+"februari 2009");
		}
	}
}
