/*
*              Author :- Poojan shah
*              Purpose:- Student management system
*              Date   :- 1st october,2020
*/

package dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import bean.StudentsBean;

public class StudentsDao implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String s_id;
	String s_name;
	String s_email;
	String s_pass;
	int s_age;
	int count=0;
	
	ArrayList<StudentsBean> studs=new ArrayList<StudentsBean>();
	StudentsBean sb=null;
	private boolean setName()
	{
		/*
		 * returns true if entered name is valid
		 * else returns false
		 */
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter name :: ");
		s_name=sc.next();
		

		Pattern p=Pattern.compile("[aA-zZ]*");
		Matcher m=p.matcher(s_name);
		Boolean b=m.matches();
		
		if(b==false)
		{
			System.out.println("Your name cound have alphabets from 'a/A'-'z/Z'");
		}
		
		return b;
	}
	private boolean setPass()
	{
		/*
		 * returns true if entered password is valid
		 *  else returns false
		 */
		Scanner sc=new Scanner(System.in);
		System.out.print("Eneter password :: ");
		s_pass=sc.next();
		
	
		Pattern p=Pattern.compile("^.*(?=.{8,10})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$");
		Matcher m=p.matcher(s_pass);
		Boolean b=m.matches();
		
		if(b==false)
		{
			System.out.println("Your password is not strong enough...");
			System.out.println("Password conditions : ");
			System.out.println("between 8-10 characters");
			System.out.println("minimum one special character");
			System.out.println("minimum one capital digit");
			System.out.println("minimum one integer value");
			System.out.println("Pease try again the sing up process");
		}
		
		return b;
	}
	
	private boolean setAge()
	{
		/*
		 * returns true if entered age is between 18-59
		 * else returns false
		 */
		Scanner sc=new Scanner(System.in);   
		System.out.print("Enter student age :: ");
		s_age=sc.nextInt();
		
		String age=Integer.toString(s_age);

		Pattern p=Pattern.compile("(1[89]|[2-5][0-9])");
		Matcher m=p.matcher(age);
		Boolean b=m.matches();

		if(b==false)
		{
			System.out.println("Invalid age...");
		}
		return b;
	}
	private boolean setEmail()
	{
		/*
		 * checks if the given email exists or not
		 * if doesn't exists then, 
		 * returns true if entered email is valid
		 * else returns false
		 */
		Scanner sc=new Scanner(System.in);   
		getData();
		System.out.print("Enter student email :: ");
		s_email=sc.next();
		
		
		boolean isthere=true;
		sb=new StudentsBean();
		for(int i=0;i<studs.size();i++)
		{
			sb=studs.get(i);
				
			if(s_email.equals(sb.getS_email()))
			{
				isthere=false;
				System.out.println("Email already exists...");
			}
		}
		Boolean b=false;
		if(isthere!=false)
		{
			Pattern p=Pattern.compile("[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]"
					+ "+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*");
			Matcher m=p.matcher(s_email);
			b=m.matches();
		
			if(b==false)
			{
				System.out.println("Invalid email...");
				System.out.println("Conditions : ");
				System.out.println("Should contain @");
				System.out.println("Cant enter '_'");
				System.out.println("Should have '.'");
			}
		}
		return b;
	}
	private void display(ArrayList<StudentsBean> sublist)
	{
		/*
		 * Displays the objects of the given List
		 */
		for(int i=0;i<sublist.size();i++)
		{
			sb=new StudentsBean();
			sb=sublist.get(i);
			System.out.println("********************************************");
			System.out.println("Student's name  : "+sb.getS_name());
			System.out.println("Student's email : "+sb.getS_email());
			System.out.println("Student's age   : "+sb.getS_age());
			System.out.println("Student's id    : "+sb.getS_id());
			System.out.println("********************************************");
		}
	}

	private void setData()
	{
		/*
		 * serializes the signed up list object
		 */
		try 
		{
			ObjectOutputStream oos =new ObjectOutputStream(new FileOutputStream("student.ser"));	
			oos.writeObject(studs);
			oos.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	private void getData()
	{
		/*
		 * gets the serialized data and adds each object to current list
		 */
		try 
		{
			ObjectInputStream ois=new ObjectInputStream(new FileInputStream("student.ser"));
			ArrayList<StudentsBean> list2=new ArrayList<>();
			sb=new StudentsBean();
			try
			{
				list2=(ArrayList<StudentsBean>) ois.readObject();
			} 
			catch (ClassNotFoundException e)
			{
					e.printStackTrace();
			}
			ois.close();
			sb=new StudentsBean();
			for(int i=0;i<list2.size();i++)
			{
				sb=list2.get(i);
				studs.add(sb);
			}
		} 
		catch (FileNotFoundException e){}
		catch (IOException e){} 
		
	}
	public void signUp()
	{
		/*
		 * checks if the given conditions are true
		 * sets the student bean object in list 
		 */
		if(setName() &&  setEmail() && setPass() && setAge())
		{
			Scanner sc=new Scanner(System.in);
			System.out.print("Enter student id :: ");
			s_id=sc.next();
			
			sb=new StudentsBean();
			sb.setS_age(s_age);
			sb.setS_email(s_email);
			sb.setS_id(s_id);
			sb.setS_name(s_name);
			sb.setS_pass(s_pass);
			
			studs.add(sb);
			setData();
		}
		else
		{
			System.out.println("Sign up failed...");
		}
	}
	
	public boolean logIn()
	{
		/*
		 * matches the entered data with the list data
		 */
		//getData();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter student email : ");
		s_email=sc.next();
		System.out.print("Enter student pass  : ");
		s_pass=sc.next();
		
		//getting data into list
		if(count==0)
		{
			getData();
			count++;
		}
		sb=new StudentsBean();
		for(int i=0;i<studs.size();i++)
		{	
			sb=studs.get(i);
			
			if(s_email.equals(sb.getS_email()) && s_pass.equals(sb.getS_pass()))
			{
				return true;
			}
		}
		return false;
	}
	public void delete()
	{
		/*
		 * checks the email and password entered 
		 * if exists then deletes the data
		 */

		Scanner sc=new Scanner(System.in);
		System.out.print("Confirm your email : ");
		s_email=sc.next();
		System.out.print("Confirm password   : ");
		s_pass=sc.next();
		
		sb=new StudentsBean();
		
		for(int i=0;i<studs.size();i++)
		{
			sb=studs.get(i);
			
			if(s_email.equals(sb.getS_email()) && s_pass.equals(sb.getS_pass()))
			{
				studs.remove(i);
				System.out.println("Student Removed");
				setData();
				break;
			}
		}
	}
	
	public void update()
	{
		/*
		 * checks the email and password entered 
		 * if exists then updates the data
		 */
		Scanner sc=new Scanner(System.in);
		System.out.print("Confirm your email : ");
		s_email=sc.next();
		System.out.print("Confirm password   : ");
		s_pass=sc.next();
			
		sb=new StudentsBean();
		for(int i=0;i<studs.size();i++)
		{
			sb=studs.get(i);
			if(s_email.equals(sb.getS_email()))
			{
				if(setName() &&  setEmail() && setPass() && setAge())
				{
					System.out.print("Enter student id :: ");
					s_id=sc.next();
					
					sb.setS_age(s_age);
					sb.setS_email(s_email);
					sb.setS_id(s_id);
					sb.setS_name(s_name);
					sb.setS_pass(s_pass);
					studs.set(i,sb);
					setData();
				}
			}
		}
	}
	
	public void dataByName()
	{
		ArrayList<StudentsBean> sublist=new ArrayList<StudentsBean>();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Student name : ");
		String s_name=sc.next();
		sb=new StudentsBean();
		
		for(int i=0;i<studs.size();i++)
		{
			sb=studs.get(i);
			
			if(s_name.equals(sb.getS_name()))
			{
				sublist.add(sb);
			}
		}
		display(sublist);
	}
	public void dataBetweenAge()
	{
		ArrayList<StudentsBean> sublist=new ArrayList<StudentsBean>();
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter minimum age : ");
		int min_age=sc.nextInt();
		System.out.print("Enter maximum age : ");
		int max_age=sc.nextInt();
		
		sb=new StudentsBean();
		for(int i=0;i<studs.size();i++)
		{
			sb=studs.get(i);
		
			if(min_age<sb.getS_age() && sb.getS_age()<max_age)
			{
				sublist.add(sb);
			}		
		}
		display(sublist);
	}
}