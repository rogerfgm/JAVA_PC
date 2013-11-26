package done.d174;


import java.io.*;
import java.util.*;
import java.math.*;
	




public class C174 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;	
	
	int N = 200100;
    int d[] = new int[N+1];

	
	public void solve() throws Exception{
		n = sc.nextInt();
		int p = 1;
	
		int add = 0;
		double sum = 0;
		Map<Integer, Integer> addm = new HashMap<Integer, Integer>();
		for(int i = 0; i < n; i++){
			int cmd = sc.nextInt();
			if(cmd == 1){
				int to = sc.nextInt();
				int val = sc.nextInt();
				sum += to * val;
				if(addm.containsKey(to)){
					int nval = val + addm.get(to);
					addm.put(to, nval);
				}
				else{
					addm.put(to, val);
				}
				if(to == p){
					add += val;
				}
			}
			else if(cmd == 2){
				p++;
				d[p] = sc.nextInt();
				sum += d[p];
				if(addm.containsKey(p-1)){
					add -= addm.get(p-1);
				}
				sum += add;
			}
			else if(cmd == 3){
				sum -= d[p];
				sum -= add;
				p--;
				if(addm.containsKey(p)){
					add += addm.get(p);
				}
				if(addm.containsKey(p+1)){
					int nval = 0;
					if(addm.containsKey(p)){
						nval = addm.get(p);
					}
					nval += addm.get(p+1);
					addm.remove(p+1);
					addm.put(p, nval);
				}
			}
			
			double ave = sum / p;
			out.println(ave);
			
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
	
		sc =  new Scanner(System.in);
		C174 t = new C174();
		t.solve();

	}

}
