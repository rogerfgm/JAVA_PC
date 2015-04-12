import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class TaroJiroGrid {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int getNumber(String[] g){
		N = g.length;
		
		
		if(!has(g)){
			return 0;
		}
		String w = "";
		String b = "";
		for(int i = 0; i < N; i++){
			w += "W";
			b += "B";
		}
		
		for(int i = 0; i < N; i++){
			String[] ng = new String[N];
			for(int j = 0; j < N; j++){
				ng[j] = g[j];
			}
			ng[i] = w;
			if(!has(ng)){
				return 1;
			}
			ng[i] = b;
			if(!has(ng)){
				return 1;
			}
		}
		
		return 2;
	}
	
	boolean has(String[] g){
		boolean has = false;
		for(int i = 0; i < N; i++){
			int cnt = 0;
			char c = 'A';
			int max = 0;
			for(int j = 0; j < N; j++){
				char n = g[j].charAt(i);
				if(n == c){
					cnt++;
				}
				else{
					cnt = 1;
					c = n;
				}
				max = Math.max(cnt, max);
			}
			if(max > N / 2){
				has = true;
			}
		}
		return has;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TaroJiroGrid t = new TaroJiroGrid();
		String[] g = {"WBBWBB",
				 "BBWBBW",
				 "WWBWBW",
				 "BWWBBB",
				 "WBWBBW",
				 "WWWBWB"};
		int r = t.getNumber(g);
		out.println(r);
	}

}
