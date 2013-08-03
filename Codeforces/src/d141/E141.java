package d141;


import java.io.*;
import java.util.*;
import java.math.*;
	




public class E141 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;
	List<List<Data>> G = new ArrayList<List<Data>>();
	public void solve() throws Exception{

		n = sc.nextInt();
		int m = sc.nextInt();
		SCC scc = new SCC(2 * (n+1));
		for(int i = 0; i < m; i++){
			int f = sc.nextInt();
			int t = sc.nextInt();
			int k = sc.nextInt();
			if(k == 1){
				scc.add_edge(f, t);
				scc.add_edge(t, f);
				scc.add_edge(f+n, t+n);
				scc.add_edge(t+n, f+n);
			}
			else{
				scc.add_edge(f, t+n);
				scc.add_edge(f+n, t);
				scc.add_edge(t+n, f);
				scc.add_edge(t, f+n);
			}
		}
		
		scc.scc();
		for(int i = 1; i <= n; i++){
			if(scc.cmp[i] == scc.cmp[i+n]){
				System.out.println("Impossible");
				return;
			}
		}
		List<Integer> ans = new ArrayList<Integer>();
		for(int i = 1; i <= n; i++){
			if(scc.cmp[i] > scc.cmp[i+n]){
				ans.add(i);
			}
		}
		System.out.println(ans.size());
		for(int i = 0; i < ans.size(); i++){
			if(i != 0) System.out.print(" ");
			out.print(ans.get(i));
		}
	
		
	}
	
	class Data {
		int t = 0;
		int k = 0;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
	
		sc =  new Scanner(System.in);
		E141 t = new E141();
		t.solve();

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
