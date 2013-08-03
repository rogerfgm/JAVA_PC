package d168;
import java.io.*;
import java.util.*;
	

/*
 * TLEだけどなんでTLEなのか理由がわからない‥
 */


public class C168 {
	static Scanner sc = null;
	static PrintWriter out = null;
	int n = 0;
	
	
	
	public void solve() throws Exception{

		n = sc.nextInt();
		long k = sc.nextInt();
		if(k == 1){
			System.out.println(n);
			return;
		}
		int[] d = new int[n];
		for(int i = 0; i < n; i++){
			d[i] = sc.nextInt();
		}
		Arrays.sort(d);
		Map<Integer, Integer> idxm = new HashMap<Integer, Integer>();
		for(int i = 0; i < d.length; i++){
			idxm.put(d[i], i);
		}
		
		boolean[] used = new boolean[n];
		
		int ans = 0;
		for(int i = 0; i < n; i++){
			if(used[i]){
				continue;
			}
			used[i] = true;
			long ck = d[i] * k;
			
			if(ck < Integer.MAX_VALUE && idxm.containsKey((int)ck)){
				int num = 1;
				
				while(ck < Integer.MAX_VALUE && idxm.containsKey((int)ck)){
					num++;
					int idx = idxm.get((int)ck);
					used[idx] = true;
					ck *= k;
				}
				ans += (num + 1) / 2;
			}
			else{
				ans++;
			}
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

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = new PrintWriter(System.out);
		sc =  new Scanner(System.in);
		C168 t = new C168();
		t.solve();
	
	
	}
	
	
}