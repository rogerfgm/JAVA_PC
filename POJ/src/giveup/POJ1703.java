package giveup;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// TLE食らう。
// Union Findの実装が悪いのか、入力処理のhandlingが悪いのか。
// その他処理は間違ってないと思う。
public class POJ1703 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	
	String ns = "Not sure yet.";
	String df = "In different gangs.";
	String sm = "In the same gang.";
	public void solve() throws Exception{

		UnionFind uf = new UnionFind(2*N+2);

		
		for(int i = 0; i < M; i++){
			String s = sc.next();
			if(s.equals("D")){
				int a = sc.nextInt();
				int b = sc.nextInt();
				uf.unite(a, N + b);
				uf.unite(N + a, b);
			}
			else{
				int a = sc.nextInt();
				int b = sc.nextInt();
				if(uf.same(a, N+b)){
					out.println(df);
				}
				else if(uf.same(a, b)){
					out.println(sm);
				}
				else{
					out.println(ns);
				}
			}
		}
		
		
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
		POJ1703 p = new POJ1703();
		int T = sc.nextInt();
		int t = 1;
		
		while(t++ <= T){
			
			try{
				N = sc.nextInt();
				M = sc.nextInt();
				if(N == 0 && M == 0){
					break;
				}
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
