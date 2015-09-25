package hw2_2;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.w3c.dom.css.Rect;

class UIPanel extends JPanel implements MouseMotionListener,MouseListener{
	private static final int PanelSizeToMarginRaito=8;
	private int panelWidth;
	private int panelHeight;
	private int pxPerUnit;
	private int sectionMainWidth;
	private int sectionMainHeight;
	private int sectionMainLeftMargin;
	private int sectionMainTopMargin;
	private boolean shouldPause=false;
	private int middleGap;
	private int sectionNextShapeX;
	private int sectionNextShapeY;
	private int sectionNextShapeWidth;
	private int sectionNextShapeHeight;
	private String textDisplay;
	private int level,lines,score;
	private int textDisplayX;
	private int textDisplayY;
	private String quitText="QUIT";
	private int quitRectWidth;
	private int quitRectHeight;
	private int quitRectX;
	private int quitRectY;
	private int pauseRectWidth;
	private int pauseRectHeight;
	private int pauseRectX;
	private int pauseRectY;
	private String pauseText = "PAUSE";
	private int pauseTextX;
	private int pauseTextY;
	Graphics2D g2d;

	/**
	 * 
	 */
	public UIPanel() {
		super();
		// TODO Auto-generated constructor stub
		panelHeight=this.getHeight();
		panelWidth = this.getWidth();
		if(panelHeight>panelWidth){
			pxPerUnit=panelWidth/12;
		}else{
			pxPerUnit=panelHeight/12;
		}
		sectionMainWidth = pxPerUnit*5;
		sectionMainHeight = pxPerUnit*10;

		sectionMainLeftMargin=this.getWidth()/PanelSizeToMarginRaito;
		sectionMainTopMargin=this.getHeight()/PanelSizeToMarginRaito;
		middleGap = pxPerUnit;
		sectionNextShapeX = sectionMainLeftMargin+sectionMainWidth+middleGap;
		sectionNextShapeY = sectionMainTopMargin;
		sectionNextShapeWidth = sectionMainWidth*2/3;
		sectionNextShapeHeight = sectionMainHeight/5;
		level=1;
		lines=0;
		score = 0;
		textDisplay = "Level:  "+level+"\n"+"Lines:  "+lines+"\n"+"Score:  "+score;
		textDisplayX = sectionNextShapeX;
		textDisplayY = sectionNextShapeY+sectionNextShapeHeight*2;
		quitRectWidth = sectionNextShapeWidth/2;
		quitRectHeight = 50;
		quitRectX = sectionNextShapeX;
		quitRectY = sectionMainTopMargin+sectionMainHeight-quitRectHeight;
		pauseRectWidth = sectionMainWidth/2;
		pauseRectHeight = sectionMainHeight/9;
		pauseRectX = sectionMainLeftMargin + sectionMainWidth/4;
		pauseRectY = sectionMainTopMargin + 4*pauseRectHeight;
		pauseTextX = pauseRectX;
		pauseTextY = pauseRectY;
		
	    this.addMouseMotionListener(this);
	    this.addMouseListener(this);


	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		panelHeight=this.getHeight();
		panelWidth = this.getWidth();
		sectionMainLeftMargin=this.getWidth()/PanelSizeToMarginRaito;
		sectionMainTopMargin=this.getHeight()/PanelSizeToMarginRaito;
		middleGap = pxPerUnit;
		if(panelHeight>panelWidth){
			pxPerUnit=panelWidth/12;
		}else{
			pxPerUnit=panelHeight/12;
		}
		g2d = (Graphics2D)g;
		drawSectionMainArea(g2d);
		drawSectionNextShape(g2d);
		drawSectionText(g2d);
		drawQuitButton(g2d);
		
		
	}
	
