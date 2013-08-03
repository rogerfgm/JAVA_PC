package accept;

import java.util.*;
import java.io.*;


public class POJ2236 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int n = 0;
	static int D = 0;

	int[][] d = null;
	int[] u = null;
	public void solve() throws Exception{
		d = new int[n][2];
		UnionFind uf = new UnionFind(n);
		boolean[] f = new boolean[n];
		for(int i = 0; i < n; i++){
			d[i][0] = sc.nextInt();
			d[i][1] = sc.nextInt();
		}
		while(true){
			String c;
			try{
				c = sc.next();
			}
			catch(Exception ex){
				return;
			}
			if(c.equals("O")){
				int p = sc.nextInt()-1;
				if(f[p]) continue;
				f[p] = true;
				for(int i = 0; i < n; i++){
					if(p == i) continue;
					if(!f[i]) continue;
					if(check(i, p)){
						uf.unite(i, p);
					}
				}
			}
			else{
				int p = sc.nextInt() -1;
				int q = sc.nextInt() - 1;
				if(uf.same(p, q)){
					out.println("SUCCESS");
				}
				else{
					out.println("FAIL");
				}
			}
			
		}
		
		
	}

	boolean check(int a, int b){
		int x = d[a][0] - d[b][0];
		int y = d[a][1] - d[b][1];
		double dist = x*x + y*y;
		dist = Math.sqrt(dist);
		if(dist <= D || Math.abs(dist - D) < DF){
			return true;
		}
		return false;
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
		POJ2236 p = new POJ2236();

		while(true){
			try{
			n = sc.nextInt();
			D = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			
			p.solve();
		}
	}
	public class UnionFind {
		int Max_N = 100000;
		int[] par = null; // 親
		int[] rank = null; // 木の深さ
		
		public UnionFind(int n){
			Max_N = n;
			init();
		}
		
		
		// n要素で初期化
		void init(){
			par = new int[Max_N];
			rank = new int[Max_N];
			for(int i = 0; i < Max_N; i++){
				par[i] = i;
				rank[i] = 0;
			}
		}
		
		int find(int x){
			if(par[x] == x){
				return x;
			}
			else{
				return par[x] = find(par[x]);
			}
		}
		
		void unite(int x, int y){
			x = find(x);
			y = find(y);
			if(x == y) return;
			
			if(rank[x] < rank[y]){
				par[x] = y;
			}
			else{
				par[y] = x;
				if(rank[x] == rank[y]) rank[x]++;
			}
		}
		
		boolean same(int x, int y){
			return find(x) == find(y);
		}
	}

}
