import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;


/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

//test 
public class View extends JPanel{

	private JButton b1;
	final static int imageWidth=165;
	final static int imageHeight=165;
	final int frameCount =10;
	int frameNum=0;
	final static int frameWidth = 500;
	final static int frameHeight = 300;
	Direction direction = Direction.SOUTHEAST;
	int xPos=0;
	int yPos=0;
	boolean run = true;
	
	String[] fileArray = new String [] {"orc_forward_north.png", "orc_forward_northeast.png", 
			"orc_forward_east.png", "orc_forward_southeast.png", "orc_forward_south.png",
			"orc_forward_southwest.png","orc_forward_west.png","orc_forward_northwest.png"};
	String[] idleArray = new String [] {"orc_idle_east.png", "orc_idle_west.png", 
			"orc_idle_north.png", "orc_idle_south.png","orc_idle_northeast.png", 
			"orc_idle_southeast.png", "orc_idle_northwest.png", "orc_idle_southwest.png"};
	JFrame frame;
	
	BufferedImage[][]pics;
	BufferedImage[][]idlePics;
	HashMap<String, BufferedImage[]> picMap;
	HashMap<String, BufferedImage[]> idlePicMap;
	
	View(){
		/* Constructor for view()
		 * 		loads all image files
		 * 		sets up the JFrame
		 */
		picMap = new HashMap<String, BufferedImage[]>();
		pics = new BufferedImage[8][10];
		idlePicMap = new HashMap<String, BufferedImage[]>();
		idlePics = new BufferedImage[8][10];
		/* Idle changes: added idleArray, idlePicMap, & idlePics to store the 
		 *  idle images in the same way as forward images & frames
		 */
		
		for (int j = 0; j<fileArray.length; j++) {
			BufferedImage img = createImage(fileArray[j]);
			BufferedImage idleImg = createImage(idleArray[j]);
			for(int i = 0; i < frameCount; i++) {
				pics[j][i] = img.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
				idlePics[j][i] = idleImg.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
			}
			picMap.put(fileArray[j].substring(12, fileArray[j].length()-4), pics[j]);
			idlePicMap.put(idleArray[j].substring(9, idleArray[j].length()-4), idlePics[j]);	
		}	

    	b1 = new JButton("Stop");
    	b1.setBounds(50,100,50,50);
		
		frame = new JFrame();
		frame.add(b1);
		frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setFocusable(true);
    	frame.requestFocus();
    	frame.setVisible(true);	
    	//update(0,0,direction, false);
    	
	}
	
	public int getWidth() {
		return frameWidth;
	}
	public int getHeight() {
		return frameHeight;
	}
	public int getImageWidth() {
		return imageWidth;
	}
	public int getImageHeight() {
		return imageHeight;
	}
	
	public JButton getButton()
	{
		return b1;
	}
	public void update(int x, int y, Direction d, boolean start_stop) {
		/*
		 * Calls the updates the frame and calls the repaint function
		 */
		run = start_stop;
		frameNum = (frameNum +1) % frameCount;
		direction = d;
		xPos=x;
		yPos=y;

		
		/* changed repaint to always run,
		 * switched control of images to paint function
		 */
		frame.repaint();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private BufferedImage createImage(String filename){
		/*
		 * Reads in the image files
		 */
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File("src/orc/"+filename));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    }
	
	public void paint(Graphics g) {
		/*
		 * Draws the picture to the screen
		 */

		try {
			if(run) {
				g.drawImage((picMap.get(direction.getName()))[frameNum], xPos, yPos, Color.gray, this);
			}
			else {
				g.drawImage((idlePicMap.get(direction.getName()))[frameNum], xPos, yPos, Color.gray, this);
			}
			/* only draws running images when running,
			 * draws idle images if stop button is pressed,
			 * draws from idlePicMap.
			 */
			
		}
		catch(NullPointerException e)	{
			System.out.println(e);
		}
		
	}
	
}