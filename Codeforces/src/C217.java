


import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class C217 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] t = nextInts();
		N = t[0];
		int m = t[1];
		int[] col = new int[m+1];
		t = nextInts();
		int[] num = new int[m+1];
		for(int i = 0; i < N; i++){
			col[t[i]] +=1;
			num[t[i]]+=2;
		}
		
		MaxFlowFast flow = new MaxFlowFast(2 * m + 10);
		
		int start = 2 * m + 3;
		int end = start + 1;
		
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= m; j++){
				if(i == j) continue;
				
				flow.add_edge(i, m + j, col[i]);
			}
		}
		
		for(int i = 1; i<= m; i++){
			flow.add_directed_edge(start, i, col[i]);
			flow.add_directed_edge(m + i, end, col[i]);
		}
		

		long ans = flow.maxflow(start, end);
		out.println(ans);
		
		List<List<MaxFlowFast.Edge>> list = flow.edgeList;
		for(int i = 1; i <= m; i++){
			for(MaxFlowFast.Edge edge : list.get(i)){
				if(edge.to > m && edge.to <= 2 * m){
					//MaxFlowFast.Edge rev = list.get(edge.to).get(edge.rev);
					int cnt = col[i] - (int)edge.cap;
					int col2 = edge.to - m;
					for(int j = 0; j < cnt; j++){
						out.println(i + " " + col2);
					}
					num[i] -= cnt;
					num[col2] -= cnt;
				}
			}
		}

		for(int i = 1; i <= m; i++){
			while(num[i] > 0){
				out.println(i + " " + i);
				num[i]-=2;
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
    
    private String nextS() throws IOException{
		String s = br.readLine();
		return s;
	}

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
	}
	
	private long nextLong() throws IOException{
		String s = br.readLine();
		return parseLong(s);
	}
	
	private int[] nextInts() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		int[] r = new int[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseInt(sp[i]);
		}
		return r;
	}
	
	private long[] nextLongs() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		long[] r = new long[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseLong(sp[i]);
		}
		return r;
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
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		C217 t = new C217();
		t.solve();
		bw.close();
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
