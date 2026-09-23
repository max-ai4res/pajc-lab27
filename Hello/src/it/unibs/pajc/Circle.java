package it.unibs.pajc;

public class Circle implements Shape2D {

	int radius;
	public Circle(int radius) {
		this.radius = radius;
	}
	
	@Override
	public int perimeter() {
		return (int)(2*Math.PI*radius);
	}

	@Override
	public int area() {
		return (int)(Math.PI * radius*radius);
	}

}
