package controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import dao.StudentsDao;

public class StudentsController
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		StudentsDao sd=new StudentsDao();
		while(true)
		{
			start:
			try
			{
				System.out.println("*******************************");
				System.out.println("1---------Sign up");
				System.out.println("2---------Sign in");
				System.out.println("3---------Exit");
				System.out.print("\nEnter choice :: ");
				int choice=sc.nextInt();
				
				switch(choice)
				{
				case 1:
					sd.signUp();
					break;
				case 2:
					if(sd.logIn())  
					{
						while(true)
						{
							System.out.println("**********************************************");
							System.out.println("::::::::::::::::::::MENU::::::::::::::::::::::");
							System.out.println("**********************************************");
							System.out.println("1---------update");
							System.out.println("2---------delete");
							System.out.println("3---------get data by name");
							System.out.println("4---------get data between(age groups)");
							System.out.println("5---------go back to main page");
							System.out.println("6---------Exit");
							System.out.println("\nEnter choice :: ");
							int c=sc.nextInt();
							
							switch(c)
							{
							case 1:
								sd.update();
								break start;
							case 2:
								sd.delete();
								break start;
							case 3:
								sd.dataByName();
								break;
							case 4:
								sd.dataBetweenAge();
								break;
							case 5:
								break start;
							case 6:
								System.exit(0);
							}
						}
					}
					else
					{
						System.out.println("Login failed...try again");
					}
					break;
				case 3:
					System.exit(0);
				}
			}	
			catch(InputMismatchException e)
			{
				System.out.println("Please choice from '1','2','3' options");
				break ;
			}
		}
	}
}