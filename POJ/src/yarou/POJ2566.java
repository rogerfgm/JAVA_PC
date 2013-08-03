package yarou;




import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// WA
// 累積和をソートすると、その値についてしゃくとりが使えるのが...
public class POJ2566 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int K = 0;

	Data[] da = null;
	public void solve() throws Exception{

		da = new Data[N+1];
		
		int sum = 0; 
		da[0] = new Data(0, 0);
		
		for(int i = 1; i <= N; i++){
			int n = sc.nextInt();
			sum += n;
			da[i] = new Data(sum, i);
		
		}
		
		Arrays.sort(da, new Comparator<Data>() {
			public int compare(Data o1, Data o2) {
				return o1.sum - o2.sum;
			}
		});
		
		for(int i = 0; i < K; i++){
			int t = sc.nextInt();
			int[] ret = checka(t);
			out.println(ret[0] + " " + ret[1] + " " + ret[2]);
		}
		
		
	}
	
	int[] checka(int t){
		int[] ret = new int[3];
		
		int val = -INF;
		int vall = 0;
		int valr = 0;
		
		int l = 0;
		int r = 0;
		while(r < da.length){
			
			int v = da[r].sum - da[l].sum;
			if(abs(t-v) < abs(t-val)){
				val = v;
				vall = min(da[r].idx, da[l].idx) + 1;
				valr = max(da[r].idx, da[l].idx);
			}
			if(v < t){
				r++; 
			}
			else{
				l++;
			}
		}
		ret[0] = val;
		ret[1] = vall;
		ret[2] = valr;
		return ret;
	}
	
	class Data{
		public Data(int sum, int idx){
			this.sum = sum;
			this.idx = idx;
		}
		int sum = 0;
		int idx = 0;
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
		POJ2566 p = new POJ2566();
	
		while(true){
			try{
				N = sc.nextInt();
				K = sc.nextInt();
			}
			catch(Exception ex){
				break;
			}
			if(N == 0 && K == 0) break;
			p.solve();
		}
	}

}
