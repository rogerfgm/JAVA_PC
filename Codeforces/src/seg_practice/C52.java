package seg_practice;


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Integer.*;
import static java.lang.Math.*;



public class C52 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int N = 0;
	static BufferedReader br = null;
	
	public void solve() throws Exception{
		
		N = parseInt(br.readLine());
		
		SegmentTree sg = new SegmentTree(N);
		
		String[] sp = br.readLine().split(" ");
		for(int i = 0; i < N; i++){
			sg.update(i, parseInt(sp[i]));
		}
		
		int M = parseInt(br.readLine());
	
		for(int i = 0; i < M; i++){
			String s = br.readLine();
			String[] ss = s.split(" ");
			if(ss.length == 2){
				int f = parseInt(ss[0]);
				int t = parseInt(ss[1]);
				int min = Integer.MAX_VALUE;
				if(t < f){
					min = min(min, sg.query(f, N-1));
					min = min(min, sg.query(0, t));
				}
				else{
					min = min(min, sg.query(f, t));
				}
				
				
				out.println(min);
			}
			else{
				int f = parseInt(ss[0]) ;
				int t = parseInt(ss[1]) ;
				int v = parseInt(ss[2]);
				if(t < f){
					int nt = N-1;
					sg.add(f, nt, v);
					sg.add(0, t, v);
				}
				else{
					sg.add(f, t, v);
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

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
		br = new BufferedReader(new InputStreamReader(System.in));
		//sc =  new Scanner(System.in);
		C52 t = new C52();
		t.solve();

	}
	

	public class SegmentTree {
		int n;
		int dat[] = null;
		int add[] = null;
		
		public SegmentTree(int N){
			init(N);
		}
		

		void init(int n_){
			
			
			n = 1;
			while(n < n_) n *= 2;
			dat = new int[2*n];
			add = new int[2*n];
			for (int i = 0; i < 2 * n; i++) dat[i] = Integer.MAX_VALUE;
		}
		

		void  update(int k, int a){
			
			k += n-1;
			dat[k] = a;
			while(k > 0){
				k = (k-1) / 2;
				dat[k] = Math.min(dat[k*2+1], dat[k*2+2]);
			}
		}
		

		int query(int a, int b){
			return query(a, b, 0, 0, n-1);
		}
		void add(int a, int b, int v){
			add(a, b, 0, 0, n-1, v);
		}
		void add(int a, int b, int k, int l, int r, int v){
			
			if(r < a || b < l) return;
			
		
			if( a <= l && r <= b){
				add[k] += v;
				
				
				while(k > 0){
					k = (k-1) / 2;
					dat[k] = min(dat[2*k+1] + add[2*k+1], dat[2*k+2] + add[2*k+2]);
				}
				
				
				
				return;
			}
			
		
			add(a, b, k*2+1, l, (l+r) / 2, v);
			add(a, b, k*2+2, (l+r)/2+1, r, v);
			
				
			
		}
		
		int query(int a, int b, int k, int l, int r){
		
			if(r < a || b < l) return Integer.MAX_VALUE;
			
		
			if( a <= l && r <= b) return dat[k] + add[k];
			else{
		
				int vl = query(a, b, k*2+1, l, (l+r) / 2);
				int vr = query(a, b, k*2+2, (l+r)/2+1, r);
				return min(vl, vr) + add[k];
				
			}
		}
			
	}
	

	
}
