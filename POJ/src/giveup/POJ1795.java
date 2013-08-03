package giveup;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// MLE
// c++のソースと比べても、変数の作り方の違うところはないからとりあえずgive up

// 完全に包含されるのは消す。
// すると、訪れたところ＋最後に訪れたところをとっとけば、巡回セールスマン的にいける。
// (他のを完全に包含することはないので、最後のをとっとけばそこのどこからかからしか重ならない）
public class POJ1795 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	int[][] dp = null;
	short[][] del = null;
	String[] tmp = null;
	public void solve() throws Exception{
		tmp = new String[N];
		boolean[] f = new boolean[N];
		for(int i = 0;i < N; i++){
			tmp[i] = sc.next();
		}
		for(int i = 0; i < N; i++){
			if(f[i]) continue;
			for(int j = 0; j < N; j++){
				if(i == j) continue;
				if(f[j]) continue;
				if(tmp[j].contains(tmp[i])){
					f[i] = true;
				}
			}
		}
		List<String> ts = new ArrayList<String>();
		ts.add("");
		for(int i = 0; i < N; i++){
			if(f[i]) continue;
			ts.add(tmp[i]);
		}
		M = ts.size();
		tmp = new String[ts.size()];
		for(int i = 0; i < ts.size(); i++){
			tmp[i] = ts.get(i);
		}
		ts = null;
		f = null;
		
		dp = new int[1<<M][M];
		del = new short[M][M];
		for(int i = 0; i < M; i++){
			for(int j = 0; j < M; j++){
				if(i == j) continue;
				del[i][j] = (short)getdel(tmp[i], tmp[j]);
			}
		}
		check(1, 0);
		String ret = create(1, 0);
		out.println(ret);
	}
	
	String create(int p, int pr){
		String ret = "";
		if(p == (1 << M) -1 ) return "";
		int min = INF;
		int midx = -1;
		for(int i = 1; i < M; i++){
			if( (p & 1 << i) == 0){
				int np = p | 1 << i;
				if(dp[np][i] <= min){
					
					if(dp[np][i] == min){
						if(tmp[i].compareTo(tmp[midx]) < 0){
							midx = i;
						}
					}
					else{
						midx = i;
						min = dp[np][i];
					}
					
				}
			}
		}
		int np = p | 1 << midx;
		ret = concat(tmp[midx], create(np, midx));
		return ret;
	}
	int getdel(String a, String b){
		int ed = 0;
		while(true){
			String ct = a.substring(ed);
			if(b.startsWith(ct)){
				break;
			}
			ed++;
		}
		return a.length() - ed;
	} 
	
	String concat(String a, String b){
		int ed = 1;
		
		while(true){
			String ct = a.substring(ed);
			if(b.startsWith(ct)){
				break;
			}
			ed++;
		}
		return a.substring(0, ed) + b;
	}

	int check(int p, int pr){
		if(p == (1 << M) -1) return 0;
		if(dp[p][pr] != 0){
			return dp[p][pr];
		}
		
		int ret = Integer.MAX_VALUE;
		for(int i = 1; i < M; i++){
			if( ((1 << i) & p) == 0){
				String t = tmp[i];
				int np = p | (1 << i);
				int ns = -del[pr][i] + t.length() + check(np, i);
				ret = min(ret, ns);
			}
		}
		
		dp[p][pr] = ret;
		return ret;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		out = System.out;
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		sc =  new Scanner(System.in);
		POJ1795 p = new POJ1795();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			try{
				N = sc.nextInt();
				if(N == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
			out.println("Scenario #" + t + ":");
			p.solve();
			t++;
		}
	}

}
