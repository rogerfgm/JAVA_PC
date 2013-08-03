package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

//全域木で最大の枝を追加していくだけ
public class POJ2377 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	public void solve() throws Exception{
		List<List<Data>> list = new ArrayList<List<Data>>();
		for(int i = 0; i < N; i++){
			List<Data> l = new ArrayList<Data>();
			list.add(l);
		}
		for(int i = 0; i < M; i++){
			int f = sc.nextInt() -1;
			int t = sc.nextInt() -1;
			int c = sc.nextInt();
			list.get(f).add(new Data(t, c));
			list.get(t).add(new Data(f, c));
		}
		

		PriorityQueue<Data> que = new PriorityQueue<Data>(1000, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				return o2.c - o1.c;
			}
		});
		
		boolean[] used = new boolean[N];
		used[0] = true;
		List<Data> l = list.get(0);
		for(Data d : l){
			Data nd = new Data(d.t, d.c);
			que.add(nd);
		}
		
		int ans = 0;
		while(que.size() > 0){
			Data d = que.poll();
			if(used[d.t]){
				continue;
			}
			used[d.t]= true; 
	
			ans += d.c;
			l = list.get(d.t);
			for(Data dt : l){
				if(used[dt.t] ) continue;
				Data nd = new Data(dt.t, dt.c);
				que.add(nd);
			}
		}
		
		for(int i = 0; i < N; i++){
			if(!used[i]){
				out.println("-1");
				return;
			}
		}
		out.println(ans);
	}

	class Data{
		public Data(int t, int c){
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
		POJ2377 p = new POJ2377();
		while(true){
			
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
}
