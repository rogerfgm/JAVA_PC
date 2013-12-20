package srm589;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

import java.io.*;

public class GearsDiv1 {

	int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	boolean[][] g = null;
	int[] col = null;
	public int getmin(String c, String[] gs){
		N = gs.length;
		g = new boolean[gs.length][gs.length];
		col = new int[N];
		for(int i = 0;i < N; i++){
			if(c.charAt(i) == 'G'){
				col[i] = 1;
			}
			else if(c.charAt(i) == 'B'){
				col[i] = 2;
			}
			String s = gs[i];
			for(int j = 0; j < N; j++){
				if(s.charAt(j) == 'Y'){
					g[i][j] = true;
				}
			}
		}
		
		int ans = INF;
		for(int i = 0; i < 2; i++){
			for(int j = 0; j < 2; j++){
				for(int k = 0; k < 2; k++){
					int[] d = new int[3];
					d[0] = i;
					d[1] = j;
					d[2] = k;
					int ret = check(d);
					ans = min(ans, ret);
				}
			}
		}
		
		
		return ans;
	}

	int check(int[] d){
		Matching mat = new Matching(N+1);
		for(int i = 0; i < N; i++){
			
			int nc = col[i];
			for(int j = i+1; j < N; j++){
				if(g[i][j] ){
					int colj = col[j];
					if(d[nc] == d[colj]){
						mat.add_edge(i, j);
					}
				}
			}
		}

		return mat.bipartite_matching();
		

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GearsDiv1 t = new GearsDiv1();
		String c = "RRRRRGRRBGRRGBBGGGBRRRGBRGRRGG";
		String[] s = {"NNNNNYNNNYNNYNNNYNNNNNNNNYNNYY",
				 "NNNNNNNNYNNNYNYNNYNNNNYNNYNNYY",
				 "NNNNNYNNNNNNNNNNNNYNNNNNNYNNNY",
				 "NNNNNNNNNYNNYNNYYYNNNNYNNYNNNN",
				 "NNNNNNNNNYNNYNNYYYNNNNYNNNNNNN",
				 "YNYNNNYYYNNYNYYNNNNNYYNYNNYYNN",
				 "NNNNNYNNNNNNNNNYYYNNNNYNNYNNYY",
				 "NNNNNYNNNNNNNNNYNNNNNNNNNNNNYN",
				 "NYNNNYNNNYNNYNNYYYNNNNYNNYNNYY",
				 "YNNYYNNNYNNNNYYNNNYNYYNYNNNNNN",
				 "NNNNNNNNNNNNYNNYNYNNNNYNNNNNNY",
				 "NNNNNYNNNNNNYNNYYYNNNNNNNNNNYN",
				 "YYNYYNNNYNYYNYYNNNYNYNNYNNNNNN",
				 "NNNNNYNNNYNNYNNYYYNNNNYNNYNYYY",
				 "NYNNNYNNNYNNYNNYYYNNNNYNNYNNYY",
				 "NNNYYNYYYNYYNYYNNNYNYNNYYNYYNN",
				 "YNNYYNYNYNNYNYYNNNYNNNNYYNNYNN",
				 "NYNYYNYNYNYYNYYNNNNYYNNYYNYNNN",
				 "NNYNNNNNNYNNYNNYYNNNNNYNNYNNNY",
				 "NNNNNNNNNNNNNNNNNYNNNNYNNYNNNY",
				 "NNNNNYNNNYNNYNNYNYNNNNYNNNNNYY",
				 "NNNNNYNNNYNNNNNNNNNNNNYNNNNNNN",
				 "NYNYYNYNYNYNNYYNNNYYYYNYYNYNNN",
				 "NNNNNYNNNYNNYNNYYYNNNNYNNNNNNY",
				 "NNNNNNNNNNNNNNNYYYNNNNYNNYNNYY",
				 "YYYYNNYNYNNNNYYNNNYYNNNNYNYYNN",
				 "NNNNNYNNNNNNNNNYNYNNNNYNNYNNYN",
				 "NNNNNYNNNNNNNYNYYNNNNNNNNYNNYY",
				 "YYNNNNYYYNNYNYYNNNNNYNNNYNYYNN",
				 "YYYNNNYNYNYNNYYNNNYYYNNYYNNYNN"};
		int r = t.getmin(c, s);
		out.println(r);
	}
	public class Matching {

		int MAXV = 0;
		public List<List<Integer>> edgeList = null;
		int[] match = null;
		boolean[] used = null;
		
		public Matching(int maxv){
			MAXV = maxv;
			init();
		}
		
		public void add_edge(int u, int v){
			edgeList.get(u).add(v);
			edgeList.get(v).add(u);
		}
		
		void init(){
			edgeList = new ArrayList<List<Integer>>();
			for(int i = 0; i < MAXV; i++){
				List<Integer> list = new ArrayList<Integer>();
				edgeList.add(list);
			}
			match = new int[MAXV];
			used = new boolean[MAXV];
		}
		
		boolean dfs(int v){
			used[v] = true;
			for(int i = 0; i < edgeList.get(v).size(); i++){
				int u = edgeList.get(v).get(i), w = match[u];
				if(w < 0 || !used[w] && dfs(w)){
					match[v] = u;
					match[u] = v;
					return true;
				}
			}
			return false;
		}
		
		public int bipartite_matching() {
			int res = 0;
			for(int i = 0; i < match.length; i++){
				match[i] = -1;
			}
			for(int v = 0; v < MAXV; v++){
				if(match[v] < 0){
					used = new boolean[MAXV];
					if(dfs(v)){
						res++;
					}
				}
			}

			return res;
		}

	}
}
