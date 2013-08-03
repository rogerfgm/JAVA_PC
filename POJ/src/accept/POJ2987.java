package accept;

import java.io.*;
import java.util.*;



public class POJ2987 {

	
	long INF = Long.MAX_VALUE / 10000;
	static Scanner sc = null;

	static int n = 0;
	static int m = 0;
	
	int[] pro = null;


	boolean[] used = null;

	MaxFlowFast maxflow = null;
	public void solve() throws Exception{

		pro = new int[n];

		for(int i = 0; i < n; i++){
			pro[i] = sc.nextInt();
		}
		
		maxflow = new MaxFlowFast(n+2);
		
		for(int i = 0; i < m; i++){
			int f = sc.nextInt() -1;
			int t = sc.nextInt() -1;
			maxflow.add_edge(f, t, INF);
		}
		
		for(int i = 0; i < pro.length; i++){
			if(pro[i] > 0){
				maxflow.add_edge(n, i, pro[i]);
			}
			else if(pro[i] < 0){
				maxflow.add_edge(i, n+1, -pro[i]);
			}
		}
		maxflow.maxflow(n, n+1);
		
		used = new boolean[n+2];
		dfs(n, n+1);
		long ans = 0;
		int num = 0;
		for(int i = 0; i < n; i++){
			if(used[i]){
				ans += pro[i];
				num++;
			}
		}
		System.out.println(num + " " + ans);
	}
	
	void dfs(int f, int t){
		used[f] = true;
		if(f == t){
			return;
		}
		for(MaxFlowFast.Edge e : maxflow.edgeList.get(f)){
			if(!used[e.to] && e.cap > 0){
				dfs(e.to, t);
			}
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
		POJ2987 t = new POJ2987();

		n = sc.nextInt();
		m = sc.nextInt();
		t.solve();

	}
	
	public class MaxFlowFast {
		long INF = Long.MAX_VALUE / 5;
		int MAXV = 0;
		public List<List<Edge>> edgeList = null;
		
		int[] level = null;
		int[] iter = null;
		
		
		
		public MaxFlowFast(int maxv){
			this.MAXV = maxv;
			init();
		}
		
		public void init(){
			edgeList = new ArrayList<List<Edge>>();
			
			for(int i = 0; i < MAXV; i++){
				List<Edge> list = new ArrayList<Edge>();
				edgeList.add(list);
			}
			level = new int[MAXV];
			iter = new int[MAXV];
		}
		
		public void add_edge(int from, int to, long cap){
			add_directed_edge(from, to, cap);
		}
		
		public void add_directed_edge(int from, int to, long cap){
			Edge e = new Edge(to, cap, edgeList.get(to).size());
			edgeList.get(from).add(e);
			e = new Edge(from, 0, edgeList.get(from).size()-1);
			edgeList.get(to).add(e);
		}
		
		public void add_undirected_edge(int from, int to, long cap){
			add_directed_edge(from, to, cap);
			add_directed_edge(to, from, cap);
		}
		
		public class Edge{
			public Edge(int t, long c, int r){
				to = t;
				cap = c;
				rev = r;
			}
			public int to = 0;
			public long cap = 0;
			public int rev = 0;
		}
		
		void bfs(int s){
			for(int i = 0; i < level.length; i++){
				level[i] = -1;
			}
			Queue<Integer> que = new LinkedList<Integer>();
			level[s] = 0;
			que.add(s);
			while(que.size() > 0){
				int v = que.poll();
				for(int i = 0; i < edgeList.get(v).size(); i++){
					Edge e = edgeList.get(v).get(i);
					if(e.cap > 0 && level[e.to] < 0){
						level[e.to] = level[v] + 1;
						que.add(e.to);
					}
				}
			}
		}
		
		long dfs(int v, int t, long f){
			if(v == t){
				return f;
			}
			for(; iter[v] < edgeList.get(v).size(); iter[v]++){
				Edge e = edgeList.get(v).get(iter[v]);
				if(e.cap > 0 && level[v] < level[e.to]){
					long d = dfs(e.to, t, Math.min(f, e.cap));
					if(d > 0){
						e.cap -= d;
						edgeList.get(e.to).get(e.rev).cap += d;
						return d;
					}
				}
			}
			return 0;
		}
		
		public long maxflow(int s, int t){
			long flow = 0;

			while(true){
				bfs(s);
				if(level[t] < 0) return flow;
				iter = new int[MAXV];
				long f = 0;
				while((f = dfs(s, t, INF)) > 0){
					flow += f;
				}
			}
		}
	}


}
