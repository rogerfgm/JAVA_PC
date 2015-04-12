import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class TaroGrid {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int getNumber(String[] gs){
		int ans = 0;
		N = gs.length;
		
		for(int i = 0; i < N; i++){
			char p = 'A';
			int cnt = 0;
			for(int j = 0; j < N; j++){
				char c = gs[j].charAt(i);
				if(c == p){
					cnt++;
				}
				else{
					cnt = 1;
					p = c;
				}
				ans = Math.max(ans, cnt);
			}
		}
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TaroGrid t = new TaroGrid();

		out.println();
	}

}
