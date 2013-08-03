import java.util.*;


// POJ 3041, 2987
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

