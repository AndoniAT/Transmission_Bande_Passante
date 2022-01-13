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

		public int haut = 100;
		public int bas = 300;
		public int izq = 100;
		
		public int x_av = izq;
		public int x_ap = izq;
		public int y_av = haut;
		public int y_ap = haut;
		 
		
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
			System.out.println("h");
			// Si il n'y a rien écrit on ne fait rien
			if(str.length() ==0 ) {
				return;
			}
			
			creationBase(g);	
			
			// Appel de methodes selon le boutton choisi
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
		
		/**
		 * Methode NRZ
		 * @param g
		 */
		public void nrz(Graphics g) {
			x_av = 100;
			x_ap = 150;
			
			if(str.charAt(0) == '1') {
				y_av = 100;
				y_ap = 100;
			} else {
				y_av = 300;
				y_ap = 300;
			}
			
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
			 	x_ap = x_ap + 50;	
			 }
		}
		
		public void manchester(Graphics g) {
			x_av = 100;
			x_ap = 125;
			
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt (n);
			 	
			 	if(c == '1') {
			 		condMan(g, 1);
				} else {
					condMan(g, 0);
				}
			 	
			 	if(n+1 < str.length()) {
				 	if(!change(n, c)) {
				 		g.drawLine(x_av, haut, x_av, bas);
				 	}
			 	}
			}
			
		}
		
		/**
		 * Methode Manchester Differentiel
		 * @param g
		 */
		public void manchesterDiff(Graphics g) {
			x_av = izq;
			x_ap = x_av;
			y_av = haut;
			y_ap = haut;
			
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt (n);
			 	
			 	if(c == '1') {
			 		faireChemin(g);
			 	} else {
			 		faireLigneVerticale(g);
			 		faireChemin(g);
			 	}
			}
			
			
		}
		
		/**
		 * Methode Miller
		 * @param g
		 */
		public void miller(Graphics g) {
			x_av = izq;
			x_ap = izq;
			
			y_av = haut;
			y_ap = haut;
			
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt (n);
			 	
			 	switch(c) {
			 		case '1' : {
			 					 faireChemin(g);
			 					 break;
			 					}
			 		case '0' : {
			 					 
			 					ligneRect(g);	
			 					
			 					if(n+1 < str.length()) {
			 						if(!change(n, c)) {
			 							faireLigneVerticale(g);
			 						}
			 					}
			 					
			 					break;
			 				   }
			 	}
			 	
			}
			
		}
		
		
		// ==================== D'autres méthodes =====================================
		
		/**
		 * Methode pour creer le tableau vide
		 * @param g
		 */
		public void creationBase(Graphics g) {
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
			
			if(str.length() == 0) {
				return;
			}
			
			g.setColor(Color.BLACK);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			
			g.drawString("nV", 50, 100);
			g.drawString("0V", 50, 200);
			g.drawString("-nV", 50, 300);
			
			BasicStroke bsLine = new BasicStroke(6);
			g2d.setStroke(bsLine);
			g2d.setColor(Color.RED);
			
		}
		
		/**
		 * Dessiner les lignes pour Manchester
		 * @param g
		 * @param valeur
		 */
		public void condMan(Graphics g, int valeur) {
			// Si la valeur est 1 cela vaut dire que nous allons partir en haut
			if(valeur == 1) {
				y_av = haut;
				y_ap = haut;
			} else {
				// Sinon on part en bas
				y_av = bas;
				y_ap = bas;
			}
			
			
			g.drawLine(x_av, y_av, x_ap, y_ap);
			
			x_av = x_ap;
			
			if(valeur == 1) {
				y_ap = bas;
			} else {
				y_ap = haut;
			}
			
			g.drawLine(x_av, y_av, x_ap, y_ap);
			
			x_ap = x_ap + 25;
			y_av = y_ap;
			g.drawLine(x_av, y_av, x_ap, y_ap);
			
			x_av = x_ap;
			x_ap = x_ap + 25;
		}
		
		
		/**
		 * Methode pour savoir s'il y aura un changement
		 * @param n
		 * @param c
		 * @return
		 */
		private boolean change(int n, char c) {
			boolean change;
			if(str.charAt(n+1) != c) {
				change = true;
			} else {
				change = false;
			}
			return change;
		}
		
		/**
		 * Incrementation de la variable puor avancer
		 */
		public void xApresAvancer() {
			x_ap = x_ap + 25;
		}
		
		/**
		 * Incrementation de x_av
		 */
		public void xAvantAvancer() {
			x_av = x_ap;
		}
		
		/**
		 * Methode pour dessiner le chemin du nombre correspondant
		 * @param g
		 */
		public void faireChemin(Graphics g) {
			xApresAvancer();
			draw(g);
			faireLigneVerticale(g);
			xApresAvancer();
			draw(g);
			xAvantAvancer();
			
		}
		
		/**
		 * Faire une ligne Verticale
		 * @param g
		 */
		public void faireLigneVerticale(Graphics g) {
			xAvantAvancer();
			if(y_ap == haut ) {
				y_ap = bas;
				draw(g);
				y_av = bas;
			} else {
				y_ap = haut;
				draw(g);
				y_av = haut;
			}
		}
		
		/**
		 * Pour dessiner une ligne rect
		 * @param g
		 */
		public void ligneRect(Graphics g) {
			System.out.println("x : " + x_ap);
			xApresAvancer();
			xApresAvancer();
			System.out.println("x : " + x_ap);
			draw(g);
			xAvantAvancer();
			
		}
		
		/**
		 * Methode pour dessiner dans la position actuelle
		 * @param g
		 */
		public void draw(Graphics g) {
			g.drawLine(x_av, y_av, x_ap, y_ap);
		}
}
