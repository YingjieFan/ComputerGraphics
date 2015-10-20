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
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.w3c.dom.css.Rect;

class UIPanel extends JPanel implements MouseMotionListener,MouseListener{
	private int[] shapeQueue;
	private static Timer timer;
	private final int mainAreaWidthNumOfUnits=5;
	private final int mainAreaHeightNumOfUnits=10;
	private static final int PanelSizeToMarginRaito=8;
	List<List<ElementGrid>> GridStatus;
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
	Polygon triangleRed;
	Polygon square;
	Polygon triangleYellow;
	Polygon diamondGreen;
	Polygon diamondOrange;
	Graphics2D g2d;
	private int currentShapeId=-1;
	private NextShapeGenerator randomShapeIdGenerator;
	private int nextShapeId=-1;
	private boolean shouldSpawn=true;
	//private int[][] currentShape;
	private String[] shapeLst = {"triangleRed","square","triangleYellow","diamondGreen","diamondOrange"};
	/**
	 * 
	 */
	public UIPanel() {
		super();
		// TODO Auto-generated constructor stub
		randomShapeIdGenerator = new NextShapeGenerator();
		shapeQueue = new int[2];
		shapeQueue[0]= randomShapeIdGenerator.randomShape();
		shapeQueue[1]= randomShapeIdGenerator.randomShape();
		GridStatus = new ArrayList<List<ElementGrid>>();
		for(int i =0;i<mainAreaHeightNumOfUnits;i++){
			GridStatus.add(new ArrayList<ElementGrid>());
			for(int j=0;j<mainAreaWidthNumOfUnits;j++){
				GridStatus.get(i).add(new ElementGrid());
			}
			
		}

		currentShapeId=shapeQueue[0];
		nextShapeId = shapeQueue[1];
		//nextShapeId = 4;
		timer = new Timer();
		
		/*currentShape = new int[][]{
			{-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1}		
		};*/
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
		
	    if(shouldSpawn==true){
			spawnNewShape();
			repaint();
		}
	    TimerTask timerTask = new TimerTask(){

			@Override
			public void run() {
				System.out.println("Enter run");
				// TODO Auto-generated method stub
				//spawnNewShape();
				
				updateFrame();
				if(shouldSpawn==true){
					spawnNewShape();
				}
				
				/*for(int i =0;i<mainAreaHeightNumOfUnits;i++){
					for(int j=0;j<mainAreaWidthNumOfUnits;j++){
						ElementGrid tmpGrid = GridStatus.get(i).get(j);
						if(tmpGrid.getPattern()==-1){
							continue;
						}
						if(tmpGrid.isMidAir()){
							
							GridStatus.get(i).get(j).setUpdated(false);;
						}
					}		
				}*/
			}
			
		};
		
		timer.schedule(timerTask, 1000, 1000);
	}
	private void refreshShapeQueue(){
		shapeQueue[0]=shapeQueue[1];
		shapeQueue[1]=randomShapeIdGenerator.randomShape();
		currentShapeId = shapeQueue[0];
		nextShapeId=shapeQueue[1];
	}
	private void freezeGrid(){
		for(int i =mainAreaHeightNumOfUnits-1;i>=0;i--){
			for(int j=0;j<mainAreaWidthNumOfUnits;j++){
				//GridStatus.get(i).get(j).setShouldUpdate(false);
				GridStatus.get(i).get(j).setFreezed(true);
			}}
	}
	private boolean checkLineCollision(int currentLine){
		boolean collide=false;
		int nextLine = currentLine+1;
		int currentPattern;
		int nextLinePattern;
		if(currentLine==mainAreaHeightNumOfUnits-1){
			System.out.println("Start checking from bottomLine");
			return false;
		}
	
		for(int i=0; i<mainAreaWidthNumOfUnits; i++){
			nextLinePattern = GridStatus.get(nextLine).get(i).getPattern();
			currentPattern = GridStatus.get(currentLine).get(i).getPattern();
			if(nextLinePattern==0||nextLinePattern==1||nextLinePattern==4){
				System.out.println("Pattern is one of 124");
				if(currentPattern!=-1&&(!GridStatus.get(nextLine).get(i).isFreezed()||!GridStatus.get(currentLine).get(i).isFreezed())){
					GridStatus.get(currentLine).get(i).setFreezed(true);
					GridStatus.get(currentLine).get(i).setShouldUpdate(false);
					System.out.println("currentLine not -1");
					collide = true;
					//return true;
				}
				
			}
			if(nextLinePattern==2){
				if(currentPattern!=1&&currentPattern!=-1&&(!GridStatus.get(nextLine).get(i).isFreezed()||!GridStatus.get(currentLine).get(i).isFreezed())){
					GridStatus.get(currentLine).get(i).setFreezed(true);
					GridStatus.get(currentLine).get(i).setShouldUpdate(false);
					collide = true;
				}
			}
			if(nextLinePattern==3){
				if(currentPattern!=0&&currentPattern!=-1&&(!GridStatus.get(nextLine).get(i).isFreezed()||!GridStatus.get(currentLine).get(i).isFreezed())){
					GridStatus.get(currentLine).get(i).setFreezed(true);
					GridStatus.get(currentLine).get(i).setShouldUpdate(false);
					collide = true;
				}
			}
		}
		return collide;
	}
	private void updateFrame(){
		//repaint();
		System.out.println("UpdateFrame");

		
		for(int i =mainAreaHeightNumOfUnits-1;i>=0;i--){
			
			if(checkLineCollision(i)){
				System.out.println("LineCollisionDetected between"+i+" "+(i+1));
				freezeGrid();
				refreshShapeQueue();
				shouldSpawn=true;
				return;
			}
			for(int j=0;j<mainAreaWidthNumOfUnits;j++){
				ElementGrid bottomNeighbor;
				System.out.println("Processing element"+i+" "+j);
				ElementGrid tmpGrid = GridStatus.get(i).get(j);
				if(i<mainAreaHeightNumOfUnits-1){
					bottomNeighbor = GridStatus.get(i+1).get(j);
				}else{
					bottomNeighbor = null;
				}
				
				if(tmpGrid.getPattern()==-1){
					System.out.println("Pattern"+tmpGrid.getPattern());
					//tmpGrid.setUpdated(true);
					continue;
					
				}else
				if(!tmpGrid.shouldUpdate()){
					System.out.println("Current Grid already updated");
					continue;
				}
				if(tmpGrid.shouldUpdate()){
					System.out.println("shouldUpdate and updating");
					if(bottomNeighbor==null){
						//tmpGrid.setShouldUpdate(false);
						continue;
					}
					/*if(bottomNeighbor.getPattern()==0||bottomNeighbor.getPattern()==1){
						freezeGrid();
						shouldSpawn=true;
						refreshShapeQueue();
						return;
						
					}*/
					System.out.println("Current Grid need update");
					System.out.println("Current pattern "+tmpGrid.getPattern());
					//tmpGrid.setUpdated(true);
					
					bottomNeighbor.setPattern(tmpGrid.getPattern());
					bottomNeighbor.setColor(tmpGrid.getColor());
					bottomNeighbor.setShouldUpdate(true);
					//GridStatus.get(i+1).set(j, tmpGrid);
					System.out.println("copy tmpGrid to gird "+(i+1)+" "+j);
					System.out.println("grid pattern after copy is " +GridStatus.get(i+1).get(j).getPattern());
					tmpGrid.clearGrid();
					System.out.println("Clear "+i+" "+j+"now is"+GridStatus.get(i).get(j).getPattern());
					//bottomNeighbor.setUpdated(true);
					if(i+1==mainAreaHeightNumOfUnits-1){
						bottomNeighbor.setFreezed(true);
						shouldSpawn=true;
					}
				}
			}		
		}
		
		this.repaint();
	}
	private void spawnNewShape(){
		if(currentShapeId==0){

			GridStatus.get(0).get(2).setPattern(2);
			GridStatus.get(0).get(2).setColor(Color.RED);
			GridStatus.get(0).get(2).setShouldUpdate(true);
			//GridStatus.get(0).get(2).setUpdated(false);;

		}else
		if(currentShapeId==1){
			GridStatus.get(0).get(2).setPattern(4);
			GridStatus.get(0).get(2).setColor(Color.BLUE);
			GridStatus.get(0).get(2).setShouldUpdate(true);
			//GridStatus.get(0).get(2).setUpdated(false);

		}else
		if(currentShapeId==2){
			GridStatus.get(0).get(1).setPattern(3);
			GridStatus.get(0).get(1).setColor(Color.YELLOW);
			GridStatus.get(0).get(2).setPattern(2);
			GridStatus.get(0).get(2).setColor(Color.YELLOW);
			GridStatus.get(0).get(1).setShouldUpdate(true);
			GridStatus.get(0).get(2).setShouldUpdate(true);
			//GridStatus.get(0).get(1).setUpdated(false);
			//GridStatus.get(0).get(2).setUpdated(false);

		}else
		if(currentShapeId==3){
			GridStatus.get(0).get(1).setPattern(1);
			GridStatus.get(0).get(1).setColor(Color.GREEN);
			GridStatus.get(0).get(2).setPattern(2);
			GridStatus.get(0).get(2).setColor(Color.GREEN);
			GridStatus.get(0).get(1).setShouldUpdate(true);

			GridStatus.get(0).get(2).setShouldUpdate(true);

			//GridStatus.get(0).get(1).setUpdated(false);
			//GridStatus.get(0).get(2).setUpdated(false);
			//currentShape[0][1]=1;
			//currentShape[0][2]=2;
		}else
		if(currentShapeId==4){
			GridStatus.get(0).get(1).setPattern(3);
			GridStatus.get(0).get(1).setColor(Color.ORANGE);
			GridStatus.get(0).get(2).setPattern(0);
			GridStatus.get(0).get(2).setColor(Color.ORANGE);
			GridStatus.get(0).get(1).setShouldUpdate(true);

			GridStatus.get(0).get(2).setShouldUpdate(true);

			//GridStatus.get(0).get(1).setUpdated(false);
			//GridStatus.get(0).get(2).setUpdated(false);
			//currentShape[0][1]=3;
			//currentShape[0][2]=0;
		}else{
			System.out.println("Error: ShapeId is "+currentShapeId);
		}
		System.out.println("SpawnedShapeId:************************"+currentShapeId);
		shouldSpawn=false;
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
		drawCurrentShape(g2d);
		
		
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
		
		
		Polygon poly;
		int[] x;
		int[] y;
		int ax,bx,cx;
		int ay,by,cy;
		
		
		if(nextShapeId==-1){
			System.out.println("Cannot draw next shape id=-1");
		}
		if(nextShapeId==0){
			int edge = sectionNextShapeHeight*3/5;
			ax = sectionNextShapeX+(sectionNextShapeWidth-sectionNextShapeHeight)/2;
			bx = ax;
			cx = ax+edge;
			ay = sectionNextShapeY+(sectionNextShapeHeight-edge)/2;
			by = ay+edge;
			cy = by;
			x = new int[]{ax,bx,cx};
			y = new int[]{ay,by,cy};
			poly = new Polygon(x,y,3);
			g2d.drawPolygon(poly);
			g2d.setColor(Color.RED);
			g2d.fillPolygon(poly);
			g2d.setColor(Color.BLACK);
		}
		if(nextShapeId==1){
			int edge = sectionNextShapeHeight*3/4;
			ax = sectionNextShapeX+(sectionNextShapeWidth-edge)/2;
			bx = ax;
			cx = bx + edge;
			int dx = cx;
			
			ay = sectionNextShapeY+(sectionNextShapeHeight-edge)/2;
			by = ay+edge;
			cy = by;
			int dy = ay;
			x = new int[]{ax,bx,cx,dx};
			y = new int[]{ay,by,cy,dy};
			poly = new Polygon(x,y,4);
			g2d.drawPolygon(poly);
			g2d.setColor(Color.BLUE);
			g2d.fillPolygon(poly);
			g2d.setColor(Color.BLACK);
		}
		if(nextShapeId==2){
			x = new int[]{sectionNextShapeX+sectionNextShapeWidth/10,sectionNextShapeX+sectionNextShapeWidth/2,sectionNextShapeX+sectionNextShapeWidth-sectionNextShapeWidth/10};
			y = new int[]{sectionNextShapeY+sectionNextShapeHeight-sectionNextShapeHeight/10,sectionNextShapeY+sectionNextShapeHeight/10,sectionNextShapeY+sectionNextShapeHeight-sectionNextShapeHeight/10};
			poly = new Polygon(x,y,3);
			g2d.drawPolygon(poly);
			g2d.setColor(Color.YELLOW);
			g2d.fillPolygon(poly);
			g2d.setColor(Color.BLACK);
		}
		if(nextShapeId==3){
			int edge = sectionNextShapeHeight*3/4;
			ax = sectionNextShapeX+(sectionNextShapeWidth-2*edge)/2;
			bx = ax + edge;
			cx = bx + edge;
			int dx = bx;
			
			ay = sectionNextShapeY+(sectionNextShapeHeight-edge)/2;
			by = ay+edge;
			cy = by;
			int dy = ay;
			x = new int[]{ax,bx,cx,dx};
			y = new int[]{ay,by,cy,dy};
			poly = new Polygon(x,y,4);
			g2d.drawPolygon(poly);
			g2d.setColor(Color.GREEN);
			g2d.fillPolygon(poly);
			g2d.setColor(Color.BLACK);
		}
		if(nextShapeId==4){
			int edge = sectionNextShapeHeight*3/4;
			ax = sectionNextShapeX+(sectionNextShapeWidth-2*edge)/2;
			bx = ax + edge;
			cx = bx + edge;
			int dx = bx;
			
			ay = sectionNextShapeY+(sectionNextShapeHeight-edge)/2 + edge;
			by = ay;
			cy = by - edge;
			int dy = cy;
			x = new int[]{ax,bx,cx,dx};
			y = new int[]{ay,by,cy,dy};
			poly = new Polygon(x,y,4);
			g2d.drawPolygon(poly);
			g2d.setColor(Color.GREEN);
			g2d.fillPolygon(poly);
			g2d.setColor(Color.BLACK);
		}
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
		//System.out.println(sectionNextShapeWidth);

		g2d.setFont(new Font("TimesRoman", Font.PLAIN, sectionNextShapeWidth/7));
		int currentY=textDisplayY;
		for (String line : textDisplay.split("\n")){
			g2d.drawString(line, textDisplayX, currentY);
			currentY += g2d.getFontMetrics().getHeight()*2;
		}
		repaint();
    
	}
	
