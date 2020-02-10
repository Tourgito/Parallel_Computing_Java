package hfdhfd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class right_car extends Thread{
	
	private bridge bridge;
	private int id;
	private DateFormat dateFormat;
	private Date date;
	private String type;
	private int scenario;
	
	//Constructor
	public right_car(bridge bridge, int id,int scenario) 
	{
		this.bridge = bridge;
		this.id = id;
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		this.scenario = scenario;
		type = "right_car";
		
	}

	@Override
	public void run() {
		try {
			 Thread.sleep((long)(Math.random() * 40000));
	        } catch (InterruptedException e) {}
		
		// Scenario 1
		if (scenario == 1) 
		{
			System.out.println("                                                      Right car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_wrong(id, type);
		}
		// Scenario 2
		if (scenario == 2)
		{
			System.out.println("                                                      Right car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_save(id, type);
		}
		// Scenario 3
		if(scenario == 3) 
		{
			System.out.println("                                                      Right car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_alternately(id, type);
		}
		// Scenario 4
		if (scenario == 4)
		{
			System.out.println("                                                      Right car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_alternately_with_adjustment(id, type);
		
		}
	}
	
	
	
	
	
	
}
