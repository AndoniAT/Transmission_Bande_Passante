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

		public int haut;
		public int bas;
		public int izq;
		
		public int x_av;
		public int x_ap;
		public int y_av;
		public int y_ap;
		 
		public int saut;
		public int double_saut;
		
		/**
		 * Quand on cree notre Dessin on reinitialise tous les valeurs
		 */
		public DessinNRZ() {
			reinitialiser();
		}
		
		/**
		 * Valeurs par defaut des valeurs
		 */
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
		
		/**
		 * Retourn le boutons que l'on a choisit
		 * @return
		 */
		public String getChoix() {
			return choix;
		}

		/**
		 * Pour etablir notre choix de boutton
		 * @param s
		 */
		public void setChoix(String s) {
			this.choix = s;
		}

		/**
		 * Obtenir le nombre fourni par l'utilisateur
		 * @return
		 */
		public String getStr() {
			return str;
		}

		/**
		 * Etablir le nombre à traiter
		 * @param str
		 */
		public void setStr(String str) {
			this.str = str;
		}
		
		/**
		 * Surcharge de la methode paint
		 */
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			creationBase(g); // Creer notre tableau vide
			
			// Si il n'y a rien écrit on ne fait rien
			if(str.length() == 0 ) {
				return;
			}
			
			
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

			// Reinitialiser valeurs à la fin
			reinitialiser();
		}
		
		/**
		 * Methode NRZ : 
		 * codage du bit 1 par un signal de n volts
		 * codage du bit 0 par un signal de -n volts.
		 * @param g
		 */
		public void nrz(Graphics g) {
			// Si le nombre commence par 0 on commence en bas
			if(str.charAt(0) == '0') {
				y_av = bas;
				y_ap = bas;
			}
			
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt(n);
			 	
			 	ligneRect(g); 
			 	
			 	// Si il y a un changement dans le nombre suivant on fait une ligne verticale
			 	if(n+1 < str.length()) {
			 		if(change(n, c)) {
			 			faireLigneVerticale(g);
			 		}
			 	}		 		
			 }
			return;
		}
		
		/**
		 * Methode Manchester:
		 * Il s’agit d’un code basé sur les variations du signal : ce n’est plus
		 * la tension qui est importante mais la différence de signal.
		 * 1 est codé par un passage de la tension n à -n et 0 par le
		 * passage en sens inverse.
		 * @param g
		 */
		public void manchester(Graphics g) {
			// Si le nnombre commence par 0 on commence par le bas
			if(str.charAt(0) == '0') { 
				y_av = bas;
				y_ap = bas;
			}
			
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt (n);
			 	
			 	faireChemin(g);
			 	
			 	// S'il y aura un changement on fait une ligne verticale
			 	if(n+1 < str.length()) {
				 	if(!change(n, c)) {
				 		faireLigneVerticale(g);
				 	}
			 	}
			}
		}
		
		/**
		 * Methode Manchester Differentiel : 
		 * Il est similaire au Manchester mais le bit 0 est codé par une
		 * transition en début d’horloge contrairement au bit 1.
		 * @param g
		 */
		public void manchesterDiff(Graphics g) {
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt (n);
			 	
			 	// Pour chaque 0 on fait une ligne verticale
			 	if(c == '0') {
			 		faireLigneVerticale(g);
			 	}
			 	
			 	faireChemin(g);
			}
		}
		
		/**
		 * Methode Miller :
		 * Le bit 1 est codé par une transition en milieu de temps horloge et
		 *	le bit 0 par une absence de transition.
		 *	Les longues suites de 0 posant toujours le problème de la
		 *	synchronisation, si un bit 0 est suivi d’un autre 0 une transition
		 *	est rajoutée à la fin du temps horloge.
		 * @param g
		 */
		public void miller(Graphics g) {
			for (int n=0; n < str.length(); n++) {
			 	char c = str.charAt (n);
			 	
			 	switch(c) {
			 		case '1' : {
			 					 faireChemin(g); // Chemin normal pour les 1
			 					 break;
			 					}
			 		case '0' : {
			 					 
			 					ligneRect(g); // Ligne recte pour chaque 0	
			 					
			 					if(n+1 < str.length()) {
			 						if(!change(n, c)) {
			 							// 	S'il y aura un autr 0, on ajoute une ligne vertical
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
			int largeur = str.length();
			
			if(largeur == 0 ) {
				largeur = 5;
			}
			
			Graphics2D g2d = (Graphics2D)g;
			
			// Obtenir le le maximum
			int max = 100;
			
			for(int i = 1; i <= largeur; i++) {
				max = max + 50;
			}
			
			// On fait le bord
			g.setColor(Color.BLUE);
			g.drawRect(izq - 10, haut - 40, largeur * 50 + 20, 280);
			
			// ======  Section cadre gris =====
			g.setColor(Color.GRAY);
			for(int i = izq, j = 0 ; j <= largeur ; i = i + 50, j++) {
				g.drawLine(i, haut - 20, i, bas + 20);
			}
			
			
			// Inisialitation variables
			int difference = 20;
			int hautCadreGris = haut - difference;
			int basCadreGris = bas + difference;
			int milieu = bas - haut; 
			
			// Dessiner le cadre
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
			
			// Grosseur de la ligne
			BasicStroke bsLine = new BasicStroke(6); 
			g2d.setStroke(bsLine);
			
			// Ecriture des nombres dans le tableau
			g.setColor(Color.BLACK);
			for (int i = 0, j = izq + saut; i < str.length(); i++, j = j + double_saut) {
				char c = str.charAt(i);
				g.drawString(c + "", j, haut - 5);
			}
			// =================
			
			g2d.setColor(Color.RED);
		}
		
		/**
		 * Methode pour savoir s'il y aura un changement de nombre
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
