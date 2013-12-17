
import java.io.*;
import java.util.StringTokenizer;

class GaussJordan2 {
	double[] gaussjordan(double[][] a) {
		int N = a.length;
		for (int k = 0; k < N; k++) {
			for (int j = k + 1; j <= N; j++) {
				// 枢軸要素で割る
				a[k][j] = a[k][j] / a[k][k];
			}
			for (int i = 0; i < N; i++) {
				if (i != k) {// 枢軸以外
					for (int j = k + 1; j <= N; j++) {
						a[i][j] -= a[i][k] * a[k][j];
					}
				}
			}
		}
		double[] ans = new double[a.length];
		for(int i = 0; i < a.length; i++){
			ans[i] = a[i][a[i].length-1];
		}
		return ans;
	}

	
	

	public static void main(String[] args) throws IOException {
		GaussJordan2 gj = new GaussJordan2();
		double a[][] = { { 1, 1, 2, 3 }, { 1, 1, 1, 1 }, {2, 2, 2, 5 } };
		double[] ans = gj.gaussjordan(a);

		for (int i = 0; i < ans.length; i++) {
			
				System.out.print(ans[i] + " " );
			
		
		}
		System.out.println();
	}
}