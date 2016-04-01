package com.psl.util;

public class Rule 
{
	public int CheckViolation(String var)
	{
		int ret = 0;
		// Check rule 1
		ret = rule1(var);
		if(ret > 0) return ret;
		
		// Check rule 2
		ret = rule2(var);
		if(ret > 0) return ret;
		
		// Check rule 3
		ret = rule3(var);
		if(ret > 0) return ret;
		
		return ret;
		
	}
	
	// Rule 1: First Character should not be any upper-case
	private int rule1(String var)
	{
		int rule=0;
		
		
		if(Character.isUpperCase(var.charAt(0)))
				 rule=1;
					
		return rule;
		
	}
	// 
	private int rule2(String var)
	{
		int rule=0;
		for(int i=0;i<var.length();i++)
			if(var.charAt(i)=='$')
			{
				rule=2;
				break;
			}
			
		return rule;
	
	}
	
	private int rule3(String var)
	{
		int rule=0;
		for(int i=0;i<var.length();i++)
			if(var.charAt(i)=='_')
			{
				rule=3;
				break;
			}
			
		return rule;
	}
}
