package hw2_2;



import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tetris{
	private static final String TAG = "Tetris";
	private JFrame frame;
	private JPanel panel;
	private static int currentShape;
	//private static Timer timer;
	static NextShapeGenerator nextShapeGenerator;
	/**
	 * 
	 */
	 Tetris() {
		super();
		setUpJFrame();
		currentShape = -1;
		nextShapeGenerator = new NextShapeGenerator();
		currentShape = nextShapeGenerator.randomShape();
		//timer = new Timer();
		
		//System.out.println(TAG);

		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tetris tetris = new Tetris();
		/*TimerTask timerTask = new TimerTask(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		timer.schedule(timerTask, 0, 1000);*/
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


