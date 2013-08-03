package d171;
import java.util.*;
import java.io.*;
import java.math.*;





public class D {
	
	int INF = Integer.MAX_VALUE / 1000;
	static Scanner sc = null;
	int n = 0;
	int[] d = null;
	boolean[] dp = null;
	public void solve() throws Exception{

		n = sc.nextInt();
		d = readIntArray(n);		
		dp = new boolean[1 << n];
		dp[1] = true;
		
		int[][] data = new int[n][2];
		for(int i = 0; i < n; i++){
			data[i][0] = d[i];
			data[i][1] = i;
		}
		Arrays.sort(data, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[0] != o2[0]){
					return o1[0] - o2[0];
				}
				return o1[1] - o2[1];
			}
		});
		
		int[] values = new int[n];
		int[] indexes = new int[n];
		for(int i = 0; i < n; i++){
			values[i] = data[i][0];
			indexes[i] = data[i][1];
		}
		
		List<List<Integer>> imasks = new ArrayList<List<Integer>>();
		imasks.add(new ArrayList<Integer>());
		for(int i = 1; i < n; i++){
			List<Integer> l = new ArrayList<Integer>();
			imasks.add(l);
			int target = d[i];
			for(int j = 0; j < i; j++){
				for(int k = j; k < i; k++){
					if(d[j] + d[k] == target){
						int mask = 1 << j | 1 << k;
						if(!l.contains(mask)){
							l.add(mask);
						}
					}
				}
			}
		}
		

		for(int i = 1; i < n; i++){
			int max = 1 << i;
			boolean[] ndp = new boolean[1 << n];
			for(int j = 0; j < max; j++){
				if(dp[j]){
					boolean flag = false;
					List<Integer> masks = imasks.get(i);
					for(int mask : masks){
						if( (mask & j) == mask){
							flag = true;
							break;
						}
					}
					/*
					for(int k = 0; k < i; k++){
						if( ((1 << k) & j) > 0){
							int target = d[i] - d[k];
							int pos = Arrays.binarySearch(values, target);
							if(pos >= 0){
								while(values[pos] == target){
									int index = indexes[pos];
									if(index >= i){
										break;
									}
									if( (j & 1 << index) > 0){
										flag = true;
										break;
									}
									pos++;
								}
							}
						}
						if(flag){
							break;
						}
					}
					*/
					if(flag){
						int nj = j | (1 << i);
						ndp[nj] = true;
						for(int p = 0; p < i; p++){
							int mask = 1 << p;
							if( (j & mask) > 0){
								ndp[(j^mask) | 1 << i ] = true;
							}
						}
					}
				}
			}
			dp = ndp;
		}
		int ans = INF;
		for(int i = 0; i < 1 << n; i++){
			if(dp[i]){
				int tmp = i;
				int num = 0;
				while(tmp > 0){
					if( (tmp & 1) > 0){
						num++;
					}
					tmp = tmp >> 1;
				}
				ans = Math.min(ans, num);
			}
		}
		if(ans != INF){
			System.out.println(ans);
		}
		else{
			System.out.println("-1");
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
		sc =  new Scanner(System.in);
		D t = new D();
		t.solve();
		
	}
	
	
}