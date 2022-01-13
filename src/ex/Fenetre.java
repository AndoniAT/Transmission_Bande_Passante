package ex;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre extends JFrame implements ActionListener{
	public JButton jb1;
	public JButton jb2;
	public JButton jb3;
	public JButton jb4;
	public JTextField edit;
	public MonPanel jpTexte;
	
	public JPanel jpDessin;
	public DessinNRZ d;
	public JPanel dessinPanel;
	public Dessin dd;
	public BoxLayout boxL;
	public String txt;
	
	public JPanel jp;
	public BoxLayout box;
	
	
	public BoxLayout boxBT;
	public JPanel jpBT;
	public JLabel texte;
	
	public Fenetre() {
		super("Transmission Bande Passante");
		creation();
		
		//setSize(500,500);
		
		// Sortir du programme
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);

		setVisible(true);
		init();
	}
	
	 public void actionPerformed(ActionEvent e){
		 // Quand on a cliqué sur le bouton ici
	        JButton b = (JButton)e.getSource();
	        String s = e.getActionCommand();
	        
	        
	        d.setChoix(s);
	        d.setStr(edit.getText());
	        add(d, BorderLayout.CENTER);
	        
			int w = 100;
	        
	        for (int n=0; n <= edit.getText().length(); n++) {
	        	w = w + 50;
	        }
	        
	        w = w + 50;
	        
	        if(w < 500) {
	        	w = 500;
	        }
	        
			setSize(w, 500);
			setLocationRelativeTo(null);
	 }
	
	 public void init(){
	        d.setStr(edit.getText());
	        add(d, BorderLayout.CENTER);
			setSize(500, 500);
			setLocationRelativeTo(null);
	 }
	
	 
	 public void creation () {
		// setSize(500,500);
		 jp = new MonPanel();
		 jpDessin = new MonPanel();
		 
		 box = new BoxLayout(jp, BoxLayout.Y_AXIS);
		 
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
			jb1 = new JButton("Manchester");
			jb2 = new JButton("Manchester Differenciel");
			jb3 = new JButton("NRZ");
			jb4 = new JButton("Miller");
			
			// Ajout listener pour les bouttons
			jb1.addActionListener(this);
			jb2.addActionListener(this);
			jb3.addActionListener(this);
			jb4.addActionListener(this);
			
			// Ajout des bouttons dans le panel
			jpBouttons.add(jb1);
			jpBouttons.add(jb2);
			jpBouttons.add(jb3);
			jpBouttons.add(jb4);
			
			// Ajout dand le JFrame
			//add(jpBouttons, BorderLayout.PAGE_START);
			
			// Ajout bouttons dans le JPanel
			jpBT.add(jpBouttons);
			
		// ====== TEXTE ET TEXTFIELD ==========
			// Panel du texte
			jpTexte = new MonPanel();
			
			// Layout
			boxL = new BoxLayout(jpTexte, BoxLayout.X_AXIS);
	        jpTexte.setLayout(boxL);
			
			// Creation elements
	        txt = "Veuilliez de saissir votre nombre binaire : ";
			texte = new JLabel(txt);	
			edit = new JTextField(15);
			
			// Ajout du texte et du champ dans le panel
			jpTexte.add(texte);
			jpTexte.add(edit);
			
			// Ajout dans le Panel avec les bouttons
			jpBT.add(jpTexte);
			
			add(jpBT, BorderLayout.NORTH);
			//add(jpDessin, BorderLayout.CENTER);
			
		// ====== DESSIN ==========
			// Creation du dessin
			d = new DessinNRZ();
			
			
	 }

	 private class MonPanel extends JPanel {
		    {
		        setOpaque(true);
		    }
		    
		    public void paintComponent(Graphics g) {
		        super.paintComponent(g);
		        setOpaque(true);
		    }
		    
		    public void changeSize(int w, int h){
		    	super.paintComponent(getGraphics());
		    	System.out.println("size : " + getSize());
		    	System.out.println("changeeee");
		    	setSize(w, h);
		    	System.out.println("size : " + getSize());
		    	repaint();
		    }
		}
}



