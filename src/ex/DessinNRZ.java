package ex;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.event.ActionEvent;

public class DessinNRZ extends Canvas {		
		public String str;
		public String choix;
		public ActionEvent e;

		public int haut;
		public int bas;
		public int izq;
		
		public int x_av;
		public int x_ap;
		public int y_av;
		public int y_ap;
		 
		public int saut;
		public int double_saut;
		
		public DessinNRZ() {
			reinitialiser();
		}
		
		public void reinitialiser() {
			haut = 100;
			bas = 300;
			izq = 100;
			saut = 25;
			double_saut = saut +saut;
			
			x_av = izq;
			x_ap = izq;
			y_av = haut;
			y_ap = haut;
			
			
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
			System.out.println("holaaaa");
			
			// Si il n'y a rien écrit on ne fait rien
			if(str.length() == 0 ) {
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
		        
		        default: return;
	        
	        }
			reinitialiser();
		}
		
		/**
		 * Methode NRZ
		 * @param g
		 */
		public void nrz(Graphics g) {
			if(str.charAt(0) == '0') {
				y_av = bas;
				y_ap = bas;
			}
			
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt (n);
			 	
			 	ligneRect(g);
			 	
			 	if(n+1 < str.length()) {
			 		if(change(n, c)) {
			 			faireLigneVerticale(g);
			 		}
			 	}		 		
			 }
			return;
		}
		
		public void manchester(Graphics g) {
			if(str.charAt(0) == '0') {
				y_av = bas;
				y_ap = bas;
			}
			
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt (n);
			 	faireChemin(g);
			 	if(n+1 < str.length()) {
				 	if(!change(n, c)) {
				 		faireLigneVerticale(g);
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
			g.drawRect(izq - 10, haut - 40, str.length() * 50 + 20, 280);
			
			// Section cadre gris
			g.setColor(Color.GRAY);
			for(int i = izq, j = 0 ; j <= str.length() ; i = i + 50, j++) {
				g.drawLine(i, haut - 20, i, bas + 20);
			}
			
			int difference = 20;
			
			int hautCadreGris = haut - difference;
			int basCadreGris = bas + difference;
			int milieu = bas - haut; 
			g.drawLine(izq, hautCadreGris, max, hautCadreGris);
			g.drawLine(izq, basCadreGris, max, basCadreGris);
			
			g.drawLine(izq, haut, max, izq);
			g.drawLine(izq, milieu, max, milieu);
			g.drawLine(izq, bas, max, bas);
			
			
			g.setColor(Color.BLACK);
			
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20)); 
			
			g.drawString("nV", double_saut, izq);
			g.drawString("0V", double_saut, milieu);
			g.drawString("-nV", double_saut, bas);
			
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
			
			
			draw(g);
			
			x_av = x_ap;
			
			if(valeur == 1) {
				y_ap = bas;
			} else {
				y_ap = haut;
			}
			
			draw(g);
			
			x_ap = x_ap + 25;
			y_av = y_ap;
			draw(g);
			
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
		 * Incrementation de la variable pour avancer un saut
		 */
		public void xApresSaut() {
			x_ap = x_ap + saut;
		}
		
		/**
		 * Incrementation de la variable pour avancer deux saut
		 */
		public void xApresDoubleSaut() {
			x_ap = x_ap + double_saut;
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
			xApresSaut();
			draw(g);
			faireLigneVerticale(g);
			xApresSaut();
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
			xApresDoubleSaut();
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
