package srm594;
import java.util.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import java.io.*;

public class NOTFoxAndGo3 {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	static PrintStream out = System.out;
	final int MOD = 1000000007;
	
	int N = 0;
	int[] b = null;
	Map<Integer, Integer> map = null;
	Set<Integer> set = null;
	List<Integer> list = null;
	
	
	public int maxEmptyCells(String[] bo){
		int a = 0;
	
		int n = bo.length;
		int[][]b = new int[n+2][n+2];
		int W = 2;
		int B = 0;
		int S = 1;
		for(int i = 0; i < bo.length; i++){
			String s = bo[i];
			for(int j = 0; j < s.length(); j++){
				if(s.charAt(j) == '.'){
					a++;
					b[i+1][j+1] = S;
				}
				else if(s.charAt(j) == 'o'){
					b[i+1][j+1] = W;
				}
				else{
					
				}
			}
		}
		for(int i = 0; i < b.length; i++){
			for(int j = 0 ; j < b.length; j++){
				if(b[i][j] == W){
					out.print("o");
				}
				else if(b[i][j] == S){
					out.print(".");
				}
				else{
					out.print("x");
				}
			}
			out.println("");
		}
		out.println("");;

		

		
		while(true){
			boolean flag = false;

			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					int y = i+1;
					int x = j+1;
					if(b[y][x] == W && b[y-1][x] == B && b[y+1][x] == B && b[y][x-1] == B && b[y][x+1] == B){
						b[y][x] = S;
						a++;
						flag = true;
					}
				}
			}
			if(flag) continue;
			
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					int y = i+1;
					int x = j+1;
					for(int k = 1; k <=n; k++){
						if(i + k > n || j + k > n) continue;
						
						int wc = 0;
						Set<Integer> set = new HashSet<Integer>();
						for(int m = 0; m < k; m++){
							for(int l = 0; l < k; l++){
								if(b[y+m][x+l] == W){
									if(b[y+m-1][x+l] == S){
										int idx = (y + m - 1) * (n+1) + (x + l);
										set.add(idx);
									}
									if(b[y+m+1][x+l] == S){
										int idx = (y + m + 1) * (n+1) + (x + l);
										set.add(idx);
									}
									if(b[y+m][x+l-1] == S){
										int idx = (y + m) * (n+1) + (x + l - 1);
										set.add(idx);
									}
									if(b[y+m][x+l+1] == S){
										int idx = (y + m) * (n+1) + (x + l + 1);
										set.add(idx);
									}
									wc++;
								}
							}
						}
						if(wc > 0 && wc >= set.size()){
							a += wc - set.size();
							for(int m = 0; m < k; m++){
								for(int l = 0; l < k; l++){
									if(b[y+m][x+l] == W){
										b[y+m-1][x+l] = B;
										b[y+m+1][x+l] = B;
										b[y+m][x+l-1] = B;
										b[y+m][x+l-1] = B;
										b[y+m][x+l] = S;
									}
								}
							}
							flag = true;
							break;
						}
						
					}
					if(flag){
						break;
					}
				}
				if(flag){
					break;
				}
			}
			
			for(int i = 0; i < b.length; i++){
				for(int j = 0 ; j < b.length; j++){
					if(b[i][j] == W){
						out.print("o");
					}
					else if(b[i][j] == S){
						out.print(".");
					}
					else{
						out.print("x");
					}
				}
				out.println("");
			}
			out.println("");;
			


			
			if(!flag){
				break;
			}
		}
		
		
		return a;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NOTFoxAndGo3 t = new NOTFoxAndGo3();
	String[] s ={".xox.",
			     ".o.ox",
			     "x.o.o",
			     "ox.ox",
			     ".ox.."};
		int r = t.maxEmptyCells(s);
		out.println(r);
	}

}
