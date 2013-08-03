package gcj20131B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Bsmall {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	int h = 0;
	double P = 0;
	int y = 0;
	
	public void solve() throws Exception{
		int N = sc.nextInt();
		int X = sc.nextInt();
		int Y = sc.nextInt();
		P = 0;
		
		if(X == 0 && Y == 0){
			out.println("1.0");
			return;
		}
		else if(abs(X) + Y == 2){
			if(N >= 6){
				out.println("1.0");
				return;
			}
			else{
				if(X == 0){
					out.println("0.0");
					return;
				}
				else{
					N--;
					h = 2;
					int[] st = new int[2];
					y = Y+1;
					check(st, 1, N);
				}
			}
		}
		else if(abs(X) + Y == 4){
			if(N >= 15){
				out.println("1.0");
				return;
			}
			else{
				if(X == 0){
					out.println("0.0");
					return;
				}
				else{
					N-= 6;
					h = 4;
					int[] st = new int[2];
					y = Y+1;
					check(st, 1, N);
				}
			}
		}
		else if(abs(X) + Y == 6){
			
			if(X == 0){
				out.println("0.0");
				return;
			}
			else{
				N-= 15;
				h = 6;
				int[] st = new int[2];
				y = Y+1;
				check(st, 1, N);
			}
			
		}

		out.println(P);
			
	}
	
	void check(int[] st, double p, int n){
		if(n <= 0){
			if(st[0] >= y){
				P += p;
			}
			return;
		}
		
		if(st[0] == h){
			int[] nst = create(st);
			nst[1]++;
			check(nst, p, n-1);
		}
		else if(st[1] == h){
			int[] nst = create(st);
			nst[0]++;
			check(nst, p, n-1);
		}
		else{
			int[] nst = create(st);
			nst[0]++;
			check(nst, p * 0.5, n-1);
			nst = create(st);
			nst[1]++;
			check(nst, p * 0.5, n-1);
		}

	}
	
	int[] create(int[] st){
		int[] nst = new int[2];
		nst[0] = st[0];
		nst[1] = st[1];
		return nst;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("B-small-attempt0.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		out = new PrintWriter(new FileWriter(new File("output.txt")));
		
		Bsmall b = new Bsmall();
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
