


import java.io.*;
import java.util.*;
import java.math.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;



// TLE on 1
public class D188 {
	int INF = Integer.MAX_VALUE / 100;
	static Scanner sc = null;
	static BufferedReader br = null;
	static PrintStream out = null;
	static BufferedWriter bw = null;
	int N = 0;
	
	public void solve() throws Exception{
		String[] tps = br.readLine().split(" ");
		int n = parseInt(tps[0]);
		int t = parseInt(tps[1]);
		out.println(new Date());
		n = 30000;
		int b = 65;
		
		int SZ = b * 2 + 2;
		int[][] d = new int[SZ][SZ];
		d[0+b][0+b] = n;
		while(true){
			boolean f = false;
			int[][] nd = new int[SZ][SZ];
			for(int i = 0; i < SZ; i++){
				for(int j = 0; j < SZ; j++){
					if(d[i][j] >= 4){
						f = true;
						int ml = d[i][j] / 4;
						nd[i][j] += d[i][j] % 4;
						nd[i][j+1]+=ml;
						nd[i][j-1]+=ml;
						nd[i+1][j]+=ml;
						nd[i-1][j]+=ml;
					}
					else{
						nd[i][j] += d[i][j];
					}
				}
			}
			d = nd;
			if(!f){
				break;
			}
		}
//		for(int i = 0; i < SZ; i++){
//			for(int j = 0; j < SZ; j++){
//				out.print(d[i][j] + " ");
//			}
//			out.println();
//		}
		
		
		for(int i = 0; i < t; i++){
			String s = br.readLine();
			String[] sp = s.split(" ");
			int x = parseInt(sp[0]);
			int y = parseInt(sp[1]);
			x += b;
			y += b;
			if(x < 0 || y < 0 || x >= SZ || y >= SZ){
				out.println("0");
			}
			else{
				out.println(d[y][x]);
			}
		}
		out.println(new Date());
	}
	
	void fast(int[][] d, int y, int x){
		int ml = d[y][x];
		d[y][x] = d[y][x] % 4;
		d[y+1][x] += ml;
		d[y-1][x] += ml;
		d[y][x+1] += ml;
		d[y][x-1] += ml;
		if(d[y+1][x] >= 4){
			fast(d, y+1, x);
		}
		if(d[y-1][x] >= 4){
			fast(d, y-1, x);
		}
		if(d[y][x+1] >= 4){
			fast(d, y, x+1);
		}
		if(d[y][x-1] >= 4){
			fast(d, y, x-1);
		}
	}
	

    public int[] readIntArray(int n) {
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = sc.nextInt();
        }
        return ret;
    }

	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		File file = new File("input.txt");
		if(file.exists()){
			System.setIn(new BufferedInputStream(new FileInputStream("input.txt")));
		}
		out = System.out;
		bw = new BufferedWriter(new PrintWriter(out));
		//sc =  new Scanner(System.in);
		br = new BufferedReader(new InputStreamReader(System.in));
		D188 t = new D188();
		t.solve();
		bw.close();
	}

}
