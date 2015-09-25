package hw2_2;



import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris {
	private static final String TAG = "Tetris";
	private JFrame frame;
	private JPanel panel;
	/**
	 * 
	 */
	 Tetris() {
		super();
		setUpJFrame();
		System.out.println(TAG);

		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tetris tetris = new Tetris();
	}
	private void setUpJFrame(){
		frame = new JFrame("Tetris Game");
		panel = new UIPanel();
		frame.setExtendedState( frame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(600,600);
    	//Add the panel component to JFrame
    	frame.add(panel);
    	frame.setVisible(true);
 
	}
}


