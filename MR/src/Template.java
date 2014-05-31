import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;


public class Template {

	
	static PrintStream err = null;
	static long progtime = 0;
	
	
	Random rnd = new Random();
	
	public int init(){
		
		progtime = 0;
		long start = System.currentTimeMillis();

		
		
		progtime += System.currentTimeMillis() - start;
		return 0;
	}
	
	public Object[] next(){
		long start =  System.currentTimeMillis();

		Object[] ret = null;
		try{
			
			
			
		}
		catch(Exception ex){
			err.println(ex.getMessage());
		}
		
		progtime += System.currentTimeMillis() - start;
		return ret;
	}
	
	

	
	

	
	
	public static void main(String[] args) throws Exception{
		Template s = new Template();
		err = System.err;



		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	    s.init();

	    bw.close();
	    br.close();
	    err.println("time : " + progtime);
	}

}
