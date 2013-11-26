package done.d184;


import java.io.*;
import java.util.*;
import java.math.*;
	




public class C {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;
	
	public void solve() throws Exception{
		int n = sc.nextInt();
		int[] d = new int[n];
		Set<Integer> set = new HashSet<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++){
			d[i] = sc.nextInt();
	
			int now = d[i];
			while(true){
				boolean f = false;
				if(set.contains(now)){
					f = true;
					set.remove(now);
					now++;
				}
				else{
					set.add(now);
				}
				if(!f){
					break;
				}
			}
		}
		

		
		Integer[] a = new Integer[set.size()];
		a = set.toArray(a);
		Arrays.sort(a);
		
		
		long ans = 0;
		int now = 0;
		int idx = 0;

		while(idx < a.length){
			if(a[idx].intValue() == now){
				now++;
				idx++;
			}
			else{
				int dif = a[idx].intValue() - now;
				
				ans += dif;
				now = a[idx] + 1;
				idx++;
			}
		}

		out.println(ans);
		return;
	
		
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
	
		sc =  new Scanner(System.in);
		C t = new C();
		t.solve();

	}

}
