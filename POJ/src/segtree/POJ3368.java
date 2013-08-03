package segtree;


import java.util.*;
import java.io.*;
import static java.lang.Math.*;

// TLE
//各個数を求めて、個数のsegment木を作る。
//値からindexをもとめ、間で最大の個数を求める。
// 左端、右端はbinary searchで協会を求めて個数を出す。
// これでTLE 5 log N
public class POJ3368 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	List<int[]> list = null;
	int[] d = null;
	public void solve() throws Exception{
		list = new ArrayList<int[]>();
		d = new int[N];
		int n = INF;
		int c = 0;
		for(int i = 0; i < N; i++){
			d[i] = sc.nextInt();
			if(d[i] != n){
				if(n != INF){
					int[] dt = new int[2];
					dt[0] = n;
					dt[1] = c;
					list.add(dt);
				}
				n = d[i];
				c = 1;
			}
			else{
				c++;
			}
		}
		{
			int[] dt = new int[2];
			dt[0] = n;
			dt[1] = c;
			list.add(dt);
		}
		SegmentTree sg = new SegmentTree(list.size());
		for(int i = 0; i < list.size(); i++){
			sg.update(i, list.get(i)[1]);
		}
		int ans = 0;
		for(int i = 0; i < M; i++){
			int st = sc.nextInt() -1;
			int ed = sc.nextInt()-1;
			if(d[st] == d[ed]){
				ans = ed - st + 1;
				out.println(ans);
			}
			else{
				int sti = getidx(d[st])+1;
				int edi = getidx(d[ed])-1;
				if(sti <= edi){
					ans = sg.query(sti, edi);
				}
				int upb = upperb(d[st]);
				int lwb = lowerb(d[ed]);
				ans = max(ans, upb - st + 1);
				ans = max(ans, ed - lwb + 1);
				out.println(ans);
			}
		}
	}
	
	int upperb(int n){
		int min = 0;
		int max = d.length-1;
		if(d[max] == n){
			return max;
		}
		while(min + 1 < max){
			int mid = (min + max) / 2;
			if(d[mid] > n){
				max = mid;
			}
			else{
				min = mid;
			}
		}
		return min;
	}
	int lowerb(int n){
		int min = 0;
		int max = d.length-1;
		if(d[min] == n) return min;
		while(min + 1 < max){
			int mid = (min + max) / 2;
			if(d[mid] < n){
				min = mid;
			}
			else{
				max = mid;
			}
		}
		return max;
	}

	int getidx(int n){
		int min = 0;
		int max = list.size()-1;
		if(list.get(min)[0] == n){
			return min;
		}
		if(list.get(max)[0] == n){
			return max;
		}
		while(true){
			int mid = (min + max) / 2;
			if(list.get(mid)[0] == n){
				return mid;
			}
			else if(list.get(mid)[0] < n){
				min = mid;
			}
			else{
				max = mid;
			}
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
		POJ3368 p = new POJ3368();
		while(true){
			
			try{
				N = sc.nextInt();
				M = sc.nextInt();
				if(N == 0 && M == 0){
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
			for (int i = 0; i < 2 * n; i++) dat[i] = Integer.MIN_VALUE;
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
			if(r < a || b < l) return Integer.MIN_VALUE;
			
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
