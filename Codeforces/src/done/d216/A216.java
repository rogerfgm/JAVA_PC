package done.d216;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class A216 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] t = nextInts();
		int n = t[0];
		int b = t[1];
		int p = t[2];
		int[] d = nextInts();
		int B = b;
		int P = p;
		int ans = 0;
		for(int i = 0; i < d.length; i++){
			if(d[i] == 2){
				if(P > 0){
					P--;
				}
				else if(B > 0){
					B--;
				}
				else{
					ans++;
				}
			}
			else{
				if(B > 0){
					B--;
				}
				else{
					ans++;
				}
			}
		}
		out.println(ans);;
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
		A216 t = new A216();
		t.solve();
		bw.close();
	}

}
