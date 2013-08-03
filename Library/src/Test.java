
import java.util.*;






public class Test {

	public static void main(String[] a){
		Test t = new Test();
		t.run();
	}
	
	void run(){
		
		Mat m = new Mat(1, 5);
		m.d[0][0] = 4;
		for(int i = 1; i <= 4; i++){
			m.d[0][i] = 3;
		}
		
		m = mul(m, m);
	
		//m = pow(m, 7);
		for(int j = 0; j < m.d.length; j++){
			for(int i = 0; i < m.d[0].length; i++){
				System.out.print(m.d[j][i] + " ");
			}
			System.out.println();
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
					C.d[i][j] = C.d[i][j] + A.d[i][k] * B.d[k][j]; // MOD�̏ꍇ�A���̒l��MOD����
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
	
	public void test(){
		Mat A = new Mat(2, 2);
		A.d[0][0] = 1;
		A.d[0][1] = 1;
		A.d[1][0] = 1;
		A.d[1][1] = 0;
		A = pow(A, 10);
		System.out.println(A.d[1][0]);
	}
}
