package yame;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// WA 
// 出力があやしいし、2分マッチングで理解できそうだからとりあえずやめ
public class POJ1486 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;

	public void solve() throws Exception{
		List<Data> list = new ArrayList<Data>();
		for(int i = 0; i < N; i++){
			char c = (char)('A' + i);
			Data d = new Data();
			d.c = c;
			d.xl = sc.nextInt();
			d.xr = sc.nextInt();
			d.yl = sc.nextInt();
			d.yr = sc.nextInt();
			list.add(d);
		}
		List<L> ls = new ArrayList<L>();
		for(int i = 0; i < N; i++){
			L l  = new L();
			l.idx = i + 1;
			l.x = sc.nextInt();
			l.y = sc.nextInt();
			ls.add(l);
		}
		int[] match = null;
		for(int i = 0; i < list.size(); i++){
			Data d = list.get(i);
			for(int j = 0; j < ls.size(); j++){
				L l = ls.get(j);
				if(d.xl < l.x && l.x < d.xr && d.yl < l.y && l.y < d.yr){
					Matching mc = new Matching(2 * N);
					for(int k = 0; k < list.size(); k++){
						if(k == i) continue;
						Data dt = list.get(k);
						for(int p = 0; p < ls.size(); p++){
							if(p == j) continue;
							L lt = ls.get(p);
							if(dt.xl < lt.x && lt.x < dt.xr && dt.yl < lt.y && lt.y < dt.yr){
								mc.add_edge(k, p + list.size());
							}
						}
					}
					int ret = mc.bipartite_matching();
					if(ret == N-1){
						mc.match[i] = j + list.size();
						if(match == null){
							match = mc.match;
						}
						else{
							for(int k = 0; k < N; k++){
								if(mc.match[k] != match[k]){
									out.println("none");
									return;
								}
							}
						}

					}
				}
			}
		}
		if(match != null){
			for(int k = 0; k < N; k++){
				if(k != 0){
					out.print(" ");
				}
				char c = (char)('A' + k);
				int num = (match[k] - N + 1);
				out.print("(" + c + "," + num + ")");
			}
			out.println();
		}
		else{
			out.println("none");
		}
		
		return;
//		if(anum == N){
//			for(int i = 0; i < list.size(); i++){
//				if(i != 0){
//					out.print(" ");
//				}
//				out.print("(" + list.get(i).c + "," + list.get(i).ans + ")");
//			}
//			out.println();
//		}
//		else{
//			out.println("none");
//		}
	}


	class Data{
		char c = 'a';
		int ans = 0;
		int xl = 0;
		int yl = 0;
		int xr = 0;
		int yr = 0;
	}
	
	class L{
		int idx = 0;
		int x = 0;
		int y = 0;
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
		POJ1486 p = new POJ1486();
		int t = 1;
		while(true){
			
			try{
				N = sc.nextInt();

				if(N == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
			out.println("Heap " + t);
			p.solve();
			out.println();
			t++;
		}
	}
	public class Matching {

		int MAXV = 0;
		public List<List<Integer>> edgeList = null;
		int[] match = null;
		boolean[] used = null;
		
		public Matching(int maxv){
			MAXV = maxv;
			init();
		}
		
		public void add_edge(int u, int v){
			edgeList.get(u).add(v);
			edgeList.get(v).add(u);
		}
		
		void init(){
			edgeList = new ArrayList<List<Integer>>();
			for(int i = 0; i < MAXV; i++){
				List<Integer> list = new ArrayList<Integer>();
				edgeList.add(list);
			}
			match = new int[MAXV];
			used = new boolean[MAXV];
		}
		
		boolean dfs(int v){
			used[v] = true;
			for(int i = 0; i < edgeList.get(v).size(); i++){
				int u = edgeList.get(v).get(i), w = match[u];
				if(w < 0 || !used[w] && dfs(w)){
					match[v] = u;
					match[u] = v;
					return true;
				}
			}
			return false;
		}
		
		public int bipartite_matching() {
			int res = 0;
			for(int i = 0; i < match.length; i++){
				match[i] = -1;
			}
			for(int v = 0; v < MAXV; v++){
				if(match[v] < 0){
					used = new boolean[MAXV];
					if(dfs(v)){
						res++;
					}
				}
			}

			return res;
		}

	}

}
