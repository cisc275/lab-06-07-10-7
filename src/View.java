import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/

public class View extends JPanel{

	final static int imageWidth=165;
	final static int imageHeight=165;
	final int frameCount =10;
	int frameNum=0;
	final static int frameWidth = 500;
	final static int frameHeight = 300;
	Direction direction;
	int xPos;
	int yPos;
	String[] fileArray = new String [] {"orc_forward_north.png", "orc_forward_northeast.png", 
			"orc_forward_east.png", "orc_forward_southeast.png", "orc_forward_south.png",
			"orc_forward_southwest.png","orc_forward_west.png","orc_forward_northwest.png"};
	JFrame frame;
	
	BufferedImage[][]pics;
	HashMap<String, BufferedImage[]> picMap;
	
	View(){
		/* Constructor for view()
		 * 		loads all image files
		 * 		sets up the JFrame
		 */
		picMap = new HashMap<String, BufferedImage[]>();
		pics = new BufferedImage[8][10];
		for (int j = 0; j<fileArray.length; j++) {
    		BufferedImage img = createImage(fileArray[j]);
    		for(int i = 0; i < frameCount; i++) {
    			pics[j][i] = img.getSubimage(imageWidth*i, 0, imageWidth, imageHeight);
    		}
    		picMap.put(fileArray[j].substring(12, fileArray[j].length()-4), pics[j]);
    		
    	}
		
		
		frame = new JFrame();
		frame.getContentPane().add(this);
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(frameWidth, frameHeight);
    	frame.setVisible(true);	
    	
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
	
	public void update(int x, int y, Direction d) {
		/*
		 * Calls the updates the frame and calls the repaint function
		 */
		frameNum = (frameNum +1) % frameCount;
		direction = d;
		xPos=x;
		yPos=y;
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
    		bufferedImage = ImageIO.read(new File("../src/orc/"+filename));
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
		g.drawImage((picMap.get(direction.getName()))[frameNum], xPos, yPos, Color.gray, this);	
	}
	
}