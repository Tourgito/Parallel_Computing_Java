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
	

	
	//Kataskeuasths
	public bridge()
	{
	dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	}

	//Methodos pou ulopoih to prwto senario.
	//Prwto orisma einai to monadiko id tou kathe amaksiou.
	//Deutero orisma einai to ti tupou einai to amaksi dhladh ama erxetai apo deksia h aristera.
	public void cross_wrong(int id,String type)
	{
		//O xronos pou xreiazetai gia na perasei thn gefura.			
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
		
		
		
	//Methodos pou ulopoih to deutero senario.
	//Prwto orisma einai to monadiko id tou kathe amaksiou.
	//Deutero orisma einai to ti tupou einai to amaksi dhladh ama erxetai apo deksia h aristera.	
	public synchronized void cross_save(int id,String type)
	{	
		//O xronos pou xreiazetai gia na perasei thn gefura.	
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
	
	
	
	
	//Methodos pou ulopoih to trito senario.
	//Prwto orisma einai to monadiko id tou kathe amaksiou.
	//Deutero orisma einai to ti tupou einai to amaksi dhladh ama erxetai apo deksia h aristera.	
	public synchronized void cross_alternately (int id,String type)
	{	
		//Oi grammes kwdika 81-102 aforoun to amaksi pou erxetai apo deksia kai se periptwsei
		//pou den einai seira tou tote 8a perimenei alliws ama einai seira tou h
		//den tha perasei allo amaksi apo thn allh meria proxwraei.
		if (type.equals("right_car"))
		{
			//ama einai to prwto amaksi pou perna thn gefyra orizei thn meblhth turn
			//mono mia fora oi opoia einai upeuthini na deixnei poianou seira einai na perasei.
			if (flag == 1)
			{
				turn = true;
				flag = 0;
			}
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
			
		//Oi grammes kwdika 107-128 aforoun to amaksi pou erxetai apo aristera kai se periptwsei
		//pou den einai seira tou tote 8a perimenei alliws ama einai seira tou h
		//den tha perasei allo amaksi apo thn allh meria proxwraei.
		if (type.equals("left_car")) 
		{	
			//ama einai to prwto amaksi pou perna thn gefyra orizei thn meblhth turn
			//mono mia fora oi opoia einai upeuthini na deixnei poianou seira einai na perasei.
			if (flag == 1)
			{
				turn = false;
				flag = 0;
			}
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
			
		//O xronos pou xreiazetai gia na perasei thn gefura.	
		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
				
				
		//Oi grammes kwdika 140-147 elexoun to amaksi pou perase apo poia meria hr8e
		//kai analogos allazei thn metablhth turn pou einai upeuthinh na 
		//kathorizei apo poias merias to amaksi einai seira na perasei.
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
			
		notifyAll();
		
	}
	
	
	
	

	//Methodos pou ulopoih to tetarto senario.
	//H leitourgia ths einai oti uparxei austhrh enallagh alla se periptwsei
	//pou se kapoia meria exoun mazeutei panw apo 5 amaksia tote tha feugoun 
	//sunexws amaksia apo authn thn meria kai as mhn einai kanonika h seira tous
	//mexria o arithmos twn amaksiwn sthn seira auth na einai 5.
	//Prwto orisma einai to monadiko id tou kathe amaksiou.
	//Deutero orisma einai to ti tupou einai to amaksi dhladh ama erxetai apo deksia h aristera.	
	public synchronized void cross_alternately_with_adjustment (int id,String type) 
	{
		//Oi grammes kwdika 183-206 aforoun to amaksi pou erxetai apo deksia kai se periptwsei
		//pou den einai seira tou tote 8a perimenei alliws proxwraei.
		//Auksanei +1 mia metblhth pou einai upeuthinei na metraei posa 
		//amaksia perimenoun sthn deksia meria
		if (type.equals("right_car"))
		{
			right_car_wait += 1;
			
			//ama einai to prwto amaksi pou perna thn gefyra orizei thn meblhth turn
			//mono mia fora oi opoia einai upeuthini na deixnei poianou seira einai na perasei.
			if (flag == 1)
			{
				turn = true;
				flag = 0;
			}
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
				
		//Oi grammes kwdika 208-235 aforoun to amaksi pou erxetai apo aristera kai se periptwsei
		//pou den einai seira tou tote 8a perimenei alliws proxwraei.
		//Auksanei +1 mia metblhth pou einai upeuthinei na metraei posa 
		//amaksia perimenoun sthn aristerh meria
		if (type.equals("left_car"))
		{
			left_car_wait += 1;
			
			//ama einai to prwto amaksi pou perna thn gefyra orizei thn meblhth turn
			//mono mia fora oi opoia einai upeuthini na deixnei poianou seira einai na perasei.
			if (flag == 1)
			{
				turn = false;
				flag = 0;
			}		
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
				
		//O xronos pou xreiazetai gia na perasei thn gefura.				
		try {
				Thread.sleep(1000);
			} 
		catch (InterruptedException e) {}
					
					
				
		//oi grammes kwdika 249-259 elexoun apo poia meria einai to amaksi
		//pou perna thn gefyra kai analogos meiwnei thn antoistoixh
		//metablhth pou apothikeuei twn arithmo twn amaksiwn pou perimenoun
		//se kathe meria kai auksanei thn metablhth poy krataei posa amaksia perasan apo thn gefura apo kathe meria.
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
			
					
					
		//Oi grammes kwdika 267-274 elexoun to amaksi pou perase apo poia meria hr8e
		//kai analogos me thn ulopoihsh tou senriou
		//allazei thn metablhth turn pou einai upeuthinh na 
		//kathorizei apo poias merias to amaksi einai seira na perasei.
		if (turn == true && (right_car_wait <= 5))
		{
			turn = false;
		}
		else if(turn == false  && (left_car_wait <= 5))
		{
			turn = true;
		}				
					
			
		notifyAll();
			
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
	
	
	
	
	
	
	
	
}
