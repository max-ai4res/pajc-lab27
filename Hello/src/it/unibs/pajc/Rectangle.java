package it.unibs.pajc;

public class Rectangle implements Shape2D {
	int width;
	int height;
	public Rectangle(int w, int h) {
		this.width=w;
		this.height=h;
	}
	
	public int perimeter() {
		return (width+height)*2;
	}
	
	public int area() {
		return (width*height);
	}
}
