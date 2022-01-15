package ex;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Fenetre extends JFrame implements ActionListener{
	// Bouttons
	public JButton jb1 , jb2 ,jb3, jb4, jb5;
	
	// Panels
	public JPanel jpTexte, jpBT;
	
	// Layouts
	public BoxLayout boxL, boxBT;
	
	//Autres
	public JTextField edit;
	public Dessin d;
	public String txt;
	public JLabel texte;
	
	public Fenetre() {
		super("Transmission Bande Passante");
		
		creation();
		
		// Sortir du programme
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);

		setVisible(true);
		init();
	}
	
	/**
	 * Action des bouttons
	 */
	 public void actionPerformed(ActionEvent e){
		 	String txt = edit.getText();
		 	String nv = "";
		 	
		 	// Copier le text sans des champs vides
		 	for (int i = 0; i < txt.length(); i++) {
				char c = txt.charAt(i);
		 		if(c != ' ') {
		 			nv = nv + c;
		 		}
			}
		 	
		 	System.out.println(nv);
		 	// Verifier que le texte soit un code binaire
		 	for (int i = 0; i < nv.length(); i++) {
				char c = nv.charAt(i);
				System.out.println(c);
				if(c != '0' && c != '1') {
					JOptionPane.showMessageDialog(this, "Vieuillez d'ecrire que du code binaire");
					return;
				}
			}
		 	
		 	

	        JButton b = (JButton)e.getSource();
	        String s = e.getActionCommand();
	        
	        d.setChoix(s);
	        d.setStr(nv);
	        add(d, BorderLayout.CENTER);
	        
			int w = 150;
	        for (int n=0; n <= edit.getText().length(); n++) w = w + 50;
	        
	        if(w < 500) w = 500;
	        
	        setSize(w, 500);
			setLocationRelativeTo(null);
	 }
	
	 /**
	  * Initialisation de la fenetre
	  */
	 public void init(){
	        d.setStr(edit.getText());
	        add(d, BorderLayout.CENTER);
			setSize(500, 500);
			setLocationRelativeTo(null);
	 }
	
	 /**
	  * Traitement pour la creation de la fenetre
	  */
	 public void creation () {
		 // Panel pour Bouttons et text 
		 jpBT = new JPanel();
		 boxBT = new BoxLayout(jpBT, BoxLayout.Y_AXIS);
		 jpBT.setLayout(boxBT);
		 
		 // === BOUTTONS ======= 
		 	
			// Panel de bouttons
			JPanel jpBouttons = new JPanel();
			
			// Layout pour les bouttons
			FlowLayout flButton = new FlowLayout();
			
			// On étabi le layout dans le panel
			jpBouttons.setLayout(flButton);
			
			// Creation de bouttons
			jb1 = new JButton("NRZ");
			jb2 = new JButton("NRZI");
			jb3 = new JButton("Manchester");
			jb4 = new JButton("Manchester Differenciel");
			jb5 = new JButton("Miller");
			
			// Ajout listener pour les bouttons
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			jb3.addActionListener(this);
			jb4.addActionListener(this);
			jb5.addActionListener(this);
			
			// Ajout des bouttons dans le panel
			jpBouttons.add(jb1);
			jpBouttons.add(jb2);
			jpBouttons.add(jb3);
			jpBouttons.add(jb4);
			jpBouttons.add(jb5);
			
			// Ajout bouttons dans le JPanel
			jpBT.add(jpBouttons);
			
		// ====== TEXTE ET TEXTFIELD ==========
			// Panel du texte
			jpTexte = new JPanel();
			
			// Layout
			boxL = new BoxLayout(jpTexte, BoxLayout.X_AXIS);
	        jpTexte.setLayout(boxL);
			
			// Creation elements
	        txt = "Veuilliez de saissir votre nombre binaire : ";
			texte = new JLabel(txt);	
			
			edit = new JTextField(15);
			
			
			edit.addKeyListener(new KeyListener() {				
				@Override
				public void keyTyped(KeyEvent e) {
					
				}
				
				@Override
				public void keyReleased(KeyEvent e) {
				}

				private void verification(KeyEvent e, int length) {
				}
				
				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
			
			// Ajout du texte et du champ dans le panel
			jpTexte.add(texte);
			jpTexte.add(edit);
			
			// Ajout dans le Panel avec les bouttons
			jpBT.add(jpTexte);
			
			add(jpBT, BorderLayout.NORTH);
			
		// ====== DESSIN ==========
			// Creation du dessin
			d = new Dessin();
	 }
	 
	 
	 
	 
}