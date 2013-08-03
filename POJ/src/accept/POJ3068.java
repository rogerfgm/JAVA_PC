package accept;
import java.util.*;
import java.io.*;

public class POJ3068 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	static int N = 0;
	static int M = 0;
	

	public void solve() throws Exception{
		MinCostFlowFast mcf = new MinCostFlowFast(2 * N);
		for(int i = 1; i <= N-2; i++){
			mcf.add_edge(i, i+N, 1, 0);
		}
		for(int i = 0; i < M; i++){
			int f = sc.nextInt();
			int t = sc.nextInt();
			int c = sc.nextInt();
			if(f == 0){
				mcf.add_edge(f, t, 1, c);
			}
			else{
				mcf.add_edge(f+N, t, 1, c);
			}
		}
		
		int ret = mcf.min_cost_flow(0, N-1, 2);
		if(ret == -1){
			System.out.println("Not possible");
		}
		else{
			System.out.println(ret);
		}
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
		POJ3068 t = new POJ3068();
		
		int idx = 1;
		while(true){
			N = sc.nextInt();
			M = sc.nextInt();
			if(N == 0 && M == 0){
				break;
			}
			System.out.print("Instance #" + idx + ": ");
			t.solve();
			idx++;
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