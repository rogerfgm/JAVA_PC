package done.d216;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class B216 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] t = nextInts();
		int n = t[0];
		int k = t[1];
		int l = t[2];
		int r = t[3];
		int sall = t[4];
		int sk = t[5];
		
		int max = 0;
		List<Integer> ans = new ArrayList<Integer>();
		for(int i = r; i >= l; i--){
			if(i * k == sk){
				max = i;
				for(int j = 0; j < k; j++){
					ans.add(i);
				}
				break;
			}
			else if(i * k > sk && (i-1) * k < sk){
				int bn = sk - (i - 1) * k;
				int sn = k - bn;
				for(int j = 0; j < bn; j++){
					ans.add(i);
				}
				for(int j = 0; j < sn; j++){
					ans.add(i-1);
				}
				max = i-1;
				break;
			}
		}
		for(int i = max; i >= l; i--){
			if(i * (n-k) == sall - sk){
				for(int j = 0; j < n-k; j++){
					ans.add(i);
				}
				break;
			}
			else if(i * (n-k) > sall - sk && (i-1) *(n- k) < sall - sk){
				int bn = sall - sk - (i - 1) * (n-k);
				int sn = n-k - bn;
				for(int j = 0; j < bn; j++){
					ans.add(i);
				}
				for(int j = 0; j < sn; j++){
					ans.add(i-1);
				}
				
				break;
			}
		}
		for(int i = 0; i < ans.size(); i++){
			if(i != 0){
				out.print(" ");
			}
			out.print(ans.get(i));
		}
		
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
		B216 t = new B216();
		t.solve();
		bw.close();
	}

}
