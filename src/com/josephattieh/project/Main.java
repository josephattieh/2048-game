package com.josephattieh.project;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		java.util.Scanner scan = new Scanner(System.in);
		String s1 = scan.nextLine();
		scan.nextLine();
		int n = scan.nextInt();
		
		String v ="eiou";
		String s2=s1+"";
		for(int i=0; i< s1.length();i++) {
			if(v.indexOf(s1.charAt(i))!=-1)
				for(int x=1; x< n; x++)
					s2= s2.substring(0, i+x)+s1.charAt(i)+s2.substring(i+x);
		}
		System.out.println(s2);
	}

}
