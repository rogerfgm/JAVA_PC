package gcj2013qual;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class A {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	public void solve() throws Exception{
		char[][] c = new char[4][4];
		char X = 'X';
		char O = 'O';
		char P = '.';
		String xwon = "X won";
		String owon = "O won";
		String notcomp = "Game has not completed";
		String draw = "Draw";
		int pn = 0;
		for(int i = 0; i < 4; i++){
			String s = sc.next();
			for(int j = 0; j < 4; j++){
				c[i][j] = s.charAt(j);
				if(c[i][j] == '.') pn++;
			}
		}
		for(int i = 0; i < 4; i++){
			boolean fx = true;
			boolean fo = true;
			for(int j = 0; j < 4; j++){
				if(c[i][j] == P){
					fx = fo = false;
				}
				else if(c[i][j] == X){
					fo = false;
				}
				else if(c[i][j] == O){
					fx = false;
				}
			}
			if(fx){
				out.println(xwon);
				return;
			}
			else if(fo){
				out.println(owon);
				return;
			}
		}
		for(int i = 0; i < 4; i++){
			boolean fx = true;
			boolean fo = true;
			for(int j = 0; j < 4; j++){
				if(c[j][i] == P){
					fx = fo = false;
				}
				else if(c[j][i] == X){
					fo = false;
				}
				else if(c[j][i] == O){
					fx = false;
				}
			}
			if(fx){
				out.println(xwon);
				return;
			}
			else if(fo){
				out.println(owon);
				return;
			}
		}
		{
			boolean fx = true;
			boolean fo = true;
			for(int i = 0; i < 4; i++){
				if(c[i][i] == P){
					fx = fo = false;
				}
				else if(c[i][i] == X){
					fo = false;
				}
				else if(c[i][i] == O){
					fx = false;
				}
			}
			if(fx){
				out.println(xwon);
				return;
			}
			else if(fo){
				out.println(owon);
				return;
			}
		}
		{
			boolean fx = true;
			boolean fo = true;
			for(int i = 0; i < 4; i++){
				if(c[i][3-i] == P){
					fx = fo = false;
				}
				else if(c[i][3-i] == X){
					fo = false;
				}
				else if(c[i][3-i] == O){
					fx = false;
				}
			}
			if(fx){
				out.println(xwon);
				return;
			}
			else if(fo){
				out.println(owon);
				return;
			}
		}
		
		if(pn == 0){
			out.println(draw);
			return;
		}
		out.println(notcomp);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("A-large.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		A b = new A();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
	}
}
