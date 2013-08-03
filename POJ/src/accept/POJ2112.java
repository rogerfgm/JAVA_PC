package accept;

import java.util.*;
import java.io.*;




import static java.lang.Math.*;

public class POJ2112 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int K = 0;
	static int C = 0;
	static int M = 0;
	
	public void solve() throws Exception{
		int n = K + C;
		int[][] d = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				d[i][j] = sc.nextInt();
				if(d[i][j] == 0 && i != j){
					d[i][j] = INF;
				}
			}
		}
		for(int k = 0; k < n; k++){
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					d[i][j] = min(d[i][j], d[i][k] + d[k][j]);
				}
			}
		}
		List<Data> list = new ArrayList<Data>();
		for(int i = 0; i < K; i++){
			for(int j = K; j < n; j++){
				if(d[i][j] != INF){
					Data data = new Data(d[j][i],j, i);
					list.add(data);
				}
			}
		}
		Data[] dt = new Data[0];
		dt = list.toArray(dt);
		Arrays.sort(dt, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				return o1.d - o2.d;
			}
		});
		

		int N = n + 2;
		// start = n;
		// end = n+1;
		
		MaxFlow mcf = new MaxFlow(N);
		for(int i = K; i < n; i++){
			mcf.add_edge(n, i, 1);
		}
		
		for(int i = 0; i < K; i++){
			mcf.add_edge(i, n+1, M);
		}
		
		int num = C;
		int ans = 0;
		for(int i = 0; i < C; i++){
			mcf.add_edge(dt[i].f, dt[i].t, 1);
		}
		int idx = C;
		
		while(true){
			
			int ret = (int)mcf.maxflow(n, n+1);
			num -= ret;
			if(num <= 0){
				ans = dt[idx-1].d;
				break;
			}
			mcf.add_edge(dt[idx].f, dt[idx].t, 1);
			idx++;
		}
		
		out.println(ans);
		
	}
	
	class Data{
		int d = 0;
		int f = 0;
		int t = 0;
		public Data(int d, int f, int t){
			this.d = d;
			this.f = f;
			this.t = t;
		}
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
		POJ2112 p = new POJ2112();
	
		while(true){

			try{
				K = sc.nextInt();
				C = sc.nextInt();
				M = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			p.solve();
		}
	}

	public class MaxFlow {
		int MAXV = 0;
		boolean[] used = null;
		public List<List<Edge>> edgeList = null;
		
		int INF = Integer.MAX_VALUE / 100;
		
		public MaxFlow(int maxv){
			this.MAXV = maxv;
			init();
		}
		
		public void init(){
			used = new boolean[MAXV];
			edgeList = new ArrayList<List<Edge>>();
			
			for(int i = 0; i < MAXV; i++){
				List<Edge> list = new ArrayList<Edge>();
				edgeList.add(list);
			}
		}
		
		public void add_edge(int from, int to, int cap){
			add_directed_edge(from, to, cap);
		}
		
		public void add_directed_edge(int from, int to, int cap){
			Edge e = new Edge(to, cap, edgeList.get(to).size());
			edgeList.get(from).add(e);
			e = new Edge(from, 0, edgeList.get(from).size()-1);
			edgeList.get(to).add(e);
		}
		
		public void add_undirected_edge(int from, int to, int cap){
			add_directed_edge(from, to, cap);
			add_directed_edge(to, from, cap);
		}
		
		public class Edge{
			public Edge(int t, int c, int r){
				to = t;
				cap = c;
				rev = r;
			}
			public int to = 0;
			public int cap = 0;
			public int rev = 0;
		}
		
		int dfs(int s, int t, int f){
			if(s == t){
				return f;
			}
			used[s] = true;
			List<Edge> edges = edgeList.get(s);
			for(int i = 0; i < edges.size(); i++){
				Edge e = edges.get(i);
				if(!used[e.to] && e.cap > 0){
					int d = dfs(e.to, t, Math.min(f, e.cap));
					if(d > 0){
						e.cap -= d;
						Edge rev = edgeList.get(e.to).get(e.rev);
						rev.cap += d;
						return d;
					}
				}
			}
			
			return 0;
		}
		
		public int maxflow(int s, int t){
			int flow = 0;

			while(true){
				used = new boolean[MAXV];
				int f = dfs(s, t, INF);
				if(f == 0){
					break;
				}
				flow += f;
			}
			
			return flow;
		}
		
//		private void solve(){
//			MaxFlow maxflow = new MaxFlow(10000);
//			maxflow.add_edge(0, 2, 2);
//			maxflow.add_edge(0, 1, 10);
//			maxflow.add_edge(0, 2, 2);
//			maxflow.add_edge(1, 2, 6);
//			maxflow.add_edge(1, 3, 6);
//			maxflow.add_edge(2, 4, 5);
//			maxflow.add_edge(3, 4, 8);
//			
//			int ret = maxflow.maxflow(0, 4);
//			System.out.println(ret);
//		}

	}
	
}
