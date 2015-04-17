package gcj20141B;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;

public class Csmall {
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintWriter out = null;
	static PrintStream sysout = System.out;
	static Random rnd = new Random();
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	long b = 1;
	int N = 0;
	int M = 0;
	
	String[] zs = null;
	List<List<Integer>> ls = null;
	String ans = null;
	public void solve() throws Exception{
		String s = br.readLine();
		
		String[] sp = s.split(" ");
		N = Integer.parseInt(sp[0]);
		int M = Integer.parseInt(sp[1]);
		zs = new String[N];
		for(int i = 0; i < N; i++){
			zs[i] = br.readLine();
		}
		ls = new ArrayList<List<Integer>>();
		for(int i = 0; i < N; i++){
			ls.add(new ArrayList<Integer>());
		}
		for(int i = 0; i < M; i++){
			s = br.readLine();
			sp = s.split(" ");
			int f = Integer.parseInt(sp[0]) - 1;
			int t = Integer.parseInt(sp[1]) - 1;
			ls.get(f).add(t);
			ls.get(t).add(f);
		}
		ans = "";
		for(int i = 0; i < N; i++){
			ans += "99999";
		}
		Permutation p = new Permutation(N);
		Iterator<int[]> ite = p.iterator();
		while(ite.hasNext()){
			int[] d = ite.next();
			List<Integer> vis = new ArrayList<Integer>();
			
			if(check(0, vis, d)){
				StringBuilder sb = new StringBuilder();
				for(int i = 0; i < d.length; i++){
					sb.append(zs[d[i]]);
				}
				String ck = sb.toString();
				if(ans.compareTo(ck) > 0){
					ans = ck;
				}
			}
		}
		println(ans);
	}
	
	boolean check(int idx, List<Integer> vis, int[] d){
		if(idx == d.length -1){
			return true;
		}
		int f = d[idx];
		vis.add(f);
		while(vis.size() > 0){
			Integer NI = vis.get(vis.size()-1);
			f = vis.get(vis.size()-1);
			List<Integer> l = ls.get(f);
			
			if(l.contains(d[idx+1]) && check(idx+1, vis, d)){
				return true;
			}
			if(vis.contains(NI)) vis.remove(NI);
	
		}
		
		
		return false;
	}
	
	public class Permutation implements Iterable<int[]> {
		private final int size;

		/**
		 * <p>順列を表すクラスのコンストラクタ。反復子が返す配列の要素数を指定する。
		 * @param size 順列の要素数（10程度が妥当な最大値）
		 * @throws IllegalArgumentException 引数（順列の要素数）が0以下の場合
		 */
		public Permutation(int size) {
			if (size <= 0) throw new IllegalArgumentException();
			this.size = size;
		}

		public Iterator<int[]> iterator() {
			return new Iter(size);
		}

		private class Iter implements Iterator<int[]> {
			private final int[] source;
			private boolean isFirst = true;

			private Iter(int size) {
				source = new int[size];
				for(int i = 0; i < size; ++i) {
					source[i] = i;
				}
			}

			/**
			 * <p>反復子がまだ順列を返しうるか調べる。
			 * 本メソッドは{@link Iter#next()}呼び出し前に必ず1回ずつ実行する必要がある。
			 * @return 順列が存在する場合はtrue
			 */
			public boolean hasNext() {
				if (isFirst) {
					isFirst = false;
					return true;
				}

				int n = source.length;
				for (int i = n - 1; i > 0; i--) {
					if (source[i - 1] < source[i]) {
						int j = n;
						while (source[i - 1] >= source[--j]);
						swap(source, i - 1, j);
						reverse(source, i, n);
						return true;
					}
				}
				reverse(source, 0, n);
				return false;
			}

			/**
			 * <p>次の順列を表すint型配列を返す。
			 * <p>このメソッドが返す参照は常に同じ配列に対する参照である。
			 * このため配列の要素を変更したり次回の{@link Iter#next()}呼び出し後も参照する場合は、
			 * クラスの呼び出し側が配列のクローンを生成して利用する必要がある。
			 * @return 順列を表すint型配列
			 */
			public int[] next() {
				return source;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}

			private void swap(int[] is, int i, int j) {
				int t = is[i];
				is[i] = is[j];
				is[j] = t;
			}

			private void reverse(int[] is, int s, int t) {
				while (s < --t) swap(is, s++, t);
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("C-small-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		else{
			throw new Exception("can't find a input file : " + file.getAbsolutePath());
		}
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		Csmall b = new Csmall();
		int T = 0;
		if(sc != null){
			T = sc.nextInt();
		}
		else{
			T = parseInt(br.readLine());
		}
		int t = 1;
		while(t <= T){
			out.print("Case #" + t + ": ");
			System.out.print("Case #" + t + ": ");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
	
	void print(int i){
		out.print(i + "");
		System.out.print(i);
	}
	void println(int i){
		out.println(i + "");
		System.out.println(i);
	}
	void print(String s){
		out.print(s);
		System.out.print(s);
	}
	void println(String s){
		out.println(s);
		System.out.println(s);
	}
	void print(long i){
		out.print(i + "");
		System.out.print(i);
	}
	void println(long i){
		out.println(i + "");
		System.out.println(i);
	}
}
