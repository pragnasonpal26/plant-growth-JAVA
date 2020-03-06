package edu.neu.csye6200.bgg;

import java.util.Observable;

/**
 * Runnable BGGenerationSet  
 * @author Pragna
 */

public class BGGenerationSet extends Observable implements Runnable {    
	BGGeneration bg1;        //Instance of BGGeneration
	int no_gen;
	String no_rule;
	
/**
 * A constructor to create BGGenerationSet
 * @author Pragna
 *
 */
	public BGGenerationSet(int no_gen,String no_rule) 
	{
		BGCanvas bgc=BGCanvas.instance();         ///Linking with sigelton design pattern of BGCanvas       
		addObserver(bgc);
		this.no_gen=no_gen;
		this.no_rule=no_rule;
	}	
	public void run() {
		bg1=new BGGeneration("Roses",true,5);
		
		for(int i=0;i<no_gen;i++)     //Loop through No of generation entered by user
		{
			bg1.start_growth(no_gen,no_rule);     //Start growth using inputs No of generations and rules
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			doAction();

		}
	}
	public void doAction() { // notify observer about each actions being performed
		setChanged();
		notifyObservers(this);
	}

}
