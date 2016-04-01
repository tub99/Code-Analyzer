package com.psl;

import java.io.File;
import java.util.ArrayList;


import com.psl.util.FetchManager;

public class Client {
	public static void main(String a[])
	{
		FetchManager fm=new FetchManager();
		
		ArrayList<String> fpt=new ArrayList<String>();
		File fp=new File("E:/Eclipse_for java_workspace/Test_case");
		fpt=fm.traverse(fp,"");
		fm.reader(fpt);
		//cc.generateCheckers();
		
	}

}