	private void drawCurrentShape(Graphics2D g2d){
		//System.out.println("drawCurrentShape********************************");
		int ax,bx,cx,ay,by,cy;
		Polygon poly = null;
		for(int i=0;i<mainAreaHeightNumOfUnits;i++){
			for(int j=0;j<mainAreaWidthNumOfUnits;j++){
				ElementGrid tmpGrid = GridStatus.get(i).get(j);
				int tmpPattern=tmpGrid.getPattern();
				Color tmpColor = tmpGrid.getColor();
				int anchorx = sectionMainLeftMargin+pxPerUnit*j;
				int anchory = sectionMainTopMargin+pxPerUnit*i;
				if(tmpPattern==-1) continue;
				if(tmpPattern==0){
					ax = anchorx;
					bx = anchorx;
					cx = anchorx+pxPerUnit;
					ay = anchory;
					by = anchory+pxPerUnit;
					cy = anchory;
					
					int[] x = {ax,bx,cx};
					int[] y = {ay,by,cy};
					poly = new Polygon(x,y,3);
				}else if(tmpPattern==1){
					ax = anchorx;
					bx = anchorx+pxPerUnit;
					cx = anchorx+pxPerUnit;
					ay = anchory;
					by = anchory;
					cy = anchory+pxPerUnit;
					int[] x = {ax,bx,cx};
					int[] y = {ay,by,cy};
					poly = new Polygon(x,y,3);
				}else if(tmpPattern==2){
					ax = anchorx;
					bx = anchorx;
					cx = anchorx+pxPerUnit;
					ay = anchory;
					by = anchory+pxPerUnit;
					cy = anchory+pxPerUnit;
					int[] x = {ax,bx,cx};
					int[] y = {ay,by,cy};
					poly = new Polygon(x,y,3);
				}else if(tmpPattern==3){
					ax = anchorx;
					bx = anchorx+pxPerUnit;
					cx = anchorx+pxPerUnit;
					ay = anchory+pxPerUnit;
					by = anchory;
					cy = anchory+pxPerUnit;
					int[] x = {ax,bx,cx};
					int[] y = {ay,by,cy};
					poly = new Polygon(x,y,3);
				}
				else if(tmpPattern==4){
					ax = anchorx;
					ay = anchory;
					int[] x = {ax,ax,ax+pxPerUnit,ax+pxPerUnit};
					int[] y = {ay,ay+pxPerUnit,ay+pxPerUnit,ay};
					poly = new Polygon(x,y,4);
				}else{
					System.out.println("Error in drawCurrentShape()");
				}
				
				g2d.drawPolygon(poly);
				g2d.setColor(tmpColor);
				g2d.fillPolygon(poly);
			}
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
