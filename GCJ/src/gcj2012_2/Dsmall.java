package gcj2012_2;


import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Dsmall {
	static Scanner sc = null;
	static PrintWriter out = null;
	
	int INF = Integer.MAX_VALUE / 10;
	double DF = 0.0000000001;
	boolean[][] f = null;
	boolean[][] used = null;
	int R = 0;
	int C = 0;
	public void solve() throws Exception{
		R = sc.nextInt();
		C = sc.nextInt();
		int[][] cs = new int[10][2];
		for(int i = 0; i < 10;i++){
			cs[i][0] = cs[i][1] = -1;
		}
		f = new boolean[R][C];
		for(int i = 0; i < R; i++){
			String s = sc.next();
			for(int j = 0; j < C; j++){
				char c = s.charAt(j);
				if(c == '#'){
				}
				else{
					f[i][j] = true;
					if(c != '.'){
						int n = c - '0';
						cs[n][0] = i;
						cs[n][1] = j;
					}
				}
			}
		}
		for(int i = 0; i < 10; i++){
			if(cs[i][0] == -1){
				break;
			}
			List<P> list = new ArrayList<P>();
			used = new boolean[R][C];
			int y = cs[i][0];
			int x = cs[i][1];
			dfs(y, x, list);
			int n = list.size();
			boolean flag = true;
			
			while(true){
				
				flag = move(list, y, x, 1);
				flag |= move(list, y, x, -1);
				flag = move(list, y, x, 1);
				if(!flag){
					break;
				}
			}
			out.print(i + ": " +  n + " ");
			x = list.get(0).x;
			y = list.get(0).y;
			flag = true;
			for(int j = 1; j < list.size(); j++){
				P p = list.get(j);
				if(p.x != x || p.y != y){
					flag = false;
				}
			}
			
			if(flag){
				out.println("Lucky");
			}
			else{
				out.println("Unlucky");
			}
		}
	}
	
	boolean move(List<P> list, int y, int x, int mv){
		boolean flag = false;
		for(int j = 0; j < C; j++){
			boolean error = false;
			boolean candown = false;
			for(P p : list){
				if(p.y < R-1 && f[p.y + 1][p.x] && used[p.y + 1][p.x]){
					candown = true;
				}
				else if(p.y < R-1 && f[p.y + 1][p.x] && !used[p.y + 1][p.x]){
					error = true;
				}
			}
			if(!error && candown){
				flag = true;
				for(P p : list){
					if(p.y < R-1 && used[p.y + 1][p.x]){
						p.y++;
					}
				}
			}
			for(int k = list.size()-1; k >= 0; k--){
				P p = list.get(k);
				if(p.x + mv >= 0 && p.x + mv < C && used[p.y][p.x+mv]){
					p.x += mv;
				}
			}
			
		}
		return flag;
	}

	void dfs(int y, int x, List<P> list){
		used[y][x] = true;
		list.add(new P(y, x));
		if(x > 0 && !used[y][x-1] && f[y][x-1]){
			dfs(y, x-1, list);
		}
		if(x < C-1 && !used[y][x+1] && f[y][x+1]){
			dfs(y, x+1, list);
		}
		if(y > 0 && !used[y-1][x] && f[y-1][x]){
			dfs(y-1, x, list);
		}
	}
	
	class P{
		public P(int y, int x){
			this.y = y;
			this.x = x;
		}
		int y = 0;
		int x = 0;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("D-large-practice.in");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream(file)));
		}
		sc =  new Scanner(System.in);
		FileWriter fw = new FileWriter(new File("output.txt"));
		out = new PrintWriter(fw);
		
		Dsmall b = new Dsmall();
		int T = sc.nextInt();
		int t = 1;
		while(t <= T){
			out.println("Case #" + t + ":");
			b.solve();
			t++;
		}
		out.close();
		fw.close();
	}
}
