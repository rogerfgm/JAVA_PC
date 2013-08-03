package gcj2013_2;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class ASubmit {
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
	
	long MOD  = 1000002013;
	
	public void solve() throws Exception{
		N = sc.nextInt();
		M = sc.nextInt();
		
		long ans = 0;
		List<Data> list = new ArrayList<Data>();
		Pt[] pts = new Pt[M];
		for(int i = 0; i < M; i++){
			Pt pt = new Pt();
			pt.f = sc.nextLong();
			pt.t = sc.nextLong();
			pt.n = sc.nextLong();
			pts[i] = pt;
		}
		
		Comparator<Pt> cmp =  new Comparator<Pt>() {

			@Override
			public int compare(Pt o1, Pt o2) {
				if(o1.f == o2.f){
					return 0;
				}
				if(o1.f < o2.f){
					return -1;
				}
				else if(o1.f > o2.f){
					return 1;
				}
				return 0;
			}
		};
		
		Arrays.sort(pts, cmp);
		
		long org = 0;
	
		List<Data> done = new ArrayList<Data>();
		List<Long> from = new ArrayList<Long>();
		List<Long> to = new ArrayList<Long>();
		
		
		for(int i = 0; i < pts.length; i++){
			
			Pt pt = pts[i];
			org += get(pt.f, pt.t) * pt.n;
			
			int delmax = -1;
			for(int j = 0; j < to.size(); j++){
				if(to.get(j).longValue() < pt.f){
					delmax = j;
					Data dt = new Data();
					dt.f = from.get(j);
					dt.t = to.get(j);
					done.add(dt);
				}
			}
			for(int j = delmax; j >= 0; j--){
				to.remove(j);
				from.remove(j);
			}
			
			boolean f = false;
			
//			if(to.size() > 0 && to.get(0).longValue() == pt.t){
//				f = true;
//				to.remove(0);
//				from.remove(0);
//			}
			
			for(int j = 0; j < pt.n; j++){
				from.add(pt.f);
				to.add(pt.t);
			}
				
				
			Collections.sort(to);
			Collections.sort(from, new Comparator<Long>() {

				@Override
				public int compare(Long o1, Long o2) {
					// TODO Auto-generated method stub
					return -o1.compareTo(o2);
				}
			});
			
		}
		for(int i = 0; i < to.size(); i++){
			Data nd = new Data();
			nd.f = from.get(i);
			nd.t = to.get(i);
			done.add(nd);
			
		}
		for(int i = 0; i < done.size(); i++){
			Data dt = done.get(i);
			ans += get(dt.f, dt.t);
			ans %= MOD;
		}
		ans = org - ans;
		out.println(ans);
	}
	
	long get(long f, long t)throws Exception{
		if(f > t){
			throw new Exception("test");
		}
		long n = t - f;
		long a = n*N + (-n * n + n) / 2;
		return a;
	}
	
	class Data{
		long f = 0;
		long t = 0;
	}
	
	class Pt{
		long f = 0;
		long t = 0;
		long n = 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("A-small-attempt3.in");
		//File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		//br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		ASubmit b = new ASubmit();
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
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
