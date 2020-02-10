package hfdhfd;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

public class bridge {

	private DateFormat dateFormat;
	private Date date;
	
	private boolean turn;
	private int flag = 1;
	private int right_car_wait = 0;
	private int left_car_wait = 0;
	private int left_cars_will_pass;
	private int right_cars_will_pass;
	private int left_cars_passed=0;
	private int right_cars_passed=0;
	

	
	//Constructor
	public bridge()
	{
	dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	}
	
	public void setLeft_cars_will_pass(int left_cars_will_pass) {
		this.left_cars_will_pass = left_cars_will_pass;
	}

	public void setRight_cars_will_pass(int right_cars_will_pass) {
		this.right_cars_will_pass = right_cars_will_pass;
	}
	
	public void set_first_side_car_pass(boolean turn) {
		this.turn = turn;
	}
	
	
	
	

	//Function that implements the first scenario
	//First parameter is the id of the car
	//Second parameter is the type of the car, if it comes from left or right
	public void cross_wrong(int id,String type)
	{
		//the time that the car needs to cross the bridge
		try 
		{
			Thread.sleep(1000);
		} 
		catch (InterruptedException e) {}
				
		if ( type.equals("right_car"))
			System.out.println("                                                      Right car " + id + " Passing at " + dateFormat.format(date = new Date()));
		else
			System.out.println("Left car " + id + " Passing at " + dateFormat.format(date = new Date()));
				
	}
		
		
		
	// it is the same function with above, but it is synchronized
	public synchronized void cross_save(int id,String type) 
	{	
		
		try 
		{
		   Thread.sleep(1000);
		} 
		catch (InterruptedException e) {}
			
		if (type.equals("right_car"))
			System.out.println("                                                      Right car " + id + " Passing at " + dateFormat.format(date = new Date()));
		else
			System.out.println("Left car " + id + " Passing at " + dateFormat.format(date = new Date()));
			
	}
	
	
	
	
	//Function that implements the third scenario
		public synchronized void cross_alternately (int id,String type)
	{	
		
		//If the car comes from right	
		if (type.equals("right_car"))
		{
			//If it is the first car that cross the bridge
			if (flag == 1)
			{
				turn = true;
				flag = 0;
			}
			
			//if it is not its turn to cross the bridge it will wait
			while ( !turn == true && left_cars_passed != left_cars_will_pass)
			{
				try 
				{
					wait();
				} 
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		
		//if the car comes from left
		if (type.equals("left_car")) 
		{	
			
			//If it is the first car that cross the bridge
			if (flag == 1)
			{
				turn = false;
				flag = 0;
			}
			
			//if it is not its turn to cross the bridge it will wait
			while (turn == true  && right_cars_passed != right_cars_will_pass)
			{
				try 
				{
					wait();
				} 
				catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			
		//the time that the car needs to cross the bridge
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
				
				
		
		//Checks the car that crossed the bridge from which side it came and notify the cars that are waiting
		//from which side will be the next car that will cross the bridge
		if (turn == true)
		{
			turn = false;
		}
		else 
		{
			turn = true;
		}
				
				
				
		if (type.equals("right_car")) 
		{
			System.out.println("                                                      Right car " + id + " Passing at " + dateFormat.format(date = new Date()));
			right_cars_passed += 1;
		}
		else 
		{
			System.out.println("Left car " + id + " Passing at " + dateFormat.format(date = new Date()));
			left_cars_passed += 1;
		}
			
		notifyAll(); //wakes all the cars that are waiting
		
	}
	
	
	
	
	//Function that implements the fourth scenario
	public synchronized void cross_alternately_with_adjustment (int id,String type) 
	{
		
		//If the car comes from right	
		if (type.equals("right_car"))
		{
			right_car_wait += 1;
			
			//If it is the first car that cross the bridge
			if (flag == 1)
			{
				turn = true;
				flag = 0;
			}
			
			//if it is not its turn to cross the bridge it will wait
			while ( (!turn == true && (right_car_wait <= 5)) && left_cars_passed != left_cars_will_pass)
			{
				try 
				{
					wait();
				} 
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
				
		//If the car comes from left	
		if (type.equals("left_car"))
		{
			left_car_wait += 1;
			
			//If it is the first car that cross the bridge
			if (flag == 1)
			{
				turn = false;
				flag = 0;
			}		
			
			//if it is not its turn to cross the bridge it will wait
			while ((turn == true  && (left_car_wait <= 5)) && right_cars_passed != right_cars_will_pass)
			{
				try 
				{
					wait();
				} 
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
				
		//the time that the car needs to cross the bridge		
		try {
				Thread.sleep(1000);
			} 
		catch (InterruptedException e) {}
					
					

		if (type.equals("right_car"))
		{
			System.out.println("                                                      Right car " + id + " Passing at " + dateFormat.format(date = new Date()));
			right_car_wait -= 1;
			right_cars_passed +=1;
		}
		else {
				System.out.println("Left car " + id + " Passing at " + dateFormat.format(date = new Date()));
				left_car_wait -= 1;
				left_cars_passed +=1;
			}
			
					
	
		if (turn == true && (right_car_wait <= 5))
		{
			turn = false;
		}
		else if(turn == false  && (left_car_wait <= 5))
		{
			turn = true;
		}				
					
			
		notifyAll();   //wakes all the cars that are waiting
			
	}

	
	
	
	
}
