package javaBasic;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Stringbasic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//check $ value is present in String
		
		String str = "Palindrom 12$ is a value";
		String str1 =  new String("Hello 123$ is welcome ticket");
		
		System.out.println(str.charAt(12));
		System.out.println(str1.indexOf('t'));

	}

}
