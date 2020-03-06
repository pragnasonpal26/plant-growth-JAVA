package edu.neu.csye6200.bgg;
import java.util.ArrayList;

/**
 * BGGeneration using stems to determine the growth of tree 
 * @author Pragna
 */

public class BGGeneration 
{

	protected ArrayList<BGStem> stemList;          		//Protected ArrayList for stems of Plant
	protected ArrayList <BGStem> treeList;				//Protected ArrayList for all the generations of stems

	private BGStem base;									//base of plant 

	protected ArrayList<Double> angles;					//Arraylist of angels getting to contain rules from BGRule class
	protected int depth;								//Container for depth of each stem

	String rule_n;										//Rule No
	int num_Gen;										//No. of Generation

	static int idCounter=0;								// idCounter to generate unique id
	private boolean ifbase;                    			// boolean variable to check base is present or not 
	private double len=5;					  			// Stem length
	private int id;
	int count;
	protected String type;
	private int height;
	private int width;
	int x=25; 						//Starting X-coordinate 
	int y=15;						//Starting Y-coordinate
	double direction=90;			//Direction for base stem

	/**
	 * BGGeneration constructor
	 */

	public BGGeneration(String type,boolean ifbase,double len)
	{
		this.id=idCounter++;
		this.type=type;
		this.len=len;
		this.ifbase=ifbase;
	}

	/**
	 * Method to determine Initial Growth (Base stem to next generations)
	 */

	public void start_growth(int num_Gen,String rule_n)   
	{
		this.rule_n = rule_n;
		this.num_Gen=num_Gen;

		treeList=new ArrayList<BGStem>();
		double dir=90.0;  							//Base Stem angel
		if(ifbase)									//Check point for base stem
		{
			if(base==null)							//If Plant is allowed too have base but not available
			{
				base=new BGStem(x,y,len,dir);			//Create base stem

				growth(base);						//Grow next generation on top of that
				this.treeList.add(base);      		//Adding every stem to master Tree list
			}
			else
			{
				growth(base);						//Base is already available , grow the tree
			}
		}
	}

	/**
	 * Actual Method for growth calculation
	 */

	public void growth(BGStem stt)   
	{	
		BGRule rule=new BGRule();
		angles=rule.getAngleList(rule_n);   		//Getting angel from BGRule
		depth=rule.getLengthRule(rule_n);			//Getting Length from BGRule

		if(!stt.childstemList.isEmpty())     		//Check point for availability of child stems
		{
			for(BGStem s : stt.childstemList)
			{
				growth(s);							//Recursive call to growth method 
			}
		}
		else
		{
			for(double angle : angles)	   //For each angels from BGRule
			{
				int new_x=(int)(stt.getX()+(Math.cos(stt.getDirection()))*(depth));  //updated x coordinate of child stem
				int new_y=(int)(stt.getY()+(Math.sin(stt.getDirection()))*(depth));  //updated y coordinate of child stem

				BGStem st=new BGStem(new_x,new_y,depth,angle);
				stt.childstemList.add(st);	    //Adding child stem to the ArrayList
				this.treeList.add(st);			//Adding all generations to Master stemList
			}	

		}


	}

	/**
	 * Print Method for all the stems
	 */
	
	public  void PrintAll()
	{
		if(ifbase)       //Checking if plant has base stem or not
		{
			if(base!=null)       
			{
				System.out.println("Base stem:"+base.toString());
				base.Display();	     // Iterate through each child stems 
			}
		}
		else
		{
			if(stemList!=null)
			{
				for(BGStem t : stemList)
				{
					System.out.println("Parent Stem :"+t.toString());
					t.Display();
					System.out.println("-------------------------------------------");
				}
			}	
		}
	}
	public String toString()     //toString method for Plants details
	{
		return "Type:" + type +" * " + "Plant base? "+ ifbase  + " * " + "Length=" + len;
	}


	public ArrayList<BGStem> getTreeList() {
		return treeList;
	}
	public int getId() {
		return id;
	}
	public ArrayList<BGStem> getStemList() {
		return stemList;
	}
	public boolean isIfbase() {
		return ifbase;
	}
	public void setIfbase(boolean ifbase) {
		this.ifbase = ifbase;
	}
	public double getLength() {
		return len;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	public BGStem getBase() {
		return base;
	}
	public void setBase(BGStem base) {
		this.base = base;
	}
}
