package done.d216;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class C216 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int n = 0;
	Map<Integer, List<Integer>> nmap = null;
	Map<Integer, List<Integer>> rmap = null;
	List<Integer> ans = null;
	boolean[] used = null;
	public void solve() throws Exception{
		n = nextInt();
		nmap = new HashMap<Integer, List<Integer>>();
		rmap = new HashMap<Integer, List<Integer>>();
		ans = new ArrayList<Integer>();
		for(int i = 0; i < n-1; i++){
			int[] t = nextInts();
			int f = t[0];
			int to = t[1];
			int c = t[2];
			if(c == 1){
				if(!nmap.containsKey(f)){
					nmap.put(f, new ArrayList<Integer>());
				}
				if(!nmap.containsKey(to)){
					nmap.put(to, new ArrayList<Integer>());
				}
				nmap.get(f).add(to);
				nmap.get(to).add(f);
			}
			else{
				if(!rmap.containsKey(f)){
					rmap.put(f, new ArrayList<Integer>());
				}
				if(!rmap.containsKey(to)){
					rmap.put(to, new ArrayList<Integer>());
				}
				rmap.get(f).add(to);
				rmap.get(to).add(f);
			}
		}
		used = new boolean[n+1];
		check(1);
		out.println(ans.size());;
		for(int i = 0; i < ans.size(); i++){
			if(i != 0){
				out.print(" ");;
			}
			out.print(ans.get(i));
		}
	}
	
	boolean check(int f){
		boolean flag = false;
		used[f] = true;
		if(nmap.containsKey(f)){
			for(int to : nmap.get(f)){
				if(used[to]){
					continue;
				}
				flag |= check(to);
			}
		}
		if(rmap.containsKey(f)){
			for(int to : rmap.get(f)){
				if(used[to]){
					continue;
				}
				boolean ck = check(to);
				if(!ck){
					ans.add(to);
					ck = true;
				}
				flag |= ck;
			}
		}

		
		
		return flag;
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
		C216 t = new C216();
		t.solve();
		bw.close();
	}

}
