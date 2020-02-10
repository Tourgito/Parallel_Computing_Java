package Code;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class left_car extends Thread{

	private bridge bridge;
	private int id;
	private DateFormat dateFormat;
	private Date date;
	private String type;
	private int scenario;
	
	//Constructor
	public left_car(bridge bridge, int id,int scenario) 
	{
		this.bridge = bridge;
		this.id = id;
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		type = "left_car";
		this.scenario = scenario;
	}

	@Override
	public void run()
	{
		
		try 
		{
			 Thread.sleep((long)(Math.random() * 40000));
		} catch (InterruptedException e) {}
		
		
		if (scenario == 1) 
		{
			System.out.println("Left car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_wrong(id, type);
		}
		//Scenario 3
		if (scenario == 2)
		{
			System.out.println("Left car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_save(id, type);
		}
		
		//Scenario 3
		if(scenario == 3) 
		{
			System.out.println("Left car " + id + " Arrived at " + dateFormat.format(date = new Date()));
		bridge.cross_alternately(id, type);
		}
		//Scenario 4
		if (scenario == 4)
		{
			System.out.println("Left car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_alternately_with_adjustment(id, type);
		
		}
	}
	}
