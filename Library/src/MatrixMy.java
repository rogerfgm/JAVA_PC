
public class MatrixMy {

	
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
	
	public void test(){
		Mat A = new Mat(2, 2);
		A.d[0][0] = 1;
		A.d[0][1] = 1;
		A.d[1][0] = 1;
		A.d[1][1] = 0;
		A = pow(A, 10);
		System.out.println(A.d[1][0]);
	}
	
	public static void main(String[] args){
		MatrixMy m = new MatrixMy();
		m.test();
	}
}
