package accept;


import java.util.*;
import java.io.*;


public class POJ1274 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	static int n = 0;
	static int m = 0;

	public void solve() throws Exception{

		
		Matching maxflow = new Matching(n + m + 1);

		for(int i = 0; i < n; i++){
			int num = sc.nextInt();
			int cow = i + 1;
			for(int j = 0; j < num; j++){
				int stole = sc.nextInt() + n;
				maxflow.add_edge(cow, stole);
			}
		}
		int ret = maxflow.bipartite_matching();
		System.out.println(ret);
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
		POJ1274 t = new POJ1274();
		while(true){
			n = sc.nextInt();
			m = sc.nextInt();
			if(n == 0 && m == 0) break;
			t.solve();
		}
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
		
//		/**
//		 * @param args
//		 */
//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
	//
//		}

	}



}