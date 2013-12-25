package done.d221;

import java.io.*;
import java.util.*;
import java.math.*;

import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.lang.Long.*;




public class C221 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	
	public void solve() throws Exception{

		String s = nextS();
		StringBuilder sb = new StringBuilder();
		StringBuilder s0 = new StringBuilder();

		boolean[] f = new boolean[4];
		char[] cs = s.toCharArray();
		for(int i = 0; i < s.length(); i++){
			int a = cs[i] - '0';
			if(a == 1 && !f[0]){
				f[0] = true;
			}
			else if(a == 6 && !f[1]){
				f[1] = true;
			}
			else if(a == 8 && !f[2]){
				f[2] = true;
			}
			else if(a == 9 && !f[3]){
				f[3] = true;
			}
			else if(a == 0){
				s0.append("0");
			}
			else{
				sb.append(s.charAt(i));
			}
		}

		if(sb.length() == 0){
			String ans = "1869" + s0.toString();
			out.print(ans);
			return;
		}
		int ama = 0;
		String ta = sb.toString() + s0.toString();
		for(int i = 0; i < ta.length(); i++){
			int u = ta.charAt(i) - '0';
			ama = (ama * 10 + u)%7;
		}
		ama = ama * 10000 % 7;
	
		int[] ord = {1, 6, 8, 9};
		Permutation p = new Permutation(4);
		Iterator<int[]> ite = p.iterator();

		while(ite.hasNext()){
			int[] d = ite.next();
			int u = 0;
			int m = 1;
			for(int i = 0; i < d.length; i++){
				u += ord[d[i]] * m;
				m*= 10;
			}
			int ama2 = (ama + u)%7;
			if(ama2 == 0){
				bw.write(ta + u + "\n");
				return;
			}
			
		}
		out.println("0");
		
	}
	
	static boolean nextPermutation(int[] src) {
		int i;
		for(i = src.length - 2;i >= 0 && src[i] > src[i + 1];i--)
			;
		if(i == -1)
			return false;
		int j;
		for(j = i + 1;j < src.length && src[i] < src[j];j++)
			;
		int d = src[i];
		src[i] = src[j - 1];
		src[j - 1] = d;
		for(int p = i + 1, q = src.length - 1;p < q;p++, q--){
			d = src[p];
			src[p] = src[q];
			src[q] = d;
		}
		return true;
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
	
	private long[] nextLongs() throws IOException{
		String s = br.readLine();
		String[] sp = s.split(" ");
		long[] r = new long[sp.length];
		for(int i = 0;i < sp.length; i++){
			r[i] = parseLong(sp[i]);
		}
		return r;
	}
	
	void pl(String s){
		try{
			bw.write(s);
		}
		catch(Exception ex){
		}
	}
	
	void pln(String s){
		try{
			bw.write(s);
			bw.write(System.lineSeparator());
		}
		catch(Exception ex){
		}
	}
	void pln(){
		try{
			bw.write(System.lineSeparator());
		}
		catch(Exception ex){
		}
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
		C221 t = new C221();
		t.solve();
		bw.close();
	}




		public class Permutation implements Iterable<int[]> {
			private final int size;


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
	


}