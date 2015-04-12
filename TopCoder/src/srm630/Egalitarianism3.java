package srm630;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class Egalitarianism3 {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	int[][] D = null;
	int[][] d = null;
	public int maxCities(int n, int[] a, int[] b, int[] len){
		if(n == 1) return 1;
		if(n == 2) return 2;
		 D = new int[n][n];
		N = n;
		for(int i = 0; i < n-1; i++){
			a[i]--;
			b[i]--;
		}
		d = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i == j){
					d[i][j] = 0;
				}
				else{
					d[i][j] = INF;
				}
			}
		}
		for(int i = 0; i < a.length; i++){
			int f = a[i];
			int t = b[i];
			d[f][t] = d[t][f] = len[i];
			D[f][t] = D[t][f] = len[i];
		}
		
		for(int k = 0; k < n; k++){
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}
		
		int ans = 2;
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(D[i][j] > 0){
					boolean[] used = new boolean[n];
					used[i] = used[j] = true;
					Set<Integer> dist = new HashSet<Integer>();
					dist.add(D[i][j]);
					find(dist, D[i][j], j, used);
					for(int ckd : dist){
						int cnt = 1;
						for(int k = 0; k < n; k++){
							if(!used[k] && D[i][k] > 0){
								boolean[] newusd = new boolean[n];
								for(int m = 0; m < n; m++){
									newusd[m] = used[m];
								}
								newusd[k] = true;
								if(has(k, ckd, newusd, D[i][k])){
									cnt++;
								}
							}
						}
						if(cnt > ans){
							ans = cnt;
						}
					}
				}
			}
		}
		
		
		return ans;
	}
	
	boolean has(int p, int ckd, boolean[] usd, int dst){
		if(dst == ckd) return true;
		if(dst > ckd){
			return false;
		}
		boolean f = false;
		for(int i = 0; i < N; i++){
			if(!usd[i] && D[p][i] > 0){
				usd[i] = true;
				f |= has(i, ckd, usd, dst + D[p][i]);
			}
		}
		return f;
	}
	
	void find(Set<Integer> dist, int c, int p, boolean[] used){
		for(int i = 0; i < N; i++){
			if(!used[i] && D[p][i] > 0){
				int nd = c + D[p][i];
				dist.add(nd);
				used[i] = true;
				find(dist, nd, i, used);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Egalitarianism3 t = new Egalitarianism3();
		int n = 6;
		int[] a = {1,2,3,2,3};
		int[] b = {2,3,4,5,6};
		int[] len = {2,1,3,2,3};
//		int n = 4;
//		int[] a = {1,1,1};
//		int[] b = {2,3,4};
//		int[] len = {1,1,1};
		int r = t.maxCities(n, a, b, len);
		out.println(r);
	}

}
