package accept;
import java.util.*;
import java.io.*;


public class POJ2676 {
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;

	int n = 0;

	int[][] d = new int[9][9];
	public void solve() throws Exception{
		for(int i = 0; i < 9; i++){
			String s = sc.next();
			for(int j = 0; j < 9; j++){
				d[i][j] = s.charAt(j) - '0';
			}
		}
		
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(d[i][j] == 0){
					{
						Set<Integer> set = new HashSet<Integer>();
						set.add(0);
						for(int x = 0; x < 9; x++){
							set.add(d[i][x]);
						}
						if(set.size() == 9){
							for(int k = 1; k <= 9; k++){
								if(!set.contains(k)){
									d[i][j] = k;
								}
							}
							continue;
						}
					}
					{
						Set<Integer> set = new HashSet<Integer>();
						set.add(0);
						for(int x = 0; x < 9; x++){
							set.add(d[x][j]);
						}
						if(set.size() == 9){
							for(int k = 1; k <= 9; k++){
								if(!set.contains(k)){
									d[i][j] = k;
								}
							}
							continue;
						}
					}
					{
						Set<Integer> set = new HashSet<Integer>();
						set.add(0);
						int si = (i / 3) * 3;
						int sj = (j / 3) * 3;
						for(int x = si; x < si+3; x++){
							for(int y = sj; y < sj + 3; y++){
								set.add(d[x][y]);
							}
						}
						if(set.size() == 9){
							for(int k = 1; k <= 9; k++){
								if(!set.contains(k)){
									d[i][j] = k;
								}
							}
							continue;
						}
					}
				}
			}
		}
		
		
		calc(0, 0);
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				System.out.print(d[i][j]);
			}
			System.out.println("");
		}
	}

	boolean calc(int i, int j){
		if(i == 9){
			return true;
		}
		int nexti = i;
		int nextj = j + 1;
		if(j == 8){
			nexti++;
			nextj = 0;
		}
	
		if(d[i][j] != 0){
			return calc(nexti, nextj);
		}
		for(int k = 1; k <= 9; k++){
			boolean flag = true;
			for(int x = 0; x < 9; x++){
				if(x != j && d[i][x] == k){
					flag = false;
				}
			}
			if(!flag)continue;
			for(int x = 0; x < 9; x++){
				if(x != i && d[x][j] == k){
					flag = false;
				}
			}
			if(!flag)continue;
			int si = (i / 3) * 3;
			int sj = (j / 3) * 3;
			for(int x = si; x < si + 3; x++){
				for(int y = sj; y < sj + 3; y++){
					if( !(x == i && y == j ) && d[x][y] == k){
						flag = false;
					}
				}
			}
			if(flag){
				d[i][j] = k;
				if(calc(nexti, nextj)){
					return true;
				}
			}
		}
		d[i][j] = 0;
		return false;
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
		sc =  new Scanner(System.in);
		POJ2676 t = new POJ2676();
		int T = sc.nextInt();
		while(T-- > 0){
			t.solve();
		}
	}


}