package hfdhfd;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		int priority = 0;
		bridge bridge = new bridge();   
		   
		right_car[] r_c = new right_car[100];
		left_car[] l_c = new left_car[100];
		  
		Scanner input = new Scanner(System.in);
		System.out.println("��� �� ������� �� ������� ��� ������ �������������� ��� ��� ���� �������� ��������.");
		System.out.println("������� �����: 1");
		System.out.println("������� ������: 2");
		System.out.println("������� �����: 3");
		System.out.println("������� �������: 4");		
		int scenario = input.nextInt();

		
		System.out.println("���� ������ ������ �� ������ ��� ��������;");
		int number_of_left_cars = input.nextInt();
		bridge.setLeft_cars_will_pass(number_of_left_cars);
		
		System.out.println("���� ������ ������ �� ������ ��� �����;");
		int number_of_right_cars = input.nextInt();
		bridge.setRight_cars_will_pass(number_of_right_cars);
		
	
		System.out.println("                                             Bridge");
		   
		   
		    
		    
		
		//Sths grammes kwdika dimiourgei ta threads
		//gia ta orismata pou pernaw sta threads deita ta sxolia
		//twn kataskeuastwn twn klasewn.
		for (int i=0; i<number_of_left_cars; i++) 
		{
			l_c[i] = new left_car(bridge,i+1,scenario);
		}
  
		for (int i=0; i<number_of_right_cars; i++)
		{
			r_c[i] = new right_car(bridge,i+1,scenario);
		}

		
		
		//sths grammes kwdika trexei ta threads.
		for (int i=0;i<number_of_left_cars;i++)
		{
			l_c[i].start(); 
		}
    
		for (int i=0;i<number_of_right_cars;i++)
		{
			r_c[i].start();
		}

	
	}
}

