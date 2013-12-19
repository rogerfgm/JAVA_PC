import static java.lang.Math.*;

public class GaussJourdanAri {
	final double EPS = 1E-8;

	void swap(double[][] m, int a, int b) {
		if (a == b)
			return;
		for (int i = 0; i < m[0].length; i++) {
			double tmp = m[a][i];
			m[a][i] = m[b][i];
			m[b][i] = tmp;
		}
	}

	void swapC(double[][] m, int a, int b) {
		if (a == b)
			return;
		for (int i = 0; i < m.length; i++) {
			double tmp = m[i][a];
			m[i][a] = m[i][b];
			m[i][b] = tmp;
		}
	}

	// Aは正方行列
	double[] gauss_jordan(double[][] A, double[] b) {
		int n = A.length;
		double[][] B = new double[n][n + 1];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				B[i][j] = A[i][j];
			}
			B[i][n] = b[i];
		}

		for (int i = 0; i < n; i++) {
			int pivot = i;
			for (int j = i; j < n; j++) {
				if (abs(B[j][i]) > abs(B[pivot][i]))
					pivot = j;
			}
			swap(B, i, pivot);

			if (abs(B[i][i]) < EPS)
				continue;

			// 注目している変数の係数を１にする
			for (int j = i + 1; j <= n; j++)
				B[i][j] /= B[i][i];
			B[i][i] = 1;

			for (int j = 0; j < n; j++) {
				if (i != j) {
					// j番目の式からi番目の変数を除去
					for (int k = i + 1; k <= n; k++)
						B[j][k] -= B[j][i] * B[i][k];
					B[j][i] = 0;
				}
			}
		}

		double[] ans = new double[n];
		for (int i = 0; i < n; i++) {
			ans[i] = B[i][n];
		}

		return ans;
	}

	public int rank(int n, int m, double[][] a, int[] b) {

		int ans = 0;

		boolean used[] = new boolean[n];

		// Using Gauss-Jordan in [ A : b ] to make it in row-echellon form.
		// So that we can find the rank. We also need to make sure that the
		// rank of [ A ] is equal to the rank of [A : b], else there are
		// no solutions
		for (int j = 0; j < m; j++) {
			int i = 0;
			while ((i < n) && (used[i] || (abs(a[i][j]) < EPS))) {
				i++;
			}
			if (i == n)
				continue;
			ans++;
			used[i] = true;
			double div = a[i][j];
			for (int k = j; k < m; k++) {
				a[i][k] /= div;
			}

			for (int k = 0; k < n; k++){
				if (abs(a[k][j]) >= EPS) {
					double mul = a[k][j];
					for(int l = k; l < m; l++){
						a[k][l] -= a[i][j] * mul;
					}
					b[k] -= -b[i] * mul;
				}
			}
		}

		// If rank of [A] is not equal to the rank of [A : b]
		for (int i = 0; i < n; i++) {
			if (!used[i] && abs(b[i]) >= EPS) {
				return -1;
			}
		}

		return ans;
	}

	// mod pの等式
	public int rank(int n, int m, int[][] a, int[] b, int p) {

		int ans = 0;

		boolean used[] = new boolean[n];

		// Using Gauss-Jordan in [ A : b ] to make it in row-echellon form.
		// So that we can find the rank. We also need to make sure that the
		// rank of [ A ] is equal to the rank of [A : b], else there are
		// no solutions
		for (int j = 0; j < m; j++) {
			int i = 0;
			while ((i < n) && (used[i] || (a[i][j] == 0))) {
				i++;
			}
			if (i == n)
				continue;
			ans++;
			used[i] = true;
			for (int k = 0; k < n; k++)
				if (!used[k]) {
					// Find a value of coef equal to (-a[k][j] / a[i][j]);
					// we can just try 0,1,..,p-1 until we find one.
					int coef = 0;
					while ((a[i][j] * coef + a[k][j]) % p != 0) {
						coef++;
					}
					for (int l = 0; l < m; l++) {
						a[k][l] = (a[k][l] + a[i][l] * coef) % p;
					}
					b[k] = (b[k] + b[i] * coef) % p;
				}
		}

		// If rank of [A] is not equal to the rank of [A : b]
		for (int i = 0; i < n; i++) {
			if (!used[i] && b[i] != 0) {
				return -1;
			}
		}

		return ans;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GaussJourdanAri g = new GaussJourdanAri();
		double[][] A = { { 1, -2, 3 }, { 4, -5, 6 }, { 7, -8, 10 } };
		double[] b = { 6, 12, 21 };
		double[] a = g.gauss_jordan(A, b);

	}

}
