package done.d219;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class A219 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		N = nextInt() * 2;
		
		int[] d = new int[10];
		for(int i = 0; i < 4; i++){
			String s = nextS();
			for(int j = 0; j < 4; j++){
				char c = s.charAt(j);
				if(c == '.') continue;
				int idx = c - '0';
				d[idx]++;
			}
		}
		String no = "NO";
		String yes = "YES";
		
		for(int i = 1; i <= 9; i++){
			if(d[i] > N){
				out.println(no);
				return;
			}
		}
		out.println(yes);
	
		
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
		A219 t = new A219();
		t.solve();
		bw.close();
	}

}
