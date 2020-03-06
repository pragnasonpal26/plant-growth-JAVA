package edu.neu.csye6200.bgg;
import java.util.ArrayList;

/**
 * A Stem Class to build stems for the tree
 * @author Pragna
 *
 */

public class BGStem
{
	private static int idCounter=0;
	private int x;         //x coordinate
	private int y;		  //y coordinate
	private int id;       // Unique id
	private double len;    //length of stem
	private double direction;   //angle of direction to create branch
	int count;
	
	ArrayList<BGStem> childstemList; 
	
	public BGStem(int x,int y,double len,double direction)
	{
		this.id=idCounter++;
		this.setX(x);
		this.setY(y);
		this.len=len;
		this.direction=Math.toRadians(direction);
		
		childstemList=new ArrayList<BGStem>();	
	}
	public String toString()
	{
		
		String st=String.format("<Stem> ID: (%1$01d)  Location: (%2$01d,%3$01d)  Length: %4$3.2f  Angle:%5$3.2f \t \r \n", getId(),getX(),getY(),getlen(), getDirection());
		return st;
	}
	
	public void check()      //method to determine if the child list is empty
	{	
		 System.out.println("Is ArrayList Empty? "+childstemList.isEmpty());	
	}
	
	public void Display()      //Display all child stems
	{
		System.out.println("--------------------------------------------");
		if(!this.childstemList.isEmpty())
		{
			for(BGStem st : childstemList)
			{
				
				System.out.println("children stems:"+st.toString());
				st.Display();	
			}
			System.out.println("--------------------------------------------");
		}
	}
	public int n_child() {        //Method to determine basic Count of child stem 
		int count=1;
			if(childstemList.isEmpty())
			{
				return 1;
			}
			else
			{
				for(BGStem st: childstemList)
				{
					count+=st.n_child();
				}
			}
		return count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public double getlen() {
		return len;
	}
	public void setLength(int len) {
		this.len = len;
	}
	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction) {
		this.direction = direction;
	}
	public ArrayList<BGStem> getChildstemList() {
		return childstemList;
	}
	public void setChildstemList(ArrayList<BGStem> childstemList) {
		this.childstemList = childstemList;
	}	
}
