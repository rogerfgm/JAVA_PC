


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class C218 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		String s = br.readLine();
		int[] ns = nextInts();
		int[] ps = nextInts();
		long r = nextLong();
		long[] ma = new long[3];
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) == 'B'){
				ma[0]++;
			}
			else if(s.charAt(i) == 'S'){
				ma[1]++;
			}
			else{
				ma[2]++;
			}
		}
		
		long max = (long)1000000 * 1000000 + 200;
		long min = 0;
		while(min + 1 < max){
			long mid = (min + max) / 2;
			long req = 0;
			for(int i = 0; i < 3; i++){
				long num = ma[i] * mid - ns[i];
				if(num <= 0){
					continue;
				}
				req += num * ps[i];
			}
			if(req <= r){
				min = mid;
			}
			else{
				max = mid;
			}
		}
		out.println(min);
		
	}
	

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
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
		C218 t = new C218();
		t.solve();
		bw.close();
	}

}
