import static java.lang.Math.*;

public class GaussJourdanAri {
	final double EPS = 1E-8;
	
	void swap(double[][] m, int a, int b){
		if(a == b) return;
		for(int i = 0; i < m[0].length; i++){
			double tmp = m[a][i];
			m[a][i] = m[b][i];
			m[b][i] = tmp;
		}
	}
	
	void swapC(double[][] m, int a, int b){
		if(a == b) return;
		for(int i = 0; i < m.length; i++){
			double tmp = m[i][a];
			m[i][a] = m[i][b];
			m[i][b] = tmp;
		}
	}
	
	// Aは正方行列
	double[] gauss_jordan(double[][] A, double[] b){
		int n = A.length;
		double[][] B = new double[n][n+1];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				B[i][j] = A[i][j];
			}
			B[i][n] = b[i];
		}
		
		for(int i = 0; i < n; i++){
			int pivot = i;
			for(int j = i; j < n; j++){
				if(abs(B[j][i]) > abs(B[pivot][i])) pivot = j;
			}
			swap(B, i, pivot);
			
			
			if(abs(B[i][i]) < EPS) continue;
			
			// 注目している変数の係数を１にする
			for(int j = i + 1; j <= n; j++) B[i][j] /= B[i][i];
			B[i][i] = 1;
			
			for(int j = 0; j < n; j++){
				if(i != j){
					// j番目の式からi番目の変数を除去
					for(int k = i+1; k <= n; k++) B[j][k] -= B[j][i] * B[i][k];
					B[j][i] = 0;
				}
			}
		}
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j <= n; j++){
				System.out.print(B[i][j] + " ");
			}
			System.out.println();
		}
		
		double[] ans = new double[n];
		for(int i = 0; i < n; i++){
			ans[i] = B[i][n];
		}
		
		
		return ans;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GaussJourdanAri g = new GaussJourdanAri();
		double[][] A  = {{1, -2, 3}, {4, -5, 6}, {7, -8, 10}};
		double[] b = {6, 12, 21};
		double[] a = g.gauss_jordan(A, b);
		
	}

}
