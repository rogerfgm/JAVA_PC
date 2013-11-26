package done.d173;


import java.io.*;
import java.util.*;
import java.math.*;
	

public class E173 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;
	Data trie = null;
	int M = 40;
	
	public void solve() throws Exception{
		int n = sc.nextInt();
		long[] d = new long[n];
		long f = 0;
	
		for(int i = 0; i < n; i++){
			d[i] = sc.nextLong();
			f ^= d[i];
		}
		long ans = f;
		trie = new Data();
		Data tmp = trie;
		for(int i = 0; i < M-1; i++){
			tmp.zero = new Data();
			tmp = tmp.zero;
		}
		tmp.num = 0;
		trie.num = 0;
		
		long r = 0;
		for(int i = n-1; i >= 0; i--){
			r ^= d[i];
			f ^= d[i];
			
			
			Data t = trie;
			for(int j = 0; j < M; j++){
				int si = M-1 - j;
				boolean flag = false;
				if( ((r >> si) & 1) > 0){
					flag = true;
				}
				if(flag){
					if(t.one != null){
						t = t.one;
					}
					else{
						t.one = new Data();
						t = t.one;
					}
				}
				else{
					if(t.zero != null){
						t = t.zero;
					}
					else{
						t.zero = new Data();
						t = t.zero;
					}
				}
			}
			t.num = r;
			
			t = trie;
			for(int j = 0; j < M; j++){
				if(t.one == null && t.zero == null){
					break;
				}
				int si = M-1 - j;
				boolean flag = false;
				if( ((f >> si) & 1) > 0){
					flag = true;
				}
				if(flag){
					if(t.zero != null){
						t = t.zero;
					}
					else{
						t = t.one;
					}
				}
				else{
					if(t.one != null){
						t = t.one;
					}
					else{
						t = t.zero;
					}
				}
			}
			
			ans = Math.max(ans, f ^ t.num);
			
		}
		
		
		
		
		System.out.println(ans);
		
	}
	

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
    
    class Data{
    	long num = -1;
    	public Data one = null;
    	public Data zero = null;
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
	
		sc =  new Scanner(System.in);
		E173 t = new E173();
		t.solve();

	}
}
