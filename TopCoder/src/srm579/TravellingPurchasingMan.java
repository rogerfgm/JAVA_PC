package srm579;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;


// 入れ忘れてたdp入れた
public class TravellingPurchasingMan {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;

	int me = 0;
	int[][] dp = null;
	int IN = 0;
	In[] is = null;
	int[][] d = null;
	
	public int maxStores(int N, String[] in, String[] rs){
		
		d = new int[N][N];
		for(int i =0; i < N; i++){
			for(int j = 0; j < N; j++){
				if(i != j) d[i][j] = INF;
			}
		}
		
		for(String s : rs){
			String[] ss  = s.split(" ");
			int f = Integer.parseInt(ss[0]);
			int t = parseInt(ss[1]);
			int dst = parseInt(ss[2]);
			d[f][t] = d[t][f] = min(dst, d[t][f]);
		}
		
		for(int k = 0; k < N; k++){
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}
		
		is = new In[in.length];
		me = 0;
		for(int i = 0; i < in.length; i++){
			String[] ss  = in[i].split(" ");
			int s = parseInt(ss[0]);
			int e = parseInt(ss[1]);
			int d = parseInt(ss[2]);
			is[i] = new In(s, e, d);
			me = max(me, e);
		}
		IN = in.length;

		dp = new int[IN][1<<IN];
		for(int i = 0; i < IN; i++){
			for(int j = 0; j < 1 << IN; j++){
				dp[i][j] = INF;
			}
		}
		
		int ret = 0;
		for(int i = 0; i < IN; i++){
			ret = max(ret, check(i, 0, d[N-1][i]));
		}
		
		return ret;
	}
	
	int check(int idx, int p, int t){
		if(t > is[idx].ed){
			return 0;
		}

		if(dp[idx][p] <= t){
			return 0;
		}
		dp[idx][p] = t;
		
		int nt = max(t, is[idx].st);
		nt += is[idx].du;
		
		int np = p | 1 << idx;
		
		int ret = 0;
		for(int i = 0; i < IN; i++){
			if((np & 1 << i) == 0 && nt + d[idx][i] <= is[i].ed ){
				ret = max(ret,  check(i, np, nt + d[idx][i]));
			}
		}
		ret++;
		
		return ret;
	}
	
	class In{
		public In(int s, int e, int d){
			st = s;
			ed = e;
			du = d;
		}
		int st = 0;
		int ed = 0;
		int du = 0;
	}
	
	class Data{
		public Data(int t, int d){
			this.t= t;
			this.d = d;
		}
		int t = 0;
		int d = 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TravellingPurchasingMan t = new TravellingPurchasingMan();
		/*
		 * 
		 * 


		 */
//		String[] in = {"1 10 10" , "1 55 30", "10 50 100" };
//		String[] in2 = {"1 2 10"};
//		int r = t.maxStores(3, in, in2);
		
		
		String[] in = {"0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000", "0 100000 1000"};
		String[] in2 = {"0 1 1000", "1 2 1000", "2 3 1000", "3 4 1000", "4 5 1000", "5 6 1000", "6 7 1000", "7 8 1000", "8 9 1000", "9 10 1000", "10 11 1000", "11 12 1000", "12 13 1000", "13 14 1000", "14 15 1000", "15 16 1000", "16 17 1000", "17 18 1000", "18 19 1000", "19 20 1000", "20 21 1000", "21 22 1000", "22 23 1000", "23 24 1000", "24 25 1000", "25 26 1000", "26 27 1000", "27 28 1000", "28 29 1000", "29 30 1000", "30 31 1000", "31 32 1000", "32 33 1000", "33 34 1000", "34 35 1000", "35 36 1000", "36 37 1000", "37 38 1000", "38 39 1000", "39 40 1000", "40 41 1000", "41 42 1000", "42 43 1000", "43 44 1000", "44 45 1000", "45 46 1000", "46 47 1000", "47 48 1000", "48 49 1000", "49 0 1000"};
		int r = t.maxStores(50, in, in2);
		out.println(r);
	}

}
