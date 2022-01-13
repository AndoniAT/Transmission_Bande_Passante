package ex;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Dessin extends Canvas {
	
	public Dessin() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		System.out.println("mira");
		g.setColor(Color.BLUE);
		g.drawRect(50, 50, 300, 300);
	}

}
