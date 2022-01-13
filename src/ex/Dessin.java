package ex;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Dessin extends Canvas implements Dess{
	
	public Dessin() {
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(50, 50, 300, 300);
	}

}
