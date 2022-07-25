package javaBasic;

public class ReverseOfString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String s = "Madam";
		String reverse = "";
		for(int i=s.length()-1 ; i>=0; i--)
		{
			//System.out.println(s.charAt(i));
			reverse = reverse + s.charAt(i);
		
		}
		System.out.println(reverse);

		if(s.equalsIgnoreCase(reverse))
		{
			System.out.println("palindrom");
		}
		else
		{
			System.out.println("not palindrome");
		}
	}

}
