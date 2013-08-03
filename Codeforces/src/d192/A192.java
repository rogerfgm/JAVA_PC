package d192;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class A192 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		int[] ti = nextInts();
		int r = ti[0];
		int c = ti[1];
		boolean[][] f = new boolean[r][c];
		for(int i = 0; i < r; i++){
			String s = br.readLine();
			for(int j = 0; j < s.length(); j++){
				if(s.charAt(j) == '.'){
					f[i][j] = true;
				}
			}
		}
		boolean[][] f2 = new boolean[r][c];
		
		int ans = 0;
		for(int i = 0; i < r; i++){
			int cnt = 0;
			boolean flag = true;
			for(int j = 0; j < c; j++){
				if(!f[i][j] ) flag = false;
			}
			if(flag){
				for(int j = 0; j < c; j++){
					if(!f2[i][j]){
						cnt++;
						f2[i][j] = true;
					}
				}
				ans += cnt;
			}
			
		}
		for(int j = 0; j < c; j++){
			int cnt = 0;
			boolean flag = true;
			for(int i = 0; i < r; i++){
				if(!f[i][j] ) flag = false;
			}
			if(flag){
				for(int i = 0; i < r; i++){
					if(!f2[i][j] ) {
						cnt++;
						f2[i][j] = true;
					}
				}
				ans += cnt;
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
		A192 t = new A192();
		t.solve();
		bw.close();
	}

}
