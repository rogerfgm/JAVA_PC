package gcj2011;


import java.io.*;
import java.util.Arrays;
import java.util.*;
import static java.lang.Math.*;



public class B {

	public void run() throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader("B-small-practice.in"));
		BufferedWriter bw = new BufferedWriter(new FileWriter("OUTPUT.txt"));
		
		
		
		String s = br.readLine();
		int T = Integer.parseInt(s);
		int t = 1;
		while(t <= T){
			s = br.readLine();
			String[] ss = s.split(" ");
			int N = Integer.parseInt(ss[0]);
			int W = Integer.parseInt(ss[1]);
			int H = Integer.parseInt(ss[2]);
			
			s = br.readLine();
			ss = s.split(" ");
			int[][] len = new int[N][2];
			for(int i = 0; i < N; i++){
				len[i][1] = i;
				len[i][0] = Integer.parseInt(ss[i]);
			}
			
			Arrays.sort(len, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[0] == o2[0]){
						return o1[1] - o2[1];
					}
					return o2[0] - o1[0];
				}
			});
			
			int[][] ans = null;
			Random rnd = new Random();
			while(true){
				ans = new int[N][2];
				ans[len[0][1]][0] = 0;
				ans[len[0][1]][1] = 0;
				if(N == 1){
					break;
				}
				ans[len[1][1]][0] = W;
				ans[len[1][1]][1] = H;
				if(N == 2){
					break;
				}
				List<int[]> usedList = new ArrayList<int[]>();
				
				int[] used = {0, 0, len[0][0]};
				usedList.add( used);
				used = new int[3];
				used[0] = W;
				used[1] = H;
				used[2] = len[1][0];
				usedList.add(used);
				
				boolean success = true;
				for(int i = 2; i < N; i++){
			        
					boolean found = false;
					for(int ttt = 0; ttt < 100; ttt++){
				        int x = rnd.nextInt(W);
				        int y = rnd.nextInt(H);
				        int l = len[i][0];
				        int ind = len[i][1];
				        
				        boolean kabu = false;
				        for(int j = 0; j < usedList.size(); j++){
				        	int[] us = usedList.get(j);
				        	int ux = us[0];
				        	int uy = us[1];
				        	int ul = us[2];
				        	{
				        		int cx = ux - ul;
				        		int cy = uy - ul;
				        		if(cx > x - l && cx < x + l && cy > y - l && cy < y + l){
				        			kabu = true;
				        			break;
				        		}
				        	}
				        }
				        if(!kabu){
				        	found = true;
				        	int[] usd = new int[3];
				        	usd[0] = x;
				        	usd[1] = y;
				        	usd[2] = l;
				        	usedList.add(usd);
				        	ans[ind][0] = x;
				        	ans[ind][1] = y;
				        	break;
				        }
				        
					}
					if(!found){
						success = false;
						break;
					}
				}
				if(success){
					break;
				}
				break;
			}
			bw.write("Case #" + t + ":");
			try{
			for(int i = 0; i < ans.length; i++){
				bw.write(" " + ans[i][0] + " " + ans[i][1]);
			}
			}
			catch(Exception ex){
				System.out.print("");
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
		B b = new B();
		b.run();
	}

}
