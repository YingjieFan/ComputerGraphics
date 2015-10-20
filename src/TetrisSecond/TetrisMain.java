package TetrisSecond;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class TetrisMain extends JFrame {
  public TetrisMain(){
	  setTitle("assignment 1 Tetris");
	  TetrisWin a=new TetrisWin();
	  add(a);
	  setSize(580,650);
	  setLocation(500,100);
	   setResizable(false);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setVisible(true);	  
	  
  }
 /* private void sizeWindowOnScreen(TetrisMain calculator, double widthRate, double heightRate)
  {
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
     calculator.setSize(new Dimension((int)(screenSize.width * widthRate),(int)(screenSize.height *heightRate)));
  }
*/
	
	public static void main(String[] args){
	        new TetrisMain();
	  
  }
}
