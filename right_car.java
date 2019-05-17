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
	
	//Kataskeuasths: prwto orisma einai h gefura,deutero orisma einai to monadiko id to kathe amaksiou
	//kai trito orisma einai poio apo ta 4 senaria ths efarmoghs tha ulopoihsh to amaksi.
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
		
		//Ama to trito orisma ths klasshs einai iso me 1 tote tha treksei to prwto senario
		if (scenario == 1) 
		{
			System.out.println("                                                      Right car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_wrong(id, type);
		}
		//Ama to trito orisma ths klasshs einai iso me 2 tote tha treksei to deutero senario
		if (scenario == 2)
		{
			System.out.println("                                                      Right car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_save(id, type);
		}
		//Ama to trito orisma ths klasshs einai iso me 3 tote tha treksei to trito senario
		if(scenario == 3) 
		{
			System.out.println("                                                      Right car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_alternately(id, type);
		}
		//Ama to trito orisma ths klasshs einai iso me 4 tote tha treksei to tetarto senario
		if (scenario == 4)
		{
			System.out.println("                                                      Right car " + id + " Arrived at " + dateFormat.format(date = new Date()));
			bridge.cross_alternately_with_adjustment(id, type);
		
		}
	}
	
	
	
	
	
	
}
