package bean;

import java.io.Serializable;

public class StudentsBean implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String s_id;
	private String s_name;
	private String s_email;
	private String s_pass;
	private int s_age;
	
	public String getS_id() 
	{
		return s_id;
	}
	
	public void setS_id(String s_id)
	{
		this.s_id = s_id;
	}
	
	public String getS_name()
	{
		return s_name;
	}
	
	public void setS_name(String s_name) 
	{
		this.s_name = s_name;
	}
	
	public String getS_email() 
	{
		return s_email;
	}
	
	public void setS_email(String s_email)
	{
		this.s_email = s_email;
	}
	
	public String getS_pass() 
	{
		return s_pass;
	}
	
	public void setS_pass(String s_pass)
	{
		this.s_pass = s_pass;
	}
	
	public int getS_age() 
	{
		return s_age;
	}
	
	public void setS_age(int s_age)
	{
		this.s_age = s_age;
	}
}