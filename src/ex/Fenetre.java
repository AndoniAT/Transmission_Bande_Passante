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
	public JPanel jpTexte;
	public JPanel jpDessin;
	public DessinNRZ d;
	public JPanel dessinPanel;
	
	public Fenetre() {
		
		setBounds(100,100,300,500);
		setTitle("Titre");
		
		// Panel de bouttons
		JPanel jpBouttons = new JPanel();
		
		// Layout pour les bouttons
		FlowLayout flButton = new FlowLayout();
		
		// On étabi le layout dans le panel
		jpBouttons.setLayout(flButton);
		
		// Creation de bouttons
		jb1 = new JButton("Manchester");
		jb1.addActionListener(this);
		
		jb2 = new JButton("Manchester Differenciel");
		jb2.addActionListener(this);
		
		jb3 = new JButton("NRZ");
		jb3.addActionListener(this);
		
		jb4 = new JButton("Miller");
		jb4.addActionListener(this);
		
		// Ajout des bouttons dans le panel
		jpBouttons.add(jb1);
		jpBouttons.add(jb2);
		jpBouttons.add(jb3);
		jpBouttons.add(jb4);
		
		
		// Panel du texte
		jpTexte = new JPanel();
		JLabel texte = new JLabel("Veuilliez de saissir votre nombre binaire : ");
		
		// Champ pour écrire le nombre binaire
		edit = new JTextField(15);
		
		// Ajout du texte et du champ dans le panel
		jpTexte.add(texte);
		jpTexte.add(edit);
		
		// Ajout de paneaux
		add(jpBouttons, BorderLayout.NORTH);
		add(jpTexte, BorderLayout.CENTER);
		
		// Panel pour le dessin
		dessinPanel = new JPanel();
		
		// Creation du dessin
		d = new DessinNRZ();
		
		// Ajout du dessin eu Panel
		dessinPanel.add(d);
		
		// Ajout du panel au JFrame
        add(dessinPanel, BorderLayout.SOUTH);
        
        // Sortir du programme
        setDefaultCloseOperation(EXIT_ON_CLOSE);

		//setSize(500, 500);
		pack();
		setVisible(true);
	}
	
	 public void actionPerformed(ActionEvent e){
		 	// quand on a cliqué sur le bouton ici
	        JButton b = (JButton)e.getSource();
	        String s = e.getActionCommand();
	        
	        d.setChoix(s);
	        d.setStr(edit.getText());
			d.setE(e);
	        //d.repaint();
	        //jpTexte.repaint();
	        add(jpTexte);
			add(d);
	        
	        int w = 100;
	        
	        for (int n=0; n <= edit.getText().length(); n++) {
	        	w = w + 50;
	        }
	        w = w + 50;
	        
	        if(w < 500) {
	        	w = 500;
	        }
	        
	        setSize(w, 500);


	    }
}
