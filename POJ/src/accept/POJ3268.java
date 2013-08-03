package accept;


import java.util.*;
import java.io.*;


public class POJ3268 {
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	static PrintStream out = null;
	static int n = 0;
	static int m = 0;
	static int X = 0;

	List<List<Edge>> fwd = null;
	List<List<Edge>> rev = null;
	
	public void solve() throws Exception{
		fwd = new ArrayList<List<Edge>>();
		rev = new ArrayList<List<Edge>>();
		for(int i = 0; i <= n; i++){
			List<Edge> l = new ArrayList<Edge>();
			fwd.add(l);
			l = new ArrayList<Edge>();
			rev.add(l);
		}
		
		for(int i = 0; i < m; i++){
			int f = sc.nextInt();
			int t = sc.nextInt();
			int c = sc.nextInt();
			Edge e = new Edge(t, c);
			fwd.get(f).add(e);
			e = new Edge(f, c);
			rev.get(t).add(e);
		}
		
		
		
		Queue<POJ3268.Data> que = new PriorityQueue<POJ3268.Data>(100, new Comparator<POJ3268.Data>() {
			public int compare(Data o1, Data o2) {
				return o1.d - o2.d;
			}
		});
		
		Data data = new Data(X, 0);
		que.add(data);
		int[] fd = new int[n+1];
		int[] rd = new int[n+1];
		for(int i = 0; i <= n; i++){
			fd[i] = INF;
			rd[i] = INF;
		}
		fd[X] = rd[X] = 0;
		while(que.size() > 0){
			data = que.poll();
			int p = data.p;
			if(fd[p] < data.d){
				continue;
			}
			List<Edge> es = fwd.get(p);
			for(Edge e : es){
				if(fd[e.t] > e.c + fd[p] ){
					fd[e.t]= e.c + fd[p];
					Data nd = new Data(e.t, fd[e.t]);
					que.add(nd);
				}
			}
		}
		data = new Data(X, 0);
		que.add(data);
		while(que.size() > 0){
			data = que.poll();
			int p = data.p;
			if(rd[p] < data.d){
				continue;
			}
			List<Edge> es = rev.get(p);
			for(Edge e : es){
				if(rd[e.t] > e.c + rd[p] ){
					rd[e.t]= e.c + rd[p];
					Data nd = new Data(e.t, rd[e.t]);
					que.add(nd);
				}
			}
		}
		int ans = 0;
		for(int i = 1; i <= n; i++){
			ans = Math.max(ans, fd[i] + rd[i]);
		}
		out.println(ans);
	}
	
	public class Data {
		public Data(int p, int d){
			this.p = p;
			this.d = d;
		}
		int p = 0;
		int d = 0;
	}
	
	class Edge{
		public Edge( int t, int c){
			this.t = t;
			this.c = c;
		}
		int t = 0;
		int c = 0;
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
		POJ3268 t = new POJ3268();

		while(true){
			try{
			n = sc.nextInt();
			m = sc.nextInt();
			X = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(n == 0) break;
			t.solve();
		}
	}


}
