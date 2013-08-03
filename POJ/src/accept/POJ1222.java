package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class POJ1222 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	int R = 5;
	int C = 6;
	boolean[][] d = null;
	public void solve() throws Exception{

		d = new boolean[R][C];
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				int t = sc.nextInt();
				if(t > 0){
					d[i][j] = true;
				}
			}
		}
		
		int s = 0;
		for(int j = 0; j < C; j++){
			if(d[0][j]){
				s |= 1 << j;
			}
		}
		for(int i = 0; i < 1 << C; i++){
			int p = i;
			int ns = s;
			for(int j = 0; j < C; j++){
				if( (p & (1 << j)) > 0){
					ns = ns ^ (1 << j);
					if(j > 0){
						ns = ns ^ (1 << (j-1));
					}
					if(j < C-1){
						ns = ns ^ (1 << (j+1));
					}
				}
			}
			
			
			
			int[] ret = check(1, i, ns);
			if(ret != null){
				ret[0] = p;
				for(int j = 0; j < ret.length; j++){
					int rp = ret[j];
					for(int k = 0; k < C; k++){
						if(k != 0) out.print(" ");
						int ans = rp & (1 << k);
						if(ans > 0){
							out.print("1");
						}
						else{
							out.print("0");
						}
						
					}
					out.println();
				}
				
				return;
			}
		}
		
	}
	
	int[] check(int idx, int p, int ps){		
		int ns = 0;
		for(int j = 0; j < C; j++){
			if(d[idx][j]){
				ns |= 1 << j;
			}
		}
		
		for(int j = 0; j < C; j++){
			if( (p & (1 << j)) > 0){
				ns = ns ^ (1 << j);
			}
		}
		
		int np = 0;
		for(int i = 0; i < C; i++){
			if( (ps & (1 << i)) > 0){
				np |= 1 << i;
				ns = ns ^ (1 << i);
				if(i > 0){
					ns = ns ^ (1 << (i-1));
				}
				if(i < C-1){
					ns = ns ^ (1 << (i+1));
				}
			}
		}
		
		if(idx == R-1){
			if(ns == 0){
				int[] ret = new int[R];
				ret[R-1] = np;
				return ret;
			}
			else{
				return null;
			}
		}
		
		
		int[] ret =  check(idx+1, np, ns);
		if(ret != null){
			ret[idx] = np;
		}
		return ret;
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
		POJ1222 p = new POJ1222();
		N = sc.nextInt();
		int t = 1;
		while(t <= N){
			out.println("PUZZLE #" + t);
			p.solve();
			
			t++;
		}
	}

}
