package ex;

import javax.swing.SwingUtilities;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("begin");
		
		SwingUtilities.invokeLater(
				() -> {
					new Fenetre();
				}
				);
	}

}
