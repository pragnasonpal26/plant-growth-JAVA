package edu.neu.csye6200.bgg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * An application for the  Biological Growth of plant
 * @author Pragna
 */
public class MyApp extends BGApp{

	private static Logger log = Logger.getLogger(MyApp.class.getName());

	protected JPanel mainPanel = null;
	protected JPanel westPanel = null;
	protected JPanel northPanel=null;
	protected JButton startBtn = null;
	protected JButton stopBtn = null;
	protected JButton pauseBtn=null;

	protected JButton resumeBtn=null;
	protected JComboBox combo;
	protected JComboBox gen;
	private BGCanvas bgPanel = null;
	protected JLabel gen_label = null; 
	protected JLabel rule_label =null;
	Thread a ;

	BGGenerationSet g;

	/**
	 * My plant app constructor
	 */
	public MyApp() {

		frame.setSize(500, 400); // initial Frame size
		frame.setTitle("MyApp");

		menuMgr.createDefaultActions(); // Set up default menu items

		showUI(); // Cause the Swing Dispatch thread to display the JFrame
	}

	/**
	 * Create a main panel that will hold the bulk of our application display
	 */
	@Override
	public JPanel getMainPanel() {

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(BorderLayout.WEST, getWestPanel());

		bgPanel = BGCanvas.instance();
		mainPanel.add(BorderLayout.CENTER, bgPanel);

		mainPanel.add(BorderLayout.NORTH,getNorthPanel());

		return mainPanel;
	}

	/**
	 * Create a west panel that will hold control buttons to start,stop,pause and resume our execution 
	 * @return
	 */
	public JPanel getWestPanel() {
		westPanel = new JPanel();
		westPanel.setLayout(new FlowLayout());

		startBtn = new JButton("Start");
		startBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)        //ActionListener to start the thread and execution on button click
			{
				log.info("We received an ActionEvent " + ae);
				String n_rule=(String)combo.getItemAt(combo.getSelectedIndex());
				int no_gen=(int)gen.getItemAt(gen.getSelectedIndex());
				g=new BGGenerationSet(no_gen,n_rule);
				a =new Thread(g);                             //Creation of thread
				a.start();									//Invoking Thread
			}}
				); 

		// Allow the app to hear about button pushes
		westPanel.add(startBtn);
		pauseBtn=new JButton("Pause");
		resumeBtn=new JButton("Resume");

		westPanel.add(pauseBtn);
		westPanel.add(resumeBtn);

		stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
		stopBtn.addActionListener(new ActionListener()                        //Stop button to terminate the execution of plant growth
		{
			public void actionPerformed(ActionEvent ae)
			{
				log.info("We received an ActionEvent " + ae);

				a.stop();
			}}
		); 

		pauseBtn.addActionListener(new ActionListener()                  ///Pause button to stop the execution in between 
		{
			public void actionPerformed(ActionEvent ae)
			{
				log.info("pause pressed ");
				log.info("We received an ActionEvent " + ae);

				a.suspend();
			}}
		); 
		resumeBtn.addActionListener(new ActionListener()				//Resume button to retrieve the paused state and start execution again
		{

			public void actionPerformed(ActionEvent ae)

			{
				log.info("resume pressed ");
				log.info("We received an ActionEvent " + ae);

				a.resume();
			}}
				); 
		westPanel.add(stopBtn);
		return westPanel;
	}

	/**
	 * Create a North panel that will hold inputs from users to determine the growth
	 * @return
	 */
	public JPanel getNorthPanel()
	{
		northPanel=new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		rule_label = new JLabel("Rules");        //JLabel for rule selection
		rule_label.setHorizontalAlignment(JLabel.CENTER);  
		rule_label.setSize(400,100); 
		northPanel.add(rule_label);

		String rules[]= {"1","2"};

		combo=new JComboBox(rules);                    //Combo box to have drop down of rules for user selection 
		combo.setForeground(Color.BLUE);
		combo.setBounds(100,100,80,30);  
		combo.setFont(new Font("Arial", Font.BOLD, 14));

		northPanel.add(combo);

		gen_label = new JLabel("No. of Generations");     //JLabel for No. of generation 
		gen_label.setHorizontalAlignment(JLabel.CENTER);  
		gen_label.setSize(400,100); 
		northPanel.add(gen_label);

		Integer[] no_gen= {2,3,4,5,6,7,8};
		gen=new JComboBox(no_gen);                        //Combo box to have list of selection for number of generation 
		gen.setForeground(Color.DARK_GRAY);
		gen.setFont(new Font("Arial", Font.BOLD, 14));
		northPanel.add(gen);

		return northPanel;
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		log.info("We received an ActionEvent " + ae);
		if (ae.getSource() == startBtn)
			System.out.println("Start pressed");
		else if (ae.getSource() == stopBtn)
			System.out.println("Stop pressed");
	}

	@Override
	public void windowOpened(WindowEvent e) {
		log.info("Window opened");
	}

	@Override
	public void windowClosing(WindowEvent e) {	
		log.info("Window closing");
	}

	@Override
	public void windowClosed(WindowEvent e) {
		log.info("Window closed");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		log.info("Window iconified");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {	
		log.info("Window deiconified");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		log.info("Window activated");
	}
	@Override
	public void windowDeactivated(WindowEvent e) {	
		log.info("Window deactivated");
	}

	/**
	 * My Plant application starting point
	 * @param args
	 */
	public static void main(String[] args) {
		MyApp mapp = new MyApp();    
		log.info(" started");
	}


}
