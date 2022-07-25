package javaBasic;

public class Inheritancebasicchild extends InheritancebasicParent{
	
	int a = 20;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inheritancebasicchild it = new Inheritancebasicchild();
		System.out.println(it.a);
		
		InheritancebasicParent ip = new InheritancebasicParent();
		System.out.println(ip.a);
		
		//child to parent class variable
		InheritancebasicParent ip1 = new Inheritancebasicchild();
		System.out.println(ip1.a);
		
		Inheritancebasicchild it1 = (Inheritancebasicchild) new InheritancebasicParent();
		System.out.println(it1.a);
	}

}
