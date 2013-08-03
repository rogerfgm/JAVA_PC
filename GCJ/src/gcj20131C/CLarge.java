package gcj20131C;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class CLarge {
	static Scanner sc = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	int N = 0;
	int M = 0;
	
	Map<Integer, Integer> map = null;
	
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
			
	
		Data[] dt = new Data[cnt];
		ArrayList<Data> list = new ArrayList<Data>();
		Set<Integer> poss = new HashSet<Integer>();
		for(int i = 0; i < N; i++){
			for(int j = 0; j < n[i]; j++){
				int day = d[i] + j * b[i];
				int stren = s[i] + j * c[i];
				int west = w[i] + j * m[i];
				int east = e[i] + j * m[i];
				list.add(new Data(day, west, east-1, stren));
				poss.add(west);
				poss.add(east-1);
			}
		}
		dt = list.toArray(dt);
		Arrays.sort(dt, new Comparator<Data>() {
			@Override
			public int compare(Data o1, Data o2) {
				return o1.d - o2.d;
			}
		});
		Integer[] ps = new Integer[0];
		ps = poss.toArray(ps);
		Arrays.sort(ps);
		map = new HashMap<Integer, Integer>();
		int idx = 0;
		for(int i = 0; i < ps.length; i++){
			if(i != 0){
				if(ps[i].intValue() == ps[i-1].intValue()+1){
					idx++;
				}
				else{
					idx += 2;
				}
			}
			map.put(ps[i], idx);
		}
		
		SegmentTreeAdd sg = new SegmentTreeAdd(idx+1);
		//SegmentTreeAdd sg = new SegmentTreeAdd(10000);
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
			
				int min = sg.query(west, east);
				
				if(min < dt[i].s){
					ans++;
				}
			}
			for(int i = st; i < ed; i++){
				int west = pos(dt[i].w);
				int east = pos(dt[i].e);
				sg.add(west, east, dt[i].s);
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
		//return p + 500;
		return map.get(p);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		sysout.println(new Date());
		File file = new File("C-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		CLarge b = new CLarge();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
		sysout.println(new Date());
	}
	
	public class SegmentTreeAdd {

		int n;
		int min[] = null;
		int add[] = null;
		
		public SegmentTreeAdd(int N){
			init(N);
		}
		
		void init(int n_){
			n = 1;
			while(n < n_) n *= 2;
			min = new int[2*n];
			add = new int[2*n];
		}
		


		int query(int a, int b){
			return query(a, b, 0, 0, n-1);
		}
		void add(int a, int b, int v){
			add(a, b, 0, 0, n-1, v);
		}
		void add(int a, int b, int k, int l, int r, int v){
			
			if(r < a || b < l) return;
			
			if( a <= l && r <= b){
				add[k] = max(add[k], v); 
				
				// update parent value
				while(k > 0){
					k = (k-1) / 2;
					min[k] = min( max(min[2*k+1], add[2*k+1]), max(min[2*k+2], add[2*k+2]));
				}
				return;
			}
		
			add(a, b, k*2+1, l, (l+r) / 2, v);
			add(a, b, k*2+2, (l+r)/2+1, r, v);
		}
		
		int query(int a, int b, int k, int l, int r){
		
			if(r < a || b < l) return Integer.MAX_VALUE;
		
			if( a <= l && r <= b) return max(min[k] , add[k]);
			else{
				int vl = query(a, b, k*2+1, l, (l+r) / 2);
				int vr = query(a, b, k*2+2, (l+r)/2+1, r);
				return max(min(vl, vr), add[k]);
			}
		}

	}


}
