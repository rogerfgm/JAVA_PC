package srm595;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

public class TLEMLELittleElephantAndRGB {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	char G = 'G';
	public long getNumber(String[] list, int M) {
		long a = 0;
		String s = "";
		for(int i = 0; i < list.length; i++){
			s += list[i];
		}
		N = s.length();
		char[] cs = s.toCharArray();
		
		long[][] dplc = new long[N][M+1]; // con

		if(cs[0] == G) {
			dplc[0][1] = 1;
		}
		else{
			dplc[0][0] = 1;
		}
		
		for(int i = 1; i < N; i++){
			dplc[i][M] = dplc[i-1][M];
			if(cs[i] == G){
				dplc[i][1] += 1; // jibun nomi
				for(int j = 1; j <= M; j++){
					dplc[i][j] += dplc[i-1][j-1];
				}
			}
			else{
				dplc[i][0] += 1;
				for(int j = 0; j < M; j++){
					dplc[i][0] += dplc[i-1][j];
				}
			}
		}
		
		long[][] dprc = new long[N][M+1]; // con

		if(cs[N-1] == G) {
			dprc[N-1][1] = 1;
		}
		else{
			dprc[N-1][0] = 1;
		}
		
		for(int i = N-2; i >= 0; i--){
			dprc[i][M] = dprc[i+1][M];
			if(cs[i] == G){
				dprc[i][1] += 1; // jibun nomi
				for(int j = 1; j <= M; j++){
					dprc[i][j] += dprc[i+1][j-1];
				}
			}
			else{
				dprc[i][0] += 1;
				for(int j = 0; j < M; j++){
					dprc[i][0] += dprc[i+1][j];
				}
			}
		}
		
		long[][] dpr = new long[N][M+1];
		for(int i = 0; i < N; i++){
			dpr[i][M] = dprc[i][M];
			for(int j = M-1; j >= 0; j--){
				dpr[i][j] += dpr[i][j+1] + dprc[i][j];
			}
		}
		
		
		
		for(int i = 0; i < N-1; i++){
			a += dplc[i][M] * (ncl(N-i-1, 2)+N-i-1);
			for(int j = 0; j < M; j++){ // left
				for(int k = i+1; k < N; k++){
					int enough = M - j;
					a += dplc[i][j] * dpr[k][enough];
				}
			}
		}
		
		return a;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TLEMLELittleElephantAndRGB t = new TLEMLELittleElephantAndRGB();
		String[] in = {"GRGGGRBRGG", "GGGGGGGG", "BRGRBRB"};
		int m  = 4;
		long r = t.getNumber(in, m);
		out.println(r);
	}
	long ncl(int n, int c){
		if(n < c){
			return 0;
		}
		if(n-c < c){
			c = n-c;
		}
		
		long ret = 1;
	    for (int k = 0; k < c; k++) {
	        ret = ret * (n-k) / (k+1);
	    }
		
		return ret;
	}
}
