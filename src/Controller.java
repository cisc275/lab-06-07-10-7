/**
 * Do not modify this file without permission from your TA.
 **/

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Timer;
public class Controller implements ActionListener{

	private Model model;
	private View view;
	private boolean start_stop=true;
	int drawDelay = 30;
	
	Action drawAction;
	
	public Controller() {
		view = new View();
		System.out.println("View Initiaized");
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
		System.out.println("Model Initiaized");
		view.b1.addActionListener(this);
		
		drawAction = new AbstractAction(){
			public void actionPerformed(ActionEvent e){
    			//increment the x and y coordinates, alter direction if necessary
				model.updateLocationAndDirection(start_stop);
    			//update the view
    			view.update(model.getX(), model.getY(), model.getDirect(), start_stop);
    		}
    	};
	}
	
	public void actionPerformed(ActionEvent a)
	{
		start_stop=!(start_stop);
	}
	
        //run the simulation
	public void start(){
	
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				
				Timer t = new Timer(drawDelay, drawAction);
				t.start();
				
			}
		});
	}

}
