package giveup;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// 凸包という括りをみてしまった。。。がわからない
public class POJ3689 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	public void solve() throws Exception{
		
		
	}


	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ3689 p = new POJ3689();
		while(true){
			
			try{
				N = sc.nextInt();
				M = sc.nextInt();
				if(N == 0 && M == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
		
			p.solve();
		}
	}

}
