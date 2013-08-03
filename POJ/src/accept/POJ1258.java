package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class POJ1258 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	public void solve() throws Exception{
		int[][] d = new int[N][N];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				d[i][j] = sc.nextInt();
			}
		}
		boolean[] f = new boolean[N];
		Queue<Data> que = new PriorityQueue<Data>(100, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				return o1.d - o2.d;
			}
		});
		que.add(new Data(0, 0));
		int ans = 0;
		while(que.size() > 0){
			Data dt = que.poll();
			if(f[dt.p]){
				continue;
			}
			f[dt.p] = true;
			ans += dt.d;
			for(int i = 0; i < N; i++){
				if(i == dt.p){
					continue;
				}
				Data ndt = new Data(i, d[dt.p][i]);
				que.add(ndt);
			}
		}
		out.println(ans);
	}

	class Data{
		public Data(int p, int d){
			this.p = p;
			this.d = d;
		}
		int p = 0;
		int d = 0;
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
		POJ1258 p = new POJ1258();
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
		
			p.solve();
		}
	}
}
