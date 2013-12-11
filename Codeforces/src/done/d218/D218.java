package done.d218;



import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;




public class D218 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{

		int n = nextInt();
		int[] cap = nextInts();
		int[] d = new int[n];
		int m = nextInt();
		
		TreeSet<Integer> rems = new TreeSet<Integer>();
		for(int i = 0; i < n; i++){
			rems.add(i);
		}
		
		for(int t = 0; t < m; t++){
			int[] in = nextInts();
			if(in.length == 3){
				int num = in[2];
				int idx = in[1] - 1;
				while(num > 0 && idx >= 0){
					if(rems.contains(idx)){
						if(num >= cap[idx] - d[idx]){
							num -= cap[idx] - d[idx];
							d[idx] = cap[idx];
							rems.remove(idx);
							Integer high = rems.higher(idx);
							if(high == null){
								break;
							}
							idx = high;
						}
						else{
							d[idx] += num;
							num = 0;
						}
					}
					else{
						Integer high = rems.higher(idx);
						if(high == null){
							break;
						}
						idx = high;
					}
				}
				
				
			}
			else{
				out.println(d[in[1]-1]);
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
		D218 t = new D218();
		t.solve();
		bw.close();
	}

}
