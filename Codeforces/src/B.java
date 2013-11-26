


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class B {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		int[] t = nextInts();
		int n = t[0];
		int m = t[1];
		int[] d = nextInts();
		
		int[] is = new int[m];
		for(int i = 0; i < m; i++){
			is[i] = nextInt();
		}
		
		int[] ans = new int[n];
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i = 0; i < d.length; i++){
			if(!map.containsKey(d[i])){
				map.put(d[i], 0);
			}
			int num = map.get(d[i]);
			num++;
			map.put(d[i], num);
		}
		for(int i = 0; i < n; i++){
			ans[i] = map.keySet().size();
			int ck = d[i];
			int num = map.get(ck);
			num--;
			if(num == 0){
				map.remove(ck);
			}
			else{
				map.put(ck, num);
			}
		}
		for(int i = 0; i < m; i++){
			int a = ans[is[i]-1];
			out.println(a);
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
		B t = new B();
		t.solve();
		bw.close();
	}

}
