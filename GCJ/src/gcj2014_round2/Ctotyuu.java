package gcj2014_round2;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class Ctotyuu {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	long b = 1;
	int N = 0;
	int M = 0;
	

	
	public void solve() throws Exception{
		String s = br.readLine();
		
		String[] sp = s.split(" ");
		int W = Integer.parseInt(sp[0]);
		int H = Integer.parseInt(sp[1]);
		int B =Integer.parseInt(sp[2]);
		
		boolean[][] f = new boolean[H][W];
		
		for(int i = 0; i < B; i++){
			s = br.readLine();
			sp = s.split(" ");
			for(int j = Integer.parseInt(sp[1]); j <= Integer.parseInt(sp[3]); j++){
				for(int k = Integer.parseInt(sp[0]); k <= Integer.parseInt(sp[2]); k++){
					f[j][k] = true;
				}
			}
		}
		int start = H * W;
		int end = start + 1;
		
		MaxFlow mf = new MaxFlow(end+1);
		for(int i = 0; i < W; i++){
			if(!f[0][i]){
				mf.add_edge(start, i, 1);
			}
		}
		for(int i = 1; i < H; i++){
			for(int j = 0; j < W; j++){
				if(!f[i-1][j] && !f[i][j]){
					mf.add_edge((i-1)*W + j, (i)*W + j, 1);
				}
			}
		}
		for(int i = 0; i < H; i++){
			for(int j = 0; j < W-1; j++){
				if(!f[i][j] && !f[i][j+1]){
					mf.add_edge((i)*W + j, (i)*W + j+1, 1);
				}
			}
		}
		for(int i = 0; i < W; i++){
			if(!f[H-1][i]){
				mf.add_edge((H-1)*W + i, end, 1);
			}
		}
		
		int ans = mf.maxflow(start, end);
		println(ans);
//		private void solve(){
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		Ctotyuu b = new Ctotyuu();
		int T = 0;
		if(sc != null){
			T = sc.nextInt();
		}
		else{
			T = parseInt(br.readLine());
		}
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			System.out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
	
	void print(int i){
		out.print(i + "");
		System.out.print(i);
	}
	void println(int i){
		out.println(i + "");
		System.out.println(i);
	}
	void print(String s){
		out.print(s);
		System.out.print(s);
	}
	void println(String s){
		out.println(s);
		System.out.println(s);
	}
	void print(long i){
		out.print(i + "");
		System.out.print(i);
	}
	void println(long i){
		out.println(i + "");
		System.out.println(i);
	}
	class MaxFlow {
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
		


	}

}
