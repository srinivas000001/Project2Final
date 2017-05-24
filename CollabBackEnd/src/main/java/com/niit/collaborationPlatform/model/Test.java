package com.niit.collaborationPlatform.model;

public class Test{
	int x=10;
	static int y=10;
	static int z=10;
	static int x1;
	static int y2;
	static int z2;
public static void main(String[] args) {
	x1=++y;
	y2=z++;
	z2=z;
	
System.out.println(x1);
System.out.println(y2);
System.out.println(z2);
}
	
	
}