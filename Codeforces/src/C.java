


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class C {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	String yes = "YES";
	String no = "NO";
	public void solve() throws Exception{
		String s = br.readLine();
		char[] c = s.toCharArray();
		int n = s.length();
		int m = nextInt();
		int[] b = new int[n];
		for(int i = 0; i < n - 2; i++){
			if(c[i] == 'z' && c[i+1] == 'y' && c[i+2] == 'x') b[i] = 1;
			if(c[i] == 'x' && c[i+1] == 'z' && c[i+2] == 'y') b[i] = 1;
			if(c[i] == 'y' && c[i+1] == 'x' && c[i+2] == 'z') b[i] = 1;
		}
		
		BIT bit = new BIT(n);
		BIT bitx = new BIT(n);
		BIT bity = new BIT(n);
		BIT bitz = new BIT(n);
		for(int i = 0; i < n; i++){
			if(c[i] == 'x'){
				bitx.add(i+1, 1);
			}
			if(c[i] == 'y'){
				bity.add(i+1, 1);
			}
			if(c[i] == 'z'){
				bitz.add(i+1, 1);
			}
			bit.add(i+1, b[i]);
		}
		for(int i = 0; i < m; i++){
			int[] t = nextInts();
			int l = t[0];
			int r = t[1];
			if(r - l < 2){
				out.println(yes);
			}
			else{
				
				
				int x = bitx.sum(r) - bitx.sum(l-1);
				int y = bity.sum(r) - bity.sum(l-1);
				int z = bitz.sum(r) - bitz.sum(l-1);
				boolean flag = false;
				if(x > 0 && y > 0 && z > 0){
					int maxn = max(x, y);
					maxn = max(maxn, z);
					int minn = min(x, y);
					minn = min(minn, z);
					if(maxn - minn <= 1){
						flag = true;
					}
				}

				if(flag){
					out.println(yes);
				}
				else{
					out.println(no);
				}
			}
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
		C t = new C();
		t.solve();
		bw.close();
	}

	class BIT {

		public BIT(int n){
			this.N = n;
			bit = new int[N+2];
		}
		
	    int N = 100000;
	    int bit[] = null; 
		
	  
	    int sum0(int i){
	    	return sum(i+1);
	    }
	    
	    void add0(int i, int x){
	    	add(i+1, x);
	    }
	    

	    int sum(int i){
	    	int s = 0;
	    	while(i > 0){
	    		s += bit[i];
	    		i-=i&-i;
	    	}
	    	return s;
	    }
	    

	    void add(int i, int x){
	    	while(i <= N){
	    		bit[i] = bit[i] + x;
	    		i += i & -i;
	    	}
	    }
	}
	
	
}
