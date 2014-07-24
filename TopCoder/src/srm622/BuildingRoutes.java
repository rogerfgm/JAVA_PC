package srm622;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class BuildingRoutes {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public 	int build(String[] ds, int T){
		int ans = 0;
		N = ds.length;
		int[][] d = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				char c = ds[i].charAt(j);
				d[i][j] = c - '0';
			}
		}
		
	
			int[][] dist = new int[N][N];
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					dist[i][j] = d[i][j];
				}
			}
			for(int k = 0; k < N; k++){
				for(int i = 0; i < N; i++){
					for(int j = 0; j < N; j++){
						dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
					}
				}
			}
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					
					int cnt = 0;
					for(int k = 0; k < N; k++){
						for(int m = 0; m < N; m++){
							if(dist[k][m] == dist[k][i] + d[i][j] + dist[j][m]){
								cnt++;
							}
						}
					}
					if(cnt >= T){
						ans += d[i][j];
						d[i][j] = 0;
					}
				}
			}
		
		
		
		return ans;
	}
	
	void upddist(int[][] dist, int[][] d){
		for(int k = 0; k < N; k++){
			for(int i = 0; i < N; i++){
				for(int j = 0; j < N; j++){
					dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuildingRoutes t = new BuildingRoutes();
		String[] ds = {"05789654",
				 "10347583",
				 "65085479",
				 "55602398",
				 "76590934",
				 "57939045",
				 "12345608",
				 "68647640"};
		int T = 3;
		int r = t.build(ds, T);
		
		out.println(r);
	}

}
