package hw1_1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Surface extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private void doDrawing(Graphics g) {
    	Graphics2D g2d = (Graphics2D) g;

        double edge = 400;
    	double alpha = Math.toRadians(45);
    	double factor = 1/(2*Math.sin(alpha));

        g2d.translate(300, 300);

        for (int i = 0; i < 15; i++) {

            int intEdge = (int) Math.round(edge);

            g2d.drawRect(-intEdge / 2, -intEdge / 2, intEdge, intEdge);

            edge = edge * factor;

            g2d.rotate(alpha);
        }
        
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}

public class ConcentricSquare{


    public static void main(String[] args) {

    	JFrame frame = new JFrame("Concentric Squares");
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(600,600);
        Surface s = new Surface();
        frame.add(s);
    	frame.setVisible(true);

    }
}
