package gcj2011;


import java.io.*;
import java.util.Arrays;
import java.util.*;




public class B2011 {

	public void run() throws Exception{
		
		//BufferedReader br = new BufferedReader(new FileReader("B-small-practice.in"));
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.txt"));
		
		
		
		String s = br.readLine();
		int T = Integer.parseInt(s);
		int t = 1;
		while(t <= T){
			s = br.readLine();
			String[] ss = s.split(" ");
			int R = Integer.parseInt(ss[0]);
			int C = Integer.parseInt(ss[1]);
			int D = Integer.parseInt(ss[2]);

			int[][] data = new int[R][C];
			for(int i = 0; i < R; i++){
				s = br.readLine();
				for(int j = 0; j < C; j++){
					data[i][j] = Integer.parseInt(s.substring(j, j+1)) + D;
				}
			}
			
			int CUT = (Math.min(R, C) - 1) / 2;
			int[][][][] sikaku = new int[R][C][CUT + 1][2];
			int[][][][] taikaku = new int[R][C][CUT + 1][2];
		
			
			
			for(int k = 1; k <= CUT; k++){
				for(int i = 0; i < R; i++){
					for(int j = 0; j < C; j++){
						if(i - k < 0 || i + k >= R || j - k < 0 || j + k >= C){
							continue;
						}
						int xsum = 0;
						int ysum = 0;
						for(int m = -k; m <= k; m++){
							xsum -= data[i-k][j + m] * k ;
							xsum += data[i+k][j+m] * k;
							ysum -= data[i+m][j-k] * k ;
							ysum += data[i+m][j+k] * k ;
						}
						
						for(int m = -k + 1; m <= k -1; m++){
							xsum += data[i + m][j - k] * m;
							xsum += data[i + m][j + k] * m;
							ysum += data[i - k][j + m] * m;
							ysum += data[i + k][j + m] * m;
						}
						
						
						sikaku[i][j][k][0] = xsum;
						sikaku[i][j][k][1] = ysum;
						
						taikaku[i][j][k][0] -= data[i-k][j-k] * k;
						taikaku[i][j][k][0] -= data[i-k][j+k] * k;
						taikaku[i][j][k][0] += data[i+k][j-k] * k;
						taikaku[i][j][k][0] += data[i+k][j+k] * k;
						
						taikaku[i][j][k][1] -= data[i-k][j-k] * k;
						taikaku[i][j][k][1] -= data[i+k][j-k] * k;
						taikaku[i][j][k][1] += data[i-k][j+k] * k;
						taikaku[i][j][k][1] += data[i+k][j+k] * k;
					}
				}
			}
			
			int ans = 0;
			for(int k = 1; k <= CUT; k++){
				for(int i = 0; i < R; i++){
					for(int j = 0; j < C ; j++){
						if(i - k < 0 || i + k >= R || j - k < 0 || j + k >= C){
							continue;
						}
						int xsum = 0;
						int ysum = 0;
						for(int m = 1; m <= k; m++){
							xsum += sikaku[i][j][m][0];
							ysum += sikaku[i][j][m][1];
						}
						xsum -= taikaku[i][j][k][0];
						ysum -= taikaku[i][j][k][1];
						if(xsum == 0 && ysum == 0){
							ans = k;
						}
					}
				}
			}
			
			
			
			bw.write("Case #" + t + ": ");
			if(ans == 0){
				bw.write("IMPOSSIBLE");
			}
			else{
				ans = ans * 2 + 1;
				bw.write(ans + "");
			}
			
			bw.write("\n");
			t++;
		}
		bw.close();
		br.close();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		B2011 b = new B2011();
		b.run();
	}

}
