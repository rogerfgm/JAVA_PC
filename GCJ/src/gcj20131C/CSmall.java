package gcj20131C;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class CSmall {
	static Scanner sc = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	int N = 0;
	int M = 0;
	
	public void solve() throws Exception{
		N = sc.nextInt();
		int[] d = new int[N]; // the day of first attack
		int[] n = new int[N]; // attack number
		int[] w = new int[N];
		int[] e = new int[N];
		int[] s = new int[N]; // strength
		int[] b = new int[N]; // day between move
		int[] m = new int[N]; // move east
		int[] c = new int[N]; // change of strength
		
		int cnt = 0;
		for(int i = 0; i < N; i++){
			d[i] = sc.nextInt();
			n[i] = sc.nextInt();
			w[i] = sc.nextInt();
			e[i] = sc.nextInt();
			s[i] = sc.nextInt();
			b[i] = sc.nextInt();
			m[i] = sc.nextInt();
			c[i] = sc.nextInt();
			cnt+= n[i];
		}
			
		int[] W = new int[1000];
		Data[] dt = new Data[cnt];
		ArrayList<Data> list = new ArrayList<Data>();
		for(int i = 0; i < N; i++){
			for(int j = 0; j < n[i]; j++){
				int day = d[i] + j * b[i];
				int stren = s[i] + j * c[i];
				int west = w[i] + j * m[i];
				int east = e[i] + j * m[i];
				list.add(new Data(day, west, east-1, stren));
			}
		}
		dt = list.toArray(dt);
		Arrays.sort(dt, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				return o1.d - o2.d;
			}
		});
		
		int st = 0;
		int ed = 0;
		int ans = 0;
		N = dt.length;
		while(true){
			if(st == N) break;
			while(ed < N){
				if(dt[st].d == dt[ed].d){
					ed++;
				}
				else{
					break;
				}
			}
			for(int i = st; i < ed; i++){
				int west = pos(dt[i].w);
				int east = pos(dt[i].e);
				boolean flag = false;
				for(int j = west; j <= east; j++){
					if(W[j] < dt[i].s){
						flag = true;
					}
				}
				if(flag){
					ans++;
				}
			}
			for(int i = st; i < ed; i++){
				int west = pos(dt[i].w);
				int east = pos(dt[i].e);
				
				for(int j = west; j <= east; j++){
					W[j] = max(W[j], dt[i].s);
				}
			}
			
			st = ed;
		}
		out.println(ans);
	}
	
	class Data{
		public Data(int d, int w, int e, int s){
			this.d = d;
			this.w = w;
			this.e = e;
			this.s = s;
		}
		int d = 0;
		int w = 0;
		int e = 0;
		int s = 0;
	}
	
	int pos(int p){
		return p + 500;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("C-small-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		CSmall b = new CSmall();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
