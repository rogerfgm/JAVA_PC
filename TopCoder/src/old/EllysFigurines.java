package old;
import java.util.*;

public class EllysFigurines {

	int R = 0;
	int C = 0;
	int r = 0;
	int c = 0;
	boolean[][] d = null;
	
	public int getMoves(String[] b, int R, int C){
		this.R = R;
		this.C = C;
	
		d = new boolean[b.length][b[0].length()];
		for(int i = 0; i < b.length; i++){
			String s = b[i];
			for(int j = 0; j < s.length(); j++){
				if(s.charAt(j) == 'X'){
					d[i][j] = true;
				}
			}
		}
		r = d.length;
		c = d[0].length;
		int n = 1 << r;
		int ret = 1000000;
		for(int i = 0; i < n; i++){
			int cnt = 0;
			for(int j = 0; j < r; j++){
				if(( (1 << j) & i) > 0){
					cnt++;
					j += R-1;
				}
			}
			ret = Math.min(ret, check(i) + cnt);
		}
		
		return ret;
	}
	
	int check(int m){
		int ret = 0;
		for(int j = 0; j < c; j++){
			boolean f = false;
			for(int i = 0;i < r; i++){
				if( (1 << i & m) > 0){
					continue;
				}
				if(d[i][j]){
					f = true;
				}
			}
			if(f){
				j += C-1;
				ret++;
			}
		}
		
		return ret;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EllysFigurines e = new EllysFigurines();
		String[] b = {"XXXXXXX"};
		int ret = e.getMoves(b, 2, 3);
		System.out.println(ret);

	}

}
