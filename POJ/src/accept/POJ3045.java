package accept;

import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// 本では２分探索に分類されていたが、ただソートしただけで通っちゃった
public class POJ3045 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	public void solve() throws Exception{

		Data[] d = new Data[N];
		for(int i = 0; i < N; i++){
			int w = sc.nextInt();
			int st = sc.nextInt();
			d[i] = new Data(w, st);
		}
		Arrays.sort(d, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				int n1 = o1.st - o2.w;
				int n2 = o2.st - o1.w;
				if(n1 == n2){
					return o1.w - o2.w;
				}
				return n1-n2;
			}
		});
		int sw = 0;
		int ans = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++){
			ans = max(ans, sw - d[i].st);
			sw += d[i].w;
		}
	
		out.println(ans);
	}
	
	class Data{
		public Data(int w, int st){
			this.w = w;
			this.st = st;
		}
		int w = 0;
		int st = 0;
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
		POJ3045 p = new POJ3045();
	
		while(true){
			try{
				N = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if( N == 0) break;
			p.solve();
		}
	}

}
