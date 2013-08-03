import java.util.*;

// POJ 3041
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
	
//	private void solve(){
//		MaxFlow maxflow = new MaxFlow(10000);
//		maxflow.add_edge(0, 2, 2);
//		maxflow.add_edge(0, 1, 10);
//		maxflow.add_edge(0, 2, 2);
//		maxflow.add_edge(1, 2, 6);
//		maxflow.add_edge(1, 3, 6);
//		maxflow.add_edge(2, 4, 5);
//		maxflow.add_edge(3, 4, 8);
//		
//		int ret = maxflow.maxflow(0, 4);
//		System.out.println(ret);
//	}

}
