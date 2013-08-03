package giveup;


import java.util.*;
import java.io.*;

import static java.lang.Math.*;

// WAになるも、何が間違っているのかわからないのでgiveup
public class POJ3264 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	public void solve() throws Exception{
		SegmentTree2 sg1 = new SegmentTree2(N);
		SegmentTree2 sg2 = new SegmentTree2(N);
		for(int i = 0; i < N; i++){
			int a = sc.nextInt();
			sg1.update(i, a);
			sg2.update(i, -a);
		}
		
		for(int i = 0; i < M; i++){
			int a = sc.nextInt()-1;
			int b = sc.nextInt();
			int min = sg1.query(a, b, 0, 0, N);
			int max = -sg2.query(a, b, 0, 0, N);
			int ans = max - min;
			out.println(ans + "");
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
		POJ3264 p = new POJ3264();
		while(true){
			try{
				N = sc.nextInt();
				M = sc.nextInt();
				if(N == 0 && M == 0) break;
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
	
		void init(int n_){
			
	
			n = 1;
			while(n < n_) n *= 2;
			dat = new int[2*n];
	
			for (int i = 0; i < 2 * n; i++) dat[i] = Integer.MAX_VALUE;
		}
		
		void  update(int k, int a){
			
			k += n-1;
			dat[k] = a;
			while(k > 0){
				k = (k-1) / 2;
				dat[k] = Math.min(dat[k*2+1], dat[k*2+2]);
			}
		}
		
		int query(int a, int b, int k, int l, int r){

			if(r < a || b < l) return Integer.MAX_VALUE;
			
			if( a <= l && r <= b) return dat[k];
			else{
				int vl = query(a, b, k*2+1, l, (l+r) / 2);
				int vr = query(a, b, k*2+2, (l+r)/2+1, r);
				return Math.min(vl, vr);
				
			}
		}
	}

	public class SegmentTree2 {
		int n;
		int dat[] = null;
		
		public SegmentTree2(int N){
			init(N);
		}
	
		void init(int n_){
			
	
			n = 1;
			while(n < n_) n *= 2;
			dat = new int[2*n];
	
			for (int i = 0; i < 2 * n; i++) dat[i] = Integer.MAX_VALUE;
		}
		
		void  update(int k, int a){
			
			k += n-1;
			dat[k] = a;
			while(k > 0){
				k = (k-1) / 2;
				dat[k] = Math.min(dat[k*2+1], dat[k*2+2]);
			}
		}
		
		int query(int a, int b, int k, int l, int r){

			if(r <= a || b <= l) return Integer.MAX_VALUE;
			
			if( a <= l && r <= b) return dat[k];
			else{
				int vl = query(a, b, k*2+1, l, (l+r) / 2);
				int vr = query(a, b, k*2+2, (l+r)/2, r);
				return Math.min(vl, vr);
				
			}
		}
	}
}
