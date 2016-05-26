 import java.lang.*;
		
		/**
                 * De klasse EersteProg is een java applicatie
                 *
                 * @author Lise Vermariën
                 * @version 1.5
                 */
	public class EersteProg{
		
		/**
		 * Dit is de main functie, hier start het programma
		 * @param args Dit is een parameter die kan meegegeven worden via de commandline
		 */

		public static void main(String args[])
		{
		double pi=0.0;
		int noemer=1;
		for (int t=0;t<5000;t++)//5000 keer
			{
			pi=pi+(1.0/noemer); //let op voor integerdeling
			noemer=noemer+2;
			pi=pi-(1/(double)noemer); //andere manier im integerdeling te vermijden
			noemer=noemer+2;
			}
		pi=pi*4;
		System.out.println("PI=" +pi);
		
		}	
	}


