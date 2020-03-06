package edu.neu.csye6200.bgg;
import java.util.ArrayList;

/**
 * BGRule class which defines unique rules for plant growth 
 * @author Pragna
 */

public class BGRule  {
	BGGeneration bgg;
	BGRule rule;
	int count=0;
	        
	protected int length_rule;

/**
 * Routine @return the angels to growth method for creation of new stem
 *
 */
	public ArrayList<Double> getAngleList(String ruleNo) {       //Rule set for angels of each stem generation
		ArrayList<Double> angles = new ArrayList<Double>();
		if(ruleNo=="1")	
		{
			angles.add(-40.0);
			angles.add(-90.0);
			angles.add(-120.0);
			
		}else if(ruleNo =="2") {
			angles.add(-60.0);
			angles.add(-120.0);
			angles.add(-180.0);
		}
	return angles;
	}
	
/**
* Routine @return the lengths to growth method for creation of new stem
*
*/	
	public int getLengthRule(String ruleNo) {         //Rule set for length of each stem generation
		if(ruleNo=="1")
		{
			length_rule=3;
			count++;
			if(count>2)
			{
				length_rule=2;
			}
		}
		else if(ruleNo=="2")
		{
			length_rule=2;
			count++;
			if(count>2)
			{
				length_rule=1;
			}
		}
		return length_rule;
		
	}

}
