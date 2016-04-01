package com.psl.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class FetchManager {
	ArrayList<String> fname=new ArrayList<String>();
	ArrayList<String> fpath=new ArrayList<String>();
	Rule r=new Rule();
	Set<String> s=new TreeSet<String>();
	MyLogger ml=new MyLogger();
	public ArrayList<String> traverse(File parentNode,String leftIndent)
	{

		if(parentNode.isDirectory())
		{
			
			leftIndent+=" ";// creating tree like structure
			File childNodes[]=parentNode.listFiles();// childNode array contains all files in parentnode
			for(int i=0;i<childNodes.length;i++) // for(File childnodes : cn)
				traverse(childNodes[i],leftIndent); // traverse(cn,leftIndent);
				
		}
		else{
			
			boolean b=parentNode.getName().endsWith("java");
			
			if(b)
			{
				fname.add(parentNode.getName());
				fpath.add(parentNode.getAbsolutePath());
			}
		}
		
		return fname;
		
	}
	public void reader(ArrayList<String> fname)
	{
		
		// Creating a Set which contains all data-types
		s.add("int");s.add("boolean");s.add("float");s.add("double");s.add("long");s.add("String");
		
		String line="";//initializing line
		BufferedReader br=null;
		
		//Traversing through all the Files
		for(int i=0;i<fpath.size();i++)
		{
			int lineNo=0;// counting lineNo in the File
			String x=fpath.get(i);// getting path of ith File
			
			try
			{
				
				br=new BufferedReader(new FileReader(x));// new FileReader
				
				try 
				{
											
					while((line=br.readLine())!=null)
					{
						
						lineNo++;//incrementing line Count
						ArrayList<String> al=splitMe(line);// Getting Individual Strings from File Line
						 int result=0;
						
						 //Iterating through the 1 String line of the File
						 for(int k=0;k<al.size();k++)
						{
							
							//Checking for datatype match
							 if(s.contains(al.get(k)))
							{
								if((k+1)<al.size())
								{
								
									//Storing violated RuleNo if any
									result=r.CheckViolation(al.get(k+1));
									
									//Rule has been violated
									if(result>0)
									{
										// Sending violated statement to Log File
										ml.logViolation(fname.get(k),al.get(k), lineNo, al.get(k+1),result);
									}
								}
							}
							

							
						}//for-loop ends
						
						
					}
					
			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			finally
			{
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
	
		
		
	}
	private  ArrayList<String> splitMe(String line)
	{
		String s="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz$_";
		ArrayList<String> al_str=new ArrayList<String>();
		
		//iterating through line
		for(int i=0;i<line.length();i++)
	 	{

			// Reach to the begining of the word
			int beignIndex=0;
			int endIndex=0;
			int j=0;
			j=i;// starting from the last occured Endindex
			
			//Checking BeginIndex
			for(;j<line.length();j++)
			{
			     // Check whether it is a valid character
			     int index =  s.indexOf(line.charAt(j));
			     
			     if(index>=0)
			    	 break;
			}
			
			beignIndex = j;// we found the begining of the string which is jth index
			
			// Collect word and check the end of the word
	        for(;j<line.length();j++)
			{
			     
	        	// Check whether it is a valid character
			     int index =  s.indexOf(line.charAt(j));
			     if(index<0)
			    	 break;
			}
	        
			endIndex = j;// the end index 
			String string=line.substring(beignIndex, endIndex);// returning valid string
		
			//Checking whether a sting contains a valid data
			if(!(string.equals("")))
				al_str.add(string);
		
			i=endIndex;// Now beginning from the end Index as we got (begin-end)String
	 	}
		
		return al_str;
	}


}
