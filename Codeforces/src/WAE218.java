


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class WAE218 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static PrintStream err = System.err;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		N = nextInt();
		int[] D = nextInts();
		int K = nextInt();
		St[] sts = new St[N];
		for(int i = 0; i < N; i++){
			sts[i] = new St(i+1, D[i]);
		}
		Arrays.sort(sts, new Comparator<St>(){

			@Override
			public int compare(St o1, St o2) {
				return o1.x - o2.x;
			}
			
		});
		int ans = 0;
	
		long lensum = 0;

		long minsum = lensum;
		
		for(int i = 1; i < N-K+1; i++){
			if(sts[i].idx == 65 || sts[i].idx == 42){
				err.println( + sts[i-1].x + " " + sts[i].x);
				err.println( + sts[i+K-2].x + " " + sts[i+K-1].x);
			}
			//err.println(sts[i].idx);
			
			long minus = (long)(sts[i].x - sts[i-1].x) * (long)K;
			long plus = (long)(sts[i+K-1].x - sts[i+K-2].x) * (long)K;
			lensum += plus - minus;
			if(lensum < minsum){
				minsum = lensum;
				ans = i;
			}
		}
		for(int i = 0; i < K; i++){
			if(i != 0){
				out.print(" ");
			}
			out.print(sts[ans+i].idx);
		}
		
	}
	
	class St{
		public St(int idx, int x){
			this.idx = idx;
			this.x = x;
		}
		int idx = 0;
		int x = 0;
	}
	
    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
    
    private String nextS() throws IOException{
		String s = br.readLine();
		return s;
	}

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
	}
	
	private long nextLong() throws IOException{
		String s = br.readLine();
		return parseLong(s);
	}
	
	private int[] nextInts() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		int[] r = new int[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseInt(sp[i]);
		}
		return r;
	}
    
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		WAE218 t = new WAE218();
		t.solve();
		bw.close();
	}

}