	//The method for drawing the main game play section on the left including the Pause 
	private void drawSectionMainArea(Graphics2D g2d){

		sectionMainWidth = pxPerUnit*5;
		sectionMainHeight = pxPerUnit*10;
		//System.out.println(this.getHeight()+","+this.getWidth());
		Rectangle sectionMainRect = new Rectangle(sectionMainLeftMargin, sectionMainTopMargin, sectionMainWidth, sectionMainHeight);
		g2d.draw(sectionMainRect);
		
		pauseRectWidth = sectionMainWidth/2;
		pauseRectHeight = sectionMainHeight/9;
		pauseRectX = sectionMainLeftMargin + sectionMainWidth/4;
		pauseRectY = sectionMainTopMargin + 4*pauseRectHeight;
		pauseTextX = pauseRectX;
		pauseTextY = pauseRectY;
		if(shouldPause == true){
			Rectangle pauseRect = new Rectangle(pauseRectX,pauseRectY,pauseRectWidth,pauseRectHeight);
			g2d.draw(pauseRect);
			g2d.setFont(new Font("TimesRoman", Font.PLAIN, sectionNextShapeHeight/7*2));
			g2d.drawString(pauseText, pauseTextX+pauseRectWidth/5, pauseTextY+pauseRectHeight/3*2);
		}
		repaint();
	}
	
	private void drawSectionNextShape(Graphics2D g2d){
		sectionNextShapeX = sectionMainLeftMargin+sectionMainWidth+middleGap;
		sectionNextShapeY = sectionMainTopMargin;
		sectionNextShapeWidth = sectionMainWidth*2/3;
		sectionNextShapeHeight = sectionMainHeight/5;
		Rectangle sectionNextShapeRect = new Rectangle(sectionNextShapeX, sectionNextShapeY, sectionNextShapeWidth, sectionNextShapeHeight);
		g2d.draw(sectionNextShapeRect);
		int[] x = {sectionNextShapeX+sectionNextShapeWidth/10,sectionNextShapeX+sectionNextShapeWidth/2,sectionNextShapeX+sectionNextShapeWidth-sectionNextShapeWidth/10};
		int[] y = {sectionNextShapeY+sectionNextShapeHeight-sectionNextShapeHeight/10,sectionNextShapeY+sectionNextShapeHeight/10,sectionNextShapeY+sectionNextShapeHeight-sectionNextShapeHeight/10};
		Polygon poly = new Polygon(x,y,3);
		g2d.drawPolygon(poly);
		g2d.setColor(Color.YELLOW);
		g2d.fillPolygon(poly);
		g2d.setColor(Color.BLACK);
		repaint();

	}
	
	private void drawQuitButton(Graphics2D g2d){
		quitRectWidth = sectionNextShapeWidth/2;
		quitRectHeight = sectionNextShapeHeight/2;
		quitRectX = sectionNextShapeX;
		quitRectY = sectionMainTopMargin+sectionMainHeight-quitRectHeight;
		Rectangle rectQuit = new Rectangle(quitRectX, quitRectY, quitRectWidth, quitRectHeight);
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, quitRectHeight/5*2));
		g2d.drawString(quitText,quitRectX+quitRectWidth/4,quitRectY+quitRectHeight/3*2);
		g2d.draw(rectQuit);
		repaint();

	}

	private void drawSectionText(Graphics2D g2d){
		textDisplay = "Level:  "+level+"\n"+"Lines:  "+lines+"\n"+"Score:  "+score;
		textDisplayX = sectionNextShapeX;
		textDisplayY = sectionNextShapeY+sectionNextShapeHeight*2;
		System.out.println(sectionNextShapeWidth);

		g2d.setFont(new Font("TimesRoman", Font.PLAIN, sectionNextShapeWidth/7));
		int currentY=textDisplayY;
		for (String line : textDisplay.split("\n")){
			g2d.drawString(line, textDisplayX, currentY);
			currentY += g2d.getFontMetrics().getHeight()*2;
		}
		repaint();
    
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
        int my = e.getY();
        if(mx>sectionMainLeftMargin&&mx<(sectionMainLeftMargin+sectionMainWidth)&&my>sectionMainTopMargin&&my<sectionMainTopMargin+sectionMainHeight) {
                shouldPause=true;
                //System.out.println("yes");
/*                if(e.getButton() == MouseEvent.BUTTON1){
                    System.out.println("Pressed");
                }*/
                repaint();
        } else {
                shouldPause=false;
               // System.out.println("no");
                repaint();

        }
        
        //System.exit(0);
        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

	}



	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		if(x>quitRectX&&x<quitRectX+quitRectWidth&&y>quitRectY&&y<quitRectY+quitRectHeight){
			System.out.print("Clicked");
	        System.exit(0);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}





	

}
