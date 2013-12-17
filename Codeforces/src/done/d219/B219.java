package done.d219;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class B219 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		long[] t = nextLongs();
		long w = t[0];
		long m = t[1];
		long k = t[2];
		
		long len = 0;
		long tm = m;
		while(tm > 0){
			tm /= 10;
			len++;
		}
	
		long ans = 0;
		BigInteger bk = new BigInteger(Long.toString(k));
		while(true){
			long next = (long)pow(10, len)-1;
			long cnt = next - m + 1;
			BigInteger cntB = new BigInteger(Long.toString(cnt));
			BigInteger lenB = new BigInteger(Long.toString(len));
			BigInteger bw = new BigInteger(Long.toString(w));
			if(cntB.multiply(lenB).multiply(bk).compareTo(bw) < 0){
				w -= cntB.multiply(lenB).multiply(bk).longValue();
				ans += cnt;
				len++;
				m = next + 1;
			}
			else{
				cnt = w / (lenB.multiply(bk).longValue());
				ans += cnt;
				break;
			}
		}
		out.println(ans);
		
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
	
	private long[] nextLongs() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		long[] r = new long[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseLong(sp[i]);
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
		B219 t = new B219();
		t.solve();
		bw.close();
	}

}
