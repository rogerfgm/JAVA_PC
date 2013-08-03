package mle;


import java.util.*;
import java.io.*;


public class POJ3683 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	static int n = 0;
	int[] S = null;
	int[] T = null;
	int[] D = null;
	
	public void solve() throws Exception{

		
		S = new int[n];
		T = new int[n];
		D = new int[n];
		
		for(int i = 0; i < n; i++){
			String s = sc.next();
			String t = sc.next();
			D[i] = sc.nextInt();
			
			String[] sp = s.split(":");
			S[i] = 60 * Integer.parseInt(sp[0]) + Integer.parseInt(sp[1]);
			sp = t.split(":");
			T[i] = 60 * Integer.parseInt(sp[0]) + Integer.parseInt(sp[1]);
		}
		
		SCC scc = new SCC(2 * n);
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < i; j++){
				if(Math.min(S[i] + D[i],  S[j] + D[j]) > Math.max(S[i], S[j])){
					scc.add_edge(i, n + j);
					scc.add_edge(j, n+i);
				}
				if(Math.min(S[i] + D[i],  T[j]) > Math.max(S[i],  T[j]-D[j])){
					scc.add_edge(i, j);
					scc.add_edge(j+n, i+n);
				}
				if(Math.min(T[i],  S[j] + D[j]) > Math.max(T[i]-D[i],  S[j])){
					scc.add_edge(n+i, n+j);
					scc.add_edge(j, i);
				}
				if(Math.min(T[i], T[j]) > Math.max(T[i]-D[i],  T[j] - D[j])){
					scc.add_edge(n+i, j);
					scc.add_edge(n+j, i);
				}
			}
		}
		
		int k = scc.scc();
		
		for(int i = 0; i < n; i++){
			if(scc.cmp[i] == scc.cmp[i+n]){
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		for(int i = 0; i < n; i++){
			if(scc.cmp[i] > scc.cmp[i+n]){
				int start = S[i];
				int end = S[i] + D[i];
				int sth = start / 60;
				int stm = start % 60;
				int edh = end / 60;
				int edm = end % 60;
				System.out.printf("%02d:%02d %02d:%02d", sth, stm, edh, edm);
			}
			else{
				int start = T[i] - D[i];
				int end = T[i];
				int sth = start / 60;
				int stm = start % 60;
				int edh = end / 60;
				int edm = end % 60;
				System.out.printf("%02d:%02d %02d:%02d", sth, stm, edh, edm);
			}
			if(i == n-1) break;
			System.out.println();
		}
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ3683 t = new POJ3683();

		while(true){
			try{
			n = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			t.solve();
		}
	}
	
	public class SCC {
		int MAXV = 0;
		List<List<Integer>> G = null;
		List<List<Integer>> rG = null;
		List<Integer> vs; 
		boolean[] used = null;
		int[] cmp = null;
		
		public SCC(int maxv){
			MAXV = maxv;
			init();
		}
		void init(){
			G = new ArrayList<List<Integer>>();
			rG = new ArrayList<List<Integer>>();
			for(int i = 0; i < MAXV; i++){
				List<Integer> list = new ArrayList<Integer>();
				G.add(list);
				list = new ArrayList<Integer>();
				rG.add(list);
			}
			vs = new ArrayList<Integer>();
			used = new boolean[MAXV];
			cmp = new int[MAXV];
		}
		
		public void add_edge(int from, int to){
			G.get(from).add(to);
			rG.get(to).add(from);
		}
		
		void dfs(int v){
			used[v] = true;
			for(int i = 0; i < G.get(v).size(); i++){
				if (!used[G.get(v).get(i)]) dfs(G.get(v).get(i));
			}
			vs.add(v);
		}
		
		void rdfs(int v, int k){
			used[v] = true;
			cmp[v] = k;
			for(int i = 0; i < rG.get(v).size(); i++){
				if(!used[rG.get(v).get(i)]) rdfs(rG.get(v).get(i), k);
			}
		}
		
		public int scc(){
			used = new boolean[MAXV];
			vs = new ArrayList<Integer>();
			for(int v = 0; v < MAXV; v++){
				if(!used[v]) dfs(v);
			}
			used = new boolean[MAXV];
			int k = 0;
			for(int i = vs.size()-1; i >= 0; i--){
				if(!used[vs.get(i)]) rdfs(vs.get(i), k++);
			}
			return k;
		}
	}

}
