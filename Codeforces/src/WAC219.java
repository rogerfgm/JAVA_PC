


import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class WAC219 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		N = nextInt();
	
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		for(int i = 0; i < N; i++){
			int d = nextInt();
	
			if(map.containsKey(d)){
				int cnt = map.get(d);
				map.put(d, cnt+1);
			}
			else{
				map.put(d, 1);
			}
		}
	

		int ans = 0;
		while(!map.isEmpty()){
		
			int d = map.lastKey();
			int div = d/2;
			if(map.lowerKey(div+1) == null){
				break;
			}
			int low = map.lowerKey(div+1);
			remove(map, d);
			remove(map, low);
			
			ans++;
		}
		for(Integer key : map.keySet()){
			ans += map.get(key);
		}
		
		out.println(ans);
		
	}
	
	void remove(TreeMap<Integer, Integer> map, Integer d){
		int cnt = map.get(d);
		if(cnt == 1){
			map.remove(d);
		}
		else{
			map.put(d, cnt-1);
		}
	}

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }
    
    private String nextS() throws IOException{
		String s = br.readLine();
		return s;
	}

	private int nextInt() throws IOException{
		String s = br.readLine();
		return parseInt(s);
	}
	
	private long nextLong() throws IOException{
		String s = br.readLine();
		return parseLong(s);
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
		WAC219 t = new WAC219();
		t.solve();
		bw.close();
	}

}
