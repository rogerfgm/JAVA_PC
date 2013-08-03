package accept;


import java.util.*;
import java.awt.Point;
import java.io.*;


public class POJ2186 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	int n = 0;
	int m = 0;
	

	
	int kvi = 1;
	public void solve() throws Exception{

		n = sc.nextInt();
		m = sc.nextInt();
		SCC scc = new SCC(n);
		for(int i = 0; i < m; i++){
			int f = sc.nextInt() -1;
			int t = sc.nextInt() -1;
			scc.add_edge(f, t);
		}
		int k = scc.scc();
		int ans = 0;
		int v = 0;
		for(int i = 0; i < n; i++){
			if(scc.cmp[i] == k-1){
				ans++;
				v = i;
			}
		}
		scc.used = new boolean[n];
		scc.rdfs(v, 0);
		for(int i = 0; i < n; i++){
			if(!scc.used[i]){
				System.out.println(0);
				return;
			}
		}
		System.out.println(ans);
	
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
	
    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
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
		POJ2186 t = new POJ2186();
		int T = 1;
		while(T-- > 0){
			t.solve();
		}
	}


}