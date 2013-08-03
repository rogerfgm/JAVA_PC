package accept;



import java.util.*;
import java.io.*;

import static java.lang.Math.*;


// 各頂点に対して、容量１コスト-dと容量INF, コスト0の点をそれぞれ用意しmincostflow
public class POJ3422 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int K = 0;
	public void solve() throws Exception{
		int[][] d = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				d[i][j] = sc.nextInt();
			}
		}
		
		MinCostFlow mc = new MinCostFlow(2* N* N);
		int start = 0;
		int end = 2 * N * N -1;
		int base = N * N;


		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				int in = i * N + j;
				int ot = base + in;
	
				
				mc.add_edge(in, ot, 1, -d[i][j]);
				mc.add_edge(in, ot, INF, 0);
				
				if(j < N-1){
					int nin = in +1;
					mc.add_edge(ot, nin, INF, 0);
				}
				if(i < N-1){
					int nin = in + N;
					mc.add_edge(ot, nin, INF, 0);
				}
			}
		}
		int ret = -mc.min_cost_flow(start, end, K);
		out.println(ret);
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
		POJ3422 p = new POJ3422();
		while(true){
			
			try{
				N = sc.nextInt();
				K = sc.nextInt();
				if(N == 0 && K == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
		
			p.solve();
		}
	}
	public class MinCostFlow {
		int MAXV = 1000;
		
		int INF = Integer.MAX_VALUE / 1000;
		
		int[] dist = null;
		int[] prevv = null;
		int[] preve = null;
		List<List<Edge>> edgeList = null;
		
		void init(){
			dist = new int[MAXV];
			prevv = new int[MAXV];
			preve = new int[MAXV];
			edgeList = new ArrayList<List<Edge>>();
			for(int i = 0; i < MAXV; i++){
				List<Edge> list = new ArrayList<Edge>();
				edgeList.add(list);
			}
		}
		
		public MinCostFlow(int maxv){
			MAXV = maxv;
			init();
		}
		
		
		
		class Edge{
			public Edge(int t, int c, int cst, int r){
				to = t;
				cap = c;
				cost = cst;
				rev = r;
			}
			public int to = 0;
			public int cap = 0;
			public int rev = 0;
			public int cost = 0;
		}
		
		

		
		public void add_edge(int from, int to, int cap, int cost){
			Edge e = new Edge(to, cap, cost, edgeList.get(to).size());
			edgeList.get(from).add(e);
			e = new Edge(from, 0, -cost, edgeList.get(from).size()-1);
			edgeList.get(to).add(e);
		}
		
		public int min_cost_flow(int s, int t, int f){
			int res = 0;
			while(f > 0){
				for(int i = 0; i < dist.length; i++){
					dist[i] = INF;
				}
				dist[s] = 0;
				boolean update = true;
				while(update){
					update = false;
					for(int v = 0; v < MAXV; v++){
						if(dist[v] == INF){
							continue;
						}
						for(int i = 0; i < edgeList.get(v).size(); i++){
							Edge e = edgeList.get(v).get(i);
							if(e.cap > 0 && dist[e.to] > dist[v] + e.cost){
								dist[e.to]= dist[v] + e.cost;
								prevv[e.to]= v;
								preve[e.to]= i;
								update = true;
							}
						}
					}
				}
				if(dist[t] == INF){
					return -1;
				}
				
				int d = f;
				for(int v = t; v!= s; v = prevv[v]){
					d = Math.min(d, edgeList.get(prevv[v]).get(preve[v]).cap);
				}
				f -= d;
				res += d * dist[t];
				for(int v = t; v != s; v = prevv[v]){
					Edge e = edgeList.get(prevv[v]).get(preve[v]);
					e.cap -= d;
					edgeList.get(v).get(e.rev).cap += d;
				}
			}
			
			return res;
		}
		
		
//		static void  solve(){
//			
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
//		}
	//	
//		public static void main(String[] m){
//			solve();
//		}

	}

}
