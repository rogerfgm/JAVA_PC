package srm576;
import java.util.*;
import static java.lang.Math.*;

public class ArcadeManao {

	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	
	
	public int shortestLadder(String[] lev, int cR, int cC){
		int R = lev.length;
		int C = lev[0].length();
		cR--;
		cC--;
		boolean[][] lv = new boolean[R][C];
		for(int i = 0; i < R; i++){
			String s = lev[i];
			for(int j = 0; j < C; j++){
				char c = s.charAt(j);
				if(c == 'X'){
					lv[i][j] = true;
				}
			}
		}
		int[][] d = new int[R][C];
		for(int i = 0; i < R; i++){
			for(int j = 0; j < C; j++){
				d[i][j] = INF;
			}
		}
		
		d[cR][cC] = 0;
		PriorityQueue<Data> que = new PriorityQueue<Data>(100, new Comparator<Data>() {

			public int compare(Data d1, Data d2) {
				return d1.d - d2.d;
			}
		});
		Data fst = new Data(0, cR, cC);
		que.add(fst);
		while(que.size() > 0){
			Data dt = que.poll();
			int r = dt.r;
			int c = dt.c;
		
			if(dt.d > d[r][c]) continue;
			for(int i = r-1; i >= 0; i--){
				if(lv[i][c]){
					int len = r-i;
					if(d[i][c] > max(len, dt.d)){
						d[i][c] = max(len, dt.d);
						Data ndt = new Data(max(len, dt.d), i, c);
						que.add(ndt);
					}
					break;
				}
			}
			for(int i = r+1; i < R; i++){
				if(lv[i][c]){
					int len = i - r;
					if(d[i][c] > max(len, dt.d)){
						d[i][c] = max(len, dt.d);
						Data ndt = new Data(max(len, dt.d), i, c);
						que.add(ndt);
					}
					break;
				}
			}
			if(c-1 >= 0 && lv[r][c-1]){
				if(d[r][c-1] > dt.d){
					d[r][c-1] = dt.d;
					Data ndt = new Data(dt.d, r, c-1);
					que.add(ndt);
				}
			}
			if(c+1 < C && lv[r][c+1]){
				if(d[r][c+1] > dt.d){
					d[r][c+1] = dt.d;
					Data ndt = new Data(dt.d, r, c+1);
					que.add(ndt);
				}
			}
		}
		return d[R-1][0];
	}
	
	class Data{
		public Data(int d, int r, int c ){
			this.d = d;
			this.r = r;
			this.c = c;
		}
		int d = 0;
		int r = 0;
		int c = 0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArcadeManao t = new ArcadeManao();
		String[] in = {"X",
				 };
		int ret = t.shortestLadder(in, 1, 1);
		System.out.println(ret);
	}

}
