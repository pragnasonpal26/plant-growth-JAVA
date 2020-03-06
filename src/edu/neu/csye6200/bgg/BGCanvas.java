package edu.neu.csye6200.bgg;

import java.util.logging.Logger;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import java.util.Observable;
import java.util.Observer;

/**
 * A sample canvas that draws tree according to rule entered by user
 * @author Pragna
 */
public class BGCanvas extends JPanel implements Observer {      

	BGGenerationSet bg;
	private static final long serialVersionUID = 1L;
	private static BGCanvas instance=null;
	private static Logger log = Logger.getLogger(BGCanvas.class.getName());
	private int lineSize = 20;
	private static Color col = null;
	private long counter = 0L;

	/**
	 * BGCanvas as Singlton design pattern 
	 *
	 */
	public static BGCanvas instance()  
	{
		col=Color.WHITE;
		if(instance==null)
			instance=new BGCanvas();
		log.info("BGCanvas class");        //Log for class creation
		return instance;

	}
	/**
	 * The UI thread calls this method when the screen changes, or in response
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {
		drawBG(g); // Our Added-on drawing	
	}

	/**
	 * Draw the CA graphics panel
	 * @param g
	 */
	
	public void drawBG(Graphics g) {
		log.info("Drawing BG " + counter++);
		Graphics2D g2d = (Graphics2D) g;
		Dimension size = getSize();

		g2d.setColor(Color.BLACK);
		g2d.fillRect(0, 0, size.width, size.height);
		
		//TexturePaint paint = new TexturePaint(bufferedImage, anchorRect);
		//g2d.setPaint(paint);
		g2d.setStroke(new BasicStroke(4.0F));

		paintLine( g2d,0, 600,size.width, 600, Color.DARK_GRAY);      //Base Line
		if(bg != null) {
			//g2d.setColor(Color.GREEN);
			
			int new_x=(int)(bg.bg1.getBase().getX()* 30 +(Math.cos(bg.bg1.getBase().getDirection()))*bg.bg1.getBase().getlen()* 30);  //updated x coordinate of child stem
			int new_y=(int)(bg.bg1.getBase().getY()* 30+(Math.sin(bg.bg1.getBase().getDirection()))*bg.bg1.getBase().getlen()* 30);  //updated y coordinate of child stem

			paintLine( g2d,bg.bg1.getBase().getX() * 30, bg.bg1.getBase().getY() * 30,new_x, new_y, Color.CYAN);   //Base stem creation on canvas
			printStem(g2d,bg.bg1.getBase());       
		}	

	}
	
	/**
	 * Draw all the stems graphics on canvas through iterating child stem list
	 * @param g
	 */
	
	public void printStem(Graphics g,BGStem st) {
		Graphics2D g2d = (Graphics2D) g;
		col=Color.GREEN;
		if(!st.childstemList.isEmpty()) {
			for(BGStem s : st.childstemList) {
				int x=(int)(s.getX()* 30 +(Math.cos(s.getDirection()))*s.getlen()* 30);  //updated x coordinate of child stem
				int y=(int)(s.getY()* 30+(Math.sin(s.getDirection()))*s.getlen()* 30);  //updated y coordinate of child stem
				
				paintLine( g2d,s.getX() * 30, s.getY() * 30,x, y, col); 
				printStem(g2d,s);
			}
			col=Color.orange;
		}
	}

	/**
	 * A convenience routine to set the color and draw a line
	 * @param g2d the 2D Graphics context
	 * @param startx the line start position on the x-Axis
	 * @param starty the line start position on the y-Axis
	 * @param endx the line end position on the x-Axis
	 * @param endy the line end position on the y-Axis
	 * @param color the line color
	 */
	private void paintLine(Graphics2D g2d, int startx, int starty, int endx, int endy, Color color) {
		g2d.setColor(color);
		g2d.drawLine(startx, starty, endx, endy);
	}

	@Override      
	public void update(Observable src, Object obj) {     //Update will receive update from observable will perform repaint  
		// TODO Auto-generated method stub
		bg=(BGGenerationSet) obj;
		System.out.println("ES received update from" +src);
		System.out.println("           message:"+ bg.toString());
		this.revalidate();
		this.repaint();

	}

}
