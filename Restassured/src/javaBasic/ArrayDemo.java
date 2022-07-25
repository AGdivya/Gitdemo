package javaBasic;

public class ArrayDemo {
	
	
	
	public static void main(String args[])
	{
		String a[] = new String[5]; //array creation or declares an array and allocate memory 
		
		
		int b[] = {1,2,3,4}; // array intiliaztion
		String reverse[] = new String[a.length];
		a[0] = "jan";
		a[1] = "feb";
		a[2] = "mar";
		a[3] = "apr";
		a[4] = "may";
		for(int i=0;i<a.length;i++)
		{
			System.out.println(a[i]);
		}
		int j=0;
		
		for(int i=a.length-1 ;i>=0 ;i--)
		{
			
			reverse[j] = a[i];
			j++;
			//System.out.println(a[i]); // i= 4 ==> may , i= 3 ==>apr ,i=2 ==> mar , i=1 ==>feb , i=0 ==>jan
		}
		
		for (int i=0 ;i<reverse.length ;i++)
		{
			System.out.println(reverse[i]);
		}
	}
	
	
}