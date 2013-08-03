package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// 平均最大化
// v - x * w でソートして大きい方から足して0以上かの判定で２分探索
public class POJ3111 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.00000001;

	static int N = 0;
	static int M = 0;
	int[] v = null;
	int[] w = null;
	public void solve() throws Exception{
		v = new int[N];
		w = new int[N];
		for(int i = 0; i < N; i++){
			v[i] = sc.nextInt();
			w[i] = sc.nextInt();
		}

		double l = 0;
		double r = 100000000;
		while(l + DF < r){
			double m = (l + r) / 2;
			if(check(m)){
				l = m;
			}
			else{
				r = m;
			}
		}
		Data[] d = new Data[N];
		for(int i = 0; i < N; i++){
			d[i] = new Data(v[i] - l * w[i], i+1);
		}
		Arrays.sort(d, new Comparator<Data>() {

			public int compare(Data o1, Data o2) {
				if(o1.d < o2.d){
					return 1;
				}
				else if(o1.d == o2.d){
					return 0;
				}
				return -1;
			}
		});

		for(int i = 0; i < M; i++){
			if(i != 0){
				out.print(" ");
			}
			out.print(d[i].idx);
		}
		out.println();
	}

	class Data{
		public Data(double d, int idx){
			this.d = d;
			this.idx = idx;
		}
		double d = 0;
		int idx = 0;
	}
	
	boolean check(double x){
		double[] d = new double[N];
		for(int i = 0; i < N; i++){
			d[i] = v[i] - x * w[i];
		}
		Arrays.sort(d);
		double s = 0;
		for(int i = 0; i < M; i++){
			s += d[N-i-1];
		}
		if(s >= 0){
			return true;
		}
		else{
			return false;
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
		POJ3111 p = new POJ3111();
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
