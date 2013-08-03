package accept;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;


public class POJ2019 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int B = 0;
	static int K = 0;
	public void solve() throws Exception{
		
		SegmentTree[] mnsgs = new SegmentTree[N];
		SegmentTreeMax[] mxsgs = new SegmentTreeMax[N];
		int[][] dp = new int[N][N];
		int[][][] memo = new int[N][N][2];
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				dp[i][j] = -1;
				memo[i][j][0] = memo[i][j][1] = -1;
			}
			
		}
		
		
		for(int i = 0; i < N; i++){
			mnsgs[i] = new SegmentTree(N);
			mxsgs[i] = new SegmentTreeMax(N);
			
			for(int j = 0; j < N; j++){
				int a = sc.nextInt();
				mnsgs[i].update(j, a);
				mxsgs[i].update(j, a);
			}
		}
	
		for(int i = 0; i < K; i++){
			int y = sc.nextInt() - 1;
			int x = sc.nextInt() - 1;
			if(dp[y][x] >= 0){
				out.println(dp[y][x]);
				continue;
			}
			int min = INF;
			int max = 0;
			for(int j = 0; j < B; j++){
				int r = y + j;
				if(memo[r][x][0] >= 0){
					min = min(min, memo[r][x][0]);
					max = max(max, memo[r][x][1]);
				}
				else{
					int rm = mnsgs[r].query(x, x+B-1);
					int rx = mxsgs[r].query(x, x+B-1);
					min = min(min, rm);
					max = max(max, rx);
					memo[r][x][0] = rm;
					memo[r][x][1] = rx;
				}
			}
			int ans = max - min;
			out.println(ans);
			dp[y][x] = ans;
		}
		
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
		POJ2019 p = new POJ2019();
		while(true){
			
			try{
				N = sc.nextInt();
				B = sc.nextInt();
				K = sc.nextInt();
				if(N == 0 && B == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
		
			p.solve();
		}
	}
	


public class SegmentTree {
	int n;
	int dat[] = null;
	
	public SegmentTree(int N){
		init(N);
	}
	
	// 初期化
	void init(int n_){
		
		// 簡単のために、要素数を2のべき乗にする
		n = 1;
		while(n < n_) n *= 2;
		dat = new int[2*n];
		// すべての値をINT_MAXに
		for (int i = 0; i < 2 * n; i++) dat[i] = Integer.MAX_VALUE;
	}
	
	// k番目の値 (0-indexed)をaに変更
	void  update(int k, int a){
		
		k += n-1;
		dat[k] = a;
		while(k > 0){
			k = (k-1) / 2;
			dat[k] = Math.min(dat[k*2+1], dat[k*2+2]);
		}
	}
	
	// 下のqueryを呼ぶと間違えそうだから。
	//a, b間（どっちも含む）のquery
	int query(int a, int b){
		return query(a, b, 0, 0, n-1);
	}
	
	// (a, b)の最小値を求める。bも含める
	// 後ろの方の引数は計算のための引数
    // kは接点の番号, l, rはその接点が[l, r)に対応づいていることを表す。
	// したがって、外からは query(a, b, 0, 0, n-1 (nこで0indexならn-1になる）)として呼ぶ
	int query(int a, int b, int k, int l, int r){
		// [a, b)と[l, r)が交差しなければ、INT_MAX
		if(r < a || b < l) return Integer.MAX_VALUE;
		
		// [a, b)が [l, r)を完全に含んでいれば、この接点の値
		if( a <= l && r <= b) return dat[k];
		else{
			// そうでなければ、２つの子の最小値
			int vl = query(a, b, k*2+1, l, (l+r) / 2);
			int vr = query(a, b, k*2+2, (l+r)/2+1, r);
			return Math.min(vl, vr);
			
		}
	}
		
}

public class SegmentTreeMax {
	int n;
	int dat[] = null;
	
	public SegmentTreeMax(int N){
		init(N);
	}
	
	// 初期化
	void init(int n_){
		
		// 簡単のために、要素数を2のべき乗にする
		n = 1;
		while(n < n_) n *= 2;
		dat = new int[2*n];

	}
	
	// k番目の値 (0-indexed)をaに変更
	void  update(int k, int a){
		
		k += n-1;
		dat[k] = a;
		while(k > 0){
			k = (k-1) / 2;
			dat[k] = max(dat[k*2+1], dat[k*2+2]);
		}
	}
	
	// 下のqueryを呼ぶと間違えそうだから。
	//a, b間（どっちも含む）のquery
	int query(int a, int b){
		return query(a, b, 0, 0, n-1);
	}
	
	// (a, b)の最小値を求める。bも含める
	// 後ろの方の引数は計算のための引数
    // kは接点の番号, l, rはその接点が[l, r)に対応づいていることを表す。
	// したがって、外からは query(a, b, 0, 0, n-1 (nこで0indexならn-1になる）)として呼ぶ
	int query(int a, int b, int k, int l, int r){
		// [a, b)と[l, r)が交差しなければ、INT_MAX
		if(r < a || b < l) return 0;
		
		// [a, b)が [l, r)を完全に含んでいれば、この接点の値
		if( a <= l && r <= b) return dat[k];
		else{
			// そうでなければ、２つの子の最小値
			int vl = query(a, b, k*2+1, l, (l+r) / 2);
			int vr = query(a, b, k*2+2, (l+r)/2+1, r);
			return max(vl, vr);
			
		}
	}
		
}


}
