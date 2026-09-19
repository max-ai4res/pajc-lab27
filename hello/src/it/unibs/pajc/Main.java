package it.unibs.pajc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void visualizzaConteggi(ArrayList<Shape2D> list) {
		HashMap<String, Integer> gruppi = new HashMap<>();

		for(Shape2D s: list) {
			String nomeTipo = s.getClass().getSimpleName();
			
			if(gruppi.containsKey(nomeTipo)) {
				int noggetti = gruppi.get(nomeTipo);
				gruppi.put(nomeTipo, noggetti+1);
			} else {
				gruppi.put(nomeTipo, 1);
			}
			
		}
		for(Map.Entry<String, Integer> kv: gruppi.entrySet()) {
			System.out.printf("%s: %d\n", kv.getKey(), kv.getValue());
		}
	}
	
	public static void main(String[] args) {		
		ArrayList<Shape2D> list = new ArrayList<Shape2D>();
		
		list.add(new Square(3));
		list.add(new Square(1));
		list.add(new Rectangle(6,7));
		list.add(new Circle(2));
		list.add(new Rectangle(9,7));
		list.add(new Rectangle(12,7));
		
		visualizzaConteggi(list);
		
		
		
		
		
		
		
	}

}
