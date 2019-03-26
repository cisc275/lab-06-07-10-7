import java.awt.event.ActionEvent;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model{
	private int x;
	private int y;
	private Direction direction;
	
	private int xBound;
	private int yBound;
	
    final int xIncr = 8;
    final int yIncr = 2;
    
    private boolean forward;
    private boolean up;
    private boolean backward;
    private boolean down;
    
    final int drawDelay = 30;
	
	public Model(int w, int h, int imgW, int imgH)
	{
		xBound = w-imgW;
		yBound = h-imgH;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Direction getDirect() {
		return direction;
	}
	
	public int getDelay() {
		return drawDelay;
	}
	
	
	public void updateLocationAndDirection(boolean run) {
		/*
		 * Changes the increment based on the direction 
		 */
		if(run) {
		checkCollision();
		switch (direction) {
			case NORTH: //north
				y+=yIncr;

				break;
			
			case NORTHEAST: //north east
				x+=xIncr; 
				y-=yIncr;
;
				break;
				
			case EAST: //east
				x+=xIncr;

				break;
				
			case SOUTHEAST: //south east
				x+=xIncr;
				y+=yIncr;

				break;
				
			case SOUTH: //south
				y+=yIncr;

				break;
		
			case SOUTHWEST: //south west
				x-=xIncr;
				y+=yIncr;

				break;
				
			case WEST: //west
				x-=xIncr;
				break;
				
			case NORTHWEST: //north west
				y-=yIncr;
				x-=xIncr;
				break;
		}
		}
		
		
}
	
	private void checkCollision() {
		/*
		 * Switches flags when the position hits any of the borders
		 */
		 
		if (x>=xBound)
		{
			forward = false;
			backward = true;
		}
    	if (x<=0)
		{
			forward = true;
			backward = false;
		}
    	if (y>=yBound)
    	{
    		up = true;
    		down = false;
    		
    	}
    	if(y<=0)
    	{
    		up = false;
    		down = true;	
    	}
    	
    	setDirec();
	}
	
	private void setDirec() {
		/*
		 * sets the direction variable based on the change of direction flags
		 */
		if (up)
		{
			if(forward) {
				direction = Direction.NORTHEAST;
			}
			else if (backward){
				direction=Direction.NORTHWEST;
			}
			else {
				direction=Direction.NORTH;
			}
		}
		if (down)
		{
			if(forward) {
				direction=Direction.SOUTHEAST;
			}
			else if (backward){
				direction=Direction.SOUTHWEST;			
				}
			else {
				direction=Direction.SOUTH;
				}
		}

	}
}
