


import java.util.*;
import java.io.*;

import static java.lang.Math.*;

// 行列の繰り返し2乗
// 行列の作り方が悪い
public class POJ3735 {

	static PrintStream out = null;
	int INF = Integer.MAX_VALUE / 10;
	static Scanner sc = null;
	double DF = 0.000000000001;

	static int N = 0;
	static int M = 0;
	static int K = 0;
	public void solve() throws Exception{
		Data[] ds = new Data[N];
		for(int i =0; i < N; i++){
			ds[i] = new Data(0, i);
		}
		for(int i = 0; i < K; i++){
			String s = sc.next();
			if(s.equals("g")){
				int idx = sc.nextInt() -1;
				ds[idx].c++;
			}
			else if(s.equals("e")){
				int idx = sc.nextInt() -1;
				ds[idx].c = 0;
				ds[idx].idx = -1;
			}
			else{
				int f = sc.nextInt() -1;
				int t = sc.nextInt() -1;
				Data d = ds[f];
				ds[f] = ds[t];
				ds[t] = d;
			}
		}
		
		Mat mat = new Mat(2*N, 2*N);
		Mat mat2 = new Mat(2*N, 1);
		for(int i = 0; i < N; i++){
			Data d = ds[i];
			if(d.idx >= 0){
				mat.d[i][d.idx] = 1;	
			}
			mat.d[i][N+i] = 1;
			mat.d[N+i][i] = 1;
			mat2.d[N+i][0] = d.c;
		}
		
		
		
		mat = pow(mat, M);
		mat = mul(mat, mat2);
		for(int i = 0; i < N; i++){
			if(i != 0){
				out.print(" ");
			}
			out.print(mat.d[i][0]);
		}
		
	}

	class Data {
		public Data(int add, int idx){
			this.c = add;
			this.idx = idx;
		}
		int c = 0;
		int idx = 0;
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
		POJ3735 p = new POJ3735();
		while(true){
			
			try{
				N = sc.nextInt();
				M = sc.nextInt();
				K = sc.nextInt();
				if(N == 0 && M == 0 && K == 0){
					break;
				}
			}
			catch(Exception ex){
				break;
			}
		
			p.solve();
		}
	}

	class Mat{
		public int[][] d = null;
		public Mat(int n, int m){
			d = new int[n][m];
		}
		public int size(){
			return d.length;
		}
		public void set(int y, int x, int n){
			d[y][x] = n;
		}
	}
	
	public Mat mul(Mat A, Mat B){
		Mat C = new Mat(A.d.length, B.d[0].length);
		for(int i = 0; i < A.size(); i++){
			for(int k = 0; k < B.d.length; k++){
				for(int j = 0; j < B.d[0].length; j++){
					C.d[i][j] = C.d[i][j] + A.d[i][k] * B.d[k][j]; // MOD
				}
			}
		}
		return C;
	}
	
	public Mat pow(Mat A, long n){
		Mat B = new Mat(A.d.length, A.d.length);
		for(int i = 0; i < A.d.length; i++){
			B.d[i][i] = 1;
		}
		while(n > 0){
			long tmp = n & 1;
			if(tmp > 0) B = mul(B, A);
			A = mul(A, A);
			n >>= 1;
		}
		return B;
	}

	
}
