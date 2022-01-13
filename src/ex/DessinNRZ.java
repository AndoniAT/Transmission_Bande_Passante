package ex;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.ActionEvent;

public class DessinNRZ extends Canvas implements Dess{		
		public String str;
		public String choix;
		public ActionEvent e;
		
		public DessinNRZ() {
		}
		
		
		
		public String getChoix() {
			return choix;
		}



		public void setChoix(String s) {
			this.choix = s;
		}

		


		public ActionEvent getE() {
			return e;
		}



		public void setE(ActionEvent e) {
			this.e = e;
		}



		public String getStr() {
			return str;
		}

		public void setStr(String str) {
			this.str = str;
		}
		

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			System.out.println("chossss" );
			
			
			if(str.equals(null))
				return;
			
			
			switch(choix) {
	        case "Manchester Differenciel" : {
	        				manchesterDiff(g);
	        				break;
	        		}
	        case "Manchester" : {
		        			manchester(g);
		        			break;
	        		}
	        
	        case "NRZ" : {
	        				nrz(g);
	        				break;
	        		}
	        
	        case "Miller" : {
	        			miller(g);
	        			break;
	        		}
	        }
		}
		
		
		public void nrz(Graphics g) {
			Graphics2D g2d = (Graphics2D)g;
			// Obtenir le le maximum
			int max = 100;
			
			for(int i = 1; i <= str.length(); i++) {
				max = max + 50;
			}
			
			// On fait le bord
			g.setColor(Color.BLUE);
			g.drawRect(90, 60, str.length() * 50 + 20, 280);
			
			// Section cadre gris
			g.setColor(Color.GRAY);
			for(int i = 100, j = 0 ; j <= str.length() ; i = i + 50, j++) {
				g.drawLine(i, 80, i, 320);
			}
			
			
			g.drawLine(100, 80, max, 80);
			g.drawLine(100, 320, max, 320);
			
			g.drawLine(100, 100, max, 100);
			g.drawLine(100, 200, max, 200);
			g.drawLine(100, 300, max, 300);
			
			int x_ap;
			int y_av;
			int y_ap;
			int x_av;
			
			if(str.length() == 0) {
				return;
			}
			
			
			if(str.charAt(0) == '1') {
				x_av = 100;
				x_ap = 150;
				y_av = 100;
				y_ap = 100;
				
			} else {
				x_av = 100;	
				y_av = 300;
				x_ap = 150;
				y_ap = 300;
				
			}
			
			g.setColor(Color.BLACK);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			
			g.drawString("nV", 50, 100);
			g.drawString("0V", 50, 200);
			g.drawString("-nV", 50, 300);
			
			
			boolean change = false;
			
			BasicStroke bsLine = new BasicStroke(6);
			g2d.setStroke(bsLine);
			g2d.setColor(Color.RED);
			
			
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt (n);
			 	
			 	g.drawLine(x_av, y_av, x_ap, y_ap);
			 		
			 	x_av = x_ap;
			 	
			 	if(n+1 < str.length()) {
			 		if(change(n, c)) {
			 			
			 			if(str.charAt(n+1) == '0') {
			 				y_ap = y_ap + 200;
			 			} else {
			 				y_ap = y_ap - 200;
			 			}
			 			
			 			g.drawLine(x_av, y_av, x_ap, y_ap);
			 			y_av = y_ap;
			 		}

			 	}
			 				 		
			 		 
			 	//x_av = x_av + 5;
			 	x_ap = x_ap + 50;	
			 		
			 	
			 }

		}
		
		private boolean change(int n, char c) {
			boolean change;
			if(str.charAt(n+1) != c) {
				change = true;
			} else {
				change = false;
			}
			return change;
		}
		
		
		public void miller(Graphics g) {}
		
		public void manchesterDiff(Graphics g) {}
		
		public void manchester(Graphics g) {}
}
