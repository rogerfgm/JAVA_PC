package srm636;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class ChocolateDividingEasy {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int findBest(String[] cs){
		int a = 0;
		int R = cs.length;
		int C = cs[0].length();
		int[][] d = new int[R][C];
		for(int i = 0; i < R; i++){
			String s = cs[i];
			for(int j = 0; j < C; j++){
				d[i][j] = s.charAt(j) - '0';
			}
		}
		int[][][][] sum = new int[R][C][R][C];
		sum[0][0][0][0] = d[0][0];
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				for(int k = i; k < R; k++){
					for(int l = j; l < C; l++){
						if(i == k && j == l){
							sum[i][j][k][l] = d[i][j];
						}
						else if(i == k){
							sum[i][j][k][l] = sum[i][j][k][l-1] + d[k][l];
						}
						else if(j == l){
							sum[i][j][k][l] = sum[i][j][k-1][l] + d[k][l];
						}
						else{
							sum[i][j][k][l] = sum[i][j][k][l-1] + sum[i][j][k-1][l] - sum[i][j][k-1][l-1] + d[k][l];
						}
					}
				}
			}
		}
		
		for(int r2 = 1; r2 < R-1; r2++){
			for(int r3 = r2+1; r3 < R; r3++){
				for(int c2 = 1; c2 < C -1; c2++){
					for(int c3 = c2+1; c3 < C; c3++){
						int v0 = sum[0][0][r2-1][c2-1];
						int v1 = sum[0][c2][r2-1][c3-1];
						int v2 = sum[0][c3][r2-1][C-1];
						int v3 = sum[r2][0][r3-1][c2-1];
						int v4 = sum[r2][c2][r3-1][c3-1];
						int v5 = sum[r2][c3][r3-1][C-1];
						int v6 = sum[r3][0][R-1][c2-1];
						int v7 = sum[r3][c2][R-1][c3-1];
						int v8 = sum[r3][c3][R-1][C-1];
						
						int V = INF;
						V = Math.min(V, v0);
						V = Math.min(V, v1);
						V = Math.min(V, v2);
						V = Math.min(V, v3);
						V = Math.min(V, v4);
						V = Math.min(V, v5);
						V = Math.min(V, v6);
						V = Math.min(V, v7);
						V = Math.min(V, v8);
						
						a = Math.max(a, V);
					}
				}
			}
		}
		
		return a;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChocolateDividingEasy t = new ChocolateDividingEasy();
		String[] c = {
				"36753562",
				"91270936",
				"06261879",
				"20237592",
				"28973612",
				"93194784"
				};
		int r = t.findBest(c);
		out.println(r);
	}

}
