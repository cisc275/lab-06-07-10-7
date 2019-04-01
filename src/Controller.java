/**
 * Do not modify this file without permission from your TA.
 **/

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.Timer;
public class Controller implements ActionListener, KeyListener{

	private Model model;
	private View view;
	private boolean start_stop=true;
	int drawDelay = 30;
	int dirKey;
	
	Action drawAction;
	
	public Controller() 
	{
		view = new View();
		System.out.println("View Initiaized");
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		System.out.println("Model Initiaized");
		view.getButton().addActionListener(this);
		view.frame.addKeyListener(this);
		  
		
		drawAction = new AbstractAction()
    {
			public void actionPerformed(ActionEvent e)
      {
    			//increment the x and y coordinates, alter direction if necessary
				model.updateLocationAndDirection(start_stop,dirKey);			
    			//update the view
    			view.update(model.getX(), model.getY(), model.getDirect(), start_stop);
    		}
    	};
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
		dirKey=e.getKeyCode();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		dirKey=0;
	}
	
	public void actionPerformed(ActionEvent a)
	{
		start_stop=!(start_stop);
	}
	
        //run the simulation
	public void start()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				Timer t = new Timer(drawDelay, drawAction);
				t.start();
			}
		});
	}
}


