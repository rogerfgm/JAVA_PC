package done.d174;


import java.io.*;
import java.util.*;
import java.math.*;
	




public class A174 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static PrintStream out = null;
	int n = 0;
	
	public void solve() throws Exception{
		int p = sc.nextInt();

		int ans = 0;
		for(int i = 1; i <= p; i++){
			long b = i;
			long a = i;
			boolean flag = true;
			for(int j = 1; j <= p-1; j++){
				long mod = (a -1) % p;
				if(j < p-1){
					if(mod == 0){
						flag = false;
						break;
					}
				}
				else{
					if(mod != 0){
						flag = false;
					}
				}
				a *= b;
				a %= p;
			}
			if(flag){
				ans++;
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
		A174 t = new A174();
		t.solve();

	}

}
