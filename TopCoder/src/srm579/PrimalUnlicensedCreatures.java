package srm579;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class PrimalUnlicensedCreatures {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	public int maxWins(int l, int[] g){
		boolean[] f = new boolean[g.length];
		int N = g.length;
		int ans = 0;
		while(true){
			boolean fl = false;
			for(int i = 0; i < N; i++){
				if(f[i]) continue;
				if(l > g[i]){
					f[i] = true;
					l += g[i] / 2;
					fl = true;
					ans++;
				}
			}
			
			
			if(!fl){
				break;
			}
		}
		
		return ans;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrimalUnlicensedCreatures t = new PrimalUnlicensedCreatures();

		int i = 20;
		int[] g = {3, 3, 3, 3, 3, 1, 25 };
		int ret = t.maxWins(i, g);
		out.println(ret);
	}

}
