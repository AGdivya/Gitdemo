package javaBasic;

public class AustralianTrafficInterfacebasic implements CentralTrafficRules {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//here we created object of AustralianTrafficInterfacebasic class
		AustralianTrafficInterfacebasic at = new AustralianTrafficInterfacebasic();
	    
	    // either way of calling methods 
		CentralTrafficRules ct = new AustralianTrafficInterfacebasic();
		
		
	}

	@Override
	public void redStop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void greenGo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void yellowWaiting() {
		// TODO Auto-generated method stub
		
	}
	
	public void walk()
	{
		
	}
	
	//method overloading
	public void add (int a, int b ) // add(int, int) - signature of method 
	{
		
	}
	
	public void add (float a , int b)
	{
		
	}
	// whenever method signature change it is method overloading.
	//Method signature change either by changing parameter type or parameter sequence 

}


