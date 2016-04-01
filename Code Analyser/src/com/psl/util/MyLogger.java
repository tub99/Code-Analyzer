package com.psl.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MyLogger 
{
	ArrayList<String> al_file=new ArrayList<String>();
	public void logViolation(String filename,String datatype,int lineNo,String var,int vruleNo)
	{
		//input
		BufferedWriter br=null;
		String ip="Error In Filename: "+filename+" in line no: "+lineNo+" due to variable: "+var+" of datatype: "+datatype +" violating ruleNo: "+vruleNo+"\n"+"\n";
		al_file.add(ip);// Storing Errors one by one
		String logFile="E:/Eclipse_for java_workspace/Code Analyser/LogFile.txt";// Target-write File-LogFile.txt
		
		
		
		
		
		try {
			br=new BufferedWriter(new FileWriter(logFile));// Open the file in append mode
			for(String x:al_file)
				br.write(x);// Append the lines from individual ArrayList Index
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				br.close();// Close the file
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
			
	}
	
}
