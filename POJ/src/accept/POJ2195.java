package accept;


import java.util.*;
import java.io.*;

import static java.lang.Math.*;



public class POJ2195 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	
	public void solve() throws Exception{
		
		List<Data> hs = new ArrayList<Data>();
		List<Data> ms = new ArrayList<Data>();
		for(int i = 0; i < N; i++){
			String s = sc.next();
			for(int j = 0; j < M; j++){
				char c = s.charAt(j);
				if(c == 'm'){
					Data d = new Data(i, j);
					ms.add(d);
				}
				else if(c == 'H'){
					Data d = new Data(i, j);
					hs.add(d);
				}
			}
		}
		
		int mC = ms.size();
		int hC = hs.size();
		int start = mC + hC;
		int end = start + 1;
		
		MinCostFlowFast mc = new MinCostFlowFast(end+1);
		
		for(int i = 0; i < ms.size(); i++){
			for(int j = 0; j < hs.size(); j++){
				Data a = ms.get(i);
				Data b = hs.get(j);
				int d = abs(a.x - b.x) + abs(a.y - b.y);
				mc.add_edge(i, mC + j, 1, d);
			}
		}
		for(int i = 0; i < mC; i++){
			mc.add_edge(start, i, 1, 0);
		}
		for(int i = 0; i < hC; i++){
			mc.add_edge(mC + i, end, 1, 0);
		}
		long ans = mc.min_cost_flow(start, end, mC);
		out.println(ans);
	}
	
	class Data{
		public Data(int y, int x){
			this.y = y;
			this.x = x;
		}
		int y = 0;
		int x = 0;
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
		POJ2195 p = new POJ2195();
	
		while(true){
			try{
				N = sc.nextInt();
				M = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(N == 0 && M == 0) break;
			p.solve();
		}
	}
	
	public class MinCostFlowFast {
		int INF = Integer.MAX_VALUE / 100;
		class P{
			public P(int d, int p){
				this.d = d;
				this.p = p;
			}
			int d = 0; // minimum distance
			int p = 0; // vertices number (p1, p2, ...)
		}
		
		class Edge{
			public Edge(int t, int c, int cs, int rv){
				to = t;
				cap = c;
				cost = cs;
				rev = rv;
			}
			int to = 0;
			int cap = 0;
			int cost = 0;
			int rev = 0;
		}
		
		int MAXV = 0;
		List<List<Edge>> G = null;
		int[] h = null;
		int[] dist = null;
		int[] prevv = null;
		int[] preve = null;
		
		public MinCostFlowFast(int maxv){
			MAXV = maxv;
			init();
		}
		
		void init(){
			G = new ArrayList<List<Edge>>();
			for(int i = 0; i < MAXV; i++){
				List<Edge> list = new ArrayList<Edge>();
				G.add(list);
			}
			h = new int[MAXV];
			dist = new int[MAXV];
			prevv = new int[MAXV];
			preve = new int[MAXV];
		}
		
		void add_edge(int from, int to, int cap, int cost){
			G.get(from).add(new Edge(to, cap, cost, G.get(to).size()));
			G.get(to).add(new Edge(from, 0, -cost, G.get(from).size()-1));
		}
		
		int min_cost_flow(int s, int t, int f){
			int res = 0;
			h = new int[MAXV];
			while(f>0){
				Queue<P> que = new PriorityQueue<P>(1000, new Comparator<P>() {
					public int compare(P p1, P p2) {
						return p1.d - p2.d;
					}
				});
				for(int i = 0; i < MAXV; i++){
					dist[i] = INF;
				}
				dist[s] = 0;
				que.add(new P(0, s));
				while(que.size() > 0){
					P p = que.poll();
					int v = p.p;
					if(dist[v] < p.d) continue;
					for(int i = 0; i < G.get(v).size(); i++){
						Edge e = G.get(v).get(i);
						if(e.cap > 0 && dist[e.to] > dist[v] + e.cost + h[v] - h[e.to]){
							dist[e.to] = dist[v] + e.cost + h[v] - h[e.to];
							prevv[e.to] = v;
							preve[e.to] = i;
							que.add(new P(dist[e.to], e.to));
						}
					}
				}
				if(dist[t] == INF){
					return -1;
				}
				for(int v = 0; v < MAXV; v++) h[v] += dist[v];
				
				int d = f;
				for(int v = t; v != s; v = prevv[v]){
					d = Math.min(d, G.get(prevv[v]).get(preve[v]).cap);
				}
				f -= d;
				res += d * h[t];
				for(int v = t; v != s; v = prevv[v]){
					Edge e = G.get(prevv[v]).get(preve[v]);
					e.cap -= d;
					G.get(v).get(e.rev).cap += d;
				}
			}
			return res;
		}
		
		
		
		
//		public static void main(String[] args) {
//			MinCostFlow m = new MinCostFlow(1000);
//			m.add_edge(0, 1, 10, 2);
//			m.add_edge(0, 2, 2, 4);
//			m.add_edge(1, 2, 6, 6);
//			m.add_edge(1, 3, 6, 2);
//			m.add_edge(3, 2, 3, 3);
//			m.add_edge(2, 4, 5, 2);
//			m.add_edge(3, 4, 8, 6);
//			
//			System.out.println(m.min_cost_flow(0, 4, 9));
	//
//		}

	}

	
}
