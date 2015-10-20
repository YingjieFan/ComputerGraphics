package TetrisSecond;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.Timer;

public class TetrisWin extends JPanel implements MouseListener,ActionListener,KeyListener{
   Random r=new Random();
   JButton newGame=new JButton("Start");
   JButton endGame=new JButton("Quit");
   int level,score,score2;
   TetrisAct[] act=new TetrisAct[4];
   TetrisAct[] act2=new TetrisAct[4];
   boolean start=false;
   int[][] map=new int[10][20];
   Timer si;
   int temp;
   boolean stop=false;
   public TetrisWin()
   {
	   for(int i=0;i<act.length;i++){
		   act[i]=new TetrisAct();
	   }
	   for(int i=0;i<act.length;i++){
		   act2[i]=new TetrisAct();
	   }
	   for(int i=0;i<10;i++){
		   for(int j=0;j<20;j++){
			  map[i][j]=0;
		   }
	   }
	  setLayout(null);
	  add(newGame);
	  add(endGame); 
	  endGame.setBounds(460,565,90,45);
	  newGame.setBounds(340,565,90,45);
	  newGame.addActionListener(this);
	  endGame.addActionListener(this);
	  addKeyListener(this);
	  addMouseListener(this);
   }
   public void paintComponent(Graphics g)
   {super.paintComponent(g);
    g.drawRect(10,10,300,600);
    g.setFont(new Font("宋体",Font.BOLD,25));
	g.drawString("level"+"   "+level, 345, 240); 
	g.setFont(new Font("宋体",Font.BOLD,25));
	g.drawString("lines"+"   "+score/100, 345, 360); 
	g.setFont(new Font("宋体",Font.BOLD,25));
	g.drawString("score"+"   "+score, 345, 480); 
	g.drawRect(345,10,220,150);
if(stop){
	 g.setColor(new Color(255,155,0));
	g.drawString("pause"+"   ", 150, 330); 
	g.setFont(new Font("宋体",Font.BOLD,25));
}	

if(start){
	   g.setColor(new Color(255,0,0));
	   for(int i=0;i<act.length;i++){
		   g.fillRect(10+act[i].x*30, 10+act[i].y*30, 30, 30);
	   }
	  for(int i=0;i<10;i++){
		   for(int j=0;j<20;j++){
			  if(map[i][j]==1){
				  g.fillRect(10+i*30, 10+j*30, 30, 30);
			  }
		   }
	   } 
	   g.setColor(new Color(0,0,255));
	   for(int i=0;i<4;i++){
		   g.fillRect(360+act2[i].x*30, 50+act2[i].y*30, 30, 30);
	   }
 }
   }
   public boolean newAct(){
	   switch(temp){
	   case 0:  
		   act[0].x=1;act[0].y=0;
		   act[1].x=2;act[1].y=0;
		   act[2].x=3;act[2].y=0;
		   act[3].x=4;act[3].y=0;
		   break;
	   case 1:  
		   act[0].x=2;act[0].y=0;
		   act[1].x=3;act[1].y=0;
		   act[2].x=2;act[2].y=1;
		   act[3].x=3;act[3].y=1;
		   break;
	   case 2:  
		   act[0].x=2;act[0].y=0;
		   act[1].x=2;act[1].y=1;
		   act[2].x=2;act[2].y=2;
		   act[3].x=3;act[3].y=2;
		   break;
	   case 3:   
		   act[0].x=3;act[0].y=0;
		   act[1].x=3;act[1].y=1;
		   act[2].x=3;act[2].y=2;
		   act[3].x=2;act[3].y=2;
		   break;
	   case 4:   
		   act[0].x=3;act[0].y=0;
		   act[1].x=2;act[1].y=1;
		   act[2].x=3;act[2].y=1;
		   act[3].x=4;act[3].y=1;
		   break;
	   case 5:   
		   act[0].x=2;act[0].y=0;
		   act[1].x=3;act[1].y=0;
		   act[2].x=3;act[2].y=1;
		   act[3].x=4;act[3].y=1;
		   break;
	   case 6:  
		   act[0].x=3;act[0].y=0;
		   act[1].x=4;act[1].y=0;
		   act[2].x=3;act[2].y=1;
		   act[3].x=2;act[3].y=1;
		   break;
	   }
	   for(int i=0;i<4;i++){
		  if(MaxYes(act[i].x,act[i].y)){
			  return false;
		  }
	   }
	   return true;
   }
   public void thenAct(){
	   switch(temp){
	   case 0:  
		   act2[0].x=1;act2[0].y=0;
		   act2[1].x=2;act2[1].y=0;
		   act2[2].x=3;act2[2].y=0;
		   act2[3].x=4;act2[3].y=0;
		   break;
	   case 1:  
		   act2[0].x=2;act2[0].y=0;
		   act2[1].x=3;act2[1].y=0;
		   act2[2].x=2;act2[2].y=1;
		   act2[3].x=3;act2[3].y=1;
		   break;
	   case 2:  
		   act2[0].x=2;act2[0].y=0;
		   act2[1].x=2;act2[1].y=1;
		   act2[2].x=2;act2[2].y=2;
		   act2[3].x=3;act2[3].y=2;
		   break;
	   case 3:   
		   act2[0].x=3;act2[0].y=0;
		   act2[1].x=3;act2[1].y=1;
		   act2[2].x=3;act2[2].y=2;
		   act2[3].x=2;act2[3].y=2;
		   break;
	   case 4:   
		   act2[0].x=3;act2[0].y=0;
		   act2[1].x=2;act2[1].y=1;
		   act2[2].x=3;act2[2].y=1;
		   act2[3].x=4;act2[3].y=1;
		   break;
	   case 5:   
		   act2[0].x=2;act2[0].y=0;
		   act2[1].x=3;act2[1].y=0;
		   act2[2].x=3;act2[2].y=1;
		   act2[3].x=4;act2[3].y=1;
		   break;
	   case 6:  
		   act2[0].x=3;act2[0].y=0;
		   act2[1].x=4;act2[1].y=0;
		   act2[2].x=3;act2[2].y=1;
		   act2[3].x=2;act2[3].y=1;
		   break;
	   }
	   
   }
   
