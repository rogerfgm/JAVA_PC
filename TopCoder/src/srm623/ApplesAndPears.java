package srm623;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class ApplesAndPears {

	final int INF = Integer.MAX_VALUE / 10;
	final double EPS = 1E-8;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	long b = 1;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	public int getArea(String[] B, int K){
		N = B.length;
		int[][] b = new int[N][N];
		int A = 0;
		int P = 0;
		int E = 0;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				char c = B[i].charAt(j);
				if(c == 'A'){
					b[i][j] = 1;
					A++;
				}
				else if(c == 'P'){
					b[i][j] = 2;
					P++;
				}
				else{
					E++;
				}
			}
		}
		
		int[][][][] a = new int[N][N][N][N];
		int[][][][] p = new int[N][N][N][N];
		int[][][][] e = new int[N][N][N][N];
		
		if(b[0][0] == 0){
			e[0][0][0][0] = 1;
		}
		else if(b[0][0] == 1){
			a[0][0][0][0] = 1;
		}
		else{
			p[0][0][0][0] = 1;
		}
		
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				for(int k = i; k < N; k++){
					for(int l = j; l < N; l++){
						if(b[k][l] == 0){
							if(i == k && j == l){
								e[i][j][k][l] = 1;
								a[i][j][k][l] = 0;
								p[i][j][k][l] = 0;
							}
							else if(i == k){
								e[i][j][k][l] = e[i][j][k][l-1] + 1;
								a[i][j][k][l] = a[i][j][k][l-1];
								p[i][j][k][l] = p[i][j][k][l-1];
							}
							else if(j == l){
								e[i][j][k][l] = e[i][j][k-1][l] + 1;
								a[i][j][k][l] = a[i][j][k-1][l];
								p[i][j][k][l] = p[i][j][k-1][l];
							}
							else{
								e[i][j][k][l] = e[i][j][k-1][l] + e[i][j][k][l-1] - e[i][j][k-1][l-1]  + 1;
								a[i][j][k][l] = a[i][j][k-1][l] + a[i][j][k][l-1] - a[i][j][k-1][l-1];
								p[i][j][k][l] = p[i][j][k-1][l] + p[i][j][k][l-1] - p[i][j][k-1][l-1];
							}
						}
						else if(b[k][l] == 1){
							if(i == k && j == l){
								e[i][j][k][l] = 0;
								a[i][j][k][l] = 1;
								p[i][j][k][l] = 0;
							}
							else if(i == k){
								e[i][j][k][l] = e[i][j][k][l-1];
								a[i][j][k][l] = a[i][j][k][l-1] + 1;
								p[i][j][k][l] = p[i][j][k][l-1];
							}
							else if(j == l){
								e[i][j][k][l] = e[i][j][k-1][l];
								a[i][j][k][l] = a[i][j][k-1][l] + 1;
								p[i][j][k][l] = p[i][j][k-1][l];
							}
							else{
								e[i][j][k][l] = e[i][j][k-1][l] + e[i][j][k][l-1] - e[i][j][k-1][l-1];
								a[i][j][k][l] = a[i][j][k-1][l] + a[i][j][k][l-1] - a[i][j][k-1][l-1]  + 1;
								p[i][j][k][l] = p[i][j][k-1][l] + p[i][j][k][l-1] - p[i][j][k-1][l-1];
							}
						}
						else{
							if(i == k && j == l){
								e[i][j][k][l] = 0;
								a[i][j][k][l] = 0;
								p[i][j][k][l] = 1;
							}
							else if(i == k){
								e[i][j][k][l] = e[i][j][k][l-1];
								a[i][j][k][l] = a[i][j][k][l-1];
								p[i][j][k][l] = p[i][j][k][l-1] + 1;
							}
							else if(j == l){
								e[i][j][k][l] = e[i][j][k-1][l];
								a[i][j][k][l] = a[i][j][k-1][l];
								p[i][j][k][l] = p[i][j][k-1][l] + 1;
							}
							else{
								e[i][j][k][l] = e[i][j][k-1][l] + e[i][j][k][l-1] - e[i][j][k-1][l-1];
								a[i][j][k][l] = a[i][j][k-1][l] + a[i][j][k][l-1] - a[i][j][k-1][l-1];
								p[i][j][k][l] = p[i][j][k-1][l] + p[i][j][k][l-1] - p[i][j][k-1][l-1]  + 1;
							}
						}
					}
				}
			}
		}
		int ans = 1;
		for(int i = 0; i < N; i++){
			for(int j = 0; j < N; j++){
				for(int k = i; k < N; k++){
					for(int l = j; l < N; l++){
						{
							int c = a[i][j][k][l];
							int size = (k - i + 1) * (l - j + 1);
							int tarinai = size - c;
							if(tarinai == 0){
								ans = Math.max(size, ans);
							}
							else if(E > 0 && size <= A){
								int inE = e[i][j][k][l];
								int inP = p[i][j][k][l];
								int cnt = inE + inP*2;
								if(cnt <= K){
									ans = Math.max(ans, size);
								}
							}
						}
						{
							int c = p[i][j][k][l];
							int size = (k - i + 1) * (l - j + 1);
							int tarinai = size - c;
							if(tarinai == 0){
								ans = Math.max(size, ans);
							}
							else if(E > 0 && size <= P){
								int inE = e[i][j][k][l];
								int inA = a[i][j][k][l];
								int cnt = inE + inA*2;
								if(cnt <= K){
									ans = Math.max(ans, size);
								}
							}
						}
						{
							int c = e[i][j][k][l];
							int size = (k - i + 1) * (l - j + 1);
							int tarinai = size - c;
							if(size <= E && tarinai <= K){
								ans = Math.max(ans, size);
							}
						}
					}
				}
			}
		}
		
		return ans;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplesAndPears t = new ApplesAndPears();

		String[] b = {"AP", "PA"};
		
	
		int r = t.getArea(b, 2);
		out.println(r);
	}

}
