package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;


// 知らない人同士は存在してはいけないので、complementなグラフを作ると、最大安定集合を求めるのと同値となる
public class POJ3692 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int G = 0;
	static int B = 0;
	static int N = 0;
	public void solve() throws Exception{
		Matching mc = new Matching(G + B);
		Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
		for(int i = 1; i <= G; i++){
			Set<Integer> set = new HashSet<Integer>();
			map.put(i, set);
		}
		for(int i = 0; i < N; i++){
			int g = sc.nextInt();
			int b = sc.nextInt();
			map.get(g).add(b);
		}
		for(int i = 1; i <= G; i++){
			for(int j = 1; j <= B; j++){
				if(!map.get(i).contains(j)){
					mc.add_edge(i-1, G + j -1);
				}
			}
		}
		int ans = G + B - mc.bipartite_matching();
		out.println(ans);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ3692 p = new POJ3692();
	
		int t = 1;
		while(true){
			try{
				G = sc.nextInt();
				B = sc.nextInt();
				N = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(G == 0 && B == 0 && N == 0) break;
			out.print("Case " + t + ": ");
			p.solve();
			
			t++;
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

	}

}