   public void actionPerformed(ActionEvent e){
	 if(e.getSource()==newGame){
		 if(e.getActionCommand().equals("Start")){
		//newGame.setText("reStart");
		requestFocus(true);
		  start=true;
		temp=r.nextInt(7);
		 repaint();
		 if(e.getActionCommand().equals("reStart")){
			 start=false;
			 repaint();
		 }
		  if(!newAct()){
			  si=new Timer(1000-(100*level),new gameTimer());
			  si.start();
			  //System.out.println("GameOver1");
		      temp=r.nextInt(7);
		      thenAct();
			  repaint();
			  // game over
			  return;
		  }else{	  
		  }
		 }else{
			
				 newGame.setText("Start");
				 repaint();
			
			
		 }
		 
		 
	 }  
     if(e.getSource()==endGame){
		 System.exit(0);
	 }  
   }
   public void Move(int x,int y){
	   if(MinYes(x,y)){
		   for(int i=0;i<4;i++){
			   act[i].x+=x;
			   act[i].y+=y;
		   }
   }
	   
	   repaint();
   }
   public void rotation(){
	   TetrisAct[] aa=new TetrisAct[4];
	   for(int i=0;i<aa.length;i++){
		   aa[i]=new TetrisAct();
		   aa[i].x+=act[i].x;
		   aa[i].y+=act[i].y;
	   }
	   int tx=(aa[0].x+aa[1].x+aa[2].x+aa[3].x)/4;
	   int ty=(aa[0].y+aa[1].y+aa[2].y+aa[3].y)/4;
	   for(int i=0;i<aa.length;i++){
		   aa[i].x=tx+ty-act[i].y;
		   aa[i].y=ty+tx-act[i].x;
	   }
	   for(int i=0;i<aa.length;i++){
		  if(!MaxYes(aa[i].x,aa[i].y)){
			  return;
		  }
	   }
	   for(int i=0;i<aa.length;i++){
			  act[i].x=aa[i].x;
			  act[i].y=aa[i].y;
		   }
	   repaint();
   }
   
   public boolean MinYes(int x,int y){
	   for(int i=0;i<4;i++){
		   if(!MaxYes( act[i].x+x,act[i].y+y)){
			   return false;
		   }
	   }
	   return true;
   }
   public int delete(){
	  int line=0;
	   for(int i=0;i<20;i++){
		   int j;
		   for( j=0;j<10;j++){
			 if(map[j][i]==0){
				 break;}
		   }
		   if(j==10){
			   line+=1;
			   if(i!=0){
				   for(int j2=i-1;j>0;j--){
					   for(int n=0;n<10;n++){
						   map[n][j2+1]=map[n][j2];
					   }  
				   }
				   for(int j2=0;j<10;j++){
					   map[0][j2]=0;
				   }
			   }
		   }
	  
		   }
	   return line;
   }
   private void down(){
		 if(MinYes(0,1)){
			 for(int i=0;i<4;i++){
				   act[i].y+=1;
			   }
			 repaint();
		 }  else
		 {
		
			 for(int i=0;i<4;i++){
			  map[act[i].x][act[i].y]=1;
		 }
			 if(delete()!=0){
				 score+=100;
				 if(score-score2>=1500){
					 score2=score;
					
						 level++;
					 
				 }
			 }
		 if(!newAct()){
			 score+=500;
		
			 temp=r.nextInt(7);
			 thenAct();
			 
		 }
		 else{
			 System.out.println("gameover");
		 }
		 repaint();
		   }
		
	   }
public boolean MaxYes(int x,int y){
	if(x<=-1||x>=10||y<=-1||y>=20){
		return false;
	}
	if(map[x][y]==1){return false;}
	return true;
   }
public void mouseClicked(MouseEvent e){
	int x=e.getX();
	int y=e.getY();
	if(x<310&&y<610){
		si.stop();
		stop=true;
	   repaint();
	}
	if(e.getClickCount() == 2){
		si.start();
		stop=false;
		repaint();
	}
}

public void mouseEntered(MouseEvent e){
	int x=e.getX();     //because my computer have slow response,so you need try many times into main area.
	int y=e.getY();
	if(x<310&&y<610){
		si.stop();
		stop=true;
	   repaint();
	}
	else{
		si.start();
		stop=false;
		repaint();
	}
}
public void mouseReleased(MouseEvent e){
	
}
public void mousePressed(MouseEvent e){
	
}
public void mouseExited(MouseEvent e){
	
}
   public void keyPressed(KeyEvent e){
	   	if(start){
	   		switch(e.getKeyCode()){
	   		case KeyEvent.VK_DOWN:
	   			down();
	   		break;
	   		case KeyEvent.VK_UP:
	   			rotation();
	   		break;
	   		case KeyEvent.VK_LEFT:
	   			Move(-1,0);
	   		break;
	   		case KeyEvent.VK_RIGHT:
	   			Move(1,0);
	   		break;
	   		}
	   	}
   }
public void keyReleased(KeyEvent e){
	
}
public void keyTyped(KeyEvent e){
	
}
   public class gameTimer implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		if(start){
			down();}
		
	}}
}
