public class oef7 {

	public static void main (String args [])
	{
		int a[]={12,34,56,78,123,234,99,88};
		int b[];
		b = new int[8];
		int grootste=0;
		int grootsteIndex=0;

		for (int j=0;j<8;j++)
		{
		for (int i=0;i<8;i++)
		{
			if(a[i] >grootste)
			{
				grootste = a[i];
				grootsteIndex=i;
			}
		}
		
		b[j] = a[grootsteIndex];
		System.out.println(b[j]);
		a[grootsteIndex]=0;
		grootste=0;
		}
	}
}
